package backend.pojazd;

import backend.modul.ModulBazowy;
import util.Ext;

public class Cargo extends Pojazd {
    private static final float MAX_ZASIEG = 1000000000;

    private float maxWaga;
    public Cargo(float maxWaga, float stanPaliwa, float maxStanPaliwa, ModulBazowy modulBazowy) {
        super(stanPaliwa, maxStanPaliwa, modulBazowy);
        this.maxWaga = maxWaga;
    }

    public float obliczZasieg() {
        return (float) (maxWaga * 0.1 / super.getStanPaliwa());
    }
}
