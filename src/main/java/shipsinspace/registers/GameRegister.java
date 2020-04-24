package shipsinspace.registers;

import shipsinspace.common.Coordinates;
import shipsinspace.controller.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton pattern to keep easy one-point access to game controller instance.
 */

public final class GameRegister {
    private static GameRegister instance;

//    private GameController gameController;

    private Integer gameDifficulty = 1;

    private String humanPlayerName = "Player";
    private String computerPlayerName = "Computer";

    private List<String> humanShipList;
    private List<String> computerShipList;

    private Coordinates coordinatesHumanPlayerShotAtThisTurn = null;
    private Coordinates coordinatesComputerPlayerShotAtThisTurn = null;

    private Player ownerOfHitObjectHitThisTurnByHumanPlayer = null;
    private Player ownerOfHitObjectHitThisTurnByComputerPlayer = null;

    private String gameStatus = "game_on";

    private GameRegister() {
    }

    public static GameRegister getInstance() {
        if (instance == null) {
            instance = new GameRegister();
        }
        return instance;
    }

    public void resetGameInfo() {

        humanPlayerName = "Player";

        humanShipList = new ArrayList<>();
        computerShipList = new ArrayList<>();

        coordinatesHumanPlayerShotAtThisTurn = null;
        coordinatesComputerPlayerShotAtThisTurn = null;

        ownerOfHitObjectHitThisTurnByHumanPlayer = null;
        ownerOfHitObjectHitThisTurnByComputerPlayer = null;

        gameStatus = "game_on";
    }

    public String getHumanPlayerName() {
        return this.humanPlayerName;
    }

    public String getComputerPlayerName() {
        return this.computerPlayerName;
    }

    public void setHumanPlayerName(String humanPlayerName) {
        this.humanPlayerName = humanPlayerName;
    }

    public int getGameDifficulty() {
        return gameDifficulty;
    }

    public void setGameDifficulty(int gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    public void setHumanShipList(List<String> humanShipList) {
        this.humanShipList = humanShipList;
    }

    public void setComputerShipList(List<String> computerShipList) {
        this.computerShipList = computerShipList;
    }

    public List<String> getHumanShipList() {
        return humanShipList;
    }

    public List<String> getComputerShipList() {
        return computerShipList;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Coordinates getCoordinatesHumanPlayerShotAtThisTurn() {
        return coordinatesHumanPlayerShotAtThisTurn;
    }

    public void setCoordinatesHumanPlayerShotAtThisTurn(Coordinates coordinatesHumanPlayerShotAtThisTurn) {
        this.coordinatesHumanPlayerShotAtThisTurn = coordinatesHumanPlayerShotAtThisTurn;
    }

    public Coordinates getCoordinatesComputerPlayerShotAtThisTurn() {
        return coordinatesComputerPlayerShotAtThisTurn;
    }

    public void setCoordinatesComputerPlayerShotAtThisTurn(Coordinates coordinatesComputerPlayerShotAtThisTurn) {
        this.coordinatesComputerPlayerShotAtThisTurn = coordinatesComputerPlayerShotAtThisTurn;
    }

    public Player getOwnerOfHitObjectHitThisTurnByHumanPlayer() {
        return ownerOfHitObjectHitThisTurnByHumanPlayer;
    }

    public void setObjectHumanPlayerHitThisTurn(Player ownerOfHitObject) {
        this.ownerOfHitObjectHitThisTurnByHumanPlayer = ownerOfHitObject;
    }

    public Player getOwnerOfHitObjectHitThisTurnByComputerPlayer() {
        return ownerOfHitObjectHitThisTurnByComputerPlayer;
    }

    public void setObjectComputerPlayerHitThisTurn(Player ownerOfHitObject) {
        this.ownerOfHitObjectHitThisTurnByComputerPlayer = ownerOfHitObject;
    }
}
