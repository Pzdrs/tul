package cv11.bohac;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixTools {
    public static double[][] add(double[][] a, double[][] b) {
        if (!isSquare(a) || !isSquare(b)) return null;
        double[][] c = new double[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }

    public static double[][] multiply(double[][] a, double[][] b) {
        if (!multiplicationReady(a, b)) return null;
        double[][] c = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            double[] row = a[i];
            for (int j = 0; j < transpose(b).length; j++) {
                double[] col = transpose(b)[j];
                double temp = 0;
                for (int i1 = 0; i1 < row.length; i1++) {
                    temp += row[i1] * col[i1];
                }
                c[i][j] = temp;
            }
        }
        return c;
    }

    // v zivote jsem o tom neslysel tak to je dost mozno spatne idc
    public static double[][] normalize(double[][] a) {
        if (!isMatrix(a)) return null;
        double[][] c = new double[a.length][a[0].length];
        double squareOfSum = squareOfSum(a);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                c[i][j] = a[i][j] / squareOfSum;
            }
        }
        return c;
    }

    public static boolean isSymmetric(int[][] a) {
        return Arrays.deepEquals(a, transpose(a));
    }

    public static boolean hasSameSums(int[][] a) {
        if (!isSquare(a)) return false;
        int[] rowSums = new int[a.length];
        int[] colSums = new int[a[0].length];
        int[] diagonalSums = new int[2];

        // sums of rows
        for (int i = 0; i < a.length; i++) {
            int[] row = a[i];
            int sum = 0;
            for (int el : row) {
                sum += el;
            }
            rowSums[i] = sum;
        }

        // sums of columns
        for (int i = 0; i < a.length; i++) {
            int[] row = transpose(a)[i];
            int sum = 0;
            for (int el : row) {
                sum += el;
            }
            colSums[i] = sum;
        }

        // sums of diagonals
        int diagonalSum = 0;

        for (int i = 0; i < a.length; i++) diagonalSum += a[i][i];
        diagonalSums[0] = diagonalSum;


        diagonalSum = 0;

        for (int i = 0; i < a.length; i++) diagonalSum += a[i][a.length - 1 - i];
        diagonalSums[1] = diagonalSum;

        return Arrays.stream(rowSums).distinct().count() == 1 &&
                Arrays.stream(colSums).distinct().count() == 1 &&
                Arrays.stream(diagonalSums).distinct().count() == 1;
    }

    private static double squareOfSum(double[][] a) {
        double sum = 0;
        for (double[] row : a)
            for (double el : row) sum += el;
        return Math.sqrt(sum);
    }

    private static boolean areSameSize(double[][] a, double[][] b) {
        if (!isMatrix(a) || !isMatrix(b)) return false;
        return Arrays.equals(getMatrixDimensions(a), getMatrixDimensions(b));
    }

    private static boolean isMatrix(double[][] a) {
        int columns = a[0].length;
        for (double[] row : a) if (columns != row.length) return false;
        return true;
    }

    private static boolean isMatrix(int[][] a) {
        int columns = a[0].length;
        for (int[] row : a) if (columns != row.length) return false;
        return true;
    }

    private static int[] getMatrixDimensions(double[][] a) {
        if (!isMatrix(a)) return new int[]{0, 0};
        return new int[]{a.length, a[0].length};
    }

    private static int[] getMatrixDimensions(int[][] a) {
        if (!isMatrix(a)) return new int[]{0, 0};
        return new int[]{a.length, a[0].length};
    }

    private static boolean isSquare(double[][] a) {
        return isMatrix(a) && getMatrixDimensions(a)[0] == getMatrixDimensions(a)[1];
    }

    private static boolean isSquare(int[][] a) {
        return isMatrix(a) && getMatrixDimensions(a)[0] == getMatrixDimensions(a)[1];
    }

    private static boolean multiplicationReady(double[][] a, double[][] b) {
        return getMatrixDimensions(a)[1] == getMatrixDimensions(b)[0];
    }

    private static double[][] transpose(double[][] a) {
        int m = a.length;
        int n = a[0].length;
        double[][] transposedMatrix = new double[n][m];
        for (int x = 0; x < n; x++)
            for (int y = 0; y < m; y++)
                transposedMatrix[x][y] = a[y][x];
        return transposedMatrix;
    }

    private static int[][] transpose(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] transposedMatrix = new int[n][m];
        for (int x = 0; x < n; x++)
            for (int y = 0; y < m; y++)
                transposedMatrix[x][y] = a[y][x];
        return transposedMatrix;
    }

    public static void print(int[][] a) {
        for (int[] row : a) {
            for (int el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }

    public static void print(double[][] a) {
        for (double[] row : a) {
            for (double el : row) {
                System.out.print(new DecimalFormat("#.##").format(el) + " ");
            }
            System.out.println();
        }
    }

    public static int[][] loadIntegerMatrix(Scanner scanner) {
        System.out.print("rows: ");
        int rows = scanner.nextInt();
        System.out.print("columns: ");
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];

        System.out.println("zadavej prvky matice, jedeme po radcich z leva do prava");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("next element: ");
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    public static double[][] loadDoubleMatrix(Scanner scanner) {
        System.out.print("rows: ");
        int rows = scanner.nextInt();
        System.out.print("columns: ");
        int cols = scanner.nextInt();

        double[][] matrix = new double[rows][cols];

        System.out.println("zadavej prvky matice, jedeme po radcich z leva do prava");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("next element: ");
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }
}
