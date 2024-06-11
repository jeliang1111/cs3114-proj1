import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Stub for binary search tree class
 * We use generics here because we want this BST to be able to hold more than
 * just Rectangles (or KVPairs)
 * 
 * @author Justin Liang jeliang1111
 * @author Timothy Palamarchuk timka3
 * @version 2024-06-10
 * @param <T>
 *            the generic type; extends Comparable
 */
public class BST<T extends Comparable<T>> implements Iterable<BSTNode<T>> {
    /** The root. */
    private BSTNode<T> root;

    /** The size. */
    private int size;

    /**
     * Instantiates a new Binary Search Tree.
     */
    public BST() {
        root = null;
        size = 0;
    }


    /**
     * Size of the tree
     *
     * @return the tree size as an int
     */
    public int size() {
        return size;
    }


    /**
     * Insert a new value into the BST
     *
     * @param value
     *            the value to insert
     */
    public void insert(T value) {
        root = insertHelp(root, value);
    }


    /**
     * Insert a new node into the BST
     *
     * @param node
     *            the current node
     * @param value
     *            the value to insert
     */
    private BSTNode<T> insertHelp(BSTNode<T> node, T value) {
        if (node == null) {
            node = new BSTNode<T>(value);
            size++;
        }
        else {
            if (value.compareTo(node.getValue()) <= 0) {
                node.setLeft(insertHelp(node.getLeft(), value));
            }
            else {
                node.setRight(insertHelp(node.getRight(), value));
            }
        }
        return node;
    }

    /**
     * Remove a node from the BST
     *
     * @param node
     *            the current node to be removed
     */

    public BSTNode<T> remove(BSTNode<T> node) {
        return removeHelp(root, node.getValue(), new boolean[] {false});
    }

    /**
     * Remove a value from the BST helper function
     *
     * @param value
     *            the value to be removed
     * @param node the current node
     * @param removed flag to know if we removed and there are duplicates
     */
    private BSTNode<T> removeHelp(BSTNode<T> node, T value, boolean[] removed) {
        if (node == null) {
            System.out.print("Node is null");
            return null;
        }
        else {
            if (value.compareTo(node.getValue()) < 0) {
                node.setLeft(removeHelp(node.getLeft(), value, removed));
            }
            else if (value.compareTo(node.getValue()) > 0) {
                node.setRight(removeHelp(node.getRight()
                , value, removed));
            }
            else {
                // Only remove if not already removed
                if (!removed[0]) {
                    size--;
                    removed[0] = true; // Mark as removed
    
                    if (node.getLeft() == null) {
                        return node.getRight();
                    }
                    else if (node.getRight() == null) {
                        return node.getLeft();
                    }
                    else {
                        BSTNode<T> minNodeForRight = minValueNode(node.getRight());
                        node.setValue(minNodeForRight.getValue());
                        // Pass the same removed flag
                        node.setRight(removeHelp(node.getRight(), 
                        minNodeForRight.getValue(), removed));
                    }
                }
            }
        }
        return node;
    }
    
    /**
     * Find the minimum value node in the BST
     *
     * @param node
     *            the current node
     * @return the minimum value node
     */
    public BSTNode<T> minValueNode(BSTNode<T> node) {
        BSTNode<T> current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }


    /**
     * This is an auto-generated method stub for an iterator object because we
     * have implemented Iterable to traverse the BST (perhaps during the
     * intersections command)
     */
    @Override
    public Iterator<BSTNode<T>> iterator() {
        return new Iterator<BSTNode<T>>() {
            private Stack<BSTNode<T>> stack = new Stack<>();

            {
                if (root != null) {
                    stack.push(root);
                    pushFarthestLeft(root.getLeft());
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }


            @Override
            public BSTNode<T> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                BSTNode<T> node = stack.pop();
                if (node.getRight() != null) {
                    pushFarthestLeft(node.getRight());
                }
                return node;
            }


            private void pushFarthestLeft(BSTNode<T> node) {
                while (node != null) {
                    stack.push(node);
                    node = node.getLeft();
                }
            }
        };
    }

}
