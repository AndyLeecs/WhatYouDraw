package label;

import label.shapable.ShapableTriangle;

public class Triangle extends AbstractLabel {

    public Triangle() {
    }

    @Override
    protected void setShapable() {
        shapable = new ShapableTriangle();
    }
}
