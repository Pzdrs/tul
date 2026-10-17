package cv12.bohac;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PiskvorkyTools {
    private PiskvorkyTools() {
    }

    /**
     * Metoda pro zadanou matici a zadanou pozici matice určít počet stejnych
     * hodnot v radku v souvisle rade hodnot )pocet stejnych hodnot, jako je na
     * zadane pozici.
     *
     * @param a      matice celych cisel typu int
     * @param row    radkovy index prvku, od ktereho ma byt urcen povcet stejnych hodnot
     * @param column sloupcovy index prvku, od ktereho ma byt urcen povcet stejnych hodnot
     * @return zjisteny pocet jako hodnota typu int
     */
    public static int pocetStejnychVRadce(int[][] a, int row, int column) {
        int number = a[row][column];
        int[] _row = a[row];
        int amountOfSame = 0;
        for (int el : _row)
            if (el == number) amountOfSame++;
        return amountOfSame;
    }

    public static int pocetStejnychVSloupci(int[][] a, int row, int column) {
        int number = a[row][column];
        int[] _column = getColumn(a, column);
        int amountOfSame = 0;
        for (int el : _column)
            if (el == number) amountOfSame++;
        return amountOfSame;
    }

    public static int pocetStejnychVDiag1(int[][] a, int row, int column) {
        int number = a[row][column];
        int amountOfSame = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i][i] == number) amountOfSame++;
        }
        return amountOfSame;
    }

    public static int pocetStejnychVDiag2(int[][] a, int row, int column) {
        int number = a[row][column];
        int amountOfSame = 0;
        for (int i = column; i >= 0; i--)
            if (a[column - i][i] == number) amountOfSame++;
        return amountOfSame;
    }

    private static int[] getColumn(int[][] matrix, int column) {
        return Arrays.stream(matrix).mapToInt(ints -> ints[column]).toArray();
    }
}
