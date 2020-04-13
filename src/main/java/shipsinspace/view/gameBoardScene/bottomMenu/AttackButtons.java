package shipsinspace.view.gameBoardScene.bottomMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import shipsinspace.view.gameBoardScene.interfaces.BottomMenuElements;

public class AttackButtons implements BottomMenuElements {
    private String activeAttack;

    @Override
    public Region generateElement(double iconSize) {
        // ATTACK BUTTONS PANEL
        VBox attackButtonsPanel = new VBox();

        Label attacksLabel = new Label("Available attacks:");
        attackButtonsPanel.getChildren().add(attacksLabel);

        GridPane attackButtons = new GridPane();
        final ToggleGroup attackGroup = new ToggleGroup();

        String[][] buttonsData = {
                {"standard.png", "Standard attack", "y"},
                {"scout.png", "Scout action", "n"},
                {"sonar.png", "Scout action", "n"},
                {"roundOfFire.png", "Round of Fire attack", "n"},
                {"railgun.png", "Railgun attack", "n"},
                {"bombardment.png", "Bombardment attack", "n"}
        };

        for (int i = 0; i < buttonsData.length; i++) {
            Image icon = new Image(getClass().getResourceAsStream("/icons/" + buttonsData[i][0]), iconSize, iconSize, true, true);
            ToggleButton button = new ToggleButton("", new ImageView(icon));
            button.setTooltip(new Tooltip(buttonsData[i][1]));

            button.setToggleGroup(attackGroup);

            final String attackType = buttonsData[i][1];
            button.setOnAction(e -> activeAttack = attackType);

            if (buttonsData[i][2] == "y") {
                button.setSelected(true);
                button.fire();
            }

            attackButtons.add(button, i % 3, i / 3);
        }

        attackButtons.setAlignment(Pos.CENTER);
        attackButtons.setBorder(
                new Border(
                        new BorderStroke(
                                Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(10),
                                BorderWidths.DEFAULT
                        )
                )
        );
        attackButtons.setPadding(new Insets(10, 10, 10 ,10));
        attackButtonsPanel.getChildren().add(attackButtons);

        return attackButtonsPanel;
    }
}
