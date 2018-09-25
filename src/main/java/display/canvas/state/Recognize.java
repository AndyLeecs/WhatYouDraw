package display.canvas.state;

import config.ColorConfig;
import config.LineWidthConfig;
import data.Sketch;
import display.alert.ErrorHandler;
import display.RecognizeWin;
import display.canvas.CustomCanvas;
import exception.SampleSetDataException;
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

    private final double INIT_SPAN = -1;
    private double middleX = -1;
    private double middleY = -1;
    private double lastSpan = INIT_SPAN;

    private RecognizeWin recognizeWin;
    private Sketch sketch;

    public Recognize(final AbstractState formerState, final CustomCanvas canvas) {
        super(formerState, canvas);
    }

    @Override
    protected void matchEventHandlers() {
        final EventHandler<MouseEvent> target = event -> {
            storeForTemp();

            middleX = event.getX();
            middleY = event.getY();

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
        drawFrame(span);
        lastSpan = span;
    }

    @Override
    public void confirm() throws IOException {
        if (lastSpan == INIT_SPAN) {
            cancel();
        } else {
            recognizeWin.close();
            storeForTemp();
            final Sketch segment = getSegment();
            showLabel(segment);
            reset();
        }
    }

    @Override
    public void cancel() {
        restore();
    }

    private void storeForTemp() {
        WritableImage writableImage = canvas.save();
        sketch = new Sketch(writableImage);
    }

    private double getTargetMaxSpan() {
        double res = 0;
        if (middleX >= 0 && middleY >= 0) {
            final double targetMaxWidth = getTargetMaxInOneDimension(canvas.getWidth(), middleX);
            final double targetMaxLength = getTargetMaxInOneDimension(canvas.getHeight(), middleY);
            res = min(targetMaxLength, targetMaxWidth);
        }
        return res;
    }

    private double getTargetMaxInOneDimension(final double totalLength, final double targetPosition) {
        final double margin = totalLength - targetPosition;
        return min(targetPosition, margin);
    }

    private Sketch getSegment() {
        WritableImage writableImage = sketch.getImage();
        final BufferedImage swingImage = SwingFXUtils.fromFXImage(writableImage, null);
        final Image fxImage = SwingFXUtils.toFXImage(swingImage, null);
        final WritableImage image = new WritableImage(fxImage.getPixelReader(),
                (int) (middleX - lastSpan) + 2 * (int) LineWidthConfig.RECOGNIZE_WIDTH,
                (int) (middleY - lastSpan) + 2 * (int) LineWidthConfig.RECOGNIZE_WIDTH,
                (int) (2 * lastSpan) - 2 * (int) LineWidthConfig.RECOGNIZE_WIDTH,
                (int) (2 * lastSpan) - 2 * (int) LineWidthConfig.RECOGNIZE_WIDTH);
        return new Sketch(image);
    }

    private void showLabel(Sketch segment){
        try {
            final ILabelChooser chooser = new LabelChooser();
            final AbstractLabel label = chooser.chooseLabel(segment);
            label.draw(graphicsContext, middleX, middleY, lastSpan);
        }catch (SampleSetDataException e){
            new ErrorHandler().showErrorDialog(e);
        }
    }

    private void reset() {
        storeForTemp();
        lastSpan = INIT_SPAN;
    }

    private void drawFrame(final double span) {
        graphicsContext.strokeRect(middleX - span, middleY - span, 2 * span, 2 * span);
    }

    private void restore() {
        WritableImage writableImage = sketch.getImage();
        canvas.load(writableImage);
    }
}
