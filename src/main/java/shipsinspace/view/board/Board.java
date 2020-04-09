package shipsinspace.view.board;

import javafx.scene.layout.Region;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import shipsinspace.common.AlertWindow;
import shipsinspace.common.Coordinates;
import shipsinspace.view.interfaces.WindowElements;

import java.util.stream.IntStream;

public class Board implements WindowElements {

    private String ACTIVE_ATTACK;

    @Override
    public Region generateElement(int windowWidth, int windowHeight, int tilesCount) {

        Image bkg = new Image(getClass().getResourceAsStream("/backgrounds/background_galaxy.jpg"));

        char[] LETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int[] NUMBERS = IntStream.range(1, LETTERS.length).toArray();

        double gameBoardHeight = (double)windowHeight * 0.75;
        double gameBoardWidth = windowWidth;
        double tileBorderThickness = 1;
        double TILE_SIZE = ((double)windowWidth) / tilesCount - 2 * tileBorderThickness;

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

                Tile tile = new Tile(TILE_SIZE, TILE_SIZE, new Coordinates(x, y));
                tile.setFill(Color.TRANSPARENT);
                tile.setStroke(Color.WHITE);
                tile.setStrokeWidth(tileBorderThickness);

                tile.setOnMouseClicked(e -> {
                    AlertWindow.display("Action", ACTIVE_ATTACK);
                });

                // Highlighting tiles
                if (y != 0 && x != 0) {
                    tile.setOnMouseEntered(t -> tile.setFill(Color.rgb(210, 210, 210, 0.58)));
                    tile.setOnMouseExited(t -> tile.setFill(Color.TRANSPARENT));
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
                    Text text = new Text(String.valueOf(LETTERS[y - 1]).toUpperCase());
                    text.setFont(Font.font(40));
                    text.setFill(Color.WHITE);
                    text.setStrokeWidth(1);
                    text.setStroke(Color.BLACK);
                    gameBoardLayout.add(new StackPane(tile, text), x, y);
                }
                gameBoardLayout.add(tile, x, y);
            }
        }
        return gameBoardLayout;
    }
}
