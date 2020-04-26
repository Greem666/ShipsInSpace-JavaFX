package shipsinspace.view.gameBoardScene.sidePanels;

import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import shipsinspace.view.gameBoardScene.bottomMenu.ShipsStatusPanel;

public class SidePanel {
    protected ShipsStatusPanel playerShipsStatusPanel;
    protected int windowWidth;
    protected int windowHeight;
    protected VBox sidePanel = new VBox();

    public SidePanel(int sidePanelWidth, int sidePanelHeight, String panelOwner, String shipSegmentImageName) {
        this.windowWidth = sidePanelWidth;
        this.windowHeight = sidePanelHeight;
        this.playerShipsStatusPanel = new ShipsStatusPanel(panelOwner, shipSegmentImageName, sidePanelHeight * 0.04);
    }

    public Region generateElement() {
        updatePlayerShipsStatusPanel();

        return this.sidePanel;
    }

    public VBox getSidePanel() {
        return this.sidePanel;
    }

    public void updatePlayerShipsStatusPanel() {
        if (this.sidePanel.getChildren().size() > 0) {
            this.sidePanel.getChildren().remove(0);
        }
        this.sidePanel.getChildren().addAll(
                this.playerShipsStatusPanel.generateElement()
        );
        this.sidePanel.setAlignment(Pos.CENTER);
    };
}
