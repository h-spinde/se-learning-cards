import java.util.List;
import java.util.*;

// ------------------------------------------------------ //
public abstract class LearningCard {

    private String front;
    private String back;
    private int counter;
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
}

// ------------------------------------------------------ //
class SimpleCard extends LearningCard {

    private String front;
    private String back;
    private int counter;
    
    SimpleCard(String question, String answer)
    {
       this.front = question;
       this.back = answer;
       this.counter = 0;
    }
    
    SimpleCard(String question, String answer, int count)
    {
       this.front = question;
       this.back = answer;
       this.counter = count;
    }
    
    public List<String> getFrontContent()
    {
       List<String> myList = new ArrayList<>();
       myList.add(this.front);
       return myList;
    }
    
    public List<String> getBackContent()
    {
       List<String> myList = new ArrayList<>();
       myList.add(this.back);
       return myList;
    }
    
    public List<String> getContent()
    {
       List<String> myList = new ArrayList<>();
       myList.add(this.front);
       myList.add(this.back);
       return myList;
    }
    
    public void printToConsole()
    {
       System.out.println("the front matter is: " + this.front);
       System.out.println("the back matter is: " + this.back);
    }
    
    public void upCounter()
    {
       this.counter ++;
    }
    
    public int getCounter()
    {
       return this.counter;
    }
    
}

// ------------------------------------------------------ //
class QuestionCard extends LearningCard {

     private String title;
     private String front;
     private String back;
     private int counter = 0;
    
     QuestionCard(String ttl, String question, String answer)
    {
       this.title = ttl;
       this.front = question;
       this.back = answer;
       this.counter = 0;
    }
    
    QuestionCard(String ttl, String question, String answer, int count)
    {
       this.title = ttl;
       this.front = question;
       this.back = answer;
       this.counter = count;
    }
    
    public List<String> getFrontContent()
    {
       List<String> myList = new ArrayList<>();
       myList.add(this.title);
       myList.add(this.front);
       return myList;
    }
    
    public List<String> getBackContent()
    {
       List<String> myList = new ArrayList<>();
       myList.add(this.back);
       return myList;
    }
    
    public List<String> getContent()
    {
       List<String> myList = new ArrayList<>();
       myList.add(this.title);
       myList.add(this.front);
       myList.add(this.back);
       return myList;
    }
    
    public void printToConsole()
    {
       System.out.println("the title of this card is: " + this.title);
       System.out.println("the front matter is: " + this.front);
       System.out.println("the back matter is: " + this.back);
    }
    
    public void upCounter()
    {
       this.counter ++;
    }
    
    public int getCounter()
    {
       return this.counter;
    }
    
}

// ------------------------------------------------------ //
// -------------- ToDo: add your own card --------------- //
// ------------------------------------------------------ //
