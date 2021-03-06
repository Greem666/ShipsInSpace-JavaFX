package shipsinspace.registers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import shipsinspace.controller.GameController;
import shipsinspace.view.difficultySelectionScene.DifficultySelection;
import shipsinspace.view.gameBoardScene.GameBoard;
import shipsinspace.view.gameOverScene.GameOver;
import shipsinspace.view.homeScreenScene.HomeScreen;

public class ScenesRegister {
    private static ScenesRegister instance;
    private Scene gameOverScene, gameBoardScene, difficultySelectionScene, homeScreenScene;
    private Stage window;
    public static final int sceneWidth = 600;
    public static final int sceneHeight = 625;
    public GameBoard gameBoard;

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

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
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
