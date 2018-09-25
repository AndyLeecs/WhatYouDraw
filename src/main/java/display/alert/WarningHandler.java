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

    public void showConfirmationDialog(final String str) {
        final Alert suggestion = getAlert(str);
        final ButtonType yes = new ButtonType(WarningConfig.YES);
        final ButtonType no = new ButtonType(WarningConfig.NO);
        suggestion.getButtonTypes().setAll(yes, no);
        final Optional<ButtonType> result = suggestion.showAndWait();
        if (result.isPresent() && result.get() == yes) {
            confirmationContinue();
        }
    }

    private Alert getAlert(String str) {
        final Alert suggestion = new Alert(Alert.AlertType.WARNING);
        suggestion.setHeaderText(null);
        suggestion.setContentText(str);
        return suggestion;
    }

    private void confirmationContinue() {
        warnable.onTakenWarningSuggestion();
    }
}

