/**
 * @file RubiksCube.java
 * @author Sampanna T (kashisadan16@gmail.com)
 * @brief 
 * Provides partial implementation of Cube & allows different dimension cubes to extend it's functionality
 * 
 * @date 2nd June 2022
 */
 import java.util.Scanner;

public abstract class RubiksCube{

	//variables representing possible movements of a given layer of the cube
	public static final String FRONT = "FRONT";
	public static final String BACK = "BACK";
	public static final String LEFT = "LFET";
	public static final String RIGHT = "RIGHT";
	public static final String UP = "UP";
	public static final String DOWN = "DOWN";
	public static final String CLKWISE = "CLKWISE";
	public static final String ANTICLK = "ANTI-CLKWISE";
	
	//variables representing operations that can be performed in the cube
	public static final String ROTATE = "ROTATE";
	public static final String VERTICAL = "VERTICAL";
	public static final String HORIZONTAL = "HORIZONTAL";

	//variable to represent layer of the cube
	public static final String LAYER = "LAYER";

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
	
	//Represents the dimension of Rubiks Cube & piece represents the ref to 3D cube
	private int dimension;
	private Node piece[][][];


	/**
	* @brief 
	* constructor which initializes the RubiksCube by getting the colors from user
	* @param dimension
	* represents dimension of the RubiksCube
	*/
	public RubiksCube(int dimension){
		this.dimension = dimension;
		piece = new Node[dimension][dimension][dimension];
		createNodes();
	}


	/**
	* @brief 
	* initializes the colors of the Cube by taking input from user
	* @return void 
	*/
	private void createNodes(){
        for(int i = 0; i < dimension; i++)
            for(int j = 0; j < dimension; j++)
                for(int k = 0; k < dimension; k++)
                    piece[i][j][k] = new Node(Node.getCount(i,j,k,dimension));    
	}


	/**
	* @brief 
	* returns the dimension of Current RubiksCube
	* @param dimension
	* dimension represents the dimension of RubiksCube
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
	* @return void 
	*/
	public void setRubiksCube(boolean type,String colorInput){
		Scanner input = null;
		
		if(type == STRING_INPUT)input = new Scanner(colorInput);
		else if(type == USER_INPUT)input = new Scanner(System.in);

		int i=-1,j=-1,k=-1;

		//SETTING FRONT LAYER COLORS
		i = 0;
		for(j = 0; j < dimension; j++)
			for(k = 0; k < dimension; k++)
				setFrontColor(i,j,k,input.next().toUpperCase());

		//SETTING LEFT LAYER COLORS
		k = 0;
		for(j = 0; j < dimension; j++)
			for(i = dimension-1; i >= 0; i--)
				setLeftColor(i,j,k,input.next().toUpperCase());
		
		//SETTING BACK LAYER COLORS
		i = dimension-1;
		for(j = 0; j < dimension; j++)
			for(k = dimension-1; k >= 0; k--)
				setBackColor(i,j,k,input.next().toUpperCase());

		//SETTING RIGHT LAYER COLORS
		k = dimension-1;
		for(j = 0; j < dimension; j++)
			for(i = 0; i < dimension; i++)
				setRightColor(i,j,k,input.next().toUpperCase());

		//SETTING UP LAYER COLORS
		j = 0;
		for(i = dimension-1; i >= 0; i--)
			for(k = 0; k < dimension; k++)
				setUpColor(i,j,k,input.next().toUpperCase());

		//SETTING DOWN LAYER COLORS
		j = dimension-1;
		for(i = 0; i < dimension; i++)
			for(k = 0; k < dimension; k++)
				setDownColor(i,j,k,input.next().toUpperCase());
	}


