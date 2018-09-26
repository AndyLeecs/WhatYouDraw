package identify.algorithm;

import geometry.Point;

import java.util.Arrays;
import java.util.List;

public class PointCloudRecognizer {

    /**
     *  Main function of the $P recognizer.
     *  Classifies a candidate gesture against a set of training samples.
     *  Returns the class of the closest neighbor in the training set.
     */
    public static String classify(final Gesture candidate, final List<Gesture> trainingSet) {
        double minDistance = Double.MAX_VALUE;
        String gestureClass = "";
        for (final Gesture template : trainingSet) {
            final double dist = greedyCloudMatch(candidate.getPoints(), template.getPoints());
            if (dist < minDistance) {
                minDistance = dist;
                gestureClass = template.name;
            }
        }
        return gestureClass;
    }

    /**
     * Implements greedy search for a minimum-distance matching between two point clouds
     */
    private static double greedyCloudMatch(final List<Point> points1, final List<Point> points2) {
        int n = points1.size(); // the two clouds should have the same number of points by now
        double eps = 0.5f;       // controls the number of greedy search trials (eps is in [0..1])
        int step = (int) Math.floor(Math.pow(n, 1.0f - eps));
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < n; i += step) {
            final double dist1 = cloudDistance(points1, points2, i);   // match points1 --> points2 starting with index point i
            final double dist2 = cloudDistance(points2, points1, i);   // match points2 --> points1 starting with index point i
            minDistance = Math.min(minDistance, Math.min(dist1, dist2));
        }
        return minDistance;
    }

    /**
     * Computes the distance between two point clouds by performing a minimum-distance greedy matching
     * starting with point startIndex
     */
    private static double cloudDistance(final List<Point> points1, final List<Point> points2, final int startIndex) {
        assert points1.size() == points2.size();  // the two clouds should have the same number of points by now
        final int n = points1.size();
        boolean[] matched = new boolean[n]; // matched[i] signals whether point i from the 2nd cloud has been already matched
        Arrays.fill(matched, false);

        double sum = 0;  // computes the sum of distances between matched points (i.e., the distance between the two clouds)
        int i = startIndex;
        do {
            int index = -1;
            double minDistance = Double.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!matched[j]) {
                    final double dist = EuclideanDistanceComputer.getSqrDistance(points1.get(i), points2.get(j));// use squared Euclidean distance to save some processing time
                    if (dist < minDistance) {
                        minDistance = dist;
                        index = j;
                    }
                }
            }
            matched[index] = true; // point index from the 2nd cloud is matched to point i from the 1st cloud
            final double weight = 1.0f - ((i - startIndex + n) % n) / (1.0f * n);
            sum += weight * minDistance; // weight each distance with a confidence coefficient that decreases from 1 to 0
            i = (i + 1) % n;
        } while (i != startIndex);
        return sum;
    }
}