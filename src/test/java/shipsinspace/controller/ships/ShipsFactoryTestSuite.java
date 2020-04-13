package shipsinspace.controller.ships;

import org.junit.*;
import shipsinspace.common.Coordinates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ShipsFactoryTestSuite {
    @BeforeClass
    public static void beforeAllTests() {
        System.out.println("Starting ShipsFactory objects test suite...");
    }
    @AfterClass
    public static void afterAllTests() {
        System.out.println("ShipsFactory objects test suite COMPLETE.");
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
    public void testCreateFleetNoRepeatedSegmentsNoOccupiedFields() {
        // Given
        List<ShipTemplate> fleet = ShipsFactory.createFleet(new ArrayList<Coordinates>());
        System.out.print("Fleet creation with no previously occupied fields...");

        // When
        List<Coordinates> allShipSegments = fleet.stream()
                .flatMap(s -> s.getShipSegments().stream())
                .collect(Collectors.toList());
        int allShipSegmentsCount = allShipSegments.size();

        Set<Coordinates> setAllShipSegments = fleet.stream()
                .flatMap(s -> s.getShipSegments().stream())
                .collect(Collectors.toSet());
        int setAllShipSegmentsCount = setAllShipSegments.size();

        // Then
        Assert.assertEquals(setAllShipSegmentsCount, allShipSegmentsCount);
    }

    @Test
    public void testCreateFleetNoRepeatedSegmentsSomeOccupiedFields() {
        // Given
        List<Coordinates> occupiedFields = ShipsFactory.createFleet(new ArrayList<Coordinates>()).stream()
                .flatMap(s -> s.getShipSegments().stream())
                .collect(Collectors.toList());
        int previouslyOccupiedFields = occupiedFields.size();

        List<ShipTemplate> currentFleet = ShipsFactory.createFleet(occupiedFields);

        System.out.print("Fleet creation with some previously occupied fields...");


        // When
        List<Coordinates> allShipSegments = currentFleet.stream()
                .flatMap(s -> s.getShipSegments().stream())
                .collect(Collectors.toList());
        int currentlyOccupiedFields = allShipSegments.size();

        occupiedFields.addAll(allShipSegments);
        Set<Coordinates> allOccupiedFields = new HashSet<>(occupiedFields);

        // Then
        Assert.assertEquals(previouslyOccupiedFields + currentlyOccupiedFields, allOccupiedFields.size());
    }

    @Test
    public void testCreateFleetProperFleetSize() {
        // Given
        List<ShipTemplate> fleet = ShipsFactory.createFleet(new ArrayList<Coordinates>());
        System.out.print("Fleet creation - proper output size...");

        // When
        int fleetSize = fleet.size();

        // Then
        Assert.assertEquals(5, fleetSize);
    }
}