	/**
	* @brief 
	* returns all the colors of the RubiksCube facewise in String format
	* @return String
	*/
	public String toString(){
		StringBuilder colors = new StringBuilder("");
		int i=-1,j=-1,k=-1;

		//APPENDING ALL FRONT LAYER COLORS
		i = 0;
		for(j = 0; j < dimension; j++)
			for(k = 0; k < dimension; k++)
				colors.append(getFrontColor(i,j,k)+" ");

		//APPENDING ALL LEFT LAYER COLORS					
		k = 0;
		for(j = 0; j < dimension; j++)
			for(i = dimension-1; i >= 0; i--)
				colors.append(getLeftColor(i,j,k)+" ");
		
		//APPENDING ALL BACK LAYER COLORS
		i = dimension-1;
		for(j = 0; j < dimension; j++)
			for(k = dimension-1; k >= 0; k--)
				colors.append(getBackColor(i,j,k)+" ");

		//APPENDING ALL RIGHT LAYER COLORS
		k = dimension-1;
		for(j = 0; j < dimension; j++)
			for(i = 0; i < dimension; i++)
				colors.append(getRightColor(i,j,k)+" ");
				
		//APPENDING ALL UP LAYER COLORS
		j = 0;
		for(i = dimension-1; i >= 0; i--)
			for(k = 0; k < dimension; k++)
				colors.append(getUpColor(i,j,k)+" ");
				
		//APPENDING ALL DOWN LAYER COLORS
		j = dimension-1;
		for(i = 0; i < dimension; i++)
			for(k = 0; k < dimension; k++)
				colors.append(getDownColor(i,j,k)+" ");

		return colors.toString();
	}


	/**
	* @brief 
	* displays colors present in the cube based on type
	* @param type
	* type = FACE_WISE then color is displayed face wise 
	* type = NODE_WISE then color is displayed node wise
	* @return void 
	*/
	public void display(boolean type){
		if(type == NODE_WISE)displayNodeWise();
		else if(type == FACE_WISE)displayFaceWise();
	}


	/**
	* @brief 
	* displays colors present in the cube Node wise
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
	* @return void 
	*/
	private void displayFaceWise(){
		int i=-1,j=-1,k=-1;

		System.out.println("*******************************************");
		
		System.out.println("FRONT FACE COLORS - ");
		i = 0;
		for(j = 0; j < dimension; j++){
			for(k = 0; k < dimension; k++)System.out.print(getFrontColor(i,j,k)+" ");
			System.out.println();
		}
		System.out.println("---------------------------------------");

		System.out.println("LEFT FACE COLORS - ");
		k = 0;
		for(j = 0; j < dimension; j++){
			for(i = dimension-1; i >= 0; i--)System.out.print(getLeftColor(i,j,k)+" ");
			System.out.println();
		}
		System.out.println("---------------------------------------");
			
		System.out.println("BACK FACE COLORS - ");
		i = dimension-1;
		for(j = 0; j < dimension; j++){
			for(k = dimension-1; k >= 0; k--)System.out.print(getBackColor(i,j,k)+" ");
			System.out.println();
		}
		System.out.println("---------------------------------------");

		System.out.println("RIGHT FACE COLORS - ");
		k = dimension-1;
		for(j = 0; j < dimension; j++){
			for(i = 0; i < dimension; i++)System.out.print(getRightColor(i,j,k)+" ");
			System.out.println();
		}
		System.out.println("---------------------------------------");

		System.out.println("UP FACE COLORS - ");
		j = 0;
		for(i = dimension-1; i >= 0; i--){
			for(k = 0; k < dimension; k++)System.out.print(getUpColor(i,j,k)+" ");	
			System.out.println();
		}
		System.out.println("---------------------------------------");

		System.out.println("DOWN FACE COLORS - ");
		j = dimension-1;
		for(i = 0; i < dimension; i++){
			for(k = 0; k < dimension; k++)System.out.print(getDownColor(i,j,k)+" ");
			System.out.println();
		}	
		System.out.println("---------------------------------------");
			
		System.out.println("*******************************************");
	}


	/**
	* @brief 
	* returns the color of given index of 3D cube in String format
	* @param i,j,k
	* @param subIndex
	* i,j,k represents the index of the Node in the 3D cube
	* index represents the index within the Node
	* @return String 
	*/
	public String getColor(int i, int j, int k, int subIndex){
		return piece[i][j][k].getColor(subIndex);
	}


	/**
	* @brief 
	* returns the up color of 3D cube in String format for given Node
	* @param i,j,k
	* i,j,k represents the index of the Node in the 3D cube
	* @return String 
	*/
	public String getUpColor(int i, int j, int k){
		return getUpDownColor(i,j,k,UP);
	}


	/**
	* @brief 
	* returns the down color of 3D cube in String format for given Node
	* @param i,j,k
	* i,j,k represents the index of the Node in the 3D cube
	* @return String 
	*/
	public String getDownColor(int i, int j, int k){
		return getUpDownColor(i,j,k,DOWN);
    }


