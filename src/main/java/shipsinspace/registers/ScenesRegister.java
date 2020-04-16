package shipsinspace.registers;

import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import shipsinspace.controller.GameController;
import shipsinspace.view.difficultySelectionScene.DifficultySelection;
import shipsinspace.view.gameBoardScene.GameBoard;
import shipsinspace.view.gameOverScene.GameOver;
import shipsinspace.view.homeScreenScene.HomeScreen;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

public class ScenesRegister {
    private static ScenesRegister instance;
    private Scene gameOverScene, gameBoardScene, difficultySelectionScene, homeScreenScene;
    private Stage window;

    private ScenesRegister() {
    }

    public static ScenesRegister getInstance() {
        if (instance == null) {
            instance = new ScenesRegister();
        }
        return instance;
    }

    public void resetScenesRegister() {
        gameOverScene = gameBoardScene = difficultySelectionScene = homeScreenScene = null;
    }

    public Scene getGameOverScene() {
        if (this.gameOverScene == null) {
            this.gameOverScene = new GameOver().display();
        }
        return gameOverScene;
    }

    public Scene getGameBoardScene() {
        if (this.gameBoardScene == null) {
            this.gameBoardScene = new GameBoard(new GameController()).display();
        }
        return gameBoardScene;
    }

    public Scene getDifficultySelectionScene() {
        if (this.difficultySelectionScene == null) {
            this.difficultySelectionScene = new DifficultySelection().display();
        }
        return difficultySelectionScene;
    }

    public Scene getHomeScreenScene() {
        if (this.homeScreenScene == null) {
            this.homeScreenScene = new HomeScreen().display();
        }
        return homeScreenScene;
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        if (this.window == null) {
            this.window = window;
        }
    }
}
