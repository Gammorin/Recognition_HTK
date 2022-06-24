/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automatyzacjahtk;

import java.io.File;
import java.io.PrintWriter;

/**
 *
 * @author Morty
 */
public class StworzModels0 {
    String tekst; 
    public StworzModels0(String sciezka){
        tekst = "cztery\ndwa\ndziewiec\njeden\nosiem\npiec\nsiedem\nszesc\ntrzy\nzero";
        File plikList = new File(sciezka);
        try {
            PrintWriter pw = new PrintWriter(plikList);
            pw.print(tekst);
            pw.close();
        } catch (Exception e) {
        }
    }
    
}
