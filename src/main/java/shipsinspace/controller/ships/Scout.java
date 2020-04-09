package shipsinspace.controller.ships;

import shipsinspace.controller.ships.attackTypes.StandardAttack;

public class Scout extends ShipTemplate {
    public Scout() {
        super("Scout", new StandardAttack(), 2);
    }
}