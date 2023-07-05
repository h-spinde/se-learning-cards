package cards;

import java.util.List;
import java.util.*;

// ------------------------------------------------------ //
public abstract class LearningCard {

  private String front;
  private String back;
  private int counter;
  private int rightInARow;
  private int[] nextDate;
  
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
  
  public int[] getTodaysDate() {
    Calendar today = Calendar.getInstance();
    int[] thisdate = {today.get(Calendar.YEAR), today.get(Calendar.WEEK_OF_YEAR), today.get(Calendar.DAY_OF_WEEK)};
    return thisdate;
  }
  
  public int[] addDaysToDate(int[] date, int distance) {
    date[2] += distance;
    while (date[2] > 7) {
      date[2] -= 7;
      date[1] ++;
    }
    while (date[1] > 53) {
      date[1] -= 53;
      date[0] ++;
    }
    System.out.println("This is the new DAY: " + date[2]);
    return date;
  }
  
  public int[] getNextDate(int rightInRow) {
    int[] today = getTodaysDate();
    switch(rightInRow) {
      case 0:
        return addDaysToDate(today, 1);
      case 1:
        return addDaysToDate(today, 7);
      case 2:
        return addDaysToDate(today, 16);
      default:
        return addDaysToDate(today, 35);
    }
  }
  
  public abstract void isRight();
  
  public abstract void isWrong();
  
  public abstract int getRow();
  
  public abstract int[] getDate();
  
  public abstract void setToday();
  
}
