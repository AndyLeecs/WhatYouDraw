package identify.algorithm;

import geometry.Point;

public class EuclideanDistanceComputer {


    /**
     * Computes the Squared Euclidean Distance between two points in 2D
     */
    static double getSqrDistance(final Point a, final Point b) {

        return (a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY());
    }

    /**
     * Computes the Euclidean Distance between two points in 2D
     */
    static double getDistance(final Point a, final Point b) {
        return Math.pow(getSqrDistance(a, b), 0.5);
    }

}
