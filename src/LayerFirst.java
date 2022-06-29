/**
 * @file LayerFirst.java
 * @author Sampanna T (kashisadan16@gmail.com)
 * @brief 
 * Solves LayerFirst of RubiksCube & returns the solution pair list
 * 
 * @date 29th June 2022
 */
import javafx.util.Pair;
import java.util.List;
public class LayerFirst {
    
    private RubiksCube3X3 cube3X3;
    private String move;
    private List <Pair<String,String>>solution;
    

    /**
	* @brief 
	* initializes LayerFirst object with cube object & solution list 
	* @param cube3X3
    * @param solution
    * cube3X3 holds RubiksCube3X3 object
    * solution holds [step to be performed, RubiksCube state] pair
	*/
    public LayerFirst(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }

        
	/**
	* @brief
	* Solves LayerFirst & Adds [move,RubiksCube state] pair into the solution list  
	* @return void 
	*/
    public void solveAll()throws Exception{
        flipCube();
        solveLayerFirst();
    }
    

    /**
	* @brief
	* flips the entire Cube in opposite direction  
	* @return void 
	*/
    private void flipCube(){
        Solve3X3.add(cube3X3.circleVertical(RubiksCube3X3.V_UP),cube3X3,solution);
        Solve3X3.add(cube3X3.circleVertical(RubiksCube3X3.V_UP),cube3X3,solution);
    }

    /**
	* @brief 
	* to set the corner piece At the proper location
    * Returns true if corner piece is placed at 0,0,2 else returns false
	* @return boolean 
	*/
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
	* @brief 
	* Places the corner piece at index 0,0,2 or 0,2,2
    * Returns true if corner piece is placed at 0,0,2 else returns false
	* @return boolean 
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

   
    /**
	* @brief 
	* Checks if corner piece is valid or not
	* @param i,j,k
	* i,j,k represents the index of the 3X3 cube whole corner needs to be checked
    * @return boolean 
	*/
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
    

    /**
	* @brief 
	* checks whether the corner piece is in the right position or not
	* @return boolean 
	*/
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
