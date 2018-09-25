package identify;

import config.ExceptionConfig;
import config.LabelConfig;
import exception.SampleSetDataException;
import geometry.Point;
import identify.algorithm.Gesture;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Obtain gesture sampleSets from storage
 */
public class SampleSetLoader {

    List<Gesture> getSampleGesturesFromFile() throws SampleSetDataException{
        List<Gesture> gestures = new ArrayList<>();
        final URL sampleSetURL = this.getClass().getClassLoader().getResource("sampleSet/");
        if (sampleSetURL != null) {
            final File[] sampleSetsForEachGesture = getSampleSetsForEachGesture(sampleSetURL);
            if (sampleSetsForEachGesture != null) {
                for (final File sampleSetForEachGesture : sampleSetsForEachGesture) {
                    addGestureToList(gestures, sampleSetForEachGesture);
                }
            }
        }
        return gestures;
    }

    private File[] getSampleSetsForEachGesture(URL sampleSetURL) {
        final String sampleSetPath = sampleSetURL.getFile();
        final File wholeSampleSet = new File(sampleSetPath);
        final File[] sampleSetsForEachGesture = wholeSampleSet.listFiles();
        assert sampleSetsForEachGesture.length == LabelConfig.LABEL_LIST.size();
        return sampleSetsForEachGesture;
    }

    private void addGestureToList(List<Gesture> gestures, File sampleSetForEachGesture) throws SampleSetDataException{
        if (sampleSetForEachGesture.isDirectory()) {
            final File[] gestureSnaps = sampleSetForEachGesture.listFiles();
            final String gestureName = sampleSetForEachGesture.getName();
            assert (LabelConfig.LABEL_LIST.contains(gestureName));
            if (gestureSnaps != null) {
                for (final File gestureSnap : gestureSnaps) {
                    final Gesture gesture = getGestureFromSingleFile(gestureSnap, gestureName);
                    gestures.add(gesture);
                }
            }
        }
    }

    Gesture getGestureFromSingleFile(final File gestureSnap, final String gestureName) throws SampleSetDataException{
        try {
            final Image image = SwingFXUtils.toFXImage(ImageIO.read(gestureSnap), null);
            final List<Point> points = new ImageToPoints().getPoints(image);
            return new Gesture(points, gestureName);
        }catch (IOException e){
            throw new SampleSetDataException(ExceptionConfig.IDENTIFY);
        }
    }




}
