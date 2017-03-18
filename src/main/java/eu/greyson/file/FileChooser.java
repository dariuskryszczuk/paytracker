package eu.greyson.file;

import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public abstract class FileChooser extends JFileChooser {

    protected FileChooser() {
        this.setCurrentDirectory(this.getDefaultFolder());
        this.setFileFilter(this.getFilters());
    }

    /**
     * Location of file manager when dialog is opened.
     * @return folder
     */
    abstract File getDefaultFolder();

    /**
     * Comma separated file extensions: e.g. new FileNameExtensionFilter("jpg", "gif");
     * @return filters
     */
    abstract FileNameExtensionFilter getFilters();

    /**
     * Opens <code>javax.swing</code> dialog for picking files.
     * @return Picked file or null.
     */
    @Nullable
    public File chooseFile() {
        int result = this.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            return this.getSelectedFile();
        }
        return null;
    }
}