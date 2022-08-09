package src.cube;

/**
 * returns Exception for any unlikely operations on RubiksCube 
 * 
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 31st July 2022
 * @since JDK 10.0.2
 */
public interface RubiksCubeException {
    
    /**
     * Indicates that string input given to create RubiksCube is Invalid 
     * @return Exception
     */
    public static Exception colorStringInvalid(){
        return new Exception("COLOR STRING INVALID");
    }

    /**
     * Indicates that a particular Node is Invalid i.e if there are duplicate colors in a particular node
     * @return Exception
     */
    public static Exception nodeInvalid(){
        return new Exception("NODE INVALID");
    }

    /**
     * Indicates that color input provided by user overall is not possible for a rubikscube
     * @return Exception
     */
    public static Exception colorInputInvalid(){
        return new Exception("COLOR INPUT INVALID");
    }

    /**
     * Indicates that given state of rubiksCube is invalid for example if rubiksCube is pointing to null
     * @return Exception
     */
    public static Exception invalidCube(){
        return new Exception("INVALID RUBIKSCUBE STATE");
    }

    /**
     * Indicates that the given state fo rubiksCube can't be solved
     * @return Exception
     */
    public static Exception unSolvable(){
        return new Exception("RUBIKSCUBE CAN'T BE SOLVED");
    }

}
