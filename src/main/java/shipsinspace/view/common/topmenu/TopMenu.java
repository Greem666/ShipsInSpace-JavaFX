package shipsinspace.view.common.topmenu;

import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import shipsinspace.registers.SoundsRegister;
import shipsinspace.view.GameWindow;
import shipsinspace.view.common.QuitAlertBox;

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
        RadioMenuItem  musicOnItem = new RadioMenuItem("Music on");
        musicOnItem.setSelected(true);
        RadioMenuItem  musicOffItem = new RadioMenuItem("Music off");

        musicOffItem.setOnAction(e -> {
            if (soundsRegister.isPlayingBackgroundMusic()) {
                musicOnItem.setSelected(false);
                musicOffItem.setSelected(true);
                soundsRegister.pauseBackgroundMusic();
            }
        });
        musicOnItem.setOnAction(e -> {
            if (!soundsRegister.isPlayingBackgroundMusic()) {
                musicOnItem.setSelected(true);
                musicOffItem.setSelected(false);
                soundsRegister.playBackgroundMusic();
            }
        });

        soundsMenu.getItems().addAll(musicOnItem, musicOffItem);

        // About menu
        Menu aboutMenu = new Menu("About");
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText("Ships... but in space");
            alert.setContentText("This game has been coded as a " +
                    "project for Kodilla Java Developer course by Greem666.\n" +
                    "All rights are reserved.\n" +
                    "24 April 2020");
            alert.showAndWait();
        });
        aboutMenu.getItems().add(aboutItem);

        // MenuBar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, soundsMenu, aboutMenu);

        // Styling
        menuBar.getStylesheets().add(TopMenu.class.getResource("/css/common/topBarRegionStyle.css").toExternalForm());

        return menuBar;
    }

    private void closeProgram() {
        ButtonType answer = QuitAlertBox.closeProgram();
        Stage window = GameWindow.getPrimaryStage();
        if (answer == ButtonType.OK) {
            window.close();
        }
    }
}
