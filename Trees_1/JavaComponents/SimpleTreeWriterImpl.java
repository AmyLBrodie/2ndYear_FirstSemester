
import java.io.PrintStream;



/**
 *
 * 
 * @author Amy Brodie
 * @version 01/03/2015
 */

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class SimpleTreeWriterImpl implements SimpleTreeWriter{
    
    private File file;
    
    public void setDestination(PrintStream stream){
        try {
            stream = new PrintStream(file);
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(SimpleTreeWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    
    private String extraSpaces(String node, String largest){
        String temp = "";
        if (largest.length() > node.length()){
            for (int i=0; i<largest.length()-node.length();i++){
                temp += " ";
            }
        }
        return temp;
    }
    
    /*
     * A method that allows the BST to be printed to the screen and other files.
     * Also controls the formatting for the printing of the BST (spacing, etc).
     */
    public void write(BinaryTreeNode tree){
        int height = tree.getHeight(), largest = tree.getLargest(), leadingBlanks, betweenBlanks, level=0;
        StringBuilder printTree = new StringBuilder();
        boolean beginning = true;
            
        if (tree.getItem() != null){
            Queue<BinaryTreeNode> thisLevel = new LinkedList<BinaryTreeNode>();
            Queue<BinaryTreeNode> nextLevel = new LinkedList<BinaryTreeNode>();
            
            thisLevel.add(tree);
            while ((!thisLevel.isEmpty())&& (level < height)){
                leadingBlanks = (int) ((Math.pow(2,height-level) -1)/2);
                betweenBlanks = (int) (Math.pow(2,height-level)-1);
                while (!thisLevel.isEmpty()){
                    if (beginning == true){
                        for (int leadSpaces = 0; leadSpaces < leadingBlanks;leadSpaces++){
                            printTree.append(" ");
                        } 
                        beginning = false;
                        if (thisLevel.peek().getItem() != null){
                            printTree.append(extraSpaces(Integer.toString(thisLevel.peek().getItem()), Integer.toString(largest)));
                            printTree.append(thisLevel.peek().getItem());
                        }
                        else{
                            printTree.append(extraSpaces(" ", Integer.toString(largest)));
                            printTree.append(" ");
                        }
                    }
                    else{
                       for (int betweenSpaces = 0; betweenSpaces < betweenBlanks;betweenSpaces++){
                            printTree.append(" ");
                        } 
                        if (thisLevel.peek().getItem() != null){
                            printTree.append(extraSpaces(Integer.toString(thisLevel.peek().getItem()), Integer.toString(largest)));
                            printTree.append(thisLevel.peek().getItem());
                        }
                        else{
                            printTree.append(extraSpaces(" ", Integer.toString(largest)));
                            printTree.append(" ");
                        }
                    }
                    tree = thisLevel.peek().getLeft();
                    if (thisLevel.peek().hasLeft()){
                        nextLevel.add(tree);
                    }
                    else{
                        nextLevel.add(new BinaryTreeNode(null));
                    }
                    tree = thisLevel.peek().getRight();
                    if (thisLevel.peek().hasRight()){
                        nextLevel.add(tree);
                    }
                    else{
                        nextLevel.add(new BinaryTreeNode(null));
                    }
                    thisLevel.remove();
                }
                beginning = true;
                while (!nextLevel.isEmpty()){
                    thisLevel.add(nextLevel.remove());
                }
                level += 1;
                System.out.println(printTree.toString());
                printTree.delete(0, printTree.length());
            }
        }
        
        
    }
    
}
