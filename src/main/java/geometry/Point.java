package geometry;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Point {
    private double x;
    private double y;
    private int strokeID = 0; //ID of the stroke this point is in, default value is assigned to make sure any algorithm works even if strokeID is not recorded

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }


}
