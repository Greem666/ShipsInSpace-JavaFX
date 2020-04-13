package shipsinspace.gameRegister;

import org.junit.*;
import shipsinspace.controller.GameController;

public class GameRegisterTestSuite {
    @BeforeClass
    public static void beforeAllTests() {
        System.out.println("Starting GameRegister objects test suite...");
    }
    @AfterClass
    public static void afterAllTests() {
        System.out.println("GameRegister objects test suite COMPLETE.");
    }
    @Before
    public void beforeEachTest() {
        System.out.print("Starting test ");
    }
    @After
    public void afterEachTest() {
        System.out.println("Test complete.");
    }

    @Test
    public void testGameControllerReturn() {
        // Given
        GameRegister gameRegisterOne = GameRegister.getInstance();
        GameRegister gameRegisterTwo = GameRegister.getInstance();
        System.out.print("Game controller return from two separate instances of a GameRegister.");

        // When
        GameController firstGameController = gameRegisterOne.getGameController();
        GameController secondGameController = gameRegisterTwo.getGameController();

        // Then
        Assert.assertEquals(firstGameController, secondGameController);
    }

    @Test
    public void testGameDifficultySettingAndReturn() {
        // Given
        GameRegister gameRegisterOne = GameRegister.getInstance();
        gameRegisterOne.setGameDifficulty(1);

        GameRegister gameRegisterTwo = GameRegister.getInstance();
        System.out.print("Game difficulty return from two separate instances of a GameRegister.");

        // When
        int gameDifficultyGameRegisterOne = gameRegisterOne.getGameDifficulty();
        int gameDifficultyGameRegisterTwo = gameRegisterTwo.getGameDifficulty();

        // Then
        Assert.assertEquals(gameDifficultyGameRegisterOne, gameDifficultyGameRegisterTwo);
    }

    @Test
    public void testPlayerNameSettingAndReturn() {
        // Given
        GameRegister gameRegisterOne = GameRegister.getInstance();
        gameRegisterOne.setHumanPlayerName("Test player 1");

        GameRegister gameRegisterTwo = GameRegister.getInstance();
        System.out.print("Human player name return from two separate instances of a GameRegister.");

        // When
        String humanPlayerNameGameRegisterOne = gameRegisterOne.getHumanPlayerName();
        String humanPlayerNameGameRegisterTwo = gameRegisterTwo.getHumanPlayerName();

        // Then
        Assert.assertEquals(humanPlayerNameGameRegisterOne, humanPlayerNameGameRegisterTwo);
    }
}
