/**
 * @file Node.java
 * @author Sampanna T (kashisadan16@gmail.com)
 * @brief 
 * Holds color information & necessary methods for a given Node for Cube of any dimension
 * 
 * @date 7th June 2022
 */
 
public class Node extends CubeNode{

    private String color[];//contains all the colors for a given Node

    /**
	* @brief 
	* constructor to initialize the Node 
	*
	* @param size
	* represents the size of color[] 
    *
	*/
    public Node(int size){
        createNode(size);
    }

    /**
	* @brief 
	* creates a new String[] object and assigns it to color
    *
	* @param size
    * represents the size of color[] being created 
    *
	* @return void
	*/

    private void createNode(int size){
        if(size <= MAX_SIZE && size >= MIN_SIZE){
            color = new String[size];
        }
        else{
            color = null;
        }
    }


    /**
	* @brief 
	* returns the size of color[]
	*
    *
	* @return int 
	*/
    public int getSize(){
        return color.length;
    }


    /**
	* @brief 
	* returns color for given subIndex i.e index of color[]
    * 
	* @param subIndex
    * represents index of color[] 
    *
	* @return String 
	*/
    public String getColor(int subIndex){
        boolean isSubIndexValid = isInRange(subIndex,0,getSize()-1);

        if(isSubIndexValid)return color[subIndex];
        else return null;
    }

    
    /**
	* @brief 
	* adds newColor to the color[] at given subIndex & returns true if valid
    * 
	* @param subIndex
	* @param newColor
	* 
    *
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






/******************TESTING******************

    public static void main(String []args){
        Node n1 = new Node(3);
        Node n2 = new Node(2);
        Node n3 = new Node(1);
        Node n4 = new Node(0);
        System.out.println(n1.getSize()+" "+n2.getSize()+" "+n3.getSize()+" "+n4.getSize());

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                for(int k = 0; k < 8; k++){
                    System.out.print(i+"-"+j+"-"+k+" ("+getCount(i,j,k,8)+") ");
                }
                System.out.println();
            }
            System.out.println();
        }
        n1.color = new String[]{"red","blue","voilet"};
        n2.setColor(1,"black");
        System.out.println(n1.getColor(0)+" "+n1.getColor(1)+" "+n1.getColor(2)+" "+n2.getColor(1));
    }
 */