package bohac;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextSouborSlova {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Integer> words = new HashMap<>();

    public static void main(String[] args) {
        System.out.printf("Current directory: %s\n", System.getProperty("user.dir"));
        File file;
        do {
            System.out.print("Filename: ");
            file = new File(scanner.next());

            try (Scanner reader = new Scanner(file);
                 PrintWriter writer = new PrintWriter(String.format("%s_slova.stat", file.getName().split("\\.")[0]))) {

                while (reader.hasNext()) {
                    String word = reader.next();
                    words.put(word, words.getOrDefault(word, 0) + 1);
                }
                StringBuilder builder = new StringBuilder();
                words.forEach((s, integer) -> builder.append(s).append(" ").append(integer).append("\n"));
                System.out.println(builder);
                writer.print(builder);
            } catch (FileNotFoundException ignored) {
                System.out.println("File doesn't exist, try again.");
            }
        } while (!file.exists());
    }
}
