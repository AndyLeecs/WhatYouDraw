package label.shapable;

import javafx.scene.canvas.GraphicsContext;

public abstract class AbstractShapable {

    protected abstract void setColor(GraphicsContext graphicsContext);

    protected abstract void draw(GraphicsContext graphicsContext, double middleX, double middleY, double span);

    public final void setColorAndDraw(GraphicsContext graphicsContext, double middleX, double middleY, double span) {
        setColor(graphicsContext);
        draw(graphicsContext, middleX, middleY, span);
    }
}