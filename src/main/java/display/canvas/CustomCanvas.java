package display.canvas;

import display.canvas.state.AbstractState;
import display.canvas.state.Draw;
import display.canvas.state.Recognize;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;

/**
 * canvas on which user performs free drawing
 */
public class CustomCanvas extends Canvas {

    private AbstractState state;

    public void setDraw() {
        state = new Draw(state, this);
    }

    public void setRecognize() {
        state = new Recognize(state, this);
    }

    public WritableImage save() {
        WritableImage writableImage = new WritableImage((int) (this.getWidth()), (int) (this.getHeight()));
        writableImage = this.snapshot(new SnapshotParameters(), writableImage);
        return writableImage;
    }

    public void load(final WritableImage writableImage) {
        this.getGraphicsContext2D().drawImage(writableImage, 0, 0);
    }

    public void clear() {
        this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
    }

}
