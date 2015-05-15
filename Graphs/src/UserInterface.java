

import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author amy
 */
public class UserInterface {
    
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name of the input file to be used");
        String file = scan.nextLine();
        System.out.println("Select an option:");
        System.out.println("  N: Run the naive solution \n  I: Run the improved solution:");
        String solution = scan.nextLine().toUpperCase();
        String shortest, path;
        shortest = LoadFile.load(file, solution);
        path = shortest.substring(0,shortest.indexOf(":"));
        shortest = shortest.substring(shortest.indexOf(":")+1).trim();
        System.out.println("The shortest path is "+ path + " with a path length of " + shortest);
    }

}
