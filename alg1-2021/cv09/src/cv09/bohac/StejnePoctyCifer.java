package cv09.bohac;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ze zadani jsem trochu confused, snad je tohle reseni ok
 */
public class StejnePoctyCifer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            try {
                long[] input = Arrays.stream(scanner.nextLine().trim().replaceAll(" +", " ").split(" ")).mapToLong(Long::parseLong).toArray();
                if (!valid(input)) {
                    System.out.println("input error");
                    continue;
                }
                if (input[0] <= 0) break;
                if (sameLength(input)) {
                    System.out.println("stejna delka");
                } else System.out.println("nejsou stejne dlouhe");
            } catch (NumberFormatException exception) {
                System.out.println("input error");
            }
        }
    }

    private static boolean valid(long[] input) {
        return input.length == 2 && input[1] > 0;
    }

    private static boolean sameLength(long[] input) {
        return String.valueOf(input[0]).length() == String.valueOf(input[1]).length();
    }
}
