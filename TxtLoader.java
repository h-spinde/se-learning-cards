import java.util.*;
import java.io.*;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

public class TxtLoader {

    public static List<String> readFile(String file_path)
    {
        List<String> myList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file_path)))
        {
                myList = reader.lines().collect(Collectors.toList());
        }catch(Exception e)
        {
                 e.printStackTrace();
        }
        return myList;
    }
    
   public static int findCard(List<String> list, int min)
   {
        for(int i=min;i<list.size();i++)
        {
             if ((list.get(i)).startsWith("##"))
             {
                  return i;
             }
        }
        return list.size();
   }
   
   public static int whichCard(List<String> list, int begin, int end)
   {
        if ((list.get(begin)).equals("#SimpleCard"))
        {
             return 0;
        }
        else
        {
             return 1;
        }
   }
   
   public static String[] makeSimpleArray(List<String> list, int begin, int end)
   {
       String[] myString = new String[2];
       begin ++;
       myString[0] = (list.get(begin)).replace(String.valueOf("#"), "");
       begin ++;
       int i = 0;
       while ((begin < end-1) && (i == 0))
       {
            if ((list.get(begin)).contains("#"))
            {
                 i++;
            }
            else
            {
                 myString[0] += "\n" + list.get(begin);
                 begin ++;
            }
       }
       myString[1] = (list.get(begin)).replace(String.valueOf("#"), "");
       begin ++;
       while (begin < end-1)
       {
            myString[1] += "\n" + list.get(begin);
            begin ++;
       }
       return myString;
   }
   
   public static String[] makeQuestionArray(List<String> list, int begin, int end)
   {
       String[] myString = new String[3];
       begin ++;
       myString[0] = (list.get(begin)).replace(String.valueOf("#"), "");
       begin ++;
       myString[1] = (list.get(begin)).replace(String.valueOf("#"), "");
       begin ++;
       int i = 0;
       while ((begin < end-1) && (i == 0))
       {
            if ((list.get(begin)).contains("#"))
            {
                 i++;
            }
            else
            {
                 myString[1] += "\n" + list.get(begin);
                 begin ++;
            }
       }
       myString[2] = (list.get(begin)).replace(String.valueOf("#"), "");
       begin ++;
       while (begin < end-1)
       {
            myString[2] += "\n" + list.get(begin);
            begin ++;
       }
       return myString;
   }
   
   public static SimpleCard makeSimpleCard(List<String> list, int begin, int end)
   {
       String[] myString = makeSimpleArray(list, begin, end);
       SimpleCard newCard = new SimpleCard(myString[0], myString[1], Integer.parseInt(list.get(end-2)));
       return newCard;
   }
   
   public static QuestionCard makeQuestionCard(List<String> list, int begin, int end)
   {
       String[] myString = makeQuestionArray(list, begin, end);
       QuestionCard newCard = new QuestionCard(myString[0], myString[1], myString[2], Integer.parseInt(list.get(end-2)));
       return newCard;
   }
   
   public static LearningCard makeCard(List<String> list, int min)
   {
        int begin = findCard(list, min);
        if (begin >= list.size() - 1) { return null; }
        int end = findCard(list, begin + 1);
        int cardType = whichCard(list, begin, end);
        if (cardType == 0)
        {
            return null;
        }
        if (cardType == 1)
        {
            return makeSimpleCard(list, begin, end);
        }
        if (cardType == 2)
        {
            return makeQuestionCard(list, begin, end);
        }
        return null;
   }
    
    public static List<LearningCard> loadCardFile(String file_path) {
        List<String> myList = readFile(file_path);
        List<LearningCard> myCards = new ArrayList<>();
        int i = 0;
        while (i < myList.size() - 2)
        {
            LearningCard newCard = makeCard(myList,i);
            if (newCard != null)
            {
                myCards.add(newCard);
            }
            i = findCard(myList,i)+1;
        }
        return myCards;
    }

    // ---------------------------------------------------- //
    public static void main(String[] args) {
        List<LearningCard> myList = loadCardFile("/home/uni/Documents/se/semesterprojekt/se-learning-cards/example.txt");
        for (int i = 0; i < myList.size(); i++)
        {
            (myList.get(i)).printToConsole();
        }
    }
}
