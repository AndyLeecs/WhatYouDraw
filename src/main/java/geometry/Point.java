package geometry;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Point {
    private double x;
    private double y;
    private int strokeID;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }


}
