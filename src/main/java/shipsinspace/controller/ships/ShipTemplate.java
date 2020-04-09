package shipsinspace.controller.ships;

import shipsinspace.controller.ships.attackTypes.Attack;
import shipsinspace.common.Coordinates;

import java.util.List;

public abstract class ShipTemplate {
    private List<Coordinates> shipSegments;
    private String shipClass;
    private Attack attackType;
    private int startingSegmentsCount;

    public ShipTemplate(String shipClass, Attack attackType, int startingSegments) {
        this.shipClass = shipClass;
        this.attackType = attackType;
        this.startingSegmentsCount = startingSegments;
    }

    public List<Coordinates> getShipSegments() {
        return shipSegments;
    }

    public void setShipSegments(List<Coordinates> shipSegments) {
        this.shipSegments = shipSegments;
    }

    public Attack getAttackType() {
        return attackType;
    }

    public String getShipClass() {
        return shipClass;
    }

    public int getStartingSegmentsCount() {
        return startingSegmentsCount;
    }
}
