package label.shapable;

import config.ColorConfig;
import config.LabelConfig;
import javafx.scene.canvas.GraphicsContext;

public class ShapableRectangle extends AbstractShapable {
    public ShapableRectangle() {
    }

    @Override
    protected void setColor(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(ColorConfig.RECTANGLE_COLOR);
    }

    @Override
    public void draw(GraphicsContext graphicsContext, final double middleX, final double middleY, final double span) {
        drawLabelName(graphicsContext, LabelConfig.RECTANGLE, middleX, middleY);
    }
}
