package com.cube;

import java.util.Scanner;

/**
 * Provides implementation of Cube Interface and allows
 * RubiksCubes of different dimensions to extend all the functionality
 * 
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 2nd June 2022
 * @since JDK 10.0.2
 */
public abstract class RubiksCube {

	/**
	 * A constant field holding number of colors in a center node
	 */
	public static final int CENTER_COUNT = 1;

	/**
	 * A constant field holding number of colors in an edge node
	 */
	public static final int EDGE_COUNT = 2;

	/**
	 * A constant field holding number of colors in a corner node
	 */
	public static final int CORNER_COUNT = 3;

	/**
	 * A constant field holding number of faces in a RubiksCube
	 */
	public static final int FACE_COUNT = 6;

	/**
	 * A constant field holding front layer name
	 */
	public static final String FRONT = "FRONT";

	/**
	 * A constant field holding back layer name
	 */
	public static final String BACK = "BACK";

	/**
	 * A constant field holding left layer name
	 */
	public static final String LEFT = "LEFT";

	/**
	 * A constant field holding right layer name
	 */
	public static final String RIGHT = "RIGHT";

	/**
	 * A constant field holding up layer name
	 */
	public static final String UP = "UP";

	/**
	 * A constant field holding down layer name
	 */
	public static final String DOWN = "DOWN";

	/**
	 * A constant field holding clkwise moment name
	 */
	public static final String CLKWISE = "CLKWISE";

	/**
	 * A constant field holding anti-clkwise moment name
	 */
	public static final String ANTICLK = "ANTI-CLKWISE";

	/**
	 * A constant field holding rotate moment name
	 */
	public static final String ROTATE = "ROTATE";

	/**
	 * A constant field holding vertical moment name
	 */
	public static final String VERTICAL = "VERTICAL";

	/**
	 * A constant field holding horizontal moment name
	 */
	public static final String HORIZONTAL = "HORIZONTAL";

	/**
	 * A constant field holding circle moment name
	 */
	public static final String CIRCLE = "CIRCLE";

	/**
	 * A constant field holding the name layer
	 */
	public static final String LAYER = "LAYER";

	/**
	 * A constant field indicating that operation to be performed face wise
	 */
	public static final boolean FACE_WISE = true;

	/**
	 * A constant field indicating that operation to be performed node wise
	 */
	public static final boolean NODE_WISE = false;

	/**
	 * A constant field indicating that string input is provided
	 */
	public static final boolean STRING_INPUT = true;

	/**
	 * A constant field indicating that user input must be provided
	 */
	public static final boolean USER_INPUT = false;

	/**
	 * A constant field indicating that operation to be performed is rotate clkwise
	 */
	public static final boolean R_CLK = true;

	/**
	 * A constant field indicating that operation to be performed is rotate
	 * anti-clkwise
	 */
	public static final boolean R_ANTICLK = false;

	/**
	 * A constant field indicating that operation to be performed is horizontal
	 * right
	 */
	public static final boolean H_RIGHT = true;

	/**
	 * A constant field indicating that operation to be performed is horizontal left
	 */
	public static final boolean H_LEFT = false;

	/**
	 * A constant field indicating that operation to be performed is vertical up
	 */
	public static final boolean V_UP = true;

	/**
	 * A constant field indicating that operation to be performed is vertical down
	 */
	public static final boolean V_DOWN = false;

	private int dimension;
	private Node piece[][][];

	/**
	 * constructor which initializes the RubiksCube by creating Nodes
	 * 
	 * @param dimension
	 *                  represents dimension of the RubiksCube
	 */
	public RubiksCube(int dimension) {
		this.dimension = dimension;
		piece = new Node[dimension][dimension][dimension];
		createNodes();
	}

