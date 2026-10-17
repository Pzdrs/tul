package cv10.bohac;

import java.util.Arrays;

public class ArrayToolsTest {
    public static void main(String[] args) {
        double[] array = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] random = new double[]{1, 7, 15, 4, 3, 15, 9, 15, 2, 4};
        double[] alternating = new double[]{1, -2, 3, -4, 5, -6, 7, -8, 9, -10};

        System.out.println(ArrayTools.sum(array));

        System.out.println(ArrayTools.sum(array, 5));

        System.out.println(ArrayTools.max(array, 5));

        System.out.println(ArrayTools.firstMax(random));

        System.out.println(ArrayTools.lastMax(random));

        System.out.println(ArrayTools.occurs(random, 10, 7));

        System.out.println(ArrayTools.isAscending(random, 10));
        System.out.println(ArrayTools.isAscending(array, 10));

        System.out.println(ArrayTools.isAlternating(array));
        System.out.println(ArrayTools.isAlternating(alternating));

        ArrayTools.reverse(array);
        System.out.println(Arrays.toString(array));

        System.out.println(Arrays.toString(ArrayTools.getReversed(array)));
    }
}
