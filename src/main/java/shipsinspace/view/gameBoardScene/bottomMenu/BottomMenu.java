package shipsinspace.view.gameBoardScene.bottomMenu;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import shipsinspace.view.gameBoardScene.interfaces.WindowElements;
import javafx.scene.layout.Region;

public class BottomMenu implements WindowElements {
    private String activeAttack;
    private AttackButtons attackButtons = new AttackButtons();
    private ShipsStatusPanel playerShipsStatusPanel = new ShipsStatusPanel("Player");
    private ShipsStatusPanel computerShipsStatusPanel = new ShipsStatusPanel("Computer");

    @Override
    public Region generateElement(int windowWidth, int windowHeight, int tilesCount) {

        double tileSize = ((double)windowWidth) / tilesCount;
        double bottomMenuHeight = ((double)windowHeight) * 0.25;
        double iconSize = bottomMenuHeight * 0.1;
        // BOTTOM CONTROL PANEL
        HBox bottomControlPanel = new HBox();
        bottomControlPanel.getChildren().addAll(
                attackButtons.generateElement(iconSize),
                playerShipsStatusPanel.generateElement(iconSize),
                computerShipsStatusPanel.generateElement(iconSize)
        );
        bottomControlPanel.setSpacing(windowWidth * 0.1);
//        bottomControlPanel.setMinHeight(BOTTOM_MENU_HEIGHT);
        bottomControlPanel.setAlignment(Pos.CENTER);

        return bottomControlPanel;
    }

    public String getActiveAttack() {
        return activeAttack;
    }
}
