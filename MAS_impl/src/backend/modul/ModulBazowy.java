package backend.modul;

import backend.Stacja;
import backend.pojazd.Pojazd;
import util.Ext;
import util.IdGenerator;

import java.util.*;


public class ModulBazowy extends Ext {
    private long id;
    private StatusModulu statusModulu;
    private Stacja stacja;

    private EnumSet<TypModulu> typyModulu;

    private float pojemnoscPrzechowalni;
    private Map<Long, Towar> towary;

    private int pojemnoscHangaru;
    private List<Pojazd> pojazdy;

    public static ModulBazowy stworzModul(StatusModulu statusModulu, Stacja stacja) {
        ModulBazowy modul = new ModulBazowy(statusModulu, stacja, EnumSet.noneOf(TypModulu.class));

        return modul;
    }

    public static ModulBazowy stworzHangar(StatusModulu statusModulu, Stacja stacja, int maxPojemnosc) {
        ModulBazowy modul = new ModulBazowy(statusModulu, stacja, EnumSet.of(TypModulu.HANGAR));
        modul.initLists(modul);
        modul.setPojemnoscHangaru(maxPojemnosc);

        return modul;
    }

    public static ModulBazowy stworzPrzechowalnie(StatusModulu statusModulu, Stacja stacja, int maxPojemnosc) {
        ModulBazowy modul = new ModulBazowy(statusModulu, stacja, EnumSet.of(TypModulu.PRZECHOWALNIA));
        modul.initLists(modul);
        modul.setPojemnoscPrzechowalni(maxPojemnosc);

        return modul;
    }

    public static ModulBazowy stworzModulMieszany(StatusModulu statusModulu, Stacja stacja, int pojenoscHangaru, int pojemnoscPrzechowalni) {
        ModulBazowy modul = new ModulBazowy(statusModulu, stacja, EnumSet.of(TypModulu.HANGAR, TypModulu.PRZECHOWALNIA));
        modul.initLists(modul);
        modul.setPojemnoscHangaru(pojenoscHangaru);
        modul.setPojemnoscPrzechowalni(pojemnoscPrzechowalni);

        return modul;
    }

    private void initLists(ModulBazowy modulBazowy) {
        if(modulBazowy.typyModulu.contains(TypModulu.HANGAR)) {
            this.pojazdy = new ArrayList<>();
        }

        if(modulBazowy.typyModulu.contains(TypModulu.PRZECHOWALNIA)) {
            this.towary = new HashMap<>();
        }
    }

    private void setPojemnoscHangaru(int pojemnoscHangaru) {
        this.pojemnoscHangaru = pojemnoscHangaru;
    }

    private void setPojemnoscPrzechowalni(float pojemnoscPrzechowalni) {
        this.pojemnoscPrzechowalni = pojemnoscPrzechowalni;
    }


    private ModulBazowy(StatusModulu statusModulu, Stacja stacja, EnumSet<TypModulu> typModulu) {
        this.statusModulu = statusModulu;
        this.id = Long.parseLong(IdGenerator.genId());
        this.stacja = stacja;
        stacja.dodajModul(this);
        towary = new HashMap<>();
        this.typyModulu = typModulu;
    }

    public void dodajTowar(Towar towar) {
        if(!typyModulu.contains(TypModulu.PRZECHOWALNIA)) {
            throw new IllegalArgumentException("Modul nie posiada przechowalni");
        }

        if(towar.getModulBazowy() != this) {
            towar.setModul(this);
        }
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

    public EnumSet<TypModulu> getTypModulu() {
        return typyModulu;
    }

    public float aktualnaPojemnosc() {
        if(!typyModulu.contains(TypModulu.PRZECHOWALNIA)) {
            throw new IllegalArgumentException("Modul nie posiada przechowalni");
        }
        float sum = 0;
        List<Towar> t = (List<Towar>) towary.values();

        for(Towar towar : t) {
            sum += towar.getRozmiar();
        }

        return sum;
    }

    public float getPojemnoscPrzechowalni(ModulBazowy modulBazowy) {
        if(!modulBazowy.getTypModulu().contains(TypModulu.PRZECHOWALNIA)) {
            throw new IllegalArgumentException("Modul nie posiada przechwalni");
        }

        return pojemnoscHangaru;
    }

    @Override
    public int remove() {
        if(stacja != null) {
            stacja.usunModul(this);
            stacja = null;
        }

        return super.remove();
    }

    @Override
    public String toString() {
        if(typyModulu.contains(TypModulu.HANGAR)) {
            return "Hangar{" +
                    "id=" + id +
                    ", statusModulu=" + statusModulu +
                    ", stacja=" + stacja.getName() +
                    ", pojemnoscHangaru=" + pojemnoscHangaru +
                    ", pojazdy=" + pojazdy +
                    '}';
        }

        if(typyModulu.contains(TypModulu.PRZECHOWALNIA)) {
            return "Przechowalnia{" +
                    "id=" + id +
                    ", statusModulu=" + statusModulu +
                    ", stacja=" + stacja.getName() +
                    ", pojemnoscPrzechowalni=" + pojemnoscPrzechowalni +
                    ", towary=" + towary +
                    '}';
        }

        return "ModulBazowy{" +
                "id=" + id +
                ", statusModulu=" + statusModulu +
                ", stacja=" + stacja.getName() +
                ", typyModulu=" + typyModulu +
                "}";
    }

    public long getId() {
        return id;
    }
}
