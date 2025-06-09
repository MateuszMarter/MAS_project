package backend.zadanie;

import backend.Raport;
import util.Ext;
import util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Zadanie extends Ext {
    private long id;
    private StatusZadania statusZadania;
    private TypZadania typZadania;
    private final List<Raport> raporty = new ArrayList<>();

    public Zadanie(TypZadania typZadania) {
        this.typZadania = typZadania;
        this.statusZadania = StatusZadania.DOSTEPNE;
        this.id = IdGenerator.genId();
        Raport.dodajZadanie(this);
    }

    public void setStatus(StatusZadania statusZadania) {
        this.statusZadania = statusZadania;
    }

    public long getId() {
        return id;
    }

    public void setRaport(Raport raport) {
        this.raporty.add(raport);
    }


    public void setTypZadania(TypZadania typZadania) {
        this.typZadania = typZadania;
    }

    public TypZadania getTypZadania() {
        return typZadania;
    }

    public StatusZadania getStatus() {
        return statusZadania;
    }

    public Raport getRaport() {
        return raporty.get(raporty.size() - 1);
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
