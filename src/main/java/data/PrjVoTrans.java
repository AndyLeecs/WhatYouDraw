package data;

import javafx.scene.image.WritableImage;

/**
 * Rules to display a project or to obtain the project from display
 */
public class PrjVoTrans {

    private IPrjManagable prjManagable;

    public PrjVoTrans(final IPrjManagable prjManagable) {
        this.prjManagable = prjManagable;
    }

    public void prjToVo(final Prj prj) {
        if(prj != null) {
            final Sketch sketch = prj.getSketch();
            final WritableImage image = sketch.getImage();
            prjManagable.getCanvas().load(image);
        }
    }

    public Prj voToPrj() {
        final WritableImage image = prjManagable.getCanvas().save();
        final Sketch sketch = new Sketch(image);
        return new Prj(sketch);
    }

    public void clearVo() {
        prjManagable.getCanvas().clear();
    }

}
