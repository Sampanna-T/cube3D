/**
 * @file Cube.java
 * @author Sampanna T (kashisadan16@gmail.com)
 * @brief 
 * Provides declaration of variables used in a Cube
 * 
 * @date 2nd June 2022
 */
 
public interface Cube{
	
	//variables representing faces of the cube
	String FRONT = "FRONT";
	String BACK = "BACK";
	String LEFT = "LFET";
	String RIGHT = "RIGHT";
	String UP = "UP";
	String DOWN = "DOWN";
	
	//variables representing direction of movement of layer/layers of the cube
	String ROTATE = "ROTATE";
	String VERTICAL = "VERTICAL";
	String HORIZONTAL = "HORIZONTAL";

	//varaiables representing all possible movement of layer of the cube
	boolean R_CLK = true;
	boolean R_ANTICLK = false;
	boolean H_RIGHT = true;
	boolean H_LEFT = false;
	boolean V_UP = true;
	boolean V_DOWN = false;
	
	/**
	* @brief 
	* initializes the colors of the Cube by taking input from
	* 
	* @return void 
	*/
	public abstract void setRubiksCube();
	

	/**
	* @brief 
	* displays colors present in the cube based on type
	*
	* @param type
	* type = FACE_WISE then color is displayed face wise 
	* type = NODE_WISE then color is displayed node wise
	*
	* @return void 
	*/
	public abstract void display(boolean type);

		
	/**
	* @brief 
	* returns the array of string indicating the location of given Node index
	*
	* @param i
	* @param j
	* @param k
	* i,j,k represents Node index of 3D Cube
	*
	* @return String[] 
	*/
	public abstract String[] getLocation(int i, int j, int k);


	/**
	* @brief 
	* returns the rotate operation performed in String format
	*
	* @param i
	* @param direction
	* i represents the layer of the cube to be rotated
	* direction = R_CLK rotates ith layer of cube in clkwise direction
	* direction = R_ANTICLK rotates ith layer of cube in anticlkwise direction
	*
	* @return String 
	*/
	public abstract String rotate(int i, boolean direction);


	/**
	* @brief 
	* returns the horizontal operation performed in String format
	*
	* @param j
	* @param direction
	* j represents the layer of the cube to be moved horizontally
	* direction = H_RIGHT moves jth layer of cube in the right direction
	* direction = H_LEFT moves jth layer of cube in the left direction
	*
	* @return String 
	*/
	public abstract String horizontal(int j, boolean direction);

	
	/**
	* @brief 
	* returns the vertical operation performed in String format
	*
	* @param k
	* @param direction
	* k represents the layer of the cube to be moved vertically
	* direction = V_UP moves kth layer of cube in the upward direction
	* direction = V_DOWN moves kth layer of cube in the downward direction
	*
	* @return String 
	*/
	public abstract String vertical(int k, boolean direction);


	/**
	* @brief 
	* returns the circleHorizontal operation performed in String format
	*
	* @param direction
	* direction = H_RIGHT moves the entire cube towards right direction
	* direction = H_LEFT moves the entire cube towards left direction
	*
	* @return String 
	*/
	public abstract String circleHorizontal(boolean direction);
	

	/**
	* @brief 
	* returns the circleHorizontal operation performed in String format
	*
	* @param direction
	* direction = V_UP moves the entire cube towards upward direction
	* direction = V_DOWN moves the entire cube towards downward direction
	*
	* @return String 
	*/
	public abstract String circleVertial(boolean direction);

	
	/**
	* @brief 
	* returns the color of given index of 3D cube in String format
	*
	* @param i
	* @param j
	* @param k
	* @param index
	* i,j,k represents the index the Node in the 3D cube
	* index represents the index within the Node
	*
	* @return String 
	*/
	public abstract String getColor(int i, int j, int k, int index);

	
	
}