/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package summative;
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
    * Method is used to draw the image on the screen
    */
    public void draw(){
        app.image(image,x,y);
    }
    
    /*
    * Method is used to draw the image on the screen with desired location
    */
    public void draw(int x, int y){
        app.image(image,x,y);
    }
    
    /*
    * Method is used to resize the image and their width and height for collision
    */
    public void resize(int x, int y){
        image.resize(x,y);
        this.width = image.width;
        this.height = image.height;
    }
}
