package shipsinspace.view.gameBoardScene.board.elements;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

public class ShipView extends EmptyImageView {

    private RotateTransition rotation = new RotateTransition(Duration.millis(3000), this);
    private ScaleTransition scale = new ScaleTransition(Duration.millis(2000), this);
    private boolean isRotating;
    private boolean isScaling;

    public ShipView(double size) {
        super(size);
    }

    public void startRotatingShip() {
        if (!isRotating) {
            rotation.setByAngle(180);
            rotation.setCycleCount(RotateTransition.INDEFINITE);
            rotation.setAutoReverse(true);
            rotation.play();
            isRotating = true;
        }
    }

    public void stopRotatingShip() {
        if (!isScaling) {
            rotation.stop();
            isRotating = false;
        }
    }

    public void startDamagedAnimation() {
        scale.setByX(-0.2f);
        scale.setByY(-0.2f);

        scale.setCycleCount(RotateTransition.INDEFINITE);
        scale.setAutoReverse(true);
        scale.play();

        isScaling = true;

        if (!isRotating) {
            rotation.play();
        }
    }
}
