
import student.TestCase;

/**
 * This class tests the methods of Rectangle class,
 * ensuring that they work as they should.
 * 
 * @author Justin Liang jeliang1111
 * @author Timothy Palamarchuk timka3
 * @version 2024-06-11
 */
public class RectangleTest extends TestCase {
    private Rectangle rect;

    /**
     * Initializes a rectangle object to be used for the tests.
     */
    public void setUp() {
        rect = new Rectangle(0, 0, 10, 10);
    }


    /**
     * Tests the setup, sanity check.
     */
    public void testSanity() {
        assertEquals(0, rect.getxCoordinate());
        assertEquals(0, rect.getyCoordinate());
        assertEquals(10, rect.getWidth());
        assertEquals(10, rect.getHeight());
        assertFalse(rect.isInvalid());
    }


    /**
     * Tests the isInvalid method.
     */
    public void testIsInvalid() {
        rect = new Rectangle(-1, 0, 10, 10);
        assertTrue(rect.isInvalid());
        rect = new Rectangle(0, -1, 10, 10);
        assertTrue(rect.isInvalid());
        rect = new Rectangle(0, 0, -1, 10);
        assertTrue(rect.isInvalid());
        rect = new Rectangle(0, 0, 10, -1);
        assertTrue(rect.isInvalid());

        rect = new Rectangle(1020, 1020, 5, 4);
        assertTrue(rect.isInvalid());
        rect = new Rectangle(1020, 1020, 4, 5);
        assertTrue(rect.isInvalid());
    }


    /**
     * Tests the equals method.
     */
    public void testEquals() {
        Rectangle rect2 = new Rectangle(0, 0, 10, 10);
        assertTrue(rect.equals(rect2));
        rect2 = new Rectangle(0, 0, 5, 5);
        assertFalse(rect.equals(rect2));
        rect2 = new Rectangle(0, 0, 10, 5);
        assertFalse(rect.equals(rect2));
        rect2 = new Rectangle(0, 0, 5, 10);
        assertFalse(rect.equals(rect2));
        rect2 = new Rectangle(0, 0, 10, 10);
        assertTrue(rect.equals(rect2));
    }

}
