package shipsinspace.view.gameOverScene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import shipsinspace.gameRegister.GameRegister;
import shipsinspace.view.GameWindow;
import shipsinspace.view.difficultySelectionScene.DifficultySelection;
import shipsinspace.view.homeScreenScene.HomeScreen;

import java.util.stream.Stream;

public class GameOver {

    public static Scene display(Scene nextScene) {

        Stage window = GameWindow.getPrimaryStage();
        GameRegister gameRegister = GameRegister.getInstance();

        Image bkg = new Image(HomeScreen.class.getResourceAsStream("/backgrounds/background_stars.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(bkg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        // Layout
        VBox gameOverLayout = new VBox();
        gameOverLayout.setAlignment(Pos.CENTER);
        gameOverLayout.setBackground(background);
        gameOverLayout.setSpacing(20);

        // Label
        Text gameOverText = new Text("Would you like to play again?");
        gameOverText.getStyleClass().add("gameOverText");

        // Difficulty buttons
        Button playAgainButton = new Button("Play again");
        playAgainButton.setOnAction(e -> {
            window.setScene(nextScene);
        });
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            window.close();
        });

        Stream.of(playAgainButton, quitButton)
                .forEach(button -> {
                    button.getStyleClass().add("gameOverButtons");
                });

        gameOverLayout.getChildren().addAll(gameOverText, playAgainButton, quitButton);
        Scene gameOverScene = new Scene(gameOverLayout, 600, 600);
        gameOverScene.getStylesheets().add(DifficultySelection.class.getResource("/css/gameOverSceneStyles.css").toExternalForm());

        return gameOverScene;
    }
}
