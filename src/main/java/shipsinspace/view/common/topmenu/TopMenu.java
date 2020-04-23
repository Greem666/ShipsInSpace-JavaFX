package shipsinspace.view.common.topmenu;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import shipsinspace.registers.SoundsRegister;
import shipsinspace.view.GameWindow;
import shipsinspace.view.common.ConfirmBox;
import shipsinspace.view.difficultySelectionScene.DifficultySelection;

public class TopMenu {

    private SoundsRegister soundsRegister = SoundsRegister.getInstance();

    public Region generateElement() {

        // File menu
        Menu fileMenu = new Menu("File");
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction(e -> {
            closeProgram();
        });
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

        // MenuBar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, soundsMenu, aboutMenu);

        // Styling
        menuBar.getStylesheets().add(TopMenu.class.getResource("/css/topBarRegionStyle.css").toExternalForm());

        return menuBar;
    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Close program", "Sure, you want to exit \"Ships... but in Space\"?");
        Stage window = GameWindow.getPrimaryStage();
        if (answer) {
            window.close();
        }
    }
}
