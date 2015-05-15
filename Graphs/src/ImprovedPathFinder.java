
import java.util.ArrayList;


/**
 *
 * @author amy
 */
public class ImprovedPathFinder extends PathFinder{
    
    // instance variables
    private ArrayList<String> queue = new ArrayList<String>();
    
    // constructor for the ImprovedPathFinder class that calls the constructor for the PathFinder class
    ImprovedPathFinder(ArrayList<String> paths, ArrayList<String> nodes){
        super(paths, nodes);
    }
    
    // finds the next node by looking at the nodes that connect to the previous node and finding the shortest edge
    public String nextNode(ArrayList<String> nodes, Integer[][] matrix, String path, String fullPath){
        
        int index = nodes.indexOf(path);
        int shortestIndex = index;
        
        // loops through the nodes
        for (int i=0; i<nodes.size(); i++){
            
            // checks that the specific node is not in the path already
            if (! fullPath.contains(nodes.get(i))){
                // checks if the index of the shortest is equal to the original index
		if (shortestIndex == index){
                    shortestIndex = i;
		}
                // checks if the current edge is smaller than the shortest edge so far
		else if (matrix[index][i] < matrix[index][shortestIndex]){
                    // makes the shortest index the current index
                    shortestIndex = i;
		}
                // checks if the current edge is equal to the shortest edge so far
		else if (matrix[index][i] == matrix[index][shortestIndex]){
                    // adds the two nodes to the queue array
                    queue.add(nodes.get(i));
                    queue.add(nodes.get(shortestIndex));
		}
            }
        }

        // checks if the queue is not empty and does not contain the node being looked at
        if (! queue.isEmpty() && ! queue.contains(path)){
            // returns an empty string
            return "";
        }
        // checks if the queue is empty or the queue contains the node being looked at
        else{
            // returns the node with the shortest path length    
            return nodes.get(shortestIndex);
        }
    }
    
    // finds the shortest path given the nodes and edges
    public String ShortestPath(ArrayList<String> nodes, String startNode){
        
        Integer[][] matrix = super.getMatrix();
        String path = startNode, next, previous;
        // find next node and set previous node as start node
        next = nextNode(nodes, matrix, startNode, path);
        previous = startNode;
        
        // loop until length of the path is equal to the size of the nodes array
        while (path.length() < nodes.size()){
            // checks if the queue is empty
            if (!queue.isEmpty()){
                int i = 0;
                // loops until i is equal to the size of the queue array minus one
                while (i < queue.size()-1){
                    String node1 = nextNode(nodes, matrix, queue.get(i), path+queue.get(i));
                    String node2 = nextNode(nodes, matrix, queue.get(i+1), path+queue.get(i+1));
                    // checks if the edge from the previous to node1 is the shortest
                    if (matrix[nodes.indexOf(previous)][nodes.indexOf(node1)] < matrix[nodes.indexOf(previous)][nodes.indexOf(node2)]){
                        // sets previous to the first value in the queue, sets the next to be node1
                        previous = queue.get(i);
                        next = node1;
                    }
                    // checks if the edge from the previous to node2 is shortest
                    else if (matrix[nodes.indexOf(previous)][nodes.indexOf(node1)] > matrix[nodes.indexOf(previous)][nodes.indexOf(node2)]){
                        // sets previous to the second value in the queue, sets the next to be node2
                        previous = queue.get(i+1);
                        next = node2;
                    }
                    i++;
                }
                // clears the queue
                queue.clear();
                // updates the path
                path += previous;
            }
            else{
                // sets previous to the current node
                previous = next;
                // updates the path
                path += previous;
                // sets next to be the next node with the shortest edge
                next = nextNode(nodes, matrix, previous, path);
            }
            
        }
        // adds the start node to the end of the path string to complete the cycle
        path += startNode;
        
        // returns the path string
        return path;
    }
    
    
    // calculates the path length of a specific path
    public Integer CalculatePaths(String path, ArrayList<String> nodes){
        
        Integer[][] matrix = super.getMatrix();
        int tempPath = 0;
        
        // runs through the path character by character
        for (int i=0; i<path.length()-1; i++){
            // adds the edge in the adjacency from path[i] to path[i+1] to the total path variable
            tempPath += matrix[nodes.indexOf(path.substring(i,i+1))][nodes.indexOf(path.substring(i+1,i+2))];
        }
        
        // returns the path length
        return tempPath;
    }
    
    // returns the shortest path string
    public String getPath(ArrayList<String> nodes, String startNode){
        
        // calls the ShortestPath method so that a value can be assigned to the shortestPath variable
        String path = ShortestPath(nodes,startNode);
        
        // returns the shortest path
        return path + ": " +Integer.toString(CalculatePaths(path, nodes));
    }
}
