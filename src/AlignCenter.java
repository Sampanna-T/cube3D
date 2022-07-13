import javafx.util.Pair;
import java.util.List;

/**
 * Solves AlignCenter of RubiksCube and returns the solution pair list
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 30th June 2022
 * @since JDK 10.0.2
 */
class AlignCenter {
    
    private RubiksCube3X3 cube3X3;
    private List <Pair<String,String>>solution;
    
    /**
	* initializes AlignCenter object with cube object and solution list 
    * @param cube3X3
    * Reference to RubiksCube3X3 object
    * @param solution
    * Reference to a list containing [move to be performed, Rubikscube state] pair
	*/
    AlignCenter(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }


    /**
	* solves the AlignCenter
    * @throws Exception
    * If AlignCenter couldn't be solved
	*/
    void solveAll()throws Exception{
        align();
    }


	//aligns all the up layer edge pieces at the right position
    private void align()throws Exception{
        byte loopCheck = 0;

        //loops until UP layer center gets aligned
        while(!isCenterAligned()){//whileMain
            
            if(++loopCheck>100)throw new Exception("INFINTE LOOP");
            if(isOppositeColorsAligned()){
                while(!cube3X3.getFrontColor(0,0,1).equals(cube3X3.getFrontColor(0,1,1))){   
                    if(++loopCheck>100)throw new Exception("INFINTE LOOP");
                    Solve3X3.add(cube3X3.circleHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
                }
                Algorithm3X3.upTwist(cube3X3,solution);
            }
            else if(isAdjacentColorsAligned()){
                
                while(!(cube3X3.getBackColor(2,0,1).equals(cube3X3.getBackColor(2,1,1)) && cube3X3.getRightColor(1,0,2).equals(cube3X3.getRightColor(1,1,2)))){
                    if(++loopCheck>100)throw new Exception("INFINTE LOOP");
                    Solve3X3.add(cube3X3.circleHorizontal(RubiksCube3X3.H_RIGHT),cube3X3,solution);
                }
                Algorithm3X3.upTwist(cube3X3,solution);  
            }
            else if(!isCenterAligned()){//else if(!isCenterAligned)
                int value=0;
                while(!(isAdjacentColorsAligned() || isOppositeColorsAligned())){//while
                    if(++loopCheck>100)throw new Exception("INFINTE LOOP");
                    if(value==4)
                        Algorithm3X3.upTwist(cube3X3,solution);
                    
                    Solve3X3.add(cube3X3.upHorizontal(RubiksCube3X3.H_LEFT),cube3X3,solution);
                    
                    value++;
                }//while
            }//else if(!isCenterAligned())
        }//whileMain
    }


    //returns true if the center is aligned
    private boolean isCenterAligned(){
        String frontMidColor = cube3X3.getFrontColor(0,1,1);
        String leftMidColor = cube3X3.getLeftColor(1,1,0);
        String backMidColor = cube3X3.getBackColor(2,1,1);
        String rightMidColor = cube3X3.getRightColor(1,1,2);
        
        String frontFUMColor = cube3X3.getFrontColor(0,0,1);
        String leftMULColor = cube3X3.getLeftColor(1,0,0);
        String backBUMColor = cube3X3.getBackColor(2,0,1);
        String rightMURColor = cube3X3.getRightColor(1,0,2);
        
        if(frontFUMColor.equals(frontMidColor) && leftMULColor.equals(leftMidColor) && backBUMColor.equals(backMidColor) && rightMURColor.equals(rightMidColor))
            return true;
        else
            return false;  
    }
    

    //returns true if adjacent colors are aligned
    private boolean isAdjacentColorsAligned(){
        String frontMidColor=cube3X3.getFrontColor(0,1,1);
        String leftMidColor=cube3X3.getLeftColor(1,1,0);
        String backMidColor=cube3X3.getBackColor(2,1,1);
        String rightMidColor=cube3X3.getRightColor(1,1,2);
        
        String frontFUMColor=cube3X3.getFrontColor(0,0,1);
        String leftMULColor=cube3X3.getLeftColor(1,0,0);
        String backBUMColor=cube3X3.getBackColor(2,0,1);
        String rightMURColor=cube3X3.getRightColor(1,0,2);
        
        boolean adj1 = frontFUMColor.equals(frontMidColor) && leftMULColor.equals(leftMidColor);
        boolean adj2 = leftMULColor.equals(leftMidColor) && backBUMColor.equals(backMidColor);
        boolean adj3 = backBUMColor.equals(backMidColor) && rightMURColor.equals(rightMidColor);
        boolean adj4 = rightMURColor.equals(rightMidColor) && frontFUMColor.equals(frontMidColor);
        
        if(adj1 || adj2 || adj3 || adj4)return true;
        else return false;
    }
    

	//returns true if opposite colors are aligned 
    private boolean isOppositeColorsAligned(){
        String frontMidColor = cube3X3.getFrontColor(0,1,1);
        String leftMidColor = cube3X3.getLeftColor(1,1,0);
        String backMidColor = cube3X3.getBackColor(2,1,1);
        String rightMidColor = cube3X3.getRightColor(1,1,2);
        
        String frontFUMColor=cube3X3.getFrontColor(0,0,1);
        String leftMULColor=cube3X3.getLeftColor(1,0,0);
        String backBUMColor=cube3X3.getBackColor(2,0,1);
        String rightMURColor=cube3X3.getRightColor(1,0,2);
        
        boolean opp1 = frontFUMColor.equals(frontMidColor) && backBUMColor.equals(backMidColor);
        boolean opp2 = leftMULColor.equals(leftMidColor) && rightMURColor.equals(rightMidColor);
        
        if(opp1 || opp2)return true;
        else return false;
    }
    
}
