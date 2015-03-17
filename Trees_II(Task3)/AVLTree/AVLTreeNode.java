

//Task3

/**
 * Implements a node suitable for building AVL tree structures.
 * 
 * @author Stephan Jamieson
 * @version 3/3/2015
 */
public class AVLTreeNode {

    private String key;
    private int height;
    private int keyValue;
    
    private AVLTreeNode left;
    private AVLTreeNode right;
    
    public final static AVLTreeNode EMPTY_NODE = new AVLTreeNode();
    
    private AVLTreeNode() { this.key=null; this.height=-1; this.left=null; this.right=null; }
    
    
    /**
     * Create an AVLTreeNode that contains the given key
     */
    public AVLTreeNode(String key) { this(null, key, null); }
    
    private AVLTreeNode(AVLTreeNode left, String key, AVLTreeNode right) {
        assert(key!=null);
        this.left=left;
        this.right=right;
        this.key=key;
        this.height=0;
        this.keyValue=setKeyValue(key);
    }
    
    /* Low level structural operations */  
    
    /**
     * Determine the nodes keyValue.
     */
    public void setKeyValue(int keyValue) {
        this.keyValue = keyValue; 
    }
    
    /**
     * Deletes specified node.
     */
    public void deleteNode(AVLTreeNode node){
        node.setLeft(null);
    }
    
    /**
     * Determine the nodes keyValue.
     */
    public Integer setKeyValue(String key) { 
        assert(key!=null);
        int tempKey=0;
        switch (key.toLowerCase().charAt(0)){
            case 'a': tempKey = 1;
                break;
            case 'b': tempKey = 2;
                break;
            case 'c': tempKey = 3;
                break;
            case 'd': tempKey = 4;
                break;
            case 'e': tempKey = 5;
                break;
            case 'f': tempKey = 6;
                break;
            case 'g': tempKey = 7;
                break;
            case 'h': tempKey = 8;
                break;
            case 'i': tempKey = 9;
                break;
            case 'j': tempKey = 10;
                break;
            case 'k': tempKey = 11;
                break;
            case 'l': tempKey = 12;
                break;
            case 'm': tempKey = 13;
                break;
            case 'n': tempKey = 14;
                break;
            case 'o': tempKey = 15;
                break;
            case 'p': tempKey = 16;
                break;
            case 'q': tempKey = 17;
                break;
            case 'r': tempKey = 18;
                break;
            case 's': tempKey = 19;
                break;
            case 't': tempKey = 20;
                break;
            case 'u': tempKey = 21;
                break;
            case 'v': tempKey = 22;
                break;
            case 'w': tempKey = 23;
                break;
            case 'x': tempKey = 24;
                break;
            case 'y': tempKey = 25;
                break;
            case 'z': tempKey = 26;
                break;
        }
        
        return tempKey;
    }
    
    /**
     * Obtain keyValue of this node.
     */
    public Integer getKeyValue() { 
        return this.keyValue; 
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
    public boolean hasKey() { return key!=null; }
        
    /**
     * Obtain the key stored in this node.
     */
    public String getKey() { return key; }
    
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
     * Reset the key stored in this node.
     */
    public void setKey(String key) { 
        assert(this!=EMPTY_NODE);
        this.key=key; 
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
        return this.getKeyValue()+"("+this.getBalanceFactor()+")" + "("+this.getKey()+")";
    }
    

}
