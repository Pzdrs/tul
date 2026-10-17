package bohac.util;

import bohac.apps.PolynomialApp;
import bohac.polynomial.Polynomial;

import java.util.AbstractMap;

public class Utils {
    private Utils() {
        throw new AssertionError();
    }

    public static Polynomial getPolynomial(String message) {
        return PolynomialApp.polynomials.get(Terminal.promptNumericInt(message, new AbstractMap.SimpleEntry<>(1, PolynomialApp.polynomials.size())) - 1);
    }

    public static Polynomial createPolynomial() {
        double[] coefficients = new double[Terminal.promptNumericInt("How many coefficients?: ", new AbstractMap.SimpleEntry<>(1, Integer.MAX_VALUE))];
        for (int i = 0; i < coefficients.length; i++) {
            coefficients[i] = Terminal.promptNumericDouble(String.format("Coefficient of the term x^%d: ", coefficients.length - 1 - i));
        }
        return Polynomial.of(coefficients);
    }

    /**
     * Check whether a value is in range of two bounds
     *
     * @param value The tested value
     * @param low   Low bound
     * @param high  High bound
     * @return Is the value in range
     */
    public static boolean inRange(int value, int low, int high) {
        return value >= low && value <= high;
    }
}
