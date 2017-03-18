package eu.greyson;

import eu.greyson.file.FileChooser;
import eu.greyson.file.StarterFileChooser;

public class Paytracker {

    public static void main(String args[]) {
        FileChooser fileChooser = new StarterFileChooser();
        fileChooser.chooseFile();
    }

}
