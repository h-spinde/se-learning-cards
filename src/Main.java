import java.util.*;
import java.nio.charset.*;
import cards.*;
import fileManipulation.*;

public class Main {

  public void playGame() {
    Scanner scanner = new Scanner(System.in);
    try {
      System.out.println("");
      System.out.print("Which Cards do you want to test? Please enter the Save File name: ");
      String line = scanner.nextLine();
      ConsoleGame fun = new ConsoleGame();
      fun.play(line);
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
  public void askWhatToDo() {
    Scanner scanner = new Scanner(System.in);
    boolean continueplaying = true;
    try {
      while (continueplaying)
      {
        System.out.println("");
        System.out.println("What do you want to do next?");
        System.out.println("p: play with your current learning cards");
        System.out.println("s: go to settings or edits");
        System.out.println("x: (default) exit the game");
        System.out.print("Please enter: [p/s/X] ");
        String line = scanner.nextLine();
        if ((line.equals("p")) || (line.equals("P")) || (line.equals("Play")) || (line.equals("play"))) {
          playGame();
        } else if ((line.equals("s")) || (line.equals("S")) || (line.equals("Settings")) || (line.equals("settings"))) {
          Settings settings = new Settings();
          settings.settingsMenu();
        } else {
          continueplaying = false;
        }
      }
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
  public static void main(String[] args) {
    Main newGame = new Main();
    newGame.askWhatToDo();
  }
}
