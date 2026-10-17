package cv10.bohac;

import java.util.Random;

public class NahodnaPermutace {
    static Random random = new Random();

    public static int[] randomPermutation(int n) {
        int[] permutation = new int[n];
        for (int i = 0; i < permutation.length; i++) permutation[i] = i;
        shuffle(permutation);
        return permutation;
    }

    public static void shuffle(int[] array) {
        for (int i = array.length; i > 1; i--) {
            int j = random.nextInt(i);
            int temp = array[i - 1];
            array[i - 1] = array[j];
            array[j] = temp;
        }
    }
}
