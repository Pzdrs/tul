package cv10.bohac;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayTools {
    public static void main(String[] args) {
        double[] array = new double[]{1, 2, 3, 4, 5};
    }

    public static double sum(double[] array) {
        double sum = 0;
        for (double v : array) {
            sum += v;
        }
        return sum;
    }

    public static double sum(double[] array, int n) {
        if (n > array.length) return -1;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
        }
        return sum;
    }

    public static void reverse(double[] array) {
        double[] clone = array.clone();
        for (int i = array.length - 1; i >= 0; i--)
            array[array.length - 1 - i] = clone[i];
    }

    public static double[] getReversed(double[] array) {
        double[] reversed = new double[array.length];
        for (int i = array.length - 1; i >= 0; i--)
            reversed[array.length - 1 - i] = array[i];
        return reversed;
    }

    public static double max(double[] array, int n) {
        if (n > array.length) return -1;
        double max = 0;
        double[] temp = Arrays.copyOf(array, n);
        for (double v : temp) {
            if (v > max) max = v;
        }
        return max;
    }

    public static boolean isAlternating(double[] array) {
        for (int i = 0; i < array.length; i++)
            if (i > 0 && Math.signum(array[i] * array[i - 1]) != -1) return false;
        return true;
    }

    public static boolean isAscending(double[] array, int n) {
        if (n > array.length) return false;
        for (int i = 0; i < n; i++)
            if (i > 0 && array[i - 1] > array[i]) return false;
        return true;
    }

    public static int occurs(double[] array, int n, double number) {
        if (n > array.length) return -1;
        Map<Double, Integer> occurrences = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (occurrences.containsKey(array[i])) {
                occurrences.put(array[i], occurrences.get(array[i]) + 1);
            } else occurrences.put(array[i], 1);
        }
        return occurrences.getOrDefault(number, 0);
    }

    public static int firstMax(double[] array) {
        double max = max(array, array.length);
        for (int i = 0; i < array.length; i++) {
            if (array[i] == max) return i;
        }
        return -1;
    }

    public static int lastMax(double[] array) {
        double max = max(array, array.length);
        int maxCount = occurs(array, array.length, max);
        int goneThrough = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == max) goneThrough++;
            if (goneThrough == maxCount) return i;
        }
        return -1;
    }
}
