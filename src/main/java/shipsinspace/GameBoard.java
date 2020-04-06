package shipsinspace;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class GameBoard extends Application {

    private Stage window;
    private Image bkg = new Image("file:resources/backgrounds/background_galaxy.jpg");
    private Image ship_2a = new Image("file:resources/ships/2_space_a.png", 25.0, 50.0, true, true);
    private Image ship_2b = new Image("file:resources/ships/2_space_b.png", 25.0, 50.0, true, true);
    private Image ship_3 = new Image("file:resources/ships/3_space.png", 25.0, 75.0, true, true);
    private Image ship_4 = new Image("file:resources/ships/4_space.png", 25.0, 100.0, true, true);
    private Image ship_5 = new Image("file:resources/ships/5_space.png", 25.0, 125.0, true, true);

    private double BOARD_SIZE = 11;
    private char[] LETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private int[] NUMBERS = IntStream.range(1, LETTERS.length).toArray();
    private double WINDOW_WIDTH = 900;
    private double WINDOW_HEIGHT = 900;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Ships... but in space!");

        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(bkg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        // Top menu buttons
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuAbout = new Menu("About");
        menuBar.getMenus().addAll(menuFile, menuAbout);

        // Attack buttons
        Button standardAttackAction = new Button("Attack");
        Button scoutAction = new Button("Scout");
        Button sonarAction = new Button("Sonar");
        Button roundOfFireAttackAction = new Button("Round of Fire");
        Button railgunAttackAction = new Button("Railgun");
        Button bombardmentAttackAction = new Button("Bombardment");

        HBox attackButtonsPanel = new HBox();
        attackButtonsPanel.getChildren().addAll(standardAttackAction, scoutAction, sonarAction,
                roundOfFireAttackAction, railgunAttackAction, bombardmentAttackAction);
        attackButtonsPanel.setAlignment(Pos.CENTER);
        attackButtonsPanel.setSpacing(8);
        attackButtonsPanel.setBorder(
                new Border(
                        new BorderStroke(
                                Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(10),
                                BorderWidths.DEFAULT
                        )
                )
        );
        attackButtonsPanel.setPadding(new Insets(10, 10, 10 ,10));

        // Game board
        GridPane gameBoardLayout = new GridPane();
        gameBoardLayout.setBackground(background);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {

                Rectangle tile = new Rectangle(WINDOW_WIDTH / 11, WINDOW_HEIGHT / 11);
                tile.setFill(Color.TRANSPARENT);
                tile.setStroke(Color.WHITE);
                tile.setStrokeWidth(2);

                // Highlighting tiles
                if (i != 0 && j != 0) {
//                    tile.setOnMouseEntered(t -> tile.setFill(Color.rgb(210, 210, 210, 0.58)));
                    tile.setOnMouseEntered(t -> tile.setFill(Color.rgb(210, 210, 210, 0.58)));
                    tile.setOnMouseExited(t -> tile.setFill(Color.TRANSPARENT));
                }


                if (i == 0 && j > 0) {
                    Text text = new Text(String.valueOf(NUMBERS[j - 1]));
                    text.setFont(Font.font(40));
                    text.setFill(Color.WHITE);
                    text.setStrokeWidth(1);
                    text.setStroke(Color.BLACK);
                    gameBoardLayout.add(new StackPane(tile, text), j, i);
                }

                if (j == 0 && i > 0) {
                    Text text = new Text(String.valueOf(LETTERS[i - 1]));
                    text.setFont(Font.font(40));
                    text.setFill(Color.WHITE);
                    text.setStrokeWidth(1);
                    text.setStroke(Color.BLACK);
                    gameBoardLayout.add(new StackPane(tile, text), j, i);
                }
                gameBoardLayout.add(tile, j, i);


                //GridPane.setRowIndex(tile, i);
                //GridPane.setColumnIndex(tile, j);
                //gameBoard.getChildren().addAll(tile, text);
//                tile.setOnMouseClicked(event -> drawMove(text));
            }
        }


        BorderPane windowLayout = new BorderPane();
        windowLayout.setTop(menuBar);
        windowLayout.setCenter(gameBoardLayout);
        windowLayout.setBottom(attackButtonsPanel);

        Scene gameScene = new Scene(windowLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setScene(gameScene);

        window.show();

    }
}
