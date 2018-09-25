package display.canvas.state;

import config.ColorConfig;
import display.canvas.CustomCanvas;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import lombok.Getter;

@Getter
public class Erase extends AbstractState {

    private static final double WIDTH = 30;

    public Erase(final AbstractState formerState, final CustomCanvas canvas) {
        super(formerState, canvas);
    }

    @Override
    protected void matchEventHandlers() {
        final EventHandler<MouseEvent> erasing = event -> {
            GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

            graphicsContext.setStroke(ColorConfig.BACKGROUND_COLOR);
            graphicsContext.setLineWidth(WIDTH);
            graphicsContext.lineTo(event.getX(), event.getY());
            graphicsContext.stroke();
        };
        final EventHandler<MouseEvent> startErasing = event -> {
            GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
            graphicsContext.beginPath();
            graphicsContext.moveTo(event.getX(), event.getY());
            graphicsContext.stroke();
        };
        canvas.addEventFilter(MouseEvent.MOUSE_PRESSED, startErasing);
        canvas.addEventFilter(MouseEvent.MOUSE_DRAGGED, erasing);

        eventHandlers.add(startErasing);
        eventHandlers.add(erasing);
    }
}
