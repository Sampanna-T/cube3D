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

	//variables representing if whether testing or not
	public static final boolean STRING_INPUT = true;
	public static final boolean USER_INPUT = false;

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
	* initializes the colors of the Cube based on type & colorInput
	* @param type
	* @param colorInput
	* if type = STRING_INPUT & colorInput is specified RubiksCube is initialized with given color input 
	* if type = USER_INPUT & colorInput is any arbitrary String then RubiksCube is initialized with user input
	*
	* @return void 
	*/
	protected void setRubiksCube(boolean type, String colorInput){
		if(type == STRING_INPUT){
			setCube(type,colorInput);
		}
		else if(type == USER_INPUT){
			setCube(type,colorInput);
		}
	}


	/**
	* @brief 
	* initializes the colors of the Cube based on type & colorInput
	*
	* @param type
	* @param colorInput
	* if type = STRING_INPUT & colorInput is specified RubiksCube is initialized with given color input 
	* if type = USER_INPUT & colorInput is any arbitrary String then RubiksCube is initialized with user input
	*
	* @return void 
	*/
	private void setCube(boolean type,String colorInput){
		Scanner input;
		if(type == STRING_INPUT)
			input = new Scanner(colorInput);
		else if(type == USER_INPUT)
			input = new Scanner(System.in);
		else
			input = null;

		setCube(FRONT,input);
		setCube(LEFT,input);
		setCube(BACK,input);
		setCube(RIGHT,input);
		setCube(UP,input);
		setCube(DOWN,input);
	}


	/**
	* @brief 
	* initializes the colors of a particular face
	* @param face
	* @param input
	* face can be either FRONT,LEFT,BACK,RIGHT,UP OR DOWN... Based on which face to initialize with color
	* that face colors will be initialized
	* input represents reference to Scanner object to take input from either user or already accepted colorInput 
	*
	* @return void 
	*/
	private void setCube(String face, Scanner input){
		int i=-1,j=-1,k=-1;
		switch(face){
			case FRONT : 	i = 0;
							for(j = 0; j < dimension; j++){
								for(k = 0; k < dimension; k++){
									String color = input.next().toUpperCase();
									setFrontColor(i,j,k,color);
								}
							}
							break;

			case LEFT : 	k = 0;
							for(j = 0; j < dimension; j++){
								for(i = dimension-1; i >= 0; i--){
									String color = input.next().toUpperCase();
									setLeftColor(i,j,k,color);
								}
							}
							break;
			
			case BACK : 	i = dimension-1;
							for(j = 0; j < dimension; j++){
								for(k = dimension-1; k >= 0; k--){
									String color = input.next().toUpperCase();
									setBackColor(i,j,k,color);
								}
							}
							break;

			case RIGHT : 	k = dimension-1;
							for(j = 0; j < dimension; j++){
								for(i = 0; i < dimension; i++){
									String color = input.next().toUpperCase();
									setRightColor(i,j,k,color);
								}
							}
							break;

			case UP :		j = 0;
							for(i = dimension-1; i >= 0; i--){
								for(k = 0; k < dimension; k++){
									String color = input.next().toUpperCase();
									setUpColor(i,j,k,color);
								}
							}
							break;

			case DOWN :     j = dimension-1;
							for(i = 0; i < dimension; i++){
								for(k = 0; k < dimension; k++){
									String color = input.next().toUpperCase();
									setDownColor(i,j,k,color);
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
			displayNodeWise();
		}
		else if(type == FACE_WISE){
			displayFaceWise();
		}
		
	}

	/**
	* @brief 
	* displays colors present in the cube Node wise
	*
	* @return void 
	*/
	private void displayNodeWise(){
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
				System.out.println("---------------------------------------");
				}
			System.out.println("*******************************************");
	}

	/**
	* @brief 
	* displays colors present in the cube Face wise
	*
	* @return void 
	*/
	private void displayFaceWise(){
		int i=-1,j=-1,k=-1;

			System.out.println("*******************************************");
			System.out.println("FRONT FACE COLORS - ");
			i = 0;
				for(j = 0; j < dimension; j++){
					for(k = 0; k < dimension; k++){
						System.out.print(getFrontColor(i,j,k)+" ");
					}
					System.out.println();
				}
			System.out.println("---------------------------------------");

			System.out.println("LEFT FACE COLORS - ");
			k = 0;
				for(j = 0; j < dimension; j++){
					for(i = dimension-1; i >= 0; i--){
						System.out.print(getLeftColor(i,j,k)+" ");
					}
					System.out.println();
				}
			System.out.println("---------------------------------------");
			
			System.out.println("BACK FACE COLORS - ");
			i = dimension-1;
				for(j = 0; j < dimension; j++){
					for(k = dimension-1; k >= 0; k--){
						System.out.print(getBackColor(i,j,k)+" ");
					}
					System.out.println();
				}
			System.out.println("---------------------------------------");
					

			System.out.println("RIGHT FACE COLORS - ");
			k = dimension-1;
				for(j = 0; j < dimension; j++){
					for(i = 0; i < dimension; i++){
						System.out.print(getRightColor(i,j,k)+" ");
					}
					System.out.println();
				}
			System.out.println("---------------------------------------");

			System.out.println("UP FACE COLORS - ");
			j = 0;
				for(i = dimension-1; i >= 0; i--){
					for(k = 0; k < dimension; k++){
						System.out.print(getUpColor(i,j,k)+" ");
					}		
					System.out.println();
				}
			System.out.println("---------------------------------------");

			System.out.println("DOWN FACE COLORS - ");
			j = dimension-1;
				for(i = 0; i < dimension; i++){
					for(k = 0; k < dimension; k++){
						System.out.print(getDownColor(i,j,k)+" ");
					}
					System.out.println();
				}	
			System.out.println("---------------------------------------");
			System.out.println("*******************************************");
	}


	/**
	* @brief 
	* returns the color of given index of 3D cube in String format
	*
	* @param i
	* @param j
	* @param k
	* @param index
	* i,j,k represents the index of the Node in the 3D cube
	* index represents the index within the Node
	*
	* @return String 
	*/
	public abstract String getColor(int i, int j, int k, int subindex);


	/**
	* @brief 
	* returns the up color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* i,j,k represents the index of the Node in the 3D cube
	*
	* @return String 
	*/
	public String getUpColor(int i, int j, int k){
		return getUpDownColor(i,j,k,UP);
	}


	/**
	* @brief 
	* returns the down color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* i,j,k represents the index of the Node in the 3D cube
	*
	* @return String 
	*/
	public String getDownColor(int i, int j, int k){
		return getUpDownColor(i,j,k,DOWN);
    }


	/**
	* @brief 
	* returns the Up/down color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k	
	* @param type
	* i,j,k represents the index of the Node in the 3D cube
	* if type = UP up color is returned
	* if type = DOWN down color is returned
	*
	* @return String 
	*/
	private String getUpDownColor(int i, int j, int k, String type){
		
		if(!Node.isIndexValid(i,j,k,dimension))return null;

		if(type.equals(UP) && (j != 0)){
			return null;
		}
		else if(type.equals(DOWN) && (j != dimension-1)){
			return null;
		}
		else{
			if(i == 0 || i == dimension-1)return getColor(i,j,k,1);
			else return getColor(i,j,k,0);
		}
	}

	/**
	* @brief 
	* returns the left color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* i,j,k represents the index of the Node in the 3D cube
	*
	* @return String 
	*/
	public String getLeftColor(int i, int j, int k){
        return getLeftRightColor(i,j,k,LEFT);
    }


	/**
	* @brief 
	* returns the right color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* i,j,k represents the index of the Node in the 3D cube
	*
	* @return String 
	*/
	public String getRightColor(int i, int j, int k){
        return getLeftRightColor(i,j,k,RIGHT);
    }

	
	/**
	* @brief 
	* returns the left/right color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* @param type
	* i,j,k represents the index of the Node in the 3D cube
	* if type = RIGHT the right color is returned
	* if type = LEFT the left color is returned
	*
	* @return String 
	*/
	private String getLeftRightColor(int i, int j, int k, String type){
	
		if(!Node.isIndexValid(i,j,k,dimension))return null;

		if(type.equals(LEFT) && k != 0){
			return null;
		}
		else if(type.equals(RIGHT) && (k != dimension-1)){
			return null;
		}
		else{
			int lastColorIndex = Node.getCount(i,j,k,dimension)-1;
			return getColor(i,j,k,lastColorIndex);
		}
	}
	

	/**
	* @brief 
	* returns the back color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* i,j,k represents the index of the Node in the 3D cube
	*
	* @return String 
	*/
	public String getBackColor(int i, int j, int k){
        return getFrontBackColor(i,j,k,BACK);
    }


	/**
	* @brief 
	* returns the right color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* i,j,k represents the index of the Node in the 3D cube
	*
	* @return String 
	*/
	public String getFrontColor(int i, int j, int k){
        return getFrontBackColor(i,j,k,FRONT);
    }

	
	/**
	* @brief 
	* returns the front/back color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* @param type
	* i,j,k represents the index of the Node in the 3D cube
	* if type = FRONT the front color is returned
	* if type = BACK the back color is returned
	*
	* @return String 
	*/
	private String getFrontBackColor(int i, int j, int k, String type){

		if(!Node.isIndexValid(i,j,k,dimension))return null; 

		if(type.equals(FRONT) && (i != 0)){
			return null;
		}
		else if(type.equals(BACK) && (i != dimension-1)){
			return null;
		}
		else{
			return getColor(i,j,k,0);
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
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* index represents the index within the Node
	* color represents the color to be set
	*
	* @return void 
	*/
	public abstract void setColor(int i, int j, int k, int subindex, String color);

	/**
	* @brief 
	* sets the up color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* color represents the color to be set
	*
	* @return void 
	*/
	public void setUpColor(int i, int j, int k, String color){
		setUpDownColor(i,j,k,UP,color);
	}


	/**
	* @brief 
	* sets the down color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* color represents the color to be set
	*
	* @return void
	*/
	public void setDownColor(int i, int j, int k, String color){
		setUpDownColor(i,j,k,DOWN,color);
    }


	/**
	* @brief 
	* sets the Up/down color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k	
	* @param type
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* if type = UP up color is returned
	* if type = DOWN down color is returned
	* color represents the color to be set
	*
	* @return void
	*/
	private void setUpDownColor(int i, int j, int k, String type, String color){
		
		if(!Node.isIndexValid(i,j,k,dimension))return;

		if(type.equals(UP) && (j != 0)){
			return;
		}
		else if(type.equals(DOWN) && (j != dimension-1)){
			return;
		}
		else{
			if(i == 0 || i == dimension-1)setColor(i,j,k,1,color);
			else setColor(i,j,k,0,color);
		}
	}

	/**
	* @brief 
	* sets the left color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* color represents the color to be set
	*
	* @return void
	*/
	public void setLeftColor(int i, int j, int k, String color){
        setLeftRightColor(i,j,k,LEFT,color);
    }


	/**
	* @brief 
	* sets the right color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* color represents the color to be set
	*
	* @return void
	*/
	public void setRightColor(int i, int j, int k, String color){
        setLeftRightColor(i,j,k,RIGHT,color);
    }

	
	/**
	* @brief 
	* sets the left/right color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* @param type
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* if type = RIGHT the right color is returned
	* if type = LEFT the left color is returned
	* color represents the color to be set
	*
	* @return void
	*/
	private void setLeftRightColor(int i, int j, int k, String type, String color){
	
		if(!Node.isIndexValid(i,j,k,dimension))return;

		if(type.equals(LEFT) && k != 0){
			return;
		}
		else if(type.equals(RIGHT) && (k != dimension-1)){
			return;
		}
		else{
			int lastColorIndex = Node.getCount(i,j,k,dimension)-1;
			setColor(i,j,k,lastColorIndex,color);
		}
	}
	

	/**
	* @brief 
	* sets the back color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* color represents the color to be set
	*
	* @return void
	*/
	public void setBackColor(int i, int j, int k, String color){
        setFrontBackColor(i,j,k,BACK,color);
    }


	/**
	* @brief 
	* sets the right color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* color represents the color to be set
	*
	* @return void
	*/
	public void setFrontColor(int i, int j, int k, String color){
        setFrontBackColor(i,j,k,FRONT,color);
    }

	
	/**
	* @brief 
	* sets the front/back color of 3D cube in String format for given Node
	*
	* @param i
	* @param j
	* @param k
	* @param type
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* if type = FRONT the front color is returned
	* if type = BACK the back color is returned
	* color represents the color to be set
	*
	* @return void
	*/
	private void setFrontBackColor(int i, int j, int k, String type, String color){

		if(!Node.isIndexValid(i,j,k,dimension))return; 

		if(type.equals(FRONT) && (i != 0)){
			return;
		}
		else if(type.equals(BACK) && (i != dimension-1)){
			return;
		}
		else{
			setColor(i,j,k,0,color);
		}
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