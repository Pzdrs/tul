package bohac.util;

import bohac.Game;
import bohac.exception.InvalidBoardSizeException;
import javafx.util.Pair;

public interface ITicTacToe {

    Pair<Integer, Integer> MIN_SIZE = new Pair<>(3, 3);
    Pair<Integer, Integer> MAX_SIZE = new Pair<>(10, 10);

    /**
     * Pro zadane rozmery hraci plochy vrati objekt hry piskvorky
     *
     * @param pr pocet radku
     * @param ps pocet sloupcu
     * @return vytvoreny objekt
     * @throws InvalidBoardSizeException v pripade chybnych rozmeru plochy
     */
    static ITicTacToe of(int pr, int ps) {
        if (pr < MIN_SIZE.getKey() || ps < MIN_SIZE.getValue() || pr > MAX_SIZE.getKey() || ps > MAX_SIZE.getValue())
            throw new InvalidBoardSizeException(new Pair<>(pr, ps));
        return new Game(new Pair<>(pr, ps));
    }

    /**
     * Polozi dalsi symbol aktualne hrajiciho hrace na pozici (r, s), pokud je
     * dana pozice volna.
     *
     * @param cell the board cell
     */
    void placeNextSymbol(BoardCell cell);

    /**
     * Poskytuje cislo symbolu polozene na pozici (r, s).
     *
     * @param cell the board cell
     * @return symbol 0,1,2
     * @throws IndexOutOfBoundsException v pripade, ze zadana pozice je mimo rozsah
     *                                   hraciho pole.
     */
    int getSymbol(BoardCell cell);

    /**
     * Poskytuje pocet radku hraciho pole
     *
     * @return pocet radku
     */
    int getRowCount();

    /**
     * Poskytuje pocet slopcu hraciho pole
     *
     * @return pocet sloupcu
     */
    int getColumnCount();

    /**
     * Pred ukoncenim vraci cislo hrace ktery prave ted hraje. Po ukonceni hry
     * vraci hodnotu 0.
     *
     * @return cislo hrace nebo 0
     */
    int getCurrentPlayer();
}
