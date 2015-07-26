package com.francoiscabrol.tictactoe;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author Francois Cabrol <francois.cabrol@live.ca>
 * @since 15-07-24
 */
public class Square extends Button {
    
    final String defaultText   = " ";
    final String playerOneText = "X";
    final String playerTwoText = "O";

    public Square() {
        setPrefSize(300, 300);
        setFont(Font.font("Arial", 60));
        setOnAction(event -> Game.getInstance().selectSquare(this));
        reset();
    }

    public void reset() {
        setText(defaultText);
        setTextFill(Color.BLACK);
    }
    
    public void highlight() {
        setTextFill(Color.RED);
    }

    public void markByPlayer(int player) {
        if (player == 1)
            setText(playerOneText);
        else
            setText(playerTwoText);
    }
    
    public int getPlayerMark() {
        String text = getText();
        switch (text) {
            case playerOneText:
                return 1;
            case playerTwoText:
                return 2;
            default:
                return 0;
        }
    }
    
}
