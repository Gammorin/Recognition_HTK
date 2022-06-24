package automatyzacjahtk;

import java.io.File;
import java.io.PrintWriter;

public class StworzDict {

    String tekst;

    public StworzDict(String sciezka) {
        tekst = "JEDEN jeden\nDWA dwa\nTRZY trzy\nCZTERY cztery\nPIEC piec\nSZESC szesc\nSIEDEM siedem"
                + "\nOSIEM osiem\nDZIEWIEC dziewiec\nZERO zero\n";
        File plik = new File(sciezka);
        try {
            PrintWriter pw = new PrintWriter(plik);
            pw.print(tekst);
            pw.close();
        } catch (Exception e) {
        }
    }
}
