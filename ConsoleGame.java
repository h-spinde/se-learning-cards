import java.util.*;

public class ConsoleGame {
  public void printCardToConsole(List<LearningCard> cards) {
    Scanner scanner = new Scanner(System.in);
    try {
      for (int i = 0; i < 1; i++) {
        LearningCard card = cards.get(i);
        System.out.println("Question: " + card.getFrontContent());
        System.out.print("Your Answer: ");
        String line = scanner.nextLine();
        System.out.println("Solution: " + card.getBackContent());
      }
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
  public static void main(String[] args) {

        List<LearningCard> fileCards = new ArrayList();
        fileCards = MarkdownLoader.loadCardFile("cards.md");
        ConsoleGame h = new ConsoleGame();
        h.printCardToConsole(fileCards);
    }
}
