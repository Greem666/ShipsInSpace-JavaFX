package shipsinspace.controller.ships;

import shipsinspace.controller.ships.attackTypes.Attack;
import shipsinspace.common.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ShipTemplate {
    private List<ShipSegment> shipSegments;
    private String shipName;
    private Attack attackType;
    private int startingSegmentsCount;

    public ShipTemplate(String shipName, Attack attackType, int startingSegments) {
        this.shipSegments = new ArrayList<>();
        this.shipName = shipName;
        this.attackType = attackType;
        this.startingSegmentsCount = startingSegments;
    }

    public List<ShipSegment> getShipSegments() {
        return shipSegments;
    }

    public void setShipSegments(List<ShipSegment> shipSegments) {
        this.shipSegments = shipSegments;
    }

    public void addShipSegment(ShipSegment newShipSegment) {
        if (!shipSegments.contains(newShipSegment)) {
            this.shipSegments.add(newShipSegment);
        } else {
            throw new IllegalArgumentException(String.format("Coordinate %s already in ship segments list.", newShipSegment.toString()));
        }
    }

    public void destroyShipSegment(Coordinates coordinates) {
        this.shipSegments.stream()
                .forEach(s -> {
                    if (s.equals(coordinates)) {
                        s.setDestroyed(true);
                    }
                }
                );
    }

    public Attack getAttackType() {
        return attackType;
    }

    public String getShipName() {
        return shipName;
    }

    public int getStartingSegmentsCount() {
        return startingSegmentsCount;
    }
}
