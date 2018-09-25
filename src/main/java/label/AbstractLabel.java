package label;

import config.LabelConfig;
import config.LanguageConfig;
import javafx.scene.canvas.GraphicsContext;
import label.shapable.AbstractShapable;

public abstract class AbstractLabel {

    AbstractShapable shapable;//not null if the label represents a real shape

    public void AbstractLabel(){
        assert (LabelConfig.LABEL_LIST.contains(this.getClass().getName()));
    }

    protected abstract void setShapable();

    public final void draw(GraphicsContext graphicsContext, final double middleX, final double middleY, final double span) {
        setShapable();
        if (shapable != null) {
            shapable.setColorAndDraw(graphicsContext, middleX, middleY, span);
        }
    }

}
