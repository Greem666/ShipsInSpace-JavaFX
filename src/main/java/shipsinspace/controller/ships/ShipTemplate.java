package shipsinspace.controller.ships;

import shipsinspace.controller.ships.attackTypes.Attack;
import shipsinspace.common.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ShipTemplate {
    private List<Coordinates> shipSegments;
    private String shipName;
    private Attack attackType;
    private int startingSegmentsCount;

    public ShipTemplate(String shipName, Attack attackType, int startingSegments) {
        this.shipSegments = new ArrayList<>();
        this.shipName = shipName;
        this.attackType = attackType;
        this.startingSegmentsCount = startingSegments;
    }

    public List<Coordinates> getShipSegments() {
        return shipSegments;
    }

    public void setShipSegments(List<Coordinates> shipSegments) {
        this.shipSegments = shipSegments;
    }

    public void addShipSegment(Coordinates newShipSegment) {
        if (!shipSegments.contains(newShipSegment)) {
            this.shipSegments.add(newShipSegment);
        } else {
            throw new IllegalArgumentException(String.format("Coordinate %s already in ship segments list.", newShipSegment.toString()));
        }

    }

    public void removeShipSegment(Coordinates coordinates) {
        this.shipSegments = this.shipSegments.stream()
                .filter(e -> !e.equals(coordinates))
                .collect(Collectors.toList());
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
