package shipsinspace.view.gameOverScene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import shipsinspace.registers.GameRegister;
import shipsinspace.registers.ScenesRegister;
import shipsinspace.registers.SoundsRegister;
import shipsinspace.view.GameWindow;
import shipsinspace.view.common.topmenu.TopMenu;
import shipsinspace.view.difficultySelectionScene.DifficultySelection;

import java.util.stream.Stream;

public class GameOver {

    public Scene display() {

        Stage window = GameWindow.getPrimaryStage();
        ScenesRegister scenesRegister = ScenesRegister.getInstance();

        Image bkg = new Image(getClass().getResourceAsStream("/backgrounds/background_stars.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(bkg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        // MenuBar
        Region menuBar = new TopMenu().generateElement();


        // Layout
        VBox gameOverLayout = new VBox();
        gameOverLayout.setAlignment(Pos.CENTER);
        gameOverLayout.setBackground(background);
        gameOverLayout.setSpacing(20);

        // Window layout
        BorderPane homeScreenObjectsLayout = new BorderPane();
        homeScreenObjectsLayout.setTop(menuBar);
        homeScreenObjectsLayout.setCenter(gameOverLayout);

        // Would you like to play again label
        Text youWonOrLostText = new Text();
        if (GameRegister.getInstance().getGameStatus() == "human_lost") {
            youWonOrLostText.setText("You Lost!");
        } else {
            youWonOrLostText.setText("You Won!");
        }
        youWonOrLostText.getStyleClass().add("youWonOrLostText");

        // Would you like to play again label
        Text gameOverText = new Text("Would you like to play again?");
        gameOverText.getStyleClass().add("gameOverText");

        // Play again button
        Button playAgainButton = new Button("Play again");
        playAgainButton.setOnAction(e -> {
            SoundsRegister.getInstance().playButtonClickSound();
            scenesRegister.resetScenesRegister();
            Scene nextScene = scenesRegister.getHomeScreenScene();
            window.setScene(nextScene);
        });

        // Quit button
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            SoundsRegister.getInstance().playButtonClickSound();
            window.close();
        });

        Stream.of(playAgainButton, quitButton)
                .forEach(button -> {
                    button.getStyleClass().add("gameOverButtons");
                });

        gameOverLayout.getChildren().addAll(youWonOrLostText, gameOverText, playAgainButton, quitButton);
        Scene gameOverScene = new Scene(homeScreenObjectsLayout, ScenesRegister.sceneWidth, ScenesRegister.sceneHeight);
        gameOverScene.getStylesheets().add(DifficultySelection.class.getResource("/css/gameOverScene/gameOverSceneStyles.css").toExternalForm());

        return gameOverScene;
    }
}
