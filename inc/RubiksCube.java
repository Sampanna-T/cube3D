/**
 * @file Cube.java
 * @author Sampanna T (kashisadan16@gmail.com)
 * @brief 
 * Provides partial implementation of Cube & allows different dimension cubes to extend it's functionality
 * 
 * @date 2nd June 2022
 */
 import java.util.Scanner;

public abstract class RubiksCube{

	//variables representing faces of the cube
	public static final String FRONT = "FRONT";
	public static final String BACK = "BACK";
	public static final String LEFT = "LFET";
	public static final String RIGHT = "RIGHT";
	public static final String UP = "UP";
	public static final String DOWN = "DOWN";
	
	//variables representing direction of movement of layer/layers of the cube
	public static final String ROTATE = "ROTATE";
	public static final String VERTICAL = "VERTICAL";
	public static final String HORIZONTAL = "HORIZONTAL";

	//varaiables representing way of displaying the colors of Cube
	public static final boolean FACE_WISE = true;
	public static final boolean NODE_WISE = false;

	//varaiables representing all possible movement of layer of the cube
	public static final boolean R_CLK = true;
	public static final boolean R_ANTICLK = false;
	public static final boolean H_RIGHT = true;
	public static final boolean H_LEFT = false;
	public static final boolean V_UP = true;
	public static final boolean V_DOWN = false;
	
	//Represents the dimension of Rubiks Cube
	private int dimension;


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
	* initializes the colors of the Cube by taking input from user
	* 
	* @return void 
	*/
	public void setRubiksCube(){
		Scanner input = new Scanner(System.in);
		setRubiksCube(FRONT,input);
		setRubiksCube(LEFT,input);
		setRubiksCube(BACK,input);
		setRubiksCube(RIGHT,input);
		setRubiksCube(UP,input);
		setRubiksCube(DOWN,input);
	}


	/**
	* @brief 
	* initializes the colors of a particular face
	* 
	* @return void 
	*/
	private void setRubiksCube(String face, Scanner input){
		int i=-1,j=-1,k=-1;
		switch(face){
			case FRONT : 	i = 0;
							for(j = 0; j < dimension; j++){
								for(k = 0; k < dimension; k++){
									String color = input.next().toUpperCase();
									setColor(i,j,k,0,color);
								}
							}
							break;

			case LEFT : 	k = 0;
							for(j = 0; j < dimension; j++){
								for(i = dimension-1; i >= 0; i--){
									String color = input.next().toUpperCase();
									setColor(i,j,k,Node.getCount(i,j,k,dimension)-1,color);
								}
							}
							break;
			
			case BACK : 	i = dimension-1;
							for(j = 0; j < dimension; j++){
								for(k = dimension-1; k >= 0; k--){
									String color = input.next().toUpperCase();
									setColor(i,j,k,0,color);
								}
							}
							break;

			case RIGHT : 	k = dimension-1;
							for(j = 0; j < dimension; j++){
								for(i = 0; i < dimension; i++){
									String color = input.next().toUpperCase();
									setColor(i,j,k,Node.getCount(i,j,k,dimension)-1,color);
								}
							}
							break;

			case UP :		j = 0;
							for(i = dimension-1; i >= 0; i--){
								for(k = 0; k < dimension; k++){
									String color = input.next().toUpperCase();
									int count = Node.getCount(i,j,k,dimension);
									if(count == 1)setColor(i,j,k,0,color);
									else if(count == 2){
										if(i==0 || i==dimension-1)setColor(i,j,k,1,color);
										else setColor(i,j,k,0,color);
									}
									else if(count == 3)setColor(i,j,k,1,color);
								}
							}
							break;

			case DOWN :     j = dimension-1;
							for(i = 0; i < dimension; i++){
								for(k = 0; k < dimension; k++){
									String color = input.next().toUpperCase();
									int count = Node.getCount(i,j,k,dimension);
									if(count == 1)setColor(i,j,k,0,color);	
									else if(count == 2){
										if(i==0 || i==dimension-1)setColor(i,j,k,1,color);
										else setColor(i,j,k,0,color);
									}
									else if(count == 3)setColor(i,j,k,1,color);
								}
							}
							break;
		}	
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
		if(type == NODE_WISE){
			System.out.println("*******************************************");
				for(int i = 0; i < dimension; i++){
					for(int j = 0; j < dimension; j++){
						for(int k = 0; k < dimension; k++){
							int colorSize = Node.getCount(i,j,k,dimension);
							for(int index = 0; index < colorSize; index++){
								System.out.print(getColor(i,j,k,index));
								if(index != colorSize-1)System.out.print(",");
							}	
							System.out.print(" ");
						}
					System.out.println();
					}
				System.out.println("_______________________________________");
				}
			System.out.println("*******************************************");
		}
		else if(type == FACE_WISE){
			;
		}
		
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
	public abstract String getColor(int i, int j, int k, int subindex);


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
	public abstract void setColor(int i, int j, int k, int subindex, String color);


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
	public abstract String circleVertical(boolean direction);	

}