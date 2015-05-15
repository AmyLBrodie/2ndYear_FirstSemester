
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author amy
 */
public class LoadFile {
    
    public static String load(String fileName, String solution) throws FileNotFoundException{
        
        // Create and initialise necessary variables
        String node1, node2, temp, shortest = "";
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        int numberOfPaths = 0, pathLength;
        ArrayList<String> nodes = new ArrayList<String>();
        ArrayList<String> paths = new ArrayList<String>();
        
        // while the input file has more lines load the lines and extract the information
        while (scan.hasNext()){
            
            // read line from text file and split it into the two nodes and the edge that connects them
            temp = scan.nextLine();
            node1 = temp.substring(0, temp.indexOf(":")).trim();
            node2 = temp.substring(temp.indexOf(":")+1, temp.lastIndexOf(":")).trim();
            pathLength = Integer.parseInt(temp.substring(temp.lastIndexOf(":")+1).trim());
            
            // add the nodes and their edge to an arraylist
            paths.add(node1+node2+":"+Integer.toString(pathLength));
            
            // check if the arraylist keeping track of the nodes is empty and if it is add both nodes to the array
            if (nodes.isEmpty()){
                nodes.add(node1);
                nodes.add(node2);
            }
            // check if the nodes array does not contain a specific node, if not add that node to the array
            if (! nodes.contains(node1)){
                nodes.add(node1);
            }
            // check if the nodes array does not contain a specific node, if not add that node to the array
            if (! nodes.contains(node2)){
                nodes.add(node2);
            }
        }
        
        
        String startNode;
        
        // check if the solution selected is the naive solution
        if (solution.equals("N")){
            // call the PathFinder constructor
            PathFinder path = new PathFinder(paths, nodes);
            // get the shortest path information
            shortest = path.getShortestPath(nodes, paths);
        }
        // check if the solution selected is the improved solution
        else if (solution.equals("I")){
            // call the ImprovedPathFinder constructor
            ImprovedPathFinder path = new ImprovedPathFinder(paths, nodes);
            
            // find out which node to start at
            System.out.println("Enter the node to start at:");
            Scanner input = new Scanner(System.in);
            startNode = input.nextLine();
            
            // check if the inputted start node is in the nodes array
            if (nodes.contains(startNode)){
                // get the shortest path information
                shortest = path.getPath(nodes, startNode);
            }
            // check if the upper case version of the inputted start node is in the nodes array
            else if (nodes.contains(startNode.toUpperCase())){
                shortest = path.getPath(nodes, startNode.toUpperCase());
            }
            // check if the lower case version of the inputted start node is in the nodes array
            else if (nodes.contains(startNode.toLowerCase())){
                shortest = path.getPath(nodes, startNode.toLowerCase());
            }
            // check if the inputted start node is not in the nodes array
            else{
                // output error messages
                System.out.println("That is not a valid node!");
                System.out.println("Starting at node " + nodes.get(0) + "...");
                System.out.println();
                // get shortest path information using first node in nodes array
                shortest = path.getPath(nodes, nodes.get(0));
            }
        }
        
        // check if the option selected is not valid, if so exit the program
        else {
            System.out.println("Not a valid option");
            System.exit(0);
        }
        
        // returns the shortest path
        return shortest;
    }

}
