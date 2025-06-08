import backend.Raport;
import backend.pracownik.Pracownik;

import backend.modul.ModulBazowy;
import backend.modul.StatusModulu;
import backend.pracownik.Dowodca;
import backend.pracownik.Kadet;
import backend.zadanie.Zadanie;
import backend.zadanie.TypZadania;
import backend.Stacja;
import backend.Zaloga;
import util.Ext;

import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {


        try {
            Stacja s1 = new Stacja("S1", "Jerozolimskie, Warszawa");
            Stacja s2 = new Stacja("S2", "Jana Pawla, Warszawa");

            Zaloga z1 = s1.getZaloga();
            Zaloga z2 = s2.getZaloga();

            ModulBazowy m1 = ModulBazowy.stworzModul(StatusModulu.OPERACYJNA, s1);
            ModulBazowy m2 = ModulBazowy.stworzModul(StatusModulu.OPERACYJNA, s1);
            ModulBazowy m3 = ModulBazowy.stworzModul(StatusModulu.OPERACYJNA, s1);

            ModulBazowy m4 = ModulBazowy.stworzHangar(StatusModulu.OPERACYJNA, s2, 25);
            ModulBazowy m5 = ModulBazowy.stworzPrzechowalnie(StatusModulu.OPERACYJNA, s2, 100);
            ModulBazowy m6 = ModulBazowy.stworzModulMieszany(StatusModulu.OPERACYJNA, s2, 20, 60);

            System.out.println("====Stacje====");
            System.out.println(Ext.getExt(s1.getClass()));

            System.out.println("\n====Moduly====");
            //Przed usunieciem s1
            System.out.println(Ext.getExt(m1.getClass()));
            //Po ususnieciu s1
            s1.remove();
            System.out.println(Ext.getExt(m2.getClass()));

            System.out.println("\n====Zaloga i Pracownicy====");

            Kadet k1 = new Kadet("Jan", "Nowak", z2);
            Kadet k2 = new Kadet("Marek", "Kowalski", z2);
            Kadet k3 = new Kadet("Mateusz", "Wisniewski", z2);
            Dowodca dowodca = new Dowodca("Anna", "Kowalska", z2);

            k1.dodajOcene(5);
            k1.dodajOcene(3);
            k1.dodajOcene(2);
            k1.dodajOcene(5);
            k1.dodajOcene(2);
            System.out.println("Srednia k1: " + k1.obliczSrednia());

            //Zaloga z usunietej stacji
            System.out.println("Pracownicy z s1: " + z1.getPracownicy());

            //Zaloga z nie usunietej
            System.out.println("Pracownicy z s2: " + z2.getPracownicy());

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

            System.out.println("Status zadania 1: " + zadanie1.getStatus());

            Raport raport1 = new Raport("Zadanie 1", k1, zadanie1, LocalDateTime.now().plusDays(1));
            Raport raport2 = new Raport("Zadanie 2", k2, zadanie2, LocalDateTime.now().plusDays(2));
            Raport raport3 = new Raport("Zadanie 3", k3, zadanie3, LocalDateTime.now().plusDays(3));
            Raport raport4 = new Raport("Zadanie 4", dowodca, zadanie4, LocalDateTime.now().plusDays(4));
            Raport raport5 = new Raport("Zadanie 5", k1, zadanie5, LocalDateTime.now().plusDays(5));
            Raport raport6 = new Raport("Zadanie 6", k2, zadanie6, LocalDateTime.now().plusDays(6));
            Raport raport7 = new Raport("Zadanie 7", k3, zadanie7, LocalDateTime.now().plusDays(7));
            Raport raport8 = new Raport("Zadanie 8", dowodca, zadanie8, LocalDateTime.now().plusDays(8));
            Raport raport9 = new Raport("Zadanie 9", k1, zadanie9, LocalDateTime.now().plusDays(9));
            Raport raport10 = new Raport("Zadanie 10", k2, zadanie10, LocalDateTime.now().plusDays(10));
            Raport raport11 = new Raport("Zadanie 11", k3, zadanie11, LocalDateTime.now().plusDays(11));
            Raport raport12 = new Raport("Zadanie 12", dowodca, zadanie12, LocalDateTime.now().plusDays(12));
            Raport raport13 = new Raport("Zadanie 13", k1, zadanie13, LocalDateTime.now().plusDays(13));
            Raport raport14 = new Raport("Zadanie 14", k2, zadanie14, LocalDateTime.now().plusDays(14));
            Raport raport15 = new Raport("Zadanie 15", k3, zadanie15, LocalDateTime.now().plusDays(15));

            k1.wyswietlZadania();

            k1.wyswietlSwojeZadania();
            k1.porzucZadanie(zadanie1);

            System.out.println("Status zadania 1: " + zadanie1.getStatus());
            k1.wykonajZadanie(zadanie1);
            System.out.println("Status zadania 1: " + zadanie1.getStatus());

            System.out.println(k1.obliczPensje());
            Dowodca awansowany = k1.promocja();

            awansowany.wyswietlListePracownikow();
            Pracownik pracownik = awansowany.danePracownika(k1.getId());
            System.out.println(pracownik);

            awansowany.usunZadaniePracownika(k3, zadanie15);
            awansowany.wyswietlZadaniaPracownika(k3);

            System.out.println("====Hangar i Przechowalnia====");

            Ext.save();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}