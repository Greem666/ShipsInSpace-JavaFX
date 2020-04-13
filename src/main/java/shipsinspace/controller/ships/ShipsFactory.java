package shipsinspace.controller.ships;

import shipsinspace.common.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShipsFactory {
    private static Random random = new Random();

    public static List<ShipTemplate> createFleet() {
        return createFleet(new ArrayList<>());
    }

    public static List<ShipTemplate> createFleet(List<Coordinates> occupiedFields) {
        List<ShipTemplate> shipTemplates = new ArrayList<>(
                Arrays.asList(
                    new Battleship(),
                    new Carrier(),
                    new Destroyer(),
                    new Corvette(),
                    new Scout()
                )
        );

        List<ShipTemplate> fleet = new ArrayList<>();

        for (ShipTemplate shipType: shipTemplates) {
            int desiredSegmentsCount = shipType.getStartingSegmentsCount();
            int currentSegmentsCount = shipType.getShipSegments().size();

            while (currentSegmentsCount < desiredSegmentsCount) {

                Coordinates randomStartingPoint = generateRandomStartingPoint(1, 10);
                if (!occupiedFields.contains(randomStartingPoint)) {
                    shipType.addShipSegment(randomStartingPoint);

                    int chosenAxis = chooseAxis();
                    int chosenDirection = chooseDirection();

                    while (currentSegmentsCount < desiredSegmentsCount) {
                        Coordinates newShipSegment = randomStartingPoint.returnNeighbour(chosenAxis, chosenDirection);
                        if (!occupiedFields.contains(newShipSegment)) {
                            try {
                                shipType.addShipSegment(newShipSegment);
                            } catch (IllegalArgumentException e) {
                                shipType.setShipSegments(new ArrayList<Coordinates>());
                                break;
                            }
                        } else {
                            shipType.setShipSegments(new ArrayList<Coordinates>());
                            break;
                        }
                        currentSegmentsCount = shipType.getShipSegments().size();
                        randomStartingPoint = newShipSegment;
                    }
                    currentSegmentsCount = shipType.getShipSegments().size();
                }
            }

            fleet.add(shipType);
            occupiedFields.addAll(shipType.getShipSegments());
        }

        return fleet;
    }

    private static Coordinates generateRandomStartingPoint(int min, int max) {
        int range = (max - min) + 1;
        int randomX = random.nextInt(range) + 1;
        int randomY = random.nextInt(range) + 1;

        return new Coordinates(randomX, randomY);
    }

    private static int chooseAxis() {
        return random.nextInt(2);
    }

    private static int chooseDirection() {
        return random.nextInt(2) == 0 ? -1 : 1;
    }
}
