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
      System.out.printf("\n%s %s\t%s %d/%d\n", "Card:", title, makeProgressBar(i, s), i, s-1);
      System.out.printf("\t\t%s %d%s %d\n", "Times tested:", card.getCounter(), " - Times right in a row:", card.getRow());
    } else if (card.getClass().getSimpleName().equals("QuestionCard")) {
      String title = card.getFrontContent().get(0);
      System.out.printf("\n%s %s\t%s %d/%d\n", "Card:", title, makeProgressBar(i, s), i, s-1);
      System.out.printf("\t\t%s %d%s %d\n", "Times tested:", card.getCounter(), " - Times right in a row:", card.getRow());
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
     System.out.printf("!Unknown Card Type\n");
    }
  }
  
  public void cardResult(String result, LearningCard card) {
    if ((result.equals("y")) || (result.equals("Y"))) {
      card.isRight();
    } else {
      card.isWrong();
    }
  }
  
  public List<LearningCard> findCardsToTestToday(List<LearningCard> cards) {
    List<LearningCard> newCards = new ArrayList();
    Calendar today = Calendar.getInstance();
    int[] todaysDate = {today.get(Calendar.YEAR), today.get(Calendar.WEEK_OF_YEAR), today.get(Calendar.DAY_OF_WEEK)};
    int s = cards.size();
    for (int i = 0; i < s; i++) {
      LearningCard card = cards.get(i);
      int[] cardDate = card.getDate();
      if (todaysDate[0] > cardDate[0]) {
        newCards.add(card);
      } else if (todaysDate[0] == cardDate[0]) {
        if (todaysDate[1] > cardDate[1]) {
          newCards.add(card);
        } else if (todaysDate[1] == cardDate[1]) {
          if (todaysDate[2] >= cardDate[2]) {
            newCards.add(card);
          }
        }
      }
    }
    return newCards;
  }
  
  public void printCardsToConsole(List<LearningCard> cards) {
    Scanner scanner = new Scanner(System.in);
    List<LearningCard> currentCards = findCardsToTestToday(cards);
    int s = currentCards.size();
    try {
      for (int i = 0; i < s; i++) {
        LearningCard card = currentCards.get(i);
        printTitle(card, i, s);
        printFrontSide(card);
        System.out.printf("%s\t","  Your Answer: ");
        String line = scanner.nextLine();
        System.out.printf("%s\t%s\n","  Solution: ", card.getBackContent().get(0));
        System.out.println("");
        System.out.print("Was your solution correct? [y/N] ");
        line = scanner.nextLine();
        cardResult(line, card);
        card.upCounter();
      }
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
  public void play(String oldfile, String newfile) {
    System.out.println("Playing Collection \"" + oldfile + "\":");
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
