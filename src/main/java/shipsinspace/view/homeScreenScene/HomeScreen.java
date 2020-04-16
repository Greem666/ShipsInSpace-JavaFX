package shipsinspace.view.homeScreenScene;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import shipsinspace.registers.ScenesRegister;
import shipsinspace.view.GameWindow;
import shipsinspace.view.difficultySelectionScene.DifficultySelection;

public class HomeScreen {

    public Scene display() {
        Stage window = GameWindow.getPrimaryStage();

        ScenesRegister scenesRegister = ScenesRegister.getInstance();
        Scene nextScene = scenesRegister.getDifficultySelectionScene();

        Image bkg = new Image(HomeScreen.class.getResourceAsStream("/backgrounds/background_stars.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(bkg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        // Title
        Text titleText = new Text("Ships...\nbut in SPACE!!!");
        titleText.setId("titleText");

        Text pressAnyKeyText = new Text("Click mouse to continue...");
        pressAnyKeyText.setId("pressAnyKeyText");

        FadeTransition pressAnyKeyTextFadeTransition = new FadeTransition(Duration.seconds(2.0), pressAnyKeyText);
        pressAnyKeyTextFadeTransition.setFromValue(1.0);
        pressAnyKeyTextFadeTransition.setToValue(0.0);
        pressAnyKeyTextFadeTransition.setCycleCount(Animation.INDEFINITE);
        pressAnyKeyTextFadeTransition.setAutoReverse(true);
        pressAnyKeyTextFadeTransition.play();

        // Layout
        VBox homeScreenTextLayout = new VBox();
        homeScreenTextLayout.setAlignment(Pos.CENTER);
        homeScreenTextLayout.setSpacing(40);
        homeScreenTextLayout.setBackground(background);
        homeScreenTextLayout.getChildren().addAll(titleText, pressAnyKeyText);

        Scene homeScreenScene = new Scene(homeScreenTextLayout, 600, 600);
        homeScreenScene.getStylesheets().add(DifficultySelection.class.getResource("/css/homeScreenSceneStyles.css").toExternalForm());

        homeScreenScene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            window.setScene(nextScene);
        });

        return homeScreenScene;
    }
}
