/**
 * @file Corner.java
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @brief 
 * Solves Corner of RubiksCube & returns the solution pair list
 * 
 * @date 30th June 2022
 */
import javafx.util.Pair;
import java.util.List;

public class Corner{

    private RubiksCube3X3 cube3X3;
    private List <Pair<String,String>>solution;
    
    /**
	* @brief 
	* initializes Corner object with cube object & solution list 
	* @param cube3X3
    * @param solution
    * cube3X3 holds RubiksCube3X3 object
    * solution holds [step to be performed, RubiksCube state] pair
	*/
    public Corner(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }
      
      
    /**
	* @brief
	* partially solves all the corner pieces by positioning them in right way  
	* @return void 
	*/
    public void solveAll()throws Exception{
        solveCorner();
    }
    

    /**
	* @brief
	* partially solves all the corner pieces by positioning them in right way  
	* @return void 
	*/
    private void solveCorner()throws Exception{
        byte loopCheck = 0;
        while(!isAllCornerAligned()){
            
            if(++loopCheck>100)throw new Exception("INFINTE LOOP");
            
            if(isAnyCornerAligned()){
               while(!isFrontRightCornerValid()){
                    if(++loopCheck>100)throw new Exception("INFINTE LOOP");
                    Solve3X3.add(cube3X3.circleHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
               }
               while(!isAllCornerAligned()){               
                    if(++loopCheck>100)throw new Exception("INFINTE LOOP");
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
