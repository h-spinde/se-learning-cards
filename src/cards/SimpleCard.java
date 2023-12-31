package cards;

import java.util.List;
import java.util.*;

// ------------------------------------------------------ //
public class SimpleCard extends LearningCard {

  private String front;
  private String back;
  private int counter;
  private int rightInARow;
  private int[] nextDate;
    
  public SimpleCard(String question, String answer) {
    this.front = removeEmptyLine(question);
    this.back = removeEmptyLine(answer);
    this.counter = 0;
    this.rightInARow = 0;
    this.nextDate = getTodaysDate();
  }
    
  public SimpleCard(String question, String answer, int count) {
    this.front = removeEmptyLine(question);
    this.back = removeEmptyLine(answer);
    this.counter = count;
    this.rightInARow = 0;
    this.nextDate = getTodaysDate();
  }
  
  public SimpleCard(String question, String answer, int count, int row) {
    this.front = removeEmptyLine(question);
    this.back = removeEmptyLine(answer);
    this.counter = count;
    this.rightInARow = row;
    this.nextDate = getTodaysDate();
  }
  
  public SimpleCard(String question, String answer, int count, int row, int[] date) {
    this.front = removeEmptyLine(question);
    this.back = removeEmptyLine(answer);
    this.counter = count;
    this.rightInARow = row;
    this.nextDate = date;
  }
    
  public List<String> getFrontContent() {
    List<String> myList = new ArrayList<>();
    myList.add(this.front);
    return myList;
  }
    
  public List<String> getBackContent() {
    List<String> myList = new ArrayList<>();
    myList.add(this.back);
    return myList;
  }
    
  public List<String> getContent() {
    List<String> myList = new ArrayList<>();
    myList.add(this.front);
    myList.add(this.back);
    return myList;
  }
    
  public void printToConsole() {
    System.out.println("the front matter is: " + this.front);
    System.out.println("the back matter is: " + this.back);
  }
  
  public void upCounter() {
    this.counter += 1;
  }
    
  public int getCounter() {
    return this.counter;
  }
  
  public void isRight() {
    this.nextDate = getNextDate(this.rightInARow);
    this.rightInARow ++;
  }
  
  public void isWrong() {
    this.rightInARow = 0;
    this.nextDate = getTodaysDate();
  }
  
  public int getRow() {
    return this.rightInARow;
  }
  
  public int[] getDate() {
    return this.nextDate;
  }
  
  public void setToday() {
    this.nextDate = getTodaysDate();
  }
  
}
