/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package processingexample;
import processing.core.PApplet;
import processing.core.PImage;
/**
 *
 * @author tpss8
 */
public class Things {
    // Variable declaration
    public int x, y;
    public PImage image;
    public PApplet app;
    public int width, height;
    
    public Things(PApplet p, int x, int y, String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }    
    /*
    * Method is used to draw the person on the screen
    */
    public void draw(){
        app.image(image,x,y);
    }
}