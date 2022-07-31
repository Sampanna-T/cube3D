package com.cube.solve;
 
import javafx.util.Pair;
import java.util.List;
import com.cube.RubiksCube3X3;

/**
 * Solves PlusTop of RubiksCube and returns the solution pair list
 * @author Sampanna T (kashi16sadan@gmail.com) 
 * @version 1.0 30th June 2022
 * @since JDK 10.0.2
 */
class PlusTop {

    private RubiksCube3X3 cube3X3;
    private List <Pair<String,String>>solution;
    
    /**
	* initializes PlusTop object with cube object and solution list 
    * @param cube3X3
    * Reference to RubiksCube3X3 object
    * @param solution
    * Reference to a list containing [move to be performed, Rubikscube state] pair
	*/
    PlusTop(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }
    
    /**
	* solves the PlusTop
    * @throws Exception
    * if PlusTop couldn't be solved
	*/
    void solveAll()throws Exception{
        if(!Optimizer.isLayerSecondSolved(cube3X3))throw new Exception("SOLVE LAYERSECOND");
        solvePlus();
    }

    
    //solves the plus on top
    private void solvePlus()throws Exception{
        byte loopCheck = 0;
        while(!isTopPlus()){//while
            if(isTopDot()){
                Algorithm3X3.twistFront(cube3X3,solution);
            }
            else{
                while(true){
                    
                    if(++loopCheck>100)throw new Exception("INFINTE LOOP");

                    if(isTopLORMinus() || isTopDot())break;
                    else Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);       
                }
                Algorithm3X3.twistFront(cube3X3,solution);
            }
        }//while
    }


    //returns true if Top is plus
    private boolean isTopPlus(){
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String upFUMColor = cube3X3.getUpColor(0,0,1);
        String upMULColor = cube3X3.getUpColor(1,0,0);
        String upBUMColor = cube3X3.getUpColor(2,0,1);
        String upMURColor = cube3X3.getUpColor(1,0,2);
        
        if(upFUMColor.equals(upMidColor) && upMULColor.equals(upMidColor) && upBUMColor.equals(upMidColor) && upMURColor.equals(upMidColor))
            return true;
        else
            return false;
    }
    

    
	//returns true if top is dot 
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
    

    //returns true top is L or Minus
    private boolean isTopLORMinus(){
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String upMULColor = cube3X3.getUpColor(1,0,0);
        String upBUMColor = cube3X3.getUpColor(2,0,1);
        String upMURColor = cube3X3.getUpColor(1,0,2);
        boolean isTopL = upMULColor.equals(upMidColor) && upBUMColor.equals(upMidColor);
        boolean isTopMinus = upMULColor.equals(upMidColor) && upMURColor.equals(upMidColor);

        if(isTopL || isTopMinus)return true;
        else return false;
    }
    
}

