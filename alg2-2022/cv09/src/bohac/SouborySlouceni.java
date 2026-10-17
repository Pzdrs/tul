package bohac;

import java.io.*;
import java.util.Scanner;

public class SouborySlouceni {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.printf("Current directory: %s\n", System.getProperty("user.dir"));
        File outputFile = prompt();
        while (outputFile.exists()) {
            System.out.println("This outputFile already exists");
            System.out.println("[1] - Zadat novy soubor\n[2] - Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    outputFile = prompt();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }

        System.out.println("Files to concatenate (kazdy file na novy radek): ");
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim().split(" ")[0];
            if (line.isEmpty()) break;
            appendFile(outputFile, new File(line));
        }
    }

    public static void appendFile(File out, File in) {
        try (BufferedReader reader = new BufferedReader(new FileReader(in));
             BufferedWriter writer = new BufferedWriter(new FileWriter(out, true))) {
            while (reader.ready()) {
                writer.write(reader.readLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(in.getName() + " doesn't exist, ignoring..");
        } catch (IOException e) {
            System.out.println("An unexpected error occurred.");
        }
    }

    public static File prompt() {
        System.out.print("Output file: ");
        return new File(scanner.next());
    }
}
