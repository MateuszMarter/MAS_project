package backend.modul;

import backend.Stacja;
import backend.pojazd.Pojazd;
import util.Ext;
import util.IdGenerator;

import java.util.*;


/**
 * The type Modul bazowy.
 */
public class ModulBazowy extends Ext {
    private long id;
    private StatusModulu statusModulu;
    private Stacja stacja;

    private EnumSet<TypModulu> typyModulu;

    private float pojemnoscPrzechowalni;
    private Map<Long, Towar> towary;

    private int pojemnoscHangaru;
    private List<Pojazd> pojazdy;

    /**
     * Stworz modul bazowy.
     *
     * @param statusModulu status modulu
     * @param stacja stacja
     * @return modul bazowy
     */
    public static ModulBazowy stworzModul(StatusModulu statusModulu, Stacja stacja) {
        ModulBazowy modul = new ModulBazowy(statusModulu, stacja, EnumSet.noneOf(TypModulu.class));

        return modul;
    }

    /**
     * Stworz hangar.
     *
     * @param statusModulu status modulu
     * @param stacja stacja
     * @param maxPojemnosc max pojemnosc
     * @return hangar
     */
    public static ModulBazowy stworzHangar(StatusModulu statusModulu, Stacja stacja, int maxPojemnosc) {
        ModulBazowy modul = new ModulBazowy(statusModulu, stacja, EnumSet.of(TypModulu.HANGAR));
        modul.initLists(modul);
        modul.setPojemnoscHangaru(maxPojemnosc);

        return modul;
    }

    /**
     * Stworz przechowalnie modul bazowy.
     *
     * @param statusModulu the status modulu
     * @param stacja the stacja
     * @param maxPojemnosc the max pojemnosc
     * @return przechowalnia
     */
    public static ModulBazowy stworzPrzechowalnie(StatusModulu statusModulu, Stacja stacja, int maxPojemnosc) {
        ModulBazowy modul = new ModulBazowy(statusModulu, stacja, EnumSet.of(TypModulu.PRZECHOWALNIA));
        modul.initLists(modul);
        modul.setPojemnoscPrzechowalni(maxPojemnosc);

        return modul;
    }

    /**
     * Stworz modul mieszany.
     *
     * @param statusModulu          status modulu
     * @param stacja                stacja
     * @param pojenoscHangaru       pojenosc hangaru
     * @param pojemnoscPrzechowalni pojemnosc przechowalni
     * @return modul mieszany
     */
    public static ModulBazowy stworzModulMieszany(StatusModulu statusModulu, Stacja stacja, int pojenoscHangaru, int pojemnoscPrzechowalni) {
        ModulBazowy modul = new ModulBazowy(statusModulu, stacja, EnumSet.of(TypModulu.HANGAR, TypModulu.PRZECHOWALNIA));
        modul.initLists(modul);
        modul.setPojemnoscHangaru(pojenoscHangaru);
        modul.setPojemnoscPrzechowalni(pojemnoscPrzechowalni);

        return modul;
    }

    private ModulBazowy(StatusModulu statusModulu, Stacja stacja, EnumSet<TypModulu> typModulu) {
        this.statusModulu = statusModulu;
        this.id = IdGenerator.genId();
        this.stacja = stacja;
        stacja.dodajModul(this);
        towary = new HashMap<>();
        this.typyModulu = typModulu;
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


    /**
     * Dodaj towar.
     *
     * @param towar towar
     */
    public void dodajTowar(Towar towar) {
        if(!typyModulu.contains(TypModulu.PRZECHOWALNIA)) {
            throw new IllegalArgumentException("Modul nie posiada przechowalni");
        }

        if(towar.getModulBazowy() != this) {
            towar.setModul(this);
        }

        towary.put(towar.getId(), towar);
        System.out.println(towary.values());
    }

    /**
     * Dodaj pojazd.
     *
     * @param pojazd pojazd
     */
    public void dodajPojazd(Pojazd pojazd) {
        if(!typyModulu.contains(TypModulu.HANGAR)) {
            throw new IllegalArgumentException("Modul nie posiada hangaru");
        }

        if(pojemnoscHangaru < pojazdy.size() + 1) {
            throw new IllegalArgumentException("Pojemnosc hangaru jest pelna");
        }

        if(pojazdy.contains(pojazd)) {
            throw new IllegalArgumentException("Hangar ma juz ten pojazd");
        }

        pojazdy.add(pojazd);
    }

    /**
     * Dodaj stacje.
     *
     * @param stacja stacja
     */
    public void dodajStacje(Stacja stacja) {
        this.stacja = stacja;
    }

    /**
     * Usun stacje.
     *
     * @param stacja stacja
     */
    public void usunStacje(Stacja stacja) {
        if(this.stacja != stacja && stacja != null) {
            throw new IllegalArgumentException("Modul nalezy do innej stacji");
        }

        Stacja tmp = stacja;
        this.stacja = null;

        if(tmp.hasModule(this)) {
            tmp.usunModul(this);
        }
    }


    /**
     * Usun pojazd.
     *
     * @param pojazd pojazd
     */
    public void usunPojazd(Pojazd pojazd) {
        if(pojazdy.contains(pojazd)){
            pojazdy.remove(pojazd);
            pojazd.remove();
        }
    }


    /**
     * Usun towar.
     *
     * @param towar towar
     */
    public void usunTowar(Towar towar) {
        if(towary.containsKey(towar.getId())) {
            towary.remove(towar.getId());
            towar.remove();
        }
    }

    /**
     * Gets typ modulu.
     *
     * @return typ modulu
     */
    public EnumSet<TypModulu> getTypModulu() {
        return typyModulu;
    }

    /**
     * Aktualna pojemnosc float.
     *
     * @return float
     */
    public float aktualnaPojemnosc() {
        if(!typyModulu.contains(TypModulu.PRZECHOWALNIA)) {
            throw new IllegalArgumentException("Modul nie posiada przechowalni");
        }
        float sum = 0;
        List<Towar> t = new ArrayList<>(towary.values());

        for(Towar towar : t) {
            sum += towar.getRozmiar();
        }

        return sum;
    }

    /**
     * Gets pojemnosc przechowalni.
     *
     * @param modulBazowy modul bazowy
     * @return pojemnosc przechowalni
     */
    public float getPojemnoscPrzechowalni(ModulBazowy modulBazowy) {
        if(!modulBazowy.getTypModulu().contains(TypModulu.PRZECHOWALNIA)) {
            throw new IllegalArgumentException("Modul nie posiada przechwalni");
        }

        return pojemnoscPrzechowalni;
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
     * Gets pojemnosc hangaru.
     *
     * @return zajeta pojemnosc hangaru
     */
    public int getPojemnoscHangaru() {
        return pojazdy.size();
    }

    @Override
    public int remove() {
        if(stacja != null) {
            stacja.usunModul(this);
            stacja = null;
        }

        if(typyModulu.contains(TypModulu.HANGAR)) {
            List<Pojazd> tmp = new ArrayList<>(pojazdy);
            pojazdy.clear();

            for(Pojazd pojazd : tmp) {
                pojazd.remove();
            }
        }

        if(typyModulu.contains(TypModulu.PRZECHOWALNIA)) {
            List<Towar> tmp = new ArrayList<>(towary.values());

            towary.clear();

            for(Towar towar : tmp) {
                towar.remove();
            }
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
}
