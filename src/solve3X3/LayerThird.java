package src.solve3X3;

import src.cube3X3.RubiksCube3X3;
import src.cube.RubiksCubeException;
import javafx.util.Pair;
import java.util.List;

/**
 * Solves Corner of RubiksCube and returns the solution pair list
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 30th June 2022
 * @since JDK 10.0.2
 */
class LayerThird {

    private RubiksCube3X3 cube3X3;
    private List <Pair<String,String>>solution;
    

    /**
	* initializes LayerThird object with cube object and solution list 
    * @param cube3X3
    * Reference to RubiksCube3X3 object
    * @param solution
    * Reference to a list containing [move to be performed, Rubikscube state] pair
	*/
    LayerThird(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }


    /**
	* solves the RubiksCube3X3
    * @throws Exception
    * if LayerThird couldn't be solved  
	*/
    void solveAll()throws Exception{
        if(!Optimizer.isCornerSolved(cube3X3))throw new Exception("SOLVE CORNER");
        setCorner();
    }


    //solves the corner which was partially solved
    private void setCorner()throws Exception{
        String upMidColor=cube3X3.getUpColor(1,0,1);
        byte loopCheck = 0;
        
        for(int value=1;value<=4;value++){
            while(!cube3X3.getUpColor(0,0,2).equals(upMidColor)){
                if(++loopCheck>100)throw RubiksCubeException.invalidCube();
                Algorithm3X3.L(cube3X3,solution);
            }
            Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
        }
    }   

}

