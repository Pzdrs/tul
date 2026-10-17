package bohac;

import java.util.Arrays;
import java.util.Scanner;

/**
 * The main class
 */
public class PolygonPointRelationship {
    static Scanner scanner = new Scanner(System.in);
    /**
     * The amount of vertices allowed for a polygon, the assignment specifically stated a <b>triangle</b>, that's why it's set to 3
     */
    static final int MAX_POLYGON_VERTICES = 3;
    /**
     * The banner with the name of the project and it's author - me
     */
    static final String WELCOME_BANNER = new StringBuilder()
            .append("+------------------------------------------+\n")
            .append("| ALG1 - SEMESTRALNI PRACE - by Petr Bohac |\n")
            .append("+------------------------------------------+\n").toString();

    private static Polygon polygon;
    /**
     * Polygon object used for debugging, the teacher said leave debug stuff in so I did
     */
    private static final Polygon debugPolygon = new Polygon(
            new Point(20, 20),
            new Point(40, 65),
            new Point(75, 55),
            new Point(45, 85),
            new Point(20, 80));

    private static Point[] points;
    /**
     * Same as the debug Polygon object
     */
    private static final Point[] debugPoints = new Point[]{new Point(19.01, 79.18)};

    /**
     * The main method
     *
     * @param args Arguments passed to the JVM
     */
    public static void main(String[] args) {
        CommandResponse response = null;
        do {
            System.out.println(WELCOME_BANNER);
            displayHeader(response);
            displayMenu();
            response = processCommand(Utils.promptNumericInt("> "));
        } while (response.isSuccess());
    }

    /**
     * This method processes commands passed from the main method and runs necessary logic
     *
     * @param option User-chosen option
     * @return {@link CommandResponse} which is used to determine the next order of actions
     */
    public static CommandResponse processCommand(int option) {
        switch (option) {
            case 1 -> {
                //polygon = debugPolygon;
                polygon = Polygon.prompt(MAX_POLYGON_VERTICES);
                return new CommandResponse(true, "Polygon has been added successfully!");
            }
            case 2 -> {
                //points = debugPoints;
                int n = Utils.promptNumericInt("How many points will you be adding?: ");
                if (points == null)
                    points = Point.promptMany(n);
                else points = Utils.concat(points, Point.promptMany(n));
                return new CommandResponse(true, "Points have been added successfully!");
            }
            case 3 -> {
                if (polygon == null || points == null) {
                    return new CommandResponse(true, "Please set up a polygon and add the points first.");
                } else {
                    return new CommandResponse(true, () -> {
                        for (int i = 0; i < points.length; i++) {
                            Point point = points[i];
                            System.out.printf("Point #%d -> [%f, %f], lays %s the polygon\n",
                                    i + 1, point.getX(), point.getY(), polygon.contains(point) ? "inside" : "outside");
                        }
                    });
                }
            }
            case 4 -> {
                polygon = null;
                points = null;
                return new CommandResponse(true, "Current data has been reset!");
            }
            case 5 -> {
                return new CommandResponse(false, "");
            }
            default -> {
                return new CommandResponse(true, "Invalid option. Please try again.");
            }
        }
    }

    /**
     * Displays the header with all the crucial information
     *
     * @param response {@link CommandResponse} object to control what data gets displayed
     */
    private static void displayHeader(CommandResponse response) {
        if (polygon != null)
            System.out.println("Current polygon -> " + polygon);
        if (points != null)
            System.out.println("Current points -> " + Arrays.toString(points));
        System.out.println();
        if (response != null) {
            if (response.hasMessage()) System.out.println(response.getMessage());
            if (response.hasRunnable()) response.getRunnable().run();
            System.out.println();
        }
    }

    /**
     * Displays the menu
     */
    private static void displayMenu() {
        System.out.println("Choose an option to continue:");
        System.out.println("-----------------------------");
        System.out.printf("[1] - %s polygon (maximal amount of vertices = %d)\n", polygon == null ? "Add a" : "Modify the", MAX_POLYGON_VERTICES);
        System.out.println("[2] - Add points");
        System.out.println("[3] - Solve for current data");
        if (polygon != null || points != null)
            System.out.println("[4] - Reset current data");
        System.out.println("[5] - Exit");
    }
}
