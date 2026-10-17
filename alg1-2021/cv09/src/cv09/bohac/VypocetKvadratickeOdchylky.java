package cv09.bohac;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class VypocetKvadratickeOdchylky {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("pocet hodnot: ");
        int n = scanner.nextInt();
        // random data included, z pripade random dat zakomentovat input loop
        double[] data = new double[n]/*new Random().doubles(n, 1, 100).toArray()*/;

        for (int i = 0; i < n; i++) data[i] = scanner.nextDouble();
        double average = average(data);
        double maxDeviation = maxDeviation(data, average);
        double dispersion = dispersion(data, n, average);
        System.out.printf("pocet mereni: %d, prumer: %.3f, max. odchylka: %.3f, kvadr. odchylka: %.3f", n, average, maxDeviation, dispersion);
    }

    public static double average(double[] data) {
        double sum = 0;
        for (double element : data) {
            sum += element;
        }
        return sum / data.length;
    }

    public static double maxDeviation(double[] data, double average) {
        double maxDeviation = 0;
        for (double element : data) {
            double difference = Math.abs(average - element);
            if (difference > maxDeviation) maxDeviation = difference;
        }
        return maxDeviation;
    }

    public static double dispersion(double[] data, int n, double average) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.pow(data[i] - average, 2);
        }
        return (1 / (double) n) * sum;
    }
}
