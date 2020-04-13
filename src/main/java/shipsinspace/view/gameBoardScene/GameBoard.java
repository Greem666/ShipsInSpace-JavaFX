package shipsinspace.view.gameBoardScene;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import shipsinspace.controller.GameController;
import shipsinspace.gameRegister.GameRegister;
import shipsinspace.view.GameWindow;
import shipsinspace.view.common.ConfirmBox;
import shipsinspace.view.gameBoardScene.board.Board;
import shipsinspace.view.gameBoardScene.bottomMenu.BottomMenu;

public class GameBoard {
    private GameController backEnd;

    public GameBoard(GameController gameController) {
        this.backEnd = gameController;
    }

    public Scene display() {

        int WINDOW_WIDTH = 600;
        int WINDOW_HEIGHT = 800;

        Stage window = GameWindow.getPrimaryStage();
        GameRegister gameRegister = GameRegister.getInstance();
//        GameController backEnd = gameRegister.getGameController();

        Board board = new Board(this.backEnd);
        BottomMenu bottomMenu = new BottomMenu();

        // GAME SECTION

        // window
        BorderPane windowLayout = new BorderPane();
        windowLayout.setCenter(board.generateElement(WINDOW_WIDTH, WINDOW_HEIGHT, 11));
        windowLayout.setBottom(bottomMenu.generateElement(WINDOW_WIDTH, WINDOW_HEIGHT, 11));

        // Scene
        Scene gameScene = new Scene(windowLayout, WINDOW_WIDTH, WINDOW_HEIGHT);

        return gameScene;
    }
}