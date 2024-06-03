
import student.TestCase;

/**
 * This class tests the KVPair class so that the member methods work properly
 * and that the expected behavior occurs.
 * 
 * @author CS Staff
 * 
 * @version 2024.1
 */
public class KVPairTest extends TestCase {
    private KVPair<String, Rectangle> pair;

    public void setUp() {
        pair = new KVPair<String, Rectangle>("key", new Rectangle(0, 0, 10,
            10));
    }


    /**
     * Sanity check for the KVPair class.
     */
    public void testSanity() {
        assertEquals("key", pair.getKey());
        assertEquals(0, pair.getValue().getxCoordinate());
        assertEquals(0, pair.getValue().getyCoordinate());
        assertEquals(10, pair.getValue().getWidth());
        assertEquals(10, pair.getValue().getHeight());
        String expected = "(key, 0, 0, 10, 10)";
        assertEquals(expected, pair.toString());
    }


    /**
     * Tests the compareTo method of the KVPair class.
     * Where the compareTo method is used to compare the key of the KVPair
     */
    public void testCompareTo() {
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("key2",
            new Rectangle(0, 0, 10, 10));
        KVPair<String, Rectangle> pair3 = new KVPair<String, Rectangle>("ke",
            new Rectangle(0, 0, 10, 10));
        assertEquals(0, pair.compareTo(pair));
        assertEquals(-1, pair.compareTo(pair2));
        assertEquals(1, pair.compareTo(pair3));
    }

}
