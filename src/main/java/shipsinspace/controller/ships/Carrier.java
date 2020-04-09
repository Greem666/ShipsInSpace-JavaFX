package shipsinspace.controller.ships;

import shipsinspace.controller.ships.attackTypes.StandardAttack;

public class Carrier extends ShipTemplate {
    public Carrier() {
        super("Carrier", new StandardAttack(), 5);
    }
}
