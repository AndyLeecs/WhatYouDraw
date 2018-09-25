package label;

import label.shapable.ShapableCircle;

public class Circle extends AbstractLabel {

    @Override
    protected void setShapable() {
        shapable = new ShapableCircle();
    }
}
