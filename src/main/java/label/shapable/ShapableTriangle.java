package label.shapable;

import config.ColorConfig;
import config.LabelConfig;
import javafx.scene.canvas.GraphicsContext;

public class ShapableTriangle extends AbstractShapable {

    @Override
    protected void setColor(final GraphicsContext graphicsContext) {
        graphicsContext.setStroke(ColorConfig.TRIANGLE_COLOR);
    }

    @Override
    protected void setLabel() {
        labelMarker = LabelConfig.TRIANGLE;
    }

}
