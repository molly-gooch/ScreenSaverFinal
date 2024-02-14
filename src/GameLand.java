//Game Example
//Lockwood Version 2023-24
// Learning goals:
// make an object class to go with this main class
// the object class should have a printInfo()
//input picture for background
//input picture for object and paint the object on the screen at a given point
//create move method for the object, and use it
// create a wrapping move method and a bouncing move method
//create a second object class
//give objects rectangles
//start interactions/collisions

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.sql.SQLOutput;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class GameLand implements Runnable {

    //Variable Declaration Section
    //Declare the variables used in the program
    //You can set their initial values here if you want

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    //Declare the objects used in the program below
    /** STEP 1: Declare your object and give it a name **/
    public Hero YShark;
    public Hero BShark;
    public Hero PShark;
    public Hero GShark;
    /** STEP 2: Declare an image for your object**/
    public Image backgroundPic;
    public Image bbySharkY;
    public Image bbySharkB;
    public Image bbySharkP;
    public Image bbySharkG;

    public boolean YSharkIsIntersectingBShark;
    public boolean PSharkIsIntersectingGShark;
    public boolean GSharkIsIntersectingYShark;
    public boolean YSharkIsIntersectingPShark;
    public boolean BSharkIsIntersectingGShark;
    public boolean PSharkIsIntersectingBShark;


    // Main method definition: PSVM
    // This is the code that runs first and automatically
    public static void main(String[] args) {
       GameLand ex = new GameLand();   //creates a new instance of the game and tells GameLand() method to run
        new Thread(ex).start();       //creates a thread & starts up the code in the run( ) method
    }

    // Constructor Method
    // This has no return type and has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public GameLand() {
        setUpGraphics(); //this calls the setUpGraphics() method

        //create (construct) the objects needed for the game below
        //for each object that has a picture, load in images as well
        /** STEP 3: Construct a specific Hero **/
        YShark = new Hero(400, 100, -1, -1, 100, 120);
        BShark = new Hero(150, 400, 1, 1, 100, 120);
        PShark = new Hero(400, 300, 1, 1, 100, 120);
        GShark = new Hero(300, 50, 1, -1, 100, 120);
        /** STEP 4: Load in the image for your object **/
        bbySharkY = Toolkit.getDefaultToolkit().getImage("BabySharkYllw.png");
        bbySharkB = Toolkit.getDefaultToolkit().getImage("BabySharkBlue.png");
        bbySharkP = Toolkit.getDefaultToolkit().getImage("BabySharkPinkish.png");
        bbySharkG = Toolkit.getDefaultToolkit().getImage("BabySharkGreen.png");
        backgroundPic = Toolkit.getDefaultToolkit().getImage("Under the Sea Wallpaper.jpg");


        YShark.printInfo();
        System.out.println();
        BShark.printInfo();
        System.out.println();
        PShark.printInfo();
        System.out.println();
        GShark.printInfo();


    }// GameLand()

//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever using a while loop
        while (true) {
            moveThings();  //move all the game objects
            collisions(); //checks for rectangle intersections
            render();  // paint the graphics
            pause(20); // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the image of your objects below:
        /** STEP 5: Draw the image of your object to the screen**/
        g.drawImage(backgroundPic,0,0,WIDTH,HEIGHT,null);

        if (PShark.isAlive==true) {
            g.drawImage(bbySharkY, YShark.xpos, YShark.ypos, YShark.width, YShark.height, null);
        }
        if(BShark.isAlive==true) {
            g.drawImage(bbySharkB, BShark.xpos, BShark.ypos, BShark.width, BShark.height, null);
        }

        g.drawImage(bbySharkP, PShark.xpos, PShark.ypos, PShark.width, PShark.height, null);
        g.drawImage(bbySharkG, GShark.xpos, GShark.ypos, GShark.width, GShark.height, null);

        //dispose the images each time(this allows for the illusion of movement).
        g.dispose();

        bufferStrategy.show();
    }

    public void moveThings() {
        //call the move() method code from your object class
       if(YShark.isAlive){
        YShark.wrappingMove();}
       if(BShark.isAlive){
        BShark.bouncingMove();}
       if(PShark.isAlive){
        PShark.bouncingMove();}
        if(GShark.isAlive){
        GShark.wrappingMove();}
    }

    public void collisions(){
        if (YShark.rec.intersects(BShark.rec) && YSharkIsIntersectingBShark==false) {
            YSharkIsIntersectingBShark=true;
            System.out.println("Ouch");
            BShark.dy=-1*BShark.dy;
            BShark.dx=-1*BShark.dx;
            YShark.height+=10;
            YShark.width+=10;
        }
        if(YShark.rec.intersects(BShark.rec)==false){
            YSharkIsIntersectingBShark=false;
        }

        if(PShark.rec.intersects(GShark.rec) && PSharkIsIntersectingGShark==false){
            PSharkIsIntersectingGShark=true;
            System.out.println("Crash");
            PShark.dx=-1* PShark.dx;
            PShark.dy=-1*PShark.dy;
            GShark.height+=10;
            GShark.width+=10;
        }
        if(PShark.rec.intersects(GShark.rec)==false){
            PSharkIsIntersectingGShark=false;
        }

        if(GShark.rec.intersects(YShark.rec) && GSharkIsIntersectingYShark==false){
            GSharkIsIntersectingYShark=true;
            System.out.println("Switch");
            GShark.dx=-1*GShark.dx;
            GShark.dy=-1*GShark.dy;
            YShark.dx=-1* YShark.dx;
        }
        if(GShark.rec.intersects(YShark.rec)==false){
            GSharkIsIntersectingYShark=false;
        }

        if(YShark.rec.intersects(PShark.rec)&& YSharkIsIntersectingPShark==false){
            YSharkIsIntersectingPShark=true;
            System.out.println("HEHE");
            PShark.dy=-1*PShark.dy;
            PShark.dx=-1*PShark.dx;
            YShark.dx=-1*YShark.dx;
            YShark.dy=-1*YShark.dy;
        }
        if(YShark.rec.intersects(PShark.rec)==false){
            YSharkIsIntersectingPShark=false;
        }

        if(BShark.rec.intersects(GShark.rec)&& BSharkIsIntersectingGShark==false){
            BSharkIsIntersectingGShark=true;
            System.out.println("Big!");
            BShark.height+=10;
            BShark.width+=10;
            GShark.height+=10;
            GShark.width+=10;
        }
        if(BShark.rec.intersects(GShark.rec)==false){
            BSharkIsIntersectingGShark=false;
        }

        if(PShark.rec.intersects(BShark.rec)&& PSharkIsIntersectingBShark==false){
            PSharkIsIntersectingBShark=true;
            System.out.println("OK");
            PShark.height+=15;
            PShark.width+=15;
            BShark.dx=-1*BShark.dx;
            BShark.dy=-1*BShark.dy;
        }
        if(PShark.rec.intersects(BShark.rec)==false){
            PSharkIsIntersectingBShark=false;
        }

            //this grows the Blue Shark
//            BShark.width+=1;
//            BShark.height+=1;

            //this disappears the Blue Shark
//            BShark.isAlive=false;
//            BShark.dy=0;
//            BShark.dx=0;
//            BShark.xpos=2000;

            //below bounces the objects off each other
//            YShark.dx=-1*YShark.dx;
//            YShark.dy=-1*YShark.dy;
//            BShark.dx=-1*BShark.dx;
//            BShark.dy=-1*BShark.dy;

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Game Land");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

}