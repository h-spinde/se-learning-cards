package fileManipulation;

import java.util.*;
import java.io.*;
import java.nio.*;
import cards.*;

public class SaveFileGenerator {
  public void createSaveFile(List<LearningCard> cards, String output_file) {
    String inp = writeSaveFile(cards);
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
  
  public String writeSaveFile(List<LearningCard> cards) {
    String m = "THIS FILE is meant to be read for the Console LearningCard Game only\n\n";
    for(int i = 0; i < cards.size(); i++) {
      m += createCard(cards.get(i)) + "\n\n";
    }
    return m;
  }
  
  public String createCard(LearningCard card) {
    String r = "##" + card.getClass().getSimpleName() + "\n";
   if (card.getClass().getSimpleName().equals("SimpleCard")) {
     r += simpleSave(card);
   } else if (card.getClass().getSimpleName().equals("QuestionCard")) {
     r += questionSave(card);
   } else {
     System.out.println("There's an error with the card type! It is " + card.getClass().getSimpleName());
   }
   r += card.getCounter() + "\n" + card.getRow();
   return r;
  }
  
  public String simpleSave (LearningCard card) {
    String m = "#" + card.getFrontContent().get(0) + "\n#";
    m += card.getBackContent().get(0) + "\n";
    return m;
  }
  
  public String questionSave(LearningCard card) {
    String m = "#" + card.getFrontContent().get(0) + "\n#";
    m += card.getFrontContent().get(1) + "\n#";
    m += card.getBackContent().get(0) + "\n";
    return m;
  }
  
  public void saveFileFromMarkdown(String mdPath, String newPath) {
    List<LearningCard> fileCards = new ArrayList();
    fileCards = MarkdownLoader.loadCardFile(mdPath);
    SaveFileGenerator h = new SaveFileGenerator();
    h.createSaveFile(fileCards, newPath);
  }
  
  
  public static void main(String[] args) {
    List<LearningCard> fileCards = new ArrayList();
    fileCards = MarkdownLoader.loadCardFile("cards.md");
    SaveFileGenerator h = new SaveFileGenerator();
    h.createSaveFile(fileCards, "example");
  }
}
