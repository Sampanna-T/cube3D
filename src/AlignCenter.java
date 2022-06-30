/**
 * @file AlignCenter.java
 * @author Sampanna T (kashisadan16@gmail.com)
 * @brief 
 * Solves AlignCenter of RubiksCube & returns the solution pair list
 * 
 * @date 30th June 2022
 */
 import javafx.util.Pair;
 import java.util.List;
public class AlignCenter {
    
    private RubiksCube3X3 cube3X3;
    private List <Pair<String,String>>solution;
    
    /**
	* @brief 
	* initializes AlignCenter object with cube object & solution list 
	* @param cube3X3
    * @param solution
    * cube3X3 holds RubiksCube3X3 object
    * solution holds [step to be performed, RubiksCube state] pair
	*/
    public AlignCenter(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }


    /**
	* @brief
	* solves the AlignCenter
	* @return void 
	*/
    public void solveAll()throws Exception{
        align();
    }


    /**
	* @brief
	* aligns all the up layer edge pieces at the right position
	* @return void 
	*/
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


    /**
	* @brief
	* checks if the center is aligned or not
	* @return boolean 
	*/
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
    

    /**
	* @brief
	* checks if adjacent colors are aligned or not
	* @return boolean 
	*/
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
    

    /**
	* @brief
	* checks if opposite colors are aligned or not
	* @return boolean 
	*/
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
