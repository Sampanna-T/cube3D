import javafx.util.Pair;
import java.util.List;

/**
 * Solves LayerFirst of RubiksCube and returns the solution pair list
 * @author Sampanna T (kashisadan16@gmail.com)
 * @version 1.0 29th June 2022
 * @since JDK 10.0.2
 */
class LayerFirst {
    
    private RubiksCube3X3 cube3X3;
    private List <Pair<String,String>>solution;
    
    
   	/** 
	* initializes LayerFirst object with cube object and solution list 
    * @param cube3X3
    * Reference to RubiksCube3X3 object
    * @param solution
    * Reference to a list containing [move to be performed, Rubikscube state] pair
	*/
    LayerFirst(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }

        
	/**
	* Solves LayerFirst and Adds [move,RubiksCube state] pair into the solution list
    * @throws Exception
    * if LayerFirst couldn't be solved  
	*/
    void solveAll()throws Exception{
        flipCube();
        solveLayerFirst();
        flipCube();
    }
    

    /**
	* flips the entire Cube in opposite direction  
	*/
    private void flipCube(){
        Solve3X3.add(cube3X3.circleVertical(RubiksCube3X3.V_UP),cube3X3,solution);
        Solve3X3.add(cube3X3.circleVertical(RubiksCube3X3.V_UP),cube3X3,solution);
    }


    //sets the corner piece At the proper location
    private void solveLayerFirst(){
        String upMidColor = cube3X3.getUpColor(1,0,1);
        boolean topFlag = false; //Tells if the required piece is present at 0,0,2

        for(int itr = 0; itr < 4; itr++){
            topFlag = placeThePiece();
            if(!isCornerSet()){
                if(topFlag){
                    if(cube3X3.getFrontColor(0,0,2).equals(upMidColor)){
                        Solve3X3.add(cube3X3.rotateFront(RubiksCube3X3.R_CLK),cube3X3,solution);                      
                        Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);                        
                        Solve3X3.add(cube3X3.rotateFront(RubiksCube3X3.R_ANTICLK),cube3X3,solution);                       
                        Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
                        Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
                        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
                        Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution); 
                        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_UP),cube3X3,solution);
                        
                    }
                    else if(cube3X3.getRightColor(0,0,2).equals(upMidColor)){
                        Algorithm3X3.L(cube3X3,solution);
                        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);  
                        Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);  
                        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_UP),cube3X3,solution);  
                    }
                }
                else{
                    if(cube3X3.getFrontColor(0,2,2).equals(upMidColor)){
                        Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution); 
                        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
                        Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
                        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_UP),cube3X3,solution);
                        
                    }
                    else if(cube3X3.getRightColor(0,2,2).equals(upMidColor)){
                        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
                        Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
                        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_UP),cube3X3,solution);
                    }
                    else if(cube3X3.getDownColor(0,2,2).equals(upMidColor)){
                        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
                        Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
                        Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution); 
                        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_UP),cube3X3,solution);  
                        Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution); 
                        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
                        Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
                        Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_UP),cube3X3,solution);
                    }
                }
            }
        topFlag = false;

        if(itr != 3)Solve3X3.add(cube3X3.circleHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
            
        } 
    }
 

    /**
	* Places the corner piece at index 0,0,2 or 0,2,2
    * and returns true if corner piece is placed at 0,0,2 else returns false
	*/
    private boolean placeThePiece(){
        if(isCornerValid(0,0,2)||isCornerValid(0,2,2)){  
            if(isCornerValid(0,0,2)) 
                return true;//Only case where piece ends up at position 0,0,2
        }
        else if(isCornerValid(0,0,0)){//else if 
            Solve3X3.add(cube3X3.leftVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
            Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution); 
            Solve3X3.add(cube3X3.leftVertical(RubiksCube3X3.V_UP),cube3X3,solution); 
        }
        else if(isCornerValid(0,2,0)){//else if
            Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution); 
        }//else if
        else if(isCornerValid(2,0,0)){//else if
            Solve3X3.add(cube3X3.rotateBack(RubiksCube3X3.R_ANTICLK),cube3X3,solution); 
            Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);  
            Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);  
            Solve3X3.add(cube3X3.rotateBack(RubiksCube3X3.R_CLK),cube3X3,solution);  
        }//else if
        else if(isCornerValid(2,2,0)){//else if
            Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
            Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
            
        }//else if
        else if(isCornerValid(2,0,2)){//else if
            Solve3X3.add(cube3X3.rotateBack(RubiksCube3X3.R_CLK),cube3X3,solution);
            Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
            Solve3X3.add(cube3X3.rotateBack(RubiksCube3X3.R_ANTICLK),cube3X3,solution);
            
        }//else if
        else if(isCornerValid(2,2,2)){//else if
            Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
        }//else if
        return false;
    }

   
    //returns true if the corner piece specified by i,j,k is valid
    private boolean isCornerValid(int i, int j, int k){
        String frontMidColor = cube3X3.getFrontColor(0,1,1);
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String rightMidColor = cube3X3.getRightColor(1,1,2);
        
        boolean isValid=false;
        
        String []colors = cube3X3.getColors(i, j, k);
        
        for(int index = 0; index < 3; index++){
            if(colors[index].equals(frontMidColor) || colors[index].equals(rightMidColor) || colors[index].equals(upMidColor)){
                isValid=true;
            }
            else{
                isValid=false;
                break;
            }
        }
        return isValid;
    }
    

	//returns true if the corner piece is set at the right position
    private boolean isCornerSet(){
        String frontMidColor = cube3X3.getFrontColor(0,1,1);
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String rightMidColor = cube3X3.getRightColor(1,1,2);

        
        if(cube3X3.getFrontColor(0,0,2).equals(frontMidColor) && cube3X3.getUpColor(0,0,2).equals(upMidColor) && cube3X3.getRightColor(0,0,2).equals(rightMidColor))
            return true;
        else 
            return false;
    }
    
}
