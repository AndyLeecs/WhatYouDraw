package identify;

import config.ColorConfig;
import geometry.Point;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Obtain the points representation of an image
 */
public class ImageToPoints {
    List<Point> getPoints(final Image image) {
        List<Point> points = new ArrayList<>();
        final PixelReader reader = image.getPixelReader();
        Color color;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                color = reader.getColor(x, y);
                final boolean isPenColor = color.equals(ColorConfig.PEN_COLOR);//only the pixel in pen color are counted
                if (isPenColor) {
                    points.add(new Point(x, y));
                }
            }
        }
        return points;
    }
}
