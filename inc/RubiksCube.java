/**
 * @file Cube.java
 * @author Sampanna T (kashisadan16@gmail.com)
 * @brief 
 * Provides partial implementation of Cube & allows different dimension cubes to extend it's functionality
 * 
 * @date 2nd June 2022
 */
 
public abstract class RubiksCube implements Cube{
	
	private Node piece[][][];//Used to hold Nodes in 3D array
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
		piece = new Node[dimension][dimension][dimension];
		setRubiksCube();
	}
	
	/**
	* @brief 
	* returns the color of given index of 3D cube in String format
	*
	* @param i
	* @param j
	* @param k
	* @param index
	* @param String
	* i,j,k represents the index the Node in the 3D cube
	* index represents the index within the Node
	* color represents the color to be set at given index of the Node
	*
	* @return String 
	*/
	protected abstract boolean setColor(int i, int j, int k, int index, String color);
}