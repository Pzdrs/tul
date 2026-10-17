package bohac;

import java.io.*;
import java.util.Scanner;

public class TextSouborStatistika {
    static int totalCharacters, letters, digits, totalRows, emptyRows;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.printf("Current directory: %s\n", System.getProperty("user.dir"));
        boolean fileExists;
        do {
            System.out.print("Filename: ");
            String fileName = scanner.next();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%s.stat", fileName.split("\\.")[0])))) {
                fileExists = true;

                // reading line by line
                while (reader.ready()) {
                    String line = reader.readLine().trim();
                    if (line.isEmpty()) emptyRows++;
                    else {
                        for (char c : line.toCharArray()) {
                            if (Character.isLetter(c)) letters++;
                            else if (Character.isDigit(c)) digits++;
                            totalCharacters++;
                        }
                    }
                    totalRows++;
                }
                String stats = String.format("Letters: %d\nNumbers: %d\nSpecial characters (spaces, periods, ...): %d\nNon-empty rows: %d\nTotal rows: %d",
                        letters, digits, totalCharacters - (letters + digits), totalRows - emptyRows, totalRows);
                System.out.println(stats);
                writer.write(stats);
            } catch (IOException e) {
                fileExists = false;
                System.out.println("File doesn't exist, try again.");
            }
        } while (!fileExists);
    }
}
