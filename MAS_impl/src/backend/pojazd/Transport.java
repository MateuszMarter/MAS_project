package backend.pojazd;

import backend.modul.ModulBazowy;
import util.Ext;

/**
 * The type Transport.
 */
public class Transport extends Pojazd {
    private int maxIloscMiejsc;

    /**
     * Instantiates a new Transport.
     *
     * @param stanPaliwa     the stan paliwa
     * @param maxStanPaliwa  the max stan paliwa
     * @param maxIloscMiejsc the max ilosc miejsc
     * @param modulBazowy    the modul bazowy
     */
    public Transport(float stanPaliwa, float maxStanPaliwa, int maxIloscMiejsc, ModulBazowy modulBazowy) {
        super(stanPaliwa, maxStanPaliwa, modulBazowy);
        setMaxIloscMiejsc(maxIloscMiejsc);
    }

    /**
     * Sets max ilosc miejsc.
     *
     * @param maxIloscMiejsc the max ilosc miejsc
     */
    public void setMaxIloscMiejsc(int maxIloscMiejsc) {
        if(maxIloscMiejsc <= 0) {
            throw new IllegalArgumentException("Ilosc miejsc musi byc wieksza niz 0");
        }

        this.maxIloscMiejsc = maxIloscMiejsc;
    }

    /**
     * Oblicz zasieg float.
     *
     * @return the float
     */
    public float obliczZasieg() {
        return (float) (maxIloscMiejsc * 0.1 / super.getStanPaliwa());
    }
}
