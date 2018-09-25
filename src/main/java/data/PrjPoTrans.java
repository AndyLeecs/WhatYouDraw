package data;

import config.ExceptionConfig;
import exception.ProjectDataException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Rules to load or save a project from a persistent object
 * persistent object as a file currently
 */
public class PrjPoTrans {
    private FileChooser fileChooser;
    private Window mainStage;

    /**
     * allowed file format
     */
    private static final String DESCRIPTION = "Image Files";
    private static final String FORMAT_NAME = "png";
    private static final String EXTENSION = "*."+FORMAT_NAME;

    public PrjPoTrans(final Window mainStage) {
        fileChooser = new FileChooser();
        this.mainStage = mainStage;
        final FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(DESCRIPTION, EXTENSION);
        fileChooser.getExtensionFilters().add(extFilter);
    }

    public void prjToPo(final Prj prj) throws ProjectDataException {
        try {
            final Sketch sketch = prj.getSketch();
            WritableImage image = sketch.getImage();
            final File file = fileChooser.showSaveDialog(mainStage);
            if (file != null) {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), FORMAT_NAME, file);
            }
        } catch (IOException e) {
            throw new ProjectDataException(ExceptionConfig.SAVE);
        }
    }

    public Prj poToPrj() throws ProjectDataException {
        Prj prj = null;
        try {
            final File file = fileChooser.showOpenDialog(mainStage);
            WritableImage image;
            if (file != null) {
                image = SwingFXUtils.toFXImage(ImageIO.read(file), null);
                final Sketch sketch = new Sketch(image);
                prj = new Prj(sketch);
            }
            return prj;
        } catch (IOException e) {
            throw new ProjectDataException(ExceptionConfig.OPEN);
        }
    }
}
