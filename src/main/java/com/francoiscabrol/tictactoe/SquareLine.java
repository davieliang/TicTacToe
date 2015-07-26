package com.francoiscabrol.tictactoe;

/**
 * @author Francois Cabrol <francois.cabrol@live.fr>
 * @since 15-07-25
 */
public class SquareLine {
    Square[] squares;

    public SquareLine(Square[] squares) {
        this.squares = squares;
    }
    
    public String getPlayerMarks() {
        String line = "";
        for (int i = 0; i < squares.length; i++) {
            line += squares[i].getPlayerMark();
        }
        return line;
    }

    public void highlight() {
        for (int i = 0; i < squares.length; i++) {
            squares[i].highlight();
        }
    }
}
