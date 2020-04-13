package shipsinspace.view.gameBoardScene.topMenu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Region;
import shipsinspace.view.gameBoardScene.interfaces.WindowElements;

public class TopMenu implements WindowElements {

    public Region generateElement(int windowWidth, int windowHeight) {
        return generateElement(windowWidth, windowHeight, 0);
    }

    @Override
    public Region generateElement(int windowWidth, int windowHeight, int tilesCount) {
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuAbout = new Menu("About");
        menuBar.getMenus().addAll(menuFile, menuAbout);

        return menuBar;
    }



}
