package model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class alertMessage {
    private final String info;

    public alertMessage() {
        info = "default";

    }

    public void Error(String error) {
        String display = error;
        Alert signInNOSignUpError = new Alert(AlertType.ERROR);
        signInNOSignUpError.setHeaderText(display);
        signInNOSignUpError.show();
    }

    public void Info(String info) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(info);
        alert.showAndWait();
    }

    public void Warning(String warning) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText(warning);

        alert.showAndWait();
    }

    public boolean Confirmation(String confirmation) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText(confirmation);
        alert.setHeight(500);
        alert.setWidth(500);
        Optional<ButtonType> result = alert.showAndWait();
        // ... user chose OK
        // ... user chose CANCEL or closed the dialog
        return result.get() == ButtonType.OK;
    }
}
