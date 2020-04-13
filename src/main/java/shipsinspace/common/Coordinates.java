package shipsinspace.common;

import javafx.beans.NamedArg;

import java.util.Objects;

public class Coordinates {
    private int xCoordinate;
    private int yCoordinate;

    public Coordinates(@NamedArg("xCoordinate") int xCoordinate, @NamedArg("yCoordinate") int yCoordinate) {
        this(xCoordinate, yCoordinate, 10, false);
    }

    public Coordinates(@NamedArg("xCoordinate") int xCoordinate, @NamedArg("yCoordinate") int yCoordinate, @NamedArg("max") int max) {
        this(xCoordinate, yCoordinate, max, false);
    }

    public Coordinates(@NamedArg("xCoordinate") int xCoordinate, @NamedArg("yCoordinate") int yCoordinate, @NamedArg("max") int max, @NamedArg("allowedZero") boolean allowedZero) {
        if (allowedZero) {
            if ((xCoordinate >= 0 && yCoordinate >= 0) && (xCoordinate <= max && yCoordinate <= max)) {
                this.xCoordinate = xCoordinate;
                this.yCoordinate = yCoordinate;
            } else {
                throw new IllegalArgumentException(String.format("Either X or Y has set to value <= 0, or > %d", max));
            }
        } else {
            if ((xCoordinate > 0 && yCoordinate > 0) && (xCoordinate <= max && yCoordinate <= max)) {
                this.xCoordinate = xCoordinate;
                this.yCoordinate = yCoordinate;
            } else {
                throw new IllegalArgumentException(String.format("Either X or Y has set to value <= 0, or > %d", max));
            }
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
            try {
                return new Coordinates(this.getX() + direction, this.getY());
            } catch (IllegalArgumentException e) {
                return new Coordinates(this.getX(), this.getY());
            }

        } else {
            try {
                return new Coordinates(this.getX(), this.getY() + direction);
            } catch (IllegalArgumentException e) {
                return new Coordinates(this.getX(), this.getY());
            }

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

    @Override
    public String toString() {
        return String.format("Coordinates (%d, %d)", xCoordinate, yCoordinate);
    }
}
