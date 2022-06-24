/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automatyzacjahtk;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morty
 */
public class ZapisTrainList {

    public ZapisTrainList(String sciezka) {
        File katalog = new File("lists");
        try {
             boolean a = katalog.mkdir();
        } catch (Exception e) {
        }
        File plikList = new File("train.list");
        try {
            boolean b = plikList.createNewFile();            
        } catch (IOException ex) {
            Logger.getLogger(ZapisTrainList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
