package config;

import java.util.Arrays;
import java.util.List;

/**
 * Labels able to be identified
 */
public class LabelConfig {
    public static final String RECTANGLE = "rectangle";
    public static final String CIRCLE = "circle";
    public static final String TRIANGLE = "triangle";

    /**
     * list containing all the labels above
     */
    public static final List<String> LABEL_LIST = Arrays.asList(RECTANGLE, CIRCLE, TRIANGLE);
}
