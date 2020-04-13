package shipsinspace.controller.ships;

import org.junit.*;
import shipsinspace.common.Coordinates;
import shipsinspace.controller.ships.attackTypes.Attack;
import shipsinspace.controller.ships.attackTypes.StandardAttack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Coordinates> startingSegmentsList = testShip.getShipSegments();
        int startingSegmentsCount = testShip.getStartingSegmentsCount();
        Attack attackType = testShip.getAttackType();
        String shipName = testShip.getShipName();

        // Then
        Assert.assertEquals(new ArrayList<Coordinates>(), startingSegmentsList);
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
        Coordinates newShipSegment = new Coordinates(1, 1);
        testShip.addShipSegment(newShipSegment);
        List<Coordinates> shipSegmentsListAddedOne = testShip.getShipSegments();
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
        List<Coordinates> shipSegments = new ArrayList<>(
                Arrays.asList(
                        new Coordinates(2, 2),
                        new Coordinates(3, 3)
                )
        );
        testShip.setShipSegments(shipSegments);
        List<Coordinates> expectedShipSegmentsListReplacedAll = testShip.getShipSegments();

        // Then
        Assert.assertEquals(expectedShipSegmentsListReplacedAll, shipSegments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShipTemplateCreationWithRepeatedSegments() {
        // Given
        TestShipType testShip = new TestShipType();
        System.out.print("Testing test ship type creation with repeated segments...");

        // When
        Coordinates newShipSegment = new Coordinates(1, 1);
        testShip.addShipSegment(newShipSegment);
        testShip.addShipSegment(newShipSegment);

        // Then
        // -
    }


}
