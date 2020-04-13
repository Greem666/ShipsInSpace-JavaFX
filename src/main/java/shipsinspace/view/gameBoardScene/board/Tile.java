package shipsinspace.view.gameBoardScene.board;

import javafx.beans.NamedArg;
import javafx.scene.shape.Rectangle;
import shipsinspace.common.Coordinates;

public class Tile extends Rectangle {
    private Coordinates coordinates;

    public Tile(@NamedArg("width") double width, @NamedArg("height") double height, @NamedArg("coordinates") Coordinates coordinates) {
        super(width, height);
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
