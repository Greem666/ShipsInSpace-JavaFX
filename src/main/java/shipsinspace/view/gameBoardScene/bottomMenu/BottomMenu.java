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
        HBox bottomControlPanel = new HBox();
        bottomControlPanel.getChildren().addAll(
                playerShipsStatusPanel.generateElement(),
                computerShipsStatusPanel.generateElement()
        );
        bottomControlPanel.setSpacing(windowWidth * 0.1);
        bottomControlPanel.setAlignment(Pos.CENTER);

        return bottomControlPanel;
    }

}
