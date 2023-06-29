import java.util.List;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class HTMLCardGenerator {
  public void createHTMLCards(List<LearningCard> cards, String output_file) {
    String inp = makeHTMLCode(cards);
    try {
      FileWriter myWriter = new FileWriter(output_file + ".html");
      myWriter.write(inp);
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  
  public String makeHTMLCode(List<LearningCard> cards)
  {
    String m = "<!DOCTYPE html> \n <html lang=\"de\"> \n \n<style> \n  .collapsible { \n    padding: 18px; \n    width: 100%; \n    border: none; \n    text-align: left; \n    outline: none; \n    font-size: 15px; \n  } \n \n  .active, \n  .collapsible:hover { \n    background-color: rgb(88, 155, 255); \n  } \n \n  .content { \n    display: none; \n  } \n</style> \n \n<body> \n";
    for(int i = 0; i < cards.size(); i++)
    {
        m += createHTMLCard(cards.get(i));
    }
    m += "</body>\n </html>";
    return m;
  }
  
  public String createHTMLCard(LearningCard card)
  {
     String r = "<h1>";
     if (card.getClass().getSimpleName().equals("SimpleCard")) { r += simpleHTML(card); }
     else if (card.getClass().getSimpleName().equals("QuestionCard")) { r += questionHTML(card); }
     else { System.out.println("There's an error with the card type! It is " + card.getClass().getSimpleName()); }
     return r;
  }
  
  public String simpleHTML (LearningCard card)
  {
      String m = card.getFrontContent() + "</h1> \n <p>";
      List<String> back = card.getBackContent();
      for (int i = 0; i < back.size()-1; i++)
      {
          m += back.get(i) + "<br> \n";
      }
      m += back.get(back.size()-1) + "</p> \n";
      return m;
  }
  
  public String listToH (List<String> th)
  {
      String m = new String();
      for (int i = 0; i < th.size(); i++)
      {
          if ((i%2 == 0) && th.size() > 1)
          {
              m += "<h2>" + th.get(i) + "</h2> \n";
          }
          else
          {
              m += "<p>" + th.get(i) + "</p> \n";
          }
      }
      return m;
  }
  
  public String questionHTML(LearningCard card)
  {
      String m = card.getContent().get(0) + "</h1> \n";
      m += listToH(card.getFrontContent());
      m += listToH(card.getBackContent());
      return m;
  }
  
  public static void main(String[] args) {


        List<LearningCard> fileCards = new ArrayList();
        fileCards = MarkdownLoader.loadCardFile("cards.md");
        HTMLCardGenerator h = new HTMLCardGenerator();
        h.createHTMLCards(fileCards, "example");
    }
}
