
import java.util.ArrayList;


/**
 *
 * @author amy
 */
public class ImprovedPathFinder extends PathFinder{
    
    private ArrayList<String> queue = new ArrayList<String>();
    
    ImprovedPathFinder(ArrayList<String> paths, ArrayList<String> nodes){
        super(paths, nodes);
    }
    
    private String nextNode(ArrayList<String> nodes, Integer[][] matrix, String path, String fullPath){
        int index = nodes.indexOf(path);
        int shortestIndex = index;
        
        for (int i=0; i<nodes.size(); i++){
            if (! fullPath.contains(nodes.get(i))){
		if (shortestIndex == index){
			shortestIndex = i;
		}		
		else if (matrix[index][i] < matrix[index][shortestIndex]){
			shortestIndex = i;
		}
		else if (matrix[index][i] == matrix[index][shortestIndex]){
			queue.add(nodes.get(i));
                        queue.add(nodes.get(shortestIndex));
		}
            }
        }


        if (! queue.isEmpty()){
                return "";
        }
        else{
                return nodes.get(shortestIndex);
        }
    }
    
    public String ShortestPath(ArrayList<String> nodes, String startNode){
        Integer[][] matrix = super.getMatrix();
        String path = startNode, next, previous;
        next = nextNode(nodes, matrix, startNode, path);
        previous = startNode; // reassign later
        //path += next;
        while (path.length() < nodes.size()){
            if (!queue.isEmpty()){
                int i = 0;
                while (i < queue.size()-1){
                    String node1 = nextNode(nodes, matrix, queue.get(i), path);
                    String node2 = nextNode(nodes, matrix, queue.get(i+1), path);
                    if (matrix[nodes.indexOf(previous)][nodes.indexOf(node1)] < matrix[nodes.indexOf(previous)][nodes.indexOf(node2)]){
                        next = node1;
                    }
                    else if (matrix[nodes.indexOf(previous)][nodes.indexOf(node1)] > matrix[nodes.indexOf(previous)][nodes.indexOf(node2)]){
                        next = node2;
                    }
                }
                queue.clear();
                path += previous;
            }
            else{
                previous = next;
                path += previous;
                next = nextNode(nodes, matrix, previous, path);
            }
            
        }
        path += startNode;
        
        return path;
    }
    
    
    
    private Integer CalculatePaths(String path, ArrayList<String> nodes){
        Integer[][] matrix = super.getMatrix();
        int tempPath = 0;
        for (int i=0; i<path.length()-1; i++){
            tempPath += matrix[nodes.indexOf(path.substring(i,i+1))][nodes.indexOf(path.substring(i+1,i+2))];
        }
        //System.out.println(tempPath);
        return tempPath;
    }
    
    public String getPath(ArrayList<String> nodes, String startNode){
        String path = ShortestPath(nodes,startNode);
        return path + ": " +Integer.toString(CalculatePaths(path, nodes));
    }
}
