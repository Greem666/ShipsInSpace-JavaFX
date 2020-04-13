package shipsinspace.controller.ships.attackTypes;

import org.junit.*;
import shipsinspace.common.Coordinates;

public class AttackTestSuite {
    @BeforeClass
    public static void beforeAllTests() {
        System.out.println("Starting Attack objects test suite...");
    }
    @AfterClass
    public static void afterAllTests() {
        System.out.println("Attack objects test suite COMPLETE.");
    }
    @Before
    public void beforeEachTest() {
        System.out.print("Now testing: ");
    }
    @After
    public void afterEachTest() {
        System.out.println("Test complete.");
    }

    @Test
    public void testStandardAttack() {
        // Given
        StandardAttack standardAttack = new StandardAttack();
        System.out.print("Standard attack... ");

        // When
        Coordinates coordinates1 = new Coordinates(1, 1);
        Effect effect1 = standardAttack.attack(coordinates1);

        Coordinates coordinates2 = new Coordinates(2, 2);
        Effect effect2 = standardAttack.attack(coordinates2);

        Coordinates coordinates3 = new Coordinates(3, 3);
        Effect effect3 = standardAttack.attack(coordinates3);

        Coordinates coordinates4 = new Coordinates(4, 4);
        Effect effect4 = standardAttack.attack(coordinates4);

        // Then
        Assert.assertEquals(coordinates1, effect1.getCoordinates());
        Assert.assertEquals("destroy", effect1.getAction());
        Assert.assertEquals(coordinates2, effect2.getCoordinates());
        Assert.assertEquals("destroy", effect2.getAction());
        Assert.assertEquals(coordinates3, effect3.getCoordinates());
        Assert.assertEquals("destroy", effect3.getAction());
        Assert.assertEquals(coordinates4, effect4.getCoordinates());
        Assert.assertEquals("destroy", effect4.getAction());
    }


}
