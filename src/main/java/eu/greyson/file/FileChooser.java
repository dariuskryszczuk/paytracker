package eu.greyson.file;

import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public abstract class FileChooser extends JFileChooser {

    FileChooser() {
        this.setCurrentDirectory(this.getDefaultFolder());
        this.setFileFilter(this.getFilters());
    }

    /**
     * Location of file manager when dialog is opened.
     * @return folder
     */
    abstract File getDefaultFolder();

    /**
     * Description and comma separated file extensions:
     * e.g. new FileNameExtensionFilter("JPG and GIF", "jpg", "gif");
     * @return filters
     */
    abstract FileNameExtensionFilter getFilters();

    /**
     * Opens {@code javax.swing} dialog for picking files.
     * @return Picked file or null.
     */
    @Nullable
    public synchronized File chooseFile() {
        int result = this.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            return this.getSelectedFile();
        }
        return null;
    }
}