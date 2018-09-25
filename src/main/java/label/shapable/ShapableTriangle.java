package label.shapable;

import config.ColorConfig;
import config.LabelConfig;
import javafx.scene.canvas.GraphicsContext;

public class ShapableTriangle extends AbstractShapable {
    public ShapableTriangle() {
    }

    @Override
    protected void setColor(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(ColorConfig.TRIANGLE_COLOR);
    }

    @Override
    public void draw(GraphicsContext graphicsContext, final double middleX, final double middleY, final double span) {
//        Point A = new Point(middleX,middleY-span);
//        Point B = new Point(middleX - span,middleY+span);
//        Point C = new Point(middleX + span, middleY + span);
//
//        graphicsContext.beginPath();
//        moveTo(A);
//        lineTo(B);
//        lineTo(C);
//        lineTo(A);
//        graphicsContext.closePath();
// //       graphicsContext.stroke();
//        System.out.println(graphicsContext.getStroke());
        System.out.println("triangle is drawn");
        graphicsContext.strokeText(LabelConfig.TRIANGLE, middleX, middleY);
    }

//    private void moveTo(final Point P){
//        graphicsContext.moveTo(P.getX(),P.getY());
//    }
//
//    private void lineTo(final Point P){
//        graphicsContext.lineTo(P.getX(),P.getY());
//    }

}
