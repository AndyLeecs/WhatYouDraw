package identify;

import data.Sketch;
import exception.SampleSetDataException;
import geometry.Point;
import identify.algorithm.Gesture;
import identify.algorithm.PointCloudRecognizer;
import javafx.scene.image.WritableImage;
import label.AbstractLabel;
import label.LabelFactory;
import label.NotRecognizable;

import java.util.List;


public class LabelChooser implements ILabelChooser{
    @Override
    public AbstractLabel chooseLabel(final Sketch segment) throws SampleSetDataException {
        AbstractLabel label;
        WritableImage image = segment.getImage();
        List<Point> points = new ImageToPoints().getPoints(image);
        if (points.isEmpty()) {
            label = new NotRecognizable();
        } else {
            label = chooseLabel(points);
        }
        return label;
    }

    private AbstractLabel chooseLabel(final List<Point> points) throws SampleSetDataException {
        AbstractLabel label;
        List<Gesture> gestures = new SampleSetLoader().getSampleGesturesFromFile();
        final String name = PointCloudRecognizer.classify(new Gesture(points), gestures);
        final LabelFactory factory = new LabelFactory();
        label = factory.createLabel(name);
        return label;
    }
}
