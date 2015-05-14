
import java.util.ArrayList;


/**
 *
 * @author amy
 */
public class PathFinder {
    
    private Integer[][] adjacencyMatrix;
    private String startNode;
    private ArrayList<String> possiblePaths = new ArrayList<String>();
    private long pathLength;
    private String shortestPath;
    
    
    PathFinder(ArrayList<String> paths, ArrayList<String> nodes){
        adjacencyMatrix = new Integer[nodes.size()][nodes.size()];
        startNode = nodes.get(0);
        pathLength = Factorial(nodes.size()-1)/2;
        SortPaths(adjacencyMatrix, paths, nodes);
        /*for (int i =0 ; i<nodes.size(); i++){
            for (int j=0; j<nodes.size(); j++){
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }*/
    }
    
    private long Factorial(int n){
        long fact = 1;
        for (int i=n; i>0; i--){
            fact *= n;
        }
        return fact;
    }
    
    private void SortPaths(Integer[][] matrix, ArrayList<String> paths, ArrayList<String> nodes){
        int pathLength = 0;
        String node1, node2;
        for (int i=0; i<paths.size(); i++){
            pathLength = Integer.parseInt(paths.get(i).substring(paths.get(i).indexOf(":")+1));
            node1 = paths.get(i).substring(0,1);
            node2 = paths.get(i).substring(1,2);
            matrix[nodes.indexOf(node1)][nodes.indexOf(node2)] = pathLength;
            matrix[nodes.indexOf(node2)][nodes.indexOf(node1)] = pathLength;
            
            if (matrix[nodes.indexOf(node1)][nodes.indexOf(node1)]==null){
                matrix[nodes.indexOf(node1)][nodes.indexOf(node1)] = 0;
            }
            
            if (matrix[nodes.indexOf(node2)][nodes.indexOf(node2)]==null){
                matrix[nodes.indexOf(node2)][nodes.indexOf(node2)] = 0;
            }
        }
    }
    
    private ArrayList<String> PossiblePaths(ArrayList<String> paths, ArrayList<String> nodes){
        
        ArrayList<String> possible = new ArrayList<String>();
        StringBuilder build = new StringBuilder();
        for (int i=1; i<nodes.size(); i++){
            build.append(nodes.get(i));
        }
        //possible.add(build.toString());
        ArrayList<String> permutations = PossibleCombinations("", build.toString());
        //System.out.println("*"+Reverse(permutations.get(0)));
        for (int i=0; i<permutations.size(); i++){
        for (int j=i; j<permutations.size(); j++){
            String temp = Reverse(permutations.get(i));
            //System.out.println("="+permutations.get(j));
            //System.out.println("&"+temp);
            //System.out.println("^"+permutations.get(i));
            if ( !permutations.get(i).equals(permutations.get(j)) && temp.equals(permutations.get(j))){
                //possible.add(startNode+permutations.get(j)+startNode);
                //System.out.println("#" + permutations.get(j));
                permutations.remove(j);
            }
        }
        }
        for (int i=0; i<permutations.size(); i++){
            possible.add(startNode+permutations.get(i)+startNode);
        }
        
            
        return possible;
    }
    
    private String Reverse(String word){
        StringBuilder build = new StringBuilder();
        for (int i=word.length()-1; i>=0; i--){
            build.append(word.substring(i,i+1));
        }
        
        return build.toString();
    }
    
    private ArrayList<String> PossibleCombinations(String newWord, String word){
        ArrayList<String> combinations = new ArrayList<String>();
        int length = word.length();
        if (length == 0){
            combinations.add(newWord);
            //System.out.println(newWord);
        }
        else{
            for (int i=0; i<length; i++){
                combinations.addAll(PossibleCombinations(newWord+ word.charAt(i),word.substring(0,i)+word.substring(i+1)));
            }
        }
        return combinations;
    }
    
    private Integer CalculatePaths(String path, ArrayList<String> nodes){
        int tempPath = 0;
        for (int i=0; i<path.length()-1; i++){
            tempPath += adjacencyMatrix[nodes.indexOf(path.substring(i,i+1))][nodes.indexOf(path.substring(i+1,i+2))];
        }
        //System.out.println(tempPath);
        return tempPath;
    }
    
    public Integer[][] getMatrix(){
        return adjacencyMatrix;
    }
    
    private void ShortestPath(ArrayList<String> nodes, ArrayList<String> paths){
        ArrayList<String> path = PossiblePaths(paths, nodes);
        /*for (int i=0; i<path.size();i++){
            System.out.println(path.get(i));
        }*/
        int pathLengths = CalculatePaths(path.get(0), nodes);
        int shortest = 0;
        for (int i=1; i<path.size();i++){
            if (pathLengths > CalculatePaths(path.get(i), nodes)){
                pathLengths = CalculatePaths(path.get(i), nodes);
                shortest = i;
            }
        }
        //System.out.println(path.get(shortest)+": "+Integer.toString(pathLengths));
        shortestPath = path.get(shortest)+": "+Integer.toString(pathLengths);
    }
    
    public String getShortestPath(ArrayList<String> nodes, ArrayList<String> paths){
        ShortestPath(nodes,paths);
        return shortestPath;
    }

}
