import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Contains essential methods to validate the RubiksCube of any dimension
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 5th July 2022
 * @since JDK 10.0.2
 */
public interface ValidateCube{

	/**
	* returns true if colors within all the nodes of the RubiksCube are valid
	* @param cube
	* Reference to RubiksCube object whose Nodes have to be checked
    * @throws NullPointerException
    * if the RubiksCube is pointing to null
    * @throws Exception
    * if the number of colors received for a given Node is invalid
	* @return boolean
	*/
    static boolean isNodeValid(RubiksCube cube)throws Exception{
        int dimension = cube.getDimension();
        if(cube == null)
            throw new NullPointerException("RUBIKSCUBE NOT INITIALIZED");

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


	//checks if there are duplicates in colors[] or not
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


	/**
	* returns true if all the colors of the RubiksCube are valid
	* @param cube
	* Reference to RubiksCube object whose Nodes have to be checked
    * @throws NullPointerException
    * if the RubiksCube is pointing to null
    * @throws Exception
    * if the number of colors received is greater than the actual number of colors of RubiksCube
	* @return boolean
	*/
    static boolean isColorValid(RubiksCube cube)throws Exception{
        if(cube == null)
            throw new NullPointerException("RubiksCube not initialized");

        String colors[] = (cube.toString()).split(" ");
        int dimension = cube.getDimension();
        int colorCount = (dimension*dimension*RubiksCube.FACE_COUNT);
        if(colors.length != colorCount)
            throw new Exception("INVALID COLOR COUNT");

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