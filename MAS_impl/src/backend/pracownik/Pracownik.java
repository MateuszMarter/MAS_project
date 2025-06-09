package backend.pracownik;

import backend.Raport;
import backend.zadanie.StatusZadania;
import backend.zadanie.Zadanie;
import backend.Zaloga;
import util.Ext;
import util.IdGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Pracownik.
 */
public abstract class Pracownik extends Ext {
    private static float PENSJA_PODSTAWOWA = 3500f;

    private long id;
    private String imie;
    private String nazwisko;
    private Zaloga zaloga;

    private final List<Raport> raporty = new ArrayList<>();

    /**
     * Instantiates a new Pracownik.
     *
     * @param imie     the imie
     * @param nazwisko the nazwisko
     * @param zaloga   the zaloga
     */
    public Pracownik(String imie, String nazwisko, Zaloga zaloga) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        setZaloga(zaloga);
        this.id = IdGenerator.genId();
    }

    private void setZaloga(Zaloga zaloga) {
        zaloga.dodajPracownika(this);
        this.zaloga = zaloga;
    }

    /**
     * Oblicz pensje float.
     *
     * @return the float
     */
    public abstract float obliczPensje();

    /**
     * Wykonaj zadanie.
     *
     * @param zadanie the zadanie
     */
    public void wykonajZadanie(Zadanie zadanie) {
        zadanie.setStatus(StatusZadania.ZREALIZOWANE);
    }

    /**
     * Wybierz zadanie.
     *
     * @param zadanie      the zadanie
     * @param nazwaZadania the nazwa zadania
     * @param deadline     the deadline
     */
    public void wybierzZadanie(Zadanie zadanie, String nazwaZadania, LocalDateTime deadline) {
        new Raport(nazwaZadania, this, zadanie, deadline);
        zadanie.setStatus(StatusZadania.NIEZREALIZOWANE);
    }

    /**
     * Wyswietl zadania.
     */
    public void wyswietlZadania() {
        System.out.println(Raport.getZadania());
    }

    /**
     * Wyswietl swoje zadania.
     */
    public void wyswietlSwojeZadania() {getRaporty().forEach(r -> System.out.println(r.getZadanie()));}

    /**
     * Porzuc zadanie.
     *
     * @param zadanie the zadanie
     */
    public void porzucZadanie(Zadanie zadanie) {
        List<Raport> raporty = getRaporty().stream().filter(r -> r.getZadanie() == zadanie).toList();
        for(Raport raport : raporty) {
            raport.remove();
        }

        getRaporty().removeIf(r -> r.getZadanie() == zadanie);
    }

    /**
     * Gets pensja podstawowa.
     *
     * @return the pensja podstawowa
     */
    public static float getPensjaPodstawowa() {
        return PENSJA_PODSTAWOWA;
    }

    /**
     * Sets pensja podstawowa.
     *
     * @param pensjaPodstawowa the pensja podstawowa
     */
    public static void setPensjaPodstawowa(float pensjaPodstawowa) {
        PENSJA_PODSTAWOWA = pensjaPodstawowa;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets imie.
     *
     * @return the imie
     */
    public String getImie() {
        return imie;
    }

    /**
     * Gets nazwisko.
     *
     * @return the nazwisko
     */
    public String getNazwisko() {
        return nazwisko;
    }

    /**
     * Gets zaloga.
     *
     * @return the zaloga
     */
    public Zaloga getZaloga() {
        return zaloga;
    }

    /**
     * Gets raporty.
     *
     * @return the raporty
     */
    public List<Raport> getRaporty() {
        return raporty;
    }

    /**
     * Add raport.
     *
     * @param raport the raport
     */
    public void addRaport(Raport raport) {
        this.raporty.add(raport);
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", zaloga=" + zaloga +
                '}';
    }

    @Override
    public int remove() {
        zaloga.usunPracownika(this);
        zaloga = null;

        return super.remove();
    }

}
