

import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author amy
 */
public class UserInterface {
    
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name of the file you would like to use");
        String file = scan.nextLine();
        System.out.println("Run the naive solution (N) or the improved solution (I):");
        String solution = scan.nextLine().toUpperCase();
        String shortest = "", path;
        shortest = LoadFile.load(file, solution);
        path = shortest.substring(0,shortest.indexOf(":"));
        shortest = shortest.substring(shortest.indexOf(":")+1).trim();
        System.out.println("The shortest path is "+ path + " with a path length of " + shortest);
    }

}
