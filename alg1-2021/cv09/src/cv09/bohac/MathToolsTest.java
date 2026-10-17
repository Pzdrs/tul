package cv09.bohac;

public class MathToolsTest {
    public static void main(String[] args) {
        System.out.println(Math.sin(50));
        System.out.println(MathTools.sin(50, 10E-50));

        System.out.println(Math.cos(15));
        System.out.println(MathTools.cos(15, 10E-50));

        System.out.println(Math.log(69));
        System.out.println(MathTools.ln(69, 10E-50));

        System.out.println(Math.sqrt(48));
        System.out.println(MathTools.sqrt(48, 10E-50));
    }
}
