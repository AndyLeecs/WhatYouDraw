package display.canvas.state;

import config.ColorConfig;
import config.LineWidthConfig;
import data.Sketch;
import display.RecognizeWin;
import display.alert.ErrorHandler;
import display.canvas.CustomCanvas;
import exception.SampleSetDataException;
import geometry.TargetFrame;
import identify.ILabelChooser;
import identify.LabelChooser;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import label.AbstractLabel;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Math.min;

public class Recognize extends AbstractState implements IRecognize {

    private TargetFrame targetFrame; //user chosen target frame
    private RecognizeWin recognizeWin;
    private Sketch sketch; //whole sketch

    public Recognize(final AbstractState formerState, final CustomCanvas canvas) {
        super(formerState, canvas);
    }

    @Override
    protected void matchEventHandlers() {
        final EventHandler<MouseEvent> target = event -> {
            storeForTemp();

            double middleX = event.getX();
            double middleY = event.getY();

            assert middleX >= 0 && middleY >= 0;
            targetFrame = new TargetFrame(middleX, middleY);

            double maxSpan = getTargetMaxSpan();

            Platform.runLater(() -> {
                        try {
                            recognizeWin = new RecognizeWin(this, maxSpan);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        };

        canvas.addEventFilter(MouseEvent.MOUSE_CLICKED, target);

        eventHandlers.add(target);
    }

    @Override
    public void preview(final double span) {
        restore();
        setStroke(ColorConfig.RECOGNIZE_COLOR, LineWidthConfig.RECOGNIZE_WIDTH);
        targetFrame.setSpan(span);
        targetFrame.draw(graphicsContext);
    }

    @Override
    public void confirm(){
        if (targetFrame.isValid()) {
            recognizeWin.close();
            storeForTemp();
            final Sketch segment = getSegment();
            showLabel(segment);
            resetSpan();
        } else {
            cancel();
        }
    }

    @Override
    public void cancel() {
        restore();
    }

    /**
     * get the maximum allowed span on this canvas with the selected middle point
     */
    private double getTargetMaxSpan() {
        final double targetMaxWidth = getTargetMaxInOneDimension(canvas.getWidth(), targetFrame.getX());
        final double targetMaxLength = getTargetMaxInOneDimension(canvas.getHeight(), targetFrame.getY());
        return min(targetMaxLength, targetMaxWidth);
    }

    private double getTargetMaxInOneDimension(final double totalLength, final double targetPosition) {
        final double margin = totalLength - targetPosition;
        return min(targetPosition, margin);
    }

    /**
     * get the selected segment of the sketch
     */
    private Sketch getSegment() {
        final WritableImage writableImage = sketch.getImage();
        final BufferedImage swingImage = SwingFXUtils.fromFXImage(writableImage, null);
        final Image fxImage = SwingFXUtils.toFXImage(swingImage, null);
        final WritableImage image = targetFrame.clip(fxImage);
        return new Sketch(image);
    }

    private void showLabel(final Sketch segment) {
        try {
            final ILabelChooser chooser = new LabelChooser();
            final AbstractLabel label = chooser.chooseLabel(segment);
            label.draw(graphicsContext, targetFrame);
        } catch (SampleSetDataException e) {
            new ErrorHandler().showErrorDialog(e);
        }
    }

    private void resetSpan() {
        storeForTemp();
        targetFrame.resetSpan();
    }

    /**
     * temporarily store the sketch
     */
    private void storeForTemp() {
        final WritableImage writableImage = canvas.save();
        sketch = new Sketch(writableImage);
    }

    /**
     * restore the previously saved sketch
     */
    private void restore() {
        final WritableImage writableImage = sketch.getImage();
        canvas.load(writableImage);
    }
}
