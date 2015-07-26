package com.francoiscabrol.tictactoe;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * @author Francois Cabrol <francois.cabrol@live.fr>
 * @since 15-07-25
 */
public class GameControls extends HBox {
    public enum State {WAIT, PLAYING}
    private State state = State.WAIT;
            
    private Button btnStart;
    private Button btnReset;

    public GameControls() {
        build();
    }

    private void build() {
        btnStart = new Button();
        btnStart.setText("Start");
        btnStart.setFont(Font.font("Arial", 30));
        btnStart.setOnAction(event -> Game.getInstance().start());
        getChildren().addAll(btnStart);

        btnReset = new Button();
        btnReset.setText("Reset");
        btnReset.setFont(Font.font("Arial", 30));
        btnReset.setOnAction(event -> Game.getInstance().reset());
        getChildren().addAll(btnReset);
    }
    
    public void setState(State state) {
        this.state = state;
        switch (state) {
            case WAIT:
                btnStart.setDisable(false);
                btnReset.setDisable(true);
                break;
            case PLAYING:
                btnStart.setDisable(true);
                btnReset.setDisable(false);
                break;
        }
    }

}
