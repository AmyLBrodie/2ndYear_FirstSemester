//Task4

import java.util.*;

/**
 * Implements a node suitable for building AVL tree structures.
 * 
 * @author Stephan Jamieson
 * @version 3/3/2015
 */
public class AVLTreeNode {

    int height, numValues = 0;
    char keyValue;
    HashMap<Character, List<String>> nodes = new HashMap<Character, List<String>>();
    List values = new ArrayList<String>();
    
    AVLTreeNode left;
    AVLTreeNode right;
    
    public final static AVLTreeNode EMPTY_NODE = new AVLTreeNode();
    
    private AVLTreeNode() { this.values=null; this.height=-1; this.left=null; this.right=null; }
    
    
    /**
     * Create an AVLTreeNode that contains the given key
     */
    public AVLTreeNode(String key) { this(null, key, null); }
    
    private AVLTreeNode(AVLTreeNode left, String key, AVLTreeNode right) {
        assert(key!=null);
        this.left=left;
        this.right=right;
        this.values.add(key);
        this.height=0;
        this.keyValue=key.toUpperCase().charAt(0);
        this.numValues=values.size();
    }
    
    /* Low level structural operations */  
    
    
    /**
     * Obtain keyValue of this node.
     */
    public Character getKeyValue() { 
        return this.keyValue; 
    }
    
    /**
     * Obtain number of values in this node.
     */
    public Integer getNumValues() { 
        return this.numValues; 
    }
    
    /**
     * Determine whether this node has a left branch.
     */
    public boolean hasLeft() { return left!=null; }
    /**
     * Determine whether this node has a right branch.
     */
    public boolean hasRight() { return right!=null; }
    
    /** 
     * Determine whether this node has a key.
     */
    public boolean hasValue() { return !values.isEmpty(); }
        
    /**
     * Obtain the key stored in this node.
     */
    public String getValue() { 
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<values.size(); i++){
            builder.append("\n"+values.get(i));
        }
        return builder.toString(); 
    
    }
    
    /**
     * Obtain the height value stored at this node. (Requirs that ka
     */
    public int getHeight() {
        return this.height;
    }
    
    /**
     * Obtain the balance factor for this node.
     */
    public int getBalanceFactor() { 
        int left = (this.hasLeft() ? this.getLeft().getHeight() : 0);
        int right = (this.hasRight() ? this.getRight().getHeight() : 0);
        return left-right;
    }

        
        /**
     * Obtain this node's left branch. Requires that <code>this.hasLeft()</code>.
     */
    public AVLTreeNode getLeft() { 
        return this.left; 
    }
    /**
     * Obtain this node's right branch. Requires that <code>this.hasRight()</code>.
     */
    public AVLTreeNode getRight() { 
        return this.right; 
    }
    
    /**
     * Set the height stored in this node.
     */
    public void setHeight(int height) { 
        assert(this!=EMPTY_NODE);
        this.height=height; 
    }
    
    
    
    /**
     * Set this node's left branch.
     */
    public void setLeft(AVLTreeNode tree) {
        assert(this!=EMPTY_NODE);
        this.left = tree;
    }
    
    /**
     * Set this node's right branch.
     */
    public void setRight(AVLTreeNode tree) {
        assert(this!=EMPTY_NODE);
        this.right = tree;
    }
    
    /**
     * Obtain the longest node label for nodes stored in this tree structure.
     */
    public Integer getLargest() {
        Integer largest = this.toString().length();
        if (this.hasLeft()) 
            largest = Math.max(largest, this.getLeft().getLargest());
        if (this.hasRight()) 
            largest = Math.max(largest, this.getRight().getLargest());
        
        return largest;
    }

            
    /**
     * Obtain a String representation of this node.
     */
    public String toString() {
        return "("+this.getKeyValue()+")"+"("+this.getNumValues()+")" + this.getValue();
    }
    

}
