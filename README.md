A program for creating Learning Cards from Markdown Files and testing them in terminal.

#Contents of README:
See Line 8 - 30 for Syntax Instructions
See Line 33 - 57 for Compiling & Execution Instructions


#Learning Card Markdown Syntax:

replace "[hashtag]" with "#"

CardType 1:
"  [hashtag] Front Matter of Learning Card
   Back Matter of Learning Card"

CardType 2:
"  [hashtag] Title of Learning Card {QUESTION}
   [hashtag][hashtag] Text for front side of card {FRONT}
   More text for front side of card
   The number of lines on the front side does not have a limit
   [hashtag][hashtag] Text for back side of card {BACK}
   Unlimited Number of lines with more content for the back side of the card"

Note on CardType 2:
"  [hashtag][hashtag] Text for front side of card {FRONT}
   Unlimited Number of lines with more content for the front side of card"
and
"  [hashtag][hashtag] Text for back side of card {BACK}
   Unlimited Number of lines with more content for the back side of the card"
may be repeated an unlimited amount of times, in random order


#TO SUCCESFULLY COMPILE & EXECUTE with javac:

replace "[path]" with the path to the parents folder which this folder ("se-learning-cards") is in

#REQUIREMENTS:
- for "From Markdown": have .md file with Learning Cards according to Learning Card Syntax in folder "fileManipulation" or its subfolders
- for "From Save File": have save file with Learning Cards (generated from .md or written in according Syntax) in folder "se-learning-cards" or its subfolders

#From Markdown:

1. open folder "fileManipulation" in Terminal
2. execute:
     javac -classpath "/[path]/se-learning-cards/com/" SaveFileGenerator.java
3. execute:
     java -classpath "/[path]/se-learning-cards/com/" fileManipulation.SaveFileGenerator

then resume: "From Save File"

#From Save File

1. open folder "com" in Terminal
2. execute:
     javac -classpath "/[path]/se-learning-cards/com" ConsoleGame.java
3. execute:
     java -classpath "/[path]/se-learning-cards/com/" ConsoleGame
