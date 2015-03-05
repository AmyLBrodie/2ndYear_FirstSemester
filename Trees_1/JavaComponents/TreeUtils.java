

/**
 * 
 * 
 * @author Amy Brodie
 * @version 01/03/2015
 */


public class TreeUtils {
    
    /*
     * Instance variables
     */
    private static int level = 0, height1, height2;
    
    /*
     * A method that checks for similarity in the shape of the BST
     */
    public static boolean similar(BinaryTreeNode node1, BinaryTreeNode node2){
        /*
         * Checks if the level is zero and if so assigns the heights of the trees to variables
         */
        if (level == 0){
            height1 = node1.getHeight();
            height2 = node2.getHeight();
        }
        
        /*
         * Checks if the heights of the trees are equal, if yes: outputs true, if no: outputs false
         */
        if (height1 == height2){
            /*
             *Checks that level zero is being checked and whether there is a node or not in both trees.
             * If both don't have nodes returns true, if one has a node and the other doesn't it returns false.
             * In the case that both have nodes it will then check if these nodes have right and left nodes,
             * it will continue in this way until all nodes have been checked or the trees are determined to 
             * be not similar.
             */
            if (level == 0 && levelZeroNodeList(node1).equals("") && levelZeroNodeList(node2).equals("")){
                return true;
            }
            else if (level == 0 && ((!levelZeroNodeList(node1).equals("") && levelZeroNodeList(node2).equals("")) || (levelZeroNodeList(node1).equals("") && !levelZeroNodeList(node2).equals("")))){
               return false;   
            }
            else{
                level += 1;
                if (node1.hasLeft() && node2.hasLeft()){
                    if (height1-1 == level && (!node1.hasRight() && !node2.hasRight()) || (node1.hasRight() && node2.hasRight())){
                       return true;    
                    }
                    else if (height1-1 != level){
                       return similar(node1.getLeft(), node2.getLeft());
                    }
                    else{
                       return false; 
                    }
                }
                else if (node1.hasRight() && node2.hasRight()) {
                    if (height1-1 == level && (!node1.hasLeft() && !node2.hasLeft()) || (node1.hasLeft() && node2.hasLeft())){
                       return true;    
                    }
                    else if (height1-1 != level){
                       return similar(node1.getRight(), node2.getRight());
                    }
                    else{
                       return false; 
                    }    
                }
            }
        }
        else{
            return false;
        }
        return false;
    }
    
    /*
     * A method that makes a string out of the root node of a tree or returns 
     * an empty string if the node is null.
     */
    public static String levelZeroNodeList(BinaryTreeNode temp){
        if (temp.getHeight() == 0){
            return "";
        }
        else{
            return Integer.toString(temp.getItem());
        }
           
    }
    
    
}
