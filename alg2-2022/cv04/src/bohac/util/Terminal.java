package bohac.util;

import java.util.AbstractMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Terminal {
    public static final Scanner SCANNER = new Scanner(System.in);

    private Terminal() {
        throw new AssertionError();
    }

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
                value = SCANNER.nextInt();
                if (!Utils.inRange(value, range.getKey(), range.getValue())) {
                    System.out.println("Value out of range");
                    continue;
                }
                error = false;
            } catch (InputMismatchException exception) {
                SCANNER.next();
                System.out.println("Not a number");
            }
        } while (error);
        SCANNER.nextLine();
        return value;
    }

    /**
     * Prompt the user for a double
     *
     * @param message Prompt message
     * @return Validated integer
     */
    public static double promptNumericDouble(String message) {
        boolean error = true;
        double value = 0;
        do {
            try {
                System.out.print(message);
                value = SCANNER.nextDouble();
                error = false;
            } catch (InputMismatchException exception) {
                SCANNER.next();
                System.out.println("Not a number");
            }
        } while (error);
        SCANNER.nextLine();
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
}
