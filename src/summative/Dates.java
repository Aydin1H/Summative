/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Imports
package summative;
import processing.core.PApplet;
import processing.core.PImage;

/*
 * This class is used to display falling dates from the sky to be collected
 * @author 342335817
 */
public class Dates extends Things {
    // Variable declaration
    private float speed;
    private int delayFrames;  // frames to wait before falling
    private int timer;        // counts frames 
    private boolean caught = false;
    private int datesCaught = 0;

    public Dates(PApplet p, int x, int y, String imagePath){
        super(p, x, y, imagePath);
        image.resize(30,30);
        this.width = image.width;
        this.height = image.height;
        reset();
    }
    
    /*
    * This method is used to spawn dates at a random position. Used random from https://processing.github.io/processing-javadocs/core/processing/core/PApplet.html
    */
    public void reset(){
        // Random x position
        this.x = (int) app.random(50, app.width - 50);
        // Start from top 
        this.y = (int) -app.random(50, 300);
        // Random speed
        this.speed = app.random(2, 5);
        // Random delay before falling starts = 0.5 - 5 seconds
        this.delayFrames = (int) app.random(30, 300);
        this.timer = 0;
        this.caught = false;
    }
    
    /*
    * This method is used to drop the dates when the timer reaches delayFrames https://docs.oracle.com/javase/8/docs/api/java/util/Timer.html
    */
    public void update(){
        // If timer is greater than the delay frames then it drops the date
        if (timer >= delayFrames){
            y += speed;
            if (y > app.height + height){
                reset();
            }        
        } 
        else {
            timer++;
        }
    }

    /*
    * Draws the date on the screen if it is not caught and if the timer is greater than delay frames
    */
    @Override
    public void draw(){
        if (!caught && timer >= delayFrames){
            super.draw();
        }
    }
    
    /*
    * Checks if the date is caught (caught is false until it's true when it's in the basket)
    */
    public boolean isCaught(Basket basket){
        if (caught || timer < delayFrames) {
            return false;
        }
        // Collision detection
        return x + width > basket.x && x < basket.x + basket.width &&
               y + height > basket.y && y < basket.y + basket.height;
    }
    
    /*
    * Method to change the dates to caught
    */
    public void caught(){
        caught = true;
        datesCaught ++;
        reset();
    }   
}
