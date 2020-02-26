package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public final class TheGreatWizard implements Observer {
    /* Singleton implementation */
    private static TheGreatWizard theGreatWizard = null;
    /* File descriptor so that the Great Wizard can note what is happening */
    private FileWriter fileWriter;

    private TheGreatWizard(final String outputPath) {
        try {
            fileWriter = new FileWriter(outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TheGreatWizard getTheGreatWizardInstance(final String outputPath) {
        /* Creating a singleton */
        if (theGreatWizard == null) {
            theGreatWizard = new TheGreatWizard(outputPath);
        }
        return theGreatWizard;
    }

    @Override
    public void update(final Observable o, final Object arg) {
        /* The Great Wizard notes down what is happening */
        /* Writing to file */
        try {
            this.fileWriter.write((String) arg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFile() {
        try {
            this.fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
