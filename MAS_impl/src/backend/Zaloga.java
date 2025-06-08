package backend;

import backend.pracownik.Pracownik;
import util.Ext;
import util.IdGenerator;

import java.util.List;

public class Zaloga extends Ext {
    private int id;
    private List<Pracownik> zaloga;
    private List<Pracownik> zalogaNocna;
    private List<Pracownik> zalogaDzienna;
    private Stacja stacja;

    public Zaloga() {
        this.id = Integer.parseInt(IdGenerator.genId());
    }

    public void setStacja(Stacja stacja) {
        this.stacja = stacja;

        if(stacja.getZaloga() != null && stacja.getZaloga().getId() != getId()) {
            stacja.setZaloga(this);
        }
    }

    private int getId() {
        return this.id;
    }

    public void addZalogaDzienna(Pracownik pracownik) {
        if(zalogaDzienna.contains(pracownik)) {
            throw new IllegalArgumentException("Pracownik juz w zalodze");
        }

        if(zalogaNocna.contains(pracownik)) {
            throw new IllegalArgumentException("Pracownik juz w zalodze nocnej");
        }

        zalogaDzienna.add(pracownik);

        if(!zaloga.contains(pracownik)) {
            zaloga.add(pracownik);
        }
    }

    public void addZalogaNocna(Pracownik pracownik) {
        if(zalogaDzienna.contains(pracownik)) {
            throw new IllegalArgumentException("Pracownik juz w zalodze dziennej");
        }

        if(zalogaNocna.contains(pracownik)) {
            throw new IllegalArgumentException("Pracownik juz w zalodze ");
        }

        zalogaNocna.add(pracownik);

        if(!zaloga.contains(pracownik)) {
            zaloga.add(pracownik);
        }
    }

    public void removePracownik(Pracownik pracownik) {
        if(!zalogaDzienna.contains(pracownik)) {
            throw new IllegalArgumentException("Pracownik nie istnieje");
        }

        zaloga.remove(pracownik);

        zalogaDzienna.remove(pracownik);
        zalogaNocna.remove(pracownik);
    }

    public void usunZDziennej(Pracownik pracownik) {
        zalogaDzienna.remove(pracownik);
    }

    public void usunaNocnej(Pracownik pracownik) {
        zalogaNocna.remove(pracownik);
    }

}
