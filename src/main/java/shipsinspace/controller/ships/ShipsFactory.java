package shipsinspace.controller.ships;

import shipsinspace.common.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShipsFactory {
    private Random random;

    public List<ShipTemplate> createFleet(List<Coordinates> occupiedFields) {
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
            int segmentsCount = shipType.getStartingSegmentsCount();

            while (true) {
                List<Coordinates> positionCandidate = new ArrayList<>();
                Coordinates startingPoint = this.generateRandomStartingPoint(1, 11);
                int axis = chooseAxis();
                int direction = chooseDirection();

                for (int i = 0; i < segmentsCount - 1; i++) {
                    positionCandidate.add(startingPoint.returnNeighbour(axis, direction));
                    if
                }

            }
        }


        return new ArrayList<ShipTemplate>();
    }

    private Coordinates generateRandomStartingPoint(int min, int max) {
        int range = (max - min) + 1;
        int randomX = random.nextInt(range) + 1;
        int randomY = random.nextInt(range) + 1;

        return new Coordinates(randomX, randomY);
    }

    private int chooseAxis() {
        return random.nextInt(2);
    }

    private int chooseDirection() {
        return random.nextInt(2) == 0 ? -1 : 1;
    }
}
