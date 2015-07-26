package com.francoiscabrol.tictactoe;

import java.util.List;

/**
 * @author Francois Cabrol <francois.cabrol@live.fr>
 * @since 15-07-25
 */

public class Game {
    private static Game instance = null;

    enum State {
        READY, START, OVER
    }

    public State gameState;

    final int defaultPlayer = 1;
    private int currentPlayer;
    private GameBoard    board;
    private GameStatus   status;
    private GameControls controls;

    protected Game() {
        
    }

    public static Game getInstance() {
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void initialize(GameBoard board, GameStatus status, GameControls controls) {
        this.board    = board;
        this.status   = status;
        this.controls = controls;
        ready();
    }

    private void setState(State state) {
        gameState = state;
        
        switch (gameState) {
            case START:
                status.setGameStatus("The player " + currentPlayer + " has to play!");
                controls.setState(GameControls.State.PLAYING);
                break;
            case OVER:
                status.setGameStatus("Game over!");
                controls.setState(GameControls.State.PLAYING);
                break;
            case READY:
                status.setGameStatus("Press start to play!");
                controls.setState(GameControls.State.WAIT);
                break;
        }
    }
    
    private void ready() {
        setState(State.READY);
        currentPlayer = defaultPlayer;
    }
    
    public void start() {
        if (gameState != State.START) {
            setState(State.START);
        } else {
            status.setWarning("Already started!");
        }
    }
    

    public void end(int winner) {
        setState(State.OVER);
        if (winner == 0) {
            status.setWarning("No winner :(");
        } else {
            String str = "The player " + winner + " win !";
            status.setWarning(str);
        }
    }
    
    public void selectSquare(Square square) {
        if (gameState == State.START) {
            if (square.getPlayerMark() == 0) {
                square.markByPlayer(currentPlayer);
                int winner = findWinner();
                if (winner != 0) {
                    end(winner);   
                } else if (isOver()) {
                    end(0);
                } else {
                    next();
                }
            } else {
                status.setWarning("The square is already full!");
            }
        } else {
            if (gameState == State.OVER) {
                status.setWarning("Please reset and start a new game.");
            } else {
                status.setWarning("The game is not started!");
            }
        }
    }

    public int findWinner() {
        List<SquareLine> lines = board.getSquareLines();

        for (SquareLine squareLine : lines) {
            String string = squareLine.getPlayerMarks();
            if (string.contains("111")) {
                squareLine.highlight();
                return 1;
            }
            else if (string.contains("222")) {
                squareLine.highlight();
                return 2;
            }
        }

        return 0;
    }

    public boolean isOver() {
        return board.isFull();
    }
    
    public void next() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        setState(State.START);
    }


    public void reset() {
        int numOfSquares = board.numOfSquares;
        Square[][] squares = board.squares;
        
        for (int i = 0; i < numOfSquares; i++) {
            for (int j = 0; j < numOfSquares; j++) {
                squares[i][j].reset();
            }
        }
        
        ready();
    }
}
