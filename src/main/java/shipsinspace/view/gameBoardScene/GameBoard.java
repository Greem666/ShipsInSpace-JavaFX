package shipsinspace.view.gameBoardScene;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import shipsinspace.controller.GameController;
import shipsinspace.controller.player.Player;
import shipsinspace.registers.ScenesRegister;
import shipsinspace.view.common.topmenu.TopMenu;
import shipsinspace.view.gameBoardScene.board.Board;
import shipsinspace.view.gameBoardScene.bottomMenu.BottomMenu;
import shipsinspace.view.gameBoardScene.sidePanels.SidePanel;

public class GameBoard {
    private GameController backEnd;
    private BorderPane windowLayout = new BorderPane();
    private static int sidePanelWidth = 200;
    private SidePanel leftPanel;
    private StackPane leftStack;
    private Rectangle leftBackground;
    private SidePanel rightPanel;
    private StackPane rightStack;
    private Rectangle rightBackground;
    private BottomMenu bottomMenu = new BottomMenu(600, 200, 10);

    // Top Layout - MenuBar
    Region menuBar = new TopMenu().generateElement();

    public GameBoard(GameController gameController) {
        this.backEnd = gameController;
    }

    public Scene display() {

        this.backEnd.gameStatusReset();

        ScenesRegister scenesRegister = ScenesRegister.getInstance();
        scenesRegister.setGameBoard(this);



        // SIDE PANELS


        // window
        this.windowLayout.setTop(menuBar);

        // GAME SECTION
        Board board = new Board(this.backEnd, 600, 600);
        this.windowLayout.setCenter(board.generateElement());

//        LEFT PANEL
        leftPanel = new SidePanel(GameBoard.sidePanelWidth, 600, "Player", "shipSegmentPlayer.png");
        Region leftPanelRegion = leftPanel.generateElement();

        this.leftBackground = new Rectangle(GameBoard.sidePanelWidth, 600);
        RadialGradient leftBackgroundGradient = new RadialGradient(
                0,
                -1.0,
                GameBoard.sidePanelWidth / 2.0,
                600 / 2.0,
                280,
                false,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(89, 255, 253, 1.0)),
                new Stop(1, Color.rgb(11, 12, 17, 1.0)));
        this.leftBackground.setFill(leftBackgroundGradient);
        this.leftBackground.setStroke(Color.BLACK);
        this.leftBackground.setStrokeWidth(2);

        this.leftStack = new StackPane();
        this.leftStack.getChildren().addAll(leftBackground, leftPanelRegion);

        this.windowLayout.setLeft(leftStack);
        BorderPane.setAlignment(leftStack, Pos.CENTER);

        // RIGHT PANEL
        rightPanel = new SidePanel(GameBoard.sidePanelWidth, 600, "Computer", "shipSegmentEnemy.png");
        Region rightPanelRegion = rightPanel.generateElement();

        this.rightBackground = new Rectangle(GameBoard.sidePanelWidth, 600);
        RadialGradient rightBackgroundGradient = new RadialGradient(
                0,
                1.0,
                GameBoard.sidePanelWidth / 2.0,
                600 / 2.0,
                280,
                false,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(255, 89, 89, 1.0)),
                new Stop(1, Color.rgb(11, 12, 17, 1.0)));
        this.rightBackground.setFill(rightBackgroundGradient);
        this.rightBackground.setStroke(Color.BLACK);
        this.rightBackground.setStrokeWidth(2);

        this.rightStack = new StackPane();
        this.rightStack.getChildren().addAll(rightBackground, rightPanelRegion);

        this.windowLayout.setRight(rightStack);
        BorderPane.setAlignment(rightStack, Pos.CENTER);

//        windowLayout.setBottom(bottomMenu.generateElement());

        // Scene
        Scene gameScene = new Scene(windowLayout, ScenesRegister.sceneWidth + GameBoard.sidePanelWidth * 2, ScenesRegister.sceneHeight);

        return gameScene;
    }

    public void updateSidePanelShipStatus(Player player) {
        try {
            if (player.getName().equals("Computer")) {
                updateRightPanelShipStatus();
            } else {
                updateLeftPanelShipStatus();
            }
        } catch (NullPointerException e) {
            // Nobody was hit, do nothing
        }
    }

    public void updateLeftPanelShipStatus() {
        leftPanel.updatePlayerShipsStatusPanel();
        this.leftStack.getChildren().remove(0, 2);
        this.leftStack.getChildren().addAll(leftBackground, leftPanel.generateElement());
        windowLayout.setLeft(this.leftStack);
    }

    public void updateRightPanelShipStatus() {
        rightPanel.updatePlayerShipsStatusPanel();
        this.rightStack.getChildren().remove(0, 2);
        this.rightStack.getChildren().addAll(rightBackground, rightPanel.generateElement());
        windowLayout.setRight(this.rightStack);
    }

//    public void updateBottomPanelShipStatus(Player player) {
//        try {
//            if (player.getName().equals("Computer")) {
//                updateBottomPanelComputerShipStatus();
//            } else {
//                updateBottomPanelHumanShipStatus();
//            }
//        } catch (NullPointerException e) {
//            // Nobody was hit, do nothing
//        }
//
//    }
//
//    public void updateBottomPanelHumanShipStatus() {
//        bottomMenu.updateHumanPlayerShipsStatusPanel();
//        windowLayout.setBottom(bottomMenu.getBottomControlPanel());
//    }
//
//    public void updateBottomPanelComputerShipStatus() {
//        bottomMenu.updateComputerPlayerShipsStatusPanel();
//        windowLayout.setBottom(bottomMenu.getBottomControlPanel());
//    }
}