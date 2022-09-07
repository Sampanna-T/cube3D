package src.cube;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


/**
 * Provides implementation of Cube Interface and allows
 * RubiksCubes of different dimensions to extend all the functionality
 * 
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 2nd June 2022
 * @since JDK 10.0.2
 */
public abstract class RubiksCube implements RubiksCubeInterface{
	
	/**
	 * Provides functionality of a Node in the RubiksCube
	 * 
	 * @author Sampanna T (kashi16sadan@gmail.com)
	 * @version 1.0 3rd June 2022
	 * @since JDK 10.0.2
	 */
	private class Node implements RubiksCubeInterface.NodeInterface{

		// contains all the colors for a given Node
		private String color[];


		/**
		 * initializes the Node
		 * 
		 * @param size
		 * represents the size of color[]
		 */
		Node(int size) {
			createNode(size);
		}

		//creates a new String[] object and assigns it to color
		private void createNode(int size) {
			if (size <= MAX_SIZE && size >= MIN_SIZE)
				color = new String[size];
			else
				color = null;
		}


		/**
		 * returns the size of color[]
		 * 
		 * @return int
		 */
		public int getSize() {
			return color.length;
		}

		/**
		 * returns all the colors in the form of String[]
		 * 
		 * @return String[]
		 */
		public String[] getColors() {
			if (color == null)
				return null;
			else {
				String colors[] = new String[color.length];
				for (int i = 0; i < color.length; i++)
					colors[i] = new String(color[i]);
				return colors;
			}
		}


		/**
		 * returns color for given subIndex i.e index of color[]
		 * 
		 * @param subIndex
		 * represents index of color[]
		 * @return String
		 */
		public String getColor(int subIndex) {
			boolean isSubIndexValid = isInRange(subIndex, 0, getSize() - 1);

			if (isSubIndexValid)
				return color[subIndex];
			else
				return null;
		}

		
		/**
		 * adds newColor to the color[] at given subIndex and returns true if valid
		 * 
		 * @param subIndex
		 * represents index of color[]
		 * @param newColor
		 * represents the color to be set
		 * @return boolean
		 */
		public boolean setColor(int subIndex, String newColor) {
			boolean isSubIndexValid = isInRange(subIndex, 0, getSize() - 1);

			if (isSubIndexValid) {
				color[subIndex] = newColor;
				return true;
			} else
				return false;
		}
	}

	private int dimension;
	private Node piece[][][];

	/**
	 * constructor which initializes the RubiksCube by creating Nodes
	 * 
	 * @param dimension
	 * represents dimension of the RubiksCube
	 * @throws Exception
	 * if user input given is invalid
	 */
	public RubiksCube(int dimension)throws Exception{
		this(dimension, null);
	}


