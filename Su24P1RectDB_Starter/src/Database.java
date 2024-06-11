import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the BST. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * BST method after some preparation.
 * 
 * @author CS Staff
 * 
 * @version 2024-05-22
 */
public class Database {

    // this is the BST object that we are using a
    // string for the name of the rectangle and then
    // a rectangle object, these are stored in a BSTNode,
    // see the Rectangle class for more information
    private BST<KVPair<String, Rectangle>> tree;

    // This is an Iterator object over the BST to loop through it from outside
    // the class.
    // You will need to define an extra Iterator for the intersections method.
    private Iterator<KVPair<String, Rectangle>> itr1;

    /**
     * The constructor for this class initializes a BST object
     * with a KVPair of Strings and Rectangles
     */
    public Database() {
        tree = new BST<KVPair<String, Rectangle>>();
    }


    /**
     * Inserts the KVPair in the BST if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * add the KVPair specified into the sorted BST appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Rectangle> pair) {
        // Delegates the decision mostly to BST, only
        // writing the correct message to the console from
        // that
        if (pair.getValue().getxCoordinate() < 0 || pair.getValue()
            .getyCoordinate() < 0) {
            // error
            System.out.println("Rectangle rejected: (" + pair.getKey() + ", "
                + pair.getValue().toString() + ")");
                return;
        }
        if (pair.getValue().getxCoordinate() + pair.getValue().getWidth() > 1024
            || pair.getValue().getyCoordinate() + pair.getValue()
                .getHeight() > 1024) {
            // error
            System.out.println("Rectangle rejected: (" + pair.getKey() + ", "
                + pair.getValue().toString() + ")");
                return;
        }
        if(pair.getValue().getWidth() <= 0 || pair.getValue().getHeight() <= 0) {
            // error
            System.out.println("Rectangle rejected: (" + pair.getKey() + ", "
                + pair.getValue().toString() + ")");
                return;
        }
        // check if the key has invalid characters
        // else add to tree
        // The name must begin with a letter, and may contain letters, digits,
        // and underscore characters
        if (pair.getKey().matches("^[a-zA-Z][a-zA-Z0-9_]*$")) {
            tree.insert(pair);
        }
        else {
            System.out.println("Rectangle rejected: (" + pair.getKey() + ", "
                + pair.getValue().toString() + ")");
                return;
        }

    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {
        Iterator<BSTNode<KVPair<String, Rectangle>>> itr = tree.iterator();
        while (itr.hasNext()) {
            BSTNode<KVPair<String, Rectangle>> node = itr.next();
            if (node.getValue().getKey().equals(name)) {
                tree.remove(node);
                System.out.println("Rectangle removed: (" + name + ", " + node
                    .getValue().getValue().toString() + ")");
                return;
            }
        }
        System.out.println("Rectangle not found: (" + name +")");
    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public void remove(int x, int y, int w, int h) {
        Iterator<BSTNode<KVPair<String, Rectangle>>> itr = tree.iterator();
        while (itr.hasNext()) {
            BSTNode<KVPair<String, Rectangle>> node = itr.next();
            if (node.getValue().getValue().getxCoordinate() == x && node
                .getValue().getValue().getyCoordinate() == y && node.getValue()
                    .getValue().getWidth() == w && node.getValue().getValue()
                        .getHeight() == h) {
                tree.remove(node);
                System.out.println("Rectangle removed: (" + node.getValue()
                    .getKey() + ", " + node.getValue().getValue().toString()
                    + ")");
                return;
            }
        }
        System.out.println("Rectangle rejected: (" + x + ", " + y + ", " + w
            + ", " + h + ")");

    }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region.
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {
        if ( x + w <= x || y + h <= y) {
            System.out.println("Rectangle rejected: (" + x + ", " + y + ", " + w
                + ", " + h + ")");
            return;
        }
        System.out.println("Rectangles intersecting region (" + x + ", " + y
            + ", " + w + ", " + h + "):");
        Iterator<BSTNode<KVPair<String, Rectangle>>> itr = tree.iterator();
        while (itr.hasNext()) {
            BSTNode<KVPair<String, Rectangle>> node = itr.next();
            if (node.getValue().getValue().getxCoordinate() + node.getValue()
                .getValue().getWidth() < x || node.getValue().getValue()
                    .getxCoordinate() > x + w || node.getValue().getValue()
                        .getyCoordinate() + node.getValue().getValue()
                            .getHeight() < y || node.getValue().getValue()
                                .getyCoordinate() > y + h) {
                continue;
            }
            else {
                System.out.println("(" + node.getValue().getKey() + ", " + node
                    .getValue().getValue().toString() + ")");
            }
        }
    }


    /**
     * Prints out all the rectangles that intersect each other. Note that
     * it is better not to implement an intersections method in the BST class
     * as the BST needs to be agnostic about the fact that it is storing
     * Rectangles.
     */
    public void intersections() {
        System.out.println("Intersection pairs:");
        Iterator<BSTNode<KVPair<String, Rectangle>>> itr = tree.iterator();
        while (itr.hasNext()) {
            BSTNode<KVPair<String, Rectangle>> node = itr.next();
            Iterator<BSTNode<KVPair<String, Rectangle>>> itr2 = tree.iterator();
            while (itr2.hasNext()) {
                BSTNode<KVPair<String, Rectangle>> node2 = itr2.next();
                if (node2 == node) {
                    continue;
                }
                if (node.getValue().getValue().getxCoordinate() + node
                    .getValue().getValue().getWidth() < node2.getValue()
                        .getValue().getxCoordinate() || node2.getValue()
                            .getValue().getxCoordinate() + node2.getValue()
                                .getValue().getWidth() < node.getValue()
                                    .getValue().getxCoordinate() || node
                                        .getValue().getValue().getyCoordinate()
                                        + node.getValue().getValue()
                                            .getHeight() < node2.getValue()
                                                .getValue().getyCoordinate()
                    || node2.getValue().getValue().getyCoordinate() + node2
                        .getValue().getValue().getHeight() < node.getValue()
                            .getValue().getyCoordinate()) {
                    continue;
                }
                else {
                    System.out.println("(" + node.getValue().getKey() + ", "
                        + node.getValue().getValue().toString() + ") | ("
                        + node2.getValue().getKey() + ", " + node2.getValue()
                            .getValue().toString() + ")");
                }
            }
        }

    }


