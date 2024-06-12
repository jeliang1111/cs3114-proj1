import org.w3c.dom.css.Rect;

import student.TestCase;

/**
 * This class tests the KVPair class so that the member methods work properly
 * and that the expected behavior occurs.
 * 
 * @author Justin Liang jeliang1111
 * @author Timothy Palamarchuk timka3
 * @version 2024-06-11
 */

public class DatabaseTest extends TestCase {
    private Database db;

    /**
     * Sets up the test fixture.
     */
    public void setUp() {
        db = new Database();
    }


    /**
     * Testing remove function by name
     */
    public void testRemovebyName() {
        Rectangle rect = new Rectangle(0, 0, 10, 10);
        KVPair<String, Rectangle> pair = new KVPair<String, Rectangle>("A",
            rect);
        db.insert(pair);
        db.remove("A");
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle removed: (A, 0, 0, 10, 10)"));
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("B",
            rect);
        db.insert(pair2);
        db.remove("B");
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle removed: (B, 0, 0, 10, 10)"));

    }


    /**
     * Testing remove function by name not found, should not be able to remove
     */
    public void testRemovebyNameNotFound() {
        db.remove("A");
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle not found: A"));
    }


    /**
     * Testing remove function by inputting an invalid rectangle, should not be
     * able to remove
     */
    public void testRemovebyRectangleInvalid() {
        db.remove(10, 10, -5, -5);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle rejected: (10, 10, -5, -5)"));
        db.remove(10, 10, -5, 5);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle rejected: (10, 10, -5, 5)"));
        db.remove(10, 10, 5, -5);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle rejected: (10, 10, 5, -5)"));
        db.remove(10, 10, 5, 5);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle not found: (10, 10, 5, 5)"));
    }


    /**
     * Testing remove function by rectangle
     */
    public void testRemovebyRectangle() {
        Rectangle rect = new Rectangle(0, 0, 10, 10);
        KVPair<String, Rectangle> pair = new KVPair<String, Rectangle>("A",
            rect);
        db.insert(pair);
        db.remove(0, 0, 10, 10);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle removed: (A, 0, 0, 10, 10)"));
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("B",
            rect);
        db.insert(pair2);
        db.remove(0, 0, 10, 10);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle removed: (B, 0, 0, 10, 10)"));
        db.remove(0, 0, 2, 2);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle not found: (0, 0, 2, 2)"));

        // check invalid rectangle removes
        db.remove(10, 10, -5, 5);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle rejected: (10, 10, -5, 5)"));
        db.remove(10, 10, 5, -5);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle rejected: (10, 10, 5, -5)"));
        db.remove(-2, 2, 5, 5);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle rejected: (-2, 2, 5, 5)"));
        db.remove(2, -2, 5, 5);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle rejected: (2, -2, 5, 5)"));
        db.remove(0, 0, 1026, 4);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle rejected: (0, 0, 1026, 4)"));
        db.remove(0, 0, 4, 1026);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle rejected: (0, 0, 4, 1026)"));
        db.remove(10, 10, -5, -5);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle rejected: (10, 10, -5, -5)"));
        db.remove(-2, -2, 5, 5);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle rejected: (-2, -2, 5, 5)"));
        db.remove(-2, -2, -4, -4);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle rejected: (-2, -2, -4, -4)"));

    }


    /**
     * Testing remove function with no rectangles in each search
     */
    public void testRegionSearch() {
        db.regionsearch(10, 10, -5, -5);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle rejected: (10, 10, -5, -5)"));
        db.regionsearch(10, 10, -5, 5);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle rejected: (10, 10, -5, 5)"));
        db.regionsearch(10, 10, 5, -5);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle rejected: (10, 10, 5, -5)"));
        Rectangle rect = new Rectangle(3, 3, 2, 2);
        KVPair<String, Rectangle> pair = new KVPair<String, Rectangle>("A",
            rect);
        db.insert(pair);
        db.regionsearch(1, 1, 1, 1);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangles intersecting region (1, 1, 1, 1):"));
        db.regionsearch(3, 0, 1, 1);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangles intersecting region (3, 0, 1, 1):"));
        db.regionsearch(6, 4, 1, 1);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangles intersecting region (6, 4, 1, 1):"));
        db.regionsearch(4, 6, 1, 1);
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangles intersecting region (4, 6, 1, 1):"));

        db.regionsearch(3, 3, 2, 2);
        assertTrue(fuzzyContains(systemOut().getHistory(), "(3, 3, 2, 2)"));
    }


