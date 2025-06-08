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
        this.id = Long.parseLong(IdGenerator.genId());
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

    @Override
    public String toString() {
        return "Zadanie{" +
                "id=" + id +
                ", statusZadania=" + statusZadania +
                ", typZadania=" + typZadania +
                '}';
    }
}
