import java.util.Iterator;
import java.util.Stack;

/**
 * Stub for binary search tree class
 * We use generics here because we want this BST to be able to hold more than
 * just Rectangles (or KVPairs)
 * 
 * @author {Your Name Here}
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
     *           the current node
     * @param value
     *            the value to insert
     */
    private BSTNode<T> insertHelp(BSTNode<T> node, T value) {
        if (node == null) {
            node = new BSTNode<T>(value);
            size++;
        } else {
            if (value.compareTo(node.getValue()) <= 0) {
                node.setLeft(insertHelp(node.getLeft(), value));
            } else {
                node.setRight(insertHelp(node.getRight(), value));
            }
        }
        return node;
    }

    public BSTNode<T> remove(BSTNode<T> node) {
        
        return removeHelp(root, node.getValue());

    }

    private BSTNode<T> removeHelp(BSTNode<T> node, T value) {
        if (node == null) {
        	System.out.print("Node is null");
            return null;
        } else {
            if (value.compareTo(node.getValue()) < 0) {
                node.setLeft(removeHelp(node.getLeft(), value));
            } else if (value.compareTo(node.getValue()) > 0) {
                node.setRight(removeHelp(node.getRight(), value));
            } else {
                size--;
                if (node.getLeft() == null) {
                    return node.getRight();
                } else if (node.getRight() == null) {
                    return node.getLeft();
                } else {
                    BSTNode<T> minNodeForRight = minValueNode(node.getRight());
                    node.setValue(minNodeForRight.getValue());
                    node.setRight(removeHelp(node.getRight(), minNodeForRight.getValue()));
                }
            }
        }
        return node;
    }

    private BSTNode<T> minValueNode(BSTNode<T> node) {
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
        return new Iterator<BSTNode<T>>(){
            @Override
            public boolean hasNext() {
                return stack.size() > 0;
            }

            @Override
            public BSTNode<T> next() {
                if(!hasNext()) {
                    return null;
                }
                BSTNode<T> node = stack.pop();
                pushFarthestLeft(node.getRight());
                return node;
            }
            private Stack<BSTNode<T>> stack = new Stack<BSTNode<T>>() {
                {
                    pushFarthestLeft(root);
                }
            };

            private void pushFarthestLeft(BSTNode<T> node) {
                while (node != null) {
                    stack.push(node.getLeft());

                }
            }

        };
        }
        
    }

