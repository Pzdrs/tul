/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education. 
 * You can use it as you need to learn Java. 
 */
package bohac.hry.piskvorky;

/**
 *
 * @author ALG
 */
final class TicTacToeTools {

    private TicTacToeTools() {
    }

    /**
     * Metoda zjisti vrati pocet stejnych hodnot
     * v souvisle radkove rade, jejiz soucasti je symbol na pozici (r, s)
     *
     * @param a dvourozmerne pole ulozene po radcich }prvni index radkovy)
     * @param r index radku
     * @param s index sloupce
     * @return delka souvisle rady stejnych symbolu
     */
    static int pocetStejnychVRadce(int[][] a, int r, int s) {
        assert a != null;
        assert a.length > 0;
        assert r > 0;
        assert r < a.length;
        assert s > 0;
        assert s < a[0].length;
        int pocet = 1;
        // a[r][s]
        int i = s - 1;
        while (i >= 0 && a[r][i] == a[r][s]) {
            pocet++;
            i--;
        }
        i = s + 1;
        while (i < a[r].length && a[r][i] == a[r][s]) {
            pocet++;
            i++;
        }
        return pocet;
    }

    /**
     * Metoda zjisti vrati pocet stejnych hodnot
     * v souvisle slopcove rade, jejiz soucasti je symbol na pozici (r, s)
     *
     * @param a dvourozmerne pole ulozene po radcich }prvni index radkovy)
     * @param r index radku
     * @param s index sloupce
     * @return delka souvisle rady stejnych symbolu
     */
    static int pocetStejnychVSloupci(int[][] a, int r, int s) {
        int pocet = 1;
        // a[r][s]
        int i = r - 1;
        while (i >= 0 && a[i][s] == a[r][s]) {
            pocet++;
            i--;
        }
        i = r + 1;
        while (i < a.length && a[i][s] == a[r][s]) {
            pocet++;
            i++;
        }
        return pocet;
    }

    /**
     * Metoda zjisti vrati pocet stejnych hodnot
     * v souvisle diagonalni rade (v hlavnim diagonalnim smeru), 
     * jejiz soucasti je symbol na pozici (r, s)
     *
     * @param a dvourozmerne pole ulozene po radcich }prvni index radkovy)
     * @param r index radku
     * @param s index sloupce
     * @return delka souvisle rady stejnych symbolu
     */
    static int pocetStejnychVDiag1(int[][] a, int r, int s) {
        int pocet = 1;
        // a[r][s]
        int i = r - 1;
        int j = s - 1;
        while (i >= 0 && j >= 0 && a[i][j] == a[r][s]) {
            pocet++;
            i--;
            j--;
        }
        i = r + 1;
        j = s + 1;
        while (i < a.length && j < a[i].length && a[i][j] == a[r][s]) {
            pocet++;
            i++;
            j++;
        }
        return pocet;
    }

    /**
     * Metoda zjisti vrati pocet stejnych hodnot
     * v souvisle diagonalni rade (ve vedlejsim diagonalnim smeru), 
     * jejiz soucasti je symbol na pozici (r, s)
     *
     * @param a dvourozmerne pole ulozene po radcich }prvni index radkovy)
     * @param r index radku
     * @param s index sloupce
     * @return delka souvisle rady stejnych symbolu
     */
    static int pocetStejnychVDiag2(int[][] a, int r, int s) {
        int pocet = 1;
        // a[r][s]
        int i = r - 1;
        int j = s + 1;
        while (i >= 0 && j < a[i].length && a[i][j] == a[r][s]) {
            pocet++;
            i--;
            j++;
        }
        i = r + 1;
        j = s - 1;
        while (i < a.length && j >= 0 && a[i][j] == a[r][s]) {
            pocet++;
            i++;
            j--;
        }
        return pocet;
    }

    // ...
    /*
    * Testovaci kod pro overeni funkcionalit metod v teto třídě
    *
    */
    private static void vypisMatici(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.format("%4d ", a[i][j]);
            }
            System.out.println("");
        }
    }
}
