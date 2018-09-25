package label;

import label.shapable.ShapableRectangle;

public class Rectangle extends AbstractLabel {

    @Override
    protected void setShapable() {
        shapable = new ShapableRectangle();
    }
}
