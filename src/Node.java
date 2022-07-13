/**
 * Provides functionality of a Node in the RubiksCube
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 3rd June 2022
 * @since JDK 10.0.2
 */ 
public class Node{

    /**
    * max value permitted for size i.e for storing colors
    */
    public static final int MAX_SIZE = 3;
    /**
    * min value permitted for size i.e for storing colors
    */
    public static final int MIN_SIZE = 0;
    
    //contains all the colors for a given Node
    private String color[];


    /**
	* 
	* initializes the Node 
	* @param size
	* represents the size of color[] 
	*/
    public Node(int size){
        createNode(size);
    }


    /**
	* 
	* creates a new String[] object and assigns it to color
	* @param size
    * represents the size of color[] being created 
	* @return void
	*/
    private void createNode(int size){
        if(size <= MAX_SIZE && size >= MIN_SIZE)color = new String[size];
        else color = null;
    }


    /** 
	* returns the size of color[]
	* @return int 
	*/
    public int getSize(){
        return color.length;
    }


    /**
	* returns size of color[] for given dimension and i,j,k value
	* @param i
	* represents index i out of (i,j,k) in 3D cube
	* @param j
	* represents index j out of (i,j,k) in 3D cube
	* @param k
	* represents index k out of (i,j,k) in 3D cube
    * @param dimension
    * represents the dimension of 3D cube
	* @return int 
	*/
    public static int getCount(int i, int j, int k, int dimension){
        if(!isIndexValid(i,j,k,dimension))return -1;

        if(isCornerNode(i,j,k,dimension))return 3;
        else if(isEdgeNode(i,j,k,dimension))return 2;
        else if(isCenterNode(i,j,k,dimension))return 1;
        else return 0;
    }


    /** 
	* returns true if i,j,k value are valid for given dimension
	* @param i
	* represents index i out of (i,j,k) in 3D cube
	* @param j
	* represents index j out of (i,j,k) in 3D cube
	* @param k
	* represents index k out of (i,j,k) in 3D cube
    * @param dimension
    * represents the dimension of 3D cube
	* @return boolean 
	*/
    public static boolean isIndexValid(int i, int j, int k, int dimension){
        if(dimension <= 1)return false;

        boolean iValid = isInRange(i,0,dimension-1);
        boolean jValid = isInRange(j,0,dimension-1);
        boolean kValid = isInRange(k,0,dimension-1);
        
        if(iValid && iValid && kValid)return true; 
        else return false;
    }


    /**
	* returns true if i,j,k represents a valid corner Node for given dimension i.e. color[] holding 3 colors
	* @param i
	* represents index i out of (i,j,k) in 3D cube
	* @param j
	* represents index j out of (i,j,k) in 3D cube
	* @param k
	* represents index k out of (i,j,k) in 3D cube
    * @param dimension
    * represents the dimension of 3D cube
	* @return boolean 
	*/
    public static boolean isCornerNode(int i, int j, int k, int dimension){
        if(!isIndexValid(i,j,k,dimension))return false;

        boolean iValid = (i==0 || i==dimension-1);
        boolean jValid = (j==0 || j==dimension-1);
        boolean kValid = (k==0 || k==dimension-1);
        
        if(iValid && jValid && kValid)return true; 
        else return false;
    }


    /**
	* returns true if i,j,k represents a valid edge Node for given dimension i.e. color[] holding 2 colors
	* @param i
	* represents index i out of (i,j,k) in 3D cube
	* @param j
	* represents index j out of (i,j,k) in 3D cube
	* @param k
	* represents index k out of (i,j,k) in 3D cube
    * @param dimension
    * represents the dimension of 3D cube
	* @return boolean 
	*/
    public static boolean isEdgeNode(int i, int j, int k, int dimension){
        if(!isIndexValid(i,j,k,dimension))return false;
        
        boolean ijValid = (i == 0 || i == dimension-1) && (j == 0 || j == dimension-1);
        boolean jkValid = (j == 0 || j == dimension-1) && (k == 0 || k == dimension-1);
        boolean kiValid = (k == 0 || k == dimension-1) && (i == 0 || i == dimension-1);

        boolean iValid = isInRange(i,1,dimension-2);
        boolean jValid = isInRange(j,1,dimension-2);
        boolean kValid = isInRange(k,1,dimension-2);

        if(ijValid && kValid)return true;
        else if(jkValid && iValid)return true;
        else if(kiValid && jValid)return true;

        return false;
        
    }


    /**
	* returns true if i,j,k represents a valid center Node for given dimension i.e. color[] holding 1 color
	* @param i
	* represents index i out of (i,j,k) in 3D cube
	* @param j
	* represents index j out of (i,j,k) in 3D cube
	* @param k
	* represents index k out of (i,j,k) in 3D cube
    * @param dimension
    * represents the dimension of 3D cube
	* @return boolean 
	*/
    public static boolean isCenterNode(int i, int j, int k, int dimension){
        if(!isIndexValid(i,j,k,dimension))return false;
        
        boolean iValid = (i == 0 || i == dimension-1);
        boolean jValid = (j == 0 || j == dimension-1);
        boolean kValid = (k == 0 || k == dimension-1);

        boolean jkValid = (isInRange(j,1,dimension-2) && isInRange(k,1,dimension-2));
        boolean ikValid = (isInRange(i,1,dimension-2) && isInRange(k,1,dimension-2));
        boolean ijValid = (isInRange(i,1,dimension-2) && isInRange(j,1,dimension-2));

        if(iValid && jkValid)return true;
        else if(jValid && ikValid)return true;
        else if(kValid && ijValid)return true;

        return false;
    }


    /**
	* returns true if given value lies in the range of from to to
	* @param value
    * value represents the value to be checked if it is in the range 
	* @param from
    * from represents start range
	* @param to
    * to represents end range
	* @return boolean 
	*/
    public static boolean isInRange(int value, int from, int to){
        if(value >= from && value <= to)return true;
        else return false;
    }


    /**
	* returns all the colors in the form of String[]
	* @return String[]
	*/
    public String[] getColors(){
        if(color == null)
            return null;
        else{
            String colors[] = new String[color.length];
            for(int i = 0; i < color.length; i++)colors[i] = new String(color[i]);
            return colors;
        }
    }


    /**
	* returns color for given subIndex i.e index of color[]
	* @param subIndex
    * represents index of color[] 
	* @return String 
	*/
    public String getColor(int subIndex){
        boolean isSubIndexValid = isInRange(subIndex,0,getSize()-1);

        if(isSubIndexValid)return color[subIndex];
        else return null;
    }


    /**
	* adds newColor to the color[] at given subIndex and returns true if valid
	* @param subIndex
    * represents index of color[]
	* @param newColor
    * represents the color to be set
	* @return boolean 
	*/
    public boolean setColor(int subIndex, String newColor){
        boolean isSubIndexValid = isInRange(subIndex,0,getSize()-1);

        if(isSubIndexValid){
            color[subIndex] = newColor;
            return true;
        }
        else return false;
    }
}