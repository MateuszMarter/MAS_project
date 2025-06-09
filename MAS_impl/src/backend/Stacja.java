package backend;

import backend.modul.ModulBazowy;
import backend.modul.StatusModulu;
import util.Ext;
import util.IdGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Stacja.
 */
public class Stacja extends Ext {
    private final static Set<String> zajeteNazwy = new HashSet<>();

    private long id;
    private String siedziba;
    private String nazwa;
    private Zaloga zaloga;

    private final List<ModulBazowy> moduly = new ArrayList<>();

    /**
     * Instantiates a new Stacja.
     *
     * @param nazwa       the nazwa
     * @param lokalizacja the lokalizacja
     */
    public Stacja(String nazwa, String lokalizacja) {
        setNazwa(nazwa);
        setSiedziba(lokalizacja);
        this.id = IdGenerator.genId();
        dodajModul(ModulBazowy.stworzModul(StatusModulu.OPERACYJNA, this));
        this.zaloga = new Zaloga(this);
    }

    /**
     * Sets nazwa.
     *
     * @param nazwa the nazwa
     */
    public void setNazwa(String nazwa) {
        if(zajeteNazwy.contains(nazwa.toLowerCase())) {
            throw new IllegalArgumentException("Nazwa jest juz zajeta");
        }

        this.nazwa = nazwa;
        zajeteNazwy.add(nazwa.toLowerCase());
    }

    /**
     * Sets siedziba.
     *
     * @param siedziba the siedziba
     */
    public void setSiedziba(String siedziba) {
        String[] split = siedziba.split(",");
        if(split.length != 2) {
            throw new IllegalArgumentException("Nieprawidlowa lokalizacja");
        }

        this.siedziba = siedziba;
    }

    /**
     * Dodaj modul.
     *
     * @param modul the modul
     */
    public void dodajModul(ModulBazowy modul) {
        moduly.add(modul);
        modul.dodajStacje(this);
    }


    /**
     * Has module boolean.
     *
     * @param modulBazowy the modul bazowy
     * @return the boolean
     */
    public boolean hasModule(ModulBazowy modulBazowy) {
        return moduly.contains(modulBazowy);
    }

    /**
     * Dodaj moduly.
     *
     * @param moduly the moduly
     */
    public void dodajModuly(List<ModulBazowy> moduly) {
        if(moduly.isEmpty()) {
            throw new IllegalArgumentException("Musi być conajmniej jeden modul");
        }

        this.moduly.addAll(moduly);
    }

    /**
     * Usun modul.
     *
     * @param modul the modul
     */
    public void usunModul(ModulBazowy modul) {
        if(moduly.contains(modul)) {
            if(moduly.size() < 2) {
                throw new IllegalArgumentException("Nie mozna usunac modulu z stacji z jednym modulem");
            }

            moduly.remove(modul);
            modul.remove();
        }
    }

    private void usunZaloga() {
        Zaloga zaloga = this.zaloga;

        if(zaloga != null) {
            this.zaloga = null;
            zaloga.usunStacja();
            zaloga.remove();
        }
    }

    /**
     * Gets zaloga.
     *
     * @return the zaloga
     */
    public Zaloga getZaloga() {
        return zaloga;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return nazwa;
    }

    @Override
    public int remove() {
        List<ModulBazowy> tmp = new ArrayList<>(moduly);
        moduly.clear();

        for(ModulBazowy modul : tmp) {
            modul.remove();
        }

        usunZaloga();

        return super.remove();
    }

    @Override
    public String toString() {
        return "Stacja{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", siedziba='" + siedziba + '\'' +
                ", moduly=" + moduly +
                ", zaloga=" + zaloga +
                "}";
    }

    /**
     * Gets moduly.
     *
     * @return the moduly
     */
    public List<ModulBazowy> getModuly() {
        return moduly;
    }
}
