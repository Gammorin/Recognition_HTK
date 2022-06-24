/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automatyzacjahtk;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Morty
 */
public class Okno extends JFrame {

    private JTextField poleSciezkiConf;
    private ConfigFile2 plikConfig2;
    private JTextArea duzePoleTekstowe;

    public Okno() {
        setSize(600, 400);
        KonsolaCMD konsola = new KonsolaCMD();
        Container powZawartosci = getContentPane();
        JPanel panelGlowny = new JPanel();

        //adres domyślny u mnie : E:\htk

        JPanel panel2 = new JPanel();
        JButton przyc2 = new JButton("Podaj Config");
        poleSciezkiConf = new JTextField("", 35);
        przyc2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String sc;
                WyborPliku wybierz = new WyborPliku();
                sc = wybierz.getPath();
                //plikConfig = new ConfigFile(sc);
                plikConfig2 = new ConfigFile2(sc);
                poleSciezkiConf.setText(sc);

                //krok 1 
                StworzGrammar grammar = new StworzGrammar(plikConfig2.getGRAMMAR_Path());

                Hparse();

                Process p = null;

                //krok2
                StworzDict dict = new StworzDict(plikConfig2.getDICTONARY_Path());

                //pozostałe kroki są w klasie ConfigFile2
                //krok5
                //   System.out.println("^^^^^^^ tu blad:::: &&&&: "+plikConfig2.getTRAIN_LIST_Path());
                StworzTrainListITranskrypcje tr = new StworzTrainListITranskrypcje(plikConfig2.getROOT_DIR() + "\\" + "audio", plikConfig2.getTRAIN_LIST_Path(), plikConfig2.getMLF_Path());

                //krok6
                // System.out.println("hmmm= "+plikConfig2.getMODELS_Path());
                StworzFolderyHmm hm = new StworzFolderyHmm(plikConfig2.getMODELS_Path());

// magiczna stuczka:)
                for (int i = 0; i < 3; i++) {
                    HCompV();

                    StworzPlikHmmdefs hmdefs = new StworzPlikHmmdefs(plikConfig2.getDICTONARY_Path(), plikConfig2.getMODELS_Path());
                    HERest();
                    HVite();
                }
            }
        });
        panel2.add(przyc2);
        panel2.add(poleSciezkiConf);

        JPanel panel3 = new JPanel();
        duzePoleTekstowe = new JTextArea(20, 40);
        panel3.add(duzePoleTekstowe);

        panelGlowny.add(panel2);
        panelGlowny.add(panel3);

        powZawartosci.add(panelGlowny);

    }

    private void Hparse() {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("cmd /c " + plikConfig2.getROOT_DIR() + "\\HParse.exe "
                    + plikConfig2.getGRAMMAR_Path() + " "
                    + plikConfig2.getLATTICE_Path());
        } catch (IOException ex) {
            System.out.println("BŁĄD na kroku 1 !!!!");
        }
    }

    public void HCompV() {
        Process p = null;
        for (int i = 0; i < 10; i++) {
            try {
                p = Runtime.getRuntime().exec("cmd /c " + plikConfig2.getROOT_DIR() + "\\HCompV.exe " + "-C "
                        + plikConfig2.getAUDIOCONFIG_Path() + " -m -S "
                        + plikConfig2.getTRAIN_LIST_Path() + " -M "
                        + plikConfig2.getMODELS_Path() + "\\hmms\\hmm." + i + " " + plikConfig2.getPROTO_DEF_Path());
                System.out.println("proces się wykonał");
            } catch (IOException ex) {
                System.out.println("BŁĄD na kroku 1 !!!!");
            }
        }
    }

    private void HERest() {
        Process p = null;
        for (int i = 0; i < (plikConfig2.getTRAIN_STEPS() - 1); i++) {
            String k = null;
            try {
                p = Runtime.getRuntime().exec("cmd /c" + plikConfig2.getROOT_DIR() + "\\HERest.exe -T 1 -C "
                        + plikConfig2.getAUDIOCONFIG_Path() + " -I " + plikConfig2.getMLF_Path() + " -S "
                        + plikConfig2.getTRAIN_LIST_Path() + " -H " + plikConfig2.getMODELS_Path() + "\\hmms\\hmm." + i + "\\hmmdefs"
                        + " -M " + plikConfig2.getMODELS_Path() + "\\hmms\\hmm." + (i + 1) + " " + plikConfig2.getMODEL_LIST_Path());
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                try {
                    while ((k = stdInput.readLine()) != null) {
                        System.out.println(p);
                    }
                } catch (IOException ex) {
                    System.out.println("Błąd");
                }

                System.out.println("Here is the standard error of the command (if any):\n");
                try {
                    while ((k = stdError.readLine()) != null) {
                        System.out.println(p);
                    }
                } catch (IOException ex) {
                    System.out.println("Błąd!");
                }

                System.out.println("proces się wykonał");
            } catch (IOException ex) {
                System.out.println("BŁĄD na kroku 7 !!!!");
                System.out.println("Krok 7");
            }
        }
    }

    private void HVite() {
        String l = null;
        try {
            System.out.println("wchodzi do try rozpoznawania");
            Process r;
            System.out.println(plikConfig2.getROOT_DIR() + "\\HVite.exe -C "
                    + plikConfig2.getAUDIOCONFIG_Path() + " -H "
                    + plikConfig2.getMODELS_Path() + "\\hmms\\hmm.4"
                    // + plikConfig2.getTRAIN_STEPS() 
                    + "\\hmmdefs -S "
                    + plikConfig2.getTEST_LIST_Path() + " -i "
                    + plikConfig2.getRESULTS_Path() + " -w "
                    + plikConfig2.getLATTICE_Path() + " -o N "
                    + plikConfig2.getDICTONARY_Path() + " "
                    + plikConfig2.getMODEL_LIST_Path());
            r = Runtime.getRuntime().exec("cmd /c " + plikConfig2.getROOT_DIR() + "\\HVite.exe -C "
                    + plikConfig2.getAUDIOCONFIG_Path() + " -H "
                    + plikConfig2.getMODELS_Path() + "\\hmms\\hmm.4"
                    + "\\hmmdefs -S "
                    + plikConfig2.getTEST_LIST_Path() + " -i "
                    + plikConfig2.getRESULTS_Path() + " -w "
                    + plikConfig2.getLATTICE_Path() + " -o N "
                    + plikConfig2.getDICTONARY_Path() + " "
                    + plikConfig2.getMODEL_LIST_Path());
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(r.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(r.getErrorStream()));
            try {
                while ((l = stdInput.readLine()) != null) {
                    System.out.println(r);
                }
            } catch (IOException ex) {
                System.out.println("Błąd");
            }

            System.out.println("Here is the standard error of the command (if any):\n");
            try {
                while ((l = stdError.readLine()) != null) {
                    System.out.println(r);
                }
            } catch (IOException ex) {
                System.out.println("Błąd!");
            }

            System.out.println("proces się wykonał");
        } catch (IOException ex) {
            System.out.println("BŁĄD na kroku 8 !!!!");
        }
    }
}
