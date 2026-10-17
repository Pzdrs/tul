/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education.
 * You can use it as you need to learn Java.
 */

package ruta.hry.piskvorky;

/**
 * @author ALG
 */
public class Piskvorky implements PiskvorkyInterface {
    // tady muzem debatovat jestli private nebo public ale je to asi jedno kdyz to pouzivas jenom v tyhle tride
    private static final int BODY_K_VYHRE = 3;
    private final int[][] pole;
    private final int radek, sloupec;
    private boolean konecHry = false;
    private int vyhral, hraje;

    Piskvorky(int pr, int ps) {
        radek = pr;
        sloupec = ps;
        pole = new int[pr][ps];
        hraje = 1;

    }

    // asi nejak oznamit ze nekdo vyhral
    public void checkVyhra(int a) {
        if (a >= BODY_K_VYHRE) {
            vyhral = hraje;
            konecHry = true;
            //return true;
        }
        // else{return false;}
    }

    public void dalsiHrac() {
        if (hraje == 1) {
            hraje = 2;
        } else if (hraje == 2) {
            hraje = 1;
        }
    }

    @Override
    public int polozDalsiSymbol(int r, int s) {
        if (r > radek || s > sloupec) {
            throw new PiskvorkyException("Pozice je mimo rozsah", 102);
        } else if (jeKonecHry()) {
            throw new PiskvorkyException("Nelze provést tah po konci hry", 104);
        } else if (pole[r][s] != 0) {
            throw new PiskvorkyException("zadaná pozice je již obsazena", 103);
        }

        pole[r][s] = getHrajeHrac();
        return PiskvorkyTools.nejvetsiNtice(pole, r, s);
    }

    @Override
    public int getSymbol(int r, int s) {
        if (r > radek || s > sloupec) {
            throw new PiskvorkyException("Pozice je mimo rozsah", 102);
        }
        return pole[r][s];
    }

    @Override
    public int getPocetRadku() {
        return radek;
    }

    @Override
    public int getPocetSloupcu() {
        return sloupec;
    }

    @Override
    public boolean jeKonecHry() {
        return konecHry;
    }

    @Override
    public int getHrajeHrac() {
        return hraje;
    }

    @Override
    public int getCisloVyherce() {
        return vyhral;
    }
}
