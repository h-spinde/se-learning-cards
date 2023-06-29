import java.util.*;
import java.io.*;
import java.nio.*;

public class TxtGenerator {
  public void createTxtFile(List<LearningCard> cards, String output_file) {
    String inp = writeTxt(cards);
    try {
      FileWriter myWriter = new FileWriter(output_file);
      myWriter.write(inp);
      myWriter.close();
      System.out.println("");
      System.out.println("Completion successfull!");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  
  public String writeTxt(List<LearningCard> cards)
  {
    String m = "THIS FILE is meant to be read for the Console LearningCard Game only\n\n";
    for(int i = 0; i < cards.size(); i++)
    {
        m += createTxtCard(cards.get(i)) + "\n\n";
    }
    //m += "\n";
    return m;
  }
  
  public String createTxtCard(LearningCard card)
  {
     String r = "##" + card.getClass().getSimpleName() + "\n";
     if (card.getClass().getSimpleName().equals("SimpleCard")) { r += simpleTxt(card); }
     else if (card.getClass().getSimpleName().equals("QuestionCard")) { r += questionTxt(card); }
     else { System.out.println("There's an error with the card type! It is " + card.getClass().getSimpleName()); }
     r += card.getCounter(); //+ "\n\n";
     return r;
  }
  
  public String simpleTxt (LearningCard card)
  {
      String m = "#" + card.getFrontContent().get(0) + "\n#" + card.getBackContent().get(0) + "\n";
      return m;
  }
  
  public String questionTxt(LearningCard card)
  {
      String m = "#" + card.getFrontContent().get(0) + "\n#" + card.getFrontContent().get(1) + "\n#" + card.getBackContent().get(0) + "\n";
      return m;
  }
  
  public static void main(String[] args) {


        List<LearningCard> fileCards = new ArrayList();
        fileCards = MarkdownLoader.loadCardFile("cards.md");
        TxtGenerator h = new TxtGenerator();
        h.createTxtFile(fileCards, "example");
    }
}
