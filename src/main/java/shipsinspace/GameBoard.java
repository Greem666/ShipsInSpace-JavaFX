package shipsinspace;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class GameBoard extends Application {

    private double SHIP_SEGMENT_SIZE = 30;
    private double SHIP_WIDTH = SHIP_SEGMENT_SIZE;

    private Stage window;
    private Image bkg = new Image(getClass().getResourceAsStream("/backgrounds/background_galaxy.jpg"));


    private char[] LETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private int[] NUMBERS = IntStream.range(1, LETTERS.length).toArray();

    private double BOARD_SIZE = 11;
    private double GAME_BOARD_HEIGHT = 600;
    private double GAME_BOARD_WIDTH = GAME_BOARD_HEIGHT;
    private double TILE_BORDER_THICKNESS = 1;
    private double TILE_SIZE = GAME_BOARD_HEIGHT / BOARD_SIZE - 2 * TILE_BORDER_THICKNESS;

    private double BOTTOM_MENU_HEIGHT = 300;
    private double ICON_SIZE = BOTTOM_MENU_HEIGHT * 0.1;

    private double WINDOW_WIDTH = GAME_BOARD_WIDTH;
    private double WINDOW_HEIGHT = GAME_BOARD_HEIGHT + BOTTOM_MENU_HEIGHT;

    private String ACTIVE_ATTACK;
    private String LAST_CLICKED_FIELD;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Ships... but in space!");
        window.setResizable(false);

        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(bkg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        // TOP MENU BUTTONS
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuAbout = new Menu("About");
        menuBar.getMenus().addAll(menuFile, menuAbout);

        // ATTACK BUTTONS PANEL
        VBox attackButtonsPanel = new VBox();

        Label attacksLabel = new Label("Available attacks:");
        attackButtonsPanel.getChildren().add(attacksLabel);

        GridPane attackButtons = new GridPane();
        final ToggleGroup attackGroup = new ToggleGroup();

        String[][] buttonsData = {
                {"standard.png", "Standard attack", "y"},
                {"scout.png", "Scout action", "n"},
                {"sonar.png", "Scout action", "n"},
                {"roundOfFire.png", "Round of Fire attack", "n"},
                {"railgun.png", "Railgun attack", "n"},
                {"bombardment.png", "Bombardment attack", "n"}
        };

        for (int i = 0; i < buttonsData.length; i++) {
            Image icon = new Image(getClass().getResourceAsStream("/icons/" + buttonsData[i][0]), ICON_SIZE, ICON_SIZE, true, true);
            ToggleButton button = new ToggleButton("", new ImageView(icon));
            button.setTooltip(new Tooltip(buttonsData[i][1]));

            button.setToggleGroup(attackGroup);

            final String attackType = buttonsData[i][1];
            button.setOnAction(e -> ACTIVE_ATTACK = attackType);

            if (buttonsData[i][2] == "y") {
                button.setSelected(true);
                button.fire();
            }

            attackButtons.add(button, i % 3, i / 3);
        }

        attackButtons.setAlignment(Pos.CENTER);
        attackButtons.setBorder(
                new Border(
                        new BorderStroke(
                                Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(10),
                                BorderWidths.DEFAULT
                        )
                )
        );
        attackButtons.setPadding(new Insets(10, 10, 10 ,10));
        attackButtonsPanel.getChildren().add(attackButtons);


        // PLAYER SHIPS SECTION
        GridPane playerShipsStatusDisplay = new GridPane();

        Label humanShipsCaption = new Label("Player ships:");
        playerShipsStatusDisplay.add(humanShipsCaption, 0, 0);

        String[][] ships = {
                {"Corvette", "2"},
                {"Scout", "2"},
                {"Destroyer", "3"},
                {"Battleship", "4"},
                {"Carrier", "5"},
        };

        for (int i = 0; i < ships.length; i++) {
            Image shipImage = new Image(getClass().getResourceAsStream("/ships/" + ships[i][0] + ".png"), TILE_SIZE * Integer.parseInt(ships[i][1]), TILE_SIZE, true, true);
            Button shipButton = new Button("", new ImageView(shipImage));
            playerShipsStatusDisplay.add(shipButton, 0, i + 1);
            shipButton.setTooltip(new Tooltip(ships[i][0]));

            ProgressIndicator healthIndicator = new ProgressIndicator();
            healthIndicator.setProgress(0.0);
            playerShipsStatusDisplay.add(healthIndicator, 1, i + 1);
        }

        playerShipsStatusDisplay.setAlignment(Pos.TOP_CENTER);
        playerShipsStatusDisplay.setBorder(
                new Border(
                        new BorderStroke(
                                Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(10),
                                BorderWidths.DEFAULT
                        )
                )
        );
        playerShipsStatusDisplay.setPadding(new Insets(0, 10, 0 ,10));

        // COMPUTER SHIPS SECTION
        VBox computerShipsStatusDisplay = new VBox();
        computerShipsStatusDisplay.setSpacing(BOTTOM_MENU_HEIGHT * 0.1);
        Label computerShipsCaption = new Label("Computer ships:");
        Button computerCorvetteShipButton = new Button("Corvette");
        Button computerScoutShipButton = new Button("Scout");
        Button computerDestroyerShipButton = new Button("Destroyer");
        Button computerBattleshipShipButton = new Button("Battleship");
        Button computerCarrierShipButton = new Button("Carrier");
        computerShipsStatusDisplay.getChildren().addAll(
                computerShipsCaption,
                computerCorvetteShipButton,
                computerScoutShipButton,
                computerDestroyerShipButton,
                computerBattleshipShipButton,
                computerCarrierShipButton
        );
        computerShipsStatusDisplay.setAlignment(Pos.TOP_CENTER);
        computerShipsStatusDisplay.setSpacing(8);
        computerShipsStatusDisplay.setBorder(
                new Border(
                        new BorderStroke(
                                Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(10),
                                BorderWidths.DEFAULT
                        )
                )
        );
        computerShipsStatusDisplay.setPadding(new Insets(0, 10, 0 ,10));

        // BOTTOM CONTROL PANEL
        HBox bottomControlPanel = new HBox();
        bottomControlPanel.getChildren().addAll(attackButtonsPanel, playerShipsStatusDisplay, computerShipsStatusDisplay);
        bottomControlPanel.setSpacing(WINDOW_WIDTH * 0.1);
//        bottomControlPanel.setMinHeight(BOTTOM_MENU_HEIGHT);
        bottomControlPanel.setAlignment(Pos.CENTER);

        // GAME BOARD
        GridPane gameBoardLayout = new GridPane();

        gameBoardLayout.setMaxHeight(GAME_BOARD_HEIGHT);
        gameBoardLayout.setMaxWidth(GAME_BOARD_WIDTH);
        gameBoardLayout.setBackground(background);
        gameBoardLayout.setAlignment(Pos.CENTER);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {

                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                tile.setFill(Color.TRANSPARENT);
                tile.setStroke(Color.WHITE);
                tile.setStrokeWidth(TILE_BORDER_THICKNESS);

                tile.setOnMouseClicked(e -> AlertWindow.display("Action", ACTIVE_ATTACK));

                // Highlighting tiles
                if (i != 0 && j != 0) {
//                    tile.setOnMouseEntered(t -> tile.setFill(Color.rgb(210, 210, 210, 0.58)));
                    tile.setOnMouseEntered(t -> tile.setFill(Color.rgb(210, 210, 210, 0.58)));
                    tile.setOnMouseExited(t -> tile.setFill(Color.TRANSPARENT));
                }

                if (i == 0 && j > 0) {
                    tile.setFill(Color.rgb(0, 0, 0, 0.5));
                    Text text = new Text(String.valueOf(NUMBERS[j - 1]));
                    text.setFont(Font.font(40));
                    text.setFill(Color.WHITE);
                    text.setStrokeWidth(1);
                    text.setStroke(Color.BLACK);
                    gameBoardLayout.add(new StackPane(tile, text), j, i);
                }

                if (j == 0 && i > 0) {
                    tile.setFill(Color.rgb(0, 0, 0, 0.5));
                    Text text = new Text(String.valueOf(LETTERS[i - 1]).toUpperCase());
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
        windowLayout.setBottom(bottomControlPanel);

        Scene gameScene = new Scene(windowLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setScene(gameScene);

        window.show();

    }
}
