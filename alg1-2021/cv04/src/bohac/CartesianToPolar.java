package bohac;

import java.util.Scanner;

public class CartesianToPolar {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("zadej smer prevodu (k - do kartezske, p - do polarni): ");
        switch (scanner.nextLine().toLowerCase()) {
            case "k" -> {
                System.out.println("zadej hodnotu r a uhel fi (v radianech): ");
                double r = scanner.nextDouble();
                double fi = scanner.nextDouble();
                System.out.printf("Polar (%.2f, %.2f) -> Cartesian (%.2f, %.4f)", r, fi, r * Math.cos(fi), r * Math.sin(fi));
            }
            case "p" -> {
                System.out.println("zadej hodnotu x a y: ");
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                System.out.printf("Cartesian (%.2f, %.2f) -> Polar (%.2f, %.4f)", x, y, Math.hypot(x, y), Math.atan(y / x));
            }
            default -> System.out.println("neni valid option");
        }
    }
}
