package shipsinspace.controller.ships;

import shipsinspace.common.Coordinates;

import java.util.*;
import java.util.stream.Collectors;

public class ShipsFactory {
    private static Random random = new Random();

    public static List<ShipTemplate> createFleet() {
        return createFleet(new ArrayList<>());
    }

    public static List<ShipTemplate> createFleet(List<ShipSegment> occupiedFields) {
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

                ShipSegment randomStartingPoint = generateRandomStartingPoint(1, 10);
                if (areDirectNeighboursOccupied(randomStartingPoint, occupiedFields)) {
                    shipType.addShipSegment(randomStartingPoint);

                    int chosenAxis = chooseAxis();
                    int chosenDirection = chooseDirection();

                    while (currentSegmentsCount < desiredSegmentsCount) {
                        Coordinates newShipSegmentCoordinates = randomStartingPoint.returnNeighbour(chosenAxis, chosenDirection);
                        if (areDirectNeighboursOccupied(newShipSegmentCoordinates, occupiedFields)) {
                            try {
                                shipType.addShipSegment(new ShipSegment(newShipSegmentCoordinates.getX(), newShipSegmentCoordinates.getY()));
                            } catch (IllegalArgumentException e) {
                                shipType.setShipSegments(new ArrayList<ShipSegment>());
                                break;
                            }
                        } else {
                            shipType.setShipSegments(new ArrayList<ShipSegment>());
                            break;
                        }
                        currentSegmentsCount = shipType.getShipSegments().size();
                        randomStartingPoint = new ShipSegment(newShipSegmentCoordinates.getX(), newShipSegmentCoordinates.getY());
                        chosenAxis = chooseAxis();
                    }
                    currentSegmentsCount = shipType.getShipSegments().size();
                }
            }

            fleet.add(shipType);
            occupiedFields.addAll(shipType.getShipSegments());
        }

        return fleet;
    }

    private static ShipSegment generateRandomStartingPoint(int min, int max) {
        int range = (max - min) + 1;
        int randomX = random.nextInt(range) + 1;
        int randomY = random.nextInt(range) + 1;

        return new ShipSegment(randomX, randomY);
    }

    private static int chooseAxis() {
        return random.nextInt(2);
    }

    private static int chooseDirection() {
        return random.nextInt(2) == 0 ? -1 : 1;
    }

    private static boolean areDirectNeighboursOccupied(Coordinates coordinates, List<ShipSegment> occupiedFields) {
        int x = coordinates.getX();
        int y = coordinates.getY();

        List<Coordinates> neighboursAroundCoordinates = new ArrayList<>();
        int[][] combinations = {
                {-1, -1},
                {-1, 0},
                {-1, 1},
                {0, -1},
                {0, 0},
                {0, 1},
                {1, -1},
                {1, 0},
                {1, 1}
        };
        for (int[] combination : combinations) {
            try {
                neighboursAroundCoordinates.add(new Coordinates(x + combination[0], y + combination[1]));
            } catch (IllegalArgumentException e) {
                // Do nothing, coordinate outside of (1 - max) range
            }
        }

        return neighboursAroundCoordinates.stream().noneMatch(occupiedFields::contains);
    }
}
