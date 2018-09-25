package display;

import display.canvas.state.IRecognize;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;

import java.io.IOException;

/**
 * Define a span for the recognize frame
 */
public class RecognizeWinController {

    private IRecognize target;//for call back

    private double maxSpan;//maxSpan allowed for the frame to be within the screen

    @FXML
    private Slider slider;//to get the value of user-favored span


    public RecognizeWinController(final IRecognize target, final double maxSpan) {
        this.maxSpan = maxSpan;
        this.target = target;
    }

    @FXML
    public void initialize() {
        System.out.println(maxSpan);
        slider.setMin(0);
        slider.setMax(maxSpan);
    }

    @FXML
    public void previewOnAction() {
        final double span = slider.getValue();
        target.preview(span);
    }

    @FXML
    public void confirmOnAction() {
        try {
            target.confirm();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancel() {
        target.cancel();
    }


}
