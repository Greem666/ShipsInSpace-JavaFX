package shipsinspace.common;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoordinatesTestSuite {
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
}
