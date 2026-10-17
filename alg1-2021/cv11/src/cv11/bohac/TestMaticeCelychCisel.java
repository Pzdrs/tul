package cv11.bohac;

import java.util.Scanner;

public class TestMaticeCelychCisel {
    public static void main(String[] args) {
        int[][] matrix = MatrixTools.loadIntegerMatrix(new Scanner(System.in));
        System.out.println("loaded matrix: ");
        MatrixTools.print(matrix);
        System.out.println();
        System.out.println(MatrixTools.isSymmetric(matrix) ? "matice je symetricka podle hl. diagonaly" : "matice neni symetricka podle hl. diagonaly");
        System.out.println(MatrixTools.hasSameSums(matrix) ? "matice ma stejny soucet radku, sloupcu i hl. diagonal" : "matice nema stejny soucet radku, sloupcu i hl. diagonal");
    }
}
