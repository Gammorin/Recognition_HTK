/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automatyzacjahtk;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Morty
 */
public class WyborPliku {

    private JFileChooser wybor;
    private String sciezka;

    public WyborPliku() {
        wybor = new JFileChooser();
        wybor.setCurrentDirectory(new File("."));
        wybor.setFileSelectionMode(JFileChooser.FILES_ONLY);
        wybor.showOpenDialog(wybor);
        sciezka = wybor.getSelectedFile().getPath();
    }

    public String getPath() {
        return sciezka;
    }
}
