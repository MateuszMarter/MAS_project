package backend;

import backend.modul.ModulBazowy;
import backend.modul.StatusModulu;
import util.Ext;
import util.IdGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Stacja extends Ext {
    private final static Set<String> zajeteNazwy = new HashSet<>();

    private int id;
    private String lokalizacja;
    private String nazwa;
    private Zaloga zaloga;

    private final List<ModulBazowy> moduly = new ArrayList<>();

    public Stacja(String nazwa, String lokalizacja, ModulBazowy modul) {
        setNazwa(nazwa);
        setLokalizacja(lokalizacja);
        dodajModul(modul);
        this.id = Integer.parseInt(IdGenerator.genId());
        this.zaloga = new Zaloga(this);
    }

    public Stacja(String nazwa, String lokalizacja, List<ModulBazowy> moduly) {
        setNazwa(nazwa);
        setLokalizacja(lokalizacja);
        dodajModuly(moduly);
        this.id = Integer.parseInt(IdGenerator.genId());
        this.zaloga = new Zaloga(this);
    }

    public Stacja(String nazwa, String lokalizacja) {
        setNazwa(nazwa);
        setLokalizacja(lokalizacja);
        this.id = Integer.parseInt(IdGenerator.genId());
        dodajModul(new ModulBazowy(StatusModulu.OPERACYJNA));
        this.zaloga = new Zaloga(this);
    }

    public void setNazwa(String nazwa) {
        if(zajeteNazwy.contains(nazwa.toLowerCase())) {
            throw new IllegalArgumentException("Nazwa jest juz zajeta");
        }

        this.nazwa = nazwa;
        zajeteNazwy.add(nazwa.toLowerCase());
    }

    public void setLokalizacja(String lokalizacja) {
        String[] split = lokalizacja.split(",");
        if(split.length != 2) {
            throw new IllegalArgumentException("Nieprawidlowa lokalizacja");
        }

        this.lokalizacja = lokalizacja;
    }

    public void dodajModul(ModulBazowy modul) {
        moduly.add(modul);
        modul.dodajStacje(this);
    }


    public boolean hasModule(ModulBazowy modulBazowy) {
        return moduly.contains(modulBazowy);
    }

    public void dodajModuly(List<ModulBazowy> moduly) {
        if(moduly.isEmpty()) {
            throw new IllegalArgumentException("Musi być conajmniej jeden modul");
        }

        this.moduly.addAll(moduly);
    }

    public void usunModul(ModulBazowy modul) {
        if(moduly.size() < 2) {
            throw new IllegalArgumentException("Nie mozna usunac modulu z stacji z jednym modulem");
        }

        moduly.remove(modul);
        modul.remove();
    }

    private void usunZaloga() {
        Zaloga zaloga = this.zaloga;

        if(zaloga != null) {
            this.zaloga = null;
            zaloga.usunStacja();
            zaloga.remove();
        }
    }

    public Zaloga getZaloga() {
        return zaloga;
    }

    @Override
    public int remove() {
        for(ModulBazowy modul : moduly) {
            ModulBazowy tmp = modul;

            moduly.remove(modul);

            tmp.removeStacja(this);
            tmp.remove();
        }

        usunZaloga();

        return super.remove();
    }

}
