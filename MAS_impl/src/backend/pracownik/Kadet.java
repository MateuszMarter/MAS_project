package backend.pracownik;

import backend.Zaloga;

import java.util.ArrayList;
import java.util.List;

public class Kadet extends Pracownik {
    private final List<Integer> oceny = new ArrayList<>();

    public Kadet(String imie, String nazwisko, Zaloga zaloga) {
        super(imie, nazwisko, zaloga);
    }

    public void dodajOcene(int ocena) {
        oceny.add(ocena);
    }

    public float obliczSrednia() {
        float sum = 0;
        for(int ocena : oceny) {
            sum += ocena;
        }

        return  sum / oceny.size();
    }

    public Dowodca promocja() {
        return new Dowodca(this);
    }

    @Override
    public float obliczPensje() {
        return getPensjaPodstawowa() * (1 + (float)(obliczSrednia()/100.0));
    }


    @Override
    public String toString() {
        return "Kadet{" +
                "id=" + super.getId() +
                ", imie='" + super.getImie() + '\'' +
                ", nazwisko='" + super.getNazwisko() + '\'' +
                ", zaloga=" + super.getZaloga().getId() +
                ", oceny=" + oceny +
                ", srednia=" + obliczSrednia() +
                '}';
    }
}