	/**
	* @brief 
	* returns the Up/down color of 3D cube in String format for given Node
	* @param i,j,k
	* @param type
	* i,j,k represents the index of the Node in the 3D cube
	* if type = UP up color is returned
	* if type = DOWN down color is returned
	* @return String 
	*/
	private String getUpDownColor(int i, int j, int k, String type){
		if(type.equals(UP) && (j != 0))return null;
		else if(type.equals(DOWN) && (j != dimension-1))return null;
		else{
			if(i == 0 || i == dimension-1)return getColor(i,j,k,1);
			else return getColor(i,j,k,0);
		}
	}

	/**
	* @brief 
	* returns the left color of 3D cube in String format for given Node
	* @param i,j,k
	* i,j,k represents the index of the Node in the 3D cube
	* @return String 
	*/
	public String getLeftColor(int i, int j, int k){
        return getLeftRightColor(i,j,k,LEFT);
    }


	/**
	* @brief 
	* returns the right color of 3D cube in String format for given Node
	* @param i,j,k
	* i,j,k represents the index of the Node in the 3D cube
	* @return String 
	*/
	public String getRightColor(int i, int j, int k){
        return getLeftRightColor(i,j,k,RIGHT);
    }

	
	/**
	* @brief 
	* returns the left/right color of 3D cube in String format for given Node
	* @param i,j,k
	* @param type
	* i,j,k represents the index of the Node in the 3D cube
	* if type = RIGHT the right color is returned
	* if type = LEFT the left color is returned
	* @return String 
	*/
	private String getLeftRightColor(int i, int j, int k, String type){
		if(type.equals(LEFT) && k != 0)return null;
		else if(type.equals(RIGHT) && (k != dimension-1))return null;
		else return getColor(i,j,k,Node.getCount(i,j,k,dimension)-1);	
	}
	

	/**
	* @brief 
	* returns the back color of 3D cube in String format for given Node
	* @param i,j,k
	* i,j,k represents the index of the Node in the 3D cube
	* @return String 
	*/
	public String getBackColor(int i, int j, int k){
        return getFrontBackColor(i,j,k,BACK);
    }


	/**
	* @brief 
	* returns the right color of 3D cube in String format for given Node
	* @param i,j,k
	* i,j,k represents the index of the Node in the 3D cube
	* @return String 
	*/
	public String getFrontColor(int i, int j, int k){
        return getFrontBackColor(i,j,k,FRONT);
    }

	
	/**
	* @brief 
	* returns the front/back color of 3D cube in String format for given Node
	* @param i,j,k
	* @param type
	* i,j,k represents the index of the Node in the 3D cube
	* if type = FRONT the front color is returned
	* if type = BACK the back color is returned
	* @return String 
	*/
	private String getFrontBackColor(int i, int j, int k, String type){
		if(type.equals(FRONT) && (i != 0))return null;
		else if(type.equals(BACK) && (i != dimension-1))return null;
		else return getColor(i,j,k,0);
	}


	/**
	* @brief 
	* returns the color of given index of 3D cube in String format
	* @param i,j,k
	* @param index
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* index represents the index within the Node
	* color represents the color to be set
	* @return void 
	*/
	public void setColor(int i, int j, int k, int subIndex, String color){
		piece[i][j][k].setColor(subIndex,color);
	}

	/**
	* @brief 
	* sets the up color of 3D cube in String format for given Node
	* @param i,j,k
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* color represents the color to be set
	* @return void 
	*/
	public void setUpColor(int i, int j, int k, String color){
		setUpDownColor(i,j,k,UP,color);
	}


	/**
	* @brief 
	* sets the down color of 3D cube in String format for given Node
	* @param i,j,k
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* color represents the color to be set
	* @return void
	*/
	public void setDownColor(int i, int j, int k, String color){
		setUpDownColor(i,j,k,DOWN,color);
    }


	/**
	* @brief 
	* sets the Up/down color of 3D cube in String format for given Node
	* @param i,j,k	
	* @param type
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* if type = UP up color is returned
	* if type = DOWN down color is returned
	* color represents the color to be set
	* @return void
	*/
	private void setUpDownColor(int i, int j, int k, String type, String color){
		if(type.equals(UP) && (j != 0))return;
		else if(type.equals(DOWN) && (j != dimension-1))return;
		else{
			if(i == 0 || i == dimension-1)setColor(i,j,k,1,color);
			else setColor(i,j,k,0,color);
		}
	}

	/**
	* @brief 
	* sets the left color of 3D cube in String format for given Node
	* @param i,j,k
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* color represents the color to be set
	* @return void
	*/
	public void setLeftColor(int i, int j, int k, String color){
        setLeftRightColor(i,j,k,LEFT,color);
    }


