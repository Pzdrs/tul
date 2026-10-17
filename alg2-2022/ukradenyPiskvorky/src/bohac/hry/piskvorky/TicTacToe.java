/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education.
 * You can use it as you need to learn Java.
 */

package bohac.hry.piskvorky;

import bohac.hry.piskvorky.exceptions.TicTacToeException;

/**
 * @author ALG
 */
class TicTacToe implements TicTacToeInterface {
    private final int rows;
    private final int columns;
    private final int[][] grid;
    private boolean gameFinished;
    private int player;
    private int winner;
    private int maxN;

    public TicTacToe(int pr, int ps) {
        this.rows = pr;
        this.columns = ps;
        this.player = 1;
        this.winner = 0;
        this.grid = new int[pr][ps];
        this.gameFinished = false;
    }

    @Override
    public int placeNextSymbol(int row, int col) {
        if (gameFinished) throw new TicTacToeException("Game has finished already", 104);
        if (row < 0 || col < 0 || row > grid.length || col > grid[0].length) {
            throw new TicTacToeException(String.format("Out of bounds for %sx%col", row, col), 101);
        }
        if (grid[row][col] != 0) throw new TicTacToeException("Policko je obsazene", 103);
        if (grid[row][col] == 0 && player == 1) {
            grid[row][col] = 1;
            player = 2;
        }

        if (grid[row][col] == 0 && player == 2) {
            grid[row][col] = 2;
            player = 1;
        }

        maxN = Math.max(Math.max(TicTacToeTools.pocetStejnychVDiag1(grid, row, col), TicTacToeTools.pocetStejnychVDiag2(grid, row, col)),
                Math.max(TicTacToeTools.pocetStejnychVRadce(grid, row, col), TicTacToeTools.pocetStejnychVSloupci(grid, row, col)));

        if (maxN >= 5) {
            gameFinished = true;
            if (player == 1) {
                winner = 2;
            }
            if (player == 2) {
                winner = 1;
            }
        }
        if (maxN < 5) {
            boolean isDraw = false;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (grid[i][j] == 0) {
                        isDraw = false;
                        break;
                    } else {
                        isDraw = true;
                    }
                }
                if (!isDraw) break;
            }
            if (isDraw) {
                System.out.println("It's a TIE!");
            }
        }
        return maxN;
    }

    @Override
    public int getSymbol(int row, int col) {
        if (row > grid.length || col > grid[0].length) {
            throw new IndexOutOfBoundsException(String.format("Out of bounds for %sx%col", row, col));
        }
        return grid[row][col];
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public boolean isGameFinished() {
        return gameFinished;
    }

    @Override
    public int getPlayer() {
        if (gameFinished) return 0;
        else return player;
    }

    @Override
    public int getWinner() {
        if (gameFinished) return winner;
        else return 0;
    }
}
