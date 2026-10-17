package bohac;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Pair;

/**
 * All values that are meant to be configurable are here
 */
public class Settings {
    public static final String WINDOW_LABEL = "Tic-Tac-Toe v0.1 - by Pycrs";
    public static final Pair<Integer, Integer> DEFAULT_SIZE = new Pair<>(5,5);
    // 1 or 2, 0 for random initial choice
    public static final int STARTING_PLAYER = 0;
    public static final Border CELL_SELECTION_BORDER = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderStroke.MEDIUM));
}
