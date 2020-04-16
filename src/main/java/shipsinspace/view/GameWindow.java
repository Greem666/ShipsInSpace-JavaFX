package shipsinspace.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import shipsinspace.controller.GameController;
import shipsinspace.registers.ScenesRegister;
import shipsinspace.view.common.ConfirmBox;
import shipsinspace.view.difficultySelectionScene.DifficultySelection;
import shipsinspace.view.gameBoardScene.GameBoard;
import shipsinspace.view.gameOverScene.GameOver;
import shipsinspace.view.homeScreenScene.HomeScreen;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

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

        String path = getClass().getResource("/sounds/backgroundMusic/backgroundMusic.mp3").toString();
        Media sound = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

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
