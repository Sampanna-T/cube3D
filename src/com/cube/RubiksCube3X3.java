package com.cube;

/**
 * Provides implementation of RubiksCube class to 
 * perform all operations on 3X3 RubiksCube
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 8th June 2022
 * @since JDK 10.0.2
 */
public class RubiksCube3X3 extends RubiksCube{

	/**
	* creates all the nodes of the RubiksCube and sets the colors based on user input 
	* @throws Exception 
	* RubiksCube3X3 couldn't be initialized due to invalid user input provided 
	*/
    public RubiksCube3X3()throws Exception{
        super(3);
        super.setRubiksCube(USER_INPUT,null);
    }


	/**
	* initializes the RubiksCube with colors given in colorInput
	* @param colorInput
	* holds all the colors of RubiksCube in String format
	* @throws Exception
	* RubiksCube3X3 couldn't be initialized due to invalid colorInput provided 
	*/
	public RubiksCube3X3(String colorInput)throws Exception{
		super(3);
        super.setRubiksCube(STRING_INPUT,colorInput);
	}


	/**
	* returns the rotate operation performed in String format
	* @param direction
	* for directions R_CLK,R_ANTICLK rotation is performed on Front layer of the cube in
	* clkwise,anticlkwise direction respectively
	* @return String 
	*/
	public String rotateFront(boolean direction){
		int i = 0;
		return super.rotate(i,direction)+" "+LAYER+(i+1);
	}


	/**
	* returns the rotate operation performed in String format
	* @param direction
	* for directions R_CLK,R_ANTICLK rotation is performed on Middle layer of the cube in 
	* clkwise,anticlkwise direction respectively 
	* @return String 
	*/
	public String rotateMiddle(boolean direction){		
		int i = 1;
		return super.rotate(i,direction)+" "+LAYER+(i+1);
	}


	/**
	* returns the rotate operation performed in String format
	* @param direction
	* for directions R_CLK,R_ANTICLK rotation is performed on Back layer of cube in
	* clkwise,anticlkwise direction respectively 
	* @return String 
	*/
	public String rotateBack(boolean direction){		
		int i = 2;
		return super.rotate(i,direction)+" "+LAYER+(i+1);
	}


	/**
	* returns the horizontal operation performed in String format
	* @param direction
	* for directions H_RIGHT,H_LEFT move will be performed on Up layer of cube in 
	* right,left direction respectively
	* @return String 
	*/
	public String upHorizontal(boolean direction){
		int j = 0;
		return super.horizontal(j,direction)+" "+LAYER+(j+1);
    }


	/**
	* returns the horizontal operation performed in String format
	* @param direction
	* for directions H_RIGHT,H_LEFT move will be performed on Middle layer of cube in
	* right,left direction respectively
	* @return String 
	*/
	public String middleHorizontal(boolean direction){
		int j = 1;
		return super.horizontal(j,direction)+" "+LAYER+(j+1);
    }


	/**
	* returns the horizontal operation performed in String format
	* @param direction
	* for directions H_RIGHT,H_LEFT move will be performed on Down layer of cube in 
	* right,left direction resectively
	* @return String 
	*/
	public String downHorizontal(boolean direction){
		int j = 2;
		return super.horizontal(j,direction)+" "+LAYER+(j+1);
    }

	
	/**
	* returns the vertical operation performed in String format
	* @param direction
	* for directions V_UP,V_DOWN move will be performed on Left layer of cube in
	* up,down direction respectively
	* @return String 
	*/
	public String leftVertical(boolean direction){
		int k = 0;
		return super.vertical(k,direction)+" "+LAYER+(k+1);
    }


	/**
	* returns the vertical operation performed in String format
	* @param direction
	* for directions V_UP,V_DOWN move will be performed on Middle layer of cube in 
	* up,down direction respectively
	* @return String 
	*/
	public String middleVertical(boolean direction){
		int k = 1;
		return super.vertical(k,direction)+" "+LAYER+(k+1);
    }


	/**
	* returns the vertical operation performed in String format
	* @param direction
	* for directions V_UP,V_DOWN move will be performed on Right layer of cube in 
	* up,down direction respectively
	* @return String 
	*/
	public String rightVertical(boolean direction){
		int k = 2;
		return super.vertical(k,direction)+" "+LAYER+(k+1);
    }


	/**
	* returns the circleHorizontal operation performed in String format
	* @param direction
	* for directions H_RIGHT,H_LEFT move will be performed on entire cube 
	* in right,left direction respectively
	* @return String 
	*/
	public String circleHorizontal(boolean direction){
		return super.circleHorizontal(direction);
    }
	

	/**
	* returns the circleVertical operation performed in String format
	* @param direction
	* for directions V_UP,V_DOWN move will be performed on entire cube 
	* in up,down direction respectively
	* @return String 
	*/
	public String circleVertical(boolean direction){
		return super.circleVertical(direction);
    }	

		
	/**
	* returns the circleRotate operation performed in String format
	* @param direction
	* for direction R_CLK,R_ANTICLK move will be performed on entire cube in 
	* clkwise,anticlkwise direction respectively
	* @return String 
	*/
	public String circleRotate(boolean direction){
		return super.circleRotate(direction);
    }

}