package bohac.util;

import javafx.beans.property.IntegerProperty;
import javafx.scene.layout.Pane;

/**
 * Piskvorky jsou prakticky jenom grid. Initially ma okno GridPane 0x0, aplikace si ho pak initializuje na velikost,
 * kterou chce a do kazdeho okynka vlozi AnchorPane (na ten potom aplikuju listenery aby to bylo trosku interaktivni a fancy)
 */
public class BoardCell {
    /**
     * Cislo hrace, ktery vlastni tuhle bunku. Je to typ IntegerProperty, ktery umi detekovat zmenu hodnoty. Kdyz se hodnota
     * zmeni, tak se to ukaze uzivateli - bunka bude obsazena symbolem daneho hrace
     */
    private final IntegerProperty integerProperty;
    private Pane pane;

    public BoardCell(IntegerProperty integerProperty) {
        this.integerProperty = integerProperty;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public int getIntegerProperty() {
        return integerProperty.get();
    }

    public IntegerProperty integerProperty() {
        return integerProperty;
    }

    public Pane getPane() {
        return pane;
    }

    /**
     * Resets the state for this cell - owner is set to 0 => no owner, pane is cleared of its children => the
     * symbols disappear
     */
    public void resetState() {
        integerProperty.set(0);
        clearPane();
    }

    private void clearPane() {
        if (pane == null) return;
        pane.getChildren().clear();
    }
}
