package bohac;

import java.io.*;
import java.util.Scanner;

public class SrazkyZaznam {
    static Scanner scanner = new Scanner(System.in);
    static File file;

    public static void main(String[] args) {
        System.out.print("Zadej jmeno souboru: ");
        file = new File(scanner.nextLine());

        try {
            if (!file.createNewFile()) System.out.println("File created.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file, exiting...");
            System.exit(0);
        }

        try (DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file, true)))) {
            System.out.println("Zadej den, mesic, rok a celkovy uhrn srazek:");
            DenniSrazky denniSrazky = new DenniSrazky(scanner.nextByte(), scanner.nextByte(), scanner.nextShort(), scanner.nextFloat());
            denniSrazky.save(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
