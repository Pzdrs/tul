package bohac.vektory;

import java.util.Arrays;

public class Vector<T extends Number> {
    private T[] elements;

    public Vector(T... elements) {
        this.elements = elements;
    }


    /**
     * Returns a single element under a specified index
     *
     * @param index Element index
     * @return Vector element
     */
    public T getElement(int index) {
        return elements[index];
    }

    /**
     * Number of elements
     *
     * @return Vector size
     */
    public int size() {
        return elements.length;
    }

    /**
     * Delka vektoru nevim co to znamena
     *
     * @return Vector length
     */
    public double length() {
        double length = 0;
        for (T element : elements) {
            length += Math.pow(element.doubleValue(), 2);
        }
        return Math.sqrt(length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector<?> vector)) return false;
        for (int i = 0; i < vector.size(); i++)
            if (elements[i].doubleValue() != vector.elements[i].doubleValue()) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "elements=" + Arrays.toString(elements) +
                ",length=" + length() +
                '}';
    }

    public static double dotProduct(Vector<? extends Number> a, Vector<? extends Number> b) {
        checkSameSize(a, b);
        double product = 0;
        for (int i = 0; i < a.size(); i++)
            product = a.elements[i].doubleValue() * b.elements[i].doubleValue();
        return product;
    }

    public static Vector<Double> add(Vector<? extends Number> a, Vector<? extends Number> b) {
        checkSameSize(a, b);
        Double[] elements = new Double[a.size()];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = a.getElement(i).doubleValue() + b.getElement(i).doubleValue();
        }
        return new Vector<>(elements);
    }

    public static Vector<Double> subtract(Vector<? extends Number> a, Vector<? extends Number> b) {
        checkSameSize(a, b);
        Double[] elements = new Double[a.size()];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = a.getElement(i).doubleValue() - b.getElement(i).doubleValue();
        }
        return new Vector<>(elements);
    }

    private static void checkSameSize(Vector<?> a, Vector<?> b) {
        if (a.size() != b.size()) throw new IllegalArgumentException("Both vectors must be the same size.");
    }
}
