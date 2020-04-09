package shipsinspace.view.board;

import javafx.scene.shape.Rectangle;
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