    /**
     * Prints out all the rectangles with the specified name in the BST.
     * This method will delegate the searching to the BST class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        Iterator<BSTNode<KVPair<String, Rectangle>>> itr = tree.iterator();
        Boolean firstFlag = false;
        while (itr.hasNext()) {
            BSTNode<KVPair<String, Rectangle>> node = itr.next();
            if (node.getValue().getKey().equals(name)) {
                if (!firstFlag) {
                    System.out.println("Rectangles found matching \"" + name
                        + "\": ");
                    firstFlag = true;
                }
                System.out.println("(" + name + ", " + node.getValue()
                    .getValue().toString() + ")");
            }
        }
        if (!firstFlag) {
            System.out.println("Rectangle not found: (" + name + ")");
        }

    }


    /**
     * Prints out a dump of the BST which includes information about the
     * size of the BST and shows all of the contents of the BST. This
     * will all be delegated to the BST.
     */
    public void dump() {
        System.out.println("BST dump:");
        Iterator<BSTNode<KVPair<String, Rectangle>>> itr = tree.iterator();
        if (!itr.hasNext()) {
            System.out.println("Node has depth 0, Value (null)");
        }
        int depth = 0;
        while (itr.hasNext()) {
            BSTNode<KVPair<String, Rectangle>> node = itr.next();
            System.out.println("Node has depth " + depth + ", Value (" + node
                .getValue().getKey() + ", " + node.getValue().getValue()
                    .toString() + ")");
        }
        System.out.println("BST size is: " + tree.size());

    }

}
