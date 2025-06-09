package backend.modul;

import util.Ext;
import util.IdGenerator;

import java.util.List;

public class Towar extends Ext {
    private final static List<Long> takenIds = new java.util.ArrayList<>();

    private long id;
    private final float rozmiar;
    private ModulBazowy modulBazowy;

    public Towar(ModulBazowy modulBazowy, float rozmiar) {
        setId();
        this.rozmiar = rozmiar;
        setModul(modulBazowy);
    }

    private void setId() {
        long tmp = IdGenerator.genId();

        if(takenIds.contains(tmp)) {
            this.id = tmp + 1;
            takenIds.add(id);
        } else {
            this.id = tmp;
            takenIds.add(id);
        }
    }

    public void setModul(ModulBazowy modulBazowy) {
        if(!modulBazowy.getTypModulu().contains(TypModulu.PRZECHOWALNIA)) {
            throw new IllegalArgumentException("Modul nie posiada przechwalni");
        }

        if(modulBazowy.aktualnaPojemnosc() + this.rozmiar > modulBazowy.getPojemnoscPrzechowalni(modulBazowy)) {
            throw new IllegalArgumentException("Przechwowalnia jest pelna");
        }

        if(this.modulBazowy != modulBazowy) {
            this.modulBazowy = modulBazowy;
            modulBazowy.dodajTowar(this);
        }
    }

    public long getId() {
        return id;
    }

    public ModulBazowy getModulBazowy() {
        return modulBazowy;
    }

    public float getRozmiar() {
        return rozmiar;
    }

    @Override
    public String toString() {
        return "Towar{" +
                "id=" + id +
                ", rozmiar=" + rozmiar +
                ", modulBazowy=" + modulBazowy.getId() +
                '}';
    }

    @Override
    public int remove() {
        if(modulBazowy != null) {
            modulBazowy.usunTowar(this);
            modulBazowy = null;
        }

        return super.remove();
    }
}