	/**
	* @brief 
	* sets the right color of 3D cube in String format for given Node
	* @param i,j,k
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* color represents the color to be set
	* @return void
	*/
	public void setRightColor(int i, int j, int k, String color){
        setLeftRightColor(i,j,k,RIGHT,color);
    }

	
	/**
	* @brief 
	* sets the left/right color of 3D cube in String format for given Node
	* @param i,j,k
	* @param type
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* if type = RIGHT the right color is returned
	* if type = LEFT the left color is returned
	* color represents the color to be set
	* @return void
	*/
	private void setLeftRightColor(int i, int j, int k, String type, String color){
		if(type.equals(LEFT) && k != 0)return;
		else if(type.equals(RIGHT) && (k != dimension-1))return;
		else setColor(i,j,k,Node.getCount(i,j,k,dimension)-1,color);
		
	}
	

	/**
	* @brief 
	* sets the back color of 3D cube in String format for given Node
	* @param i,j,k
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* color represents the color to be set
	* @return void
	*/
	public void setBackColor(int i, int j, int k, String color){
        setFrontBackColor(i,j,k,BACK,color);
    }


	/**
	* @brief 
	* sets the right color of 3D cube in String format for given Node
	* @param i,j,k
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* color represents the color to be set
	* @return void
	*/
	public void setFrontColor(int i, int j, int k, String color){
        setFrontBackColor(i,j,k,FRONT,color);
    }


	/**
	* @brief 
	* sets the front/back color of 3D cube in String format for given Node
	* @param i,j,k
	* @param type
	* @param color
	* i,j,k represents the index of the Node in the 3D cube
	* if type = FRONT the front color is returned
	* if type = BACK the back color is returned
	* color represents the color to be set
	* @return void
	*/
	private void setFrontBackColor(int i, int j, int k, String type, String color){
		if(type.equals(FRONT) && (i != 0))return;
		else if(type.equals(BACK) && (i != dimension-1))return;
		else setColor(i,j,k,0,color);
	}


	/**
	* @brief 
	* returns the rotate operation performed in String format
	* @param i
	* @param direction
	* i represents the layer of the cube to be rotated
	* direction = R_CLK rotates ith layer of cube in clkwise direction
	* direction = R_ANTICLK rotates ith layer of cube in anticlkwise direction
	* @return String 
	*/
	public String rotate(int i, boolean direction){
		int lengthSide = getDimension()*4;

		//storing all the face colors & rotate it & then set it in the RubiksCube
		if((i == 0) || (i == getDimension()-1)){
			String faceColor[][] = new String[getDimension()][getDimension()];
			
			for(int j = 0; j < getDimension(); j++)
				for(int k = 0; k < getDimension(); k++)
					faceColor[j][k] = getColor(i,j,k,0);

			rotateFaceColor(faceColor,direction);
						
			for(int j = 0; j < getDimension(); j++)
				for(int k = 0; k < getDimension(); k++)
					setColor(i,j,k,0,faceColor[j][k]);

		}
		
		//storing all the side colors in sideColor[]
		String sideColor[] = new String[lengthSide];
		int sideIndex = 0;
		int index = -1;
		if(direction == R_CLK)index = lengthSide-getDimension();
		else if(direction == R_ANTICLK)index = getDimension();

		int j = 0, k = 0;
		for(k = 0; k < getDimension(); k++)sideColor[sideIndex++] = getUpColor(i,j,k);	
		k = getDimension()-1;
		for(j = 0; j < getDimension(); j++)sideColor[sideIndex++] = getRightColor(i,j,k);
		j = getDimension()-1;
		for(k = getDimension()-1; k >= 0; k--)sideColor[sideIndex++] = getDownColor(i,j,k);
		k = 0;
		for(j = getDimension()-1; j >= 0; j--)sideColor[sideIndex++] = getLeftColor(i,j,k);

		//putting back the rotated color to the rubiksCube by indexing 	
		j = 0;
		for(k = 0; k < getDimension(); k++){
			setUpColor(i,j,k,sideColor[index]);
			index = (index+1)%lengthSide;
		}
		k = getDimension()-1;
		for(j = 0; j < getDimension(); j++){
			setRightColor(i,j,k,sideColor[index]);
			index = (index+1)%lengthSide;
		}
		j = getDimension()-1;
		for(k = getDimension()-1; k >= 0; k--){
			setDownColor(i,j,k,sideColor[index]);
			index = (index+1)%lengthSide;
		}
		k = 0;
		for(j = getDimension()-1; j >= 0; j--){
			setLeftColor(i,j,k,sideColor[index]);
			index = (index+1)%lengthSide;
		}

		if(direction == R_CLK)return ROTATE+" "+CLKWISE;
		else if(direction == R_ANTICLK)return ROTATE+" "+ANTICLK;
		else return null;
	}


