package backend;

import backend.modul.ModulBazowy;
import util.Ext;
import util.IdGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Stacja extends Ext {
    private final static Set<String> zajeteNazwy = new HashSet<>();

    private int id;
    private String lokalizacja;
    private String nazwa;
    private Zaloga zaloga;

    private List<ModulBazowy> moduly;

    public Stacja(String lokalizacja, String nazwa, List<ModulBazowy> moduly, Zaloga zaloga) {
        this.id = Integer.parseInt(IdGenerator.genId());
        setNazwa(nazwa);
        setLokalizacja(lokalizacja);
        dodajModuly(moduly);
        setZaloga(zaloga);
    }

    public Stacja(String lokalizacja, String nazwa, ModulBazowy modul, Zaloga zaloga) {
        this.id = Integer.parseInt(IdGenerator.genId());
        setNazwa(nazwa);
        setLokalizacja(lokalizacja);
        dodajModul(modul);
        setZaloga(zaloga);
    }

    public void setLokalizacja(String lokalizacja) {
        this.lokalizacja = lokalizacja;
    }

    public void setNazwa(String nazwa) {
        if(zajeteNazwy.contains(nazwa.toLowerCase())) {
            throw new IllegalArgumentException("Nazwa stacji już istnieje");
        }

        this.nazwa = nazwa;
        zajeteNazwy.add(nazwa.toLowerCase());

    }

    public void dodajModul(ModulBazowy modul) {
        moduly.add(modul);
        modul.setStacja(this);
    }

    public void dodajModuly(List<ModulBazowy> moduly) {
        if(moduly.isEmpty()) {
            throw new IllegalArgumentException("Musi zawierac conajmniej jeden modul");
        }

        this.moduly = moduly;

        for(ModulBazowy modul : moduly) {
            modul.setStacja(this);
        }
    }

    public void usunModul(ModulBazowy modul) {
        if(!moduly.contains(modul)) {
            throw new IllegalArgumentException("Nazwa stacji ju<UNK> istnieje");
        }

        if(moduly.size() < 2) {
            throw new IllegalStateException("Musi byc conajmniej jeden modul");
        }

        moduly.remove(modul);
        if(modul.getStacja().getId() == getId() ) {
            modul.removeStacja(this);
        }
    }

    public int getId() {
        return this.id;
    }


    public boolean hasModule(ModulBazowy modul) {
        return moduly.contains(modul);
    }

    public Zaloga getZaloga() {
        return zaloga;
    }

    public void setZaloga(Zaloga zaloga) {
        this.zaloga = zaloga;
    }
}
