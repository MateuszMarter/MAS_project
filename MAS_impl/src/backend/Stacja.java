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

    private long id;
    private String siedziba;
    private String nazwa;
    private Zaloga zaloga;

    private final List<ModulBazowy> moduly = new ArrayList<>();

    public Stacja(String nazwa, String lokalizacja) {
        setNazwa(nazwa);
        setSiedziba(lokalizacja);
        this.id = IdGenerator.genId();
        dodajModul(ModulBazowy.stworzModul(StatusModulu.OPERACYJNA, this));
        this.zaloga = new Zaloga(this);
    }

    public void setNazwa(String nazwa) {
        if(zajeteNazwy.contains(nazwa.toLowerCase())) {
            throw new IllegalArgumentException("Nazwa jest juz zajeta");
        }

        this.nazwa = nazwa;
        zajeteNazwy.add(nazwa.toLowerCase());
    }

    public void setSiedziba(String siedziba) {
        String[] split = siedziba.split(",");
        if(split.length != 2) {
            throw new IllegalArgumentException("Nieprawidlowa lokalizacja");
        }

        this.siedziba = siedziba;
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

    public Zaloga getZaloga() {
        return zaloga;
    }


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

    public List<ModulBazowy> getModuly() {
        return moduly;
    }
}
