package shipsinspace.controller.ships.attackTypes;

import shipsinspace.common.Coordinates;

import java.util.Objects;

public class Effect {
    private Coordinates coordinates;
    private String action;

    public Effect(Coordinates coordinates, String action) {
        this.coordinates = coordinates;
        this.action = action;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getAction() {
        return action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Effect effect = (Effect) o;
        return Objects.equals(coordinates, effect.coordinates) &&
                Objects.equals(action, effect.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, action);
    }
}
