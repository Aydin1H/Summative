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
  private Things door;
  String userInput = "";
  int stage = 0;
  boolean showInfo;
  PImage bg;
  int screen = 1;
  int prompt = 0;
  /*
  * 
  */
  public void settings() {
    size(800, 800);
  }

  public void setup() {
    //background(bg);
    bg = loadImage("images/homePage.jpg");
    bg.resize(width,height);
    person = new Person(this,400,700,"Mr Lu", 16, "images/person.png");
    door = new Things(this,400,100,"images/door.png");
    textSize(20);
  }
  
  /*
  * Method made to use to detect the enter button
  */
  public void keyPressed(){
      //Cannot use in draw because it runs 60 times a minute leading to errors
      if (keyPressed){
       if (key == '\n' || key == '\r'){ // On windows Enter is \r and in mac it's \n
           if (prompt != 4){
                prompt ++;
            }
       }
       else if (key!= CODED){
           userInput += key;
       }  
      }
  }
  
  /*
  * Checks if there is collisions
  */
  public void drawCollisions(){
      if (person.isCollidingWith(door)){
          prompt ++;
      }
  }
  
  
  public void draw(){
      // Home Page
      switch (screen){
          case 1:
              background(bg);
              break;
      }
      
      switch (prompt){
          case 0:
              text("Enter Name: ", 20,100);
              text(userInput, 130, 100);
              break;
              
          case 1:
              text("Eid is a special celebration for Muslims after a month of fasting during Ramadan. \n"
                      + "It’s a joyful day when families wear nice clothes, go to the mosque to pray, and \n"
                      + "say \"Eid Mubarak\" to each other, which means \"Blessed Eid.\" Press 'enter' to continue",20,100);
              break;
              
          case 2: 
              text("People give Eidi (money or gifts), eat delicious food like dates, sweets, and biryani,\n"
                      + " and spend time with loved ones. It’s also a time to share with others and help those in need.\n Press 'enter' to continue", 20,100);
              break;
              
          case 3:
              text("Eid is all about faith, family, and giving.Press 'enter' to continue", 20, 100);
              break;
              
          case 4:
              text("Enter the door to continue", 20,100);
              door.draw();
              break;
      }
      if (prompt > 3){
        person.setName(userInput);
        person.draw();   
        drawCollisions();
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
  }
 
}//end class

