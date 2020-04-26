package shipsinspace.view.gameBoardScene.sidePanels;

public class LeftPanel extends SidePanel {

    public LeftPanel(int sidePanelWidth, int sidePanelHeight, String panelOwner, String shipSegmentImageName) {
        super(sidePanelWidth, sidePanelHeight, panelOwner, shipSegmentImageName);
    }

    @Override
    public void updatePlayerShipsStatusPanel() {
        this.sidePanel.getChildren().remove(0);
        this.sidePanel.getChildren().addAll(
                this.playerShipsStatusPanel.generateElement()
        );
    }
}
