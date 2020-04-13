package shipsinspace.controller.player;

import shipsinspace.common.Coordinates;
import shipsinspace.controller.ships.ShipTemplate;

import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {
    public static Random random = new Random();
    public int difficultySetting;

    public ComputerPlayer(int difficultySetting) {
        super("Computer");
        this.difficultySetting = difficultySetting;
    }

    public ComputerPlayer(List<ShipTemplate> ships, int difficultySetting) {
        super("Computer", ships);
        this.difficultySetting = difficultySetting;
    }

    public Coordinates generateRandomCoordinates(List<Coordinates> fieldsToAvoid) {
        Coordinates coordinates = generateRandomCoordinates();
        while(fieldsToAvoid.contains(coordinates)) {
            coordinates = generateRandomCoordinates();
        }
        return coordinates;
    }

    public Coordinates generateRandomCoordinates() {
        return new Coordinates(random.nextInt(10) + 1, random.nextInt(10) + 1);
    }

    public Coordinates fireAtCoordinates(List<Coordinates> playerOccupiedCoordinates) {
        int hitOrMissProbability = random.nextInt(100);
        List<Coordinates> ownOccupiedCoordinates = getFieldsOccupiedByShips();

        Coordinates returnCoordinates = null;

        switch(this.difficultySetting) {
            case 1: default:
                if (hitOrMissProbability >= 50) {
                    returnCoordinates = playerOccupiedCoordinates.get(random.nextInt(playerOccupiedCoordinates.size()));
                } else {
                    returnCoordinates = generateRandomCoordinates(playerOccupiedCoordinates);
                }
                break;
            case 2:
                if (hitOrMissProbability >= 35) {
                    returnCoordinates = playerOccupiedCoordinates.get(random.nextInt(playerOccupiedCoordinates.size()));
                } else {
                    returnCoordinates = generateRandomCoordinates(playerOccupiedCoordinates);
                }
                break;
            case 3:
                if (hitOrMissProbability >= 35) {
                    returnCoordinates = playerOccupiedCoordinates.get(random.nextInt(playerOccupiedCoordinates.size()));
                } else {
                    ownOccupiedCoordinates.addAll(playerOccupiedCoordinates);
                    returnCoordinates = generateRandomCoordinates(ownOccupiedCoordinates);
                }
                break;
        }
        return returnCoordinates;
    }
}
