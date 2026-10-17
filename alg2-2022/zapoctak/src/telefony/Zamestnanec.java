package telefony;

/**
 * @author
 */
public class Zamestnanec implements CSVSerializable {

    private int osobniCislo;
    private String jmeno;
    private String prijmeni;
    private int cisloPrac;
    private long telCislo;

    public Zamestnanec(int osobniCislo, String jmeno, String prijmeni, int cisloPrac, long telCislo) {
        this.osobniCislo = osobniCislo;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.telCislo = telCislo;
        this.cisloPrac = cisloPrac;
    }

    public int getOsobniCislo() {
        return osobniCislo;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public long getTelCislo() {
        return telCislo;
    }

    public int getCisloPrac() {
        return cisloPrac;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public void setTelCislo(long telCislo) {
        this.telCislo = telCislo;
    }

    public void setCisloPrac(int cisloPrac) {
        this.cisloPrac = cisloPrac;
    }

    @Override
    public String toString() {
        return "Zamestnanec{" + "osobniCislo=" + osobniCislo + ", jmeno=" + jmeno + ", prijmeni=" + prijmeni + ", cisloPrac=" + cisloPrac + ", telCislo=" + telCislo + '}';
    }

    @Override
    public String serialize() {
        return String.format("%d;%s;%s;%d;%d", osobniCislo, jmeno, prijmeni, cisloPrac, telCislo);
    }
}