	/**
	* @brief 
	* returns the horizontal operation performed in String format
	* @param j
	* @param direction
	* j represents the layer of the cube to be moved horizontally
	* direction = H_RIGHT moves jth layer of cube in the right direction
	* direction = H_LEFT moves jth layer of cube in the left direction
	* @return String 
	*/
	public String horizontal(int j, boolean direction){
		int lengthSide = getDimension()*4;

		//storing all the face colors & rotate it & then set it in the RubiksCube
		if((j == 0) || (j == getDimension()-1)){
			String faceColor[][] = new String[getDimension()][getDimension()];
			
			for(int i = 0; i < getDimension(); i++){
				for(int k = 0; k < getDimension(); k++){
					if(j==0)faceColor[i][k] = getUpColor(i,j,k);
					else if(j==getDimension()-1)faceColor[i][k] = getDownColor(i,j,k);
				}
			}

			rotateFaceColor(faceColor,direction);
						
			for(int i = 0; i < getDimension(); i++){
				for(int k = 0; k < getDimension(); k++){
					if(j==0)setUpColor(i,j,k,faceColor[i][k]);
					else if(j==getDimension()-1)setDownColor(i,j,k,faceColor[i][k]);
				}
			}
		}
		
		//storing all the side colors in sideColor[]
		String sideColor[] = new String[lengthSide];
		int sideIndex = 0;
		int index = -1;
		if(direction == H_RIGHT)index = lengthSide-getDimension();
		else if(direction == H_LEFT)index = getDimension();

		int i = 0, k = 0;
		for(k = 0; k < getDimension(); k++)sideColor[sideIndex++] = getFrontColor(i,j,k);	
		k = getDimension()-1;
		for(i = 0; i < getDimension(); i++)sideColor[sideIndex++] = getRightColor(i,j,k);
		i = getDimension()-1;
		for(k = getDimension()-1; k >= 0; k--)sideColor[sideIndex++] = getBackColor(i,j,k);
		k = 0;
		for(i = getDimension()-1; i >= 0; i--)sideColor[sideIndex++] = getLeftColor(i,j,k);
	
		//putting back the rotated color to the rubiksCube by indexing 
		i = 0;
		for(k = 0; k < getDimension(); k++){
			setFrontColor(i,j,k,sideColor[index]);
			index = (index+1)%lengthSide;
		}
		k = getDimension()-1;
		for(i = 0; i < getDimension(); i++){
			setRightColor(i,j,k,sideColor[index]);
			index = (index+1)%lengthSide;
		}
		i = getDimension()-1;
		for(k = getDimension()-1; k >= 0; k--){
			setBackColor(i,j,k,sideColor[index]);
			index = (index+1)%lengthSide;
		}
		k = 0;
		for(i = getDimension()-1; i >= 0; i--){
			setLeftColor(i,j,k,sideColor[index]);
			index = (index+1)%lengthSide;
		}

		if(direction == H_RIGHT)
			return HORIZONTAL+" "+RIGHT;
		else if(direction == H_LEFT)
			return HORIZONTAL+" "+LEFT;
		else
			return null;
	}

	
	/**
	* @brief 
	* returns the vertical operation performed in String format
	* @param k
	* @param direction
	* k represents the layer of the cube to be moved vertically
	* direction = V_UP moves kth layer of cube in the upward direction
	* direction = V_DOWN moves kth layer of cube in the downward direction
	* @return String 
	*/
	public String vertical(int k, boolean direction){
		int lengthSide = getDimension()*4;

		//storing all the face colors & rotate it & then set it in the RubiksCube
		if((k == 0) || (k == getDimension()-1)){
			String faceColor[][] = new String[getDimension()][getDimension()];
			
			for(int j = 0; j < getDimension(); j++)
				for(int i = 0; i < getDimension(); i++)
					faceColor[j][i] = getColor(i,j,k,Node.getCount(i,j,k,getDimension())-1);
				

			rotateFaceColor(faceColor,direction);
						
			for(int j = 0; j < getDimension(); j++)
				for(int i = 0; i < getDimension(); i++)
					setColor(i,j,k,Node.getCount(i,j,k,getDimension())-1,faceColor[j][i]);		
		}
		
		//storing all the side colors in sideColor[]
		String sideColor[] = new String[lengthSide];
		int sideIndex = 0;
		int index = -1;
		if(direction == V_UP)index = lengthSide-getDimension();
		else if(direction == V_DOWN)index = getDimension();

		int j = 0, i = 0;
		for(i = 0; i < getDimension(); i++)sideColor[sideIndex++] = getUpColor(i,j,k);	
		i = getDimension()-1;
		for(j = 0; j < getDimension(); j++)sideColor[sideIndex++] = getBackColor(i,j,k);
		j = getDimension()-1;
		for(i = getDimension()-1; i >= 0; i--)sideColor[sideIndex++] = getDownColor(i,j,k);
		i = 0;
		for(j = getDimension()-1; j >= 0; j--)sideColor[sideIndex++] = getFrontColor(i,j,k);
	
		//putting back the rotated color to the rubiksCube by indexing
		j = 0;i = 0;
		for(i = 0; i < getDimension(); i++){
			setUpColor(i,j,k,sideColor[index]);
			index = (index+1)%lengthSide;
		}	
		i = getDimension()-1;
		for(j = 0; j < getDimension(); j++){
			setBackColor(i,j,k,sideColor[index]);
			index = (index+1)%lengthSide;
		}
		j = getDimension()-1;
		for(i = getDimension()-1; i >= 0; i--){
			setDownColor(i,j,k,sideColor[index]);
			index = (index+1)%lengthSide;
		}
		i = 0;
		for(j = getDimension()-1; j >= 0; j--){
			setFrontColor(i,j,k,sideColor[index]);
			index = (index+1)%lengthSide;
		}

		if(direction == V_UP)
			return VERTICAL+" "+UP;
		else if(direction == V_DOWN)
			return VERTICAL+" "+DOWN;
		else
			return null;
	}


