package bohac.vektory;


public class VektoryTest {
    public static void main(String[] args) {
        Vector<Integer> a = new Vector<>(1, 2, 3);
        Vector<Integer> b = new Vector<>(1, 2, 3);
        Vector<Integer> c = new Vector<>(3, 4, 5);
        Vector<Double> d = new Vector<>(10.5, 9.7);
        System.out.println(a);
        System.out.println(Vector.add(a, c));
        System.out.println(Vector.subtract(a, c));
        System.out.println(Vector.dotProduct(a, b));
        System.out.println(a.equals(b));
        System.out.println(a.equals(c));
        System.out.println(d.length());
    }
}
