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

public abstract class Pracownik extends Ext {
    private static float PENSJA_PODSTAWOWA = 3500f;

    private long id;
    private String imie;
    private String nazwisko;
    private Zaloga zaloga;

    private final List<Raport> raporty = new ArrayList<>();

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

    public abstract float obliczPensje();

    public void wykonajZadanie(Zadanie zadanie) {
        zadanie.setStatus(StatusZadania.ZREALIZOWANE);
    }

    public void wybierzZadanie(Zadanie zadanie, String nazwaZadania, LocalDateTime deadline) {
        new Raport(nazwaZadania, this, zadanie, deadline);
        zadanie.setStatus(StatusZadania.NIEZREALIZOWANE);
    }

    public void wyswietlZadania() {
        System.out.println(Raport.getZadania());
    }

    public void wyswietlSwojeZadania() {getRaporty().forEach(r -> System.out.println(r.getZadanie()));}

    public void porzucZadanie(Zadanie zadanie) {
        getRaporty().removeIf(r -> r.getZadanie() == zadanie);
    }

    public static float getPensjaPodstawowa() {
        return PENSJA_PODSTAWOWA;
    }

    public static void setPensjaPodstawowa(float pensjaPodstawowa) {
        PENSJA_PODSTAWOWA = pensjaPodstawowa;
    }

    public long getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Zaloga getZaloga() {
        return zaloga;
    }

    public List<Raport> getRaporty() {
        return raporty;
    }

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
