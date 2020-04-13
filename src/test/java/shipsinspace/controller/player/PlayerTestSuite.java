package shipsinspace.controller.player;

import org.junit.*;
import shipsinspace.common.Coordinates;
import shipsinspace.controller.ships.ShipTemplate;
import shipsinspace.controller.ships.ShipsFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerTestSuite {
    @BeforeClass
    public static void beforeAllTests() {
        System.out.println("Starting Player objects test suite...");
    }
    @AfterClass
    public static void afterAllTests() {
        System.out.println("Player objects test suite COMPLETE.");
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
    public void testPlayerCreation() {
        // Given
        List<ShipTemplate> fleet = ShipsFactory.createFleet(new ArrayList<>());
        String playerName = "TestPlayer";
        Player player = new Player(playerName, fleet);
        System.out.print("Player object creation with proper input values...");

        // When
        String savedName = player.getName();
        List<ShipTemplate> savedFleet = player.getShips();

        // Then
        Assert.assertEquals(fleet, savedFleet);
        Assert.assertEquals(playerName, savedName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayerCreationNoName() {
        // Given
        List<ShipTemplate> fleet = ShipsFactory.createFleet(new ArrayList<>());
        String playerName = "";
        Player player = new Player(playerName, fleet);
        System.out.print("Player object creation with no name input...");

        // When
        // -

        // Then
        // -
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayerCreationNoShipsInFleet() {
        // Given
        List<ShipTemplate> fleet = new ArrayList<>();
        String playerName = "TestPlayer";
        Player player = new Player(playerName, fleet);
        System.out.print("Player object creation with no fleet input...");

        // When
        // -

        // Then
        // -
    }

    @Test
    public void testPlayerCreationNoFleet() {
        // Given
        List<ShipTemplate> fleet = new ArrayList<>();
        String playerName = "TestPlayer";
        Player player = new Player(playerName);
        System.out.print("Player object creation just with name...");

        // When
        String savedName = player.getName();
        List<ShipTemplate> savedFleet = player.getShips();

        // Then
        Assert.assertEquals(fleet, savedFleet);
        Assert.assertEquals(playerName, savedName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayerCreationNoFleetNoName() {
        // Given
        List<ShipTemplate> fleet = new ArrayList<>();
        String playerName = "";
        Player player = new Player(playerName);
        System.out.print("Player object creation just with name, but empty name input...");

        // When
        // -

        // Then
        // -
    }

    @Test
    public void testGetFieldsOccupiedByShips() {
        // Given
        List<ShipTemplate> fleet = ShipsFactory.createFleet();
        List<Coordinates> fieldsOccupiedByFleet = fleet.stream()
                .flatMap(s -> s.getShipSegments().stream())
                .collect(Collectors.toList());

        String playerName = "TestPlayer";
        Player player = new Player(playerName, fleet);
        System.out.print("Player object creation with no fleet input...");

        // When
        List<Coordinates> playerOccupiedFields = player.getFieldsOccupiedByShips();

        // Then
        Assert.assertEquals(fieldsOccupiedByFleet, playerOccupiedFields);
    }

    @Test
    public void testSetShips() {
        // Given
        List<ShipTemplate> fleet = ShipsFactory.createFleet(new ArrayList<>());
        String playerName = "TestPlayer";
        Player player = new Player(playerName);
        System.out.print("Setting ships...");

        // When
        List<ShipTemplate> savedFleet = player.getShips();
        player.setShips(fleet);
        List<ShipTemplate> updatedFleet = player.getShips();

        // Then
        Assert.assertEquals(new ArrayList<ShipTemplate>(), savedFleet);
        Assert.assertEquals(fleet, updatedFleet);
    }

    @Test
    public void testResetShips() {
        // Given
        List<ShipTemplate> fleet = ShipsFactory.createFleet(new ArrayList<>());
        String playerName = "TestPlayer";
        Player player = new Player(playerName, fleet);
        System.out.print("Resetting ships...");

        // When
        List<ShipTemplate> savedFleet = player.getShips();
        player.resetShips();
        List<ShipTemplate> resetFleet = player.getShips();

        // Then
        Assert.assertEquals(fleet, savedFleet);
        Assert.assertEquals(new ArrayList<ShipTemplate>(), resetFleet);
    }
}
