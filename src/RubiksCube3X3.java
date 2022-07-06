/**
 * @file RubiksCube3X3.java
 * @author Sampanna T (kashisadan16@gmail.com)
 * @brief 
 * Provides complete implementation of RubiksCube class to perform all rubiks cube moves
 * 
 * @date 8th June 2022
 */

public class RubiksCube3X3 extends RubiksCube{

	/**
	* @brief 
	* creates all the nodes of the RubiksCube & sets the colors based on user input 
	* @return void 
	*/
    public RubiksCube3X3()throws Exception{
        super(3);
        super.setRubiksCube(USER_INPUT,null);
    }


	/**
	* @brief 
	* creates all the nodes of the RubiksCube & sets the colors from space separated String 
	* @param colorInput
	* colorInput must hold all the colors of RubiksCube
	* @return void 
	*/
	public RubiksCube3X3(String colorInput)throws Exception{
		super(3);
        super.setRubiksCube(STRING_INPUT,colorInput);
	}


	/**
	* @brief 
	* returns the rotate operation performed in String format
	* @param direction
	* direction = R_CLK rotates Front layer of cube in clkwise direction
	* direction = R_ANTICLK rotates Front layer of cube in anticlkwise direction
	* @return String 
	*/
	public String rotateFront(boolean direction){
		int i = 0;
		return super.rotate(i,direction)+" "+LAYER+(i+1);
	}


	/**
	* @brief 
	* returns the rotate operation performed in String format
	* @param direction
	* direction = R_CLK rotates Midddle layer of cube in clkwise direction
	* direction = R_ANTICLK rotates Middle layer of cube in anticlkwise direction
	* @return String 
	*/
	public String rotateMiddle(boolean direction){		
		int i = 1;
		return super.rotate(i,direction)+" "+LAYER+(i+1);
	}


	/**
	* @brief 
	* returns the rotate operation performed in String format
	* @param direction
	* direction = R_CLK rotates Back layer of cube in clkwise direction
	* direction = R_ANTICLK rotates Back layer of cube in anticlkwise direction
	* @return String 
	*/
	public String rotateBack(boolean direction){		
		int i = 2;
		return super.rotate(i,direction)+" "+LAYER+(i+1);
	}

	/**
	* @brief 
	* returns the horizontal operation performed in String format
	* @param direction
	* direction = H_RIGHT moves Up layer of cube in the right direction
	* direction = H_LEFT moves Up layer of cube in the left direction
	* @return String 
	*/
	public String upHorizontal(boolean direction){
		int j = 0;
		return super.horizontal(j,direction)+" "+LAYER+(j+1);
    }


	/**
	* @brief 
	* returns the horizontal operation performed in String format
	* @param direction
	* direction = H_RIGHT moves Middle layer of cube in the right direction
	* direction = H_LEFT moves Middle layer of cube in the left direction
	* @return String 
	*/
	public String middleHorizontal(boolean direction){
		int j = 1;
		return super.horizontal(j,direction)+" "+LAYER+(j+1);
    }


	/**
	* @brief 
	* returns the horizontal operation performed in String format
	* @param direction
	* direction = H_RIGHT moves Down layer of cube in the right direction
	* direction = H_LEFT moves Down layer of cube in the left direction
	* @return String 
	*/
	public String downHorizontal(boolean direction){
		int j = 2;
		return super.horizontal(j,direction)+" "+LAYER+(j+1);
    }

	
	/**
	* @brief 
	* returns the vertical operation performed in String format
	* @param direction
	* direction = V_UP moves Left layer of cube in the upward direction
	* direction = V_DOWN moves Left layer of cube in the downward direction
	* @return String 
	*/
	public String leftVertical(boolean direction){
		int k = 0;
		return super.vertical(k,direction)+" "+LAYER+(k+1);
    }


	/**
	* @brief 
	* returns the vertical operation performed in String format
	* @param direction
	* direction = V_UP moves Middle layer of cube in the upward direction
	* direction = V_DOWN moves Middle layer of cube in the downward direction
	* @return String 
	*/
	public String middleVertical(boolean direction){
		int k = 1;
		return super.vertical(k,direction)+" "+LAYER+(k+1);
    }


	/**
	* @brief 
	* returns the vertical operation performed in String format
	* @param direction
	* direction = V_UP moves Right layer of cube in the upward direction
	* direction = V_DOWN moves Right layer of cube in the downward direction
	* @return String 
	*/
	public String rightVertical(boolean direction){
		int k = 2;
		return super.vertical(k,direction)+" "+LAYER+(k+1);
    }


	/**
	* @brief 
	* returns the circleHorizontal operation performed in String format
	* @param direction
	* direction = H_RIGHT moves the entire cube towards right direction
	* direction = H_LEFT moves the entire cube towards left direction
	* @return String 
	*/
	public String circleHorizontal(boolean direction){
		return super.circleHorizontal(direction);
    }
	

	/**
	* @brief 
	* returns the circleVertical operation performed in String format
	* @param direction
	* direction = V_UP moves the entire cube towards upward direction
	* direction = V_DOWN moves the entire cube towards downward direction
	* @return String 
	*/
	public String circleVertical(boolean direction){
		return super.circleVertical(direction);
    }	

		
	/**
	* @brief 
	* returns the circleRotate operation performed in String format
	* @param direction
	* direction = R_CLK moves the entire cube in clk direction
	* direction = R_ANTICLK moves the entire cube in anticlk direction
	* @return String 
	*/
	public String circleRotate(boolean direction){
		return super.circleRotate(direction);
    }

}