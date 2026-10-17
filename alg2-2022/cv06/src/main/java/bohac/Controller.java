package bohac;

import bohac.dialogs.GridSizeDialog;
import bohac.util.Logic;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The main controller
 */
public class Controller implements Initializable {
    public Game game;
    public VBox infoBox;

    /**
     * Code that doesn't need access to @FXML annotated
     * fields (@FXML annotation is optional since newer versions of JavaFX)
     */
    public Controller() {
        // Initialize a new game
        GridSizeDialog dialog = new GridSizeDialog();
        Optional<Game> game = dialog.showAndWait();
        if (game.isEmpty()) System.exit(0);
        else {
            this.game = game.get();
            Logic.setGameInstance(this.game);
        }
    }

    /**
     * The root parent of the GUI application
     */
    public BorderPane root;
    /**
     * Grid pro piskvorky, initially 0x0, velikost se nastavi pragramaticky az na to prijde cas
     */
    public GridPane grid;
    public Label player1Label, player2Label;
    public Label player1Wins, player2Wins;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupGrid();
        bindCurrentGameState(game);
        game.injectGridMechanics(grid);
    }

    private void setupGrid() {
        // The documentation states that this is for debugging purposes only but im a thug
        grid.setGridLinesVisible(true);
        double width = game.getRowCount();
        double height = game.getColumnCount();
        for (int i = 0; i < width; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100 / height);
            grid.getRowConstraints().add(rowConst);
        }
        for (int i = 0; i < height; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100 / width);
            grid.getColumnConstraints().add(colConst);
        }
    }

    private void bindCurrentGameState(Game game) {
        // Binding the current player property from the game object to the player 1 and player 2 labels - when
        // it's player 1's move, their label gets bolder
        player1Label.styleProperty().bind(Bindings.createStringBinding(() ->
                game.getCurrentPlayer() == 1 ? "-fx-font-weight: bold" : "", game.currentPlayerProperty()));
        player2Label.styleProperty().bind(Bindings.createStringBinding(() ->
                game.getCurrentPlayer() == 2 ? "-fx-font-weight: bold" : "", game.currentPlayerProperty()));

        // Make the grid border the same color as the color of the current player
        grid.borderProperty().bind(Bindings.createObjectBinding(() -> switch (game.getCurrentPlayer()) {
            case 1 -> new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM));
            case 2 -> new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM));
            default -> null;
        }, game.currentPlayerProperty()));

        // Binding the player wins properties from the game object to their respective labels
        player1Wins.textProperty().bind(Bindings.createStringBinding(() -> String.valueOf(game.getP1Wins()), game.p1WinsProperty()));
        player2Wins.textProperty().bind(Bindings.createStringBinding(() -> String.valueOf(game.getP2Wins()), game.p2WinsProperty()));
    }
}
