/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package summative;
import processing.core.PApplet;
import processing.core.PImage;
/**
 *
 * @author 342335817
 */
public class PersonBasket extends Person{
    // Association for the person to have a basket
    private Basket dateBasket;
    
    public PersonBasket(PApplet p, int x, int y, String name, String imagePath, String imagePathB){
        super(p,x,y,name,imagePath);
        dateBasket = new Basket(p, x+20, y+20, imagePathB);
        dateBasket.resize(60,60);
    }
    
    /*
    * Draw method to draw the person and basket on the screen
    */
    @Override
    public void draw(){
        super.draw();
        dateBasket.x = this.x + 20;
        dateBasket.y = this.y + 20;
        dateBasket.draw();
    }
    
    /*
    * Draw method to draw the person and basket on the screen with the desired location
    */
    @Override
    public void draw(int x, int y){
        app.image(image,x,y);
        app.text(getName(), x, y - 8);
        dateBasket.x = x + 20;
        dateBasket.y = y + 25;
        dateBasket.draw();
    }
}
