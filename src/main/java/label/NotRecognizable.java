package label;

public class NotRecognizable extends AbstractLabel {
    public NotRecognizable() {
    }

    @Override
    protected void setShapable() {
        shapable = null;
    }
}
