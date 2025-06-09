package backend.pojazd;

import backend.modul.ModulBazowy;
import util.Ext;

/**
 * The type Cargo.
 */
public class Cargo extends Pojazd {
    private static final float MAX_ZASIEG = 1000000000;

    private float maxWaga;

    /**
     * Instantiates a new Cargo.
     *
     * @param maxWaga       the max waga
     * @param stanPaliwa    the stan paliwa
     * @param maxStanPaliwa the max stan paliwa
     * @param modulBazowy   the modul bazowy
     */
    public Cargo(float maxWaga, float stanPaliwa, float maxStanPaliwa, ModulBazowy modulBazowy) {
        super(stanPaliwa, maxStanPaliwa, modulBazowy);
        this.maxWaga = maxWaga;
    }

    /**
     * Oblicz zasieg float.
     *
     * @return the float
     */
    public float obliczZasieg() {
        return (float) (maxWaga * 0.1 / super.getStanPaliwa());
    }
}
