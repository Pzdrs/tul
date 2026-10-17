package bohac.geometry;

import java.util.Random;

public class Trojuhlenik {
    enum SideType {
        RUZNOSTRANNY, ROVNOSTRANNY, ROVNORAMENNY
    }

    enum AngleType {
        PRAVOUHLY, TUPOUHLY, OSTROUHLY
    }

    private final double a, b, c;
    private SideType sideType;
    private AngleType angleType;

    private Trojuhlenik(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        setSideType();
        setAngleType();
    }

    public static Trojuhlenik create(double a, double b, double c) {
        if (!isReal(a, b, c)) return null;
        return new Trojuhlenik(a, b, c);
    }

    public static Trojuhlenik createRandom(int min, int max) {
        Trojuhlenik trojuhlenik;
        do {
            trojuhlenik = Trojuhlenik.create(getRandomInt(min, max), getRandomInt(min, max), getRandomInt(min, max));
        } while (trojuhlenik == null);
        return trojuhlenik;
    }

    private static int getRandomInt(int min, int max) {
        return min + new Random().nextInt(max - min + 1);
    }


    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    public double getPerimeter() {
        return a + b + c;
    }

    public void setSideType() {
        if (a == b && b == c) this.sideType = SideType.ROVNOSTRANNY;
        else if (a != b && a != c && b != c) this.sideType = SideType.RUZNOSTRANNY;
        else this.sideType = SideType.ROVNORAMENNY;
    }

    public void setAngleType() {
        double alfa = Math.toDegrees(Math.acos((Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2)) / (2 * b * c)));
        double beta = Math.toDegrees(Math.acos((Math.pow(c, 2) + Math.pow(a, 2) - Math.pow(b, 2)) / (2 * a * c)));
        double gamma = 180 - (alfa + beta);
        if (alfa == 90 || beta == 90 || gamma == 90) this.angleType = AngleType.PRAVOUHLY;
        else if (alfa > 90 || beta > 90 || gamma > 90) this.angleType = AngleType.TUPOUHLY;
        else this.angleType = AngleType.OSTROUHLY;
    }

    private static boolean isReal(double a, double b, double c) {
        return a + b > c && a + c > b && b + c > a;
    }

    public SideType getSideType() {
        return sideType;
    }

    public AngleType getAngleType() {
        return angleType;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    @Override
    public String toString() {
        return "Trojuhlenik{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", sideType=" + sideType +
                ", angleType=" + angleType +
                ", area=" + getArea() +
                ", circumference=" + getPerimeter() +
                '}';
    }
}
