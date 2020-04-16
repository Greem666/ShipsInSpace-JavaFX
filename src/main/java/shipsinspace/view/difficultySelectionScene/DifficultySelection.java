package shipsinspace.view.difficultySelectionScene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import shipsinspace.registers.GameRegister;
import shipsinspace.registers.ScenesRegister;
import shipsinspace.view.GameWindow;
import shipsinspace.view.homeScreenScene.HomeScreen;

import java.util.stream.Stream;

public class DifficultySelection {

    public Scene display() {

        Stage window = GameWindow.getPrimaryStage();

        ScenesRegister scenesRegister = ScenesRegister.getInstance();
        Scene nextScene = scenesRegister.getGameBoardScene();

        GameRegister gameRegister = GameRegister.getInstance();

        Image bkg = new Image(HomeScreen.class.getResourceAsStream("/backgrounds/background_galaxy_2.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(bkg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        // Layout
        VBox difficultySelectionLayout = new VBox();
        difficultySelectionLayout.setAlignment(Pos.CENTER);
        difficultySelectionLayout.setBackground(background);
        difficultySelectionLayout.setSpacing(20);

        // Label
        Text selectDifficultyText = new Text("Please select game difficulty:");
        selectDifficultyText.getStyleClass().add("difficultyText");

        // Difficulty buttons
        Button easyButton = new Button("Easy");
        easyButton.setOnAction(e -> {
            gameRegister.setGameDifficulty(1);
            window.setScene(nextScene);
        });
        Button mediumButton = new Button("Medium");
        mediumButton.setOnAction(e -> {
            gameRegister.setGameDifficulty(2);
            window.setScene(nextScene);
        });
        Button hardButton = new Button("Hard");
        hardButton.setOnAction(e -> {
            gameRegister.setGameDifficulty(3);
            window.setScene(nextScene);
        });

        Stream.of(easyButton, mediumButton, hardButton)
                .forEach(button -> {
                    button.getStyleClass().add("difficultyButtons");
                });

        difficultySelectionLayout.getChildren().addAll(selectDifficultyText, easyButton, mediumButton, hardButton);
        Scene difficultySelectionScene = new Scene(difficultySelectionLayout, 600, 600);
        difficultySelectionScene.getStylesheets().add(DifficultySelection.class.getResource("/css/difficultySelectionSceneStyles.css").toExternalForm());

        return difficultySelectionScene;
    }
}
