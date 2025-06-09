package backend.modul;

import util.Ext;
import util.IdGenerator;

import java.util.List;

/**
 * The type Towar.
 */
public class Towar extends Ext {
    private final static List<Long> takenIds = new java.util.ArrayList<>();

    private long id;
    private final float rozmiar;
    private ModulBazowy modulBazowy;

    /**
     * Instantiates a new Towar.
     *
     * @param modulBazowy the modul bazowy
     * @param rozmiar     the rozmiar
     */
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

    /**
     * Sets modul.
     *
     * @param modulBazowy the modul bazowy
     */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets modul bazowy.
     *
     * @return the modul bazowy
     */
    public ModulBazowy getModulBazowy() {
        return modulBazowy;
    }

    /**
     * Gets rozmiar.
     *
     * @return the rozmiar
     */
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
