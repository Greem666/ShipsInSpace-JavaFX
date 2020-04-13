package shipsinspace.view.common;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    static Boolean answer = false;

    public static boolean display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setResizable(false);

        Label label = new Label(message);

        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        HBox buttonsLayout = new HBox();
        buttonsLayout.setAlignment(Pos.CENTER);
        buttonsLayout.setSpacing(8);
        buttonsLayout.getChildren().addAll(yesButton, cancelButton);

        VBox modalWindowLayout = new VBox(4);
        modalWindowLayout.getChildren().addAll(label, buttonsLayout);
        modalWindowLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(modalWindowLayout);

        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

}
