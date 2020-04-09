package shipsinspace.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import shipsinspace.view.board.Board;
import shipsinspace.view.bottomMenu.BottomMenu;
import shipsinspace.view.topMenu.TopMenu;

public class GameBoard extends Application {
    // window
    private Stage window;
    // window
    private int WINDOW_WIDTH = 600;
    private int WINDOW_HEIGHT = 800;

    // LOGIC
    private String LAST_CLICKED_FIELD;

    private TopMenu topMenu = new TopMenu();
    private Board board = new Board();
    private BottomMenu bottomMenu = new BottomMenu();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // window
        window = primaryStage;
        window.setTitle("Ships... but in space!");
        window.setResizable(false);

        // window
        BorderPane windowLayout = new BorderPane();
        windowLayout.setTop(topMenu.generateElement(WINDOW_WIDTH, WINDOW_HEIGHT));
        windowLayout.setCenter(board.generateElement(WINDOW_WIDTH, WINDOW_HEIGHT, 11));
        windowLayout.setBottom(bottomMenu.generateElement(WINDOW_WIDTH, WINDOW_HEIGHT, 11));

        // window
        Scene gameScene = new Scene(windowLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setScene(gameScene);
        window.show();

    }
}