	/**
	 * constructor which initializes the RubiksCube by creating Nodes
	 * 
	 * @param dimension
	 * represents dimension of the RubiksCube
	 * @param colorInput
	 * represents String that holds all the colors of RubiksCube
	 * @throws Exception
	 * if string input provided to initialize the cube is invalid
	 */
	public RubiksCube(int dimension,String colorInput)throws Exception{
		this.dimension = dimension;
		piece = new Node[dimension][dimension][dimension];
		createNodes();
		if(colorInput == null)
			setRubiksCube(USER_INPUT, colorInput);
		else
			setRubiksCube(STRING_INPUT, colorInput);
	}

	
	//returns true if colors within all the nodes of the RubiksCube are valid
    private boolean isNodeValid()throws Exception{

        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                for(int k = 0; k < dimension; k++){
                    String colors[] = getColors(i,j,k);
                    if(isDuplicate(colors))return false;
                }
            }
        }

        return true;
    }


	//checks if there are duplicates in colors[] or not
    private boolean isDuplicate(String colors[]){
        
        if(colors.length == CORNER_COUNT){
            if( (colors[0].equals(colors[1])) || (colors[1].equals(colors[2])) || (colors[2].equals(colors[0])))
                return true;
            else
                return false;
        }
        else if(colors.length == EDGE_COUNT){
            if( colors[0].equals(colors[1]) )
                return true;
            else
                return false;
        }
        else
            return false;
    }


	//returns true if all the colors of the RubiksCube are valid
    private boolean isColorValid()throws Exception{
        String colors[] = toString().split(" ");
        int colorCount = (dimension*dimension*FACE_COUNT);

        HashMap <String,Integer>result = new HashMap<String,Integer>();

        for(int i = 0; i < colorCount; i++){
            if(result.containsKey(colors[i])){
                int value = result.get(colors[i]);
                result.replace(colors[i],value+1);
            }
            else
                result.put(colors[i],1);
        }

        Iterator <Map.Entry<String,Integer>>itr = result.entrySet().iterator();
        
        while(itr.hasNext()){
            Map.Entry <String,Integer>mapPair = (Map.Entry<String,Integer>)itr.next();
            int value = (int)mapPair.getValue();
            if(value != dimension*dimension)return false;
        }

        return true;
    }


	//returns size of color[] for given dimension and i,j,k value
	private static int getCount(int i, int j, int k, int dimension) {
		if (!isIndexValid(i, j, k, dimension))return -1;
		
		if (isCornerNode(i, j, k, dimension))
			return 3;
		else if (isEdgeNode(i, j, k, dimension))
			return 2;
		else if (isCenterNode(i, j, k, dimension))
			return 1;
		else
			return 0;
	}

	//returns true if i,j,k value are valid for given dimension
	private static boolean isIndexValid(int i, int j, int k, int dimension) {
		if (dimension <= 1)return false;

		boolean iValid = isInRange(i, 0, dimension - 1);
		boolean jValid = isInRange(j, 0, dimension - 1);
		boolean kValid = isInRange(k, 0, dimension - 1);

		if (iValid && jValid && kValid)
			return true;
		else
			return false;
	}

	//returns true if i,j,k represents a valid corner Node for given dimension i.e.color[] holding 3 colors
	private static boolean isCornerNode(int i, int j, int k, int dimension) {
		if (!isIndexValid(i, j, k, dimension))return false;

		boolean iValid = (i == 0 || i == dimension - 1);
		boolean jValid = (j == 0 || j == dimension - 1);
		boolean kValid = (k == 0 || k == dimension - 1);

		if (iValid && jValid && kValid)
			return true;
		else
			return false;
	}

	//returns true if i,j,k represents a valid edge Node for given dimension i.e. color[] holding 2 colors
	private static boolean isEdgeNode(int i, int j, int k, int dimension) {
		if (!isIndexValid(i, j, k, dimension))
			return false;

		boolean ijValid = (i == 0 || i == dimension - 1) && (j == 0 || j == dimension - 1);
		boolean jkValid = (j == 0 || j == dimension - 1) && (k == 0 || k == dimension - 1);
		boolean kiValid = (k == 0 || k == dimension - 1) && (i == 0 || i == dimension - 1);

		boolean iValid = isInRange(i, 1, dimension - 2);
		boolean jValid = isInRange(j, 1, dimension - 2);
		boolean kValid = isInRange(k, 1, dimension - 2);

		if (ijValid && kValid)
			return true;
		else if (jkValid && iValid)
			return true;
		else if (kiValid && jValid)
			return true;
		else
			return false;
	}


	//returns true if i,j,k represents a valid center Node for given dimension i.e. color[] holding 1 color
	private static boolean isCenterNode(int i, int j, int k, int dimension) {
		if (!isIndexValid(i, j, k, dimension))return false;

		boolean iValid = (i == 0 || i == dimension - 1);
		boolean jValid = (j == 0 || j == dimension - 1);
		boolean kValid = (k == 0 || k == dimension - 1);

		boolean jkValid = (isInRange(j, 1, dimension - 2) && isInRange(k, 1, dimension - 2));
		boolean ikValid = (isInRange(i, 1, dimension - 2) && isInRange(k, 1, dimension - 2));
		boolean ijValid = (isInRange(i, 1, dimension - 2) && isInRange(j, 1, dimension - 2));

		if (iValid && jkValid)
			return true;
		else if (jValid && ikValid)
			return true;
		else if (kValid && ijValid)
			return true;
		else
			return false;
	}


	//returns true if given value lies in the range of from to to
	private static boolean isInRange(int value, int from, int to) {
		if (value >= from && value <= to)
			return true;
		else 
			return false;
	}


	// creates nodes and adds it in 3D cube
	private void createNodes() {
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				for (int k = 0; k < dimension; k++)
					piece[i][j][k] = new Node(getCount(i, j, k, dimension));
	}

	/**
	 * returns the dimension of Current RubiksCube object
	 * 
	 * @return int
	 */
	public int getDimension() {
		return dimension;
	}

	/**
	 * initializes the colors of the Cube based on type and colorInput
	 * 
	 * @param type
	 * specifies if the input is from user or colorInput(type can
	 * be STRING_INPUT or USER_INPUT)
	 * @param colorInput
	 * holds all the colors of the RubiksCube to be initialized
	 * @throws Exception
	 * If the RubiksCube couldn't be initialized
	 */
	public void setRubiksCube(boolean type, String colorInput) throws Exception {
		Scanner input = null;

		if (type == STRING_INPUT) {
			if (colorInput.trim().split(" ").length != (dimension * dimension * FACE_COUNT))
				throw RubiksCubeException.colorStringInvalid();
			input = new Scanner(colorInput);
		} else if (type == USER_INPUT)
			input = new Scanner(System.in);

		int i = -1, j = -1, k = -1;

		// SETTING FRONT LAYER COLORS
		i = 0;
		for (j = 0; j < dimension; j++)
			for (k = 0; k < dimension; k++)
				setFrontColor(i, j, k, input.next().toUpperCase());

		// SETTING LEFT LAYER COLORS
		k = 0;
		for (j = 0; j < dimension; j++)
			for (i = dimension - 1; i >= 0; i--)
				setLeftColor(i, j, k, input.next().toUpperCase());

		// SETTING BACK LAYER COLORS
		i = dimension - 1;
		for (j = 0; j < dimension; j++)
			for (k = dimension - 1; k >= 0; k--)
				setBackColor(i, j, k, input.next().toUpperCase());

		// SETTING RIGHT LAYER COLORS
		k = dimension - 1;
		for (j = 0; j < dimension; j++)
			for (i = 0; i < dimension; i++)
				setRightColor(i, j, k, input.next().toUpperCase());

		// SETTING UP LAYER COLORS
		j = 0;
		for (i = dimension - 1; i >= 0; i--)
			for (k = 0; k < dimension; k++)
				setUpColor(i, j, k, input.next().toUpperCase());

		// SETTING DOWN LAYER COLORS
		j = dimension - 1;
		for (i = 0; i < dimension; i++)
			for (k = 0; k < dimension; k++)
				setDownColor(i, j, k, input.next().toUpperCase());

		if (!isNodeValid()) {
			piece = null;
			throw RubiksCubeException.nodeInvalid();
		}
		else if(!isColorValid()){
			piece = null;
			throw RubiksCubeException.colorInputInvalid();
		}
	}

	/**
	 * returns all the colors of the RubiksCube facewise in String format
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuilder colors = new StringBuilder("");
		int i = -1, j = -1, k = -1;

		// APPENDING ALL FRONT LAYER COLORS
		i = 0;
		for (j = 0; j < dimension; j++)
			for (k = 0; k < dimension; k++)
				colors.append(getFrontColor(i, j, k) + " ");

		// APPENDING ALL LEFT LAYER COLORS
		k = 0;
		for (j = 0; j < dimension; j++)
			for (i = dimension - 1; i >= 0; i--)
				colors.append(getLeftColor(i, j, k) + " ");

		// APPENDING ALL BACK LAYER COLORS
		i = dimension - 1;
		for (j = 0; j < dimension; j++)
			for (k = dimension - 1; k >= 0; k--)
				colors.append(getBackColor(i, j, k) + " ");

		// APPENDING ALL RIGHT LAYER COLORS
		k = dimension - 1;
		for (j = 0; j < dimension; j++)
			for (i = 0; i < dimension; i++)
				colors.append(getRightColor(i, j, k) + " ");

		// APPENDING ALL UP LAYER COLORS
		j = 0;
		for (i = dimension - 1; i >= 0; i--)
			for (k = 0; k < dimension; k++)
				colors.append(getUpColor(i, j, k) + " ");

		// APPENDING ALL DOWN LAYER COLORS
		j = dimension - 1;
		for (i = 0; i < dimension; i++)
			for (k = 0; k < dimension; k++)
				colors.append(getDownColor(i, j, k) + " ");

		return colors.toString();
	}

	/**
	 * displays colors present in the cube based on type
	 * 
	 * @param type
	 *             type is FACE_WISE,NODE_WISE then color would be displayed face
	 *             wise, node wise respectively
	 */
	public void display(boolean type) {
		if (type == NODE_WISE)
			displayNodeWise();
		else if (type == FACE_WISE)
			displayFaceWise();
	}

	/**
	 * displays colors present in the cube Node wise
	 */
	private void displayNodeWise() {
		System.out.println("*******************************************");
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				for (int k = 0; k < dimension; k++) {
					int colorSize = getCount(i, j, k, dimension);
					for (int index = 0; index < colorSize; index++) {
						System.out.print(getColor(i, j, k, index));
						if (index != colorSize - 1)
							System.out.print(",");
					}
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println("---------------------------------------");
		}
		System.out.println("*******************************************");
	}

	//displays colors present in the cube Face wise
	private void displayFaceWise() {
		int i = -1, j = -1, k = -1;

		System.out.println("*******************************************");

		System.out.println("FRONT FACE COLORS - ");
		i = 0;
		for (j = 0; j < dimension; j++) {
			for (k = 0; k < dimension; k++)
				System.out.print(getFrontColor(i, j, k) + " ");
			System.out.println();
		}
		System.out.println("---------------------------------------");

		System.out.println("LEFT FACE COLORS - ");
		k = 0;
		for (j = 0; j < dimension; j++) {
			for (i = dimension - 1; i >= 0; i--)
				System.out.print(getLeftColor(i, j, k) + " ");
			System.out.println();
		}
		System.out.println("---------------------------------------");

		System.out.println("BACK FACE COLORS - ");
		i = dimension - 1;
		for (j = 0; j < dimension; j++) {
			for (k = dimension - 1; k >= 0; k--)
				System.out.print(getBackColor(i, j, k) + " ");
			System.out.println();
		}
		System.out.println("---------------------------------------");

		System.out.println("RIGHT FACE COLORS - ");
		k = dimension - 1;
		for (j = 0; j < dimension; j++) {
			for (i = 0; i < dimension; i++)
				System.out.print(getRightColor(i, j, k) + " ");
			System.out.println();
		}
		System.out.println("---------------------------------------");

		System.out.println("UP FACE COLORS - ");
		j = 0;
		for (i = dimension - 1; i >= 0; i--) {
			for (k = 0; k < dimension; k++)
				System.out.print(getUpColor(i, j, k) + " ");
			System.out.println();
		}
		System.out.println("---------------------------------------");

		System.out.println("DOWN FACE COLORS - ");
		j = dimension - 1;
		for (i = 0; i < dimension; i++) {
			for (k = 0; k < dimension; k++)
				System.out.print(getDownColor(i, j, k) + " ");
			System.out.println();
		}
		System.out.println("---------------------------------------");

		System.out.println("*******************************************");
	}

	/**
	 * returns the color of given index of 3D cube in String format
	 * 
	 * @param i
	 * represents index i out of (i,j,k) in 3D cube
	 * @param j
	 * represents index j out of (i,j,k) in 3D cube
	 * @param k
	 * represents index k out of (i,j,k) in 3D cube
	 * @param subIndex
	 * represents the index within the Node
	 * @return String
	 */
	public String getColor(int i, int j, int k, int subIndex) {
		return piece[i][j][k].getColor(subIndex);
	}

	/**
	 * returns all the colors of given index of 3D cube in String[] format
	 * 
	 * @param i
	 * represents index i out of (i,j,k) in 3D cube
	 * @param j
	 * represents index j out of (i,j,k) in 3D cube
	 * @param k
	 * represents index k out of (i,j,k) in 3D cube
	 * @return String[]
	 */
	public String[] getColors(int i, int j, int k) {
		return piece[i][j][k].getColors();
	}

	/**
	 * returns the up color of 3D cube in String format for given Node
	 * 
	 * @param i
	 * represents index i out of (i,j,k) in 3D cube
	 * @param j
	 * represents index j out of (i,j,k) in 3D cube
	 * @param k
	 * represents index k out of (i,j,k) in 3D cube
	 * @return String
	 */
	public String getUpColor(int i, int j, int k) {
		return getUpDownColor(i, j, k, UP);
	}

	/**
	 * returns the down color of 3D cube in String format for given Node
	 * 
	 * @param i
	 * represents index i out of (i,j,k) in 3D cube
	 * @param j
	 * represents index j out of (i,j,k) in 3D cube
	 * @param k
	 * represents index k out of (i,j,k) in 3D cube
	 * @return String
	 */
	public String getDownColor(int i, int j, int k) {
		return getUpDownColor(i, j, k, DOWN);
	}

	// returns upcolor,downcolor for given index if type is UP,DOWN respectively
	private String getUpDownColor(int i, int j, int k, String type) {
		if (type.equals(UP) && (j != 0))
			return null;
		else if (type.equals(DOWN) && (j != dimension - 1))
			return null;
		else {
			if (i == 0 || i == dimension - 1)
				return getColor(i, j, k, 1);
			else
				return getColor(i, j, k, 0);
		}
	}

	/**
	 * returns the left color of 3D cube in String format for given Node
	 * 
	 * @param i
	 * represents index i out of (i,j,k) in 3D cube
	 * @param j
	 * represents index j out of (i,j,k) in 3D cube
	 * @param k
	 * represents index k out of (i,j,k) in 3D cube
	 * @return String
	 */
	public String getLeftColor(int i, int j, int k) {
		return getLeftRightColor(i, j, k, LEFT);
	}

	/**
	 * returns the right color of 3D cube in String format for given Node
	 * 
	 * @param i
	 * represents index i out of (i,j,k) in 3D cube
	 * @param j
	 * represents index j out of (i,j,k) in 3D cube
	 * @param k
	 * represents index k out of (i,j,k) in 3D cube
	 * @return String
	 */
	public String getRightColor(int i, int j, int k) {
		return getLeftRightColor(i, j, k, RIGHT);
	}

	// returns leftcolor,rightcolor for given index if type is LEFT,RIGHT respectively
	private String getLeftRightColor(int i, int j, int k, String type) {
		if (type.equals(LEFT) && k != 0)
			return null;
		else if (type.equals(RIGHT) && (k != dimension - 1))
			return null;
		else
			return getColor(i, j, k, getCount(i, j, k, dimension) - 1);
	}

	/**
	 * returns the back color of 3D cube in String format for given Node
	 * 
	 * @param i
	 * represents index i out of (i,j,k) in 3D cube
	 * @param j
	 * represents index j out of (i,j,k) in 3D cube
	 * @param k
	 * represents index k out of (i,j,k) in 3D cube
	 * @return String
	 */
	public String getBackColor(int i, int j, int k) {
		return getFrontBackColor(i, j, k, BACK);
	}

	/**
	 * returns the front color of 3D cube in String format for given Node
	 * 
	 * @param i
	 * represents index i out of (i,j,k) in 3D cube
	 * @param j
	 * represents index j out of (i,j,k) in 3D cube
	 * @param k
	 * represents index k out of (i,j,k) in 3D cube
	 * @return String
	 */
	public String getFrontColor(int i, int j, int k) {
		return getFrontBackColor(i, j, k, FRONT);
	}

	// returns frontcolor,backcolor for given index if type is FRONT,BACK respectively
	private String getFrontBackColor(int i, int j, int k, String type) {
		if (type.equals(FRONT) && (i != 0))
			return null;
		else if (type.equals(BACK) && (i != dimension - 1))
			return null;
		else
			return getColor(i, j, k, 0);
	}

	//sets color of rubikscube for given i,j,k,subindex
	private void setColor(int i, int j, int k, int subIndex, String color) {
		piece[i][j][k].setColor(subIndex, color);
	}

	
	//sets up color of rubikscube for given i,j,k
	private void setUpColor(int i, int j, int k, String color) {
		setUpDownColor(i, j, k, UP, color);
	}

	//sets down color of rubikscube for given i,j,k
	private void setDownColor(int i, int j, int k, String color) {
		setUpDownColor(i, j, k, DOWN, color);
	}

	// sets upcolor,downcolor for given index if type is UP,DOWN respectively as color
	private void setUpDownColor(int i, int j, int k, String type, String color) {
		if (type.equals(UP) && (j != 0))
			return;
		else if (type.equals(DOWN) && (j != dimension - 1))
			return;
		else {
			if (i == 0 || i == dimension - 1)
				setColor(i, j, k, 1, color);
			else
				setColor(i, j, k, 0, color);
		}
	}

	//sets left color of rubikscube for given i,j,k
	private void setLeftColor(int i, int j, int k, String color) {
		setLeftRightColor(i, j, k, LEFT, color);
	}

	//sets right color of rubikscube for given i,j,k
	private void setRightColor(int i, int j, int k, String color) {
		setLeftRightColor(i, j, k, RIGHT, color);
	}

	// sets leftcolor,rightcolor for given index if type is LEFT,RIGHT respectively as color
	private void setLeftRightColor(int i, int j, int k, String type, String color) {
		if (type.equals(LEFT) && k != 0)
			return;
		else if (type.equals(RIGHT) && (k != dimension - 1))
			return;
		else
			setColor(i, j, k, getCount(i, j, k, dimension) - 1, color);

	}

	//sets back color of rubikscube for given i,j,k
	private void setBackColor(int i, int j, int k, String color) {
		setFrontBackColor(i, j, k, BACK, color);
	}

	//sets front color of rubikscube for given i,j,k
	private void setFrontColor(int i, int j, int k, String color) {
		setFrontBackColor(i, j, k, FRONT, color);
	}

	// sets frontcolor,backcolor for given index if type is FRONT,BACK respectively as color
	private void setFrontBackColor(int i, int j, int k, String type, String color) {
		if (type.equals(FRONT) && (i != 0))
			return;
		else if (type.equals(BACK) && (i != dimension - 1))
			return;
		else
			setColor(i, j, k, 0, color);
	}

	/**
	 * returns the rotate operation performed in String format
	 * 
	 * @param i
	 * i represents the layer of the cube to be rotated
	 * @param direction
	 * for direction R_CLK,R_ANTICLK rotation of ith layer of
	 * cube happens in clkwise,anticlk direction respectively
	 * @return String
	 */
	public String rotate(int i, boolean direction) {
		int lengthSide = getDimension() * 4;

		// storing all the face colors & rotate it & then set it in the RubiksCube
		if ((i == 0) || (i == getDimension() - 1)) {
			String faceColor[][] = new String[getDimension()][getDimension()];

			for (int j = 0; j < getDimension(); j++)
				for (int k = 0; k < getDimension(); k++)
					faceColor[j][k] = getColor(i, j, k, 0);

			rotateFaceColor(faceColor, direction);

			for (int j = 0; j < getDimension(); j++)
				for (int k = 0; k < getDimension(); k++)
					setColor(i, j, k, 0, faceColor[j][k]);

		}

		// storing all the side colors in sideColor[]
		String sideColor[] = new String[lengthSide];
		int sideIndex = 0;
		int index = -1;
		if (direction == R_CLK)
			index = lengthSide - getDimension();
		else if (direction == R_ANTICLK)
			index = getDimension();

		int j = 0, k = 0;
		for (k = 0; k < getDimension(); k++)
			sideColor[sideIndex++] = getUpColor(i, j, k);
		k = getDimension() - 1;
		for (j = 0; j < getDimension(); j++)
			sideColor[sideIndex++] = getRightColor(i, j, k);
		j = getDimension() - 1;
		for (k = getDimension() - 1; k >= 0; k--)
			sideColor[sideIndex++] = getDownColor(i, j, k);
		k = 0;
		for (j = getDimension() - 1; j >= 0; j--)
			sideColor[sideIndex++] = getLeftColor(i, j, k);

		// putting back the rotated color to the rubiksCube by indexing
		j = 0;
		for (k = 0; k < getDimension(); k++) {
			setUpColor(i, j, k, sideColor[index]);
			index = (index + 1) % lengthSide;
		}
		k = getDimension() - 1;
		for (j = 0; j < getDimension(); j++) {
			setRightColor(i, j, k, sideColor[index]);
			index = (index + 1) % lengthSide;
		}
		j = getDimension() - 1;
		for (k = getDimension() - 1; k >= 0; k--) {
			setDownColor(i, j, k, sideColor[index]);
			index = (index + 1) % lengthSide;
		}
		k = 0;
		for (j = getDimension() - 1; j >= 0; j--) {
			setLeftColor(i, j, k, sideColor[index]);
			index = (index + 1) % lengthSide;
		}

		if (direction == R_CLK)
			return ROTATE + " " + CLKWISE;
		else if (direction == R_ANTICLK)
			return ROTATE + " " + ANTICLK;
		else
			return null;
	}

	/**
	 * returns the horizontal operation performed in String format
	 * 
	 * @param j
	 * j represents the layer of the cube to be moved horizontally
	 * @param direction
	 * for direction H_RIGHT,H_LEFT horizontal move of jth layer of
	 * cube happens in horizontal right,horizontal left direction
	 * respectively
	 * @return String
	 */
	public String horizontal(int j, boolean direction) {
		int lengthSide = getDimension() * 4;

		// storing all the face colors & rotate it & then set it in the RubiksCube
		if ((j == 0) || (j == getDimension() - 1)) {
			String faceColor[][] = new String[getDimension()][getDimension()];

			for (int i = 0; i < getDimension(); i++) {
				for (int k = 0; k < getDimension(); k++) {
					if (j == 0)
						faceColor[i][k] = getUpColor(i, j, k);
					else if (j == getDimension() - 1)
						faceColor[i][k] = getDownColor(i, j, k);
				}
			}

			rotateFaceColor(faceColor, direction);

			for (int i = 0; i < getDimension(); i++) {
				for (int k = 0; k < getDimension(); k++) {
					if (j == 0)
						setUpColor(i, j, k, faceColor[i][k]);
					else if (j == getDimension() - 1)
						setDownColor(i, j, k, faceColor[i][k]);
				}
			}
		}

		// storing all the side colors in sideColor[]
		String sideColor[] = new String[lengthSide];
		int sideIndex = 0;
		int index = -1;
		if (direction == H_RIGHT)
			index = lengthSide - getDimension();
		else if (direction == H_LEFT)
			index = getDimension();

		int i = 0, k = 0;
		for (k = 0; k < getDimension(); k++)
			sideColor[sideIndex++] = getFrontColor(i, j, k);
		k = getDimension() - 1;
		for (i = 0; i < getDimension(); i++)
			sideColor[sideIndex++] = getRightColor(i, j, k);
		i = getDimension() - 1;
		for (k = getDimension() - 1; k >= 0; k--)
			sideColor[sideIndex++] = getBackColor(i, j, k);
		k = 0;
		for (i = getDimension() - 1; i >= 0; i--)
			sideColor[sideIndex++] = getLeftColor(i, j, k);

		// putting back the rotated color to the rubiksCube by indexing
		i = 0;
		for (k = 0; k < getDimension(); k++) {
			setFrontColor(i, j, k, sideColor[index]);
			index = (index + 1) % lengthSide;
		}
		k = getDimension() - 1;
		for (i = 0; i < getDimension(); i++) {
			setRightColor(i, j, k, sideColor[index]);
			index = (index + 1) % lengthSide;
		}
		i = getDimension() - 1;
		for (k = getDimension() - 1; k >= 0; k--) {
			setBackColor(i, j, k, sideColor[index]);
			index = (index + 1) % lengthSide;
		}
		k = 0;
		for (i = getDimension() - 1; i >= 0; i--) {
			setLeftColor(i, j, k, sideColor[index]);
			index = (index + 1) % lengthSide;
		}

		if (direction == H_RIGHT)
			return HORIZONTAL + " " + RIGHT;
		else if (direction == H_LEFT)
			return HORIZONTAL + " " + LEFT;
		else
			return null;
	}

	/**
	 * returns the vertical operation performed in String format
	 * 
	 * @param k
	 * k represents the layer of the cube to be moved vertically
	 * @param direction
	 * for direction V_UP,V_DOWN vertical move of kth layer of
	 * cube happens in vertical up,vertical down direction
	 * respectively
	 * @return String
	 */
	public String vertical(int k, boolean direction) {
		int lengthSide = getDimension() * 4;

		// storing all the face colors & rotate it & then set it in the RubiksCube
		if ((k == 0) || (k == getDimension() - 1)) {
			String faceColor[][] = new String[getDimension()][getDimension()];

			for (int j = 0; j < getDimension(); j++)
				for (int i = 0; i < getDimension(); i++)
					faceColor[j][i] = getColor(i, j, k, getCount(i, j, k, getDimension()) - 1);

			rotateFaceColor(faceColor, direction);

			for (int j = 0; j < getDimension(); j++)
				for (int i = 0; i < getDimension(); i++)
					setColor(i, j, k, getCount(i, j, k, getDimension()) - 1, faceColor[j][i]);
		}

		// storing all the side colors in sideColor[]
		String sideColor[] = new String[lengthSide];
		int sideIndex = 0;
		int index = -1;
		if (direction == V_UP)
			index = lengthSide - getDimension();
		else if (direction == V_DOWN)
			index = getDimension();

		int j = 0, i = 0;
		for (i = 0; i < getDimension(); i++)
			sideColor[sideIndex++] = getUpColor(i, j, k);
		i = getDimension() - 1;
		for (j = 0; j < getDimension(); j++)
			sideColor[sideIndex++] = getBackColor(i, j, k);
		j = getDimension() - 1;
		for (i = getDimension() - 1; i >= 0; i--)
			sideColor[sideIndex++] = getDownColor(i, j, k);
		i = 0;
		for (j = getDimension() - 1; j >= 0; j--)
			sideColor[sideIndex++] = getFrontColor(i, j, k);

		// putting back the rotated color to the rubiksCube by indexing
		j = 0;
		i = 0;
		for (i = 0; i < getDimension(); i++) {
			setUpColor(i, j, k, sideColor[index]);
			index = (index + 1) % lengthSide;
		}
		i = getDimension() - 1;
		for (j = 0; j < getDimension(); j++) {
			setBackColor(i, j, k, sideColor[index]);
			index = (index + 1) % lengthSide;
		}
		j = getDimension() - 1;
		for (i = getDimension() - 1; i >= 0; i--) {
			setDownColor(i, j, k, sideColor[index]);
			index = (index + 1) % lengthSide;
		}
		i = 0;
		for (j = getDimension() - 1; j >= 0; j--) {
			setFrontColor(i, j, k, sideColor[index]);
			index = (index + 1) % lengthSide;
		}

		if (direction == V_UP)
			return VERTICAL + " " + UP;
		else if (direction == V_DOWN)
			return VERTICAL + " " + DOWN;
		else
			return null;
	}

	// transposes faceColor matrix
	private void transpose(String faceColor[][]) {
		for (int i = 0; i < faceColor.length; i++) {
			for (int j = 0; j < i; j++) {
				String temp = faceColor[i][j];
				faceColor[i][j] = faceColor[j][i];
				faceColor[j][i] = temp;
			}
		}
	}

	// performs rotation of faceColor by transpose method
	private void rotateFaceColor(String faceColor[][], boolean direction) {

		transpose(faceColor);

		if (direction) {
			for (int i = 0; i < faceColor.length; i++) {
				for (int j = 0; j < faceColor[0].length / 2; j++) {
					String temp = faceColor[i][j];
					faceColor[i][j] = faceColor[i][faceColor[0].length - j - 1];
					faceColor[i][faceColor[0].length - j - 1] = temp;
				}
			}
		} else {
			for (int i = 0; i < faceColor.length / 2; i++) {
				for (int j = 0; j < faceColor[0].length; j++) {
					String temp = faceColor[i][j];
					faceColor[i][j] = faceColor[faceColor.length - 1 - i][j];
					faceColor[faceColor.length - 1 - i][j] = temp;
				}
			}
		}

	}

	/**
	 * returns the circleHorizontal operation performed in String format
	 * 
	 * @param direction
	 * for direction H_RIGHT,H_RIGHT entire cube moves towards
	 * right,left direction respectively
	 * @return String
	 */
	public String circleHorizontal(boolean direction) {

		for (int count = 0; count < getDimension(); count++)
			horizontal(count, direction);

		if (direction == H_RIGHT)
			return CIRCLE + " " + HORIZONTAL + " " + RIGHT;
		else if (direction == H_LEFT)
			return CIRCLE + " " + HORIZONTAL + " " + LEFT;
		else
			return null;
	}

	/**
	 * returns the circleHorizontal operation performed in String format
	 * 
	 * @param direction
	 * for direction V_UP,V_DOWN the entire cube moves up,down
	 * direction respectively
	 * @return String
	 */
	public String circleVertical(boolean direction) {

		for (int count = 0; count < getDimension(); count++)
			vertical(count, direction);

		if (direction == V_UP)
			return CIRCLE + " " + VERTICAL + " " + UP;
		else if (direction == V_DOWN)
			return CIRCLE + " " + VERTICAL + " " + DOWN;
		else
			return null;
	}

	/**
	 * returns the circleRotate operation performed in String format
	 * 
	 * @param direction
	 * for direction R_CLK,R_ANTICLK entire cube moves in
	 * clk,anticlk direction respectively
	 * @return String
	 */
	public String circleRotate(boolean direction) {

		for (int count = 0; count < getDimension(); count++)
			rotate(count, direction);

		if (direction == R_CLK)
			return CIRCLE + " " + ROTATE + " " + CLKWISE;
		else if (direction == R_ANTICLK)
			return CIRCLE + " " + ROTATE + " " + ANTICLK;
		else
			return null;
	}

}