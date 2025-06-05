/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package processingexample;
import processing.core.PApplet;
import processing.core.PImage;

/*
* This class is used to make a person and display it on the screen with customization
* @author Aydin Hussain
*/
public class MySketch extends PApplet { 
  private Person person;
  String userInput = "";
  int stage = 0;
  boolean showInfo;
  PImage bg;
  int screen = 1;
  
  /*
  * 
  */
  public void settings() {
    size(400, 400);
  }

  public void setup() {
    //background(bg);
    bg = loadImage("images/homePage.jpg");
    bg.resize(width,height);
    person = new Person(this,150,350,"Mr Lu", 16, "images/person.png");
    textSize(20);
  }
  
  public void draw(){
      // Home Page
      if (screen == 1){
        background(bg);
      }
      
      person.draw();   
      
      if (keyPressed){
          if (keyCode == LEFT){
              person.move(-5,0);
          }
          else if (keyCode == RIGHT){
              person.move(5,0);
          }
          else if (keyCode == UP){
              person.move(0,-5);
          }
          else if (keyCode == DOWN){
              person.move(0,5);
          }
      }
  }
  
  public void mousePressed(){
      if (person.isClicked(mouseX, mouseY)){
          showInfo = !showInfo;
    }
      else {
          showInfo = false;
      } 
  }
 
}//end class

