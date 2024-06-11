/*
 * Useful Testing Links
 * https://web-cat.org/eclstats/junit-quickstart/
 * https://lti.cs.vt.edu/LTI_ruby/Books/CS3/html/mutationtesting.html
 */
//

import java.util.Iterator;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This class tests the methods of BST class
 *
 * @author CS Staff
 * @version 2024-05-22
 */
public class BSTTest extends student.TestCase {

    private BST<String> bst;

    /**
     * setUp the condition.
     */
    public void setUp() {
        // Nothing to setup here. May not be true for your tests
        bst = new BST<String>();
    }


    /**
     * This defines an assertFuzzyContains method that you could use to test
     * your code
     * 
     * @param m     message
     * @param line  line
     * @param substrs   substrings
     */
    public void assertFuzzyContains(String m, String line, String... substrs) {
        assertTrue(m, fuzzyContains(line, substrs));
    }


    /**
     * Example 1: Test tree not null
     */
    public void testBasic() {
        BST<String> theTree = new BST<String>();
        assertNotNull(theTree);
    }


    /**
     * Example 2: Test tree size in empty tree
     */
    public void testBST() {
        assertEquals("empty BST size should be zero", 0, bst.size());
    }

    /**
     * Example 4:
     */
    public void testFuzzy() {
        /*
         * this will not run until you implement some dump method for your BST
         * but you can assert that some text exists in an output of a dump
         * 
         * bst = new BST<String>();
         * bst.insert("hello");
         * 
         * String[] dumps = bst.dump().split("\n");
         * assertFuzzyContains("BST dump should have one real node", dumps[1],
         * "Node has depth", "Value (hello)");
         */
    }


    /**
     * Tests the basic insert method
     */
    public void testInsertandDelete() {
        bst.insert("hello");
        assertEquals(1, bst.size());
        bst.insert("world");
        assertEquals(2, bst.size());
        bst.insert("hello");
        bst.insert("helln");
        BSTNode<String> node = new BSTNode<String>("hello");
        bst.remove(node);
        assertEquals(3, bst.size());
        node = new BSTNode<String>("world");
        bst.remove(node);
        assertEquals(2, bst.size());
        node = new BSTNode<String>("helln");
        bst.remove(node);
        assertEquals(1, bst.size());
    }

    /**
     * Tests the basic insert method
     */
    public void testNullminValNode() {
        BSTNode<String> node = new BSTNode<String>("hello");

        assertEquals(node, bst.minValueNode(node));
        BSTNode<String> node2 = new BSTNode<String>("helln");
        node.setLeft(node2);
        assertEquals(node2, bst.minValueNode(node));
    }

    /**
     * Tests to ensure that the iterator works properly
     */
    public void testIterator() {
        bst.insert("hello");
        bst.insert("world");
        bst.insert("hello");
        bst.insert("helln");
        Iterator<BSTNode<String>> iter = bst.iterator();
        assertTrue(iter.hasNext());
        assertEquals("helln", iter.next().getValue());
        assertTrue(iter.hasNext());
        assertEquals("hello", iter.next().getValue());
        assertTrue(iter.hasNext());
        assertEquals("hello", iter.next().getValue());
        assertTrue(iter.hasNext());
        assertEquals("world", iter.next().getValue());
        assertFalse(iter.hasNext());

    }

}
