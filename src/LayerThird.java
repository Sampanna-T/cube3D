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

public class LayerThird {

    private RubiksCube3X3 cube3X3;
    private List <Pair<String,String>>solution;
    
    public LayerThird(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }


    /**
	* @brief
	* solves the 3X3 rubiksCube  
	* @return void 
	*/
    public void solveAll(){
        setCorner();
    }


    /**
	* @brief
	* solves the corner that is partially solved  
	* @return boolean 
	*/
    private void setCorner(){
        String upMidColor=cube3X3.getUpColor(1,0,1);
        byte loopCheck = 0;
        
        for(int value=1;value<=4;value++){
            while(!cube3X3.getUpColor(0,0,2).equals(upMidColor)){
                Algorithm3X3.L(cube3X3,solution);
            }
            Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
        }
    }   

}

