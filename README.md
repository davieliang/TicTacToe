Tic-Tac-Toe
===========

TicTacToe example in Java 8. I did it as an exercice.

## How to build

The project should be easy to import in Intellij (I work with it).
The build system used is Maven. Usually, I use SBT but I assume that Maven is more common.

Java 8 and Maven are required. No extra library (except for unit tests). The graphic library used is JavaFX.

To compile:

    mvn compile
    
To run tests:
 
    mvn test
    
To run:

   	java -cp target/classes com.francoiscabrol.tictactoe.App
    
To compile and run (Make is needed):
    
    make
    
## About the unit tests

The unit tests require JUnit4 and Mockito. The coverage is limited to the class com.francoiscabrol.tictactoe.Game,

