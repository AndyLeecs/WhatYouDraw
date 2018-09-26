package display;

import display.canvas.state.IRecognize;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class RecognizeWin extends Stage {
    public RecognizeWin(final IRecognize target, final double maxSpan) throws IOException {
        super();

        //load fxml
        final FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/RecognizeWin.fxml"));
        final RecognizeWinController controller = new RecognizeWinController(target, maxSpan);
        loader.setController(controller);
        final AnchorPane root = loader.load();

        setStageStyle(root);

        this.setOnCloseRequest(e -> {
            controller.cancel();
        });
    }

    private void setStageStyle(final AnchorPane pane) {
        final Scene scene = new Scene(pane);
        this.setTitle("");
        this.setScene(scene);
        this.setResizable(false);
        this.initStyle(StageStyle.DECORATED);
        this.initModality(Modality.APPLICATION_MODAL);
        this.show();
    }

}
