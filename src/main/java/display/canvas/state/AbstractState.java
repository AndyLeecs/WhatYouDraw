package display.canvas.state;

import display.canvas.CustomCanvas;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AbstractState {
    protected List<EventHandler<MouseEvent>> eventHandlers = new ArrayList<>();//record the list of all the event handlers

    protected CustomCanvas canvas;
    protected GraphicsContext graphicsContext;//the graphicsContext of the canvas above

    /**
     * On construction of a new state, remove all the eventHandlers of the canvas, and add new ones
     */
    public AbstractState(final AbstractState formerState, final CustomCanvas canvas) {
        canvas.clearMemory();
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        removeFormerEventHandlers(formerState);
        matchEventHandlers();
    }

    protected final void removeFormerEventHandlers(final AbstractState formerState) {
        if (formerState == null) {
            return;
        }
        final List<EventHandler<MouseEvent>> formerEventHandlers = formerState.getEventHandlers();
        for (final EventHandler<MouseEvent> eventHandler : formerEventHandlers) {
            canvas.removeEventFilter(MouseEvent.MOUSE_DRAGGED, eventHandler);
            canvas.removeEventFilter(MouseEvent.MOUSE_PRESSED, eventHandler);
            canvas.removeEventFilter(MouseEvent.MOUSE_RELEASED, eventHandler);
            canvas.removeEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
            canvas.removeEventFilter(MouseEvent.MOUSE_ENTERED, eventHandler);
            canvas.removeEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, eventHandler);
            canvas.removeEventFilter(MouseEvent.MOUSE_EXITED, eventHandler);
            canvas.removeEventFilter(MouseEvent.MOUSE_EXITED_TARGET, eventHandler);
            canvas.removeEventFilter(MouseEvent.MOUSE_MOVED, eventHandler);
        }
    }

    protected abstract void matchEventHandlers();

    protected void setStroke(final Paint color, final double width) {
        graphicsContext.setStroke(color);
        graphicsContext.setLineWidth(width);
    }

}
