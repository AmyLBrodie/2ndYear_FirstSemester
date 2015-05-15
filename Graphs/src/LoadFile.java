
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
        String node1, node2, temp, shortest = "";
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        int numberOfPaths = 0, pathLength;
        ArrayList<String> nodes = new ArrayList<String>();
        ArrayList<String> paths = new ArrayList<String>();
        while (scan.hasNext()){
            numberOfPaths++;
            temp = scan.nextLine();
            node1 = temp.substring(0, temp.indexOf(":")).trim();
            //System.out.println(node1);
            node2 = temp.substring(temp.indexOf(":")+1, temp.lastIndexOf(":")).trim();
            //System.out.println(node2);
            pathLength = Integer.parseInt(temp.substring(temp.lastIndexOf(":")+1).trim());
            
            paths.add(node1+node2+":"+Integer.toString(pathLength));
            
            if (nodes.isEmpty()){
                nodes.add(node1);
                nodes.add(node2);
            }
            
            if (! nodes.contains(node1)){
                nodes.add(node1);
            }
            
            if (! nodes.contains(node2)){
                nodes.add(node2);
            }
        }
       // PathFinder path = null;
        String startNode = "";
        if (solution.equals("N")){
            PathFinder path = new PathFinder(paths, nodes);
            shortest = path.getShortestPath(nodes, paths);
        }
        else if (solution.equals("I")){
            ImprovedPathFinder path = new ImprovedPathFinder(paths, nodes);
            System.out.println("Enter the node to start at:");
            Scanner input = new Scanner(System.in);
            startNode = input.nextLine();
            if (nodes.contains(startNode)){
                shortest = path.getPath(nodes, startNode);
            }
            else if (nodes.contains(startNode.toUpperCase())){
                shortest = path.getPath(nodes, startNode.toUpperCase());
            }
            else if (nodes.contains(startNode.toLowerCase())){
                shortest = path.getPath(nodes, startNode.toLowerCase());
            }
            else{
                System.out.println("That is not a valid node!");
                System.out.println("Starting at node " + nodes.get(0) + "...");
                System.out.println();
                shortest = path.getPath(nodes, nodes.get(0));
            }
        }
        else {
            System.out.println("Not a valid option");
            System.exit(0);
        }
        
        //shortest = path.getShortestPath(nodes, paths);
        
        return shortest;
    }

}
