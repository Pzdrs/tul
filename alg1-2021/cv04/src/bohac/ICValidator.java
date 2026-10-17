package bohac;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ICValidator {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.print("zadej ic: ");
            int input = scanner.nextInt();
            System.out.printf("IC %d " + (icValid(input) ? "je platne" : "je neplatne"), input);
        } catch (InputMismatchException exception) {
            System.out.println("neni cislo more");
        }
    }

    public static boolean icValid(int input) {
        int[] numbers = new int[8];
        numbers[0] = input / 10000000 % 10;
        numbers[1] = input / 1000000 % 10;
        numbers[2] = input / 100000 % 10;
        numbers[3] = input / 10000 % 10;
        numbers[4] = input / 1000 % 10;
        numbers[5] = input / 100 % 10;
        numbers[6] = input / 10 % 10;
        numbers[7] = input % 10;

        int total = numbers[0] * 8 + numbers[1] * 7 + numbers[2] * 6 + numbers[3] * 5 + numbers[4] * 4 + numbers[5] * 3 + numbers[6] * 2;
        int remainder = total % 11;
        return (remainder == 0 && numbers[7] == 1) ||
                (remainder == 1 && numbers[7] == 0) ||
                (11 - remainder == numbers[7]);
    }
}
