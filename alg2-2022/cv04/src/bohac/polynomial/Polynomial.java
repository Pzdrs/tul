package bohac.polynomial;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Polynomial {
    private final double[] coefficients;

    private Polynomial(double[] coefficients) {
        this.coefficients = coefficients.clone();
    }

    /**
     * The main factory method of the Polynomial object
     *
     * @param coefficients the polynomial coefficients
     * @return a new instance of a Polynomial. If the data passed in is null, null is returned.
     * If only zeros or nothing is passed in, a zero polynomial is returned
     */
    public static Polynomial of(double... coefficients) {
        if (coefficients == null) return null;
        if (coefficients.length == 0 || zerosOnly(coefficients)) return createConstantPolynomial(0);
        return new Polynomial(removeLeadingZeros(coefficients));
    }

    /**
     * Creates a polynomial with a single term
     *
     * @param coefficient the single term
     * @param exponent    the exponent of the single term, thus the degree of the polynomial
     * @return a monomial with a single term - <i>coefficient^exponent</i>
     */
    public static Polynomial createMonomial(double coefficient, int exponent) {
        if (coefficient == 0) return createConstantPolynomial(0);
        double[] coefficients = new double[exponent + 1];
        for (int i = 0; i < coefficients.length; i++)
            coefficients[i] = i == 0 ? coefficient : 0;
        return new Polynomial(coefficients);
    }

    /**
     * Creates a constant polynomial - a single term with the exponent of 0
     *
     * @param coefficient the constant number
     * @return constant polynomial - just a number
     */
    public static Polynomial createConstantPolynomial(double coefficient) {
        return new Polynomial(new double[]{coefficient});
    }

    /**
     * @return the array of polynomial coefficients
     */
    public double[] getCoefficients() {
        return coefficients.clone();
    }

    /**
     * Calculates the degree of this polynomial - it's just the amount of coefficients - 1
     *
     * @return the polynomial degree
     */
    public int getDegree() {
        return coefficients.length - 1;
    }

    /**
     * The derivative of the polynomial - if there are no variables left after taking the derivative, null is returned
     *
     * @return Derivative of the polynomial
     */
    public Polynomial getDerivative() {
        List<Double> coefficients = new ArrayList<>();
        for (int i = 0; i < this.coefficients.length - 1; i++)
            coefficients.add(this.coefficients[i] * (this.coefficients.length - i - 1));
        if (coefficients.isEmpty()) return null;
        return new Polynomial(coefficients.stream().mapToDouble(Double::doubleValue).toArray());
    }

    /**
     * Gets the coefficient at a given index
     *
     * @param index index of the coefficient
     * @return coefficient at a given index
     * @throws ArrayIndexOutOfBoundsException if index < 0 or index >= length of the coefficient array
     */
    public double getCoefficient(int index) {
        if (index < 0 || index >= coefficients.length) throw new ArrayIndexOutOfBoundsException(index);
        return coefficients[index];
    }

    /**
     * Calculates the value of the polynomial using the given x value
     *
     * @param x what value to substitute into the polynomial
     * @return the value of the polynomial with the substituted x value
     */
    public double getValue(double x) {
        double value = 0;
        for (int i = 0; i < coefficients.length; i++) {
            value += coefficients[i] * Math.pow(x, coefficients.length - i - 1);
        }
        return value;
    }

    @Override
    public String toString() {
        if (coefficients.length == 1 && coefficients[0] == 0) return "0";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < coefficients.length; i++) {
            int exponent = coefficients.length - i - 1;
            double coefficient = coefficients[i];
            if (coefficient == 0) continue;
            String monomial = formatMonomial(i, coefficient, exponent);
            switch (exponent) {
                case 0 -> builder.append(String.format("%s ", monomial));
                case 1 -> builder.append(String.format("%sx ", monomial));
                default -> builder.append(String.format("%sx^%d ", monomial, exponent));
            }
        }
        return builder.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polynomial that)) return false;
        return Arrays.equals(coefficients, that.coefficients);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coefficients);
    }

    /**
     * Adds two polynomials together
     *
     * @param polynomial the polynomial to be added to this one
     * @return the new polynomial
     */
    public Polynomial add(Polynomial polynomial) {
        int max = Math.max(coefficients.length, polynomial.coefficients.length);
        double[] a = padStart(coefficients, max);
        double[] b = padStart(polynomial.coefficients, max);
        double[] result = new double[max];
        for (int i = 0; i < result.length; i++) {
            result[i] = Objects.requireNonNull(a)[i] + Objects.requireNonNull(b)[i];
        }
        return new Polynomial(result);
    }

    /**
     * Subtracts two polynomials
     *
     * @param polynomial the polynomial to be subtracted from this one
     * @return the new polynomial
     */
    public Polynomial subtract(Polynomial polynomial) {
        int max = Math.max(coefficients.length, polynomial.coefficients.length);
        double[] a = padStart(coefficients, max);
        double[] b = padStart(polynomial.coefficients, max);
        double[] result = new double[max];
        for (int i = 0; i < result.length; i++) {
            result[i] = Objects.requireNonNull(a)[i] - Objects.requireNonNull(b)[i];
        }
        return new Polynomial(result);
    }

    /**
     * Multiplies the polynomial by a number
     *
     * @param multiple the constant by which the polynomial is to be multiplied
     * @return the new polynomial
     */
    public Polynomial multiply(double multiple) {
        return of(Arrays.stream(getCoefficients()).map(d -> multiple * d).toArray());
    }

    /**
     * Multiplies the polynomial by another
     *
     * @param polynomial polynomial to multiply by
     * @return the product of these two polynomials
     */
    public Polynomial multiply(Polynomial polynomial) {
        double[] coefficients = new double[getDegree() + polynomial.getDegree() + 1];
        for (int i = 0; i < getDegree() + 1; i++) {
            for (int j = 0; j < polynomial.getDegree() + 1; j++) {
                coefficients[i + j] += getCoefficient(i) * polynomial.getCoefficient(j);
            }
        }
        return Polynomial.of(coefficients);
    }

    private static boolean zerosOnly(double[] doubles) {
        for (double el : doubles) if (el != 0) return false;
        return true;
    }

    private static double[] removeLeadingZeros(double[] doubles) {
        int start = 0;
        for (double el : doubles) {
            if (el != 0) break;
            start++;
        }
        return Arrays.copyOfRange(doubles, start, doubles.length);
    }

    private String formatMonomial(int i, double coefficient, int exponent) {
        StringBuilder builder = new StringBuilder();
        if (Math.abs(coefficient) == 1) {
            if (i == 0) builder.append(coefficient < 0 ? sign(coefficient) : "");
            else builder.append(sign(coefficient)).append(" ");
            builder.append(exponent > 0 ? "" : Math.abs(coefficient));
        } else builder.append(new DecimalFormat(i == 0 ? "#.##;-#" : "+ #.##;- #").format(coefficient));
        return builder.toString();
    }

    private String sign(double d) {
        return Math.signum(d) > 0 ? "+" : "-";
    }

    private double[] padStart(double[] array, int newLength) {
        if (newLength < array.length) return null;
        double[] newArray = new double[newLength];
        int j = 0;
        for (int i = 0; i < newLength; i++) {
            if (i >= newLength - array.length) {
                newArray[i] = array[j++];
            }
        }
        return newArray;
    }
}
