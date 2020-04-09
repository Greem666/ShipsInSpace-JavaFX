package shipsinspace.controller.ships;

import shipsinspace.controller.ships.attackTypes.StandardAttack;

public class Destroyer extends ShipTemplate {
    public Destroyer() {
        super("Destroyer", new StandardAttack(), 3);
    }
}