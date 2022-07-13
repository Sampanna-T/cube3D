import javafx.util.Pair;
import java.util.List;

/**
 * Solves Corner of RubiksCube and returns the solution pair list
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 30th June 2022
 * @since JDK 10.0.2
 */
class Corner{

    private RubiksCube3X3 cube3X3;
    private List <Pair<String,String>>solution;
    
    /** 
	* initializes Corner object with cube object and solution list 
    * @param cube3X3
    * Reference to RubiksCube3X3 object
    * @param solution
    * Reference to a list containing [move to be performed, Rubikscube state] pair
	*/
    Corner(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }
      
      
    /**
	* partially solves all the corner pieces by positioning them in respective corners 
    * @throws Exception
    * If Corner couldn't be solved 
	*/
    void solveAll()throws Exception{
        solveCorner();
    }
    

	//partially solves all the corner pieces by positioning them in right way  
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


	//returns true if front left corner piece is valid 
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
    
      

	//returns true if back left corner piece is valid
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
    

    //returns true if back right corner piece is valid
    private boolean isBackRightCornerValid(){
        String backMidColor = cube3X3.getBackColor(2,1,1);
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String rightMidColor = cube3X3.getRightColor(1,1,2);
        boolean isValid=false;
        
        String []colors = cube3X3.getColors(2,0,2);
        
        for(int index = 0; index < 3; index++){
            if(colors[index].equals(backMidColor) || colors[index].equals(rightMidColor) || colors[index].equals(upMidColor)){
                isValid=true;
            }
            else{
                isValid=false;
                break;
            }
        }
        return isValid;   
    }
    

    //returns true if front right corner piece is valid
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
    

    //returns true if all the corner pieces are valid
    private boolean isAllCornerAligned(){
        if(isFrontLeftCornerValid() && isBackLeftCornerValid() && isBackRightCornerValid() && isFrontRightCornerValid() )return true;
        else return false;
    }
    
    
    //returns true if any one of the corner piece is valid
    private boolean isAnyCornerAligned(){
        if(isFrontLeftCornerValid() || isBackLeftCornerValid() || isBackRightCornerValid() || isFrontRightCornerValid() )return true;
        else return false;
    }

}
