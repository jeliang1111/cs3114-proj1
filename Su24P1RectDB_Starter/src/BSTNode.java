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

    // It could be worth including a toString() method for BSTNode for easier
    // debugging!

}
