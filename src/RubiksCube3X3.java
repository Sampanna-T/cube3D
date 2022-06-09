/**
 * @file Cube.java
 * @author Sampanna T (kashisadan16@gmail.com)
 * @brief 
 * Provides complete implementation of RubiksCube class to perform all rubiks cube moves
 * 
 * @date 8th June 2022
 */
import java.util.Scanner;

public class RubiksCube3X3 extends RubiksCube{

    private Node piece[][][];
    
    public RubiksCube3X3(){
        super(3);
        piece = new Node[3][3][3];
        setRubiksCube();
    }


	/**
	* @brief 
	* initializes the colors of the Cube by taking input from user
	* 
	* @return void 
	*/
	public void setRubiksCube(){
        for(int i = 0; i < getDimension(); i++){
            for(int j = 0; j < getDimension(); j++){
                for(int k = 0; k < getDimension(); k++){
                    int count = Node.getCount(i,j,k,getDimension());
                    piece[i][j][k] = new Node(count);
                }
            }
        }
        super.setRubiksCube();
    }
	

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
	public void display(boolean type){
        super.display(type);
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
	public String getColor(int i, int j, int k, int subindex){
        return piece[i][j][k].getColor(subindex);
    }


	/**
	* @brief 
	* returns the color of given index of 3D cube in String format
	*
	* @param i
	* @param j
	* @param k
	* @param index
	* @param color
	* i,j,k represents the index the Node in the 3D cube
	* index represents the index within the Node
	* color is the color to be set
	*
	* @return void 
	*/
	public void setColor(int i, int j, int k, int subindex, String color){
        piece[i][j][k].setColor(subindex,color);
    }


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
	public String rotate(int i, boolean direction){
        return null;
    }


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
	public String horizontal(int j, boolean direction){
        return null;
    }

	
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
	public String vertical(int k, boolean direction){
        return null;
    }


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
	public String circleHorizontal(boolean direction){
        return null;
    }
	

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
	public String circleVertical(boolean direction){
        return null;
    }	

}