import javafx.util.Pair;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Solves PlusBottom of RubiksCube and returns the solution list
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 29th June 2022
 * @since JDK 10.0.2
 */
class PlusBottom{

    private RubiksCube3X3 cube3X3; 
    private List <Pair<String,String>>solution;
    
   	/** 
	* initializes PlusBottom object with cube object and solution list 
    * @param cube3X3
    * Reference to RubiksCube3X3 object
    * @param solution
    * Reference to a list containing [move to be performed, Rubikscube state] pair
	*/
    PlusBottom(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }


	/**
	* Solves PlusBottom and Adds [move to be performed,RubiksCube state] pair into the solution list
    * @throws Exception
    * If PlusBottom couldn't be solved   
	*/
    void solveAll()throws Exception{

            String downMidColor = cube3X3.getDownColor(1,2,1);

            solveColorUp(downMidColor);
            solveColorMiddle(downMidColor);
            solveColorDown(downMidColor);

            solvePlusBottom(downMidColor);
    }


    /** 
	* puts all the up layer edge pieces having downColor onto the up layer 
	* @param downMidColor
    * represents middle color at down layer 
    * @throws Exception
    * if solveCoulor up Couldn't be performed due to infinite loop
	*/
    private void solveColorUp(String downMidColor) throws Exception{
        byte loopCheck = 0;

        while(isColorUp()){
            
            if(++loopCheck>100)throw new Exception("INFINTE LOOP");

            if(cube3X3.getFrontColor(0,0,1).equals(downMidColor)){
                Solve3X3.add(cube3X3.rotateFront(RubiksCube3X3.R_CLK),cube3X3,solution);

                while(cube3X3.getUpColor(1,0,2).equals(downMidColor)){
                    Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
                    
                    if(++loopCheck>100)throw new Exception("INFINTE LOOP");
                }
                Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_UP),cube3X3,solution);
            }
            else{
                Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution); 
            }
        }
    }

    
    /** 
	* puts all the middle layer edge pieces having downColor on the up layer
	* @param downMidColor
    * downMidColor basically represents middle color at down layer 
    * @throws Exception
    * if solveColorMiddle couldn't be performed due to infinte loop
	*/
    private void solveColorMiddle(String downMidColor)throws Exception{
        byte loopCheck = 0;

        while(isColorMiddle()){
            
            if(++loopCheck>100)throw new Exception("INFINTE LOOP");

            if(cube3X3.getFrontColor(0,1,2).equals(downMidColor)){
                
                while(cube3X3.getUpColor(1,0,2).equals(downMidColor)){
                    Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
                    
                    if(++loopCheck>100)throw new Exception("INFINTE LOOP");
                }
                Solve3X3.add(cube3X3.rightVertical(RubiksCube3X3.V_UP),cube3X3,solution);
                
            }
            else if(cube3X3.getRightColor(0,1,2).equals(downMidColor)){
                
                while(cube3X3.getUpColor(0,0,1).equals(downMidColor)){
                    Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);   
                    
                    if(++loopCheck>100)throw new Exception("INFINTE LOOP");
                }
                Solve3X3.add(cube3X3.rotateFront(RubiksCube3X3.R_ANTICLK),cube3X3,solution);
                
            }
            else{
                Solve3X3.add(cube3X3.middleHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
            }
        }
    }


   /** 
	* puts all the down layer edge pieces having downColor on the up layer
	* @param downMidColor
    * downMidColor basically represents middle color at down layer 
    * @throws Exception
    * if solveColorDown couldn't be performed due to infinte loop
	*/
    private void solveColorDown(String downMidColor)throws Exception{
        byte loopCheck = 0;

        while(isColorDown()){
            
            if(++loopCheck>100)throw new Exception("INFINTE LOOP");

            if(cube3X3.getFrontColor(0,2,1).equals(downMidColor)){
                while(cube3X3.getUpColor(0,0,1).equals(downMidColor)){
                    Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);  
                    
                    if(++loopCheck>100)throw new Exception("INFINTE LOOP");
                }
                Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
                Solve3X3.add(cube3X3.middleVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
                Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
                Solve3X3.add(cube3X3.middleVertical(RubiksCube3X3.V_UP),cube3X3,solution);
                
            }
            else if(cube3X3.getDownColor(0,2,1).equals(downMidColor)){
                while(cube3X3.getUpColor(0,0,1).equals(downMidColor)){
                    Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
                    
                    if(++loopCheck>100)throw new Exception("INFINTE LOOP");
                }  
                Solve3X3.add(cube3X3.rotateFront(RubiksCube3X3.R_CLK),cube3X3,solution);
                Solve3X3.add(cube3X3.rotateFront(RubiksCube3X3.R_CLK),cube3X3,solution);
                
            }
            else{
                Solve3X3.add(cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);    
            }
        }
    
    }


   /** 
	* Performs rotation to shift edge pieces of up layer to the down layer
	* @param downMidColor
    * downMidColor basically represents middle color at down layer 
    * @throws Exception
    * if solvePlusBottom couldn't be performed due to infinte loop
	*/
    private void solvePlusBottom(String downMidColor)throws Exception{
        byte loopCheck = 0;
        
        for(int pos=0;pos<4;pos++){
            while(!(cube3X3.getFrontColor(0,0,1).equals(cube3X3.getFrontColor(0,1,1)) && cube3X3.getUpColor(0,0,1).equals(downMidColor))){
                Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
                
                if(++loopCheck>100)throw new Exception("INFINTE LOOP");
            }
            Solve3X3.add(cube3X3.rotateFront(RubiksCube3X3.R_CLK),cube3X3,solution);
            Solve3X3.add(cube3X3.rotateFront(RubiksCube3X3.R_CLK),cube3X3,solution);
            
            if(pos!=3)Solve3X3.add(cube3X3.circleHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution); 
            
        }
    }


	//returns true if edge pieces in the UP layer contains color equal to downMidColor
    private boolean isColorUp(){
        int j=0;
        
        String downMidColor = cube3X3.getDownColor(1,2,1);
        boolean isFrontEqualsDown = cube3X3.getFrontColor(0,j,1).equals(downMidColor);    
        boolean isBackEqualsDown = cube3X3.getBackColor(2,j,1).equals(downMidColor);    
        boolean isLeftEqualsDown = cube3X3.getLeftColor(1,j,0).equals(downMidColor);    
        boolean isRightEqualsDown = cube3X3.getRightColor(1,j,2).equals(downMidColor); 

        if(isFrontEqualsDown || isBackEqualsDown || isLeftEqualsDown || isRightEqualsDown)
            return true;
        else
            return false;
    }


    
	//returns true if edge pieces in the MIDDLE layer contains color equal to downMidColor
    private boolean isColorMiddle(){
        int j=1;
        String downMidColor = cube3X3.getDownColor(1,2,1);

        boolean isFrontLeftEqualsDown = cube3X3.getFrontColor(0,j,0).equals(downMidColor) || cube3X3.getLeftColor(0,j,0).equals(downMidColor);
        boolean isBackLeftEqualsDown = cube3X3.getLeftColor(2,j,0).equals(downMidColor)|| cube3X3.getBackColor(2,j,0).equals(downMidColor);
        boolean isBackRightEqualsDown = cube3X3.getBackColor(2,j,2).equals(downMidColor)|| cube3X3.getRightColor(2,j,2).equals(downMidColor);
        boolean isFrontRightEqualsDown = cube3X3.getRightColor(0,j,2).equals(downMidColor)|| cube3X3.getFrontColor(0,j,2).equals(downMidColor);

        if(isFrontLeftEqualsDown || isBackLeftEqualsDown || isBackRightEqualsDown || isFrontRightEqualsDown)
            return true;
        else
            return false;
    }

 
	//returns true if edge pieces in the DOWN layer contain color equal to downMidColor
    private boolean isColorDown(){
        int j=2;
        String downMidColor = cube3X3.getDownColor(1,2,1);

        boolean isFrontEqualsDown = cube3X3.getFrontColor(0,j,1).equals(downMidColor)|| cube3X3.getDownColor(0,j,1).equals(downMidColor);
        boolean isLeftEqualsDown = cube3X3.getLeftColor(1,j,0).equals(downMidColor)|| cube3X3.getDownColor(1,j,0).equals(downMidColor);
        boolean isBackEqualsDown = cube3X3.getBackColor(2,j,1).equals(downMidColor)|| cube3X3.getDownColor(2,j,1).equals(downMidColor);
        boolean isRightEqualsDown = cube3X3.getRightColor(1,j,2).equals(downMidColor)|| cube3X3.getDownColor(1,j,2).equals(downMidColor);

        if(isFrontEqualsDown || isLeftEqualsDown || isBackEqualsDown || isRightEqualsDown)
            return true;
        else
            return false;
    }


}
