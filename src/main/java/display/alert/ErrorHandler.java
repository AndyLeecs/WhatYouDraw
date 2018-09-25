package display.alert;

import javafx.scene.control.Alert;

public class ErrorHandler {
    public void showErrorDialog(final Exception e) {
        final Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}