	/**
	* @brief 
	* transposes the faceColor[][]
	* @param faceColor
	* faceColor[][] holds all the face Color to be transposed
	* @return void 
	*/
	private void transpose(String faceColor[][]){	
        for(int i = 0; i < faceColor.length; i++){
            for(int j = 0; j < i; j++){
                String temp = faceColor[i][j];
                faceColor[i][j] = faceColor[j][i];
                faceColor[j][i] = temp;
            }
        }
	}


	/**
	* @brief 
	* rotates given faceColor in R_CLK or R_ANTICLK
	* @param faceColor
	* @param direction
	* faceColor represents all the faceColor
	* direction = R_CLK rotates faceColor[][] in clkwise direction
	* direction = R_ANTICLK rotates faceColor[][] in anticlkwise direction
	* @return void 
	*/
	private void rotateFaceColor(String faceColor[][], boolean direction){

		transpose(faceColor);

		if(direction){
			for(int i = 0; i < faceColor.length; i++){
				for(int j = 0; j < faceColor[0].length/2; j++){
					String temp = faceColor[i][j];
					faceColor[i][j] = faceColor[i][faceColor[0].length-j-1];
					faceColor[i][faceColor[0].length-j-1] = temp;
				}
        	}
		}
		else{
			for(int i = 0; i < faceColor.length/2; i++){
            	for(int j = 0; j < faceColor[0].length; j++){
					String temp = faceColor[i][j];
					faceColor[i][j] = faceColor[faceColor.length-1-i][j];
					faceColor[faceColor.length-1-i][j] = temp;
            	}
        	}
		}
    
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
		
		for(int count = 0; count < getDimension(); count++)horizontal(count,direction);

		if(direction == H_RIGHT	)return HORIZONTAL+" "+RIGHT;
		else if(direction == H_LEFT)return HORIZONTAL+" "+LEFT;
		else return null;	
	}
	

	/**
	* @brief 
	* returns the circleHorizontal operation performed in String format
	* @param direction
	* direction = V_UP moves the entire cube towards upward direction
	* direction = V_DOWN moves the entire cube towards downward direction
	* @return String 
	*/
	public String circleVertical(boolean direction){
				
		for(int count = 0; count < getDimension(); count++)vertical(count,direction);
		
		if(direction == V_UP)return VERTICAL+" "+UP;
		else if(direction == V_DOWN)return VERTICAL+" "+DOWN;
		else return null;
	}	

}