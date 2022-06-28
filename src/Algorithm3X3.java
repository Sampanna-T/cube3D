/**
 * @file Algorithm3X3.java
 * @author Sampanna T (kashisadan16@gmail.com)
 * @brief 
 * Provides methods that performs different algorithm useful to solve 3X3 RubiksCube
 * 
 * @date 24th June 2022
 */
public class Algorithm3X3{   
	/**
	* @brief 
	* perfoms L algorithm and returns all moves performed in String array format
	* @return String[] 
	*/
    public static String[] L(RubiksCube3X3 cube3X3){
       
        String movePerformed[] = new String[4];
    
            movePerformed[0] = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
            movePerformed[1] = cube3X3.downHorizontal(RubiksCube3X3.H_LEFT);
            movePerformed[2] = cube3X3.rightVertical(RubiksCube3X3.V_UP);
            movePerformed[3] = cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT);
          
        return movePerformed;
    }
    
        
	/**
	* @brief 
	* perfoms layer2 algorithm and returns all moves performed in String array format
	* @return String[] 
	*/
    public static String[] layer2L(RubiksCube3X3 cube3X3){
    
        String movePerformed[] = new String[8];

            movePerformed[0] = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            movePerformed[1] = cube3X3.rightVertical(RubiksCube3X3.V_UP);
            movePerformed[2] = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
            movePerformed[3] = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
            movePerformed[4] = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
            movePerformed[5] = cube3X3.rotateFront(RubiksCube3X3.R_ANTICLK);
            movePerformed[6] = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            movePerformed[7] = cube3X3.rotateFront(RubiksCube3X3.R_CLK);
        
        return movePerformed;

    }

    	
    /**
	* @brief 
	* perfoms layer2R algorithm and returns all moves performed in String array format
	* @return String[] 
	*/
    public static String[] layer2R(RubiksCube3X3 cube3X3){
    
        String movePerformed[] = new String[8];

            movePerformed[0] = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
            movePerformed[1] = cube3X3.leftVertical(RubiksCube3X3.V_UP);
            movePerformed[2] = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            movePerformed[3] = cube3X3.leftVertical(RubiksCube3X3.V_DOWN);
            movePerformed[4] = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            movePerformed[5] = cube3X3.rotateFront(RubiksCube3X3.R_CLK);
            movePerformed[6] = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
            movePerformed[7] = cube3X3.rotateFront(RubiksCube3X3.R_ANTICLK);
        
        return movePerformed;

    }

    
	/**
	* @brief 
	* perfoms twistFront algorithm and returns all moves performed in String array format
	* @return String[] 
	*/
    public static String[] twistFront(RubiksCube3X3 cube3X3){

        String movePerformed[] = new String[6];

            movePerformed[0] = cube3X3.rotateFront(RubiksCube3X3.R_CLK);
            movePerformed[1] = cube3X3.rightVertical(RubiksCube3X3.V_UP);
            movePerformed[2] = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            movePerformed[3] = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
            movePerformed[4] = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
            movePerformed[5] = cube3X3.rotateFront(RubiksCube3X3.R_ANTICLK);

        return movePerformed;

    }


	/**
	* @brief 
	* perfoms upTwist algorithm and returns all moves performed in String array format
	* @return String[] 
	*/
    public static String[] upTwist(RubiksCube3X3 cube3X3){

        String movePerformed[] = new String[8];

            movePerformed[0] = cube3X3.rightVertical(RubiksCube3X3.V_UP);
            movePerformed[1] = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            movePerformed[2] = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
            movePerformed[3] = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            movePerformed[4] = cube3X3.rightVertical(RubiksCube3X3.V_UP);
            movePerformed[5] = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            movePerformed[6] = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            movePerformed[7] = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);

        return movePerformed;
    }

    
	/**
	* @brief 
	* perfoms corner algorithm and returns all moves performed in String array format
	* @return String[] 
	*/
    public static String[] corner(RubiksCube3X3 cube3X3){
        
        String movePerformed[] = new String[8];

            movePerformed[0] = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            movePerformed[1] = cube3X3.rightVertical(RubiksCube3X3.V_UP);
            movePerformed[2] = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
            movePerformed[3] = cube3X3.leftVertical(RubiksCube3X3.V_UP);
            movePerformed[4] = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
            movePerformed[5] = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
            movePerformed[6] = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
            movePerformed[7] = cube3X3.leftVertical(RubiksCube3X3.V_DOWN);

        return movePerformed;
    }    
    
}
