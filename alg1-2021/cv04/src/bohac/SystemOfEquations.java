package bohac;

public class SystemOfEquations {
    public static void main(String[] args) {
        double x1 = 2, y1 = -3, c1 = 5;
        double x2 = 4, y2 = -6, c2 = 10;

        if (x1 / x2 == y1 / y2) {
            if (c1 * x2 / x1 == c2) {
                System.out.println("nekonecne mnoho reseni");
            } else {
                System.out.println("nema reseni");
            }
        } else {
            double y = (x2 * c1 - x1 * c2) / (x2 * y1 - x1 * y2);
            double x = (c1 - y1 * y) / x1;
            System.out.printf("x = %f, y = %f", x, y);
        }
    }
}
