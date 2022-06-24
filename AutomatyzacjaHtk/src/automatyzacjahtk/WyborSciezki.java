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
public class WyborSciezki {

    private JFileChooser wybor;
    private String sciezka;

    public WyborSciezki() {
        wybor = new JFileChooser();
        wybor.setCurrentDirectory(new File("."));
        wybor.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        wybor.showOpenDialog(wybor);
        sciezka = wybor.getSelectedFile().getPath();
    }

    public String getPath() {
        return sciezka;
    }
}
