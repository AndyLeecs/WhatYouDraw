package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * The whole project, containing the sketch, (the well-formed amendment of the sketch... for future implementation)
 */
@Getter
@AllArgsConstructor
public class Prj implements Serializable {
    private static final long serialVersionUID = 1L;
    private Sketch sketch;
}
