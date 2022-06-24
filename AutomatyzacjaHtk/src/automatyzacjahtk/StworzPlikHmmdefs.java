//sciezka do proto, sciezka2 - folderu hmms
package automatyzacjahtk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morty
 */
public class StworzPlikHmmdefs {

    private String dane, model;
    private String hamen = "";
    private int iloscOsob;
    ;
    private String[] tab;

    public StworzPlikHmmdefs(String sciezkaDict, String sciezkaModele) {
        iloscOsob = zlicz(sciezkaDict);
        tab = new String[iloscOsob];
        tab = PobierzModel(sciezkaDict);
        for (int i = 0; i < iloscOsob; i++) {
            System.out.println(tab[i]);
        }
        try {
            for (int j = 0; j < iloscOsob; j++) {
                model = tab[j];
                dane = powielaj(sciezkaModele + "\\hmms\\hmm.0\\proto", model);
                hamen = hamen + dane + "\n" + "\n";

            }
            PrintWriter hmmdefs = new PrintWriter(sciezkaModele + "\\hmms\\hmm.0\\hmmdefs");
            hmmdefs.print(hamen);
            hmmdefs.close();
            System.out.println("Stworzono hmmdesf w katalogu");


        } catch (Exception e) {
            System.out.println("nie można stowrzyć pliku hmmmdefs !!!!!$$$$$$*********!!!");
        }

    }

    //funkcja w przyszlosci zlicza ilosc osob
    private int zlicz(String sciezka) {
        int licznik = 1;
        try {
            FileReader fr = new FileReader(sciezka);
            BufferedReader brLinii = new BufferedReader(fr);
            String n = brLinii.readLine();

            while (brLinii.ready()) {
                n = brLinii.readLine();
                licznik++;
            }
            brLinii.close();
            System.out.println("licznik linii pliku: " + licznik);
            brLinii.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("funkcaj zlicz nie działa >>>>>");
        }

        return licznik;
    }
    //powielanie pliku proto

    private String powielaj(String sciezka, String model) {
        int licz = 0;
        String LiniaRobocza = "";
        String linia = "";
        try {
            FileReader fr = new FileReader(sciezka);
            BufferedReader powielaj = new BufferedReader(fr);
            //String linia = powielaj.readLine();

            while (powielaj.ready()) {

                licz++;
                if (licz == 4) {
                    LiniaRobocza = LiniaRobocza + "\n" + "~h \"" + model + "\"";
                    powielaj.readLine();
                    continue;
                }
                linia = powielaj.readLine();
                if (licz == 1) {
                    LiniaRobocza = LiniaRobocza + linia;
                    continue;
                }
                LiniaRobocza = LiniaRobocza + "\n" + linia;

            }
            powielaj.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("powielanie nie udane");
        }

        return LiniaRobocza;

    }

    private String[] PobierzModel(String sciezka) {

        int licznik = 0;
        try {
            FileReader fr = new FileReader(sciezka);
            BufferedReader brLinii = new BufferedReader(fr);
            String linia = brLinii.readLine();
            int index = linia.lastIndexOf(" "); // Szukanie spacyji
            linia = linia.substring(index + 1);
            tab[licznik] = linia;
            while (brLinii.ready()) {

                linia = brLinii.readLine();
                ++licznik;
                index = linia.lastIndexOf(" "); // Szukanie spacyji
                linia = linia.substring(index + 1);
                tab[licznik] = linia;


            }
            brLinii.close();
            brLinii.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Błąd Tablicy Słownika");
        }

        return tab;
    }
}