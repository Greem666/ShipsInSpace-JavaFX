package shipsinspace.controller.ships;

import org.junit.*;
import shipsinspace.common.Coordinates;

public class ShipSegmentTestSuite {
    @BeforeClass
    public static void beforeAllTests() {
        System.out.println("Starting ShipSegment objects test suite...");
    }
    @AfterClass
    public static void afterAllTests() {
        System.out.println("ShipSegment objects test suite COMPLETE.");
    }
    @Before
    public void beforeEachTest() {
        System.out.print("Starting test: ");
    }
    @After
    public void afterEachTest() {
        System.out.println("Test complete.");
    }

    @Test
    public void testShipSegmentCreation2ArgConstructor() {
        // Given
        ShipSegment shipSegment = new ShipSegment(1, 1);
        System.out.print("2 argument constructor... ");

        // When
        int savedCoordinateX = shipSegment.getX();
        int savedCoordinateY = shipSegment.getY();
        boolean savedDestroyedAfterInit = shipSegment.isDestroyed();

        shipSegment.setDestroyed(true);
        boolean savedDestroyedAfterSet = shipSegment.isDestroyed();

        // Then
        Assert.assertEquals(1, savedCoordinateX);
        Assert.assertEquals(1, savedCoordinateY);
        Assert.assertFalse(savedDestroyedAfterInit);
        Assert.assertTrue(savedDestroyedAfterSet);
    }

    @Test
    public void testShipSegmentToCoordinateComparison() {
        // Given
        ShipSegment shipSegment = new ShipSegment(1, 1);
        System.out.println("Comparison between ShipSegment object and Coordinate object, with the same X and Y values.");

        // When
        Coordinates coordinates = new Coordinates(1, 1);

        // Then
        Assert.assertEquals(coordinates, shipSegment);
    }
}
