package shipsinspace.view.gameBoardScene.interfaces;
import javafx.scene.layout.Region;

public interface WindowElements {
    /**
     * Interface all layout elements of the Ships In Space's window have to implement.
     * @param windowWidth Width of the top-level window of the game.
     * @param windowHeight Height of the top-level window of the game.
     * @param tilesCount Number of tiles in either X or Y direction.
     * @return Region element, which can be added to the main Scene's layout.
     */
    Region generateElement(int windowWidth, int windowHeight, int tilesCount);
}
