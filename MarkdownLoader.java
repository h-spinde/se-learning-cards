import java.util.*;
import java.io.*;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

public class MarkdownLoader {

  public static List<String> readFile(String file_path) {
    List<String> myList = new ArrayList<>();
    try(BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
      myList = reader.lines().collect(Collectors.toList());
    } catch(Exception e) {
      e.printStackTrace();
    }
    return myList;
  }
    
  public static int findCard(List<String> list, int min) {
    for(int i=min;i<list.size();i++) {
      if ((list.get(i)).startsWith("# ")) {
        return i;
      }
    }
    return list.size();
  }
   
  public static int findTag(List<String> list, int min, int max) {
    for(int i=min;i<max;i++) {
      if ((list.get(i)).startsWith("## ")) {
        return i;
      }
    }
    return 0;
  }
   
  public static int corrSimpleCard(List<String> list, int min, int max) {
    min ++;
    if ((list.get(min-1)).startsWith("# ")) {
      while (min < max) {
        if ((list.get(min)).startsWith("#")) { return 0; }
        min ++;
      }
      return 1;
    }
    return 0;
  }
   
  public static int corrQuestionCard(List<String> list, int i, int max) {
    if (((list.get(i)).startsWith("# ")) && ((list.get(i)).contains(" {QUESTION}"))) {
      while (i < max) {
        if (((list.get(i)).startsWith("## ")) && ((list.get(i)).contains(" {BACK}"))) {
          return 2;
        }
        i++;
      }
    }
    return 0;
  }
   
  public static int whichCard(List<String> list, int begin, int end) {
    if (findTag(list, begin, end) == 0) {
      return corrSimpleCard(list, begin, end);
    } else {
      return corrQuestionCard(list, begin, end);
    }
  }
   
  public static String[] makeSimpleArray(List<String> list, int begin, int end) {
    String[] myString = new String[2];
    myString[0] = (list.get(begin)).replace(String.valueOf("# "), "");
    begin ++;
    myString[1] = list.get(begin);
    begin ++;
    while (begin < end-1) {
      if ((list.get(begin)).compareTo("") != 0) {
        myString[1] += "\n" + list.get(begin);
      }
      begin ++;
    }
    return myString;
  }
   
  public static List<String> sortQuestionList(List<String> list, int begin, int end) {
    List<String> myList = new ArrayList<>();
    myList.add(list.get(begin));
    int firstTag = findTag(list, begin, end);
    int nextTag = firstTag;
    for (int i = begin+1; i < nextTag; i++) {
      myList.add(list.get(i));
    }
    int i2 = firstTag;
    while (i2 < end) {
      nextTag = findTag(list, i2+1, end);
      if (nextTag == 0) {
        nextTag = end;
      }
      if ((list.get(i2)).contains("{FRONT}")) {
        for (int j = i2; j < nextTag; j++) {
          myList.add(list.get(j));
        }
      }
      i2 = nextTag;
    }
    i2 = firstTag;
    while (i2 < end) {
      nextTag = findTag(list, i2+1, end);
      if (nextTag == 0) {
        nextTag = end;
      }
      if ((list.get(i2)).contains("{BACK}")) {
        for (int j2 = i2; j2 < nextTag; j2++) {
          myList.add(list.get(j2));
        }
      }
      i2 = nextTag;
    }
    return myList;
  }
   
  public static String[] makeQuestionArray(List<String> list, int begin, int end) {
    List<String> sortedList = sortQuestionList(list, begin, end);
    String[] myString = new String[3];
    myString[0] = (sortedList.get(0)).replace(String.valueOf("# "), "");
    myString[0] = myString[0].replace(String.valueOf(" {QUESTION}"), "");
    int border = 0;
    int i = 1;
    while ((i < sortedList.size()) && (border == 0)) {
      if (((sortedList.get(i)).startsWith("## ")) && ((sortedList.get(i)).contains(" {BACK}"))) {
        border = i;
      }
      i++;
    }
    border --;
    for (int j = 1; j < border; j++) {
      myString[1] += (sortedList.get(j)).replace(String.valueOf("## "), "") + "\n";
    }
    myString[1] = (myString[1].replace(String.valueOf("{FRONT}"), ""));
    myString[1] = (myString[1].replace(String.valueOf("null"), ""));
    for (int j2 = border; j2 < sortedList.size(); j2++) {
      myString[2] += (sortedList.get(j2)).replace(String.valueOf("## "), "") + "\n";
    }
    myString[2] = (myString[2].replace(String.valueOf("{BACK}"), ""));
    myString[2] = (myString[2].replace(String.valueOf("null"), ""));
    return myString;
  }
   
  public static SimpleCard makeSimpleCard(List<String> list, int begin, int end) {
    String[] myString = makeSimpleArray(list, begin, end);
    SimpleCard newCard = new SimpleCard(myString[0], myString[1]);
    return newCard;
  }
   
  public static QuestionCard makeQuestionCard(List<String> list, int begin, int end) {
    String[] myString = makeQuestionArray(list, begin, end);
    QuestionCard newCard = new QuestionCard(myString[0], myString[1], myString[2]);
    return newCard;
  }
   
  public static LearningCard makeCard(List<String> list, int min) {
    int begin = findCard(list, min);
    if (begin >= list.size() - 1) { return null; }
    int end = findCard(list, begin + 1);
    int cardType = whichCard(list, begin, end);
    if (cardType == 0) {
      return null;
    }
    if (cardType == 1) {
      return makeSimpleCard(list, begin, end);
    }
    if (cardType == 2) {
      return makeQuestionCard(list, begin, end);
    }
    return null;
  }
    
  public static List<LearningCard> loadCardFile(String file_path) {
    List<String> myList = readFile(file_path);
    List<LearningCard> myCards = new ArrayList<>();
    int i = 0;
    while (i < myList.size() - 2) {
      LearningCard newCard = makeCard(myList,i);
      if (newCard != null) {
        myCards.add(newCard);
      }
      i = findCard(myList,i)+1;
    }
    return myCards;
  }

  // ---------------------------------------------------- //
  public static void main(String[] args) {
    List<LearningCard> myList = loadCardFile("/home/uni/Documents/se/semesterprojekt/se-learning-cards/cards.md");
    for (int i = 0; i < myList.size(); i++) {
      (myList.get(i)).printToConsole();
    }
  }
}
