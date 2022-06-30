

//******************************************************************************************* */
//******************************************************************************************* */
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS



import javafx.util.Pair;
import java.util.List;
public class LayerFirst {
    
    private RubiksCube3X3 cube3X3;
    private String move;
    private List <Pair<String,String>>solution;
    

    /**
	* @brief 
	* initializes LayerFirst object with cube objectect & solution list 
	* @return void
	*/
    public LayerFirst(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }

        
	/**
	* @brief
	* Adds [move,RubiksCube state] pair into the solution list  
	* @return void 
	*/
    public void solveAll()throws Exception{
        solveLayerFirst();
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
                        (new Algorithm(cube3X3)).L(true,true,(++count).toString());//firstuse
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

    
}

//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS




import javafx.util.Pair;
import java.util.List;

public class LayerSecond {

    private RubiksCube3X3 cube3X3;
    private String move;
    private List <Pair<String,String>>solution;
    
    /**
	* @brief 
	* initializes LayerSecond object with cube object & solution list 
	* @param cube3X3
    * @param solution
    * cube3X3 holds RubiksCube3X3 object
    * solution holds [step to be performed, RubiksCube state] pair
	*/
    public LayerSecond(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }
    
    
    /**
	* @brief
	* Solves LayerSecond & Adds [move,RubiksCube state] pair into the solution list   
	* @return void 
	*/
    public void solveAll(){
        solveUpEdgePieces();
        solveMiddleEdgePieces();
    } 

    
    /**
	* @brief
	* solves the second layer using all the edge pieces on the UP layer
	* @return void 
	*/
    public void solveUpEdgePieces(){
        byte loopCheck = 0;
        while(isAnyUpEdgePieceValid()){//while
            loopCheck++;
            if(loopCheck>100)throw new Exception("INFINTE LOOP");

            if(isUpEdgePieceValid()){
               
                while(!cube3X3.getFrontColor(0,0,1).equals(cube3X3.getFrontColor(0,1,1))){
                    loopCheck++;
                    if(loopCheck>100)throw new Exception("INFINTE LOOP");
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
    
 
    /**
	* @brief
	* solves the unsolved pieces in the middle layer
	* @return void 
	*/
    public void solveMiddleEdgePieces(){
        byte loopCheck = 0;
        while(isAnyMiddleEdgePieceInvalid()){//whileMain
            loopCheck++;
            if(loopCheck>100)throw new Exception("INFINTE LOOP");

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


    /**
	* @brief
	* checks if there is any valid edge piece at the Up layer  
	* @return boolean 
	*/
    public boolean isAnyUpEdgePieceValid(){
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
    
     
    /**
	* @brief
	* checks if the edge piece at (i,j,k) = (0,0,1) is valid  
	* @return boolean 
	*/
    public boolean isUpEdgePieceValid(){
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String frontFUMColor = cube3X3.getFrontColor(0,0,1);
        String upFUMColor = cube3X3.getUpColor(0,0,1);
        
        boolean isFrontPieceValid = !frontFUMColor.equals(upMidColor) && !upFUMColor.equals(upMidColor);
        
        if(isFrontPieceValid)return true;
        else return false;
    }
    
    
    /**
	* @brief
	* checks if any edge piece in the middle horizontal layer is invalid/(incorrectly placed)
	* @return boolean 
	*/
    public boolean isAnyMiddleEdgePieceInvalid(){
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

//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSssss
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSssss
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSssss
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSssss
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSssss



/**
 * @file PlusTop.java
 * @author Sampanna T (kashisadan16@gmail.com)
 * @brief 
 * Solves PlusTop of RubiksCube & returns the solution pair list
 * 
 * @date 30th June 2022
 */
public class PlusTop {

    private RubiksCube3X3 cube3X3;
    private List <Pair<String,String>>solution;
    
    /**
	* @brief 
	* initializes PlusTop object with cube object & solution list 
	* @param cube3X3
    * @param solution
    * cube3X3 holds RubiksCube3X3 object
    * solution holds [step to be performed, RubiksCube state] pair
	*/
    public PlusTop(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }
    
    /**
	* @brief
	* solves the PlusTop
	* @return void 
	*/
    public void solveAll()throws Exception{
        solvePlus();
    }

    /**
	* @brief
	* solves the plus on top
	* @return void 
	*/
    private void solvePlus()throws Exception{
        byte loopCheck = 0;
        while(!isTopPlus()){//while
            if(isTopDot()){
                Algorithm.twistFront(cube3X3,solution);
            }
            else{
                while(true){
                    loopCheck++;
                    if(loopCheck>100)throw new Exception("INFINTE LOOP");

                    if(isTopLORMinus() || isTopDot())break;
                    else Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);       
                }
                Algorithm.twistFront(cube3X3,solution);
            }
        }//while
    }


    private boolean isTopPlus(){
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String upFUMColor = cube3X3.getUpColor(0,0,1);
        String upMULColor = cube3X3.getUpColor(1,0,0);
        String upBUMColor = cube3X3.getUpColor(2,0,1);
        String upMURColor = cube3X3.getUpColor(1,0,2);
        
        if(upFUMColor.equals(upMidColor) && upMULColor.equals(upMidColor) && upBUMColor.equals(upMidColor) && upMURColor.equals(upColor))
            return true;
        else
            return false;
    }
    
    private boolean isTopDot(){
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String upFUMColor = cube3X3.getUpColor(0,0,1);
        String upMULColor = cube3X3.getUpColor(1,0,0);
        String upBUMColor = cube3X3.getUpColor(2,0,1);
        String upMURColor = cube3X3.getUpColor(1,0,2);
        
        if(!upFUMColor.equals(upMidColor) && !upMULColor.equals(upMidColor) && !upBUMColor.equals(upMidColor) && !upMURColor.equals(upMidColor))
            return true;
        else
            return false;
    }
    
    private boolean isTopLORMinus(){
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String upFUMColor = cube3X3.getUpColor(0,0,1);
        String upMULColor = cube3X3.getUpColor(1,0,0);
        String upBUMColor = cube3X3.getUpColor(2,0,1);
        String upMURColor = cube3X3.getUpColor(1,0,2);
        boolean isTopL = upMULColor.equals(upMidColor) && upBUMColor.equals(upMidColor);
        boolean isTopMinus = upMULColor.equals(upMidColor) && upMURColor.equals(upMidColor);

        if(isTopL || isTopMinus)return true;
        else return false;
    }
    
}




//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS


import javafx.util.Pair;
import java.util.List;

public class Corner{

    private RubiksCube3X3 cube3X3;
    private List <Pair<String,String>>solution;
    
    public Corner(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }
    
    public void solveAll(){
        solveCorner();
    }
    

    /**
	* @brief
	* partially solves all the corner pieces by positioning them in right way  
	* @return boolean 
	*/
    private void solveCorner(){
        while(!isAllCornerAligned()){
            loopCheck++;
            if(loopCheck>100)throw new Exception("INFINTE LOOP");
            if(isAnyCornerAligned()){
               while(!isFrontRightCornerValid()){
                    loopCheck++;
                    if(loopCheck>100)throw new Exception("INFINTE LOOP");
                    Solve3X3.add(cube3X3.circleHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
               }
               while(!isAllCornerAligned()){         
                    loopCheck++;
                    if(loopCheck>100)throw new Exception("INFINTE LOOP");
                    Algorithm3X3.corner(cube3X3,solution);
               }
            }
            else{
                Algorithm3X3.corner(cube3X3,solution);
            }
        }
    }


    /**
	* @brief
	* checks if front left corner piece is valid or not  
	* @return boolean 
	*/
    private boolean isFrontLeftCornerValid(){
        String frontMidColor = cube3X3.getFrontColor(0,1,1);
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String leftMidColor = cube3X3.getLeftColor(1,1,0);
        boolean isValid=false;
        
        String []colors = cube3X3.getColors(0,0,0);
        
        for(int index = 0; index < 3; index++){
            if(colors[index].equals(frontMidColor) || colors[index].equals(leftMidColor) || colors[index].equals(upMidColor)){
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
	* checks if back left corner piece is valid or not
	* @return boolean 
	*/
    private boolean isBackLeftCornerValid(){
        String backMidColor = cube3X3.getBackColor(2,1,1);
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String leftMidColor = cube3X3.getLeftColor(1,1,0);
        boolean isValid=false;
        
        String []colors = cube3X3.getColors(2,0,0);
        
        for(int index = 0; index < 3; index++){
            if(colors[index].equals(backMidColor) || colors[index].equals(leftMidColor) || colors[index].equals(upMidColor)){
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
	* checks if back right corner piece is valid or not  
	* @return boolean 
	*/
    private boolean isBackRightCornerValid(){
        String backColor = cube3X3.getBackColor(2,1,1);
        String upColor = cube3X3.getUpColor(1,0,1);
        String rightColor = cube3X3.getRightColor(1,1,2);
        boolean isValid=false;
        
        String []colors = cube3X3.getColors(2,0,2);
        
        for(int index = 0; index < 3; index++){
            if(colors[index].equals(backColor) || colors[index].equals(rightColor) || colors[index].equals(upColor)){
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
	* checks if front right corner piece is valid or not  
	* @return boolean 
	*/
    private boolean isFrontRightCornerValid(){
        String frontMidColor = cube3X3.getFrontColor(0,1,1);
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String rightMidColor = cube3X3.getRightColor(1,1,2);
        boolean isValid=false;
        
        String []colors = cube3X3.getColors(0,0,2);
        
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
	* checks if all the corner pieces are valid or not  
	* @return boolean 
	*/
    private boolean isAllCornerAligned(){
        if(isFrontLeftCornerValid() && isBackLeftCornerValid() && isBackRightCornerValid() && isFrontRightCornerValid() )return true;
        else return false;
    }
    
    
    /**
	* @brief
	* checks if any one of the corner piece is valid or not  
	* @return boolean 
	*/
    private boolean isAnyCornerAligned(){
        if(isFrontLeftCornerValid() || isBackLeftCornerValid() || isBackRightCornerValid() || isFrontRightCornerValid() )return true;
        else return false;
    }

}



//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
