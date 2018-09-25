package display.canvas.state;

import java.io.IOException;

/**
 * Select a frame for recognition
 */
public interface IRecognize {
    void preview(double span);

    void confirm() throws IOException;

    void cancel();
}
