package shipsinspace.registers;

import org.junit.*;

import java.util.*;

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

    @Test
    public void testAllShipsStatusSettersAndGetters() {
        // Given
        GameRegister gameRegister = GameRegister.getInstance();

        // When
        Map<String, List<Boolean>> mapOfHumanShipStatuses = new HashMap<String, List<Boolean>>()
        {{
            put("Corvette", Arrays.asList(true, true));
            put("Scout", Arrays.asList(true, false));
            put("Destroyer", Arrays.asList(true, true, false));
            put("Battleship", Arrays.asList(true, true, false, true));
            put("Carrier", Arrays.asList(true, true, false, false, true));
        }};
        Map<String, List<Boolean>> mapOfComputerShipStatuses = new HashMap<String, List<Boolean>>()
        {{
            put("Corvette", Arrays.asList(true, false));
            put("Scout", Arrays.asList(true, false));
            put("Destroyer", Arrays.asList(true, true, false));
            put("Battleship", Arrays.asList(false, true, false, true));
            put("Carrier", Arrays.asList(false, true, false, false, true));
        }};
        gameRegister.setHumanAllShipsStatus(mapOfHumanShipStatuses);
        gameRegister.setComputerAllShipsStatus(mapOfComputerShipStatuses);

        // Then
        Assert.assertEquals(mapOfHumanShipStatuses, gameRegister.getHumanAllShipsStatus());
        Assert.assertEquals(mapOfComputerShipStatuses, gameRegister.getComputerAllShipsStatus());
    }
}
