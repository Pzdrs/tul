package cv11.bohac;

import java.util.Objects;
import java.util.Scanner;

public class TestPrevodMatice {
    public static void main(String[] args) {
        double[][] matrix = MatrixTools.loadDoubleMatrix(new Scanner(System.in));
        System.out.println("loaded matrix: ");
        MatrixTools.print(matrix);
        System.out.println();
        MatrixTools.print(Objects.requireNonNull(MatrixTools.normalize(matrix)));
    }
}
