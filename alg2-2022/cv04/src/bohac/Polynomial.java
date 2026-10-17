package bohac;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.Arrays;

public class Polynomial {
    private final double[] coefficients;

    private Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public static Polynomial createPolynomial(double... a) {
        return new Polynomial(null);
    }

    public int getDegree() {
        return coefficients.length -1;
    }

    public double getKoeficient(int index) {
        return 0;
    }

    public double getHodnota(double x) {
        return 0;
    }

    public Polynomial getDerivation() {
        return null;
    }

    @Override
    public String toString() {
        return "Polynom{" +
                "koef=" + Arrays.toString(coefficients) +
                '}';
    }
}
