package label.shapable;

import config.ColorConfig;
import config.LabelConfig;
import javafx.scene.canvas.GraphicsContext;

public class ShapableCircle extends AbstractShapable {
    public ShapableCircle() {
    }

    @Override
    protected void setColor(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(ColorConfig.CIRCLE_COLOR);
    }

    @Override
    public void draw(GraphicsContext graphicsContext, final double middleX, final double middleY, final double span) {
        drawLabelName(graphicsContext, LabelConfig.CIRCLE, middleX, middleY);
    }
}
