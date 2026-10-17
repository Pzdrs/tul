package bohac;

import java.util.Arrays;

/**
 * This object holds all the vertices - points - the polygon is made out of, as well as all the necessary logic
 */
public class Polygon {
    private Point[] vertices;

    public Polygon(int n) {
        this.vertices = new Point[n];
    }

    public Polygon(Point... vertices) {
        this.vertices = vertices;
    }

    /**
     * Tests whether a Point lays inside of a Polygon's bounding box
     *
     * @param point Point to test for
     * @return Whether the Point lays in the bounding box
     */
    private boolean isInsideBoundingBox(Point point) {
        double xMin, xMax, yMin, yMax;
        xMin = yMin = Double.MAX_VALUE;
        xMax = yMax = Double.MIN_VALUE;
        for (Point vertex : vertices) {
            if (vertex.getX() > xMax) xMax = vertex.getX();
            if (vertex.getX() < xMin) xMin = vertex.getX();
            if (vertex.getY() > yMax) yMax = vertex.getY();
            if (vertex.getY() < yMin) yMin = vertex.getY();
        }
        return ((point.getX() <= xMax && point.getX() >= xMin) && (point.getY() <= yMax && point.getY() >= yMin));
    }

    /**
     * Basically the assignment
     *
     * @param point A Point which is being tested
     * @return True if the Point is inside the Polygon, otherwise false
     */
    public boolean contains(Point point) {
        if (!isInsideBoundingBox(point)) return false;
        int n = vertices.length;
        int hits = 0;
        double x = point.getX();
        double y = point.getY();

        for (int i = 0; i < n - 1; i++) {
            Point a = vertices[i];
            Point b = vertices[i + 1];
            double ax = a.getX();
            double ay = a.getY();
            double bx = b.getX();
            double by = b.getY();
            if (y < ay != y < by && x < (bx - ax) * (y - ay) / (by - ay) + ax)
                hits++;
        }
        return hits % 2 != 0;
    }

    /**
     * Add a single vertex to the Polygon object
     *
     * @param point Vertex to add
     */
    public void addVertex(Point point) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == null) {
                vertices[i] = point;
                return;
            }
        }
    }

    /**
     * Prompt user to enter a Polygon
     *
     * @param n The amount of vertices said Polygon is planned to have
     * @return The Polygon object with n vertices
     */
    public static Polygon prompt(int n) {
        Polygon polygon = new Polygon(n);
        System.out.println("Please input coordinates of the points that make up the polygon.\n");
        for (int i = 0; i < n; i++) {
            System.out.printf("Vertex #%d\n", i + 1);
            System.out.println("------------");
            polygon.addVertex(Point.prompt());
            System.out.println();
        }
        return polygon;
    }

    @Override
    public String toString() {
        return "Polygon{" + Arrays.toString(vertices) + "}";
    }
}
