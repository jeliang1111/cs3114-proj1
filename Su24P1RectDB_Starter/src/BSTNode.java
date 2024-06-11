/**
 * Stub for bst node
 * We use generics here because we want the BST to be able to hold more than
 * just Rectangles (or KVPairs)
 * 
 * @author Justin Liang jeliang1111
 * @author Timothy Palamarchuk timka3
 * @version 2024-06-10
 * @param <T>
 *            the generic type; extends Comparable
 */
public class BSTNode<T extends Comparable<T>> {
    /** The left */
    private BSTNode<T> left;
    /** The right */
    private BSTNode<T> right;
    /** The value storing comparable rectangle node */
    private T value;

    /**
     * Instantiates a new node
     * 
     * @param value
     *            the value
     */
    public BSTNode(T value) {
        this.value = value;
    }


    /**
     * Gets the value
     * 
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the value
     * @param newValue the value
     */
    public void setValue(T newValue) {
        this.value = newValue;
    }


    /**
     * Gets the left node
     * 
     * @return the left node
     */
    public BSTNode<T> getLeft() {
        return left;
    }


    /**
     * Gets the right node
     * 
     * @return the right node
     */
    public BSTNode<T> getRight() {
        return right;
    }


    /**
     * Sets the left node
     * 
     * @param left
     *            the left node
     */
    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }


    /**
     * Sets the right node
     * 
     * @param right
     *            the right node
     */
    public void setRight(BSTNode<T> right) {
        this.right = right;
    }

    // It could be worth including a toString() method for BSTNode for easier
    // debugging!

}
