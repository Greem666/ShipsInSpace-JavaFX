package shipsinspace.common;

import org.junit.*;

public class CoordinatesTestSuite {
    @BeforeClass
    public static void beforeAllTests() {
        System.out.println("Starting Coordinate objects test suite...");
    }
    @AfterClass
    public static void afterAllTests() {
        System.out.println("Coordinate objects test suite COMPLETE.");
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
    public void testCoordinatesPositiveValues() {
        // Given
        Coordinates coordinates1 = new Coordinates(1, 1);
        Coordinates coordinates2 = new Coordinates(2, 2);
        Coordinates coordinates3 = new Coordinates(3, 3);
        Coordinates coordinates4 = new Coordinates(4, 4);
        System.out.print("Initialization with positive values... ");

        // When
        int corr1_X = coordinates1.getX();
        int corr1_Y = coordinates1.getY();
        int corr2_X = coordinates2.getX();
        int corr2_Y = coordinates2.getY();
        int corr3_X = coordinates3.getX();
        int corr3_Y = coordinates3.getY();
        int corr4_X = coordinates4.getX();
        int corr4_Y = coordinates4.getY();

        // Then
        Assert.assertEquals(1, corr1_X);
        Assert.assertEquals(1, corr1_Y);
        Assert.assertEquals(2, corr2_X);
        Assert.assertEquals(2, corr2_Y);
        Assert.assertEquals(3, corr3_X);
        Assert.assertEquals(3, corr3_Y);
        Assert.assertEquals(4, corr4_X);
        Assert.assertEquals(4, corr4_Y);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCoordinatesZeroXY() {
        // Given
        Coordinates coordinates1 = new Coordinates(0, 0);
        System.out.print("Initialization with 0 values... ");

        // When
        // -

        // Then
        // -
    }

    @Test
    public void testCoordinatesZeroValuesAllowed() {
        // Given
        Coordinates coordinates1 = new Coordinates(0, 1, 11, true);
        Coordinates coordinates2 = new Coordinates(1, 0, 11, true);
        System.out.print("Initialization with ALLOWED zero values... ");

        // When
        int corr1_X = coordinates1.getX();
        int corr1_Y = coordinates1.getY();
        int corr2_X = coordinates2.getX();
        int corr2_Y = coordinates2.getY();

        // Then
        Assert.assertEquals(0, corr1_X);
        Assert.assertEquals(1, corr1_Y);
        Assert.assertEquals(1, corr2_X);
        Assert.assertEquals(0, corr2_Y);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCoordinatesNegativeXY() {
        // Given
        Coordinates coordinates1 = new Coordinates(-1, -20);
        System.out.print("Initialization with negative values... ");

        // When
        // -

        // Then
        // -
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCoordinatesXAboveMax() {
        // Given
        Coordinates coordinates1 = new Coordinates(12, 11);
        System.out.print("Initialization with X exceeding max... ");

        // When
        // -

        // Then
        // -
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCoordinatesYAboveMax() {
        // Given
        Coordinates coordinates1 = new Coordinates(11, 12);
        System.out.print("Initialization with Y exceeding max... ");

        // When
        // -

        // Then
        // -
    }

    @Test
    public void testCoordinatesRaisedMaxValue() {
        // Given
        Coordinates coordinates1 = new Coordinates(14, 13, 14);
        Coordinates coordinates2 = new Coordinates(13, 14, 14);
        System.out.print("Initialization with raised MAX value... ");

        // When
        int corr1_X = coordinates1.getX();
        int corr1_Y = coordinates1.getY();
        int corr2_X = coordinates2.getX();
        int corr2_Y = coordinates2.getY();

        // Then
        Assert.assertEquals(14, corr1_X);
        Assert.assertEquals(13, corr1_Y);
        Assert.assertEquals(13, corr2_X);
        Assert.assertEquals(14, corr2_Y);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCoordinatesRaisedMaxValueXAboveMax() {
        // Given
        Coordinates coordinates1 = new Coordinates(15, 13, 14);
        System.out.print("Initialization with raised MAX value and X exceeding max... ");

        // When
        // -

        // Then
        // -
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCoordinatesRaisedMaxValueYAboveMax() {
        // Given
        Coordinates coordinates1 = new Coordinates(14, 15, 14);
        System.out.print("Initialization with raised MAX value and Y exceeding max... ");

        // When
        // -

        // Then
        // -
    }

    @Test
    public void testReturnNeighbourAxis0DirectionPos1() {
        // Given
        Coordinates coordinates = new Coordinates(5, 5);
        System.out.print("Return neighbour mechanic X+1... ");

        // When
        int axis = 0;
        int direction = 1;
        Coordinates neighbour = coordinates.returnNeighbour(axis, direction);

        // Then
        Assert.assertEquals(new Coordinates(6, 5), neighbour);
    }

    @Test
    public void testReturnNeighbourAxis0DirectionNeg1() {
        // Given
        Coordinates coordinates = new Coordinates(5, 5);
        System.out.print("Return neighbour mechanic X-1... ");

        // When
        int axis = 0;
        int direction = -1;
        Coordinates neighbour = coordinates.returnNeighbour(axis, direction);

        // Then
        Assert.assertEquals(new Coordinates(4, 5), neighbour);
    }

    @Test
    public void testReturnNeighbourAxis1DirectionNeg1() {
        // Given
        Coordinates coordinates = new Coordinates(5, 5);
        System.out.print("Return neighbour mechanic Y+1... ");

        // When
        int axis = 1;
        int direction = -1;
        Coordinates neighbour = coordinates.returnNeighbour(axis, direction);

        // Then
        Assert.assertEquals(new Coordinates(5, 4), neighbour);
    }

    @Test
    public void testReturnNeighbourAxis1DirectionPos1() {
        // Given
        Coordinates coordinates = new Coordinates(5, 5);
        System.out.print("Return neighbour mechanic Y-1... ");

        // When
        int axis = 1;
        int direction = 1;
        Coordinates neighbour = coordinates.returnNeighbour(axis, direction);

        // Then
        Assert.assertEquals(new Coordinates(5, 6), neighbour);
    }

    @Test
    public void testReturnNeighbourAxis0DirectionNeg1ExceedingMin() {
        // Given
        Coordinates coordinates = new Coordinates(1, 5);
        System.out.print("Return neighbour mechanic X-1 exceeding MIN... ");

        // When
        int axis = 0;
        int direction = -1;
        Coordinates neighbour = coordinates.returnNeighbour(axis, direction);

        // Then
        Assert.assertEquals(coordinates, neighbour);
    }

    @Test
    public void testReturnNeighbourAxis0DirectionPos1ExceedingMax() {
        // Given
        Coordinates coordinates = new Coordinates(10, 5);
        System.out.print("Return neighbour mechanic X+1 exceeding MAX... ");

        // When
        int axis = 0;
        int direction = 1;
        Coordinates neighbour = coordinates.returnNeighbour(axis, direction);

        // Then
        Assert.assertEquals(coordinates, neighbour);
    }

    @Test
    public void testReturnNeighbourAxis1DirectionNeg1ExceedingMin() {
        // Given
        Coordinates coordinates = new Coordinates(1, 1);
        System.out.print("Return neighbour mechanic Y-1 exceeding MIN... ");

        // When
        int axis = 0;
        int direction = -1;
        Coordinates neighbour = coordinates.returnNeighbour(axis, direction);

        // Then
        Assert.assertEquals(coordinates, neighbour);
    }

    @Test
    public void testReturnNeighbourAxis1DirectionPos1ExceedingMax() {
        // Given
        Coordinates coordinates = new Coordinates(10, 10);
        System.out.print("Return neighbour mechanic Y+1 exceeding MAX... ");

        // When
        int axis = 0;
        int direction = 1;
        Coordinates neighbour = coordinates.returnNeighbour(axis, direction);

        // Then
        Assert.assertEquals(coordinates, neighbour);
    }

    @Test
    public void testCoordinateToString() {
        // Given
        Coordinates coordinates = new Coordinates(5, 5);
        System.out.print("Coordinate translation into String... ");

        // When
        String stringRepresentation = coordinates.toString();

        // Then
        Assert.assertEquals("Coordinates (5, 5)", stringRepresentation);
    }
}
