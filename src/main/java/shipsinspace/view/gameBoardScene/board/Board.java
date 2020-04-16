package shipsinspace.view.gameBoardScene.board;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import shipsinspace.common.EventManager;
import shipsinspace.controller.player.Player;
import shipsinspace.controller.ships.ShipSegment;
import shipsinspace.registers.GameRegister;
import shipsinspace.common.Coordinates;
import shipsinspace.controller.GameController;
import shipsinspace.controller.ships.attackTypes.Attack;
import shipsinspace.registers.ScenesRegister;
import shipsinspace.registers.SoundsRegister;
import shipsinspace.view.GameWindow;
import shipsinspace.view.difficultySelectionScene.DifficultySelection;
import shipsinspace.view.gameOverScene.GameOver;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private double boardWidth;
    private double boardHeight;
    private int tilesCount = 11;

    private Attack ACTIVE_ATTACK;
    private GridPane board;

    private GameRegister gameRegister;
    private GameController backEndLogic;

    private Image shipPlayerImage = new Image(getClass().getResourceAsStream("/ships/shipSegmentPlayer.png"));
    private Image shipComputerImage = new Image(getClass().getResourceAsStream("/ships/shipSegmentEnemy2.png"));
    private Image shipPlayerDamagedImage = new Image(getClass().getResourceAsStream("/ships/shipSegmentPlayerDamaged.png"));
    private Image shipComputerDamagedImage = new Image(getClass().getResourceAsStream("/ships/shipSegmentEnemyDamaged.png"));

    private EventManager eventManager;

    public Board(GameController gameController, double boardWidth, double boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        gameRegister = GameRegister.getInstance();
        this.backEndLogic = gameController;
        this.ACTIVE_ATTACK = this.backEndLogic.getActiveAttack();
    }

    public Region generateElement() {

        Image bkg = new Image(getClass().getResourceAsStream("/backgrounds/background_galaxy.jpg"));


        char[] LETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int[] NUMBERS = IntStream.range(1, LETTERS.length).toArray();

        double tileBorderThickness = 1;
        double TILE_SIZE = boardWidth / tilesCount - 2 * tileBorderThickness;

        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(bkg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        // GAME BOARD
        GridPane gameBoardLayout = new GridPane();
        gameBoardLayout.setId("grid");
        gameBoardLayout.setBackground(background);

        for (int col = 0; col < tilesCount; col++) {
            for (int row = 0; row < tilesCount; row++) {

                Tile tile = new Tile(TILE_SIZE, TILE_SIZE, new Coordinates(row, col, 11, true));
                tile.getStyleClass().add("tiles");

                StackPane fieldStack = new StackPane();
                fieldStack.getChildren().add(tile);

                // PERSONALIZATION OF TILES & ADDING OF TEXT or EFFECTS/SHIPVIEW
                if (row == 0 || col ==0) {
                    Text text = new Text();
                    text.getStyleClass().add("boardMarkings");

                    fieldStack.getChildren().add(text);
                    gameBoardLayout.add(fieldStack, row, col);

                    tile.getStyleClass().add("boardMarkingTiles");

                    if (row > 0) {
                        text.setText(String.valueOf(NUMBERS[row - 1]));
                    } else if (col > 0) {
                        text.setText(String.valueOf(LETTERS[col - 1]).toUpperCase());
                    }
                } else {
                    ShipView shipView = new ShipView(TILE_SIZE);
                    Effects effects = new Effects(TILE_SIZE);

                    fieldStack.getChildren().addAll(shipView, effects);
                    gameBoardLayout.add(fieldStack, row, col);
                }

                if (col != 0 && row != 0) {

                    // FIELD SHOOTING MECHANIC
                    fieldStack.setOnMouseClicked(e -> {
                        if (e.getButton() == MouseButton.PRIMARY) {
                            carryOutGameTurn(fieldStack);
                        } else if (e.getButton() == MouseButton.SECONDARY) {
                            // For future, if different attacks get implemented
                        }
                    });

                    // HIGHLIGHTING TILES
                    fieldStack.setOnMouseEntered(t -> {
                        highlightActiveField(fieldStack);
                    });

                    fieldStack.setOnMouseExited(t -> {
                      unhighlightActiveField(fieldStack);
                    });
                }
            }
        }

        gameBoardLayout.getStylesheets().add(DifficultySelection.class.getResource("/css/gameBoardScene.css").toExternalForm());

        this.board = gameBoardLayout;

        this.drawPlayerShips(backEndLogic.getHumanPlayersFields(), shipPlayerImage, shipPlayerDamagedImage);
        this.drawPlayerShips(backEndLogic.getComputerPlayersFields(), shipComputerImage, shipComputerDamagedImage);

        return this.board;
    }

    public void drawHumanPlayerShips(List<ShipSegment> playerOccupiedFields) {
        drawPlayerShips(playerOccupiedFields, shipPlayerImage, shipPlayerDamagedImage);
    }

    public void drawComputerPlayerShips(List<ShipSegment> playerOccupiedFields) {
        drawPlayerShips(playerOccupiedFields, shipComputerImage, shipComputerDamagedImage);
    }

    public void drawPlayerShips(List<ShipSegment> playerOccupiedFields, Image imageShipFunctional, Image imageShipDestroyed) {
        for (ShipSegment shipSegment: playerOccupiedFields) {
            for (Node node: this.board.getChildren()) {
                StackPane stackPane = (StackPane) node;
                for (Node tileCandidate: stackPane.getChildren()) {
                    if (tileCandidate instanceof Tile) {
                        Tile tile = (Tile) tileCandidate;
                        if (tile.getCoordinates().equals(shipSegment)) {
                            ShipView shipView = (ShipView) stackPane.getChildren().get(1);
                            if (shipSegment.isVisible()) {
                                if (shipSegment.isDestroyed()) {
                                    shipView.setImage(imageShipDestroyed);
                                    shipView.startDamagedAnimation();
                                } else {
                                    shipView.setImage(imageShipFunctional);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void markHit(Coordinates coordinates) {
        for (Node node: this.board.getChildren()) {
            if (!(node instanceof StackPane)) {
                Tile tile = (Tile) node;
                if (tile.getCoordinates().equals(coordinates)) {
                    tile.setFill(Color.RED);
                }
            }
        }
    }

    public Effects getEffectElementOnAttackedPanel(Coordinates coordinates) {
        return (Effects) this.board.getChildren().stream()
                .map(e -> ((StackPane)e).getChildren())
                .filter(e -> ((Tile)e.get(0)).getCoordinates().equals(coordinates))
                .map(e -> e.get(2))
                .collect(Collectors.toList()).get(0);
    }

    public void carryOutGameTurn(StackPane clickedField) {
        carryOutHumanPlayerTurn(clickedField);
        checkGameStatus();

        carryOutComputerPlayerTurn();
        checkGameStatus();
    }

    public void carryOutHumanPlayerTurn(StackPane clickedField) {
        SoundsRegister.getInstance().playHumanPlayerShot();
        this.backEndLogic.gameTurn(getTileFrom(clickedField).getCoordinates());
        Player playerHitByHumanPlayer = gameRegister.getOwnerOfHitObjectHitThisTurnByHumanPlayer();
        getEffectsFrom(clickedField).animateHumanPlayerExplosion();
//                    this.markPlayerShips(backEndLogic.getHumanPlayersFields(), shipPlayerImage, shipPlayerDamagedImage);
//                    this.markPlayerShips(backEndLogic.getComputerPlayersFields(), shipComputerImage, shipComputerDamagedImage);

        redrawShipsOfHitParty(playerHitByHumanPlayer);
    }

    public void carryOutComputerPlayerTurn() {
//        Player playerHitByComputerPlayer = gameRegister.getOwnerOfHitObjectHitThisTurnByHumanPlayer();
        SoundsRegister.getInstance().playComputerPlayerShot();
        Player playerHitByComputerPlayer = gameRegister.getOwnerOfHitObjectHitThisTurnByComputerPlayer();
        Coordinates coordinatesAttackedByComputer = gameRegister.getCoordinatesComputerPlayerShotAtThisTurn();
        getEffectElementOnAttackedPanel(coordinatesAttackedByComputer).animateComputerPlayerExplosion();

        redrawShipsOfHitParty(playerHitByComputerPlayer);
    }

    public void checkGameStatus() {
        if (gameRegister.getGameStatus().equals("human_lost") || gameRegister.getGameStatus().equals("computer_lost")) {
            //TODO: Singleton to receive information about player who lost all ships
            //TODO: Some game end animation, before scene switch
            Stage window = ScenesRegister.getInstance().getWindow();
            Scene gameOverScene = ScenesRegister.getInstance().getGameOverScene();
            window.setScene(gameOverScene);
        }
    }

    public void redrawShipsOfHitParty(Player playerHit) {
        if (playerHit != null) {
            SoundsRegister.getInstance().playExplosionSound();
            if (playerHit.getName().equals("Computer")) {
                drawComputerPlayerShips(backEndLogic.getComputerPlayersFields());
            } else {
                drawHumanPlayerShips(backEndLogic.getHumanPlayersFields());
            }
            //TODO: Check if any ships left in game
        } else {
            //TODO: Animate miss
        }
    }

    private void highlightActiveField(StackPane activeField) {
        getTileFrom(activeField).setStroke(Color.RED);
        getShipViewFrom(activeField).startRotatingShip();

    }

    private void unhighlightActiveField(StackPane activeField) {
        getTileFrom(activeField).setStroke(Color.WHITE);
        getShipViewFrom(activeField).stopRotatingShip();
    }

    private Tile getTileFrom(StackPane stackPane) {
        return (Tile) stackPane.getChildren().stream()
                .filter(node -> node instanceof Tile)
                .collect(Collectors.toList()).get(0);
    }

    private ShipView getShipViewFrom(StackPane stackPane) {
        return (ShipView) stackPane.getChildren().stream()
                .filter(node -> node instanceof ShipView)
                .collect(Collectors.toList()).get(0);
    }

    private Effects getEffectsFrom(StackPane stackPane) {
        return (Effects) stackPane.getChildren().stream()
                .filter(node -> node instanceof Effects)
                .collect(Collectors.toList()).get(0);
    }
}
