package cards;

import java.util.List;
import java.util.*;

// ------------------------------------------------------ //
public abstract class LearningCard {

  private String front;
  private String back;
  private int counter;
  private int rightInARow;
  
  /** return the front site of the card */
  public abstract List<String> getFrontContent();

  /** return the back site of the card */
  public abstract List<String> getBackContent();

  /** return combined content of front and back */
  public abstract List<String> getContent();

  /**
  * write the content to the console in a meaningful way
  */
  public abstract void printToConsole();
    
  public abstract void upCounter();
    
  public abstract int getCounter();
  
  public String removeEmptyLine(String myString) {
    while (myString.startsWith("\n")) {
      myString = myString.substring(1);
    }
    while (myString.endsWith("\n")) {
      myString = myString.substring(0, myString.length()-1);
    }
    return myString;
  }
  
  public abstract void isRight();
  
  public abstract void isWrong();
  
  public abstract int getRow();
  
}
