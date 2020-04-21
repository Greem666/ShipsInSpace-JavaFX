package shipsinspace.view.gameBoardScene.board.elements;

import javafx.animation.FillTransition;
import javafx.beans.NamedArg;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import shipsinspace.common.Coordinates;

public class Tile extends Rectangle {
    private Coordinates coordinates;

    public Tile(double width, double height, Coordinates coordinates) {
        super(width, height);
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
