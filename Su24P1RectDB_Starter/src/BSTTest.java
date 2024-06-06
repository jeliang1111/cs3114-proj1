/*
 * Useful Testing Links
 * https://web-cat.org/eclstats/junit-quickstart/
 * https://lti.cs.vt.edu/LTI_ruby/Books/CS3/html/mutationtesting.html
 */
//

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This class tests the methods of BST class
 *
 * @author CS Staff
 * @version 2024-05-22
 */
public class BSTTest extends student.TestCase {

    BST<String> bst;

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
     * @param m
     * @param line
     * @param substrs
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
     * Example 3: Test named incorrectly, does not work
     */
    public void BSTFailTest() {
        // Despite this test asserting that false is true, a contradiction, it
        // does not cause an error in JUnit, this is because the test case is
        // incorrectly named, not starting with "test___"
        assertTrue(false);
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
        BSTNode<String> node = new BSTNode<String>("hello");
        bst.remove(node);
        assertEquals(1, bst.size());
        node = new BSTNode<String>("world");
        bst.remove(node);
        assertEquals(0, bst.size());
    }


}
