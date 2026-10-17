package bohac;

import bohac.exception.InvalidBoardSizeException;
import bohac.util.BoardCell;
import bohac.util.ITicTacToe;
import bohac.util.Logic;
import bohac.util.Utils;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.util.Pair;

/**
 * The main game class - keeps track of the game state, does all the logic
 */
public class Game implements ITicTacToe {
    public enum GameResult {
        TIE, NORMAL
    }

    private final BoardCell[][] board;
    private final int rows, columns;
    private final IntegerProperty currentPlayer;
    private final IntegerProperty p1Wins, p2Wins;


    public Game(Pair<Integer, Integer> size) {
        InvalidBoardSizeException.validate(size);
        this.rows = size.getKey();
        this.columns = size.getValue();

        this.board = new BoardCell[size.getKey()][size.getValue()];
        Logic.initializeBoard(board);

        this.p1Wins = new SimpleIntegerProperty(0);
        this.p2Wins = new SimpleIntegerProperty(0);

        // If Settings.STARTING_PLAYER == 0, a random person is chosen to be the first to make a move
        this.currentPlayer = new SimpleIntegerProperty(Utils.getStartingPlayer());
    }

    /**
     * This method gets called if a win/tie is detected
     *
     * @param result win/tie
     * @param winner the id of the winning player - 0 for a TIE
     */
    private void end(GameResult result, int winner) {
        Controller controller = TicTacToe.getController();
        if (result == GameResult.NORMAL) {
            if (winner == 1) {
                p1WinsProperty().set(getP1Wins() + 1);

                Label label = new Label("Player 1 has WON this round!");
                label.setFont(new Font(25));
                label.setTextFill(Color.RED);
                controller.infoBox.getChildren().add(label);
            } else if (winner == 2) {
                p2WinsProperty().set(getP2Wins() + 1);

                p1WinsProperty().set(getP1Wins() + 1);
                Label label = new Label("Player 2 has WON this round!");
                label.setFont(new Font(25));
                label.setTextFill(Color.BLUE);
                controller.infoBox.getChildren().add(label);
            }
        } else if (result == GameResult.TIE) {
            Label label = new Label("It's a TIE!");
            label.setFont(new Font(25));
            label.setTextFill(Color.BLACK);
            controller.infoBox.getChildren().add(label);
        }
        // After 2 seconds, remove the message
        new Timeline(
                new KeyFrame(Duration.seconds(2), actionEvent -> controller.infoBox.getChildren().clear())
        ).play();
        reset();
    }

    /**
     * Most of the interactive logic - what happens when player hovers over a cell or clicks on it. The symbols are
     *
     * @param grid the GridPane instance
     */
    public void injectGridMechanics(GridPane grid) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                BoardCell cell = board[i][j];
                AnchorPane pane = new AnchorPane();

                // cell claim mechanics
                pane.setOnMouseClicked(mouseEvent -> placeNextSymbol(cell));

                // Fancy border when hover over with a cursor - selection indicator
                Logic.setOnCellHoverListener(cell, pane);

                // What happens after a cell is claimed
                Logic.setCellOwnerChangeListener(cell, pane);

                cell.setPane(pane);
                grid.add(pane, j, i);
            }
        }
    }

    /**
     * Reset the game - a new random player is chosen to be the first one to start and the board is reinitialized
     */
    public void reset() {
        currentPlayerProperty().set(Utils.getStartingPlayer());
        // Reinitialize the board
        Logic.initializeBoard(board);
    }

    /**
     * This method gets called after the player makes his choice and clicks on a cell - therefore claiming it
     *
     * @param cell the BoardCell instance which to claim
     */
    @Override
    public void placeNextSymbol(BoardCell cell) {
        // only claim that cell if it's empty
        if (getSymbol(cell) != 0) return;
        Pane pane = cell.getPane();

        // apply the visuals
        cell.integerProperty().set(getCurrentPlayer());

        // basically turn off the selection state on the cell - remove the border and change the cursor to the default
        pane.setBorder(null);
        pane.setCursor(Cursor.DEFAULT);

        // check for win first, then tie
        if (Logic.checkForWin()) {
            end(GameResult.NORMAL, getCurrentPlayer());
        } else if (Logic.checkForTie()) {
            end(GameResult.TIE, 0);
        } else {
            currentPlayerProperty().set(getCurrentPlayer() == 1 ? 2 : 1);
        }
    }

    @Override
    public int getSymbol(BoardCell cell) {
        return cell.getIntegerProperty();
    }

    @Override
    public int getRowCount() {
        return rows;
    }

    @Override
    public int getColumnCount() {
        return columns;
    }

    @Override
    public int getCurrentPlayer() {
        return currentPlayer.get();
    }

    public IntegerProperty currentPlayerProperty() {
        return currentPlayer;
    }

    public BoardCell[][] getBoard() {
        return board;
    }

    public int getP1Wins() {
        return p1Wins.get();
    }

    public IntegerProperty p1WinsProperty() {
        return p1Wins;
    }

    public int getP2Wins() {
        return p2Wins.get();
    }

    public IntegerProperty p2WinsProperty() {
        return p2Wins;
    }
}
