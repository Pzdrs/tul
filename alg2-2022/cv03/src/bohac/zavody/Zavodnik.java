package bohac.zavody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Zavodnik {
    private String name, lastName;
    private UUID registrationNumber;
    private LocalDate birth;
    private LocalDateTime raceStart, raceEnd;


    public Zavodnik(String name, String lastName, int birthYear) {
        this.name = name;
        this.lastName = lastName;
        this.birth = LocalDate.of(birthYear, 1, 1);
        this.registrationNumber = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public UUID getRegistrationNumber() {
        return registrationNumber;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public LocalDateTime getRaceStart() {
        return raceStart;
    }

    public LocalDateTime getRaceEnd() {
        return raceEnd;
    }

    public void setRaceStart(LocalDateTime raceStart) {
        this.raceStart = raceStart;
    }

    public void setRaceEnd(LocalDateTime raceEnd) {
        this.raceEnd = raceEnd;
    }

    @Override
    public String toString() {
        return "Zavodnik{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registrationNumber=" + registrationNumber +
                ", birth=" + birth +
                ", raceStart=" + raceStart +
                ", raceEnd=" + raceEnd +
                '}';
    }
}
