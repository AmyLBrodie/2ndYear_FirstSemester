

// Task3

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Utility procedures for binary tree structures.
 * 
 * @author Stephan Jamieson
 * @version 25/2/2015
 */
public class TreeUtils {
   
        
    /**
     * Obtain the height value of the given node.
     * @return 0 if <code>node==null</code>, otherwise <code>node.getHeight()</code>.
     */
    public static int height(AVLTreeNode node) {
        if (node==null) {
            return 0;
        }
        else {
            return node.getHeight();
        }
    }

    /** 
     * Determine whether the given tree structure contains the given key.
     */
    public static boolean contains(AVLTreeNode node, String key) {
        boolean flag;
        if (node == null){
            flag = false;
        }
        else if(node.getKey().equals(key)){
            flag = true;
        }
        else if(node.getKey().charAt(0) < key.charAt(0)){
            flag = contains(node.getRight(),key);
        }
        else{
            flag = contains(node.getLeft(),key);
        }
        return flag;
    } 
    
    /**
     * Recursive implementation of insert on an AVLTreeNode structure.
     */
    public static AVLTreeNode insert(AVLTreeNode node, String key) {
        if (node == null){
            node = new AVLTreeNode(key);
        }
        else if (node.getKey().equals(key)){
            
        }
        else if (node.getKey().charAt(0)== key.charAt(0)){
            node.setKey(key);
        }
        else if (node.getKey().charAt(0)>key.charAt(0)){
            node.setLeft(insert(node.getLeft(),key));
            if (node.getBalanceFactor() > 1){
                node = rebalanceLeft(node,key);
            }
        }
        else{
            node.setRight(insert(node.getRight(),key));
            if (node.getBalanceFactor() < -1){
                node = rebalanceRight(node,key);
            }
        }
        if (node.hasLeft() && node.hasRight()){
            node.setHeight(Math.max(height(node.getLeft()), height(node.getRight()))+1);
        }
        else if (node.hasLeft()){
            node.setHeight(height(node.getLeft())+1);
        }
        else if (node.hasRight()){
            node.setHeight(height(node.getRight())+1);
        } 
        else{
            node.setHeight(1);
        }
        return node;
    }
    
    /**
     * Recursive implementation of delete on an AVLTreeNode structure.
     */
    public static AVLTreeNode delete(AVLTreeNode node, String key) {
        // checks if current node is empty
        if (node == null){
            System.out.println("Error: The value could not be found in the tree");
        }
        // checks if current node is greater than the key to be deleted
        else if(node.getKey().charAt(0)>key.charAt(0)){
            node.setLeft(delete(node.getLeft(),key)); // moves to left child of node
        }
        // checks if current node is less than key to be deleted
        else if(node.getKey().charAt(0)<key.charAt(0)){
            node.setRight(delete(node.getRight(),key)); // moves to right child of node
        }
        // checks key at node is equal to key to be deleted
        else if(!node.getKey().equals(key)){
            System.out.println("Error: The value could not be found and deleted from the tree");
        }
        // if key at node is key to be deleted
        else {
            // checks if node has no children
            if (!node.hasRight() && !node.hasLeft()){
                node = null; // deletes node
            }
            // checks if node has a right child but no left child
            else if(node.hasRight() && !node.hasLeft()){
                node = node.getRight(); // sets node equal to its right child
                node.right = null; // removes right child of node
            }
            // checks if node has a left child but no right child
            else if(!node.hasRight() && node.hasLeft()){
                node = node.getLeft(); // sets node equal to its left child
                node.left = null; // removes left child of node
            }
            // node has two children
            else{
                AVLTreeNode successor = successor(node); // finds nodes successor
                node.setKey(successor.getKey()); // sets nodes key equal to successors key
                node.setKeyValue(successor.getKey()); // sets nodes keyValue equal to its successors keyValue
                // checks if successor has left child
                if (successor.hasLeft() && node.hasLeft()){
                    node.left = insert(node.getLeft(),successor.getLeft().getKey());
                }
                else{
                    node.setLeft(successor.getLeft());
                }
                // checks if successor has right child
                if (successor.hasRight()){
                    node.setRight(successor.getRight()); // sets right child of node to right child of successor
                }
                else{
                    node.setRight(null); // deletes right child of node
                }
            }
        }
        // recursively sets the height of each node (if adjustment needed)
        setHeightsDelete(node);
        // checks if rebalancing is needed
        if (node!= null && node.getBalanceFactor() < -1){
            node = rebalanceDeleteRight(node);
        }
        if (node != null && node.getBalanceFactor() > 1){
            node = rebalanceDeleteLeft(node);
        }
        
        return node;
    }
    
     /**
     * Finds the successor of the node to be deleted.
     * Either inorder or preorder successor
     */
    public static AVLTreeNode successor(AVLTreeNode node){
        AVLTreeNode successor;
        successor = node.getRight();
        return successor;
        
    }
    
    
    /**
     * Rebalance binary tree node from the left.
     * Handles case 1 and case 2
     */
    public static AVLTreeNode rebalanceLeft(AVLTreeNode node, String key){
        if (key.charAt(0) < node.getLeft().getKey().charAt(0)){
            return rotateWithLeftChild(node);
        }
        else{
            return doubleRotateWithLeftChild(node);
        }
    }
    
