/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education.
 * You can use it as you need to learn Java.
 */
package ruta.hry.piskvorky;

/**
 * @author ALG
 */
final class PiskvorkyTools {

    private PiskvorkyTools() {
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
        // correct me if im wrong, ale mam pocit ze asserty by nemely zustavat v production kodu, stejne to nic nedela bez dodatecnych VM argumentu
        // https://docs.oracle.com/javase/8/docs/technotes/guides/language/assert.html#usage
        /*assert a != null;
        assert a.length > 0;
        assert r > 0;
        assert r < a.length;
        assert s > 0;
        assert s < a[0].length;*/

        // tyhle validatory ted uz actually krachnou program kdyz jsou invalid argumenty - ty asserty doted nic nedelaly
        if (a == null || a.length == 0) throw new IllegalArgumentException("Pole je null nebo je prazdne");
        if (r <= 0 || r >= a.length || s <= 0 || s >= a[0].length) throw new IllegalArgumentException();
        // 34 - 40 je duplikovano na 59 - 66 (jenom malickost)
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

    public static int nejvetsiNtice(int[][] a, int r, int s) {
        int nejvetsi = 0;
        int i = pocetStejnychVDiag1(a, r, s);
        if (i > nejvetsi) {
            nejvetsi = i;
        }
        i = pocetStejnychVSloupci(a, r, s);
        if (i > nejvetsi) {
            nejvetsi = i;
        }
        i = pocetStejnychVDiag2(a, r, s);
        if (i > nejvetsi) {
            nejvetsi = i;
        }
        i = pocetStejnychVRadce(a, r, s);
        if (i > nejvetsi) {
            nejvetsi = i;
        }
        return nejvetsi;
    }

    public static void main(String[] args) {
        int[][] mat =
                {
                        {1, 1, 1, 7},
                        {1, 0, 1, 1},
                        {5, 5, 0, 1},
                        {1, 5, 1, 0},
                        {1, 1, 5, 1}
                };
        System.out.println("");
        System.out.println("Puvodni matice");
        vypisMatici(mat);
        int[][] pocty = new int[mat.length][mat[0].length];
        for (int i = 0; i < pocty.length; i++) {
            for (int j = 0; j < pocty[i].length; j++) {
                pocty[i][j] = pocetStejnychVRadce(mat, i, j);
            }
        }
        System.out.println("");
        System.out.println("Pocty stejnych v radce");
        vypisMatici(pocty);

        for (int i = 0; i < pocty.length; i++) {
            for (int j = 0; j < pocty[i].length; j++) {
                pocty[i][j] = pocetStejnychVSloupci(mat, i, j);
            }
        }
        System.out.println("");
        System.out.println("Pocty stejnych v sloupci");
        vypisMatici(pocty);

        for (int i = 0; i < pocty.length; i++) {
            for (int j = 0; j < pocty[i].length; j++) {
                pocty[i][j] = pocetStejnychVDiag1(mat, i, j);
            }
        }
        System.out.println("");
        System.out.println("Pocty stejnych v diagonalnim smeru 1");
        vypisMatici(pocty);

        for (int i = 0; i < pocty.length; i++) {
            for (int j = 0; j < pocty[i].length; j++) {
                pocty[i][j] = pocetStejnychVDiag2(mat, i, j);
            }
        }
        System.out.println("");
        System.out.println("Pocty stejnych v diagonalnim smeru 2");
        vypisMatici(pocty);
    }
}
