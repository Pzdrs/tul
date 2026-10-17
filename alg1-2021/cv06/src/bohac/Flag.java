package bohac;

public class Flag {
    public static void main(String[] args) {
        flag(10, 15);
    }

    public static void flag(int m, int n) {
        // min size
        if ((m < 4 || n < 4) || m % 2 != 0) return;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + 1) <= m / 2) {
                    if (j < i + 1) {
                        System.out.print(" * ");
                    } else System.out.print(" - ");
                } else {
                    if (j < m - i) {
                        System.out.print(" * ");
                    } else System.out.print(" o ");
                }
            }
            System.out.println();
        }
    }
}
