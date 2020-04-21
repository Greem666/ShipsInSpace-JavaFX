package shipsinspace.view.gameBoardScene.board.elements;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import shipsinspace.registers.SoundsRegister;

import java.util.Arrays;
import java.util.List;

public class Effects extends EmptyImageView {

    final Image explosionFrame0 = new Image(getClass().getResourceAsStream("/common/emptyImage.png"));
    final Image explosionFrame1 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion1_no_background.png"));
    final Image explosionFrame2 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion2_no_background.png"));
    final Image explosionFrame3 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion3_no_background.png"));
    final Image explosionFrame4 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion4_no_background.png"));
    final Image explosionFrame5 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion5_no_background.png"));
    final Image explosionFrame6 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion6_no_background.png"));
    final Image explosionFrame7 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion7_no_background.png"));
    final Image explosionFrame8 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion8_no_background.png"));

    final Image humanExplosionFrame0 = new Image(getClass().getResourceAsStream("/common/emptyImage.png"));
    final Image humanExplosionFrame1 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion1_no_background.png"));
    final Image humanExplosionFrame2 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion2_no_background.png"));
    final Image humanExplosionFrame3 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion3_no_background.png"));
    final Image humanExplosionFrame4 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion4_no_background.png"));
    final Image humanExplosionFrame5 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion5_no_background.png"));
    final Image humanExplosionFrame6 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion6_no_background.png"));
    final Image humanExplosionFrame7 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion7_no_background.png"));
    final Image humanExplosionFrame8 = new Image(getClass().getResourceAsStream("/effects/explosion/human/explosion8_no_background.png"));

    final Image computerExplosionFrame0 = new Image(getClass().getResourceAsStream("/common/emptyImage.png"));
    final Image computerExplosionFrame1 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion1_no_background.png"));
    final Image computerExplosionFrame2 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion2_no_background.png"));
    final Image computerExplosionFrame3 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion3_no_background.png"));
    final Image computerExplosionFrame4 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion4_no_background.png"));
    final Image computerExplosionFrame5 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion5_no_background.png"));
    final Image computerExplosionFrame6 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion6_no_background.png"));
    final Image computerExplosionFrame7 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion7_no_background.png"));
    final Image computerExplosionFrame8 = new Image(getClass().getResourceAsStream("/effects/explosion/computer/explosion8_no_background.png"));

    private SoundsRegister soundsRegister;

    public Effects(double size) {
        super(size);
        this.soundsRegister = SoundsRegister.getInstance();
    }

    public Transition gameOverAnimation(double animationTime) {
        Image[] explosionAnimationImages = {
                explosionFrame0, explosionFrame1,
                explosionFrame2, explosionFrame3,
                explosionFrame4, explosionFrame5,
                explosionFrame6, explosionFrame7,
                explosionFrame8, explosionFrame0
        };

        return animateExplosionReturnTransition(explosionAnimationImages, animationTime);
    }

    public Transition animateExplosionReturnTransition(Image[] explosionAnimationImages, double animationTime) {
        soundsRegister.playExplosionSound(1.0);
        List<Image> explosionAnimationList = Arrays.asList(explosionAnimationImages);
        ImageView handle = this;

        Transition animation = new Transition() {
            {
                setCycleDuration(Duration.millis(animationTime)); // total time for animation
            }

            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction*(explosionAnimationList.size()-1));
                handle.setImage(explosionAnimationList.get(index));
            }
        };

        return animation;
    }

    public void animateExplosionAutoPlay(Image[] explosionAnimationImages) {
        animateExplosionAutoPlay(explosionAnimationImages, 500);
    }

    public void animateExplosionAutoPlay(Image[] explosionAnimationImages, double animationTime) {
        List<Image> explosionAnimationList = Arrays.asList(explosionAnimationImages);
        ImageView handle = this;

        Transition animation = new Transition() {
            {
                setCycleDuration(Duration.millis(animationTime)); // total time for animation
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
        Image[] explosionAnimationImages = {
                humanExplosionFrame0, humanExplosionFrame1,
                humanExplosionFrame2, humanExplosionFrame3,
                humanExplosionFrame4, humanExplosionFrame5,
                humanExplosionFrame6, humanExplosionFrame7,
                humanExplosionFrame8, humanExplosionFrame0
        };

        animateExplosionAutoPlay(explosionAnimationImages);
    }

    public void animateComputerPlayerExplosion() {
        Image[] explosionAnimationImages = {
                computerExplosionFrame0, computerExplosionFrame1,
                computerExplosionFrame2, computerExplosionFrame3,
                computerExplosionFrame4, computerExplosionFrame5,
                computerExplosionFrame6, computerExplosionFrame7,
                computerExplosionFrame8, computerExplosionFrame0
        };

        animateExplosionAutoPlay(explosionAnimationImages);
    }
}
