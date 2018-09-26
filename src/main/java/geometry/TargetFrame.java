package geometry;

import display.IDrawable;
import display.IImageClipable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/**
 * Target frame, with target middle point and span
 */
public class TargetFrame implements IDrawable, IImageClipable {
    private TargetPoint targetPoint;
    private double span;
    private boolean justAimed = true;

    public TargetFrame(final double x, final double y) {
        this.targetPoint = new TargetPoint(x, y);
    }

    public boolean isValid() {
        return !justAimed;
    }

    public void resetSpan() {
        justAimed = true;
    }

    public double getX() {
        return targetPoint.getX();
    }

    public double getY() {
        return targetPoint.getY();
    }

    public void setSpan(final double span) {
        if (span >= 0) {
            justAimed = false;
            this.span = span;
        }
    }

    public TargetPoint getTargetPoint() {
        return targetPoint;
    }

    @Override
    public void draw(final GraphicsContext graphicsContext) {
        final double middleX = targetPoint.getX();
        final double middleY = targetPoint.getY();
        graphicsContext.strokeRect(middleX - span, middleY - span, 2 * span, 2 * span);
    }

    @Override
    public WritableImage clip(final Image image) {
        final double middleX = targetPoint.getX();
        final double middleY = targetPoint.getY();
        return new WritableImage(image.getPixelReader(),
                (int) (middleX - span),
                (int) (middleY - span),
                (int) (2 * span),
                (int) (2 * span));
    }
}
