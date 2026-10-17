package cv09.bohac;

public class MathTools {
    private static double fact(double x) {
        return (x > 1 ? x * fact(x - 1) : 1);
    }

    private static double pow(double a, int n) {
        double pow = 1;
        for (int i = 0; i < n; i++) pow *= a;
        return pow;
    }

    public static double sin(double x, double eps) {
        double result = 0;
        int i = 0;
        double term;
        while (Math.abs((term = (pow(x, 2 * i + 1) / fact(2 * i + 1)) * pow(-1, i))) > eps) {
            result += term;
            i++;
        }
        return result;
    }

    public static double cos(double x, double eps) {
        double result = 0;
        int i = 0;
        double term;
        while (Math.abs((term = (pow(x, 2 * i) / fact(2 * i)) * pow(-1, i))) > eps) {
            result += term;
            i++;
        }
        return result;
    }

    public static double ln(double x, double eps) {
        double result = 0;
        int i = 0;
        double term;
        while (Math.abs(term = pow(x - 1, 2 * i + 1) / ((2 * i + 1) * pow(x + 1, 2 * i + 1))) > eps) {
            result += term;
            i++;
        }
        return result * 2;
    }

    public static double sqrt(double x, double eps) {
        double temp = x;
        double root;
        for (; ; ) {
            root = 0.5 * (temp + (x / temp));
            if (Math.abs(root - temp) < eps)
                break;
            temp = root;
        }

        return root;
    }
}
