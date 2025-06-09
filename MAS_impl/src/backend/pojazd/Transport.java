package backend.pojazd;

import backend.modul.ModulBazowy;
import util.Ext;

public class Transport extends Pojazd {
    private int maxIloscMiejsc;
    public Transport(float stanPaliwa, float maxStanPaliwa, int maxIloscMiejsc, ModulBazowy modulBazowy) {
        super(stanPaliwa, maxStanPaliwa, modulBazowy);
        setMaxIloscMiejsc(maxIloscMiejsc);
    }

    public void setMaxIloscMiejsc(int maxIloscMiejsc) {
        if(maxIloscMiejsc <= 0) {
            throw new IllegalArgumentException("Ilosc miejsc musi byc wieksza niz 0");
        }

        this.maxIloscMiejsc = maxIloscMiejsc;
    }

    public float obliczZasieg() {
        return (float) (maxIloscMiejsc * 0.1 / super.getStanPaliwa());
    }
}
