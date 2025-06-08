package backend;

import backend.pracownik.Pracownik;
import backend.zadanie.Zadanie;
import util.Ext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Raport extends Ext {
    private static final List<Zadanie> zadania = new ArrayList<>();
    private String nazwaZadania;
    private String opis;
    private LocalDateTime deadline;

    private Pracownik pracownik;
    private Zadanie zadanie;

    public Raport(String nazwaZadania, Pracownik pracownik, Zadanie zadanie, LocalDateTime deadline) {
        setNazwaZadania(nazwaZadania);
        setZadnie(zadanie);
        setOpis(null);
        setDeadline(deadline);
        setPracownik(pracownik);
    }

    public static List<Zadanie> getZadania() {
        return zadania;
    }

    public void setNazwaZadania(String nazwaZadania) {
        this.nazwaZadania = nazwaZadania;
    }

    public void setZadnie(Zadanie zadanie) {
        this.zadanie = zadanie;
        zadanie.setRaport(this);
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
        pracownik.addRaport(this);
    }

    public static void dodajZadanie(Zadanie zadanie) {
        zadania.add(zadanie);
    }

    public static void usunZadanie(Zadanie zadanie) {
        zadania.remove(zadanie);
    }

    public Zadanie getZadanie() {
        return this.zadanie;
    }

    @Override
    public String toString() {
        return "Raport{" +
                "nazwaZadania='" + nazwaZadania + '\'' +
                ", opis='" + opis + '\'' +
                ", deadline=" + deadline +
                ", pracownik=" + pracownik +
                ", zadanie=" + zadanie +
                '}';
    }
}
