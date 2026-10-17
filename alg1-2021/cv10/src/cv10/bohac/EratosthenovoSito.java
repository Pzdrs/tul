package cv10.bohac;

import java.util.Arrays;

public class EratosthenovoSito {
    public static void main(String[] args) {
        sieveOfEratosthenes(100);
    }

    public static void sieveOfEratosthenes(int k) {
        boolean[] array = new boolean[k + 1];
        Arrays.fill(array, true);
        for (int i = 2; i < array.length; i++) {
            if (array[i]) {
                System.out.println(i);
                for (int j = 0; j < array.length; j++) if (j % i == 0) array[j] = false;
            }
        }
    }
}
