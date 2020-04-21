package shipsinspace.view.gameBoardScene.board.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EmptyImageView extends ImageView {
    private static final Image emptyField = new Image(EmptyImageView.class.getResourceAsStream("/common/emptyImage.png"));

    public EmptyImageView(double size) {
        super(emptyField);
        this.setFitHeight(size);
        this.setFitWidth(size);
    }


}
