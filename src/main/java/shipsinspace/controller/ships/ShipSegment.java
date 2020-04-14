package shipsinspace.controller.ships;

import shipsinspace.common.Coordinates;

import java.util.Objects;

public class ShipSegment extends Coordinates {
    private boolean isDestroyed;

    public ShipSegment(int xCoordinate, int yCoordinate) {
        super(xCoordinate, yCoordinate);
        this.isDestroyed = false;
    }

    public ShipSegment(int xCoordinate, int yCoordinate, int max) {
        super(xCoordinate, yCoordinate, max);
        this.isDestroyed = false;
    }

    public ShipSegment(int xCoordinate, int yCoordinate, int max, boolean allowedZero) {
        super(xCoordinate, yCoordinate, max, allowedZero);
        this.isDestroyed = false;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }


}
