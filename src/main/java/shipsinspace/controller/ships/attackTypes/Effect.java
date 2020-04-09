package shipsinspace.controller.ships.attackTypes;

import shipsinspace.common.Coordinates;

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
}
