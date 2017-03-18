package eu.greyson.file;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.nio.file.Paths;

/**
 * This file picker should open on load of the application so the
 * user can pick "starter" data.
 */
public class StarterFileChooser extends FileChooser {

    @Override
    File getDefaultFolder() {
        return Paths.get(System.getProperty("user.home")).toFile();
    }

    @Override
    FileNameExtensionFilter getFilters() {
        return new FileNameExtensionFilter("txt files", "txt");
    }
}
