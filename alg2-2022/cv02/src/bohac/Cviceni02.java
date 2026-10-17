package bohac;

import bohac.geometry.Trojuhlenik;

public class Cviceni02 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Trojuhlenik.createRandom(5,20));
        }
    }
}
