package display;

import config.TitleConfig;
import config.WarningConfig;
import display.alert.WarningHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainWin extends Stage {
    public MainWin() throws IOException {
        super();

        //load fxml
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/MainWin.fxml"));
        final AnchorPane root = loader.load();

        setStageStyle(root);

        this.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent event) {
                new WarningHandler(loader.getController()).showConfirmationDialog(WarningConfig.SAVE_FIRST);
            }
        });
    }

    private void setStageStyle(final AnchorPane pane) {
        final Scene scene = new Scene(pane);
        this.setTitle(TitleConfig.TITLE);
        this.setScene(scene);
        this.setResizable(false);
        this.initStyle(StageStyle.DECORATED);
        this.show();
    }

}

