

/**
 * 
 * 
 * @author Amy Brodie
 * @version 01/03/2015
 */

import java.util.*;

public class TreeUtils {
    
    private int level = 0, height1, height2;
    
    public boolean similar(BinaryTreeNode node1, BinaryTreeNode node2){
        if (level == 0){
            height1 = node1.getHeight();
            height2 = node2.getHeight();
        }
        
        if (height1 == height2){
            if (level == 0 && levelZeroNodeList(node1).equals("") && levelZeroNodeList(node2).equals("")){
                return true;
            }
            else if (level == 0 && ((!levelZeroNodeList(node1).equals("") && levelZeroNodeList(node2).equals("")) || (levelZeroNodeList(node1).equals("") && !levelZeroNodeList(node2).equals("")))){
               return false;   
            }
            else{
                level += 1;
                if (node1.hasLeft() && node2.hasLeft()){
                    if (height1-1 == level && (!node1.hasRight() && !node2.hasRight()) || (node1.hasRight() && node2.hasRight())){
                       return true;    
                    }
                    else if (height1-1 != level){
                       return similar(node1.getLeft(), node2.getLeft());
                    }
                    else{
                       return false; 
                    }
                }
                else if (node1.hasRight() && node2.hasRight()) {
                    if (height1-1 == level && (!node1.hasLeft() && !node2.hasLeft()) || (node1.hasLeft() && node2.hasLeft())){
                       return true;    
                    }
                    else if (height1-1 != level){
                       return similar(node1.getRight(), node2.getRight());
                    }
                    else{
                       return false; 
                    }    
                }
            }
        }
        else{
            return false;
        }
    }
    
    public String levelZeroNodeList(BinaryTreeNode temp){
        if (temp.getHeight() == 0){
            return "";
        }
        else{
            return Integer.toString(temp.getItem());
        }
           
    }
    
    public String levelNplusOneNodeList(BinaryTreeNode temp){
        if (temp.hasLeft() && temp.hasRight()){
            return temp.getLeft() + "#" + temp.getRight() + "#";
        }
        else if (temp.hasLeft()){
            return temp.getLeft() + "#null#";
        }
        else if (temp.hasRight()){
            return "null#" + temp.getRight() + "#";
        }
        else{
            return "";
        }
        
    }
    
}
