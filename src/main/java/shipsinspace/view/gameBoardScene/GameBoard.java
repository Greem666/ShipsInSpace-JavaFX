package shipsinspace.view.gameBoardScene;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import shipsinspace.controller.GameController;
import shipsinspace.registers.GameRegister;
import shipsinspace.registers.ScenesRegister;
import shipsinspace.view.GameWindow;
import shipsinspace.view.difficultySelectionScene.DifficultySelection;
import shipsinspace.view.gameBoardScene.board.Board;
import shipsinspace.view.gameBoardScene.bottomMenu.BottomMenu;
import shipsinspace.view.gameOverScene.GameOver;

public class GameBoard {
    private GameController backEnd;

    public GameBoard(GameController gameController) {
        this.backEnd = gameController;
    }

    public Scene display() {

        this.backEnd.gameStatusReset();

        ScenesRegister scenesRegister = ScenesRegister.getInstance();
        Scene nextScene = scenesRegister.getGameOverScene();


        int WINDOW_WIDTH = 600;
        int WINDOW_HEIGHT = 600;

        Board board = new Board(this.backEnd, 600, 600);
//        BottomMenu bottomMenu = new BottomMenu(600, 200, 10);

        // GAME SECTION

        // window
        BorderPane windowLayout = new BorderPane();
        windowLayout.setCenter(board.generateElement());
//        windowLayout.setBottom(bottomMenu.generateElement());

        // Scene
        Scene gameScene = new Scene(windowLayout, WINDOW_WIDTH, WINDOW_HEIGHT);

        return gameScene;
    }

    public void resetGameBoard() {

    }
}