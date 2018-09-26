package display.alert;

import config.WarningConfig;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class WarningHandler {

    private IWarnable warnable;

    public WarningHandler(final IWarnable warnable) {
        this.warnable = warnable;
    }

    /**
     * @return true for not cancelled
     */
    public boolean showConfirmationDialog(final String str) {
        final Alert suggestion = getAlert(str);
        final ButtonType yes = new ButtonType(WarningConfig.YES);
        final ButtonType no = new ButtonType(WarningConfig.NO);
        final ButtonType cancel = new ButtonType(WarningConfig.CANCEL);
        suggestion.getButtonTypes().setAll(yes, no, cancel);
        final Optional<ButtonType> result = suggestion.showAndWait();
        assert result.isPresent();
            if (result.get() == yes) {
                confirmationContinue();
            }

        return !(result.get() == cancel);
    }

    private Alert getAlert(final String str) {
        final Alert suggestion = new Alert(Alert.AlertType.WARNING);
        suggestion.setHeaderText(null);
        suggestion.setContentText(str);
        return suggestion;
    }

    private void confirmationContinue() {
        warnable.onTakenWarningSuggestion();
    }
}

