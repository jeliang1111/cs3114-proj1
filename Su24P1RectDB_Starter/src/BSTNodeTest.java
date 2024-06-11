import student.TestCase;

/**
 * This class tests the KVPair class so that the member methods work properly
 * and that the expected behavior occurs.
 * 
 * @author CS Staff
 * 
 * @version 2024-5-22
 */
public class BSTNodeTest extends TestCase {
    private BSTNode<KVPair<String, Rectangle>> node;

    /**
     * Sets up the test fixture.
     */
    public void setUp() {
        node = new BSTNode<KVPair<String, Rectangle>>(
            new KVPair<String, Rectangle>("A", new Rectangle(1, 2, 3, 4)));
    }


    /**
     * Sanity test to ensure the class is working properly.
     */
    public void testSanity() {
        assertNotNull(node);
        KVPair<String, Rectangle> pair = node.getValue();
        assertEquals("A", pair.getKey());
    }


    /**
     * Tests setting and getting children of the node.
     */
    public void testChildren() {
        assertNull(node.getLeft());
        assertNull(node.getRight());
        BSTNode<KVPair<String, Rectangle>> left =
            new BSTNode<KVPair<String, Rectangle>>(
                new KVPair<String, Rectangle>("B", new Rectangle(1, 2, 3, 4)));
        BSTNode<KVPair<String, Rectangle>> right =
            new BSTNode<KVPair<String, Rectangle>>(
                new KVPair<String, Rectangle>("C", new Rectangle(1, 2, 3, 4)));
        node.setLeft(left);
        node.setRight(right);
        assertEquals(left, node.getLeft());
        assertEquals(right, node.getRight());
    }

}
