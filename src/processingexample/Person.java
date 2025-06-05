/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Imports
package processingexample;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This is the person class which includes everything needed for user interaction
 * @author Aydin Hussain
 */
public class Person {
    // Variable declaration
    int x, y;
    private String name;
    private int age;
    private PImage image;
    private PApplet app;
    int width, height;
    
    public Person(PApplet p, int x, int y, String name, int age, String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.age = age;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    /*
    * This method is for mouse clicking interaction
    * @param - has mouse coordinate parameters for the location of the click
    */
    
    public boolean isClicked(int mouseX, int mouseY){
        int centerX = x + (image.pixelWidth/2);
        int centerY = y + (image.pixelHeight/2);
        float d = PApplet.dist(mouseX, mouseY, centerX, centerY);
        System.out.println("image height" + image.pixelHeight);
        System.out.println("image width"+ image.pixelWidth);
        return d < 16;
    }
    
    
    public void displayInfo(PApplet p){
        app.fill(0);
        
        app.text("Name: " + name, x, y + 50);
        app.text("Age: " + age, x, y + 30);
    }
    
    /*
    * Method is used for collision detection
    * @param - has the parameter of a Person object for collision detection
    */
    
    public boolean isCollidingWith(Person other){
        boolean isLeftOfOtherRight = x < other.x + other.width;
        boolean isRightOfOtherLeft = x + other.x < other.width;
        boolean isAboveOtherBottom = y < other.y + other.height;
        boolean isBelowOtherTop = y + height > other.y;
        
        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }
    
    /*
    * Method is used to move the person on the screen
    * @param - has dx and dy parameters for movement
    */
    
    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }
    
    /*
    * Method is used to draw the person on the screen
    */

    public void draw(){
        app.image(image,x,y);
    }
    
    /*
    * Method is used to move to somewhere on click
    * @param dx and dy coordinates for the location
    */
    
    public void moveTo(int dx, int dy){
        x = dx;
        y = dy;
    }
   
}
