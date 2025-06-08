package backend.pojazd;

import util.Ext;

public class Pojazd extends Ext {
    private float stanPaliwa;
    private float maxStanPaliwa;

    private Cargo cargo;
    private Transport transport;

    public Pojazd(float stanPaliwa, float maxStanPaliwa) {
        init(maxStanPaliwa, stanPaliwa);
    }

    private void init(float maxStanPaliwa, float stanPaliwa) {
        this.maxStanPaliwa = maxStanPaliwa;

        setStanPaliwa(stanPaliwa);
    }

    public void setStanPaliwa(float stanPaliwa) {
        if(stanPaliwa < 1) {
            throw new IllegalArgumentException("Stan paliwa musi byc wiekszy od 0");
        }

        if(stanPaliwa > maxStanPaliwa) {
            throw new IllegalArgumentException("Stan paliwa nie moze przekraczac maxStanPaliwa");
        }

        this.stanPaliwa = stanPaliwa;
    }

    public void setMaxStanPaliwa(float maxStanPaliwa) {
        if(maxStanPaliwa < 1) {
            throw new IllegalArgumentException("Max stan paliwa musi byc wiekszy od 0");
        }

        if(maxStanPaliwa < stanPaliwa) {
            throw new IllegalArgumentException("Max stan paliwa nie moze mniejszy od aktualnego stan paliwa");
        }

        this.maxStanPaliwa = maxStanPaliwa;
    }
}
