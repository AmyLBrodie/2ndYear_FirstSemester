
import java.util.ArrayList;
import java.util.List;



//Task4

/**
 * Implements a node suitable for building AVL tree structures.
 * 
 * @author Stephan Jamieson
 * @version 3/3/2015
 */
public class AVLTreeNode {

    private String key;
    private int height;
    private char keyValue;
    private int numValues;
    
    private AVLTreeNode left;
    private AVLTreeNode right;
    
    private List<String> values = new ArrayList<String>();
    
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
        this.numValues = 0;
        this.keyValue=setKeyValue(key);
        values.add(key);
    }
    
    /* Low level structural operations */  
    
    /**
     * Determine the nodes keyValue.
     */
    public char setKeyValue(String key) {
        return key.toUpperCase().charAt(0); 
    }
    
    /**
     * Determine the nodes keyValue.
     */
    public void setKeyValue(char keyValue) {
        this.keyValue =  keyValue; 
    }
    
    /**
     * Get the arraylist of values in specified node.
     */
    public List<String> getList() {
        return this.values; 
    }
    
    /**
     * Obtain the values stored in this node.
     */
    public String getValue() { 
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<this.getList().size(); i++){
            builder.append("\n"+this.getList().get(i));
        }
        return builder.toString(); 
    }
    
     /**
     * Obtain the values stored in this node for the find method.
     */
    public String getFindValue() { 
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        for (int i=0; i<this.getList().size(); i++){
            builder.append(this.getList().get(i));
            if (i != this.getList().size()-1){
                builder.append(", ");
            }
        }
        builder.append(")");
        return builder.toString(); 
    
    }
    
    /**
     * Add values to the arraylist of values in specified node.
     */
    public void addToList(String key) {
        this.values.add(key);
    }
    
    /**
     * Delete values from the arraylist of values in specified node.
     */
    public void deleteFromList(String key) {
        this.values.remove(key);
    }
    
    /**
     * Deletes specified node.
     */
    public void deleteNode(AVLTreeNode node){
        node.setLeft(null);
    }
    
    
    
    /**
     * Obtain keyValue of this node.
     */
    public char getKeyValue() { 
        return this.keyValue; 
    }
    
    /**
     * Obtain number of values in this node.
     */
    public Integer getNumValues() { 
        return this.numValues; 
    }
    
    /**
     * Set the number of values in the arraylist of this node.
     */
    public void setNumValues(int values) { 
        this.numValues = values; 
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
        Integer largest = this.values.get(0).length();
        for (int i=0; i<this.values.size(); i++){
            largest = Math.max(largest,this.values.get(i).length());
        }
        if (this.hasLeft()) 
            largest = Math.max(largest, this.getLeft().getLargest());
        if (this.hasRight()) 
            largest = Math.max(largest, this.getRight().getLargest());
        
        largest = Math.max(largest, 6);
        
        return largest;
    }

            
    /**
     * Obtain a String representation of this node.
     */
    public String toString() {
        if (this.values.size()>0){
            return "("+this.getKeyValue()+")"+"("+this.getNumValues()+")" + this.getValue();
        }
        else{
            return "";
        }
    }

}
