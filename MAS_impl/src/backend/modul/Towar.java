package backend.modul;

import util.Ext;
import util.IdGenerator;

public class Towar extends Ext {
    private final long id;
    private final float rozmiar;
    private ModulBazowy modulBazowy;

    public Towar(ModulBazowy modulBazowy, float rozmiar) {
        this.id = IdGenerator.genId();
        this.rozmiar = rozmiar;
        setModul(modulBazowy);
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
}
