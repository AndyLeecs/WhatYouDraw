package display.canvas;

import javafx.scene.image.WritableImage;

import java.util.*;

/**
 * Memory stored as image list with a cursor
 */
public class Memory{
    private List<WritableImage> images = new ArrayList<>();
    private int cursor = -1;

    private void updateCursor() {
        cursor++;
    }

    private WritableImage getImage() {
        assert cursor < images.size() && cursor >= 0;
        return images.get(cursor);
    }

    public boolean canGetPrevious() {
        return cursor > 0;
    }

    public boolean canGetNext() {
        return cursor + 1 < images.size();
    }

    public WritableImage getPrevious() {
        cursor--;
        return getImage();
    }

    public WritableImage getNext() {
        cursor++;
        return getImage();
    }

    public boolean add(final WritableImage writableImage) {
        updateCursor();
        return images.add(writableImage);
    }


}
