package bohac.dialogs;

import bohac.Game;
import bohac.Settings;
import bohac.util.ITicTacToe;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.util.Pair;

public class GridSizeDialog extends Dialog<Game> {
    private Spinner<Integer> rows, columns;

    public GridSizeDialog() {
        init();
        setResultConverter(buttonType -> {
            if (buttonType.getButtonData().equals(ButtonBar.ButtonData.OK_DONE))
                return (Game) ITicTacToe.of(rows.getValue(), columns.getValue());
            return null;
        });
    }

    public void init() {
        setTitle("Choose the grid size");
        initModality(Modality.APPLICATION_MODAL);

        this.rows = new Spinner<>(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(3, Integer.MAX_VALUE, Settings.DEFAULT_SIZE.getKey())
        );
        this.columns = new Spinner<>(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(3, Integer.MAX_VALUE, Settings.DEFAULT_SIZE.getValue())
        );

        ButtonType done = new ButtonType("Play", ButtonBar.ButtonData.OK_DONE);
        ButtonType close = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(done, close);

        VBox rowsContainer = new VBox(new Label("Rows: "), rows);
        VBox colsContainer = new VBox(new Label("Columns: "), columns);
        rowsContainer.setAlignment(Pos.CENTER);
        colsContainer.setAlignment(Pos.CENTER);
        getDialogPane().setContent(new VBox(rowsContainer, colsContainer));
    }
}
