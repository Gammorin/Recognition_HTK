/*
 * Klasa dostaje ścieżkę do folderu z plikami audio
 * Tworzy tam pliki z transkrypcją
 * Tworzy plik train.lists pod podaną ścieżką
 */
package automatyzacjahtk;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Morty
 */
public class StworzTrainListITranskrypcje {

    File[] filesSciezki;
    String[] tabNazwyPlikow;
    String[] transkrypcja;
    String[] nazwyTab_PLab;

    public StworzTrainListITranskrypcje(String sciezkaAudio, String sciezkaTrain, String sciezkaAudioMlf) {
        nazwyPlikow(sciezkaAudio);
        sciezkiPlikow(sciezkaAudio);
        transkrypcja = new String[filesSciezki.length];
        TworzPlikiTranskrypcji();
        TworzenieTrainList(sciezkaTrain);
        TworzenieAudioMlf(sciezkaAudioMlf);
    }

    private void sciezkiPlikow(String sciezka) {
        try {           
            filesSciezki = new File(sciezka).listFiles(new FileFilter() {

                @Override
                public boolean accept(File file) {
                    return file.getName().toLowerCase().endsWith(".wav");
                }
            });
            
            System.out.println("SCIEZKI PLIKOW");
            for (int fileInList = 0; fileInList < filesSciezki.length; fileInList++) {
                System.out.println(filesSciezki[fileInList].toString());
            }
        } catch (Exception e) {
        }
        //  String pozycja = files[0].getAbsolutePath();
        //System.out.println("co tam"+pozycja);
    }

    private void nazwyPlikow(String sciezka) {


        File[] filesNazwy = new File(sciezka).listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
               // return file.isFile();
                return file.getName().toLowerCase().endsWith(".wav");
            }
        });

        tabNazwyPlikow = new String[filesNazwy.length];
        for (int i = 0; i < filesNazwy.length; i++) {
            tabNazwyPlikow[i] = filesNazwy[i].getName();
        }

        System.out.println("********** Nazwy pliku z katalogu ::::::::::::::::::::");
        for (int i = 0; i < tabNazwyPlikow.length; i++) {
            System.out.println(tabNazwyPlikow[i]);
        }
    }//nazwyPlikow

    private void TworzPlikiTranskrypcji() {
        String nazwaPlikuLab;
        int index;
        nazwyTab_PLab = new String[filesSciezki.length];        //tworzenie tablicy w ktorej beda nazwy plikow .lab
        
        for (int i = 0; i < filesSciezki.length; i++) {
            nazwaPlikuLab = filesSciezki[i].toString();
            nazwaPlikuLab = nazwaPlikuLab.substring(0, nazwaPlikuLab.lastIndexOf(".")) + ".lab";
            nazwyTab_PLab[i] = nazwaPlikuLab;
            System.out.println(nazwaPlikuLab);            
            File plikList = new File(nazwaPlikuLab);
            try {
                boolean b = plikList.createNewFile();
                PrintWriter pw = new PrintWriter(plikList);
                if((index = tabNazwyPlikow[i].indexOf("_")) != -1){
                    pw.print(transkrypcja[i] = tabNazwyPlikow[i].substring(0, index));
                }else{
                    pw.print(tabNazwyPlikow[i]);
                }                
                pw.close();
            } catch (IOException ex) {
                System.out.println("blad przy tworzeniu pliku transkrypcji");
            }
        }
    }//TworzPlikiTranskrypcji
    
    private void TworzenieTrainList(String path){
        File plikTrain = new File(path);
            try {
                boolean b = plikTrain.createNewFile();
                PrintWriter pw = new PrintWriter(plikTrain);
                for (int i = 0; i < filesSciezki.length; i++) {
                    pw.println(filesSciezki[i].toString());
                }               
                pw.close();
            } catch (IOException ex) {
                System.out.println("blad przy tworzeniu pliku Train.list");
            }
    }
    
    private void TworzenieAudioMlf(String path){
        File plikAudioMLF = new File(path);
            try {
                boolean b = plikAudioMLF.createNewFile();
                PrintWriter pw = new PrintWriter(plikAudioMLF);
                pw.println("#!MLF!#");
                for (int i = 0; i < filesSciezki.length; i++) {
                    pw.println("\""+nazwyTab_PLab[i]+"\"");
                    pw.println(transkrypcja[i]);
                    pw.println(".");
                }               
                pw.close();
            } catch (IOException ex) {
                System.out.println("blad przy tworzeniu pliku Audio.mlf");
            }
    }
}
