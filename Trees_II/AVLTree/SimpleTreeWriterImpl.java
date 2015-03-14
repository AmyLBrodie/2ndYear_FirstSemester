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
    
    public SimpleTreeWriterImpl(PrintStream stream){
        setDestination(stream);
    }
    
    public void setDestination(PrintStream stream){
        print = stream;
    }
    
    public void write(AVLTreeNode tree){
        int height = tree.getHeight(), largestLength = tree.getLargest(), leadingSpace, betweenSpace;
        List<AVLTreeNode> levelNodes = TreeUtils.levelZero(tree);
        for(int level=0; level<height; level++){
            leadingSpace = (int) ((Math.pow(2,height-level)-1)/2); 
            betweenSpace = (int) ((Math.pow(2,height-level)-1));
            printLevel(leadingSpace, betweenSpace,largestLength, levelNodes);
            levelNodes = TreeUtils.nextLevel(levelNodes);
        }
        
    }
    
    public void printNode(AVLTreeNode node, int length){
        if (TreeUtils.isPlaceHolder(node)){
            print.printf(spacing(length));
        }
        else{
            print.printf("%"+length+"s", node.toString());
        }
    }
    
    public void printLevel(int lead, int between, int length, List<AVLTreeNode> level){
        lead *= length;
        between *= length;
        Iterator<AVLTreeNode> iterate = level.iterator();
        if (iterate.hasNext()){
            print.print(spacing(lead));
            printNode(iterate.next(), length);
            while (iterate.hasNext()){
                print.print(spacing(between));
                printNode(iterate.next(), length);
            }
            print.println("");
        }
        
    }
    
    public String spacing(int length){
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i<length; i++){
            spaces.append(" ");
        }
        return spaces.toString();
    }

}
