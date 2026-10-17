package bohac.exception;

import javafx.util.Pair;

public final class InvalidBoardSizeException extends IllegalArgumentException {

    public InvalidBoardSizeException(Pair<Integer, Integer> size) {
        super(String.format("Invalid board size for %dx%d, the minimum is 3x3.", size.getKey(), size.getValue()));
    }

    public static void validate(Pair<Integer, Integer> size) {
        Integer width = size.getKey();
        Integer height = size.getValue();
        if (width < 3 || height < 3) throw new InvalidBoardSizeException(size);
    }
}
