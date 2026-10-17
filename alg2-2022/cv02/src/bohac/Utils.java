package bohac;

import java.util.AbstractMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Utils {
    public static Scanner scanner;

    /**
     * Prompt the user for and integer
     *
     * @param message Prompt message
     * @param range   The range the integer is suppose to be in
     * @return Validated integer
     */
    public static int promptNumericInt(String message, Map.Entry<Integer, Integer> range) {
        boolean error = true;
        int value = 0;
        do {
            try {
                System.out.print(message);
                value = scanner.nextInt();
                if (!inRange(value, range.getKey(), range.getValue())) {
                    System.out.println("Value out of range");
                    continue;
                }
                error = false;
            } catch (InputMismatchException exception) {
                scanner.next();
                System.out.println("Not a number");
            }
        } while (error);
        scanner.nextLine();
        return value;
    }

    /**
     * Prompt the user for and integer - the whole domain is considered (no upper or lower limits)
     *
     * @param message Prompt message
     * @return Integer
     */
    public static int promptNumericInt(String message) {
        return promptNumericInt(message, new AbstractMap.SimpleEntry<>(Integer.MIN_VALUE, Integer.MAX_VALUE));
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
