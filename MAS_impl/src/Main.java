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

public class Main {
    public static void main(String[] args) {


        try {
            // Przygotowanie danych zależnych

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

            //Zaloga z usunietej stacji
            System.out.println(z1.getPracownicy());
            
            //Zaloga z nie usunietej
            System.out.println(z2.getPracownicy());

            Zadanie zadanie1 = new Zadanie(TypZadania.MECHANICZNE);
            Zadanie zadanie2 = new Zadanie(TypZadania.GOSPODARCZE);
            Zadanie zadanie3 = new Zadanie(TypZadania.OGOLNE);

            // Pracownik (Kadet) wybiera zadanie
            k1.wybierzZadanie(zadanie1, "Naprawa silnika", LocalDateTime.now().plusDays(1));
            k1.wybierzZadanie(zadanie2, "Sprzątanie pokładu", LocalDateTime.now().plusDays(2));
            System.out.println("--- Zadania Kadeta ---");
            k1.wyswietlZadania();

            // Porzucenie zadania
            k1.porzucZadanie(zadanie1);
            System.out.println("--- Po porzuceniu zadania ---");
            k1.wyswietlZadania();

            // Dowodca zarządza zadaniami pracownika
            dowodca.wyswietlListePracownikow();
            System.out.println("Dowódca odczytuje dane pracownika:");
            System.out.println(k1);
            Pracownik p = dowodca.danePracownika(k1.getId());
            System.out.println(p.getImie() + " " + p.getNazwisko());

            System.out.println("Dowódca przegląda zadania pracownika:");
            dowodca.wyswietlZadaniaPracownika(k1);

            System.out.println("Dowódca usuwa zadanie pracownika:");
            dowodca.usunZadaniePracownika(k1, zadanie2);

            System.out.println("Dowódca przypisuje nowe zadanie:");
            dowodca.dodajZadaniePracownika("Inspekcja", k1, zadanie3, LocalDateTime.now().plusDays(3));

            System.out.println("Dowódca edytuje zadanie pracownika:");
            dowodca.edytujZadaniePracownika(k1, zadanie3, zadanie1);

            System.out.println("--- Zadania Kadeta po edycji ---");
            k1.wyswietlZadania();

            // Test Kadet – oceny i pensja
            k1.dodajOcene(5);
            k1.dodajOcene(4);
            System.out.println("Średnia ocen kadeta: " + k1.obliczSrednia());
            System.out.println("Pensja kadeta: " + k1.obliczPensje());

            Dowodca awansowany = k1.promocja();
            System.out.println("Awansowany dowódca (z kadeta): Pensja = " + awansowany.obliczPensje());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}