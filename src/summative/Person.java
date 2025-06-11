/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Imports
package summative;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This is the person class which includes everything needed for user interaction
 * @author Aydin Hussain
 */
public class Person extends Things{
    // Variable declaration
    private String name;
    
    public Person(PApplet p, int x, int y, String name, String imagePath){
        super(p,x,y,imagePath);
        this.name = name;
        image.resize(100,100);
        this.width = image.width;
        this.height = image.height;
    }
     
    
    /*
    * Method is used for collision detection
    * @param - has the parameter of a Person object for collision detection
    */ 
    public boolean isCollidingWith(Things other){
        boolean isLeftOfOtherRight = x + width > other.x;
        boolean isRightOfOtherLeft = x < other.x + other.width;
        boolean isAboveOtherBottom = y + height > other.y;
        boolean isBelowOtherTop = y < other.y + other.height;

        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }

    
    /*
    * Method is used to move the person on the screen with specified speed
    * @param - has dx and dy parameters for movement
    */
    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }
    
     /*
    * Method is used to move the peson on the screen with set speed
    */
    public void move(){
        x += 5;
        y += 5;
    }
    
    /*
    * Method is used to draw the person on the screen with its name
    */
    @Override
    public void draw(){
        app.image(image,x,y);
        app.text( name, x + 15, y - 10);
    }
 
    /*
    * Setter method for name
    * @param - name parameter
    */
    public void setName(String name){
        this.name = name;
    }
    
    /*
    * Getter method for name
    * @return - returns the name
    */
    public String getName(){
        return name;
    }
    
}


