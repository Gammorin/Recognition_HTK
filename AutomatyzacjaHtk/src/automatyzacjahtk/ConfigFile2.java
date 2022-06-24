/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automatyzacjahtk;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Morty
 */
public class ConfigFile2 {

    private String ROOT_DIR;
    private String GRAMMAR;
    private String LATTICE;
    private String RESULTS;
    private String DICTONARY;
    private String AUDIOCONFIG;
    private String MLF;
    private String TRAIN_LIST;
    private String PROTO_DEF;
    private String TEST_LIST;
    private String MODELS;
    private String MODEL_LIST;
    private String WORK_DIR;
    private String HVITE_PARAMS;
    private int TRAIN_STEPS;

    public ConfigFile2(String s) {
        Zainicjuj();
        WczytajPlik(s);
    }

    private void WczytajPlik(String s) {
        try {
            InputStream is = new FileInputStream(s);

            Properties pr = new Properties();
            pr.load(is);

            ROOT_DIR = pr.getProperty("ROOT_DIR");
            GRAMMAR = pr.getProperty("GRAMMAR");
            LATTICE = pr.getProperty("LATTICE");
            RESULTS = pr.getProperty("RESULTS");
            DICTONARY = pr.getProperty("DICTONARY");
            AUDIOCONFIG = pr.getProperty("AUDIOCONFIG");
            MLF = pr.getProperty("MLF");
            TRAIN_LIST = pr.getProperty("TRAIN_LIST");
            PROTO_DEF = pr.getProperty("PROTO_DEF");
            TEST_LIST = pr.getProperty("TEST_LIST");
            MODELS = pr.getProperty("MODELS");
            MODEL_LIST = pr.getProperty("MODEL_LIST");
            WORK_DIR = pr.getProperty("WORK_DIR");
            HVITE_PARAMS = pr.getProperty("HVITE_PARAMS");
            TRAIN_STEPS = Integer.parseInt(pr.getProperty("TRAIN_STEPS"));

        } catch (Exception e) {
        }
    }

    private String Path(String z) {
        int index = z.lastIndexOf("\\");
        System.out.println("pozycja ostatniego \\ " + index);
        // System.out.println("wywala się na indeksie ");
        z = z.substring(0, index);
        //  System.out.println("weszło do funkcji ");
        return ROOT_DIR + "\\" + z;
    }

    private String NameFile(String z) {
        int index = z.lastIndexOf("\\");
        System.out.println("pozycja ostatniego \\ " + index);
        z = z.substring(index + 1);
        return z;
    }

    public String getROOT_DIR() {
        return ROOT_DIR;
    }

    public String getGRAMMAR_Path() {
        //return Path(GRAMMAR);
        return ROOT_DIR + "\\" + GRAMMAR;
    }

    public String getGRAMMAR_NameFile() {
        return NameFile(GRAMMAR);
    }

    public String getTEST_LIST_Path() {
        return ROOT_DIR + "\\" + TEST_LIST;
    }

    public String getTEST_LIST_NameFile() {
        return NameFile(TEST_LIST);
    }

    public String getLATTICE_Path() {
        return ROOT_DIR + "\\" + LATTICE;
    }

    public String getLATTICE_NameFile() {
        return NameFile(LATTICE);
    }

    public String getRESULTS_Path() {
        return ROOT_DIR + "\\" + RESULTS;
    }

    public String getRESULTS_NameFile() {
        return NameFile(RESULTS);
    }

    public String getDICTONARY_Path() {
        return ROOT_DIR + "\\" + DICTONARY;
    }

    public String getDICTONARY_NameFile() {
        return NameFile(DICTONARY);
    }

    public String getAUDIOCONFIG_Path() {
        //  System.out.println("audioconfig: "+AUDIOCONFIG);
        return ROOT_DIR + "\\" + AUDIOCONFIG;
    }

    public String getAUDIOCONFIG_Name_File() {
        return NameFile(AUDIOCONFIG);
    }

    public String getMLF_Path() {
        return ROOT_DIR + "\\" + MLF;
    }

    public String getMLF_NameFile() {
        return NameFile(MLF);
    }

    public String getTRAIN_LIST_Path() {
        return ROOT_DIR + "\\" + TRAIN_LIST;
    }

    public String getTRAIN_LIST_NameFile() {
        return NameFile(TRAIN_LIST);
    }

    public String getPROTO_DEF_Path() {
        return ROOT_DIR + "\\" + PROTO_DEF;
    }

    public String getPROTO_DEF_NameFile() {
        return NameFile(PROTO_DEF);
    }

    public String getMODELS_Path() {
        String z = MODELS.toString();
        int index = z.lastIndexOf("\\");
        z = z.substring(0, index);
        index = z.lastIndexOf("\\");
        z = z.substring(0, index);
        return ROOT_DIR + "\\" + z;
    }

    public String getMODELS_PathFile() {
        return Path(MODELS);
    }

    public String getMODELS_NameFile() {
        return NameFile(MLF);
    }

    public String getMODEL_LIST_Path() {
        return ROOT_DIR + "\\" + MODEL_LIST;
    }

    public String getMODEL_LIST_NameFile() {
        return NameFile(MODEL_LIST);
    }

    public String getWORK_DIR_Path() {
        return Path(WORK_DIR);
    }

    public String getWORK_DIR_NameFile() {
        return NameFile(WORK_DIR);
    }

    public String getHVITE_PARAMS() {
        return ROOT_DIR + "\\" + HVITE_PARAMS;
    }

    public int getTRAIN_STEPS() {
        return TRAIN_STEPS;
    }

    private void Zainicjuj() {
        ROOT_DIR = "";
        GRAMMAR = "";
        LATTICE = "";
        RESULTS = "";
        DICTONARY = "";
        AUDIOCONFIG = "";
        MLF = "";
        TRAIN_LIST = "";
        PROTO_DEF = "";
        TEST_LIST = "";
        MODELS = "";
        MODEL_LIST = "";
        WORK_DIR = "";
        HVITE_PARAMS = "";
        TRAIN_STEPS = 0;
    }
}
