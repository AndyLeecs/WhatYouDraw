package identify.algorithm;

import config.IdentifyConfig;
import geometry.Point;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Getter
public class Gesture {

    public List<Point> points;            // gesture points (normalized)
    public String name = "";                 // gesture class

    /// <summary>
    /// Constructs a gesture from an array of points
    /// </summary>
    /// <param name="points"></param>
    public Gesture(final List<Point> points, final String gestureName) {
        this(points);
        this.name = gestureName;
    }

    public Gesture(final List<Point> points) {
        // normalizes the array of points with respect to scale, origin, and number of points
        this.points = scale(points);
        this.points = translateTo(this.points, centroid(this.points));
        this.points = resample(this.points, IdentifyConfig.SAMPLING_RESOLUTION);
    }

    /// <summary>
    /// Performs scale normalization with shape preservation into [0..1]x[0..1]
    /// </summary>
    /// <param name="points"></param>
    /// <returns></returns>
    private List<Point> scale(final List<Point> points) {

        double minx = Double.MAX_VALUE, miny = Double.MAX_VALUE, maxx = Double.MIN_VALUE, maxy = Double.MIN_VALUE;
        for(Point point : points){
            minx = min(minx, point.getX());
            miny = min(miny, point.getY());
            maxx = max(maxx, point.getX());
            maxy = max(maxy, point.getY());
        }

        final List<Point> newPoints = new ArrayList<>(points.size());
        final double scale = max(maxx - minx, maxy - miny);
        for(Point point : points){
            newPoints.add(new Point(
                    (point.getX() - minx) / scale, (point.getY() - miny) / scale, point.getStrokeID()));
        }
        return newPoints;
    }

    /// <summary>
    /// Translates the array of points by p
    /// </summary>
    /// <param name="points"></param>
    /// <param name="p"></param>
    /// <returns></returns>
    private List<Point> translateTo(final List<Point> points, final Point p) {
        final List<Point> newPoints = new ArrayList<>();
        for(Point point : points){
            newPoints.add(new Point(point.getX() - p.getX(), point.getY() - p.getY(), point.getStrokeID()));
        }
        return newPoints;
    }

    /// <summary>
    /// Computes the centroid for an array of points
    /// </summary>
    /// <param name="points"></param>
    /// <returns></returns>
    private Point centroid(final List<Point> points) {
        double cx = 0, cy = 0;
        for (Point point : points) {
            cx += point.getX();
            cy += point.getY();
        }
        return new Point(cx / points.size(), cy / points.size(), 0);
    }

    /// <summary>
    /// Resamples the array of points into n equally-distanced points
    /// </summary>
    /// <param name="points"></param>
    /// <param name="n"></param>
    /// <returns></returns>
    private List<Point> resample(final List<Point> points, final int n) {
        final List<Point> newPoints = new ArrayList<>();
        assert points.size() > 0;
        newPoints.add(new Point(points.get(0).getX(), points.get(0).getY(), points.get(0).getStrokeID()));
        int numPoints = 1;

        final double I = pathSize(points) / (n - 1); // computes interval size()
        double D = 0;
        for (int i = 1; i < points.size(); i++) {
            if (points.get(i).getStrokeID() == points.get(i - 1).getStrokeID()) {
                double d = EuclideanDistanceComputer.getDistance(points.get(i - 1), points.get(i));
                if (D + d >= I) {
                    Point firstPoint = points.get(i - 1);
                    while (D + d >= I) {
                        // add interpolated point
                        double t = min(max((I - D) / d, 0.0f), 1.0f);
                        if (Double.isNaN(t)) {
                            t = 0.5f;
                        }
                        newPoints.add(new Point(
                                (1.0f - t) * firstPoint.getX() + t * points.get(i).getX(),
                                (1.0f - t) * firstPoint.getY() + t * points.get(i).getY(),
                                points.get(i).getStrokeID()));
                        numPoints++;


                        // update partial size()
                        d = D + d - I;
                        D = 0;
                        firstPoint = newPoints.get(numPoints - 1);
                    }
                    D = d;
                } else {
                    D += d;
                }
            }
        }

        if (numPoints == n - 1) { // sometimes we fall a rounding-error short of adding the last point, so add it if so
            newPoints.add(new Point(points.get(points.size() - 1).getX(), points.get(points.size() - 1).getY(), points.get(points.size() - 1).getStrokeID()));
        }
        return newPoints;
    }

    /// <summary>
    /// Computes the path size() for an array of points
    /// </summary>
    /// <param name="points"></param>
    /// <returns></returns>
    private double pathSize(final List<Point> points) {
        double size = 0;
        for (int i = 1; i < points.size(); i++) {
            if (points.get(i).getStrokeID() == points.get(i - 1).getStrokeID()) {
                size += EuclideanDistanceComputer.getDistance(points.get(i - 1), points.get(i));
            }
        }

        return size;
    }
}
