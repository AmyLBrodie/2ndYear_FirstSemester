
import java.io.PrintStream;



/**
 *
 * 
 * @author Amy Brodie
 * @version 01/03/2015
 */

import java.util.*;

public class SimpleTreeWriterImpl implements SimpleTreeWriter{
    
    private PrintStream print;
    
    /*
     * Constructor that accepts a printstream as the  parameter
     */
    public SimpleTreeWriterImpl(PrintStream stream){
         setDestination(stream) ;
    }
    
    /*
     * Sets the destination for the printstream to print to
     */
    public void setDestination(PrintStream stream){
        print = new PrintStream(stream);    
           
    }
    

    
    /*
     * A method that allows the BST to be printed to the screen and other files.
     * Also controls the formatting for the printing of the BST (spacing, etc).
     */
    public void write(BinaryTreeNode tree){
        int height, largest, leadingBlanks, betweenBlanks, level=0;
        
        StringBuilder printTree = new StringBuilder();
        boolean beginning = true;
            
        if (tree != null){
            // creates two queues so the BST can be traversed level by level
            Queue<BinaryTreeNode> thisLevel = new LinkedList<BinaryTreeNode>();
            Queue<BinaryTreeNode> nextLevel = new LinkedList<BinaryTreeNode>();
            
            height = tree.getHeight();
            largest = tree.getLargest();
            
            thisLevel.add(tree);
            // loop runs while the queue is not empty and the level is less than the height of the tree
            while ((!thisLevel.isEmpty())&& (level < height)){
                leadingBlanks = (int) ((Math.pow(2,height-level)-1)/2); // number of leading spaces needed
                betweenBlanks = (int) ((Math.pow(2,height-level)-1)); // number of spaces needed in between nodes
                // loop runs while queue is not empty
                while (!thisLevel.isEmpty()){
                    // if boolean value is true then node is first in level
                    if (beginning == true){
                        for (int leadSpaces = 0; leadSpaces < leadingBlanks*Integer.toString(largest).length()-(height-1-level);leadSpaces++){
                            printTree.append(" ");
                        } 
                       
                        beginning = false;
                        // handles normal nodes
                        if (thisLevel.peek().getItem() != null){
                            //printTree.append(extraSpaces(level, height, Integer.toString(thisLevel.peek().getItem()), Integer.toString(largest)));
                            printTree.append(thisLevel.peek().getItem());
                        }
                        // handles null nodes
                        else{
                            //printTree.append(extraSpaces(level, height," ", Integer.toString(largest)));
                            printTree.append("  ");
                        }
                    }
                    else{
                       for (int betweenSpaces = 0; betweenSpaces < betweenBlanks*Integer.toString(largest).length();betweenSpaces++){
                            printTree.append(" ");
                        }
                       
                        if (thisLevel.peek().getItem() != null){
                            //printTree.append(extraSpaces(level, height,Integer.toString(thisLevel.peek().getItem()), Integer.toString(largest)));
                            printTree.append(thisLevel.peek().getItem());
                        }
                        // handles null nodes
                        else{
                            //printTree.append(extraSpaces(level, height," ", Integer.toString(largest)));
                            printTree.append("  ");
                        }
                    }
                    // checks if node has left branch
                    tree = thisLevel.peek().getLeft();
                    if (thisLevel.peek().hasLeft()){
                        nextLevel.add(tree);
                    }
                    else{
                        nextLevel.add(new BinaryTreeNode(null));
                    }
                    // checks if node has right branch
                    tree = thisLevel.peek().getRight();
                    if (thisLevel.peek().hasRight()){
                        nextLevel.add(tree);
                    }
                    else{
                        nextLevel.add(new BinaryTreeNode(null));
                    }
                    thisLevel.remove();
                }
                // transfer items in next queue to current queue
                beginning = true;
                while (!nextLevel.isEmpty()){
                    thisLevel.add(nextLevel.remove());
                }
                level += 1;
                print.println(printTree.toString());
                printTree.delete(0, printTree.length());
            }
        }
        else{
            print.println("");
        }
        
        
    }
    
}
