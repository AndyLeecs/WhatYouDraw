package label;

import config.LabelConfig;

public class LabelFactory {

    public AbstractLabel createLabel(final String name) {
        System.out.println("label" + name);
        AbstractLabel label;

        //All the labels in @LabelConfig should be listed here
        switch (name) {
            case LabelConfig.RECTANGLE:
                label = new Rectangle();
                break;
            case LabelConfig.CIRCLE:
                label = new Circle();
                break;
            case LabelConfig.TRIANGLE:
                label = new Triangle();
                break;
            default:
                label = new NotRecognizable();
        }
        return label;
    }
}


