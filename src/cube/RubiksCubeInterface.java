package src.cube;

/**
 * Provides basic functionality necessary for rubiksCube of any dimension
 * 
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 1st June 2022
 * @since JDK 10.0.2
 */
public interface RubiksCubeInterface{

    
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


    /**
     * Provides basic functionality of Node which is the base to build the RubiksCube 
     */
    public static interface NodeInterface{		
        
        /**
        * max value permitted for size i.e for storing colors
        */
        public static final int MAX_SIZE = 3;

        /**
        * min value permitted for size i.e for storing colors
        */
        public static final int MIN_SIZE = 0;
       
        /**
		 * returns the size of color[]
		 * @return int
		 */
        public abstract int getSize();

        /**
		 * returns all the colors in the form of String[]
		 * @return String[]
		 */
        public abstract String[] getColors();

        /**
		 * returns color for given subIndex i.e index of color[]
		 * @param subIndex
		 * represents index of color[]
		 * @return String
		 */
        public abstract String getColor(int subIndex);
    }
    
    /**
	 * returns the dimension of Current RubiksCube object
	 * 
	 * @return int
	 */
    public abstract int getDimension();

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
    public abstract void setRubiksCube(boolean type, String colorInput)throws Exception;

    /**
	 * displays colors present in the cube based on type
	 * 
	 * @param type
	 * type is FACE_WISE,NODE_WISE then color would be displayed face wise, node wise respectively
	 */
    public abstract void display(boolean type);

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
    public abstract String getColor(int i, int j, int k, int subIndex);

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
    public abstract String[] getColors(int i, int j, int k);

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
    public abstract String getUpColor(int i, int j, int k);

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
    public abstract String getDownColor(int i, int j, int k);
    
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
    public abstract String getLeftColor(int i, int j, int k);
    
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
    public abstract String getRightColor(int i, int j, int k);
    
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
    public abstract String getBackColor(int i, int j, int k);
    
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
    public abstract String getFrontColor(int i, int j, int k);

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
    public abstract String rotate(int i, boolean direction);
    
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
    public abstract String horizontal(int j, boolean direction);

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
    public abstract String vertical(int k, boolean direction);
    
	/**
	 * returns the circleHorizontal operation performed in String format
	 * 
	 * @param direction
	 * for direction H_RIGHT,H_RIGHT entire cube moves towards
	 * right,left direction respectively
	 * @return String
	 */
    public abstract String circleHorizontal(boolean direction);
    	
    /**
	 * returns the circleHorizontal operation performed in String format
	 * 
	 * @param direction
	 * for direction V_UP,V_DOWN the entire cube moves up,down
	 * direction respectively
	 * @return String
	 */
    public abstract String circleVertical(boolean direction);
    	
    /**
	 * returns the circleRotate operation performed in String format
	 * 
	 * @param direction
	 * for direction R_CLK,R_ANTICLK entire cube moves in
	 * clk,anticlk direction respectively
	 * @return String
	 */
    public abstract String circleRotate(boolean direction);
}
