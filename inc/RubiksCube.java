/**
 * @file Cube.java
 * @author Sampanna T (kashisadan16@gmail.com)
 * @brief 
 * Provides partial implementation of Cube & allows different dimension cubes to extend it's functionality
 * 
 * @date 2nd June 2022
 */
 
public abstract class RubiksCube implements Cube{
	
	private int dimension;//Represents the dimension of Rubiks Cube

	/**
	* @brief 
	* constructor which initializes the RubiksCube by getting the colors from user
	*
	* @param dimension
	* represents dimension of the RubiksCube
	* 
	*/
	public RubiksCube(int dimension){
		this.dimension = dimension;
	}
	
	/**
	* @brief 
	* returns the dimension of Current RubiksCube
	*
	* @param dimension
	* dimension represents the dimension of RubiksCube
	*
	* @return int 
	*/
	public int getDimension(){
		return dimension;
	}

	
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