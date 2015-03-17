

// Task4

import java.io.PrintStream;
/**
 * Implementation of an AVL Tree
 * 
 * @author Stephan Jamieson
 * @version 3/3/2015
 */
public class AVLTree {

    private AVLTreeNode root;

    /**
     * Create an empty AVL tree
     */
    public AVLTree() {
        root = null;
    }
    
    /**
     * Insert the given key into the tree.
     */
    public void insert(String key) {
        root = TreeUtils.insert(root, key); 
    }
    
    /**
     * Delete the given key from the tree.
     */
    public void delete(String key) {
        root = TreeUtils.delete(root, key); 
    }

    /**
     * Use the given PrintStream object to output a textual representation of this tree.
     */
    public void print(PrintStream printStream) {
        SimpleTreeWriter writer = new SimpleTreeWriterImpl(printStream);
        writer.write(this.root);
    }
    
    /**
     * Determine whether the tree contains the given key.
     */
    public boolean contains(String key) {
        if (root==null) {
            return false;
        }
        else {
            return TreeUtils.contains(root, key);
        }
    }
    
    /**
     * Finds the specified key in the tree.
     */
    public String find(String key) {
        if (root==null) {
            return "No entry found";
        }
        else {
            return TreeUtils.find(root, key);
        }
    }

}
