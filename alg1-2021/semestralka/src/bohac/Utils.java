package bohac;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Utils class which holds a few utility methods
 */
public class Utils {
    private Utils() {
    }

    /**
     * Method for clearing the screen, basically just print 50 empty lines
     */
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Wrapper for {@link Scanner#nextDouble()} with {@link InputMismatchException} handler
     *
     * @param message What message to prompt with
     * @return Double value
     */
    public static double promptNumericDouble(String message) {
        Scanner scanner = PolygonPointRelationship.scanner;
        boolean error = true;
        double value = 0;
        do {
            try {
                System.out.print(message);
                value = scanner.nextDouble();
                error = false;
            } catch (InputMismatchException exception) {
                scanner.next();
            }
        } while (error);
        return value;
    }

    /**
     * Does the same as {@link #promptNumericDouble(String)}, just for integers
     *
     * @param message What message to prompt with
     * @return Integer value
     */
    public static int promptNumericInt(String message) {
        return (int) promptNumericDouble(message);
    }

    /**
     * Merging two arrays - doing that stuff manually cuz I surely would get yelled at for using the Java API
     *
     * @param a First array
     * @param b Second array
     * @return Merged array
     */
    public static Point[] concat(Point[] a, Point[] b) {
        Point[] concat = new Point[a.length + b.length];
        /*System.arraycopy(a, 0, concat, 0, a.length);
        System.arraycopy(b, 0, concat, a.length, b.length);*/
        for (int i = 0; i < a.length; i++) concat[i] = a[i];
        for (int i = 0; i < b.length; i++) concat[i + a.length] = b[i];
        return concat;
    }
}
