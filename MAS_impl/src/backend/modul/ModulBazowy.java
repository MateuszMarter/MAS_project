package backend.modul;

import backend.Stacja;
import util.Ext;
import util.IdGenerator;

import java.util.EnumSet;

public class ModulBazowy extends Ext {
    private int id;
    private StatusModulu statusModulu;
    private Stacja stacja;

    public ModulBazowy(StatusModulu statusModulu) {
        this.id = Integer.parseInt(IdGenerator.genId());
        this.statusModulu = statusModulu;
    }

    public void setStacja(Stacja stacja) {
        this.stacja = stacja;

        if(!stacja.hasModule(this)) {
            stacja.dodajModul(this);
        }
    }

    public void removeStacja(Stacja stacja) {
        this.stacja = null;

        if(!stacja.hasModule(this)) {
            stacja.usunModul(this);
        }
    }

    public Stacja getStacja() {
        return stacja;
    }
}
