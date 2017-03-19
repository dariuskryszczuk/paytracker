package eu.greyson.file;

import eu.greyson.payment.Payable;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    public static Stream<String> getFileRows(Path p) throws IOException {
        return Files.lines(p);
    }
}
