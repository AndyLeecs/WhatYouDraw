package label;

import label.shapable.ShapableTriangle;

public class Triangle extends AbstractLabel {

    @Override
    protected void setShapable() {
        shapable = new ShapableTriangle();
    }
}
