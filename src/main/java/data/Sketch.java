package data;

import javafx.scene.image.WritableImage;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The sketch, containing only an image now, and may contain the stretches, the points... in the future
 */
@Getter
@AllArgsConstructor
public class Sketch {
    private WritableImage image;
}
