package shipsinspace.controller.ships.attackTypes;

import shipsinspace.common.Coordinates;

public interface Attack {
    Effect attack(Coordinates coordinates);
}
