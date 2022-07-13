 import javafx.util.Pair;
 import java.util.List;

/**
 * Provides methods that performs different algorithm useful to solve 3X3 RubiksCube
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 24th June 2022
 * @since JDK 10.0.2
 */
class Algorithm3X3{   

	/**
	* perfoms L algorithm and adds [move to be performed, RubiksCube state] pair to the solution list
    * @param cube3X3
    * Reference to RubiksCube3X3 object
    * @param solution
    * Reference to a list containing [move to be performed, Rubikscube state] pair
	*/
    static void L(RubiksCube3X3 cube3X3, List<Pair<String,String>> solution){ 
        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_DOWN),cube3X3,solution); 
        Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_UP),cube3X3,solution);
        Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);     
    }
    
        
	/**
	* perfoms layer2 algorithm and adds [move to be performed, RubiksCube state] pair to the solution list
    * @param cube3X3
    * Reference to RubiksCube3X3 object
    * @param solution
    * Reference to a list containing [move to be performed, Rubikscube state] pair
	*/
    static void layer2L(RubiksCube3X3 cube3X3, List<Pair<String,String>> solution){
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_UP),cube3X3,solution);
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
        Solve3X3.add(cube3X3.rotateFront(RubiksCube3X3.R_ANTICLK),cube3X3,solution);
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
        Solve3X3.add(cube3X3.rotateFront(RubiksCube3X3.R_CLK),cube3X3,solution);    
    }

    	
    /**
	* perfoms layer2R algorithm and adds [move to be performed, RubiksCube state] pair to the solution list
    * @param cube3X3
    * Reference to RubiksCube3X3 object
    * @param solution
    * Reference to a list containing [move to be performed, Rubikscube state] pair
	*/
    static void layer2R(RubiksCube3X3 cube3X3, List<Pair<String,String>> solution){
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
        Solve3X3.add(cube3X3.leftVertical(RubiksCube3X3.V_UP),cube3X3,solution);
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
        Solve3X3.add(cube3X3.leftVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
        Solve3X3.add(cube3X3.rotateFront(RubiksCube3X3.R_CLK),cube3X3,solution);
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
        Solve3X3.add(cube3X3.rotateFront(RubiksCube3X3.R_ANTICLK),cube3X3,solution);
    }

    
	/**
	* perfoms twistFront algorithm and adds [move to be performed, RubiksCube state] pair to the solution list
    * @param cube3X3
    * Reference to RubiksCube3X3 object
    * @param solution
    * Reference to a list containing [move to be performed, Rubikscube state] pair
	*/
    static void twistFront(RubiksCube3X3 cube3X3, List<Pair<String,String>> solution){
        Solve3X3.add(cube3X3.rotateFront(RubiksCube3X3.R_CLK),cube3X3,solution);
        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_UP),cube3X3,solution);
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
        Solve3X3.add(cube3X3.rotateFront(RubiksCube3X3.R_ANTICLK),cube3X3,solution);
    }


	/**
	* perfoms upTwist algorithm and adds [move to be performed, RubiksCube state] pair to the solution list
    * @param cube3X3
    * Reference to RubiksCube3X3 object
    * @param solution
    * Reference to a list containing [move to be performed, Rubikscube state] pair
	*/
    static void upTwist(RubiksCube3X3 cube3X3, List<Pair<String,String>> solution){
        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_UP),cube3X3,solution);
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_UP),cube3X3,solution);
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);           
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);        
    }

    
	/**
	* perfoms corner algorithm and adds [move to be performed, RubiksCube state] pair to the solution list
    * @param cube3X3
    * Reference to RubiksCube3X3 object
    * @param solution
    * Reference to a list containing [move to be performed, Rubikscube state] pair
	*/
    static void corner(RubiksCube3X3 cube3X3, List<Pair<String,String>> solution){
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_UP),cube3X3,solution);
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
        Solve3X3.add(cube3X3.leftVertical(RubiksCube3X3.V_UP),cube3X3,solution);
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
        Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
        Solve3X3.add(cube3X3.leftVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
    }    
    
}
