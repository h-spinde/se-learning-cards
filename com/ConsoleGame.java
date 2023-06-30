import java.util.*;
import java.nio.charset.*;

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
      String title = "" + i; 
      System.out.printf("\n%s %s\t\t%s %d/%d %s %d%s\n", "Card:", title, makeProgressBar(i, s), i, s-1, "- being quizzed for the", card.getCounter()+1, "th time");
    } else if (card.getClass().getSimpleName().equals("QuestionCard")) {
      String title = card.getFrontContent().get(0);
      System.out.printf("\n%s %s%s %d/%d %s %d%s\n", "Card:", title, makeProgressBar(i, s), i, s-1, "- being quizzed for the", card.getCounter()+1, "th time");
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
  
  public void printCardsToConsole(List<LearningCard> cards) {
    Scanner scanner = new Scanner(System.in);
    int s = cards.size();
    try {
      for (int i = 0; i < s; i++) {
        LearningCard card = cards.get(i);
        printTitle(card, i, s);
        System.out.printf("%s\t%s\n", "  Question: ", card.getFrontContent().get(0));
        System.out.printf("%s\t","  Your Answer: ");
        String line = scanner.nextLine();
        System.out.printf("%s\t%s\n","  Solution: ", card.getBackContent().get(0));
        card.upCounter();
      }
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
  public void play(String filename) {
    List<LearningCard> fileCards = new ArrayList();
    fileCards = SaveFileLoader.loadCardFile("/home/uni/Documents/se/semesterprojekt/se-learning-cards/example");
    printCardsToConsole(fileCards);
    SaveFileGenerator newFile = new SaveFileGenerator();
    newFile.createSaveFile(fileCards, filename);
  }
  
  public static void main(String[] args) {
    ConsoleGame fun = new ConsoleGame();
      fun.play("example");
  }
}