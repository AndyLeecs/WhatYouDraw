package identify.algorithm;

import geometry.Point;

public class EuclideanDistanceComputer {

    /// <summary>
    /// Computes the Squared Euclidean Distance between two points in 2D
    /// </summary>
    static double getSqrDistance(final Point a, final Point b) {

        return (a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY());
    }

    /// <summary>
    /// Computes the Euclidean Distance between two points in 2D
    /// </summary>
    static double getDistance(final Point a, final Point b) {
        return Math.pow(getSqrDistance(a, b), 0.5);
    }

}
