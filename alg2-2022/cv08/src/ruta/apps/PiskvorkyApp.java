/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education.
 * You can use it as you need to learn Java.
 */

package ruta.apps;

import apps.drawutils.StdDraw;
import ruta.hry.piskvorky.Piskvorky;
import ruta.hry.piskvorky.PiskvorkyInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author ALG
 */
public class PiskvorkyApp {

    private static final Scanner sc = new Scanner(System.in);
    private static int radek = PiskvorkyInterface.MIN_ROZMER;
    private static int sloupec = PiskvorkyInterface.MIN_ROZMER;
    private static Piskvorky hra;
    private static double velikost_policka_Y;
    private static double velikost_policka_X;
    private static double velikost_mensiho_policka;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean konecProgramu;
        do {
            vypisMenu();
            int volba = nactiVolbu();
            konecProgramu = obsluzVolbu(volba);
        } while (!konecProgramu);
        System.out.println("Koncim ...");
    }

    private static void vypisMenu() {
        System.out.println("");
        System.out.println("Hlavni menu programu");
        System.out.println("1. Hrat hru Piskvorky");
        System.out.println("2. Zmena velikosti hraci plochy");
        System.out.println("3. Zmena symbolu pouzitych pro jednotlive hrace");
        System.out.println("4. Zmena barevneho nastaveni");
        System.out.println("0. Konec programu");
    }

    private static int nactiVolbu() {
        int volba;
        try {
            volba = sc.nextInt();
        } catch (InputMismatchException ex) {
            volba = -1;
        }
        sc.nextLine();
        return volba;
    }

    private static boolean obsluzVolbu(int volba) {
        switch (volba) {
            case 0:
                return true;
            case 1:
                hratHru();
                break;
            case 2:
                zmenaRozmeru();
                break;
            case 3:
                zmenaSymbolu();
                break;
            case 4:
                zmenaBarev();
                break;
            default:
                System.out.println("Neznama volba");
        }
        return false;
    }

    private static void hratHru() {
        System.out.println("");
        System.out.println("Hra piskvorky");
        System.out.println("Hra je realizovana v samostatnem okne ");
        // vytvoreni objektu hry
        hra = PiskvorkyInterface.getInstance(radek, sloupec);
        StdDraw.clear();
        StdDraw.setCanvasSize(900, 900);
        velikost_policka_X = (double) 1 / (double) radek;
        velikost_policka_Y = (double) 1 / (double) sloupec;
        velikost_mensiho_policka = Math.min(velikost_policka_X, velikost_policka_Y);

        for (double y = velikost_policka_Y; y < 1; y += velikost_policka_Y) {
            for (double x = velikost_policka_X; x < 1; x += velikost_policka_X) {
                StdDraw.rectangle(x, y, velikost_policka_X, velikost_policka_Y);
            }
        }
        int Y, X;
        boolean b;
        while (!hra.jeKonecHry()) {
            b = StdDraw.isMousePressed();
            // z tohodle ifu jsem mega zmatenej - b && !b ??
            if (b & !StdDraw.isMousePressed()) {
                b = false;
                Y = prevod_na_policko(StdDraw.mouseY(), velikost_policka_Y);
                X = prevod_na_policko(StdDraw.mouseX(), velikost_policka_X);
                if (hra.getSymbol(X, Y) == 0) {
                    hra.checkVyhra(hra.polozDalsiSymbol(X, Y));
                    nakresli_symbol(X, Y);
                    hra.dalsiHrac();
                }
            }
        }

    }

    private static void nakresli_symbol(int X, int Y) {
        if (hra.getHrajeHrac() == 1) {
            StdDraw.circle(prevod_na_souradnice(X, velikost_policka_X), prevod_na_souradnice(Y, velikost_policka_Y), velikost_mensiho_policka / 2);
        } else if (hra.getHrajeHrac() == 2) {
            StdDraw.line(prevod_na_souradnice(X, velikost_policka_X) + velikost_policka_X / 4, prevod_na_souradnice(Y, velikost_policka_Y) + velikost_policka_Y / 4, prevod_na_souradnice(X, velikost_policka_X) - velikost_policka_X / 4, prevod_na_souradnice(Y, velikost_policka_Y) - velikost_policka_Y / 4);
            StdDraw.line(prevod_na_souradnice(X, velikost_policka_X) + velikost_policka_X / 4, prevod_na_souradnice(Y, velikost_policka_Y) - velikost_policka_Y / 4, prevod_na_souradnice(X, velikost_policka_X) - velikost_policka_X / 4, prevod_na_souradnice(Y, velikost_policka_Y) + velikost_policka_Y / 4);
        }
    }

    private static double prevod_na_souradnice(int X, double velikost_policka) {
        return velikost_policka / 2 + X * velikost_policka;
    }

    private static int prevod_na_policko(double X, double velikost_policka) {
        return (int) (X / (velikost_policka));
    }

    private static void zmenaRozmeru() {
        System.out.println("");
        System.out.println("Nastaveni velikosti hraciho pole");
        System.out.println("Počet řádků: " + radek);
        System.out.println("Zadejte nový počet řádků");
        int r = sc.nextInt();
        if (r >= PiskvorkyInterface.MIN_ROZMER & r <= PiskvorkyInterface.MAX_ROZMER) {
            radek = r;
        } else {
            System.out.println("zadané číslo je mimo limit");
        }
        System.out.println("Počet sloupců: " + sloupec);
        System.out.println("Zadejte nový počet sloupců");
        int s = sc.nextInt();
        if (s >= PiskvorkyInterface.MIN_ROZMER & s <= PiskvorkyInterface.MAX_ROZMER) {
            sloupec = s;
        } else {
            System.out.println("zadané číslo je mimo limit");
        }

        // vypis aktualnich hodnot
        // nacteni novych hodnot
        // provedeni zmeny pouze v pripade platnych hodnot apod.  
    }

    private static void zmenaSymbolu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void zmenaBarev() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
