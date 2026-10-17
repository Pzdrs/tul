package bohac.zavody;

import java.time.LocalDateTime;

public class ZavodnikTest {
    public static void main(String[] args) {
        Zavodnik zavodnik = new Zavodnik("Pycrs", "Bohac", 2002);
        zavodnik.setRaceStart(LocalDateTime.now());
        zavodnik.setRaceEnd(LocalDateTime.now().plusHours(2));
        System.out.println(zavodnik);
    }
}
