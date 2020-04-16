package shipsinspace.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import shipsinspace.view.gameBoardScene.board.Effects;

public class Test extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox();
        hBox.setMinHeight(200);
        hBox.setMinWidth(200);
        Effects effects = new Effects(200);
        hBox.setOnMouseClicked(e -> effects.animateHumanPlayerExplosion());
        hBox.getChildren().add(effects);
        primaryStage.setScene(new Scene(hBox, 200, 200));

        primaryStage.show();
    }
}
