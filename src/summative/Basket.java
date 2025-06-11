/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 342335817
 */
// Imports
package summative;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * This is the basket class which is meant to make a basket to collect dates
 * @author 342335817
 */
public class Basket extends Things{

    public Basket(PApplet p, int x, int y, String imagePath){
        super(p,x,y,imagePath);
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
    * Method is used to move the basket right on the screen
    * @param - has dx parameters for movement
    */
    public void moveRight(){
        x += 5;
    }
    
    /*
    * Method used to move the basket left on the screen
    * @param - has dx parameters for movement
    */
    
    public void moveLeft(){
        x -= 5;
    }
    
}
