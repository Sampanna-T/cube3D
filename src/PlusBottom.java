import javafx.util.Pair;
import java.util.List;

public class PlusBottom{

    private RubiksCube3X3 cube3X3;
    private String move;
    private List <Pair<String,String>>solution;
    
   	/**
	* @brief 
	* creates all the nodes of the RubiksCube & sets the colors based on user input 
	* @return void
	*/
    public PlusBottom(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
        this.move = "";
    }


	/**
	* @brief
	* Adds [move,RubiksCube3X3] state pair into the solution list  
	* @return void 
	*/
    public void solveAll()throws Exception{

            String downMidColor = cube3X3.getDownColor(1,2,1);

            solveColorUp(downMidColor);
            solveColorMiddle(downMidColor);
            solveColorDown(downMidColor);

            solvePlusBottom(downMidColor);

    }

     
    /**
	* @brief 
	* puts all the up layer edge pieces having downColor onto the up layer 
	* @param downMidColor
    * downMidColor basically represents middle color @ down layer
	* @return void 
	*/
    public void solveColorUp(String downMidColor) throws Exception{
        byte loopCheck = 0;

        while(isColorUp()){
            loopCheck++;
            if(loopCheck>100)throw new Exception("INFINTE LOOP");

            if(cube3X3.getFrontColor(0,0,1).equals(downMidColor)){
                move = cube3X3.rotateFront(RubiksCube3X3.R_CLK);
                solution.add(new Pair(move,cube3X3.toString()));

                while(cube3X3.getUpColor(1,0,2).equals(downMidColor)){
                    move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
                    solution.add(new Pair(move,cube3X3.toString()));
                    loopCheck++;
                    if(loopCheck>100)throw new Exception("INFINTE LOOP");
                }
                move = cube3X3.rightVertical(RubiksCube3X3.V_UP);
                solution.add(new Pair(move,cube3X3.toString()));
            }
            else{
                move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
                solution.add(new Pair(move,cube3X3.toString()));
            }
        }
    }

    
    /**
	* @brief 
	* puts all the middle layer edge pieces having downColor on the up layer
	* @param downMidColor
    * downMidColor basically represents middle color @ down layer
	* @return void 
	*/
    public void solveColorMiddle(String downMidColor)throws Exception{
        byte loopCheck = 0;

        while(isColorMiddle()){
            loopCheck++;
            if(loopCheck>100)throw new Exception("INFINTE LOOP");

            if(cube3X3.getFrontColor(0,1,2).equals(downMidColor)){
                
                while(cube3X3.getUpColor(1,0,2).equals(downMidColor)){
                    move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
                    solution.add(new Pair(move,cube3X3.toString()));
                    loopCheck++;
                    if(loopCheck>100)throw new Exception("INFINTE LOOP");
                }
                move = cube3X3.rightVertical(RubiksCube3X3.V_UP);
                solution.add(new Pair(move,cube3X3.toString()));
            }
            else if(cube3X3.getRightColor(0,1,2).equals(downMidColor)){
                
                while(cube3X3.getUpColor(0,0,1).equals(downMidColor)){
                    move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
                    solution.add(new Pair(move,cube3X3.toString()));
                    loopCheck++;
                    if(loopCheck>100)throw new Exception("INFINTE LOOP");
                }
                move = cube3X3.rotateFront(RubiksCube3X3.R_ANTICLK);
                solution.add(new Pair(move,cube3X3.toString()));
            }
            else{
                move = cube3X3.middleHorizontal(RubiksCube3X3.H_RIGHT);
                solution.add(new Pair(move,cube3X3.toString()));
            }
        }
    }


   /**
	* @brief 
	* puts all the down layer edge pieces having downColor on the up layer
	* @param downMidColor
    * downMidColor basically represents middle color @ down layer
	* @return void 
	*/
    public void solveColorDown(String downMidColor)throws Exception{
        byte loopCheck = 0;

        while(isColorDown()){
            loopCheck++;
            if(loopCheck>100)throw new Exception("INFINTE LOOP");

            if(cube3X3.getFrontColor(0,2,1).equals(downMidColor)){
                while(cube3X3.getUpColor(0,0,1).equals(downMidColor)){
                    move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
                    solution.add(new Pair(move,cube3X3.toString()));
                    loopCheck++;
                    if(loopCheck>100)throw new Exception("INFINTE LOOP");
                }
                move = cube3X3.downHorizontal(RubiksCube3X3.H_LEFT);
                solution.add(new Pair(move,cube3X3.toString()));
                move = cube3X3.middleVertical(RubiksCube3X3.V_DOWN);
                solution.add(new Pair(move,cube3X3.toString()));
                move = cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT);
                solution.add(new Pair(move,cube3X3.toString()));
                move = cube3X3.middleVertical(RubiksCube3X3.V_UP);
                solution.add(new Pair(move,cube3X3.toString()));
            }
            else if(cube3X3.getDownColor(0,2,1).equals(downMidColor)){
                while(cube3X3.getUpColor(0,0,1).equals(downMidColor)){
                    move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
                    solution.add(new Pair(move,cube3X3.toString()));
                    loopCheck++;
                    if(loopCheck>100)throw new Exception("INFINTE LOOP");
                }  
                move = cube3X3.rotateFront(RubiksCube3X3.R_CLK);
                solution.add(new Pair(move,cube3X3.toString()));
                move = cube3X3.rotateFront(RubiksCube3X3.R_CLK);
                solution.add(new Pair(move,cube3X3.toString()));
            }
            else{
                move = cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT); 
                solution.add(new Pair(move,cube3X3.toString()));   
            }
        }
    
    }


   /**
	* @brief 
	* Performs rotation to shift edge pieces of up layer to the down layer
	* @param downMidColor
    * downMidColor basically represents middle color @ down layer
	* @return void 
	*/
    public void solvePlusBottom(String downMidColor)throws Exception{
        byte loopCheck = 0;
        
        for(int pos=0;pos<4;pos++){
            while(!(cube3X3.getFrontColor(0,0,1).equals(cube3X3.getFrontColor(0,1,1)) && cube3X3.getUpColor(0,0,1).equals(downMidColor))){
                move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
                solution.add(new Pair(move,cube3X3.toString()));
                loopCheck++;
                if(loopCheck>100)throw new Exception("INFINTE LOOP");
            }
            move = cube3X3.rotateFront(RubiksCube3X3.R_CLK);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rotateFront(RubiksCube3X3.R_CLK);
            solution.add(new Pair(move,cube3X3.toString()));
            if(pos!=3){
                move = cube3X3.circleHorizontal(RubiksCube3X3.H_RIGHT);
                solution.add(new Pair(move,cube3X3.toString()));
            }
        }
    }


    /**
	* @brief 
	* checks whether the edge pieces in the UP layer edge piece contains FRONT,BACK,LEFT,RIGHT color
	* which is equal to downMidColor
    * @return boolean 
	*/
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


    /**
	* @brief 
	* checks whether the edge pieces in the MIDDLE layer contain color equal to downMidColor
    * @return boolean 
	*/
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

 
    /**
	* @brief 
	* checks whether the edge pieces in the DOWN layer contain color equal to downMidColor
    * @return boolean 
	*/
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
