package runner;

import config.LanguageConfig;
import display.MainWin;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import java.io.IOException;
import java.util.Locale;

public class ClientRunner {
    private ClientRunner() {
        new JFXPanel();
        initGUI();
    }

    public static void main(String[] args) {
        /**
         * The Strings in this project
         */
        Locale.setDefault(LanguageConfig.LOCALE);
        new ClientRunner();
    }

    private void initGUI() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MainWin();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
