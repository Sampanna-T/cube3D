/**
 * @file Algorithm3X3.java
 * @author Sampanna T (kashisadan16@gmail.com)
 * @brief 
 * Provides methods that performs different algorithm useful to solve 3X3 RubiksCube
 * 
 * @date 24th June 2022
 */
 import javafx.util.Pair;
 import java.util.List;
public class Algorithm3X3{   
	/**
	* @brief 
	* perfoms L algorithm and adds [step to be performed, RubiksCube state] pair to the solution list
	* @return void 
	*/
    public static void L(RubiksCube3X3 cube3X3, List<Pair<String,String>> solution){
       
        String move = "";
            move = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.downHorizontal(RubiksCube3X3.H_LEFT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rightVertical(RubiksCube3X3.V_UP);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));
    }
    
        
	/**
	* @brief 
	* perfoms layer2 algorithm and adds [step to be performed, RubiksCube state] pair to the solution list
	* @return void 
	*/
    public static void layer2L(RubiksCube3X3 cube3X3, List<Pair<String,String>> solution){
    
        String move = "";
            move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rightVertical(RubiksCube3X3.V_UP);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rotateFront(RubiksCube3X3.R_ANTICLK);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rotateFront(RubiksCube3X3.R_CLK);
            solution.add(new Pair(move,cube3X3.toString()));
    }

    	
    /**
	* @brief 
	* perfoms layer2R algorithm and adds [step to be performed, RubiksCube state] pair to the solution list
	* @return void 
	*/
    public static void layer2R(RubiksCube3X3 cube3X3, List<Pair<String,String>> solution){
    
        String move = "";
            move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.leftVertical(RubiksCube3X3.V_UP);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.leftVertical(RubiksCube3X3.V_DOWN);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rotateFront(RubiksCube3X3.R_CLK);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rotateFront(RubiksCube3X3.R_ANTICLK);
            solution.add(new Pair(move,cube3X3.toString()));
    }

    
	/**
	* @brief 
	* perfoms twistFront algorithm and adds [step to be performed, RubiksCube state] pair to the solution list
	* @return void 
	*/
    public static void twistFront(RubiksCube3X3 cube3X3, List<Pair<String,String>> solution){

        String move = "";
            move = cube3X3.rotateFront(RubiksCube3X3.R_CLK);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rightVertical(RubiksCube3X3.V_UP);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rotateFront(RubiksCube3X3.R_ANTICLK);
            solution.add(new Pair(move,cube3X3.toString()));
    }


	/**
	* @brief 
	* perfoms upTwist algorithm and adds [step to be performed, RubiksCube state] pair to the solution list
	* @return void 
	*/
    public static void upTwist(RubiksCube3X3 cube3X3, List<Pair<String,String>> solution){

        String move = "";
            move = cube3X3.rightVertical(RubiksCube3X3.V_UP);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rightVertical(RubiksCube3X3.V_UP);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
            solution.add(new Pair(move,cube3X3.toString()));
    }

    
	/**
	* @brief 
	* perfoms corner algorithm and adds [step to be performed, RubiksCube state] pair to the solution list
	* @return void 
	*/
    public static void corner(RubiksCube3X3 cube3X3, List<Pair<String,String>> solution){
        
        String move = "";

            move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rightVertical(RubiksCube3X3.V_UP);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.leftVertical(RubiksCube3X3.V_UP);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.leftVertical(RubiksCube3X3.V_DOWN);
            solution.add(new Pair(move,cube3X3.toString()));

    }    
    
}
