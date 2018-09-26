package display;

import javafx.scene.canvas.GraphicsContext;

/**
 * Geometry that can be drawn by a GraphicsContext
 */
public interface IDrawable {
    void draw(GraphicsContext graphicsContext);
}
