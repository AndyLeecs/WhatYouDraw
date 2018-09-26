package display;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/**
 * Geometry that can be clipped from an image
 */
public interface IImageClipable {
    WritableImage clip(Image image);
}
