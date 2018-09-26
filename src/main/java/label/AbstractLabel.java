package label;

import config.LabelConfig;
import geometry.TargetFrame;
import javafx.scene.canvas.GraphicsContext;
import label.shapable.AbstractShapable;

public abstract class AbstractLabel {

    AbstractShapable shapable;//not null if the label represents a real shape

    public AbstractLabel() {
        assert LabelConfig.LABEL_LIST.contains(this.getClass().getName());
    }

    protected abstract void setShapable();

    public final void draw(final GraphicsContext graphicsContext, final TargetFrame targetFrame) {
        setShapable();
        if (shapable != null) {
            shapable.setColorAndDraw(graphicsContext, targetFrame);
        }
    }

}
