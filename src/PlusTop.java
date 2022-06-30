/**
 * @file PlusTop.java
 * @author Sampanna T (kashisadan16@gmail.com)
 * @brief 
 * Solves PlusTop of RubiksCube & returns the solution pair list
 * 
 * @date 30th June 2022
 */

import javafx.util.Pair;
import java.util.List;

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

    /**
	* @brief
	* checks if Top is plus or not
	* @return boolean 
	*/
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
    

    /**
	* @brief
	* checks if top is dot or not
	* @return boolean 
	*/
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
    

    /**
	* @brief
	* checks if top is L or Minus
	* @return void 
	*/
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

