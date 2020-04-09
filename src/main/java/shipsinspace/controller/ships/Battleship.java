package shipsinspace.controller.ships;

import shipsinspace.controller.ships.attackTypes.StandardAttack;

public class Battleship extends ShipTemplate {
    public Battleship() {
        super("Battleship", new StandardAttack(), 4);
    }
}
