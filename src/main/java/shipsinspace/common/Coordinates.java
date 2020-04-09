package shipsinspace.common;

import java.util.Objects;

public class Coordinates {
    private int xCoordinate;
    private int yCoordinate;

    public Coordinates(int xCoordinate, int yCoordinate) {
        if (xCoordinate > 0 && yCoordinate > 0) {
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
        } else {
            throw new IllegalArgumentException("Either X or Y has set to value <= 0");
        }

    }

    public int getX() {
        return xCoordinate;
    }

    public int getY() {
        return yCoordinate;
    }

    public Coordinates returnNeighbour(int axis, int direction) {
        if (axis == 0) {
            return new Coordinates(this.getX() + direction, this.getY());
        } else {
            return new Coordinates(this.getX(), this.getY() + direction);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return xCoordinate == that.xCoordinate &&
                yCoordinate == that.yCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate);
    }
}
