package com.francoiscabrol.tictactoe;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Francois Cabrol <francois.cabrol@live.fr>
 * @since 15-07-25
 */
public class GameBoard extends GridPane {

    final int numOfSquares = 3;
    Square[][] squares = new Square[numOfSquares][numOfSquares]; // The squares set as 3 dimensional array
    private List<SquareLine> squareLines; // The squares set as list of lines

    public GameBoard() {
        build();
    }
    
    private void build() {
        for (int i = 0; i < numOfSquares; i++) {
            for (int j = 0; j < numOfSquares; j++) {
                Square btn = new Square();
                add(btn, i, j);
                setFillHeight(btn, true);
                setFillWidth(btn, true);
                squares[i][j] = btn;
            }
        }        
        initializeSquareLines();
    }

    public boolean isFull() {
        for (int i = 0; i < numOfSquares; i++) {
            for (int j = 0; j < numOfSquares; j++) {
                if (squares[i][j].getPlayerMark() == 0) return false;
            }
        }
        return true;
    }
    
    public List<SquareLine> getSquareLines() {
        return squareLines;
    }
    
    private void initializeSquareLines() {
        // List all lines with pattern and corresponding squares
        List<SquareLine> lines = new ArrayList<>();
        
        // Add row's squares
        for (int row = 0; row < numOfSquares; row++) {
            Square[] squaresLine = new Square[numOfSquares];
            for (int column = 0; column < numOfSquares; column++) {
                squaresLine[column] = squares[row][column];
            }
            lines.add(new SquareLine(squaresLine));
        }

        // Add column's squares
        for (int column = 0; column < numOfSquares; column++) {
            Square[] squaresLine  = new Square[numOfSquares];
            for (int row = 0; row < numOfSquares; row++) {
                squaresLine[row] = squares[row][column];
            }
            lines.add(new SquareLine(squaresLine));
        }

        // Add first diagonal's squares
        Square[] squaresLine1 = new Square[numOfSquares];
        for (int i = 0; i < numOfSquares; i++) {
            squaresLine1[i] = squares[i][i];
        }
        lines.add(new SquareLine(squaresLine1));

        // Add second diagonal's squares
        Square[] squaresLine2 = new Square[numOfSquares];
        for (int i = numOfSquares-1; i >= 0; i--) {
            int j = numOfSquares - i -1;
            squaresLine2[i] = squares[j][i];
        }
        SquareLine squareLine1 = new SquareLine(squaresLine2);
        lines.add(squareLine1);
        
        squareLines = lines;
    }

}
