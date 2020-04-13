package shipsinspace.view.gameBoardScene.board;

import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import shipsinspace.controller.player.Player;
import shipsinspace.gameRegister.GameRegister;
import shipsinspace.view.common.AlertWindow;
import shipsinspace.common.Coordinates;
import shipsinspace.controller.GameController;
import shipsinspace.controller.ships.attackTypes.Attack;
import shipsinspace.view.gameBoardScene.interfaces.WindowElements;

import java.util.List;
import java.util.stream.IntStream;

public class Board implements WindowElements {

    private Attack ACTIVE_ATTACK;
    private GridPane board;

    private GameRegister gameRegister;
    private GameController backEndLogic;

    private Image shipPlayerImage = new Image(getClass().getResourceAsStream("/ships/shipSegmentPlayer.png"));
    private Image shipComputerImage = new Image(getClass().getResourceAsStream("/ships/shipSegmentEnemy.png"));

    public Board(GameController gameController) {
        gameRegister = GameRegister.getInstance();
        this.backEndLogic = gameController;
        this.ACTIVE_ATTACK = this.backEndLogic.getActiveAttack();
    }

    @Override
    public Region generateElement(int boardWidth, int boardHeight, int tilesCount) {

        Image bkg = new Image(getClass().getResourceAsStream("/backgrounds/background_galaxy.jpg"));


        char[] LETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int[] NUMBERS = IntStream.range(1, LETTERS.length).toArray();

        double gameBoardHeight = boardHeight;
        double gameBoardWidth = boardWidth;
        double tileBorderThickness = 1;
        double TILE_SIZE = ((double)boardWidth) / tilesCount - 2 * tileBorderThickness;

        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(bkg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        // GAME BOARD
        GridPane gameBoardLayout = new GridPane();

        gameBoardLayout.setMaxHeight(gameBoardHeight);
        gameBoardLayout.setMaxWidth(gameBoardWidth);
        gameBoardLayout.setBackground(background);
        gameBoardLayout.setAlignment(Pos.CENTER);

        for (int y = 0; y < tilesCount; y++) {
            for (int x = 0; x < tilesCount; x++) {

                Tile tile = new Tile(TILE_SIZE, TILE_SIZE, new Coordinates(x, y, 11, true));
                tile.setFill(Color.TRANSPARENT);
                tile.setStroke(Color.WHITE);
                tile.setStrokeWidth(tileBorderThickness);
//                tile.setFill(new ImagePattern(shipPlayerImage));

                tile.setOnMouseClicked(e -> {
                    this.backEndLogic.gameTurn(tile.getCoordinates());
                    Player hitByPlayer = gameRegister.getOwnerOfHitObjectHitThisTurnByHumanPlayer();
                    System.out.println(gameRegister.getCoordinatesHumanPlayerShotAtThisTurn());
                    if (hitByPlayer != null) {
                        //TODO: Animate HIT
                        markHit(gameRegister.getCoordinatesHumanPlayerShotAtThisTurn());
                        AlertWindow.display(this.ACTIVE_ATTACK.toString(), "Something was hit at " + tile.getCoordinates());
                    } else {
                        //TODO: Animate miss
                        AlertWindow.display(this.ACTIVE_ATTACK.toString(), "Nothing was hit at " + tile.getCoordinates());
                    }

                    Player hitByComputer = gameRegister.getOwnerOfHitObjectHitThisTurnByHumanPlayer();
                    Coordinates coordinatesAttackedByComputer = gameRegister.getCoordinatesComputerPlayerShotAtThisTurn();
                    System.out.println(coordinatesAttackedByComputer);
                    if (hitByComputer != null) {
                        //TODO: Animate HIT
                        markHit(coordinatesAttackedByComputer);
                        AlertWindow.display(this.ACTIVE_ATTACK.toString(), "Computer hit something at " + coordinatesAttackedByComputer);
                    } else {
                        //TODO: Animate miss
                        AlertWindow.display(this.ACTIVE_ATTACK.toString(), "Computer hit nothing at " + coordinatesAttackedByComputer);
                    }

                });

                // Highlighting tiles
                if (y != 0 && x != 0) {
                    tile.setOnMouseEntered(t -> {
                        tile.setFill(Color.rgb(210, 210, 210, 0.58));
                    });
                    tile.setOnMouseExited(t -> {
                        tile.setFill(Color.TRANSPARENT);
                        this.markPlayerShips(backEndLogic.getHumanPlayersFields(), new ImagePattern(shipPlayerImage));
                        this.markPlayerShips(backEndLogic.getComputerPlayersFields(), new ImagePattern(shipComputerImage));
                    });
                }

                if (y == 0 && x > 0) {
                    tile.setFill(Color.rgb(0, 0, 0, 0.5));
                    Text text = new Text(String.valueOf(NUMBERS[x - 1]));
                    text.setFont(Font.font(40));
                    text.setFill(Color.WHITE);
                    text.setStrokeWidth(1);
                    text.setStroke(Color.BLACK);
                    gameBoardLayout.add(new StackPane(tile, text), x, y);
                }

                if (x == 0 && y > 0) {
                    tile.setFill(Color.rgb(0, 0, 0, 0.5));
//                    Text text = new Text(String.valueOf(LETTERS[y - 1]).toUpperCase());
                    Text text = new Text(String.valueOf(NUMBERS[y - 1]));
                    text.setFont(Font.font(40));
                    text.setFill(Color.WHITE);
                    text.setStrokeWidth(1);
                    text.setStroke(Color.BLACK);
                    gameBoardLayout.add(new StackPane(tile, text), x, y);
                }
                gameBoardLayout.add(tile, x, y);
            }
        }

        this.board = gameBoardLayout;

        this.markPlayerShips(backEndLogic.getHumanPlayersFields(), new ImagePattern(shipPlayerImage));
        this.markPlayerShips(backEndLogic.getComputerPlayersFields(), new ImagePattern(shipComputerImage));

        return this.board;
    }

    public void markPlayerShips(List<Coordinates> playerOccupiedFields, Paint image) {
        for (Coordinates coordinates: playerOccupiedFields) {
            for (Node node: this.board.getChildren()) {
                if (!(node instanceof StackPane)) {
                    Tile tile = (Tile) node;
                    if (tile.getCoordinates().equals(coordinates)) {
                        tile.setFill(image);
                    }
                }
            }
        }
    }

    public void markHit(Coordinates coordinates) {
        for (Node node: this.board.getChildren()) {
            Tile tile = (Tile) node;
            if (tile.getCoordinates().equals(coordinates)) {
                tile.setFill(Color.RED);
            }
        }
    }
}
