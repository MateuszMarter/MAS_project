package backend;

import backend.pracownik.Pracownik;
import util.Ext;
import util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Zaloga extends Ext {
    private int id;
    private List<Pracownik> zaloga;
    private List<Pracownik> zalogaNocna;
    private List<Pracownik> zalogaDzienna;
    private Stacja stacja;

    public Zaloga(Stacja stacja) {
        this.zaloga = new ArrayList<>();
        this.zalogaNocna = new ArrayList<>();
        this.zalogaDzienna = new ArrayList<>();

        this.id = Integer.parseInt(IdGenerator.genId());
        this.stacja = stacja;
    }

    private void dodajPracownika(Pracownik pracownik) {
        if(zaloga.contains(pracownik)) {
            throw new IllegalArgumentException("Pracownik juz jest w zalodze");
        }
    }

    public void dodajPracownikaDzienna(Pracownik pracownik) {
        dodajPracownika(pracownik);
        zalogaDzienna.add(pracownik);
    }

    public void dodajPracownikaNocna(Pracownik pracownik) {
        dodajPracownika(pracownik);
        zalogaNocna.add(pracownik);
    }

    public void usunPracownika(Pracownik pracownik) {
        zaloga.remove(pracownik);
        zalogaNocna.remove(pracownik);
        zalogaDzienna.remove(pracownik);
    }


    public void usunStacja() {
        Stacja tmp = this.stacja;
        stacja = null;
        tmp.remove();
    }

    @Override
    public int remove() {
        if(stacja.getZaloga() == this) {
            usunStacja();
        }

        return super.remove();
    }
}