    /**
     * Rebalance binary tree node from the left after deletion.
     * 
     */
    public static AVLTreeNode rebalanceDeleteLeft(AVLTreeNode node){
        if (node.getLeft().getBalanceFactor() >= 0){
            return rotateWithLeftChild(node);
        }
        else {
            return doubleRotateWithRightChild(node.getLeft());
        }
    }
    
    /**
     * Rebalance binary tree node from the right.
     * Handles case 3 and case 4
     */
    public static AVLTreeNode rebalanceRight(AVLTreeNode node, String key){
        if (key.charAt(0) > node.getRight().getKey().charAt(0)){
            return rotateWithRightChild(node);
        }
        else{
            return doubleRotateWithRightChild(node);
        }
    }
    
    /**
     * Rebalance binary tree node from the right after deletion.
     * 
     */
    public static AVLTreeNode rebalanceDeleteRight(AVLTreeNode node){
        if (node.getRight().getBalanceFactor() <= 0){
            return rotateWithRightChild(node);
        }
        else{
            return doubleRotateWithLeftChild(node);
        }
    }
    
     /**
     * Reset the heights of trees that have been rotated.
     * Called in the rotate left and right methods.
     */
    private static void setHeightsDelete(AVLTreeNode node){
        if (node != null && node.hasLeft() && node.hasRight()){
            node.setHeight(Math.max(height(node.getLeft()), height(node.getRight()))+1);
        }
        else if (node != null &&node.hasLeft()){
            node.setHeight(height(node.getLeft())+1);
        }
        else if (node != null &&node.hasRight()){
            node.setHeight(height(node.getRight())+1);
        } 
        else if (node != null ){
            node.setHeight(1);
        }
    }
    
    /**
     * Rotate binary tree node with left child.
     * This is a single rotation for case 1.
     */
    public static AVLTreeNode rotateWithLeftChild( AVLTreeNode k2 )
    {
        AVLTreeNode k1;
        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        resetHeights(k1);
        return k1;
    }
    

    /**
     * Rotate binary tree node with right child.
     * This is a single rotation for case 4.
     */
    public static AVLTreeNode rotateWithRightChild( AVLTreeNode k1 )
    {
        AVLTreeNode k2;
        k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        resetHeights(k2);
        return k2;
    }

    /**
     * Double rotate binary tree node: first rotate k3's left child
     * with its right child; then rotate node k3 with the new left child.
     * This is a double rotation for case 2.
     */
    public static AVLTreeNode doubleRotateWithLeftChild( AVLTreeNode k3 )
    {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * Double rotate binary tree node: first rotate k1's right child
     * with its left child; then rotate node k1 with the new right child.
     * This is a double rotation for case 3.
     */
    public static AVLTreeNode doubleRotateWithRightChild( AVLTreeNode k1 )
    {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }
    
    /**
     * Reset the heights of trees that have been rotated.
     * Called in the rotate left and right methods.
     */
    private static void resetHeights(AVLTreeNode node){
        if (node.hasLeft() && node.hasRight()){
            if (height(node.getLeft())==height(node.getRight())){
               node.setHeight(Math.max(height(node.getLeft()), height(node.getRight()))+1);
            }
            else{
               node.setHeight(Math.max(height(node.getLeft()), height(node.getRight()))); 
            }
        }
        else if (node.hasLeft()){
            
            if (height(node) != height(node.getLeft())+1){
                node.setHeight(height(node.getLeft())+1);
            }
        }
        else if (node.hasRight()){
            
            if (height(node) != height(node.getRight())+1){
                node.setHeight(height(node.getRight())+1);
            }
        }
        else{
            node.setHeight(1);
        }
        if (node.hasRight()){
            resetHeights(node.getRight());
        }
        if (node.hasLeft()){
            resetHeights(node.getLeft());
        }
    }

    
    /**
     * Obtain a list containing the root node of the given structure i.e. tNode itself.
     */
    public static List<AVLTreeNode> levelZero(AVLTreeNode tNode) {
        List<AVLTreeNode> level = new ArrayList<AVLTreeNode>();
        level.add(tNode);
        return level;
    }
    
    
    /**
     * Given a list of nodes, obtain the next level. 
     * 
     * <p>
     * If the tree structure is incomplete, <code>AVLTreeNode.EMPTY_NODE</code> is inserted as a place holder for each
     * missing node.
     * </p>
     */
    public static List<AVLTreeNode> nextLevel(List<AVLTreeNode> level) {
        List<AVLTreeNode> nextLevel = new ArrayList<AVLTreeNode>(); 
        
        for (AVLTreeNode node : level) {
            nextLevel.add(node.hasLeft() ? node.getLeft() : AVLTreeNode.EMPTY_NODE); 
            nextLevel.add(node.hasRight() ? node.getRight() : AVLTreeNode.EMPTY_NODE);
        }
        return nextLevel;
    }
    
    /**
     * Determine whether node is a place holder i.e. <code>node==AVLTreeNode.EMPTY_NODE</code>
     */
    public static boolean isPlaceHolder(AVLTreeNode node) {
        return node==AVLTreeNode.EMPTY_NODE;
    }
    
}
