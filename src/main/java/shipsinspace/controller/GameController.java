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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        gameRegister.setHumanShipList(
                this.humanPlayer.getShips().stream()
                        .filter(ship -> !ship.isDestroyed())
                        .map(ShipTemplate::getShipName)
                        .collect(Collectors.toList())
        );

        Player computerHitSomething = computerMove();
        // gameRegister.setCoordinatesComputerPlayerShotAtThisTurn();   is set in computerMove() method
        gameRegister.setObjectComputerPlayerHitThisTurn(computerHitSomething);
        gameRegister.setComputerShipList(
                this.computerPlayer.getShips().stream()
                        .filter(ship -> !ship.isDestroyed())
                        .map(ShipTemplate::getShipName)
                        .collect(Collectors.toList())
        );

        if (gameRegister.getHumanShipList().size() == 0) {
            gameRegister.setGameStatus("human_lost");
        } else if (gameRegister.getComputerShipList().size() == 0) {
            gameRegister.setGameStatus("computer_lost");
        } else {
            gameRegister.setGameStatus("game_on");
        }
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
    }

    public void generateComputerPlayer(int gameDifficulty) {
        this.computerPlayer = new ComputerPlayer(gameDifficulty);
        this.placeShips(this.computerPlayer, this.humanPlayer.getFieldsOccupiedByShips(), false);
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
