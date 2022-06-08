public abstract class CubeNode{
    public static final int MAX_SIZE = 3;//max value permitted for size i.e for storing colors
    public static final int MIN_SIZE = 0;//min value permitted for size i.e for storing colors
    
    /**
	* @brief 
	* returns the size of CubeNode
	*
    *
	* @return int 
	*/
    public abstract int getSize();


    /**
	* @brief 
	* returns size of color[] for given dimension & i,j,k value
    * 
	* @param i
	* @param j
	* @param k
	* @param dimension
    * i,j,k represents Node index of 3D cube
    * dimension represents the dimension of 3D cube
	*
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
	* @brief 
	* returns true if i,j,k value are valid for given dimension
    * 
	* @param i
	* @param j
	* @param k
	* @param dimension
    * i,j,k represents Node index of 3D cube
    * dimension represents the dimension of 3D cube
	*
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
	* @brief 
	* returns true if i,j,k represents a valid corner Node for given dimension i.e. color[] holding 3 colors
    * 
	* @param i
	* @param j
	* @param k
	* @param dimension
    * i,j,k represents Node index of 3D cube
    * dimension represents the dimension of 3D cube
	*
	* @return boolean 
	*/
    private static boolean isCornerNode(int i, int j, int k, int dimension){
        if(!isIndexValid(i,j,k,dimension))return false;

        boolean iValid = (i==0 || i==dimension-1);
        boolean jValid = (j==0 || j==dimension-1);
        boolean kValid = (k==0 || k==dimension-1);
        
        if(iValid && jValid && kValid)return true; 
        else return false;
    }


    /**
	* @brief 
	* returns true if i,j,k represents a valid edge Node for given dimension i.e. color[] holding 2 colors
    * 
	* @param i
	* @param j
	* @param k
	* @param dimension
    * i,j,k represents Node index of 3D cube
    * dimension represents the dimension of 3D cube
	*
	* @return boolean 
	*/
    private static boolean isEdgeNode(int i, int j, int k, int dimension){
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
	* @brief 
	* returns true if i,j,k represents a valid center Node for given dimension i.e. color[] holding 1 color
    * 
	* @param i
	* @param j
	* @param k
	* @param dimension
    * i,j,k represents Node index of 3D cube
    * dimension represents the dimension of 3D cube
	*
	* @return boolean 
	*/
    private static boolean isCenterNode(int i, int j, int k, int dimension){
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
	* @brief 
	* returns true if given value lies in the range of from to to
    * 
	* @param value
	* @param from
	* @param to
    * value represents the value to be checked if it is in the range 
    * from represents start range
    * to represents end range
	*
	* @return boolean 
	*/
    public static boolean isInRange(int value, int from, int to){
        if(value >= from && value <= to)return true;
        else return false;
    }


}