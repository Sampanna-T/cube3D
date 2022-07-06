/**
 * @file ValidateCube.java
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @brief 
 * methods to validate the RubiksCube object
 * 
 * @date 5th July 2022
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ValidateCube{

	/**
	* @brief 
	* returns true if all the nodes of the RubiksCube are valid
	* @param cube
	* cube basically holds RubiksCube object whose Nodes have to be checked
	* @return boolean
	*/
    public static boolean isNodeValid(RubiksCube cube)throws Exception{
        int dimension = cube.getDimension();

        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                for(int k = 0; k < dimension; k++){
                    String colors[] = cube.getColors(i,j,k);
                    if(colors.length > RubiksCube.CORNER_COUNT)
                        throw new Exception("NODE SIZE INVALID");
                    
                    if(isDuplicate(colors))
                        return false;
                }
            }
        }

        return true;
    }


	/**
	* @brief 
	* returns true if there are duplicate
	* @param colors
	* checks if there are duplicates in colors[] or not
	* @return boolean
	*/
    private static boolean isDuplicate(String colors[]){
        
        if(colors.length == RubiksCube.CORNER_COUNT){
            if( (colors[0].equals(colors[1])) || (colors[1].equals(colors[2])) || (colors[2].equals(colors[0])))
                return true;
            else
                return false;
        }
        else if(colors.length == RubiksCube.EDGE_COUNT){
            if( colors[0].equals(colors[1]) )
                return true;
            else
                return false;
        }
        else
            return false;
    }


    public static boolean isColorValid(RubiksCube cube)throws Exception{

        String colors[] = (cube.toString()).split(" ");
        int dimension = cube.getDimension();
        int colorCount = (dimension*dimension*RubiksCube.FACE_COUNT);
        if(colors.length != colorCount)throw new Exception("INVALID COLOR COUNT");

        HashMap <String,Integer>result = new HashMap<String,Integer>();

        for(int i = 0; i < colorCount; i++){
            if(result.containsKey(colors[i])){
                int value = result.get(colors[i]);
                result.replace(colors[i],value+1);
            }
            else
                result.put(colors[i],1);
        }

        Iterator itr = result.entrySet().iterator();
        
        while(itr.hasNext()){
            Map.Entry mapPair = (Map.Entry)itr.next();
            int value = (int)mapPair.getValue();
            if(value != dimension*dimension)return false;
        }

        return true;
    }
    
}