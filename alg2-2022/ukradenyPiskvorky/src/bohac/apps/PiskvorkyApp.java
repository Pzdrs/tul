/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education.
 * You can use it as you need to learn Java.
 */

package bohac.apps;

import bohac.apps.drawutils.StdDraw;

import bohac.hry.piskvorky.TicTacToeInterface;
import bohac.hry.piskvorky.exceptions.TicTacToeException;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author ALG
 */
public class PiskvorkyApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final Color[] COLORS = {Color.white, Color.black, Color.gray, Color.blue, Color.red, Color.yellow, Color.green};
    public static char symbol1 = 'x';
    public static char symbol2 = 'O';
    private static int backgroundColor = 2;
    private static int gridColor = 1;
    private static int rows = 5;
    private static int columns = 5;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean exit;
        do {
            printMenu();
            System.out.print(" > ");
            int choice = promptChoice();
            exit = runChoice(choice);
        } while (!exit);
    }

    private static void printMenu() {
        System.out.println("");
        System.out.println(">>> TIC TAC TOE <<<");
        System.out.println("=".repeat(25));
        System.out.println("[1] New game");
        System.out.println("[2] Change grid size");
        System.out.println("[3] Change symbols");
        System.out.println("[4] Change color references");
        System.out.println("[0] Exit");
    }

    private static int promptChoice() {
        int choice;
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException ex) {
            choice = -1;
        }
        sc.nextLine();
        return choice;
    }

    private static boolean runChoice(int choice) {
        switch (choice) {
            case 0:
                return true;
            case 1:
                createGame();
                break;
            case 2:
                changeSize();
                break;
            case 3:
                changeSymbol();
                break;
            case 4:
                changeColor();
                break;
            default:
                System.out.printf("Unknown choice: %d", choice);
        }
        return false;

    }

    private static void createGame() {
        TicTacToeInterface game = TicTacToeInterface.getInstance(rows, columns);

        StdDraw.setPenColor(COLORS[gridColor]);
        StdDraw.clear(COLORS[backgroundColor]);
        StdDraw.setXscale(0, game.getRows());
        StdDraw.setYscale(0, game.getColumns());
        for (int x = 0; x < game.getRows(); x++) {
            for (int y = 0; y < game.getColumns(); y++) {
                StdDraw.square(x, y, 1);
            }
        }
        while (game.getPlayer() != 0) {
            int x = (int) StdDraw.mouseX();
            int y = (int) StdDraw.mouseY();

            if (StdDraw.isMousePressed()) {
                if (game.getSymbol(x, y) == 0 && game.getPlayer() == 1) {
                    StdDraw.text(x + TicTacToeInterface.OFFSET, y + TicTacToeInterface.OFFSET, Character.toString(PiskvorkyApp.symbol1));
                    game.placeNextSymbol(x, y);

                }
                if (game.getSymbol(x, y) == 0 && game.getPlayer() == 2) {
                    StdDraw.text(x + TicTacToeInterface.OFFSET, y + TicTacToeInterface.OFFSET, Character.toString(PiskvorkyApp.symbol2));
                    game.placeNextSymbol(x, y);
                }
            }
            if (game.isGameFinished()) {
                System.out.printf("Player %d has won the game!!", game.getWinner());
            }
        }
    }

    private static void changeSize() {
        System.out.println("");
        System.out.println("Change grid size");
        System.out.println("");

        System.out.printf("Current rows: %d", rows);
        System.out.printf("Current columns: %d", columns);

        System.out.print("New rows amount: ");
        columns = sc.nextInt();
        if (columns < TicTacToeInterface.MIN_SIZE) {
            throw new TicTacToeException("Too few columns", 101, "" + columns);
        }
        if (columns > TicTacToeInterface.MAX_SIZE) {
            throw new TicTacToeException("Too much columns", 101, "" + columns);
        }
        System.out.print("Nový počet sloupců ");
        rows = sc.nextInt();
        if (rows < TicTacToeInterface.MIN_SIZE) {
            throw new TicTacToeException("Too few rows", 101, "" + rows);
        }
        if (rows > TicTacToeInterface.MAX_SIZE) {
            throw new TicTacToeException("Too many rows", 101, "" + rows);
        }
    }

    private static void changeSymbol() {
        System.out.println("Player 1 symbol: " + symbol1);
        System.out.println("Player 2 symbol: " + symbol2);

        System.out.print("New P1 symbol: ");
        symbol1 = sc.next().charAt(0);

        System.out.print("New p2 symbol: ");
        symbol2 = sc.next().charAt(0);
    }

    private static void changeColor() {
        System.out.println("[0] - bila\n[1] - cerna\n[2] - seda\n[3] - modra\n[4] - cervena\n[5] - zluta\n[6] - zelena");
        System.out.println("Background color: " + backgroundColor);
        System.out.println("Grid color: " + gridColor);
        System.out.print("New background color: ");
        backgroundColor = sc.nextInt();
        if (backgroundColor > COLORS.length || backgroundColor < 0) {
            throw new IllegalArgumentException("Unknown color");
        }

        System.out.print("New grid color: ");
        gridColor = sc.nextInt();
        if (gridColor > COLORS.length || backgroundColor < 0) {
            throw new IllegalArgumentException("Unknown color");
        }

    }
}
