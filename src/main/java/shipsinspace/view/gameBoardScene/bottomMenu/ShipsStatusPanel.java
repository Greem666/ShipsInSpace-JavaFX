package shipsinspace.view.gameBoardScene.bottomMenu;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import shipsinspace.registers.GameRegister;
import shipsinspace.view.common.topmenu.TopMenu;

import java.util.*;

public class ShipsStatusPanel {

    private String shipsOwner;
    private Image shipImage;
    private double iconSize;
    private GameRegister gameRegister;
    private Region shipStatusPanel;

    public ShipsStatusPanel(String shipsOwner, String shipImageName, double iconSize) {
        this.shipsOwner = shipsOwner;
        this.iconSize = iconSize;
        this.shipImage = new Image(getClass().getResourceAsStream("/ships/" + shipImageName), iconSize, iconSize, true, true);
        this.gameRegister = GameRegister.getInstance();
    }

    public Region generateElement() {
        // PLAYER SHIPS SECTION
        VBox shipsStatusLayout = new VBox();
        shipsStatusLayout.setSpacing(20);
        Label shipsOwnerLabel = new Label(this.shipsOwner);
        shipsOwnerLabel.setId("shipOwnerLabel");
        shipsStatusLayout.getChildren().add(shipsOwnerLabel);

        Map<String, List<Boolean>> playerShipsStatus;

        if (this.shipsOwner.equals("Computer")) {
            playerShipsStatus = gameRegister.getComputerAllShipsStatus();
        } else {
            playerShipsStatus = gameRegister.getHumanAllShipsStatus();
        }

        drawShipsStatusPanel(playerShipsStatus, shipsStatusLayout);

        shipsStatusLayout.setAlignment(Pos.CENTER);

        shipsStatusLayout.getStylesheets().add(TopMenu.class.getResource("/css/gameBoardScene/sidePanel/shipStatusPanel.css").toExternalForm());

        this.shipStatusPanel = shipsStatusLayout;

        return this.shipStatusPanel;
    }

    public Region getShipStatusPanel() {
        return shipStatusPanel;
    }

    public void drawShipsStatusPanel(Map<String, List<Boolean>> listOfShipStatuses, VBox vBox) {

        List<String> shipTypeNamesInOrder = Arrays.asList("Corvette", "Scout", "Destroyer", "Battleship", "Carrier");
//        vBox.getChildren().remove()

        for (String shipName: shipTypeNamesInOrder) {
            VBox shipOwnerLabelAndShipSegmentStatusLayout = new VBox();
            shipOwnerLabelAndShipSegmentStatusLayout.setSpacing(10);
            shipOwnerLabelAndShipSegmentStatusLayout.setAlignment(Pos.CENTER);
            List<Boolean> shipSegmentStatuses = listOfShipStatuses.get(shipName);

            HBox ShipSegmentStatusLayout = new HBox();
            ShipSegmentStatusLayout.setAlignment(Pos.CENTER);

            // Ship name label
            Label shipNameLabel = new Label(shipName);

            if (!shipSegmentStatuses.contains(false)) {
                shipNameLabel.getStyleClass().add("shipDestroyedLabel");
            }

            shipOwnerLabelAndShipSegmentStatusLayout.getChildren().add(shipNameLabel);
            shipOwnerLabelAndShipSegmentStatusLayout.getChildren().add(ShipSegmentStatusLayout);


            for (Boolean shipSegmentStatus : shipSegmentStatuses) {

                // Ship segments display
                StackPane stackPane = new StackPane();
                Rectangle segmentStatusPanel = new Rectangle(iconSize, iconSize);
                ImageView segmentImagePanel = new ImageView(shipImage);
                segmentStatusPanel.setFill(shipSegmentStatus ? Color.RED : Color.TRANSPARENT);
                stackPane.getChildren().addAll(segmentStatusPanel, segmentImagePanel);

//                // Final panel
//                gridPane.add(stackPane, j, i + 1);
                ShipSegmentStatusLayout.getChildren().add(stackPane);
            }

            vBox.getChildren().add(shipOwnerLabelAndShipSegmentStatusLayout);
        }

    }
}
