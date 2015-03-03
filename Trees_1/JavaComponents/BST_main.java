
/**
 *
 * @author Amy Brodie
 * @version 01/03/2015
 */

import java.util.*;

public class BST_main {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a comma separarted list of numbers for tree one: ");
        String treeOne = scan.nextLine();
        System.out.print("Enter a comma separarted list of numbers for tree two: ");
        String treeTwo = scan.nextLine();
        
        SimpleBST searchTreeOne = new SimpleBST(), searchTreeTwo = new SimpleBST();
        int tempOne = 0, tempTwo = 0;
        
        while (treeOne != null){
            tempOne = Integer.parseInt(treeOne.substring(0,treeOne.indexOf(",")));
            treeOne = treeOne.substring(treeOne.indexOf(",")+2);
            System.out.println(treeOne);
            
        }
        
        
        
    }
    
}
