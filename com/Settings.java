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
  
  public LearningCard addCard() {
    Scanner scanner = new Scanner(System.in);
    SimpleCard newCard = new SimpleCard("This card failed", "This card failed");
    try {
      List<LearningCard> fileCards = new ArrayList();
      System.out.println("Please enter the new card's front matter:");
      String front = scanner.nextLine();
      System.out.println("Please enter the new card's back matter:");
      String back = scanner.nextLine();
      newCard = new SimpleCard(front, back);
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
    return newCard;
  }
  
  public void fileFromTerminal() {
    Scanner scanner = new Scanner(System.in);
    boolean more = true;
    try {
      List<LearningCard> fileCards = new ArrayList();
      System.out.print("Please enter a name for the new file: ");
      String name = scanner.nextLine();
      while (more) {
        System.out.print("Do you want to add another card? [y/N] ");
        String line = scanner.nextLine();
        if ((line.equals("y")) || (line.equals("Y"))) {
          LearningCard card = addCard();
          fileCards.add(card);
        }
        else {
          more = false;
        }
      }
      SaveFileGenerator make = new SaveFileGenerator();
      make.createSaveFile(fileCards, "./saveFiles/" + name);
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
  public void addViaMarkdown() {
    System.out.println("This is where you get to add to an existing file using Markdown!");
  }
  
  public void addViaTerminal() {
    System.out.println("This is where you get to add to an existing file using the terminal!");
  }

  public void addNewFile() {
    Scanner scanner = new Scanner(System.in);
    try {
      System.out.println("Do you want to generate a file from Markdown or in the Terminal? [m/t]");
      String line = scanner.nextLine();
      if ((line.equals("m")) || (line.equals("M"))) {
        fileFromMarkdown();
      } else if ((line.equals("t")) || (line.equals("T"))) {
        fileFromTerminal();
      }
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
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
