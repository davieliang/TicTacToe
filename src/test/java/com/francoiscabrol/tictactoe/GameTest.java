package com.francoiscabrol.tictactoe;

import static org.mockito.Mockito.*;
import javafx.application.Application;
import javafx.stage.Stage;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

    public static class AsNonApp extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            // --
        }
    }

    @BeforeClass
    public static void initJFX() throws Exception {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(AsNonApp.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
    }
    
    @Test
    public void testStart() throws Exception {
        GameStatus gameStatus = new GameStatus();
        Game.getInstance().initialize(new GameBoard(), gameStatus, new GameControls());
        Game game = Game.getInstance();
        
        game.start();
        Assert.assertTrue(game.gameState == Game.State.START);
        Assert.assertTrue(gameStatus.warning.getText().isEmpty());
        
        game.start();
        Assert.assertTrue(gameStatus.warning.getText() == "Already started!");
    }
    
    @Test
    public void testEnd()  throws Exception {
        GameStatus gameStatus = new GameStatus();
        Game.getInstance().initialize(new GameBoard(), gameStatus, new GameControls());
        Game game = Game.getInstance();
        
        game.end(0);
        Assert.assertTrue(game.gameState == Game.State.OVER);
        Assert.assertTrue(gameStatus.warning.getText().contains("No winner"));

        game.end(1);
        Assert.assertTrue(gameStatus.warning.getText().contains("The player"));
    }

    @Test
    public void testSelectSquare()  throws Exception {
        Game game = spy(Game.class);
        GameStatus gameStatus = new GameStatus();
        game.initialize(new GameBoard(), gameStatus, new GameControls());

        game.start();
        Square square = new Square();
        game.selectSquare(square);
        verify(game).next();
        verify(game).findWinner();

        stub(game.findWinner()).toReturn(2);
        game.selectSquare(new Square());
        verify(game).end(2);
        
        game.start();
        stub(game.findWinner()).toReturn(0);
        stub(game.isOver()).toReturn(true);
        game.selectSquare(new Square());
        verify(game).end(0);
    }
    
    @Test
    public void testFindWinner()  throws Exception {
        Game game = spy(Game.class);
        GameStatus gameStatus = new GameStatus();
        GameBoard board = spy(GameBoard.class);
        game.initialize(board, gameStatus, new GameControls());

        // Check if it can find that the second player win
        Square[] squares = new Square[board.numOfSquares];
        for (int i = 0; i < squares.length; i++) {
            squares[i] = new Square();
            squares[i].markByPlayer(2);
        }
        SquareLine squareLine = new SquareLine(squares);
        List<SquareLine> list = new ArrayList<>();
        list.add(squareLine);
        stub(board.getSquareLines()).toReturn(list);
        Assert.assertTrue(game.findWinner() == 2);

        // Check if it can find that the first player win
        squares = new Square[board.numOfSquares];
        for (int i = 0; i < squares.length; i++) {
            squares[i] = new Square();
            squares[i].markByPlayer(1);
        }
        squareLine = new SquareLine(squares);
        list = new ArrayList<>();
        list.add(squareLine);
        stub(board.getSquareLines()).toReturn(list);
        Assert.assertTrue(game.findWinner() == 1);
    }

}