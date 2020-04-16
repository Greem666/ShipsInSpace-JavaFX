package shipsinspace.view.gameBoardScene.board;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.Arrays;
import java.util.List;

public class Effects extends EmptyImageView {

    public Effects(double size) {
        super(size);
    }

    public void animateExplosion(Image[] explosionAnimationImages) {
        List<Image> explosionAnimationList = Arrays.asList(explosionAnimationImages);
        ImageView handle = this;

        Transition animation = new Transition() {
            {
                setCycleDuration(Duration.millis(500)); // total time for animation
            }

            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction*(explosionAnimationList.size()-1));
                handle.setImage(explosionAnimationList.get(index));
            }
        };
        animation.play();
    }

    public void animateHumanPlayerExplosion() {
        final Image explosionFrame0 = new Image(getClass().getResourceAsStream("/common/emptyImage.png"));
        final Image explosionFrame1 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion1_no_background.png"));
        final Image explosionFrame2 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion2_no_background.png"));
        final Image explosionFrame3 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion3_no_background.png"));
        final Image explosionFrame4 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion4_no_background.png"));
        final Image explosionFrame5 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion5_no_background.png"));
        final Image explosionFrame6 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion6_no_background.png"));
        final Image explosionFrame7 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion7_no_background.png"));
        final Image explosionFrame8 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion8_no_background.png"));

        Image[] explosionAnimationImages = {
                explosionFrame0, explosionFrame1,
                explosionFrame2, explosionFrame3,
                explosionFrame4, explosionFrame5,
                explosionFrame6, explosionFrame7,
                explosionFrame8, explosionFrame0
        };

        animateExplosion(explosionAnimationImages);
    }

    public void animateComputerPlayerExplosion() {
        final Image explosionFrame0 = new Image(getClass().getResourceAsStream("/common/emptyImage.png"));
        final Image explosionFrame1 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion1_no_background.png"));
        final Image explosionFrame2 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion2_no_background.png"));
        final Image explosionFrame3 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion3_no_background.png"));
        final Image explosionFrame4 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion4_no_background.png"));
        final Image explosionFrame5 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion5_no_background.png"));
        final Image explosionFrame6 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion6_no_background.png"));
        final Image explosionFrame7 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion7_no_background.png"));
        final Image explosionFrame8 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion8_no_background.png"));

        Image[] explosionAnimationImages = {
                explosionFrame0, explosionFrame1,
                explosionFrame2, explosionFrame3,
                explosionFrame4, explosionFrame5,
                explosionFrame6, explosionFrame7,
                explosionFrame8, explosionFrame0
        };

        animateExplosion(explosionAnimationImages);
    }
}
