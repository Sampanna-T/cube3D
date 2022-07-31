package com.cube.solve;
 
import javafx.util.Pair;
import java.util.List;
import com.cube.RubiksCube3X3;

/**
 * Solves LayerSecond of RubiksCube and returns the solution pair list
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 29th June 2022
 * @since JDK 10.0.2
 */
class LayerSecond {

    private RubiksCube3X3 cube3X3;
    private List <Pair<String,String>>solution;
    
    /**
	* initializes LayerSecond object with cube object and solution list 
    * @param cube3X3
    * Reference to RubiksCube3X3 object
    * @param solution
    * Reference to a list containing [move to be performed, Rubikscube state] pair
	*/
    LayerSecond(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }
    
    
    /**
	* Solves LayerSecond and Adds [move,RubiksCube state] pair into the solution list   
    * @throws Exception
    * if LayerSecond couldn't be solved
	*/
    void solveAll()throws Exception{
        if(!Optimizer.isLayerFirstSolved(cube3X3))throw new Exception("SOLVE LAYERFIRST");
        solveUpEdgePieces();
        solveMiddleEdgePieces();
    } 


    //solves the second layer using all the edge pieces on the UP layer 
    private void solveUpEdgePieces()throws Exception{
        byte loopCheck = 0;
        while(isAnyUpEdgePieceValid()){//while
            
            if(++loopCheck>100)throw new Exception("INFINTE LOOP");

            if(isUpEdgePieceValid()){
               
                while(!cube3X3.getFrontColor(0,0,1).equals(cube3X3.getFrontColor(0,1,1))){
                    
                    if(++loopCheck>100)throw new Exception("INFINTE LOOP");
                    Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
                    Solve3X3.add(cube3X3.circleHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
                }
                if(cube3X3.getUpColor(0,0,1).equals(cube3X3.getLeftColor(1,1,0)))
                    Algorithm3X3.layer2R(cube3X3,solution);
                else if(cube3X3.getUpColor(0,0,1).equals(cube3X3.getRightColor(1,1,2)))
                    Algorithm3X3.layer2L(cube3X3,solution);
                
            }//if color up is valid
            else{
                Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
            }//if color up is not valid

        }//while

    }
    
 
	//solves the unsolved pieces in the middle layer 	
    private void solveMiddleEdgePieces()throws Exception{
        byte loopCheck = 0;
        while(isAnyMiddleEdgePieceInvalid()){//whileMain
            
            if(++loopCheck>100)throw new Exception("INFINTE LOOP");

            String frontMidColor=cube3X3.getFrontColor(0,1,1);
            String rightMidColor=cube3X3.getRightColor(1,1,2);
            String frontFMRColor=cube3X3.getFrontColor(0,1,2);
            String rightFMRColor=cube3X3.getRightColor(0,1,2);
            
            //checks if FMR piece is at the right position or not
            if( !(frontFMRColor.equals(frontMidColor) && rightFMRColor.equals(rightMidColor)) ){
                Algorithm3X3.layer2L(cube3X3,solution);
                solveUpEdgePieces();
            }//if
            else if(isAnyMiddleEdgePieceInvalid()){
                Solve3X3.add(cube3X3.circleHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
            }//else if
        }//whileMain
    }


	//checks if there is any valid edge piece at the Up layer  
    private boolean isAnyUpEdgePieceValid(){
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String frontFUMColor = cube3X3.getFrontColor(0,0,1);
        String upFUMColor = cube3X3.getUpColor(0,0,1);
        String leftMULColor = cube3X3.getLeftColor(1,0,0);
        String upMULColor = cube3X3.getUpColor(1,0,0);
        String backBUMColor = cube3X3.getBackColor(2,0,1);
        String upBUMColor = cube3X3.getUpColor(2,0,1);
        String rightMURColor = cube3X3.getRightColor(1,0,2);
        String upMURColor = cube3X3.getUpColor(1,0,2);
       
        boolean isFrontPieceValid = !frontFUMColor.equals(upMidColor) && !upFUMColor.equals(upMidColor);
        boolean isLeftPieceValid = !leftMULColor.equals(upMidColor) && !upMULColor.equals(upMidColor);
        boolean isBackPieceValid = !backBUMColor.equals(upMidColor) && !upBUMColor.equals(upMidColor);
        boolean isRightPieceValid = !rightMURColor.equals(upMidColor) && !upMURColor.equals(upMidColor);
        
        if(isFrontPieceValid || isLeftPieceValid || isBackPieceValid || isRightPieceValid)return true;
        else return false;
    }
    
     
    //checks if the edge piece at (i,j,k) = (0,0,1) is valid  
    private boolean isUpEdgePieceValid(){
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String frontFUMColor = cube3X3.getFrontColor(0,0,1);
        String upFUMColor = cube3X3.getUpColor(0,0,1);
        
        boolean isFrontPieceValid = !frontFUMColor.equals(upMidColor) && !upFUMColor.equals(upMidColor);
        
        if(isFrontPieceValid)return true;
        else return false;
    }
    
    
	//checks if any edge piece in the middle horizontal layer is invalid/(incorrectly placed)
    private boolean isAnyMiddleEdgePieceInvalid(){
        String frontMidColor = cube3X3.getFrontColor(0,1,1);
        String leftMidColor = cube3X3.getLeftColor(1,1,0);
        String backMidColor = cube3X3.getBackColor(2,1,1);
        String rightMidColor = cube3X3.getRightColor(1,1,2);
        
        String frontFMLColor = cube3X3.getFrontColor(0, 1, 0);
        String leftFMLColor = cube3X3.getLeftColor(0, 1, 0);
        String leftBMLColor = cube3X3.getLeftColor(2, 1, 0);
        String backBMLColor = cube3X3.getBackColor(2, 1, 0);
        String backBMRColor = cube3X3.getBackColor(2, 1, 2);
        String rightBMRColor = cube3X3.getRightColor(2, 1, 2);
        String rightFMRColor = cube3X3.getRightColor(0, 1, 2);
        String frontFMRColor = cube3X3.getFrontColor(0, 1, 2);
        
        boolean isFMLValid = (frontFMLColor.equals(frontMidColor) && leftFMLColor.equals(leftMidColor));
        boolean isBMLValid = (leftBMLColor.equals(leftMidColor) && backBMLColor.equals(backMidColor));
        boolean isBMRValid = (backBMRColor.equals(backMidColor) && rightBMRColor.equals(rightMidColor));
        boolean isFMRValid = (rightFMRColor.equals(rightMidColor) && frontFMRColor.equals(frontMidColor));
        
        if(!isFMLValid || !isBMLValid || !isBMRValid || !isFMRValid)return true;
        else return false;
    }

}

