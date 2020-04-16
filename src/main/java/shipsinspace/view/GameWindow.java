package shipsinspace.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shipsinspace.registers.ScenesRegister;
import shipsinspace.registers.SoundsRegister;
import shipsinspace.view.common.ConfirmBox;

public class GameWindow extends Application {
    // window
    private static Stage window;
    private ScenesRegister scenesRegister = ScenesRegister.getInstance();

    public static Stage getPrimaryStage() {
        return window;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        SoundsRegister.getInstance().playBackgroundMusic();

        // window
        window = primaryStage;
        window.setTitle("Ships... but in space!");
        window.setResizable(false);

        scenesRegister.setWindow(window);

        Scene nextScene = scenesRegister.getHomeScreenScene();

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        // START WINDOW

        window.setScene(nextScene);
        window.show();

    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Close program", "Sure, you want to exit \"Ships... but in Space\"?");
        Stage window = GameWindow.getPrimaryStage();
        if (answer) {
            window.close();
        }
    }
}
