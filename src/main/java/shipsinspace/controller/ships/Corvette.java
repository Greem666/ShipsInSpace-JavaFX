package shipsinspace.controller.ships;

import shipsinspace.controller.ships.attackTypes.StandardAttack;

public class Corvette extends ShipTemplate {
    public Corvette() {
        super("Corvette", new StandardAttack(), 2);
    }
}