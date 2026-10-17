/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education. 
 * You can use it as you need to learn Java. 
 */
package bohac.hry.piskvorky;

import bohac.hry.piskvorky.exceptions.TicTacToeException;

/**
 * Tento interface definuje rozhrani trid objektu src.hry Piskvorky.
 *
 * @author ALG
 */
public interface TicTacToeInterface {
    float OFFSET = 0.5F;
    int MIN_SIZE = 5;
    int MAX_SIZE = 30;

    /**
     * Pro zadane rozmery hraci plochy vrati objekt src.hry piskvorky
     *
     * @param pr pocet radku
     * @param ps pocet sloupcu
     * @return vytvoreny objekt
     * @throws TicTacToeException v pripade chybnych rozmeru plochy
     */
    static TicTacToeInterface getInstance(int pr, int ps) {
        if (pr < MIN_SIZE) {
            throw new TicTacToeException("Maly pocet radku", 101, "" + pr);
        }
        if (pr > MAX_SIZE) {
            throw new TicTacToeException("Velky pocet radku", 101, "" + pr);
        }
        if (ps < MIN_SIZE) {
            throw new TicTacToeException("Maly pocet sloupcu", 101, "" + ps);
        }
        if (ps > MAX_SIZE) {
            throw new TicTacToeException("Velky pocet sloupcu", 101, "" + ps);
        }
        return new TicTacToe(pr, ps);
    }

    /**
     * Polozi dalsi symbol aktualne hrajiciho hrace na pozici (r, s), pokud je
     * dana pozice volna. Metoda vraci informaci o tom jake nejdelsi n-tice je
     * nove polozeny symbol soucasti.
     *
     * @param r index radku
     * @param s index sloupce
     * @return delku nejdelsi souvisle n-tice, jejiz soucasti je nove polozeny
     * symbol.
     * @throws TicTacToeException v pripade ze je pozice mimo rozsah nebo je
     * pozice obsazena, nebo jde o tah po ukonceni src.hry. Konkretni chybovy stav z
     * tecto tri uvedenych lze urcit z chyboveho kodu generovane vyjimky.
     */
    int placeNextSymbol(int r, int s);

    /**
     * Poskytuje cislo symbolu polozene na pozici (r, s).
     *
     * @param r index radku
     * @param s index sloupce
     * @return symbol 0,1,2
     * @throws TicTacToeException v pripade, ze zadana pozice je mimo rozsah
     * hraciho pole.
     */
    int getSymbol(int r, int s);

    /**
     * Poskytuje pocet radku hraciho pole
     *
     * @return pocet radku
     */
    int getRows();

    /**
     * Poskytuje pocet slopcu hraciho pole
     *
     * @return pocet sloupcu
     */
    int getColumns();

    /**
     * Informace o ukonceni src.hry
     *
     * @return true po ukonceni src.hry v ostatnich pripadech false
     */
    boolean isGameFinished();

    /**
     * Pred ukoncenim vraci cislo hrace ktery prave ted hraje. Po ukonceni src.hry
     * vraci hodnotu 0.
     *
     * @return cislo hrace nebo 0
     */
    int getPlayer();

    /**
     * Po ukonceni src.hry poskytuje cislo hrace (1 nebo 2), ktery vyhral. Pred
     * ukoncenim src.hry vraci hodnotu 0.
     *
     * @return cislo hrace nebo 0
     */
    int getWinner();
}
