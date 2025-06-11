/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//imports
package summative;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/*
* This class is used to make a person and display it on the screen with customization
* @author Aydin Hussain
*/
public class MySketch extends PApplet { 
  // Objets
  private Person person;
  private Things door;
  private Basket basket;
  private PersonBasket personBasket;
  // Used arraylist to get a list of dates https://www.w3schools.com/java/java_arraylist.asp
  ArrayList<Dates> fallingDates;
  int score = 0;
  int gameTimer = 0;
  boolean gameOver = false;
  String userInput = "";
  int stage = 0;
  boolean showInfo;
  PImage bg;
  int screen = 1;
  int prompt = 0;
  PImage bg2;
  // eidMsgs are static because it is the same through all instances of the game and it never changed 
  static String[] eidMsgs = new String[7];
  int counter = 0;
  PImage bg3;
  private Things homeless[][] = new Things[2][4];
  // Sets all values of mans to 0
  private int[] mans = {0,0,0,0};
  private Things date[] = new Things[4];
  PImage bg4;
  
  /*
  * Method used to set the screen size
  */
  public void settings() {
    size(800, 800);
  }

  public void setup() {
    //background(bg);
    bg = loadImage("images/homePage.jpg");
    bg.resize(width,height);
    bg2 = loadImage("images/datesBack.jpg");
    bg2.resize(width,height);
    bg4 = loadImage("images/finished.png");
    bg4.resize(width,height);
    person = new Person(this,400,720,"Anonymous", "images/person.png");
    door = new Things(this,350,50,"images/door.png");
    basket = new Basket(this,400,750,"images/basket.png");
    fallingDates = new ArrayList<>();
    for(int i=0; i<10; i++){
        fallingDates.add(new Dates(this, 0, 0, "images/date.png"));
    }
    textSize(20);
    try { 
        Scanner scanner = new Scanner(new File("EidMessages.txt"));
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            eidMsgs[counter] = line;
            counter ++;
        }
        scanner.close();
        }
    catch (Exception E){
        System.out.println("Error reading file");
    }
    bg3 = loadImage("images/handingDatesBg.jpg");
    bg3.resize(width,height);
    homeless[0][0] = new Things(this,20,20,"images/homelessSad.png");
    homeless[0][1] = new Things(this,720,20,"images/homelessSad.png");
    homeless[0][2] = new Things(this,20,720,"images/homelessSad.png");
    homeless[0][3] = new Things(this,720,720,"images/homelessSad.png");
    homeless[1][0] = new Things(this,20,20,"images/homelessHappy.png");
    homeless[1][1] = new Things(this,720,20,"images/homelessHappy.png");
    homeless[1][2] = new Things(this,20,720,"images/homelessHappy.png");
    homeless[1][3] = new Things(this,720,720,"images/homelessHappy.png");
    for (int n = 0; n < 2; n ++){
        for (int p = 0; p < 4; p ++){
            homeless[n][p].resize(100,100);
      }
    }
    personBasket = new PersonBasket(this,400,400, "Anonymous", "images/person.png", "images/basketDates.png");
    date[0] = new Things(this, 30,30,"images/date.png");
    date[0].resize(30,30);
    date[1] = new Things(this, 730,30,"images/date.png");
    date[1].resize(30,30);
    date[2] = new Things(this, 30,730,"images/date.png");
    date[2].resize(30,30);
    date[3] = new Things(this, 730,730,"images/date.png");
    date[3].resize(30,30);

  }
  
  /*
  * Method made to use to detect the enter button
  */
  public void keyPressed(){
      //Cannot use in draw because it runs 60 times a minute leading to errors
      if (keyPressed){
       if (key == '\n' || key == '\r'){ // On windows Enter is \r and in mac it's \n
           if (prompt < 4){
                prompt ++;
            }
       }
       else if (key != CODED){
           userInput += key;
       }  
      }
  }
  
  /*
  * Checks if there is collisions
  */
  public void drawCollisions(){
      if (person.isCollidingWith(door) && screen < 2){
          prompt ++;
          screen ++;
      }
  }
  
  /*
  * This method is used for the homeless people collisions
  */
  public void homelessCollisions(Things a, int index){
      if (personBasket.isCollidingWith(a) && screen == 3){
          mans[index] = 1;
      }
  }
  
  /*
  * This method is used to allow the movement of the player
  */
  public void movement(){
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
  
  
  /*
  * This method is used to draw everything onto the screen
  */
  
  public void draw(){
      // Home Page
      switch (screen){
          case 1:
              background(bg);
              break;
              
          case 2:
            if (!gameOver){  
                background(bg2);
                int timeLeft = max(0, 20 - gameTimer / 60);
                textSize(20);
                text("Time Left: " + timeLeft + "s", 20, 70);
                gameTimer ++;
                basket.draw();

              if (keyPressed){
                  if (keyCode == LEFT) basket.moveLeft();
                  if (keyCode == RIGHT) basket.moveRight();
              }

              for (Dates d : fallingDates){
                  d.update();
                  d.draw();
                  if (d.isCaught(basket)){
                      score++;
                      d.caught();
                  }
              }
              
              // Checks if time is out, then ends the game and adds the game data to the history
              if (gameTimer >= 60 * 20){
                  gameOver = true;
                  prompt ++; 
                  try {
                      FileWriter writer = new FileWriter("score.txt", true);
                      PrintWriter printwriter = new PrintWriter(writer);
                      printwriter.println("Player: " + person.getName() + ", Dates collected: " + score);
                      writer.close();
                      printwriter.close();
                  }
                  catch(Exception e) {
                      System.out.println("Error writing to file");
                  }
              }
            
            }
       
            else {
                background(bg2);
                try{
                    // Count lines in score.txt
                    Scanner lineCounter = new Scanner(new File("Score.txt"));
                    int lineCount = 0;
                    while (lineCounter.hasNextLine()) {
                        lineCounter.nextLine();
                        lineCount++;
                    }
                    lineCounter.close();

                    // Get lines from score.txt
                    String[] lines = new String[lineCount];
                    Scanner lineReader = new Scanner(new File("Score.txt"));
                    int i = 0;
                    while (lineReader.hasNextLine()) {
                        lines[i] = lineReader.nextLine();
                        i++;
                    }
                    lineReader.close();

                    // Display history
                    int yVal = 50;
                    for (int u = 0; u < lineCount; u ++) {
                        yVal += 20;
                        text(lines[u], 300, yVal);
                    }
                }
                catch (Exception e){
                    System.out.println("Unable to read file");
                }
                if (keyPressed){
                    if (key == '\n' || key == '\r'){ // On windows Enter is \r and in mac it's \n
                        screen ++;
                        prompt ++;
                    }      
                }
            }
                break;
          case 3:
              background(bg3);
              // Give dates to homeless sad and turn them into happy with dates
              personBasket.draw();
              if (keyPressed){
                if (keyCode == LEFT){
                    personBasket.move(-5,0);
                }
                else if (keyCode == RIGHT){
                    personBasket.move(5,0);
                }
                else if (keyCode == UP){
                    personBasket.move(0,-5);
                }
                else if (keyCode == DOWN){
                    personBasket.move(0,5);
                }
               }
              
              for (int i = 0; i < 4; i++) {
                if (mans[i] == 0) {
                    homeless[0][i].draw();
                    homelessCollisions(homeless[0][i], i);
                } else {
                    homeless[1][i].draw();
                    date[i].draw();
                }
               }   
              boolean allMansHappy = true;
              for (int l = 0; l < mans.length; l++){
                  if(mans[l] != 1){
                      allMansHappy = false;
                      break;
                  }
              }
              
              if (allMansHappy){
                  screen ++;
                  prompt ++;
              }
              break;
              
          case 4:
              background(bg3);
               personBasket.draw(350,350);
               for (int i = 0; i < 4; i++) {
                   homeless[1][i].draw();
                   date[i].draw();
               }
               
               if (keyPressed){
                    if (key == '\n' || key == '\r'){ // On windows Enter is \r and in mac it's \n
                        screen ++;
                        prompt ++;
                    }  
                }
               break;
              
          case 5:
              background(bg4);
              break;
      }
      
      switch (prompt){
          case 0:
              text("Enter Name: ", 20,100);
              text(userInput, 130, 100);
              break;
              
          case 1:
              person.setName(userInput);
              personBasket.setName(userInput);
              text(eidMsgs[0] + "\n" + eidMsgs[1] + "\n" + eidMsgs[2],20,100);
              break;
              
          case 2: 
              text(eidMsgs[3] + "\n" + eidMsgs[4] + "\n" + eidMsgs[5], 20, 100);
              break;
              
          case 3:
              text(eidMsgs[6], 20, 100);
              break;
              
          case 4:
              text("Enter the door to continue", 300,40);
              person.draw();   
              drawCollisions();
              door.draw();
              movement();
              break;
              
          case 5:
              textSize(35);
              text("Collect dates for ramadan", 230, 100);
              textSize(20);
              text("Dates collected: " + score,20,50);
              break;
              
          case 6:
              textSize(20);
              text("Game Over", 20,40);
              text("Score: " + score + " dates collected", 20,60);
              text("History: (Press enter to continue)", 300, 40);
              break;
              
          case 7:
              textSize(35);
              text("Give all the homeless people the dates you collected", 20,200);
              break;
          
          case 8:
              text("All the homeless have food because of you!", 100, 200);
              text("Press 'enter' to continue", 100,250);
              break;
          
          case 9:
              text("Thank you for playing my game and \n learning about Ramadan!",100, 300);
              break;
      }
      
  }
 
}//end class
