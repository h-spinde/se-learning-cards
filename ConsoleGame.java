import java.util.*;

public class ConsoleGame {
  public void printCardToConsole(LearningCard card) {
    Scanner scanner = new Scanner(System.in);
    try {
      for (int i = 0; i < 1; i++) {
        System.out.println(card.getFrontContent());
        System.out.println("Do you want to see the solution?");
        String line = scanner.nextLine();
        System.out.println(card.getBackContent());
      }
    } catch (IllegalStateException | NoSuchElementException e) {
      System.out.println("System.in was closed");
    }
  }
  
  public static void main(String[] args) {

        List<LearningCard> fileCards = new ArrayList();
        fileCards = MarkdownLoader.loadCardFile("cards.md");
        PlayInConsole h = new PlayInConsole();
        h.printCardToConsole(fileCards.get(0));
    }
}
