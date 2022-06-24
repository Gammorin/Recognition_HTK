/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automatyzacjahtk;

import java.io.File;

/**
 *
 * @author Morty
 */
public class StworzFolderyHmm {

    public StworzFolderyHmm(String sciezkaHmm) {
        System.out.println("Z funkcji foldery sciezka: "+sciezkaHmm);
        utworzFoldery(sciezkaHmm);
    }

    private void utworzFoldery(String s) {
        for (int i = 0; i < 10; i++) {
      
            File katalog = new File(s+"\\hmms\\hmm."+i);
            try {
                boolean a = katalog.mkdirs();
            } catch (Exception e) {
            }
        }

    }
}
