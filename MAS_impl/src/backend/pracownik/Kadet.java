package backend.pracownik;

import backend.Zaloga;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Kadet.
 */
public class Kadet extends Pracownik {
    private final List<Integer> oceny = new ArrayList<>();

    /**
     * Instantiates a new Kadet.
     *
     * @param imie     the imie
     * @param nazwisko the nazwisko
     * @param zaloga   the zaloga
     */
    public Kadet(String imie, String nazwisko, Zaloga zaloga) {
        super(imie, nazwisko, zaloga);
    }

    /**
     * Dodaj ocene.
     *
     * @param ocena the ocena
     */
    public void dodajOcene(int ocena) {
        oceny.add(ocena);
    }

    /**
     * Oblicz srednia float.
     *
     * @return the float
     */
    public float obliczSrednia() {
        if(oceny.isEmpty()) {
            return 0;
        }

        float sum = 0;
        for(int ocena : oceny) {
            sum += ocena;
        }

        return  sum / oceny.size();
    }

    /**
     * Promocja dowodca.
     *
     * @return the dowodca
     */
    public Dowodca promocja() {
        Dowodca dowodca = new Dowodca(this);
        super.remove();

        return dowodca;
    }

    @Override
    public float obliczPensje() {
        return getPensjaPodstawowa() * (1 + (float)(obliczSrednia()/100.0));
    }


    @Override
    public String toString() {
        return super.getImie() + ' ' + super.getNazwisko();
    }
}
