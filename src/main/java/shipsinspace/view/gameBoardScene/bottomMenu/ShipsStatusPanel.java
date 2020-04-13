package shipsinspace.view.gameBoardScene.bottomMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import shipsinspace.view.gameBoardScene.interfaces.BottomMenuElements;

public class ShipsStatusPanel implements BottomMenuElements {

    private String shipsOwner;

    public ShipsStatusPanel(String shipsOwner) {
        this.shipsOwner = shipsOwner;
    }

    @Override
    public Region generateElement(double iconSize) {
        // PLAYER SHIPS SECTION
        GridPane shipsStatusDisplay = new GridPane();

        Label shipsCaption = new Label(this.shipsOwner + "'s ships:");
        shipsStatusDisplay.add(shipsCaption, 0, 0);

        String[][] ships = {
                {"Corvette", "2"},
                {"Scout", "2"},
                {"Destroyer", "3"},
                {"Battleship", "4"},
                {"Carrier", "5"},
        };

        for (int i = 0; i < ships.length; i++) {
            Image shipImage = new Image(getClass().getResourceAsStream("/ships/" + ships[i][0] + ".png"), iconSize * Integer.parseInt(ships[i][1]), iconSize, true, true);
            Button shipButton = new Button("", new ImageView(shipImage));
            shipsStatusDisplay.add(shipButton, 0, i + 1);
            shipButton.setTooltip(new Tooltip(ships[i][0]));

            ProgressIndicator healthIndicator = new ProgressIndicator();
            healthIndicator.setProgress(0.0);
            shipsStatusDisplay.add(healthIndicator, 1, i + 1);
        }

        shipsStatusDisplay.setAlignment(Pos.TOP_CENTER);
        shipsStatusDisplay.setBorder(
                new Border(
                        new BorderStroke(
                                Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(10),
                                BorderWidths.DEFAULT
                        )
                )
        );
        shipsStatusDisplay.setPadding(new Insets(0, 10, 0 ,10));

        return shipsStatusDisplay;
    }
}
