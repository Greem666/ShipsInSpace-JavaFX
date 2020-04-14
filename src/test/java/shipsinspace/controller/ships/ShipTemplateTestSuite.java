package shipsinspace.controller.ships;

import org.junit.*;
import shipsinspace.common.Coordinates;
import shipsinspace.controller.ships.attackTypes.Attack;
import shipsinspace.controller.ships.attackTypes.StandardAttack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class TestShipType extends ShipTemplate {
    public TestShipType() {
        super("TestShipType", new StandardAttack(), 4);
    }
}

public class ShipTemplateTestSuite {
    @BeforeClass
    public static void beforeAllTests() {
        System.out.println("Starting ShipTemplate objects test suite...");
    }
    @AfterClass
    public static void afterAllTests() {
        System.out.println("ShipTemplate objects test suite COMPLETE.");
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
    public void testShipTemplateCreation() {
        // Given
        TestShipType testShip = new TestShipType();
        System.out.print("Testing test ship type creation...");

        // When
        List<ShipSegment> startingSegmentsList = testShip.getShipSegments();
        int startingSegmentsCount = testShip.getStartingSegmentsCount();
        Attack attackType = testShip.getAttackType();
        String shipName = testShip.getShipName();

        // Then
        Assert.assertEquals(new ArrayList<ShipSegment>(), startingSegmentsList);
        Assert.assertEquals(0, startingSegmentsList.size());
        Assert.assertEquals(4, startingSegmentsCount);
        Assert.assertEquals(new StandardAttack(), attackType);
        Assert.assertEquals("TestShipType", shipName);
    }

    @Test
    public void testShipTemplateCreationAddingOneElement() {
        // Given
        TestShipType testShip = new TestShipType();
        System.out.print("Testing adding new segment to test ship...");

        // When
        ShipSegment newShipSegment = new ShipSegment(1, 1);
        testShip.addShipSegment(newShipSegment);
        List<ShipSegment> shipSegmentsListAddedOne = testShip.getShipSegments();
        List<Coordinates> expectedShipSegmentsListAddedOne = new ArrayList<Coordinates>(Arrays.asList(newShipSegment));

        // Then
        Assert.assertEquals(expectedShipSegmentsListAddedOne, shipSegmentsListAddedOne);
    }

    @Test
    public void testShipTemplateCreationSettingNewSegmentsList() {
        // Given
        TestShipType testShip = new TestShipType();
        System.out.print("Testing setting new segments list to test ship...");

        // When
        List<ShipSegment> shipSegments = new ArrayList<>(
                Arrays.asList(
                        new ShipSegment(2, 2),
                        new ShipSegment(3, 3)
                )
        );
        testShip.setShipSegments(shipSegments);
        List<ShipSegment> expectedShipSegmentsListReplacedAll = testShip.getShipSegments();

        // Then
        Assert.assertEquals(expectedShipSegmentsListReplacedAll, shipSegments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShipTemplateCreationWithRepeatedSegments() {
        // Given
        TestShipType testShip = new TestShipType();
        System.out.print("Testing test ship type creation with repeated segments...");

        // When
        ShipSegment newShipSegment = new ShipSegment(1, 1);
        testShip.addShipSegment(newShipSegment);
        testShip.addShipSegment(newShipSegment);

        // Then
        // -
    }

    @Test
    public void testShipTemplateDestroySegment() {
        // Given
        TestShipType testShip = new TestShipType();
        System.out.print("Testing ship segment destruction method...");

        // When
        ShipSegment newShipSegment1 = new ShipSegment(1, 1);
        testShip.addShipSegment(newShipSegment1);
        boolean initialSegment1DestroyedState = newShipSegment1.isDestroyed();

        Coordinates attackCoordinates = new Coordinates(1, 1);
        testShip.destroyShipSegment(attackCoordinates);

        // Then
        Assert.assertFalse(initialSegment1DestroyedState);
        Assert.assertTrue(newShipSegment1.isDestroyed());
    }


}
