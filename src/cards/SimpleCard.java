package cards;

import java.util.List;
import java.util.*;

// ------------------------------------------------------ //
public class SimpleCard extends LearningCard {

  private String front;
  private String back;
  private int counter;
    
  public SimpleCard(String question, String answer) {
    this.front = removeEmptyLine(question);
    this.back = removeEmptyLine(answer);
    this.counter = 0;
  }
    
  public SimpleCard(String question, String answer, int count) {
    this.front = removeEmptyLine(question);
    this.back = removeEmptyLine(answer);
    this.counter = count;
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
  
}