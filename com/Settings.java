import java.util.*;
import java.nio.charset.*;
import cards.*;
import fileManipulation.*;

public class Settings {

  public void fileFromMarkdown() {
    Scanner scanner = new Scanner(System.in);
    try {
      System.out.println("Please make sure there is a properly formatted Markdown file in the folder markdownFiles.");
      System.out.print("Please enter the file name: ");
      String mdfile = scanner.nextLine();
      System.out.print("Please enter the name the new Save File should have: ");
      String savefile = scanner.nextLine();
      SaveFileGenerator h = new SaveFileGenerator();
      h.saveFileFromMarkdown("./markdownFiles/" + mdfile, "./saveFiles/" + savefile);
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }

  public void addNewFile() {
    Scanner scanner = new Scanner(System.in);
    try {
      System.out.println("Do you want to generate a file from Markdown or in the Terminal? [m/t]");
      String line = scanner.nextLine();
      if ((line.equals("m")) || (line.equals("M"))) {
        fileFromMarkdown();
      }
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }

  /* TODO:
  *    - Add Option to add new Cards to existing file from markdown
  *    - Add Option to add new Cards to existing file in terminal
  *    - Add Option to make new File from markdown
  *    - Add Option to make new File by entering Cards in Terminal
  *   (- Add Option to remove Cards, MAYBE)
  */
  
  public void settingsMenu() {
    System.out.println("This is the Settings menu!");
    System.out.println("You will now make a new file from Markdown.");
    addNewFile();
  }
  
  public static void main(String[] args) {
    ConsoleGame fun = new ConsoleGame();
    fun.play("example");
  }
}
