package label.shapable;

import geometry.TargetFrame;
import geometry.TargetPoint;
import javafx.scene.canvas.GraphicsContext;

public abstract class AbstractShapable {

    protected String labelMarker;

    public final void setColorAndDraw(final GraphicsContext graphicsContext, final TargetFrame targetFrame) {
        setColor(graphicsContext);
        setLabel();
        draw(graphicsContext, targetFrame);
    }

    protected abstract void setColor(GraphicsContext graphicsContext);

    protected abstract void setLabel();

    protected final void draw(final GraphicsContext graphicsContext, final TargetFrame targetFrame) {
        drawLabelName(graphicsContext, labelMarker, targetFrame.getTargetPoint());
    }

    final void drawLabelName(final GraphicsContext graphicsContext, final String name, final TargetPoint targetPoint) {
        final double middleX = targetPoint.getX();
        final double middleY = targetPoint.getY();
        graphicsContext.strokeText(name, middleX, middleY);
    }
}
