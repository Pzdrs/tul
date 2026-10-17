import java.io.File;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<MenuItem> menu = new ArrayList<>(Arrays.asList(
            new MenuItem("Convert from Base10 to BaseX", () -> {
                NumberConvertor convertor = new NumberConvertor(10, Utils.promptNumericInt("Convert to base: ", new AbstractMap.SimpleEntry<>(2, 16)));
                int num = Utils.promptNumericInt("Enter a number: ");
                System.out.printf("Converting [%d] from Base%d to Base%d => %s\n",
                        num, convertor.getBaseFrom(), convertor.getBaseTo(), convertor.convert(num));
            }),
            new MenuItem("Convert from BaseX to Base10", () -> {
                NumberConvertor convertor = new NumberConvertor(Utils.promptNumericInt("Convert from base: ", new AbstractMap.SimpleEntry<>(2, 16)), 10);
                Utils.tryCatch(() -> {
                    System.out.print("Enter a string: ");
                    String s = scanner.nextLine();
                    System.out.printf("Converting [%s] from Base%d to Base%d => %s\n",
                            s, convertor.getBaseFrom(), convertor.getBaseTo(), convertor.convert(s));
                }, () -> System.out.printf("Invalid value for Base%d\n", convertor.getBaseFrom()));
            }),
            new MenuItem("File shenanigans", () -> {
                Utils.readLineUntilEmpty(s -> {
                    File file = new File(s);
                    if (file.exists()) {
                        System.out.printf("Parent folder (absolute path): %s\nFilename: %s\nExtension: %s\n",
                                file.getAbsoluteFile().getParentFile(), file.getName(), file.getName().substring(file.getName().indexOf(".")));
                    } else System.out.println("This file doesn't exist");
                });
            }),
            new MenuItem("Palindrome check", () -> {
                Utils.readLineUntilEmpty(s -> System.out.println(Utils.isPalindrome(s) ? "Is a palindrome" : "Isn't a palindrome"));
            }),
            new MenuItem("Exit", () -> System.exit(0))
    ));

    public static void main(String[] args) {
        menu.get(Utils.printMenu(menu) - 1).run();
    }
}
