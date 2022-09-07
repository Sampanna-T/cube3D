package src.cube3X3;


/**
 * Returns RubiksCube object for both string input and user input
 * 
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 5th August 2022
 * @since JDK 10.0.2
 */
public interface RubiksCube3X3Factory {

    /**
     * Returns RubiksCube3X3 Object for userInput
     * @return RubiksCube3X3
     */
    public static RubiksCube3X3 getInstance(){
        try{
            RubiksCube3X3 cube3X3 = new RubiksCube3X3();
            return cube3X3;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns RubiksCube3X3 Object for String input
     * @param color
     * represents all the colors of the RubiksCube
     * @return RubiksCube3X3
     */
    public static RubiksCube3X3 getInstance(String color){
        try{
            RubiksCube3X3 cube3X3 = new RubiksCube3X3(color);
            return cube3X3;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}