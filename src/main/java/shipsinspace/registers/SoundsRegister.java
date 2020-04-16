package shipsinspace.registers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class SoundsRegister {
    private static SoundsRegister instance;
    private MediaPlayer backgroundMusicPlayer;
    private MediaPlayer sfxPlayer;

    private SoundsRegister() {
        Media sound = new Media(new File("src/main/resources/sounds/backgroundMusic/backgroundMusic.mp3").toURI().toString());
        this.backgroundMusicPlayer = new MediaPlayer(sound);
        this.sfxPlayer = new MediaPlayer(sound);
    }

    public static SoundsRegister getInstance() {
        if (instance == null) {
            instance = new SoundsRegister();
        }
        return instance;
    }

    public void playBackgroundMusic() {
        backgroundMusicPlayer.seek(Duration.ZERO);
        backgroundMusicPlayer.play();
        backgroundMusicPlayer.setVolume(0.5);
        backgroundMusicPlayer.play();
    }

    public void playHumanPlayerShot() {
        playSound("src/main/resources/sounds/effects/playerShot.mp3");
    }

    public void playComputerPlayerShot() {
        playSound("src/main/resources/sounds/effects/computerShot.mp3");
    }

    public void playExplosionSound() {
        playSound("src/main/resources/sounds/effects/explosion.mp3");
    }

    public void playButtonClickSound() {
        playSound("src/main/resources/sounds/effects/buttonClick.mp3");
    }

    public void playSound(String soundPath) {
        Media shotSound = new Media(new File(soundPath).toURI().toString());
        this.sfxPlayer = new MediaPlayer(shotSound);
        this.sfxPlayer.play();
    }
}