	// creates nodes and adds it in 3D cube
	private void createNodes() {
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				for (int k = 0; k < dimension; k++)
					piece[i][j][k] = new Node(Node.getCount(i, j, k, dimension));
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
	 *                   specifies if the input is from user or colorInput(type can
	 *                   be STRING_INPUT or USER_INPUT)
	 * @param colorInput
	 *                   holds all the colors of the RubiksCube to be initialized
	 * @throws Exception
	 *                   If the RubiksCube couldn't be initialized
	 */
	public void setRubiksCube(boolean type, String colorInput) throws Exception {
		Scanner input = null;

		if (type == STRING_INPUT) {
			if (colorInput.split(" ").length != (dimension * dimension * FACE_COUNT))
				throw new Exception("INVALID COLOR INPUT");
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

		if (!ValidateCube.isNodeValid(this) || !ValidateCube.isColorValid(this)) {
			piece = null;
			throw new Exception("INVALID COLOR INPUT");
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
					int colorSize = Node.getCount(i, j, k, dimension);
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

	/**
	 * displays colors present in the cube Face wise
	 */
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
	 *                 represents index i out of (i,j,k) in 3D cube
	 * @param j
	 *                 represents index j out of (i,j,k) in 3D cube
	 * @param k
	 *                 represents index k out of (i,j,k) in 3D cube
	 * @param subIndex
	 *                 represents the index within the Node
	 * @return String
	 */
	public String getColor(int i, int j, int k, int subIndex) {
		return piece[i][j][k].getColor(subIndex);
	}

	/**
	 * returns all the colors of given index of 3D cube in String[] format
	 * 
	 * @param i
	 *          represents index i out of (i,j,k) in 3D cube
	 * @param j
	 *          represents index j out of (i,j,k) in 3D cube
	 * @param k
	 *          represents index k out of (i,j,k) in 3D cube
	 * @return String[]
	 */
	public String[] getColors(int i, int j, int k) {
		return piece[i][j][k].getColors();
	}

	/**
	 * returns the up color of 3D cube in String format for given Node
	 * 
	 * @param i
	 *          represents index i out of (i,j,k) in 3D cube
	 * @param j
	 *          represents index j out of (i,j,k) in 3D cube
	 * @param k
	 *          represents index k out of (i,j,k) in 3D cube
	 * @return String
	 */
	public String getUpColor(int i, int j, int k) {
		return getUpDownColor(i, j, k, UP);
	}

	/**
	 * returns the down color of 3D cube in String format for given Node
	 * 
	 * @param i
	 *          represents index i out of (i,j,k) in 3D cube
	 * @param j
	 *          represents index j out of (i,j,k) in 3D cube
	 * @param k
	 *          represents index k out of (i,j,k) in 3D cube
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
	 *          represents index i out of (i,j,k) in 3D cube
	 * @param j
	 *          represents index j out of (i,j,k) in 3D cube
	 * @param k
	 *          represents index k out of (i,j,k) in 3D cube
	 * @return String
	 */
	public String getLeftColor(int i, int j, int k) {
		return getLeftRightColor(i, j, k, LEFT);
	}

	/**
	 * returns the right color of 3D cube in String format for given Node
	 * 
	 * @param i
	 *          represents index i out of (i,j,k) in 3D cube
	 * @param j
	 *          represents index j out of (i,j,k) in 3D cube
	 * @param k
	 *          represents index k out of (i,j,k) in 3D cube
	 * @return String
	 */
	public String getRightColor(int i, int j, int k) {
		return getLeftRightColor(i, j, k, RIGHT);
	}

	// returns leftcolor,rightcolor for given index if type is LEFT,RIGHT
	// respectively
	private String getLeftRightColor(int i, int j, int k, String type) {
		if (type.equals(LEFT) && k != 0)
			return null;
		else if (type.equals(RIGHT) && (k != dimension - 1))
			return null;
		else
			return getColor(i, j, k, Node.getCount(i, j, k, dimension) - 1);
	}

	/**
	 * returns the back color of 3D cube in String format for given Node
	 * 
	 * @param i
	 *          represents index i out of (i,j,k) in 3D cube
	 * @param j
	 *          represents index j out of (i,j,k) in 3D cube
	 * @param k
	 *          represents index k out of (i,j,k) in 3D cube
	 * @return String
	 */
	public String getBackColor(int i, int j, int k) {
		return getFrontBackColor(i, j, k, BACK);
	}

	/**
	 * returns the right color of 3D cube in String format for given Node
	 * 
	 * @param i
	 *          represents index i out of (i,j,k) in 3D cube
	 * @param j
	 *          represents index j out of (i,j,k) in 3D cube
	 * @param k
	 *          represents index k out of (i,j,k) in 3D cube
	 * @return String
	 */
	public String getFrontColor(int i, int j, int k) {
		return getFrontBackColor(i, j, k, FRONT);
	}

	// returns frontcolor,backcolor for given index if type is FRONT,BACK
	// respectively
	private String getFrontBackColor(int i, int j, int k, String type) {
		if (type.equals(FRONT) && (i != 0))
			return null;
		else if (type.equals(BACK) && (i != dimension - 1))
			return null;
		else
			return getColor(i, j, k, 0);
	}

	/**
	 * returns the color of given index of 3D cube in String format
	 * 
	 * @param i
	 *                 represents index i out of (i,j,k) in 3D cube
	 * @param j
	 *                 represents index j out of (i,j,k) in 3D cube
	 * @param k
	 *                 represents index k out of (i,j,k) in 3D cube
	 * @param subIndex
	 *                 represents the index of color[] within given Node
	 * @param color
	 *                 index represents the index within the Node
	 *                 color represents the color to be set
	 */
	public void setColor(int i, int j, int k, int subIndex, String color) {
		piece[i][j][k].setColor(subIndex, color);
	}

	/**
	 * sets the up color of 3D cube in String format for given Node
	 * 
	 * @param i
	 *              represents index i out of (i,j,k) in 3D cube
	 * @param j
	 *              represents index j out of (i,j,k) in 3D cube
	 * @param k
	 *              represents index k out of (i,j,k) in 3D cube
	 * @param color
	 *              color represents the color to be set
	 */
	public void setUpColor(int i, int j, int k, String color) {
		setUpDownColor(i, j, k, UP, color);
	}

	/**
	 * sets the down color of 3D cube in String format for given Node
	 * 
	 * @param i
	 *              represents index i out of (i,j,k) in 3D cube
	 * @param j
	 *              represents index j out of (i,j,k) in 3D cube
	 * @param k
	 *              represents index k out of (i,j,k) in 3D cube
	 * @param color
	 *              color represents the color to be set
	 */
	public void setDownColor(int i, int j, int k, String color) {
		setUpDownColor(i, j, k, DOWN, color);
	}

	// sets upcolor,downcolor for given index if type is UP,DOWN respectively as
	// color
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

	/**
	 * sets the left color of 3D cube in String format for given Node
	 * 
	 * @param i
	 *              represents index i out of (i,j,k) in 3D cube
	 * @param j
	 *              represents index j out of (i,j,k) in 3D cube
	 * @param k
	 *              represents index k out of (i,j,k) in 3D cube
	 * @param color
	 *              color represents the color to be set
	 */
	public void setLeftColor(int i, int j, int k, String color) {
		setLeftRightColor(i, j, k, LEFT, color);
	}

	/**
	 * sets the right color of 3D cube in String format for given Node
	 * 
	 * @param i
	 *              represents index i out of (i,j,k) in 3D cube
	 * @param j
	 *              represents index j out of (i,j,k) in 3D cube
	 * @param k
	 *              represents index k out of (i,j,k) in 3D cube
	 * @param color
	 *              color represents the color to be set
	 */
	public void setRightColor(int i, int j, int k, String color) {
		setLeftRightColor(i, j, k, RIGHT, color);
	}

	// sets leftcolor,rightcolor for given index if type is LEFT,RIGHT respectively
	// as color
	private void setLeftRightColor(int i, int j, int k, String type, String color) {
		if (type.equals(LEFT) && k != 0)
			return;
		else if (type.equals(RIGHT) && (k != dimension - 1))
			return;
		else
			setColor(i, j, k, Node.getCount(i, j, k, dimension) - 1, color);

	}

	/**
	 * sets the back color of 3D cube in String format for given Node
	 * 
	 * @param i
	 *              represents index i out of (i,j,k) in 3D cube
	 * @param j
	 *              represents index j out of (i,j,k) in 3D cube
	 * @param k
	 *              represents index k out of (i,j,k) in 3D cube
	 * @param color
	 *              color represents the color to be set
	 */
	public void setBackColor(int i, int j, int k, String color) {
		setFrontBackColor(i, j, k, BACK, color);
	}

	/**
	 * sets the right color of 3D cube in String format for given Node
	 * 
	 * @param i
	 *              represents index i out of (i,j,k) in 3D cube
	 * @param j
	 *              represents index j out of (i,j,k) in 3D cube
	 * @param k
	 *              represents index k out of (i,j,k) in 3D cube
	 * @param color
	 *              color represents the color to be set
	 */
	public void setFrontColor(int i, int j, int k, String color) {
		setFrontBackColor(i, j, k, FRONT, color);
	}

	// sets frontcolor,backcolor for given index if type is FRONT,BACK respectively
	// as color
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
	 *                  i represents the layer of the cube to be rotated
	 * @param direction
	 *                  for direction R_CLK,R_ANTICLK rotation of ith layer of
	 *                  cube happens in clkwise,anticlk direction respectively
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
	 *                  j represents the layer of the cube to be moved horizontally
	 * @param direction
	 *                  for direction H_RIGHT,H_LEFT horizontal move of jth layer of
	 *                  cube happens in horizontal right,horizontal left direction
	 *                  respectively
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
	 *                  k represents the layer of the cube to be moved vertically
	 * @param direction
	 *                  for direction V_UP,V_DOWN vertical move of kth layer of
	 *                  cube happens in vertical up,vertical down direction
	 *                  respectively
	 * @return String
	 */
	public String vertical(int k, boolean direction) {
		int lengthSide = getDimension() * 4;

		// storing all the face colors & rotate it & then set it in the RubiksCube
		if ((k == 0) || (k == getDimension() - 1)) {
			String faceColor[][] = new String[getDimension()][getDimension()];

			for (int j = 0; j < getDimension(); j++)
				for (int i = 0; i < getDimension(); i++)
					faceColor[j][i] = getColor(i, j, k, Node.getCount(i, j, k, getDimension()) - 1);

			rotateFaceColor(faceColor, direction);

			for (int j = 0; j < getDimension(); j++)
				for (int i = 0; i < getDimension(); i++)
					setColor(i, j, k, Node.getCount(i, j, k, getDimension()) - 1, faceColor[j][i]);
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
	 *                  for direction H_RIGHT,H_RIGHT entire cube moves towards
	 *                  right,left direction respectively
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
	 *                  for direction V_UP,V_DOWN the entire cube moves up,down
	 *                  direction respectively
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
	 *                  for direction R_CLK,R_ANTICLK entire cube moves in
	 *                  clk,anticlk direction respectively
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