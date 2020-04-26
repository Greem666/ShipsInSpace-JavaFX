package shipsinspace.controller;

import shipsinspace.common.Coordinates;
import shipsinspace.controller.player.ComputerPlayer;
import shipsinspace.controller.player.Player;
import shipsinspace.controller.ships.ShipSegment;
import shipsinspace.controller.ships.ShipTemplate;
import shipsinspace.controller.ships.ShipsFactory;
import shipsinspace.controller.ships.attackTypes.Attack;
import shipsinspace.controller.ships.attackTypes.StandardAttack;
import shipsinspace.registers.GameRegister;

import java.util.*;
import java.util.stream.Collectors;

public class GameController {

    private Player humanPlayer;
    private ComputerPlayer computerPlayer;
    private Attack activeAttack;
    private GameRegister gameRegister;

    public GameController() {
        this.gameRegister = GameRegister.getInstance();

        generateHumanPlayer();
        generateComputerPlayer(this.gameRegister.getGameDifficulty());

        this.activeAttack = new StandardAttack();
    }

    public void gameStatusReset() {
        gameRegister.resetGameInfo();

        generateHumanPlayer();
        generateComputerPlayer(this.gameRegister.getGameDifficulty());

        this.activeAttack = new StandardAttack();
    }

    public void gameTurn(Coordinates coordinatesHumanPlayerShotAt) {
        Player ownerOfHitObject = humanFireAtCoordinates(coordinatesHumanPlayerShotAt);
        gameRegister.setCoordinatesHumanPlayerShotAtThisTurn(coordinatesHumanPlayerShotAt);
        gameRegister.setObjectHumanPlayerHitThisTurn(ownerOfHitObject);
        updateHumanAndComputerShipsStatusInRegister();

        Player computerHitSomething = computerMove();
        // gameRegister.setCoordinatesComputerPlayerShotAtThisTurn();   is set in computerMove() method
        gameRegister.setObjectComputerPlayerHitThisTurn(computerHitSomething);
        updateHumanAndComputerShipsStatusInRegister();

        if (gameRegister.getHumanRemainingShipsList().size() == 0) {
            gameRegister.setGameStatus("human_lost");
        } else if (gameRegister.getComputerRemainingShipsList().size() == 0) {
            gameRegister.setGameStatus("computer_lost");
        } else {
            gameRegister.setGameStatus("game_on");
        }
    }

    private void updateHumanAndComputerShipsStatusInRegister() {
        updateHumanShipsStatusInRegister();
        updateComputerShipStatusInRegister();
    }

    private void updateHumanShipsStatusInRegister() {
        updateShipStatusInRegister(this.humanPlayer);
    }

    private void updateComputerShipStatusInRegister() {
        updateShipStatusInRegister(this.computerPlayer);
    }

    private void updateShipStatusInRegister(Player player) {
        if (player instanceof ComputerPlayer) {
            gameRegister.setComputerRemainingShipsList(getListOfActiveShipNames(player));
            gameRegister.setComputerAllShipsStatus(getListOfAllShipsStatus(player));
        } else {
            gameRegister.setHumanRemainingShipsList(getListOfActiveShipNames(player));
            gameRegister.setHumanAllShipsStatus(getListOfAllShipsStatus(player));
        }
    }

    private List<String> getListOfActiveShipNames(Player player) {
        return player.getShips().stream()
                .filter(ship -> !ship.isDestroyed())
                .map(ShipTemplate::getShipName)
                .collect(Collectors.toList());
    }

    private Map<String, List<Boolean>> getListOfAllShipsStatus(Player player) {
        return player.getShips().stream()
                .collect(Collectors.toMap(
                        ShipTemplate::getShipName,
                        ship -> ship.getShipSegments().stream()
                                .map(ShipSegment::isDestroyed)
                                .collect(Collectors.toList())
                ));
    }

    public Player humanFireAtCoordinates(Coordinates coordinatesHumanPlayerShotAt) {
        return checkCoordinatesShotAt(coordinatesHumanPlayerShotAt);
    }

    public Player computerMove() {
        Coordinates coordinatesComputerPlayerShotAt = this.computerPlayer.fireAtCoordinates(this.humanPlayer.getFieldsOccupiedByShips());
        gameRegister.setCoordinatesComputerPlayerShotAtThisTurn(coordinatesComputerPlayerShotAt);
        return checkCoordinatesShotAt(coordinatesComputerPlayerShotAt);
    }

    private Player checkCoordinatesShotAt(Coordinates coordinatesShotAt) {
        Player playerHit = null;
        List<Player> players = new ArrayList<>(Arrays.asList(this.humanPlayer, this.computerPlayer));
        for (Player player: players) {
            if (player.checkIfShipHit(coordinatesShotAt)) {
                playerHit = player;
            }
        }
        return playerHit;
    }

    public void generateHumanPlayer() {
        this.humanPlayer = new Player("Human");
        this.placeShips(this.humanPlayer, true);
        updateHumanShipsStatusInRegister();
    }

    public void generateComputerPlayer(int gameDifficulty) {
        this.computerPlayer = new ComputerPlayer(gameDifficulty);
        this.placeShips(this.computerPlayer, this.humanPlayer.getFieldsOccupiedByShips(), false);
        updateComputerShipStatusInRegister();
    }

    public void placeShips(Player player, boolean createVisible) {
        player.setShips(ShipsFactory.createFleet(createVisible));
    }

    public void placeShips(Player player, List<ShipSegment> occupiedFields, boolean createVisible) {
        player.setShips(ShipsFactory.createFleet(occupiedFields, createVisible));
    }

    public List<ShipSegment> getHumanPlayersFields() {
        return this.humanPlayer.getFieldsOccupiedByShips();
    }

    public List<ShipSegment> getComputerPlayersFields() {
        return this.computerPlayer.getFieldsOccupiedByShips();
    }

    public List<ShipSegment> getPlayersFields(Player player) {
        if (player == this.humanPlayer) {
            return this.humanPlayer.getFieldsOccupiedByShips();
        } else {
            return this.computerPlayer.getFieldsOccupiedByShips();
        }

    }

    public Attack getActiveAttack() {
        return activeAttack;
    }


}
