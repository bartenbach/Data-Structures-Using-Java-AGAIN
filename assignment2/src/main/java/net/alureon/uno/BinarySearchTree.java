package net.alureon.uno;// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 *
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    /**
     * The tree root.
     */
    private BinaryNode<AnyType> root;

    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        root = insert(x, root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */
    public void remove(AnyType x) {
        root = remove(x, root);
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public AnyType findMin() throws UnderflowException {
        if (isEmpty())
            throw new UnderflowException();
        return findMin(root).element;
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item of null if empty.
     */
    public AnyType findMax() throws UnderflowException {
        if (isEmpty())
            throw new UnderflowException();
        return findMax(root).element;
    }

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return new BinaryNode<>(x, null, null);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return t;   // Item not found; do nothing

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) // Two children
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t != null)
            while (t.right != null)
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param t the node that roots the subtree.
     */
    private void printTree(BinaryNode<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    /**
     * Public call for printing the tree in descending order.  No parameters.  Returns nothing.
     */
    public void printTreeDescending() {
        Stack<AnyType> stack = new Stack<>();
        printTreeDesc(root, stack);
        while (!stack.empty()) {
            System.out.print(stack.pop().toString() + " ");
        }
    }

    /**
     * Private method to recursively get nodes in descending order using a stack.
     * @param t The node of the current iteration.
     * @param stack The stack to hold nodes in.
     */
    private void printTreeDesc(BinaryNode<AnyType> t, Stack<AnyType> stack) {
        if (t != null) {
            printTreeDesc(t.left, stack);
            stack.push(t.element) ;
            printTreeDesc(t.right, stack);
        }
    }

    /**
     * Returns the number of leaves in the binary tree.
     * @param t The node to check, usually if a recursive call, otherwise the root node.
     * @return The number of leaves in the supplied binary tree.
     */
    public int numLeaves(BinaryNode<AnyType> t) {
        if (t != null) {
            if (t.right == null && t.left == null) {
                return 1;
            } else {
                return numLeaves(t.left) + numLeaves(t.right);
            }
        }
        return 0;
    }

    /**
     * Returns the number of nodes in the tree with only one child.
     * @param t The Node to check (when recursive, otherwise the root node).
     * @return The number of nodes in the tree with only one child.
     */
    public int numOneChildNodes(BinaryNode<AnyType> t) {
        if (t != null) {
            if (t.right == null || t.left == null) {
                return 1 + numOneChildNodes(t.right) + numOneChildNodes(t.left);
            } else {
                return numOneChildNodes(t.right) + numOneChildNodes(t.left);
            }
        }
        return 0;
    }

    /**
     * Returns the number of two children nodes in a binary tree.
     * @param t The root node
     * @return the number of full nodes in the tree
     */
    public int numTwoChildrenNodes(BinaryNode<AnyType> t) {
        if (t != null) {
            if (t.right != null && t.left != null) {
                return 1;
            } else {
                return numTwoChildrenNodes(t.left) + numTwoChildrenNodes(t.right);
            }
        }
        return 0;
    }

    /**
     * Getter for the root of the binary tree.
     * @return The root of this tree object..
     */
    public BinaryNode<AnyType> getRoot() {
        return this.root;
    }

    /**
     * Internal method to compute height of a subtree.
     *
     * @param t the node that roots the subtree.
     */
    private int height(BinaryNode<AnyType> t) {
        if (t == null)
            return -1;
        else
            return 1 + Math.max(height(t.left), height(t.right));
    }


    /**
     * The public level order traversal function. Calls 'printLevel'
     */
    public void levelOrder() {
        int height = this.height(root);
        for (int i = 1; i < height; i++) {
            printLevel(root, i);
        }
    }

    /**
     * Prints a given level in the binary tree
     * @param t The current node
     * @param i the level to print
     */
    private void printLevel(BinaryNode<AnyType> t, int i) {
        if (t != null) {
            if (i == 1) {
                System.out.println(t.element);
            } else {
                printLevel(t.left, i-1);
                printLevel(t.right, i-1);
            }
        }
    }

    /**
     * The public wrapper for the recursive function 'printBet'
     * FIXME this cast is ugly and error-prone
     * @param i the starting point
     * @param j the ending point
     */
    public void printBetween(int i, int j) {
        BinaryNode<Integer> intRoot = (BinaryNode<Integer>) root;
        printBet(intRoot, i, j);
    }

    /**
     * Prints all nodes between i and j
     * @param t The root node
     * @param i Starting point
     * @param j Ending point
     */
    private void printBet(BinaryNode<Integer> t, int i, int j) {
        if (t != null) {
            if (t.element >= i && t.element <= j) {
                System.out.println(t.element);
                printBet(t.left, i, j);
                printBet(t.right, i, j);
            } else if (t.element < i) {
                printBet(t.left, i, j);
                printBet(t.right, i, j);
            }
            // if the element is not less than j, nothing will happen.  this should be less than O(N) in practice,
            // but i'm not sure if it counts as log(N)
        }
    }
}
