package shipsinspace.view.interfaces;

import shipsinspace.controller.ships.ShipTemplate;
import shipsinspace.common.Coordinates;

public interface ControllerToViewInterface {
    boolean markTiles(Coordinates...coordinates);
    boolean updateShipStatus(ShipTemplate... ships);
}
