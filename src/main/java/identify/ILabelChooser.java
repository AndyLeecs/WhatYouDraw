package identify;

import data.Sketch;
import exception.SampleSetDataException;
import label.AbstractLabel;

/**
 * Choose a label for the segment of sketch
 */
public interface ILabelChooser {

    AbstractLabel chooseLabel(Sketch segment) throws SampleSetDataException;
}
