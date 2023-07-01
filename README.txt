A program for creating Learning Cards from Markdown Files and testing them in terminal.

#Contents of README:
See Line 8 - 30 for Syntax Instructions
See Line 33 - 43 for Compiling & Execution Instructions


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

#From Markdown:

1. open folder "com" in Terminal
2. execute:
     javac -classpath "/home/uni/Documents/se/semesterprojekt/se-learning-cards/com/" LearningCardGame.java
3. execute:
     java -classpath "/home/uni/Documents/se/semesterprojekt/se-learning-cards/com/" LearningCardGame
