package bohac;

/**
 * This object holds two double values - the x and y coordinate - that's it
 */
public class Point {
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Prompt user to enter a single point
     *
     * @return Point with the user specified coordinates
     */
    public static Point prompt() {
        double x = Utils.promptNumericDouble("Value of x: ");
        double y = Utils.promptNumericDouble("Value of y: ");
        return new Point(x, y);
    }

    /**
     * Prompt user to enter multiple points
     *
     * @param n The amount of points
     * @return Array of Points
     */
    public static Point[] promptMany(int n) {
        Point[] points = new Point[n];
        System.out.println("Please input coordinates of the points.\n");
        for (int i = 0; i < n; i++) {
            System.out.printf("Point #%d\n", i + 1);
            System.out.println("------------");
            points[i] = prompt();
            System.out.println();
        }
        return points;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
