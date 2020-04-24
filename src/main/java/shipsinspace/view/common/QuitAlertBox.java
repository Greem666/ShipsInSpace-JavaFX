package shipsinspace.view.common;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import shipsinspace.view.GameWindow;

public class QuitAlertBox {
    public static ButtonType closeProgram() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit game");
        alert.setContentText("Are you sure, you want to exit \"Ships... but in Space\"?");
        alert.showAndWait();

        return alert.getResult();
    }
}
