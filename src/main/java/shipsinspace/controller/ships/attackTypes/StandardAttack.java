package shipsinspace.controller.ships.attackTypes;

import shipsinspace.common.Coordinates;

public class StandardAttack implements Attack {
    @Override
    public Effect attack(Coordinates coordinates) {
        return new Effect(coordinates, "destroy");
    }
}
