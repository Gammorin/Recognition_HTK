/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automatyzacjahtk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morty
 */
public class StworzGrammar {

    String tekst;

    public StworzGrammar(String sciezka) {
        tekst = "$digit = JEDEN | DWA | TRZY | CZTERY | PIEC | SZESC | SIEDEM | OSIEM | DZIEWIEC | ZERO ; \n"
                + "($digit)";

        File plikList = new File(sciezka);
        try {
//            boolean b = plikList.createNewFile();
//            BufferedWriter bw = new BufferedWriter(new FileWriter(plikList));
//            bw.write(tekst);
//            
//            if(bw !=null){
//                bw.flush();
//                bw.close();
//            }
            PrintWriter pw = new PrintWriter(plikList);
            pw.print(tekst);
            pw.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            Logger.getLogger(ZapisTrainList.class.getName()).log(Level.SEVERE, null, ex);


        }
    }
}
