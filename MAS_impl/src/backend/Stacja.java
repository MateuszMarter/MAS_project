package backend;

import backend.modul.ModulBazowy;
import util.Ext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Stacja extends Ext {
    private final static Set<String> zajeteNazwy = new HashSet<>();

    private int id;
    private String lokalizacja;
    private String nazwa;

    private List<ModulBazowy> moduly;

    public Stacja(String lokalizacja, String nazwa, List<ModulBazowy> moduly) {
        setNazwa(nazwa);
    }

    public Stacja(String lokalizacja, String nazwa, ModulBazowy modul) {

    }

    public void setLokalizacja(String lokalizacja) {
        this.lokalizacja = lokalizacja;
    }

    public void setNazwa(String nazwa) {
        if(zajeteNazwy.contains(nazwa)) {
            throw new IllegalArgumentException("Nazwa stacji już istnieje");
        }

    }

    public void dodajModul(ModulBazowy modul) {

    }

    public void usunModul(ModulBazowy modul) {

    }

    public int getId() {
        return this.id;
    }


}
