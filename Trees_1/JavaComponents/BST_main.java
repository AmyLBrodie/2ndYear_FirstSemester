
/**
 *
 * @author Amy Brodie
 * @version 01/03/2015
 */

import java.util.*;
import java.io.*;

/*
 * Driver class for the BST project
 */
public class BST_main {
    public static void main(String args[]) throws FileNotFoundException{
        // Input from commandline
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a comma separarted list of numbers for tree one: ");
        String treeOne = scan.nextLine();
        System.out.print("Enter a comma separarted list of numbers for tree two: ");
        String treeTwo = scan.nextLine();
        
        // variables
        SimpleBST searchTreeOne = new SimpleBST(), searchTreeTwo = new SimpleBST();
        int tempOne = 0, tempTwo = 0;
        
        /*
         * Loops through the list provided in the commandline and removes the commas 
         * from the input and makes each value a node in the BST
         */
        while (!treeOne.equals("")){
         
            if (treeOne.indexOf(",") == -1){
                tempOne = Integer.parseInt(treeOne);
                treeOne = "";
            }
            else{
                tempOne = Integer.parseInt(treeOne.substring(0,treeOne.indexOf(",")));
                treeOne = treeOne.substring(treeOne.indexOf(",")+2);
            }
            
            searchTreeOne.insert(tempOne);
        }
        
        /*
         * Loops through the list provided in the commandline and removes the commas 
         * from the input and makes each value a node in the BST
         */
        while (!treeTwo.equals("")){
        
            if (treeTwo.indexOf(",") == -1){
                tempTwo = Integer.parseInt(treeTwo);
                treeTwo = "";
            }
            else{
                tempTwo = Integer.parseInt(treeTwo.substring(0,treeTwo.indexOf(",")));
                treeTwo = treeTwo.substring(treeTwo.indexOf(",")+2);
            }
            
            searchTreeTwo.insert(tempTwo);
        }
        
        File file1 = new File("T1.out"), file2 = new File("T2.out");
        
        SimpleTreeWriter writer = new SimpleTreeWriterImpl(System.out), treeWriter1 = new SimpleTreeWriterImpl(new PrintStream(file1)), treeWriter2 = new SimpleTreeWriterImpl(new PrintStream(file2));
        /*
         * Prints the BSTs to the command line
         */
        System.out.println("Tree one:");
        SimpleBST.print(searchTreeOne,writer);
        SimpleBST.print(searchTreeOne,treeWriter1);
        System.out.println("Tree two:");
        SimpleBST.print(searchTreeTwo,writer);
        SimpleBST.print(searchTreeTwo,treeWriter2);
        
        
        
        
        /*
         * Checks if the BSTs are similarly built and outputs if similar or not
         */
        if (searchTreeOne.similar(searchTreeTwo)){
            System.out.print("The trees are similar.");
        }
        else{
            System.out.print("The trees are not similar.");
        }
        
    }
    
}
