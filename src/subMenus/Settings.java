package subMenus;

import java.util.*;
import java.nio.charset.*;
import cards.*;
import fileManipulation.*;

public class Settings {

  public void fileFromMarkdown() {
    Scanner scanner = new Scanner(System.in);
    String filePath = System.getProperty("user.dir");
    filePath = filePath.substring(0, filePath.length() - 4);
    filePath += "/files/";
    try {
      System.out.println("Please make sure there is a properly formatted Markdown file in the folder markdownFiles and enter the file name here: ");
      String mdfile = scanner.nextLine();
      System.out.print("Please enter the name the new Save File should have: ");
      String savefile = scanner.nextLine();
      SaveFileGenerator h = new SaveFileGenerator();
      h.saveFileFromMarkdown(filePath + "markdownFiles/" + mdfile, filePath + "saveFiles/" + savefile);
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
  public List<LearningCard> loadCardFile(String file_path) {
    String filePath = System.getProperty("user.dir");
    filePath = filePath.substring(0, filePath.length() - 4);
    return SaveFileLoader.loadCardFile(filePath + "/files/saveFiles/" + file_path);
  }
  
  public void createSaveFile(List<LearningCard> cards, String filename) {
    String filePath = System.getProperty("user.dir");
    filePath = filePath.substring(0, filePath.length() - 4);
    SaveFileGenerator h = new SaveFileGenerator();
    h.createSaveFile(cards, filePath + "/files/saveFiles/" + filename);
  }
  
  public LearningCard addCard() {
    Scanner scanner = new Scanner(System.in);
    LearningCard newCard = new SimpleCard("This card failed!", "This card failed!");
    try {
      List<LearningCard> fileCards = new ArrayList();
      System.out.println("Please enter the new card's title - if you want one:");
      String title = scanner.nextLine();
      System.out.println("Please enter the new card's front matter:");
      String front = scanner.nextLine();
      System.out.println("Please enter the new card's back matter:");
      String back = scanner.nextLine();
      if (title.equals("")) {
        newCard = new SimpleCard(front, back);
      } else {
        newCard = new QuestionCard(title, front, back);
      }
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
      System.out.println("");
      System.out.print("Please enter a name for the new file: ");
      String name = scanner.nextLine();
      while (more) {
        System.out.println("");
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
      createSaveFile(fileCards, name);
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
  public void addViaMarkdown() {
    Scanner scanner = new Scanner(System.in);
    try {
      System.out.println("Please make sure there is a properly formatted Markdown file in the folder markdownFiles and enter the file name: ");
      String mdfile = scanner.nextLine();
      System.out.print("Please enter the Save File you want to add to: ");
      String oldfile = scanner.nextLine();
      //Scanner scanner = new Scanner(System.in);
      String filePath = System.getProperty("user.dir");
      filePath = filePath.substring(0, filePath.length() - 4);
      List<LearningCard> fileCards = new ArrayList();
      fileCards = loadCardFile(oldfile);
      fileCards.addAll(MarkdownLoader.loadCardFile(filePath + "/files/markdownFiles/" + mdfile));
      createSaveFile(fileCards, oldfile);
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
  public void addViaTerminal() {
    Scanner scanner = new Scanner(System.in);
    boolean more = true;
    try {
      System.out.println("");
      System.out.print("Please enter the Save File you want to add to: ");
      String oldfile = scanner.nextLine();
      List<LearningCard> fileCards = new ArrayList();
      fileCards = loadCardFile(oldfile);
      while (more) {
        System.out.println("");
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
      createSaveFile(fileCards, oldfile);
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }

  public void newFile() {
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
  
  public void addToFile() {
    Scanner scanner = new Scanner(System.in);
    try {
      System.out.println("Do you want to add cards from a Markdown file or in the Terminal? [m/t/Exit]");
      String line = scanner.nextLine();
      if ((line.equals("m")) || (line.equals("M"))) {
        addViaMarkdown();
      } else if ((line.equals("t")) || (line.equals("T"))) {
        addViaTerminal();
      }
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
  public void debugFunctionalities() {
    Scanner scanner = new Scanner(System.in);
    try {
      String line = scanner.nextLine();
      if (line.equals("set date today")) {
        System.out.println("file name: ");
        line = scanner.nextLine();
        List<LearningCard> fileCards = new ArrayList();
        fileCards = loadCardFile(line);
        for (LearningCard card : fileCards) {
          card.setToday();
        }
        System.out.println("file name: ");
        line = scanner.nextLine();
        createSaveFile(fileCards, line);
      }
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
  public void settingsMenu() {
    Scanner scanner = new Scanner(System.in);
    try {
      System.out.println("");
      System.out.println("Do you want to create a new file or add to an existing one? [new/add/Exit]");
      String line = scanner.nextLine();
      if ((line.equals("new")) || (line.equals("New"))) {
        newFile();
      } else if ((line.equals("add")) || (line.equals("Add"))) {
        addToFile();
      } else if ((line.equals("debug"))) {
        debugFunctionalities();
      }
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
  public static void main(String[] args) {
    ConsoleGame fun = new ConsoleGame();
    fun.play("example");
  }
}
