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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import shipsinspace.gameRegister.GameRegister;
import shipsinspace.view.gameBoardScene.interfaces.BottomMenuElements;

public class ShipsStatusPanel implements BottomMenuElements {

    private String shipsOwner;
    private Image shipImage;
    private double iconSize;
    private GameRegister gameRegister;

    public ShipsStatusPanel(String shipsOwner, String shipImageName, double iconSize) {
        this.shipsOwner = shipsOwner;
        this.iconSize = iconSize;
        this.shipImage = new Image(getClass().getResourceAsStream("/ships/" + shipImageName), iconSize, iconSize, true, true);
        this.gameRegister = GameRegister.getInstance();
    }

    @Override
    public Region generateElement() {
        // PLAYER SHIPS SECTION
        VBox shipsStatusLayout = new VBox();
        Label shipsCaption = new Label(this.shipsOwner + "'s ships:");
        shipsStatusLayout.getChildren().add(shipsCaption);

        GridPane shipsStatusDisplay = new GridPane();
        shipsStatusDisplay.setVgap(5);
        shipsStatusLayout.getChildren().add(shipsStatusDisplay);

        String[][] ships = {
                {"Corvette", "2"},
                {"Scout", "2"},
                {"Destroyer", "3"},
                {"Battleship", "4"},
                {"Carrier", "5"},
        };

        for (int i = 0; i < ships.length; i++) {
//            Button shipButton = new Button("", new ImageView(shipImage));
            shipsStatusDisplay.add(new Label(ships[i][0]), 0, i);
            for (int j = 0; j < Integer.parseInt(ships[i][1]); j++) {
                shipsStatusDisplay.add(new Rectangle(iconSize, iconSize, new ImagePattern(shipImage)), j + 2, i);
            }

//            shipButton.setTooltip(new Tooltip(ships[i][0]));

//            ProgressIndicator healthIndicator = new ProgressIndicator();
//            healthIndicator.setProgress(0.0);
//            shipsStatusDisplay.add(healthIndicator, 1, i + 1);
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

        return shipsStatusLayout;
    }
}
