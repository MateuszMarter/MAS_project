package util;

import backend.Raport;
import backend.Stacja;
import backend.Zaloga;
import backend.modul.ModulBazowy;
import backend.modul.StatusModulu;
import backend.pracownik.Dowodca;
import backend.pracownik.Kadet;
import backend.zadanie.TypZadania;
import backend.zadanie.Zadanie;

import java.time.LocalDateTime;

public class Init {
    public static void init() {
        System.out.println("Init");

        System.out.println("Stacja:");
        Stacja s1 = new Stacja("S1", "Jerozolimskie, Warszawa");

        System.out.println("\nModuly:");
        ModulBazowy m1 = ModulBazowy.stworzModul(StatusModulu.OPERACYJNA, s1);
        ModulBazowy m2 = ModulBazowy.stworzModul(StatusModulu.OPERACYJNA, s1);
        ModulBazowy m3 = ModulBazowy.stworzModul(StatusModulu.OPERACYJNA, s1);

        ModulBazowy m4 = ModulBazowy.stworzHangar(StatusModulu.OPERACYJNA, s1, 25);
        ModulBazowy m5 = ModulBazowy.stworzPrzechowalnie(StatusModulu.OPERACYJNA, s1, 100);
        ModulBazowy m6 = ModulBazowy.stworzModulMieszany(StatusModulu.OPERACYJNA, s1, 20, 60);

        System.out.println("\nZaloga");
        Zaloga z1 = s1.getZaloga();
        Kadet k1 = new Kadet("Jan", "Nowak", z1);
        Kadet k2 = new Kadet("Marek", "Kowalski", z1);
        Kadet k3 = new Kadet("Mateusz", "Wisniewski", z1);
        Dowodca dowodca = new Dowodca("Mateusz", "Marter", z1);

        System.out.println("\nZadania");
        Zadanie zadanie1 = new Zadanie(TypZadania.MECHANICZNE);
        Zadanie zadanie2 = new Zadanie(TypZadania.GOSPODARCZE);
        Zadanie zadanie3 = new Zadanie(TypZadania.OGOLNE);
        Zadanie zadanie4 = new Zadanie(TypZadania.MECHANICZNE);
        Zadanie zadanie5 = new Zadanie(TypZadania.GOSPODARCZE);
        Zadanie zadanie6 = new Zadanie(TypZadania.OGOLNE);
        Zadanie zadanie7 = new Zadanie(TypZadania.MECHANICZNE);
        Zadanie zadanie8 = new Zadanie(TypZadania.GOSPODARCZE);
        Zadanie zadanie9 = new Zadanie(TypZadania.OGOLNE);
        Zadanie zadanie10 = new Zadanie(TypZadania.MECHANICZNE);
        Zadanie zadanie11 = new Zadanie(TypZadania.GOSPODARCZE);
        Zadanie zadanie12 = new Zadanie(TypZadania.OGOLNE);
        Zadanie zadanie13 = new Zadanie(TypZadania.MECHANICZNE);
        Zadanie zadanie14 = new Zadanie(TypZadania.GOSPODARCZE);
        Zadanie zadanie15 = new Zadanie(TypZadania.OGOLNE);

        System.out.println("Raporty");
        Raport raport1 = new Raport("Zadanie 1", k1, zadanie1, LocalDateTime.now().plusDays(1));
        Raport raport2 = new Raport("Zadanie 2", k2, zadanie2, LocalDateTime.now().plusDays(2));
        Raport raport3 = new Raport("Zadanie 3", k3, zadanie3, LocalDateTime.now().plusDays(3));
        Raport raport4 = new Raport("Zadanie 4", dowodca, zadanie4, LocalDateTime.now().plusDays(4));
        Raport raport5 = new Raport("Zadanie 5", k1, zadanie5, LocalDateTime.now().plusDays(5));
        Raport raport6 = new Raport("Zadanie 6", k2, zadanie6, LocalDateTime.now().plusDays(6));
        Raport raport7 = new Raport("Zadanie 7", k3, zadanie7, LocalDateTime.now().plusDays(7));
        Raport raport8 = new Raport("Zadanie 8", dowodca, zadanie8, LocalDateTime.now().plusDays(8));
        Raport raport9 = new Raport("Zadanie 9", k1, zadanie9, LocalDateTime.now().plusDays(9));
        Raport raport10 = new Raport("Zadanie 10", k2, zadanie4, LocalDateTime.now().plusDays(4));

    }
}
