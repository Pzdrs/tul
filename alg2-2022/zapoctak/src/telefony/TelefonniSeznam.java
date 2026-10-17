package telefony;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author petr.bohac
 */
public class TelefonniSeznam {

    private List<Pracoviste> pracoviste;
    private List<Zamestnanec> zamestnanci;

    private TelefonniSeznam(List<Pracoviste> pracoviste, List<Zamestnanec> zamestnanci) {
        this.pracoviste = pracoviste;
        this.zamestnanci = zamestnanci;
    }

    public Pracoviste getPracoviste(int id) {
        Optional<Pracoviste> potentialWorkplace = pracoviste.stream().filter(workplace -> workplace.getCisloPracoviste() == id).findFirst();
        return potentialWorkplace.orElse(null);
    }

    public static TelefonniSeznam getInstance(String jmSoubPrac, String jmSoubZam) throws IOException {
        List<Pracoviste> workplaces = new ArrayList<>();
        List<Zamestnanec> employees = new ArrayList<>();
        try (BufferedReader employeesReader = new BufferedReader(new FileReader(jmSoubZam));
             BufferedReader workplacesReader = new BufferedReader(new FileReader(jmSoubPrac))) {

            // fuck prvni radky
            employeesReader.readLine();
            workplacesReader.readLine();

            while (employeesReader.ready()) {
                String[] row = employeesReader.readLine().split(";");
                employees.add(new Zamestnanec(Integer.parseInt(row[0]), row[1], row[2], Integer.parseInt(row[3]), Long.parseLong(row[4])));
            }
            while (workplacesReader.ready()) {
                String[] row = workplacesReader.readLine().split(";");
                workplaces.add(new Pracoviste(Integer.parseInt(row[0]), row[1], row[2]));
            }
        }
        return new TelefonniSeznam(workplaces, employees);
    }

    public List<Pracoviste> getPracoviste() {
        return pracoviste;
    }

    public List<Zamestnanec> getZamestnanci() {
        return zamestnanci;
    }

    @Override
    public String toString() {
        return "TelefonniSeznam{" +
                "pracoviste=" + pracoviste +
                ", zamestnanci=" + zamestnanci +
                '}';
    }
}
    
    
