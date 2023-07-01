A program for creating Learning Cards from Markdown Files and testing them in terminal.

#Contents of README:
See Line 09 - 12 for Folder Structure
See Line 14 - 35 for Markdown Syntax Instructions
See Line 38 - 46 for Compiling & Execution Instructions


#Folder Structure

- "src" folder contains source code
- markdown files to be saved in "files/markdownFiles" (the program will not be able to find them otherwise!)

#Markdown Syntax for Learning Cards:
You can find an example file in "files/markdownFiles"

CardType 1:
"  # Front Matter of Learning Card
   Back Matter of Learning Card"

CardType 2:
"  # Title of Learning Card {QUESTION}

   ## Text for front side of card {FRONT}
   
   More text for front side of card
   The number of lines on the front side does not have a limit
   
   ## Text for back side of card {BACK}
   Unlimited Number of lines with more content for the back side of the card"


#TO SUCCESFULLY COMPILE & EXECUTE with javac:

replace "[path]" with the path to the parents folder which this folder ("se-learning-cards") is in

1. open folder "src" in Terminal
2. execute:
     javac -classpath "/[path]/se-learning-cards/src/" LearningCardGame.java
3. execute:
     java -classpath "/[path]/se-learning-cards/src/" LearningCardGame
