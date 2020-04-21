package shipsinspace.view.common.topmenu;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;
import shipsinspace.registers.SoundsRegister;

public class TopMenu {

    private SoundsRegister soundsRegister = SoundsRegister.getInstance();

    public Region generateElement() {
        MenuBar menuBar = new MenuBar();

        // File menu
        Menu fileMenu = new Menu("File");

        MenuItem quitItem = new MenuItem("Quit");

        fileMenu.getItems().add(quitItem);

        // Sounds menu
        Menu soundsMenu = new Menu("Sounds");

        CheckMenuItem musicOffItem = new CheckMenuItem("Music off");
        musicOffItem.setOnAction(e -> {
            if (soundsRegister.isPlayingBackgroundMusic()) {
                musicOffItem.setSelected(true);
                soundsRegister.stopBackgroundMusic();
            } else {
                musicOffItem.setSelected(false);
                soundsRegister.playBackgroundMusic();
            }
        });

        soundsMenu.getItems().add(musicOffItem);

        // About menu
        Menu aboutMenu = new Menu("About");

        MenuItem aboutItem = new MenuItem("About");

        aboutMenu.getItems().add(aboutItem);

        return menuBar;
    }
}
