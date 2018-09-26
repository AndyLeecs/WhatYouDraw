package display.canvas.state;

import config.ColorConfig;
import config.LineWidthConfig;
import display.canvas.CustomCanvas;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import lombok.Getter;

@Getter
public class Draw extends AbstractState {

    public Draw(final AbstractState formerState, final CustomCanvas canvas) {
        super(formerState, canvas);
        canvas.saveToMemory();
    }

    @Override
    protected void matchEventHandlers() {
        //define event handlers
        final EventHandler<MouseEvent> start = new EventHandler<MouseEvent>() {
            @Override
            public final void handle(MouseEvent event) {
                final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
                setStroke(ColorConfig.PEN_COLOR, LineWidthConfig.PEN_WIDTH);
                graphicsContext.lineTo(event.getX(), event.getY());
                graphicsContext.stroke();
            }
        };
        final EventHandler<MouseEvent> onGoing = new EventHandler<MouseEvent>() {
            @Override
            public final void handle(MouseEvent event) {
                final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
                graphicsContext.beginPath();
                graphicsContext.moveTo(event.getX(), event.getY());
                graphicsContext.stroke();
            }
        };

        final EventHandler<MouseEvent> stop = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                canvas.saveToMemory();
                canvas.notifyUndoable();
            }
        };

        //add event handlers, make them filters to ensure performance
        canvas.addEventFilter(MouseEvent.MOUSE_DRAGGED, start);
        canvas.addEventFilter(MouseEvent.MOUSE_PRESSED, onGoing);
        canvas.addEventFilter(MouseEvent.MOUSE_RELEASED, stop);

        //record event handlers
        eventHandlers.add(start);
        eventHandlers.add(onGoing);
        eventHandlers.add(stop);
    }

}
