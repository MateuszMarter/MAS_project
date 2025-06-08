package backend.modul;

import backend.Stacja;
import util.Ext;
import util.IdGenerator;


public class ModulBazowy extends Ext {
    private int id;
    private StatusModulu statusModulu;
    private Stacja stacja;

    public ModulBazowy(StatusModulu statusModulu) {
        this.statusModulu = statusModulu;
        this.id = Integer.parseInt(IdGenerator.genId());
    }

    public ModulBazowy(StatusModulu statusModulu, Stacja stacja) {
        this.statusModulu = statusModulu;
        this.id = Integer.parseInt(IdGenerator.genId());
        this.stacja = stacja;
    }


    public void dodajStacje(Stacja stacja) {
        this.stacja = stacja;
    }

    public void removeStacja(Stacja stacja) {
        if(this.stacja != stacja && stacja != null) {
            throw new IllegalArgumentException("Modul nalezy do innej stacji");
        }

        Stacja tmp = stacja;
        this.stacja = null;

        if(tmp.hasModule(this)) {
            tmp.usunModul(this);
        }
    }

    @Override
    public int remove() {
        removeStacja(stacja);

        return super.remove();
    }
}
