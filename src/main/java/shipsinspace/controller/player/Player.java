package shipsinspace.controller.player;

import shipsinspace.common.Coordinates;
import shipsinspace.controller.ships.ShipSegment;
import shipsinspace.controller.ships.ShipTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Player {
    private String name;
    private List<ShipTemplate> ships;

    public Player(String name) {
        if (!name.equals("")) {
            this.name = name;
            this.ships = new ArrayList<>();
        } else {
            throw new IllegalArgumentException("Player name empty.");
        }
    }

    public Player(String name, List<ShipTemplate> ships) {
        if (!name.equals("") && ships.size() > 0) {
            this.name = name;
            this.ships = ships;
        } else {
            throw new IllegalArgumentException("Player name empty, or no ships added.");
        }
    }

    public String getName() {
        return name;
    }

    public List<ShipTemplate> getShips() {
        return ships;
    }

    public void setShips(List<ShipTemplate> ships) {
        this.ships = ships;
    }

    public void resetShips() {
        this.ships = new ArrayList<>();
    }

    public List<ShipSegment> getFieldsOccupiedByShips() {
        return this.getShips().stream()
                .flatMap(s -> s.getShipSegments().stream())
                .collect(Collectors.toList());
    }

    public boolean checkIfShipHit(Coordinates coordinates) {
        for (ShipTemplate ship: this.getShips()) {
            for (ShipSegment shipSegment: ship.getShipSegments()) {
                if (shipSegment.equals(coordinates)) {
                    ship.destroyShipSegment(coordinates);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
