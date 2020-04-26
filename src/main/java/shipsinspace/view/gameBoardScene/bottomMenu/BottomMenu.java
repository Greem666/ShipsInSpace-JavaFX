package shipsinspace.view.gameBoardScene.bottomMenu;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import shipsinspace.view.gameBoardScene.interfaces.WindowElements;
import javafx.scene.layout.Region;

public class BottomMenu {
    private String activeAttack;
//    private AttackButtons attackButtons = new AttackButtons();
    private ShipsStatusPanel playerShipsStatusPanel;
    private ShipsStatusPanel computerShipsStatusPanel;
    private int windowWidth;
    private int windowHeight;
    private int tilesCount;
    private HBox bottomControlPanel = new HBox();

    public BottomMenu(int bottomMenuWidth, int bottomMenuHeight, int tilesCount) {
        this.windowWidth = bottomMenuWidth;
        this.windowHeight = bottomMenuHeight;
        this.tilesCount = tilesCount;
        this.playerShipsStatusPanel = new ShipsStatusPanel("Player", "shipSegmentPlayer.png", bottomMenuHeight * 0.1);
        this.computerShipsStatusPanel = new ShipsStatusPanel("Computer", "shipSegmentEnemy.png", bottomMenuHeight * 0.1);
    }

    public Region generateElement() {

        double bottomMenuHeight = ((double)windowHeight) * 0.25;
        // BOTTOM CONTROL PANEL

        this.bottomControlPanel.getChildren().addAll(
                playerShipsStatusPanel.generateElement(),
                computerShipsStatusPanel.generateElement()
        );
        this.bottomControlPanel.setSpacing(windowWidth * 0.1);
        this.bottomControlPanel.setAlignment(Pos.CENTER);

        return this.bottomControlPanel;
    }

    public HBox getBottomControlPanel() {
        return this.bottomControlPanel;
    }

    public void updateHumanPlayerShipsStatusPanel() {
        this.bottomControlPanel.getChildren().remove(0, 2);
        this.bottomControlPanel.getChildren().addAll(
                this.playerShipsStatusPanel.generateElement(),
                this.computerShipsStatusPanel.getShipStatusPanel()
        );
    }

    public void updateComputerPlayerShipsStatusPanel() {
        this.bottomControlPanel.getChildren().remove(0, 2);
        this.bottomControlPanel.getChildren().addAll(
                this.playerShipsStatusPanel.getShipStatusPanel(),
                this.computerShipsStatusPanel.generateElement()
        );
    }

}
