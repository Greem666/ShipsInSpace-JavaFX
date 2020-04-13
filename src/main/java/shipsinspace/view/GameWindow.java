package shipsinspace.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shipsinspace.controller.GameController;
import shipsinspace.view.common.ConfirmBox;
import shipsinspace.view.difficultySelectionScene.DifficultySelection;
import shipsinspace.view.gameBoardScene.GameBoard;
import shipsinspace.view.homeScreenScene.HomeScreen;

public class GameWindow extends Application {
    // window
    private static Stage window;

    private Scene gameScene, difficultySelectionScene, homeScreenScene;


    public static Stage getPrimaryStage() {
        return window;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // window
        window = primaryStage;
        window.setTitle("Ships... but in space!");
        window.setResizable(false);

        gameScene = new GameBoard(new GameController()).display();
        difficultySelectionScene = new DifficultySelection().display(gameScene);
        homeScreenScene = new HomeScreen().display(difficultySelectionScene);

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        // DIFFICULTY SELECTION

        // START WINDOW

        window.setScene(homeScreenScene);
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
