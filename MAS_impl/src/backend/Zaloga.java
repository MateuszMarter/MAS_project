package backend;

import backend.pracownik.Pracownik;
import util.Ext;
import util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Zaloga.
 */
public class Zaloga extends Ext {
    private long id;
    private final List<Pracownik> zaloga = new ArrayList<>();
    private final List<Pracownik> zalogaNocna = new ArrayList<>();
    private final List<Pracownik> zalogaDzienna = new ArrayList<>();
    private Stacja stacja;

    /**
     * Instantiates a new Zaloga.
     *
     * @param stacja the stacja
     */
    public Zaloga(Stacja stacja) {
        this.id = IdGenerator.genId();
        this.stacja = stacja;
    }

    /**
     * Dodaj pracownika.
     *
     * @param pracownik the pracownik
     */
    public void dodajPracownika(Pracownik pracownik) {
        if(zaloga.contains(pracownik)) {
            throw new IllegalArgumentException("Pracownik juz jest w zalodze");
        }

        zaloga.add(pracownik);
    }

    /**
     * Dodaj pracownika dzienna.
     *
     * @param pracownik the pracownik
     */
    public void dodajPracownikaDzienna(Pracownik pracownik) {
        dodajPracownika(pracownik);
        zalogaDzienna.add(pracownik);
    }

    /**
     * Dodaj pracownika nocna.
     *
     * @param pracownik the pracownik
     */
    public void dodajPracownikaNocna(Pracownik pracownik) {
        dodajPracownika(pracownik);
        zalogaNocna.add(pracownik);
    }

    /**
     * Usun pracownika.
     *
     * @param pracownik the pracownik
     */
    public void usunPracownika(Pracownik pracownik) {
        if(zaloga.contains(pracownik)) {
            zaloga.remove(pracownik);
            zalogaNocna.remove(pracownik);
            zalogaDzienna.remove(pracownik);

        }
    }


    /**
     * Usun stacja.
     */
    public void usunStacja() {
        Stacja tmp = this.stacja;
        stacja = null;
        tmp.remove();
    }

    /**
     * Gets pracownicy.
     *
     * @return the pracownicy
     */
    public List<Pracownik> getPracownicy() {
        return zaloga;
    }

    /**
     * Gets pracownicy dzienne.
     *
     * @return the pracownicy dzienne
     */
    public List<Pracownik> getPracownicyDzienne() {
        return zalogaDzienna;
    }

    /**
     * Gets pracownicy nocne.
     *
     * @return the pracownicy nocne
     */
    public List<Pracownik> getPracownicyNocne() {
        return zalogaNocna;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    @Override
    public int remove() {
        if(stacja != null && stacja.getZaloga() == this) {
            usunStacja();
        }
        List<Pracownik> tmp = new ArrayList<>(zaloga);
        zaloga.clear();
        zalogaDzienna.clear();
        zalogaNocna.clear();

        for(Pracownik pracownik : tmp) {
            pracownik.remove();
        }

        return super.remove();
    }

    @Override
    public String toString() {
        return "Zaloga{" +
                "id=" + id +
                ", zaloga=" + zaloga +
                ", zalogaNocna=" + zalogaNocna +
                ", zalogaDzienna=" + zalogaDzienna +
                ", stacja=" + stacja.getName() +
                "}";
    }
}
