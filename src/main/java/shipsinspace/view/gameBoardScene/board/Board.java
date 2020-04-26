package shipsinspace.view.gameBoardScene.board;

import javafx.animation.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import shipsinspace.controller.player.Player;
import shipsinspace.controller.ships.ShipSegment;
import shipsinspace.registers.GameRegister;
import shipsinspace.common.Coordinates;
import shipsinspace.controller.GameController;
import shipsinspace.controller.ships.attackTypes.Attack;
import shipsinspace.registers.ScenesRegister;
import shipsinspace.registers.SoundsRegister;
import shipsinspace.view.gameBoardScene.board.elements.Effects;
import shipsinspace.view.gameBoardScene.board.elements.ShipView;
import shipsinspace.view.gameBoardScene.board.elements.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private double boardWidth;
    private double boardHeight;
    private int tilesCount = 11;

    private Attack ACTIVE_ATTACK;
    private StackPane board;

    private GameRegister gameRegister;
    private GameController backEndLogic;

    private final Image shipPlayerImage = new Image(getClass().getResourceAsStream("/ships/shipSegmentPlayer.png"));
    private final Image shipComputerImage = new Image(getClass().getResourceAsStream("/ships/shipSegmentEnemy2.png"));
    private final Image shipPlayerDamagedImage = new Image(getClass().getResourceAsStream("/ships/shipSegmentPlayerDamaged.png"));
    private final Image shipComputerDamagedImage = new Image(getClass().getResourceAsStream("/ships/shipSegmentEnemyDamaged.png"));

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
        GridPane gameBoardTilesLayout = new GridPane();
        gameBoardTilesLayout.setId("grid");
        gameBoardTilesLayout.setBackground(background);

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
                    gameBoardTilesLayout.add(fieldStack, row, col);

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
                    gameBoardTilesLayout.add(fieldStack, row, col);
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

        StackPane gameBoardLayout = new StackPane();

        Rectangle bgRect = new Rectangle(600, 600);
        bgRect.setFill(Color.TRANSPARENT);
        bgRect.setStroke(Color.BLACK);
        bgRect.setStrokeWidth(2);
        bgRect.setMouseTransparent(true);

        Rectangle backgroundShadow = new Rectangle(600, 600);
        backgroundShadow.setFill(Color.TRANSPARENT);
        backgroundShadow.setMouseTransparent(true);

        Text announcementText = new Text();
        announcementText.setId("announcement");
        announcementText.setMouseTransparent(true);
        announcementText.setTextAlignment(TextAlignment.CENTER);
        announcementText.setFont(Font.font("Verdena", FontWeight.BOLD, 45));
        announcementText.setFill(Color.TRANSPARENT);

        gameBoardTilesLayout.getStylesheets().add(Board.class.getResource("/css/gameBoardScene/gameBoardScene.css").toExternalForm());
        gameBoardLayout.getChildren().addAll(gameBoardTilesLayout, backgroundShadow, announcementText, bgRect);

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
            for (Node stackPaneNode: this.board.getChildren()) {
                if (stackPaneNode instanceof GridPane) {
                    GridPane gridPane = (GridPane) stackPaneNode;
                    for (Node gridPaneNode: gridPane.getChildren()) {
                        StackPane stackPane = (StackPane) gridPaneNode;
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
        }
    }

    public Effects getEffectElementOnAttackedPanel(Coordinates coordinates) {
        return (Effects) ((GridPane)this.board.getChildren().get(0)).getChildren().stream()
                .map(e -> ((StackPane)e).getChildren())
                .filter(e -> ((Tile)e.get(0)).getCoordinates().equals(coordinates))
                .map(e -> e.get(2))
                .collect(Collectors.toList()).get(0);
    }

    public void carryOutGameTurn(StackPane clickedField) {
        // Human turn
        disableHumanInput(true);
        carryOutHumanPlayerTurn(clickedField);
        checkGameStatus();

        // Computer turn
        PauseTransition afterComputerMoveDelay = new PauseTransition(Duration.seconds(0.5));
        announceComputerTurn().setOnFinished(e -> {
            carryOutComputerPlayerTurn();
            checkGameStatus();
            afterComputerMoveDelay.setOnFinished(f -> {
                announcePlayerTurn().setOnFinished(g -> {
                    disableHumanInput(false);
                });
            });
            afterComputerMoveDelay.play();
        });
    }

    public FillTransition announcePlayerTurn() {
        return announceTurnOwner(gameRegister.getHumanPlayerName());
    }

    public FillTransition announceComputerTurn() {
        return announceTurnOwner(gameRegister.getComputerPlayerName());
    }

    public FillTransition announceTurnOwner(String turnOwner) {
        Text text = (Text) this.board.getChildren().get(2);
        text.setText(turnOwner + "'s turn");
        FillTransition textDisplayTransition = new FillTransition(Duration.seconds(1.0), text, Color.TRANSPARENT, Color.YELLOW);
        textDisplayTransition.setAutoReverse(true);
        textDisplayTransition.setCycleCount(2);
        textDisplayTransition.play();

        return textDisplayTransition;
    }

    private void disableHumanInput(boolean isDisabled) {
        EventHandler<MouseEvent> handler = MouseEvent::consume;

        if (isDisabled) {
            // block events
            this.board.getChildren().stream()
                    .filter(node -> node instanceof GridPane)
                    .flatMap(node -> ((GridPane) node).getChildren().stream())
                    .forEach(node -> ((StackPane) node).addEventFilter(MouseEvent.MOUSE_CLICKED, handler));
        } else {
            // restore events
            this.board.getChildren().stream()
                    .filter(node -> node instanceof GridPane)
                    .flatMap(node -> ((GridPane) node).getChildren().stream())
                    .forEach(node -> ((StackPane) node).removeEventFilter(MouseEvent.MOUSE_CLICKED, handler));
        }
    }

    public void carryOutHumanPlayerTurn(StackPane clickedField) {
        SoundsRegister.getInstance().playHumanPlayerShot();
        this.backEndLogic.gameTurn(getTileFrom(clickedField).getCoordinates());
        Player playerHitByHumanPlayer = gameRegister.getOwnerOfHitObjectHitThisTurnByHumanPlayer();
        getEffectsFrom(clickedField).animateHumanPlayerExplosion();

        ScenesRegister.getInstance().getGameBoard().updateSidePanelShipStatus(playerHitByHumanPlayer);

        redrawShipsOfHitParty(playerHitByHumanPlayer);
    }

    public void carryOutComputerPlayerTurn() {
        SoundsRegister.getInstance().playComputerPlayerShot();
        Player playerHitByComputerPlayer = gameRegister.getOwnerOfHitObjectHitThisTurnByComputerPlayer();
        Coordinates coordinatesAttackedByComputer = gameRegister.getCoordinatesComputerPlayerShotAtThisTurn();
        getEffectElementOnAttackedPanel(coordinatesAttackedByComputer).animateComputerPlayerExplosion();

        ScenesRegister.getInstance().getGameBoard().updateSidePanelShipStatus(playerHitByComputerPlayer);

        redrawShipsOfHitParty(playerHitByComputerPlayer);
    }

    public void checkGameStatus() {
        Transition animation = new SequentialTransition();
        if (gameRegister.getGameStatus().equals("human_lost")) {
            animation = playHumanLostAnimation();

        } else if (gameRegister.getGameStatus().equals("computer_lost")) {
            animation = playComputerLostAnimation();
        }
        animation.setOnFinished(e -> {
            Stage window = ScenesRegister.getInstance().getWindow();
            window.setOnShowing(Event::consume);
            Scene gameOverScene = ScenesRegister.getInstance().getGameOverScene();
            window.setScene(gameOverScene);
        });
    }

    private Transition playHumanLostAnimation() {
        return playLostAnimation(this.backEndLogic.getHumanPlayersFields());
    }

    private Transition playComputerLostAnimation() {
        return playLostAnimation(this.backEndLogic.getComputerPlayersFields());
    }

    private Transition playLostAnimation(List<ShipSegment> shipSegments) {
        // Ship segments explosions
        List<Transition> effectsFieldsOccupiedByShipFragments = new ArrayList<>();
        for (ShipSegment shipSegment: shipSegments) {
            Transition animation = getEffectsFromCoordinates(shipSegment).gameOverAnimation(100);
            effectsFieldsOccupiedByShipFragments.add(animation);
        }

        SequentialTransition explosionsSequence = new SequentialTransition();
        explosionsSequence.getChildren().addAll(effectsFieldsOccupiedByShipFragments);
        explosionsSequence.setCycleCount(4);

        // Screen fading
        FillTransition screenWhitening = new FillTransition(Duration.millis(6400), (Rectangle) this.board.getChildren().get(1));
        screenWhitening.setFromValue(Color.TRANSPARENT);
        screenWhitening.setToValue(Color.WHITE);
        screenWhitening.setCycleCount(1);
        screenWhitening.setAutoReverse(false);

        // Screen shaking
        TranslateTransition screenShaking = new TranslateTransition(Duration.millis(50), (GridPane) this.board.getChildren().get(0));
        screenShaking.setFromX(this.board.getTranslateX() - 7);
        screenShaking.setToX(this.board.getTranslateX() + 7);
        screenShaking.setCycleCount(128);
        screenShaking.setAutoReverse(true);

        // Final animation
        ParallelTransition complexAnimation = new ParallelTransition();
        complexAnimation.getChildren().addAll(explosionsSequence, screenShaking, screenWhitening);
        complexAnimation.play();

        return complexAnimation;
    }

    public void redrawShipsOfHitParty(Player playerHit) {
        if (playerHit != null) {
            SoundsRegister.getInstance().playExplosionSound();
            if (playerHit.getName().equals("Computer")) {
                drawComputerPlayerShips(backEndLogic.getComputerPlayersFields());
            } else {
                drawHumanPlayerShips(backEndLogic.getHumanPlayersFields());
            }
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

    private Effects getEffectsFromCoordinates(Coordinates coordinates) {
        return ((GridPane) this.board.getChildren().get(0)).getChildren().stream()
                .filter(e -> ((Tile)((StackPane) e).getChildren().get(0)).getCoordinates().equals(coordinates))
                .map(e -> getEffectsFrom((StackPane)e))
                .collect(Collectors.toList()).get(0);
    }
}
