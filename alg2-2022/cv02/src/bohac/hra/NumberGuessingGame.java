package bohac.hra;

import bohac.Utils;

import java.util.AbstractMap;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    static final Scanner SCANNER = new Scanner(System.in);
    static final int RANDOM_MAX = 100;
    static Menu menu = new Menu(
            new Menu.MenuItem("You guess", NumberGuessingGame::userGuessing),
            new Menu.MenuItem("The computer guesses", NumberGuessingGame::computerGuessing),
            new Menu.MenuItem("Clear the terminal", Menu.CLEAR),
            new Menu.MenuItem("Exit", Menu.EXIT)
    );

    public static void main(String[] args) {
        // Scanner instance the Utils class is suppose to use (additional abstraction)
        Utils.scanner = SCANNER;
        menu.prompt();
    }

    public static void userGuessing() {
        int guess, tries = 0, random = new Random().nextInt(RANDOM_MAX);
        do {
            guess = Utils.promptNumericInt("Enter your guess: ");
            tries++;
            if (guess != random)
                System.out.println(guess > random ? "Your guess is larger" : "Your guess is smaller");
        } while (guess != random);
        System.out.printf("Correct! It took you %d %s to guess the number.\n", tries, tries > 1 ? "tries" : "try");
    }

    public static void computerGuessing() {
        int guess, tries = 0,
                random = Utils.promptNumericInt(String.format("What number is the computer guessing (0-%d)?: ", RANDOM_MAX), new AbstractMap.SimpleEntry<>(0, RANDOM_MAX)),
                low = 0, high = RANDOM_MAX;
        do {
            guess = (low + high) / 2;
            tries++;
            String guessResult;
            if (guess > random) {
                guessResult = "too large";
                high = guess;
            } else if (guess < random) {
                guessResult = "too small";
                low = guess;
            } else guessResult = "just right";
            System.out.printf("The computer's guess: %d - %s\n", guess, guessResult);
        } while (guess != random);
        System.out.printf("Correct! It took the computer %d %s to guess the number.\n", tries, tries > 1 ? "tries" : "try");
    }
}
