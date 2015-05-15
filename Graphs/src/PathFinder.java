
import java.util.ArrayList;


/**
 *
 * @author amy
 */
public class PathFinder {
    
    // instance variables
    private Integer[][] adjacencyMatrix;
    private String startNode;
    private ArrayList<String> possiblePaths = new ArrayList<String>();
    private String shortestPath;
    
    // constructor for PathFinder class
    PathFinder(ArrayList<String> paths, ArrayList<String> nodes){
        adjacencyMatrix = new Integer[nodes.size()][nodes.size()];
        startNode = nodes.get(nodes.indexOf("A"));
        SortPaths(adjacencyMatrix, paths, nodes);
    }
    
    // a method that initialise a 2d integer array (adjacency matrix) to store all edge values
    private void SortPaths(Integer[][] matrix, ArrayList<String> paths, ArrayList<String> nodes){
        int pathLength = 0;
        String node1, node2;
        
        // loops from zero until the size of the paths array minus one
        for (int i=0; i<paths.size(); i++){
            // gets edge information from paths array
            pathLength = Integer.parseInt(paths.get(i).substring(paths.get(i).indexOf(":")+1));
            
            // gets node information from the paths array
            node1 = paths.get(i).substring(0,1);
            node2 = paths.get(i).substring(1,2);
            
            // assigns the edge values from node1 to node2 and node2 to node1 in the adjacency matrix
            matrix[nodes.indexOf(node1)][nodes.indexOf(node2)] = pathLength;
            matrix[nodes.indexOf(node2)][nodes.indexOf(node1)] = pathLength;
            
            // assigns zero to the edge values from node1 to node1 in the adjaceny matrix
            if (matrix[nodes.indexOf(node1)][nodes.indexOf(node1)]==null){
                matrix[nodes.indexOf(node1)][nodes.indexOf(node1)] = 0;
            }
            
            // assigns zero to the edge values from node2 to node2 in the adjaceny matrix
            if (matrix[nodes.indexOf(node2)][nodes.indexOf(node2)]==null){
                matrix[nodes.indexOf(node2)][nodes.indexOf(node2)] = 0;
            }
        }
    }
    
    // creates an array of all the possible paths
    private ArrayList<String> PossiblePaths(ArrayList<String> paths, ArrayList<String> nodes){
        
        ArrayList<String> possible = new ArrayList<String>();
        StringBuilder build = new StringBuilder();
        
        // create first possible path from nodes array
        for (int i=1; i<nodes.size(); i++){
            build.append(nodes.get(i));
        }
        
        // calls the PossibleCombinations method to find all permutations of the first possible path and assigns this to an array
        ArrayList<String> permutations = PossibleCombinations("", build.toString());
        
        // runs through the permuations array twice
        for (int i=0; i<permutations.size(); i++){
            for (int j=i; j<permutations.size(); j++){
                // find the reverse of a specific permutation
                String temp = Reverse(permutations.get(i));
                
                // check that the boolean value from permutation_j==reverse and permutation_i=permutation_j are not the same
                // i.e. when one is false the other must be true, etc
                if ( !permutations.get(i).equals(permutations.get(j)) && temp.equals(permutations.get(j))){
                    // removes the permutation from the list
                    permutations.remove(j);
                }
            }
        }
        
        // runs through all the possible permutations
        for (int i=0; i<permutations.size(); i++){
            // adds the start node, the permutation and the end node to the array.
            possible.add(startNode+permutations.get(i)+startNode);
        }
        
        // returns all the possible paths    
        return possible;
    }
    
    // reverses a string
    private String Reverse(String word){
        
        StringBuilder build = new StringBuilder();
        // runs through the word from the end to the front
        for (int i=word.length()-1; i>=0; i--){
            build.append(word.substring(i,i+1));
        }
        
        // returns the reversed string
        return build.toString();
    }
    
    // finds all possible combinations of a string (permutations)
    private ArrayList<String> PossibleCombinations(String newWord, String word){
        
        ArrayList<String> combinations = new ArrayList<String>();
        int length = word.length();
        // when the length of the word is zero add the newWord variable to the array
        if (length == 0){
            combinations.add(newWord);
        }
        else{
            // run through the word and recursively call the function to add each permutation to the array
            for (int i=0; i<length; i++){
                combinations.addAll(PossibleCombinations(newWord+ word.charAt(i),word.substring(0,i)+word.substring(i+1)));
            }
        }
        
        // returns the array of permutations
        return combinations;
    }
    
    // calculates the path length of a specific path
    public Integer CalculatePaths(String path, ArrayList<String> nodes){
        
        int tempPath = 0;
        
        // runs through the path character by character
        for (int i=0; i<path.length()-1; i++){
            // adds the edge in the adjacency from path[i] to path[i+1] to the total path variable
            tempPath += adjacencyMatrix[nodes.indexOf(path.substring(i,i+1))][nodes.indexOf(path.substring(i+1,i+2))];
        }
        
        // returns the total path length of the path
        return tempPath;
    }
    
    // returns the adjecency matrix
    public Integer[][] getMatrix(){
        return adjacencyMatrix;
    }
    
    // find the shortest path of all the paths
    private void ShortestPath(ArrayList<String> nodes, ArrayList<String> paths){
        
        ArrayList<String> path = PossiblePaths(paths, nodes);
        int pathLengths = CalculatePaths(path.get(0), nodes);
        int shortest = 0;
        
        // run through the path array 
        for (int i=1; i<path.size();i++){
            // checks if the current path is shorter than the previously stored path
            if (pathLengths > CalculatePaths(path.get(i), nodes)){
                // stores the new shortest path length and its index
                pathLengths = CalculatePaths(path.get(i), nodes);
                shortest = i;
            }
        }
        // creates a string with the path string and length for the shortest path
        shortestPath = path.get(shortest)+": "+Integer.toString(pathLengths);
    }
    
    // returns the shortest path string
    public String getShortestPath(ArrayList<String> nodes, ArrayList<String> paths){
        
        // calls the ShortestPath method so that a value can be assigned to the shortestPath variable
        ShortestPath(nodes,paths);
        
        // returns the shortest path
        return shortestPath;
    }

}
