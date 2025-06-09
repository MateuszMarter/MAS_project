package backend;

import backend.pracownik.Pracownik;
import backend.zadanie.StatusZadania;
import backend.zadanie.Zadanie;
import util.Ext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Raport.
 */
public class Raport extends Ext {
    private static final List<Zadanie> zadania = new ArrayList<>();
    private String nazwaZadania;
    private String opis;
    private LocalDateTime deadline;

    private Pracownik pracownik;
    private Zadanie zadanie;

    /**
     * Instantiates a new Raport.
     *
     * @param nazwaZadania the nazwa zadania
     * @param pracownik    the pracownik
     * @param zadanie      the zadanie
     * @param deadline     the deadline
     */
    public Raport(String nazwaZadania, Pracownik pracownik, Zadanie zadanie, LocalDateTime deadline) {
        setNazwaZadania(nazwaZadania);
        setZadnie(zadanie);
        setOpis(null);
        setDeadline(deadline);
        setPracownik(pracownik);
    }

    /**
     * Gets zadania.
     *
     * @return the zadania
     */
    public static List<Zadanie> getZadania() {
        return zadania;
    }

    /**
     * Sets nazwa zadania.
     *
     * @param nazwaZadania the nazwa zadania
     */
    public void setNazwaZadania(String nazwaZadania) {
        this.nazwaZadania = nazwaZadania;
    }

    /**
     * Sets zadnie.
     *
     * @param zadanie the zadanie
     */
    public void setZadnie(Zadanie zadanie) {
        this.zadanie = zadanie;
        zadanie.setRaport(this);
        zadanie.setStatus(StatusZadania.NIEZREALIZOWANE);
    }

    /**
     * Sets opis.
     *
     * @param opis the opis
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * Sets deadline.
     *
     * @param deadline the deadline
     */
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    /**
     * Sets pracownik.
     *
     * @param pracownik the pracownik
     */
    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
        pracownik.addRaport(this);
    }

    /**
     * Dodaj zadanie.
     *
     * @param zadanie the zadanie
     */
    public static void dodajZadanie(Zadanie zadanie) {
        zadania.add(zadanie);
    }

    /**
     * Dodaj zadania.
     *
     * @param zadania the zadania
     */
    public static void dodajZadania(List<Zadanie> zadania) {
        Raport.zadania.addAll(zadania);
    }

    /**
     * Usun zadanie.
     *
     * @param zadanie the zadanie
     */
    public static void usunZadanie(Zadanie zadanie) {
        zadania.remove(zadanie);
    }

    /**
     * Gets zadanie.
     *
     * @return the zadanie
     */
    public Zadanie getZadanie() {
        return this.zadanie;
    }


    /**
     * Gets nazwa zadania.
     *
     * @return the nazwa zadania
     */
    public String getNazwaZadania() {
        return nazwaZadania;
    }

    /**
     * Gets pracownik.
     *
     * @return the pracownik
     */
    public Pracownik getPracownik() {
        return pracownik;
    }


    /**
     * Gets opis.
     *
     * @return the opis
     */
    public String getOpis() {
        return opis;
    }


    /**
     * Gets all pracownicy.
     *
     * @return the all pracownicy
     */
    public List<Pracownik> getAllPracownicy() {
        List<Raport> raportyZadania = zadanie.getRaporty();
        List<Pracownik> wspolPracownicy = new ArrayList<>();

        for(Raport raport : raportyZadania) {
            wspolPracownicy.add(raport.getPracownik());
        }

        return wspolPracownicy;
    }

    /**
     * Gets deadline.
     *
     * @return the deadline
     */
    public LocalDateTime getDeadline() {
        return deadline;
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

    @Override
    public int remove() {
        pracownik.getRaporty().remove(this);
        zadanie.getRaporty().remove(this);
        if(zadanie.getRaporty().isEmpty()) {
            zadanie.setStatus(StatusZadania.DOSTEPNE);
        }

        return super.remove();
    }

}
