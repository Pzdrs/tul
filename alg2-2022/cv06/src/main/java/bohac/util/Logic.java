package bohac.util;

import bohac.Game;
import bohac.Settings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Cursor;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Arrays;

/**
 * This class contains all the logic/mechanic related code. This class just makes the Game class less crowded.
 */
public class Logic {
    private static Game game;

    public static void setGameInstance(ITicTacToe game) {
        Logic.game = (Game) game;
    }

    private Logic() {
        throw new AssertionError();
    }

    /**
     * Check for a row/column/diagonal win of a specific player - diagonal check is
     * performed only if the grid is a square (width == height)
     *
     * @return whether a row or a column or one of the diagonals is completed by the player
     */
    public static boolean checkForWin() {
        return checkRows() || checkColumns() || (game.getRowCount() == game.getColumnCount() && checkDiagonals());
    }

    /**
     * If there are no more cells to be populated and a win hasn't occurred yet - it's a tie
     *
     * @return whether its a tie
     */
    public static boolean checkForTie() {
        return Arrays.stream(game.getBoard())
                .flatMap(Arrays::stream).noneMatch(boardCell -> boardCell.getIntegerProperty() == 0);
    }

    /**
     * Check for a filled in row
     *
     * @return whether there is a completely filled in row (with a single symbol)
     */
    private static boolean checkRows() {
        int done = 0;
        for (int i = 0; i < game.getBoard().length; i++) {
            BoardCell[] row = game.getBoard()[i];
            for (BoardCell boardCell : row) {
                if (boardCell.getIntegerProperty() == game.getCurrentPlayer()) done++;
                if (done == row.length) return true;
            }
            done = 0;
        }
        return false;
    }

    /**
     * Check for a filled in column
     *
     * @return whether there is a completely filled in column (with a single symbol)
     */
    private static boolean checkColumns() {
        int done = 0;
        for (int j = 0; j < game.getBoard()[0].length; j++) {
            for (int i = 0; i < game.getBoard().length; i++) {
                if (game.getBoard()[i][j].getIntegerProperty() == game.getCurrentPlayer()) done++;
                if (done == game.getBoard().length) return true;
            }
            done = 0;
        }
        return false;
    }

    /**
     * Checks for a win on both diagonals - only for square grids
     *
     * @return a winn occurrence on one of the two diagonals
     */
    private static boolean checkDiagonals() {
        int done = 0;
        // Main diagonal
        for (int i = 0; i < game.getBoard().length; i++) {
            BoardCell cell = game.getBoard()[i][i];
            if (cell.getIntegerProperty() == game.getCurrentPlayer()) done++;
            if (done == game.getBoard().length) return true;
        }
        done = 0;
        // Anti-diagonal
        for (int i = 0, j = game.getBoard().length - 1; i < game.getBoard().length; i++, j--) {
            BoardCell cell = game.getBoard()[i][j];
            if (cell.getIntegerProperty() == game.getCurrentPlayer()) done++;
            if (done == game.getBoard().length) return true;
        }
        return false;
    }

    /**
     * Adding a change listener on the IntegerProperty object in a cell. When the value changes, i.e. the owner of the cell changes, their
     * respective symbol is put in.
     *
     * @param cell cell on the board
     * @param pane pane child of the cell in the GridPane
     */
    public static void setCellOwnerChangeListener(BoardCell cell, Pane pane) {
        cell.integerProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue.intValue() == 1) {
                Circle circle = new Circle(pane.getWidth() / 2, pane.getHeight() / 2, pane.getWidth() / 2 - 5, Color.TRANSPARENT);
                circle.setStroke(Color.BLACK);
                pane.getChildren().add(circle);
            } else if (newValue.intValue() == 2) {
                Line line = new Line(5, 5, pane.getWidth() - 5, pane.getHeight() - 5);
                Line line2 = new Line(pane.getWidth() - 5, 5, 5, pane.getHeight() - 5);
                pane.getChildren().addAll(line, line2);
            }
        });
    }

    /**
     * Adding a change listener on the hover property of the cell - the child Pane. Modifying the cursor and the cell border.
     *
     * @param cell cell on the board
     * @param pane pane child of the cell in the GridPane
     */
    public static void setOnCellHoverListener(BoardCell cell, Pane pane) {
        pane.hoverProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue && cell.getIntegerProperty() == 0) {
                pane.setBorder(Settings.CELL_SELECTION_BORDER);
                pane.setCursor(Cursor.HAND);
            } else {
                pane.setBorder(null);
                pane.setCursor(Cursor.DEFAULT);
            }
        });
    }

    /**
     * Filling the board up with initial values so that no NPEs are produced during the logic
     * injection by {@link bohac.Game#injectGridMechanics(GridPane)} (int, int) getComponentAt}. If the
     * board had already been initialized, calling this method will just reset the board, resulting
     * in a clean slate so to speak.
     *
     * @param board the 2d array of BoardCells
     */
    public static void initializeBoard(BoardCell[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                BoardCell cell = board[i][j];
                if (cell == null) {
                    board[i][j] = new BoardCell(new SimpleIntegerProperty(0));
                } else cell.resetState();
            }
        }
    }
}
