package bohac;

import java.io.*;
import java.util.Scanner;

public class SrazkyAnalyza {
    static Scanner scanner = new Scanner(System.in);
    static File file;

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        System.out.print("Zadej jmeno souboru: ");
        file = new File(scanner.nextLine());

        try (DataInputStream inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            while (true) {
                DenniSrazky load = DenniSrazky.load(inputStream);
                System.out.println(load);
            }
        } catch (IOException e) {
            if (!(e instanceof EOFException))
                System.out.println("An error occurred while trying to read the file, probably doesn't exist.");
        }
    }
}
