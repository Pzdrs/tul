package apps;

import java.io.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import telefony.Pracoviste;
import telefony.TelefonniSeznam;
import telefony.Zamestnanec;

/**
 * @author petr.bohac
 */
public class TelefonniSeznamApp {
    // testovaci soubory
    // "data/tel/prac.csv"
    // "data/tel/zam.csv"

    private static final Scanner sc = new Scanner(System.in);
    private static TelefonniSeznam telSez = null;
    private static String employeesFilePath, workplacesFilePath;
    private static boolean konecProgramu = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Vita vas TELEFONNI SEZNAM");
        try {
            vytvorTelefonniSeznam();
            konecProgramu = telSez == null;
        } catch (FileNotFoundException e) {
            System.out.println("Nektery ze vstupnich souboru neexistuje");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Chyba pri nacitani vstupnich souboru");
            System.out.println("Nelze spustit aplikaci telefonnho seznamu");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(-2);
        }
        // podarilo se nacist soubory, vytvorit telefonni seznam
        // hlavni ridici smycka programu
        while (!konecProgramu) {
            vypisHlavniMenuProgramu();
            int volba = nactiVolbu();
            obsluhaVolby(volba);
        }
        System.out.println("Konec programu ...");
    }

    private static void obsluhaVolby(int volbaUzivatele) {
        switch (volbaUzivatele) {
            case 0 -> konecProgramu = true;
            case 1 -> searchEmployees();
            case 2 -> showAllEmployees();
            case 3 -> showAllWorkplaces();
            case 4 -> addEmployee();
            case 5 -> addWorkplace();
            default -> System.out.println("Zadana neplatna volba");
        }

    }

    private static void addWorkplace() {
        System.out.print("id: ");
        int id = sc.nextInt();
        if (telSez.getPracoviste().stream().anyMatch(workplace -> workplace.getCisloPracoviste() == id)) {
            System.out.println("pracoviste s timto id uz existuje");
            return;
        }
        System.out.print("zkratka: ");
        String zkratka = sc.next();
        if (telSez.getPracoviste().stream().anyMatch(workplace -> workplace.getZkratka().equals(zkratka))) {
            System.out.println("pracoviste s touto zkratkou uz existuje");
            return;
        }
        sc.nextLine();
        System.out.print("nazev: ");
        String nazev = sc.nextLine();
        Pracoviste pracoviste = new Pracoviste(id, zkratka, nazev);
        telSez.getPracoviste().add(pracoviste);
        try (PrintWriter writer = new PrintWriter(new FileWriter(workplacesFilePath, true))) {
            writer.println(pracoviste.serialize());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addEmployee() {
        System.out.print("os cislo: ");
        int osCislo = sc.nextInt();
        if (telSez.getZamestnanci().stream().anyMatch(employee -> employee.getOsobniCislo() == osCislo)) {
            System.out.println("pracant s timhle osobnim cislem uz existuje");
            return;
        }
        // line++
        sc.nextLine();
        System.out.print("jmeno: ");
        String name = sc.nextLine().trim();
        System.out.print("prijmeni: ");
        String lastName = sc.nextLine().trim();
        System.out.print("cislo pracoviste: ");
        Pracoviste pracoviste = telSez.getPracoviste(sc.nextInt());
        if (pracoviste == null) {
            System.out.println("neexistujici pracoviste");
            return;
        }
        System.out.print("tel cislo: ");
        long phone = sc.nextLong();
        Zamestnanec employee = new Zamestnanec(osCislo, name, lastName, pracoviste.getCisloPracoviste(), phone);
        telSez.getZamestnanci().add(employee);
        try (PrintWriter writer = new PrintWriter(new FileWriter(employeesFilePath, true))) {
            writer.println(employee.serialize());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void searchEmployees() {
        System.out.print("Hledat: ");
        String search = sc.nextLine().trim().toLowerCase();
        List<Zamestnanec> result = telSez.getZamestnanci().stream().filter(employee ->
                employee.getJmeno().toLowerCase().contains(search) || employee.getPrijmeni().toLowerCase().contains(search)).toList();
        if (result.size() == 0) {
            System.out.println("found 0 results");
        } else {
            System.out.println("Jmeno Prijmeni Tel. cislo Pracoviste");
            result.forEach(employee -> {
                System.out.printf("%s %s %d %s\n",
                        employee.getJmeno(),
                        employee.getPrijmeni(),
                        employee.getTelCislo(),
                        telSez.getPracoviste(employee.getCisloPrac()).getNazev());
            });
        }
    }

    private static void showAllWorkplaces() {
        System.out.println();
        telSez.getPracoviste().forEach(System.out::println);
    }

    private static void showAllEmployees() {
        System.out.println();
        telSez.getZamestnanci().forEach(System.out::println);
    }

    private static void vypisHlavniMenuProgramu() {
        System.out.println("");
        System.out.println("Hlavni menu programu");
        System.out.println("1. Vyhledat zamestnace");
        System.out.println("2. Kontrolni vypis vsech zamestnancu");
        System.out.println("3. kontrolni vypis vsech pracovist");
        System.out.println("4. pridat pracanta");
        System.out.println("5. pridat pracoviste");
        System.out.println("0. Konec programu");
    }

    private static int nactiVolbu() {
        int volba = -1;
        System.out.print("Zadej zvolenou polozku menu: ");
        try {
            // zkusim nacist cislo
            volba = sc.nextInt();
        } catch (InputMismatchException e) {
            // textovy retezec se nepodarilo prevest na cele cislo
            // cokoli jiného než číslo je neplatna volba
            volba = -1;
        } finally {
            // v kazdem pripade vyprazdnime vstup - respektive 
            // aktualni radek vstupu vcetne sekvence znak; konce radku
            sc.nextLine();
        }
        // je vracena platna celocislena hodnota
        return volba;
    }

    private static void vytvorTelefonniSeznam() throws IOException {
        System.out.println("Zadej název/cestu souboru se seznamem pracovišť ");
        workplacesFilePath = sc.nextLine().trim();
        System.out.println("Zadej název/cestu souboru se seznamem zaměstnanců ");
        employeesFilePath = sc.nextLine().trim();
        telSez = TelefonniSeznam.getInstance(workplacesFilePath, employeesFilePath);
    }

}
