import java.util.*;
import java.nio.charset.*;
import cards.*;
import fileManipulation.*;

public class LearningCardGame {

  public void playGame() {
    Scanner scanner = new Scanner(System.in);
    try {
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
    try {
      System.out.print("Do you want to play (p), go to settings/edits (s) or leave (x)? [p/s/x] ");
      String line = scanner.nextLine();
      if ((line.equals("p")) || (line.equals("P")) || (line.equals("Play")) || (line.equals("play"))) {
        playGame();
      } else if ((line.equals("s")) || (line.equals("S")) || (line.equals("Settings")) || (line.equals("settings"))) {
        Settings settings = new Settings();
        settings.settingsMenu();
      }
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
  public static void main(String[] args) {
    LearningCardGame newGame = new LearningCardGame();
    newGame.askWhatToDo();
  }
}
