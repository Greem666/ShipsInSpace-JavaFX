package shipsinspace.gameRegister;

import shipsinspace.common.Coordinates;
import shipsinspace.controller.GameController;
import shipsinspace.controller.player.Player;

import java.util.List;

/**
 * Singleton pattern to keep easy one-point access to game controller instance.
 */

public final class GameRegister {
    private static GameRegister instance;

//    private GameController gameController;

    private int gameDifficulty;

    private String humanPlayerName;

    private List<String> humanShipList;
    private List<String> computerShipList;

    private Coordinates coordinatesHumanPlayerShotAtThisTurn = null;
    private Coordinates coordinatesComputerPlayerShotAtThisTurn = null;

    private Player ownerOfHitObjectHitThisTurnByHumanPlayer = null;
    private Player ownerOfHitObjectHitThisTurnByComputerPlayer = null;

    private String gameStatus;

    private GameRegister() {
//        this.gameController = gameController;
    }

    public static GameRegister getInstance() {
        if (instance == null) {
            instance = new GameRegister();
        }
        return instance;
    }

//    public GameController getGameController() {
//        return gameController;
//    }

    public String getHumanPlayerName() {
        return humanPlayerName;
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