    /**
     * Testing intersection function, initially have no intersections, then
     * check with an intersection
     */
    public void testIntersection() {
        Rectangle rect = new Rectangle(2, 2, 1, 1);
        KVPair<String, Rectangle> pair = new KVPair<String, Rectangle>("A",
            rect);
        db.insert(pair);
        Rectangle rect2 = new Rectangle(0, 2, 1, 1);
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("B",
            rect2);
        db.insert(pair2);
        Rectangle rect3 = new Rectangle(2, 0, 1, 1);
        KVPair<String, Rectangle> pair3 = new KVPair<String, Rectangle>("C",
            rect3);
        db.insert(pair3);
        Rectangle rect4 = new Rectangle(4, 2, 1, 1);
        KVPair<String, Rectangle> pair4 = new KVPair<String, Rectangle>("D",
            rect4);
        db.insert(pair4);
        Rectangle rect5 = new Rectangle(2, 4, 1, 1);
        KVPair<String, Rectangle> pair5 = new KVPair<String, Rectangle>("E",
            rect5);
        db.insert(pair5);
        db.intersections();
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Intersection pairs:"));
        assertFalse(fuzzyContains(systemOut().getHistory(),
            "(A, 2, 2, 1, 1) | (B, 0, 2, 1, 1)"));

        Rectangle rect6 = new Rectangle(2, 2, 1, 1);
        KVPair<String, Rectangle> pair6 = new KVPair<String, Rectangle>("F",
            rect6);
        db.insert(pair6);
        db.intersections();
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "(A, 2, 2, 1, 1) | (F, 2, 2, 1, 1)"));
        db.remove("F");
        db.remove("E");
        db.remove("D");
        db.remove("C");
        db.remove("B");

        Rectangle rect7 = new Rectangle(0, 0, 3, 3);
        KVPair<String, Rectangle> pair7 = new KVPair<String, Rectangle>("G",
            rect7);
        db.insert(pair7);
        db.intersections();
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "(A, 2, 2, 1, 1) | (G, 0, 0, 3, 3)"));

    }


    /**
     * Testing search function, initially have no rectangles, then search for a
     * rectangle that does not exist, then search for a rectangle that does
     * exist
     */
    public void testNameSearch() {
        Rectangle rect = new Rectangle(2, 2, 1, 1);
        KVPair<String, Rectangle> pair = new KVPair<String, Rectangle>("A",
            rect);
        db.insert(pair);
        db.search("B");
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Rectangle not found: B"));
        db.search("A");
        assertTrue(fuzzyContains(systemOut().getHistory(), "(A, 2, 2, 1, 1)"));
        Rectangle rect2 = new Rectangle(0, 2, 1, 1);
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("A",
            rect2);
        db.insert(pair2);
        db.search("A");
        assertTrue(fuzzyContains(systemOut().getHistory(), "(A, 0, 2, 1, 1)"));

    }


    /**
     * Testing dump function, initially have no rectangles, then dump with
     * rectangles
     */
    public void testDump() {
        db.dump();
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Node has depth 0, Value (null)"));
        Rectangle rect = new Rectangle(2, 2, 1, 1);
        KVPair<String, Rectangle> pair = new KVPair<String, Rectangle>("A",
            rect);
        db.insert(pair);
        Rectangle rect2 = new Rectangle(0, 2, 1, 1);
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("B",
            rect2);
        db.insert(pair2);
        db.dump();
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Node has depth 0, Value (A, 2, 2, 1, 1)"));
        assertTrue(fuzzyContains(systemOut().getHistory(),
            "Node has depth 0, Value (B, 0, 2, 1, 1)"));
    }
}
