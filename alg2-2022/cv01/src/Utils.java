import java.util.*;
import java.util.function.Consumer;

public class Utils {
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

    /**
     * Check for a palindrome property of a string
     *
     * @param s String to test
     * @return Is a palindrome
     */
    public static boolean isPalindrome(String s) {
        String trim = s.replaceAll("[\s,]", "");
        return new StringBuilder(trim).reverse().toString().equalsIgnoreCase(trim);
    }

    /**
     * Print the menu in console + prompt the user
     *
     * @param menu A list of MenuItem instances
     * @return The choice (clean data)
     */
    public static int printMenu(List<MenuItem> menu) {
        System.out.println("Choose an option: ");
        for (int i = 0; i < menu.size(); i++) {
            System.out.printf("[%d] - %s\n", i + 1, menu.get(i).getDescription());
        }
        return Utils.promptNumericInt("> ", new AbstractMap.SimpleEntry<>(1, menu.size()));
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
                value = Main.scanner.nextInt();
                if (!inRange(value, range.getKey(), range.getValue())) {
                    System.out.println("Value out of range");
                    continue;
                }
                error = false;
            } catch (InputMismatchException exception) {
                Main.scanner.next();
                System.out.println("Not a number");
            }
        } while (error);
        Main.scanner.nextLine();
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
     * Prompt the user for a string until empty line is passed in
     *
     * @param consumer A consumer interface with a piece of code that is suppose to be ran on each iteration
     */
    public static void readLineUntilEmpty(Consumer<String> consumer) {
        String line;
        do {
            System.out.print("Enter a string: ");
            line = Main.scanner.nextLine();
            if (!line.isEmpty()) consumer.accept(line);
        } while (!line.equals(""));
    }

    /**
     * Functional approach to try catch wrapped in a do-while loop to assure an eventual execution of the supplied Runnable
     *
     * @param runnable    Try code
     * @param onException Catch code
     */
    public static void tryCatch(Runnable runnable, Runnable onException) {
        boolean done = false;
        do {
            try {
                runnable.run();
                done = true;
            } catch (Exception exception) {
                onException.run();
            }
        } while (!done);
    }
}
