package backend.zadanie;

import backend.Raport;
import backend.pracownik.Pracownik;
import util.Ext;
import util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Zadanie.
 */
public class Zadanie extends Ext {
    private long id;
    private StatusZadania statusZadania;
    private TypZadania typZadania;
    private final List<Raport> raporty = new ArrayList<>();

    /**
     * Instantiates a new Zadanie.
     *
     * @param typZadania the typ zadania
     */
    public Zadanie(TypZadania typZadania) {
        this.typZadania = typZadania;
        this.statusZadania = StatusZadania.DOSTEPNE;
        this.id = IdGenerator.genId();
        Raport.dodajZadanie(this);
    }

    /**
     * Sets status.
     *
     * @param statusZadania the status zadania
     */
    public void setStatus(StatusZadania statusZadania) {
        this.statusZadania = statusZadania;
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
     * Sets raport.
     *
     * @param raport the raport
     */
    public void setRaport(Raport raport) {
        this.raporty.add(raport);
    }

    /**
     * Sets typ zadania.
     *
     * @param typZadania the typ zadania
     */
    public void setTypZadania(TypZadania typZadania) {
        this.typZadania = typZadania;
    }

    /**
     * Gets typ zadania.
     *
     * @return the typ zadania
     */
    public TypZadania getTypZadania() {
        return typZadania;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public StatusZadania getStatus() {
        return statusZadania;
    }

    /**
     * Gets raporty.
     *
     * @return the raporty
     */
    public List<Raport> getRaporty() {
        return raporty;
    }

    /**
     * Gets all pracownicy.
     *
     * @return the all pracownicy
     */
    public List<Pracownik> getAllPracownicy() {
        List<Pracownik> wspolpracownicy = new ArrayList<>();
        for(Raport raport : raporty) {
            wspolpracownicy.add(raport.getPracownik());
        }

        return wspolpracownicy;
    }

    @Override
    public String toString() {
        return "Zadanie{" +
                "id=" + id +
                ", statusZadania=" + statusZadania +
                ", typZadania=" + typZadania +
                '}';
    }
}
