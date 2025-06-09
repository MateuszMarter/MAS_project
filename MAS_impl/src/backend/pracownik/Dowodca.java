package backend.pracownik;

import backend.Raport;
import backend.zadanie.StatusZadania;
import backend.zadanie.Zadanie;
import backend.Zaloga;

import java.time.LocalDateTime;
import java.util.List;

public class Dowodca extends Pracownik {

    private int iloscDowodzonychMisji;

    public Dowodca(String imie, String nazwisko, Zaloga zaloga) {
        super(imie, nazwisko, zaloga);
        this.iloscDowodzonychMisji = 0;
    }

    public Dowodca(Kadet kadet) {
        super(kadet.getImie(), kadet.getNazwisko(), kadet.getZaloga());
        this.iloscDowodzonychMisji = 0;
    }

    @Override
    public float obliczPensje() {
        return getPensjaPodstawowa() * (1 + (float)(iloscDowodzonychMisji/100.0));
    }

    public List<Pracownik> wyswietlListePracownikow() {
        List<Pracownik> pracownicy = getZaloga().getPracownicy();
        for(Pracownik pracownik : pracownicy) {
            System.out.println(pracownik.getImie() + " " + pracownik.getNazwisko());
        }

        return pracownicy;
    }

    public Pracownik danePracownika(double id) {
        Pracownik pracownik = getZaloga().getPracownicy().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if(pracownik == null) {
            throw new IllegalArgumentException("Nie ma takiego pracownika");
        }

        return pracownik;
    }

    public void wyswietlZadaniaPracownika(Pracownik pracownik) {
        pracownik.getRaporty().forEach(r -> {
            System.out.println(r.getZadanie());
        });
    }

    public void usunZadaniePracownika(Pracownik pracownik, Zadanie zadanie) {
        pracownik.getRaporty().removeIf(r -> r.getZadanie() == zadanie);
    }

    public void dodajZadaniePracownika(String nazwaZadania, Pracownik pracownik, Zadanie zadanie, LocalDateTime deadline) {
        new Raport(nazwaZadania, pracownik, zadanie, deadline);
        zadanie.setStatus(StatusZadania.NIEZREALIZOWANE);
    }

    public void edytujZadaniePracownika(Pracownik pracownik, Zadanie zadanie, Zadanie noweZadanie) {
        System.out.println(zadanie.getId() + " " + noweZadanie.getId());
        pracownik.getRaporty().forEach(r -> System.out.println(r.getZadanie().getId()));

        Raport raport = pracownik.getRaporty().stream()
                .filter(r -> r.getZadanie().getId() == zadanie.getId())
                .findFirst()
                .orElse(null);

        if(raport == null) {
            throw new IllegalArgumentException("Pracownik nie posiada zadania o takim tytule");
        }

        raport.setZadnie(noweZadanie);
    }

    /*@Override
    public String toString() {
        return "Dowodca{" +
                "id=" + super.getId() +
                ", imie='" + super.getImie() + '\'' +
                ", nazwisko='" + super.getNazwisko() + '\'' +
                ", zaloga=" + super.getZaloga().getId() +
                ", iloscDowodzonychMisji=" + iloscDowodzonychMisji +
                '}';
    }*/

    @Override
    public String toString() {
        return super.getImie() + ' ' + super.getNazwisko();
    }
}
