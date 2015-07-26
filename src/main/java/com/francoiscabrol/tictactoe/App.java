package com.francoiscabrol.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tic Tac Toe - play!");

        GameBoard    board    = new GameBoard();
        GameStatus   status   = new GameStatus();
        GameControls controls = new GameControls();
        Game.getInstance().initialize(board, status, controls);

        HBox topBar = new HBox(10); // Set spacing to 10
        topBar.getChildren().addAll(controls, status);

        BorderPane gameContainer = new BorderPane();
        gameContainer.setTop(topBar);
        gameContainer.setCenter(board);

        primaryStage.setScene(new Scene(gameContainer, 600, 700));
        primaryStage.show();
    }
}