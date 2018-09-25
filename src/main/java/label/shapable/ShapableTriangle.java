package label.shapable;

import config.ColorConfig;
import config.LabelConfig;
import javafx.scene.canvas.GraphicsContext;

public class ShapableTriangle extends AbstractShapable {
    public ShapableTriangle() {
    }

    @Override
    protected void setColor(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(ColorConfig.TRIANGLE_COLOR);
    }

    @Override
    public void draw(GraphicsContext graphicsContext, final double middleX, final double middleY, final double span) {
        drawLabelName(graphicsContext, LabelConfig.TRIANGLE, middleX, middleY);
    }
}
