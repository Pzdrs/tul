package bohac;

import java.util.Scanner;

public class HraHadaniCisla {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run;
        do {
            System.out.print("> ");
            run = execute(scanner.next());
        } while (run);
    }

    public static boolean execute(String command) {
        switch (command) {
            case "end":
            case "0":
            case "quit":
            case "exit":
                return false;
            case "game":
                game();
                break;
            default:
                System.out.println("unknown command");
        }
        return true;
    }

    public static void game() {
        int low, high, tries = 0, input;
        System.out.println("zadej rozsah cisel: ");
        low = scanner.nextInt();
        high = scanner.nextInt();
        int target = low + (int) (Math.random() * ((high - low) + 1));
        System.out.println(target);
        do {
            System.out.print("guess: ");
            input = scanner.nextInt();
            tries++;
        } while (input != target);
        System.out.printf("Uhodnul jsi cislo %d, pokusy - %d\n", target, tries);
    }
}
