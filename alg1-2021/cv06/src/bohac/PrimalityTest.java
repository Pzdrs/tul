package bohac;

import java.util.Scanner;

public class PrimalityTest {
    public static void main(String[] args) {
        while (true) {
            System.out.print("number to test: ");
            if (isPrime(new Scanner(System.in).nextInt())) {
                System.out.println("is prime");
            } else System.out.println("isnt prime");
        }
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= n; i++) if (n % i == 0 && i != n) return false;
        return true;
    }
}
