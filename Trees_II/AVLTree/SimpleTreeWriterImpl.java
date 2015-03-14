// Task1


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Amy Brodie
 * @version 10/03/2015
 */
public class SimpleTreeWriterImpl implements SimpleTreeWriter {
    
    private PrintStream print;
    
    // constructor that accepts a printstream parameter
    public SimpleTreeWriterImpl(PrintStream stream){
        setDestination(stream);
    }
    
    // sets the destination printstream
    public void setDestination(PrintStream stream){
        print = stream;
    }
    
    // writes the AVL tree to the chosen printstream
    public void write(AVLTreeNode tree){
        int height = tree.getHeight(), largestLength = tree.getLargest(), leadingSpace, betweenSpace;
        List<AVLTreeNode> levelNodes = TreeUtils.levelZero(tree); // prints the root node
        // runs through the levels of the AVL tree
        for(int level=0; level<height; level++){
            leadingSpace = (int) ((Math.pow(2,height-level)-1)/2); // calculates leading space needed
            betweenSpace = (int) ((Math.pow(2,height-level)-1)); // calculates internode space needed
            printLevel(leadingSpace, betweenSpace,largestLength, levelNodes); // prints a specific level
            levelNodes = TreeUtils.nextLevel(levelNodes); // goes to the next level in the tree
        }
        
    }
    
    // prints out the node with correct formatting
    public void printNode(AVLTreeNode node, int length){
        // checks if node is null
        if (TreeUtils.isPlaceHolder(node)){
            print.printf(spacing(length)); // prints correct spacing for a null node
        }
        else{
            print.printf("%"+length+"s", node.toString()); // prints node with padding space if needed
        }
    }
    
    // prints out each level on its own line with correct spacing and nodes
    public void printLevel(int lead, int between, int length, List<AVLTreeNode> level){
        lead *= length; // calculates the amount of space needed before the first node of a level
        between *= length; // calculates the amount of space needed between each node on a level
        Iterator<AVLTreeNode> iterate = level.iterator(); // goes through the nodes on each level
        
        // checks if there are more nodes in the level
        if (iterate.hasNext()){
            print.print(spacing(lead)); // prints leading space
            printNode(iterate.next(), length); // prints first node
            // prints nodes and spacing while there are still more nodes on the level
            while (iterate.hasNext()){
                print.print(spacing(between)); // prints spacing between nodes
                printNode(iterate.next(), length); // prints next node on the level
            }
            print.println(""); // moves to next line so that the next level can be printed
        }
        
    }
    
    // sets the spacing for before and between the nodes
    public String spacing(int length){
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i<length; i++){
            spaces.append(" ");
        }
        return spaces.toString();
    }

}
