import java.util.*;
import java.nio.charset.*;
import cards.*;
import fileManipulation.*;

public class ConsoleGame {

  public String getTitle(LearningCard card, int i) {
    if (card.getClass().getSimpleName().equals("SimpleCard")) {
      return "" + i; 
    } else if (card.getClass().getSimpleName().equals("QuestionCard")) {
      return card.getFrontContent().get(0);
    } else {
      return "Unknown Card Type";
    }
  }
  
  public void printTitle(LearningCard card, int i, int s) {
    if (card.getClass().getSimpleName().equals("SimpleCard")) {
      String title = "";
      if (i < 10) {
        title += 0;
      }
      title += i;
      System.out.printf("\n%s %s\t%s %d/%d %s %d%s\n", "Card:", title, makeProgressBar(i, s), i, s-1, "- being quizzed for the", card.getCounter()+1, "th time");
    } else if (card.getClass().getSimpleName().equals("QuestionCard")) {
      String title = card.getFrontContent().get(0);
      System.out.println(title);
      System.out.printf("\n%s %s\t%s %d/%d %s %d%s\n", "Card:", title, makeProgressBar(i, s), i, s-1, "- being quizzed for the", card.getCounter()+1, "th time");
    } else {
     System.out.printf("Unknown Card Type");
    }
  }
  
  public String makeProgressBar(int curr, int goal) {
    String progressBar = "[";
    for(int i = 0; i < curr; i++) {
    progressBar += "◼";
    }
    while (curr < goal-1) {
      progressBar += " ";
      curr ++;
    }
    progressBar += "]";
    return progressBar;
  }
  
  public void printFrontSide(LearningCard card) {
    if (card.getClass().getSimpleName().equals("SimpleCard")) {
      System.out.printf("%s\t%s\n", "  Question: ", card.getFrontContent().get(0));
    } else if (card.getClass().getSimpleName().equals("QuestionCard")) {
      System.out.printf("%s\t%s\n", "  Question: ", card.getFrontContent().get(1));
    } else {
     System.out.printf("Unknown Card Type");
    }
  }
  
  public void printCardsToConsole(List<LearningCard> cards) {
    Scanner scanner = new Scanner(System.in);
    int s = cards.size();
    try {
      for (int i = 0; i < s; i++) {
        LearningCard card = cards.get(i);
        printTitle(card, i, s);
        printFrontSide(card);
        System.out.printf("%s\t","  Your Answer: ");
        String line = scanner.nextLine();
        System.out.printf("%s\t%s\n","  Solution: ", card.getBackContent().get(0));
        card.upCounter();
      }
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
  public void play(String oldfile, String newfile) {
    List<LearningCard> fileCards = new ArrayList();
    String filePath = System.getProperty("user.dir");
    filePath = filePath.substring(0, filePath.length() - 4);
    fileCards = SaveFileLoader.loadCardFile(filePath + "/files/saveFiles/" + oldfile);
    printCardsToConsole(fileCards);
    SaveFileGenerator newFile = new SaveFileGenerator();
    newFile.createSaveFile(fileCards, filePath + "/files/saveFiles/" + newfile);
  }
  
  public void play(String filename) {
    play(filename, filename);
  }
  
  public static void main(String[] args) {
    ConsoleGame fun = new ConsoleGame();
      fun.play("example");
  }
}
