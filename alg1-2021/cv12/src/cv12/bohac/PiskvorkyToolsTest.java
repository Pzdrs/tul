package cv12.bohac;

public class PiskvorkyToolsTest {
    public static void main(String[] args) {
        int[][] a = new int[][]{
                new int[]{0, 1, 1, 0, 1},
                new int[]{1, 0, 1, 0, 1},
                new int[]{0, 1, 0, 1, 1},
                new int[]{0, 1, 1, 0, 0},
                new int[]{1, 0, 1, 0, 1},
        };

        System.out.println(PiskvorkyTools.pocetStejnychVRadce(a, 0, 0));
        System.out.println(PiskvorkyTools.pocetStejnychVSloupci(a, 0, 1));
        System.out.println(PiskvorkyTools.pocetStejnychVDiag1(a, 2, 2));
        System.out.println(PiskvorkyTools.pocetStejnychVDiag2(a, 0, 4));
    }
}
