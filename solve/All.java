public class LayerFirst {
    
    private RubiksCube3X3 cube3X3;
    private String move;
    private List <Pair<String,String>>solution;
    

    /**
	* @brief 
	* initializes LayerFirst object with cube objectect & solution list 
	* @return void
	*/
    public LayerFirst(RubiksCube3X3 cube3X3, List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }

        
	/**
	* @brief
	* Adds [move,RubiksCube state] pair into the solution list  
	* @return void 
	*/
    public void solveAll()throws Exception{
        solveLayerFirst();
    }
    
    
    /**
	* @brief 
	* Checks if corner piece is valid or not
	* @param i,j,k
	* i,j,k represents the index of the 3X3 cube whole corner needs to be checked
    * @return boolean 
	*/
    private boolean isCornerValid(int i, int j, int k){
        String frontMidColor = cube3X3.getFrontColor(0,1,1);
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String rightMidColor = cube3X3.getRightColor(1,1,2);
        
        boolean isValid=false;
        
        String []colors = cube3X3.getColors(i, j, k);
        
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
	* checks whether the corner piece is in the right position or not
	* @return boolean 
	*/
    private boolean isCornerSet(){
        String frontMidColor = cube3X3.getFrontColor(0,1,1);
        String upMidColor = cube3X3.getUpColor(1,0,1);
        String rightMidColor = cube3X3.getRightColor(1,1,2);

        
        if(cube3X3.getFrontColor(0,0,2).equals(frontMidColor) && cube3X3.getUpColor(0,0,2).equals(upMidColor) && cube3X3.getRightColor(0,0,2).equals(rightMidColor))
            return true;
        else 
            return false;
    }
    
    
    /**
	* @brief 
	* Places the corner piece at index 0,0,2 or 0,2,2
    * Returns true if corner piece is placed at 0,0,2 else returns false
	* @return boolean 
	*/
    private boolean placeThePiece(){
        if(isCornerValid(0,0,2)||isCornerValid(0,2,2)){  
            if(isCornerValid(0,0,2)) 
                return true;//Only case where piece ends up at position 0,0,2
        }
        else if(isCornerValid(0,0,0)){//else if 
            move = cube3X3.leftVertical(RubiksCube3X3.V_DOWN);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.leftVertical(RubiksCube3X3.V_UP);
            solution.add(new Pair(move,cube3X3.toString()));
        }
        else if(isCornerValid(0,2,0)){//else if
            move = cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));
        }//else if
        else if(isCornerValid(2,0,0)){//else if
            move = cube3X3.rotateBack(RubiksCube3X3.R_ANTICLK);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rotateBack(RubiksCube3X3.R_CLK);
            solution.add(new Pair(move,cube3X3.toString()));
        }//else if
        else if(isCornerValid(2,2,0)){//else if
            move = cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));
        }//else if
        else if(isCornerValid(2,0,2)){//else if
            move = cube3X3.rotateBack(RubiksCube3X3.R_CLK);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.downHorizontal(RubiksCube3X3.H_LEFT);
            solution.add(new Pair(move,cube3X3.toString()));
            move = cube3X3.rotateBack(RubiksCube3X3.R_ANTICLK);
            solution.add(new Pair(move,cube3X3.toString()));
        }//else if
        else if(isCornerValid(2,2,2)){//else if
            move = cube3X3.downHorizontal(RubiksCube3X3.H_LEFT);
            solution.add(new Pair(move,cube3X3.toString()));
        }//else if
        return false;
    }

        
    /**
	* @brief 
	* to set the corner piece At the proper location
    * Returns true if corner piece is placed at 0,0,2 else returns false
	* @return boolean 
	*/
    private void solveLayerFirst(){
        String upMidColor = cube3X3.getUpColor(1,0,1);
        boolean topFlag = false; //Tells if the required piece is present at 0,0,2

        for(int itr = 0; itr < 4; itr++){
            topFlag = placeThePiece();
            if(!isCornerSet()){
                if(topFlag){
                    if(cube3X3.getFrontColor(0,0,2).equals(upMidColor)){
                        move = cube3X3.rotateFront(RubiksCube3X3.R_CLK);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.rotateFront(RubiksCube3X3.R_ANTICLK);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.downHorizontal(RubiksCube3X3.H_LEFT);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.downHorizontal(RubiksCube3X3.H_LEFT);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.rightVertical(RubiksCube3X3.V_UP);
                        solution.add(new Pair(move,cube3X3.toString()));
                    }
                    else if(cube3X3.getRightColor(0,0,2).equals(upMidColor)){
                        (new Algorithm(cube3X3)).L(true,true,(++count).toString());//firstuse
                        move = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.downHorizontal(RubiksCube3X3.H_LEFT);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.rightVertical(RubiksCube3X3.V_UP);
                        solution.add(new Pair(move,cube3X3.toString()));
                    }
                }
                else{
                    if(cube3X3.getFrontColor(0,2,2).equals(upMidColor)){
                        move = cube3X3.downHorizontal(RubiksCube3X3.H_LEFT);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.rightVertical(RubiksCube3X3.V_UP);
                        solution.add(new Pair(move,cube3X3.toString()));
                    }
                    else if(cube3X3.getRightColor(0,2,2).equals(upMidColor)){
                        move = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.downHorizontal(RubiksCube3X3.H_LEFT);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.rightVertical(RubiksCube3X3.V_UP);
                        solution.add(new Pair(move,cube3X3.toString()));
                    }
                    else if(cube3X3.getDownColor(0,2,2).equals(upMidColor)){
                        move = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.downHorizontal(RubiksCube3X3.H_LEFT);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.downHorizontal(RubiksCube3X3.H_LEFT);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.rightVertical(RubiksCube3X3.V_UP);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.downHorizontal(RubiksCube3X3.H_RIGHT);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.rightVertical(RubiksCube3X3.V_DOWN);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.downHorizontal(RubiksCube3X3.H_LEFT);
                        solution.add(new Pair(move,cube3X3.toString()));
                        move = cube3X3.rightVertical(RubiksCube3X3.V_UP);
                        solution.add(new Pair(move,cube3X3.toString()));
                    }
                }
            }
        topFlag = false;

        if(itr != 3)
            move = cube3X3.circleHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));
        } 
    }

    
}

//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS



public class LayerSecond {

    private RubiksCube3X3 cube3X3;
    private String move;
    private List <Pair<String,String>>solution;
    
    public LayerSecond(RubiksCube3X3 cube3X3, Integer count){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }
    
    //checks if there is any valid edge piece at the Up layer
    public boolean isAnyUpEdgePieceValid(){
        int j=0;
        
        String upColor = cube3X3.getUpColor(1,0,1);
        String frontColor = cube3X3.getFrontColor(0,j,1);
        String frontUpColor = cube3X3.getUpColor(0,j,1);
        String leftColor = cube3X3.getLeftColor(1,j,0);
        String leftUpColor = cube3X3.getUpColor(1,j,0);
        String backColor = cube3X3.getBackColor(2,j,1);
        String backUpColor = cube3X3.getUpColor(2,j,1);
        String rightColor = cube3X3.getRightColor(1,j,2);
        String rightUpColor = cube3X3.getUpColor(1,j,2);
       
        boolean isFrontPieceValid = !frontColor.equals(upColor) && !frontUpColor.equals(upColor);
        boolean isLeftPieceValid = !leftColor.equals(upColor) && !leftUpColor.equals(upColor);
        boolean isBackPieceValid = !backColor.equals(upColor) && !backUpColor.equals(upColor);
        boolean isRightPieceValid = !rightColor.equals(upColor) && !rightUpColor.equals(upColor);
        
        if(isFrontPieceValid || isLeftPieceValid || isBackPieceValid || isRightPieceValid)
            return true;
        else 
            return false;
    }
    
    //checks if the edge piece at 001 is valid 
    public boolean isUpEdgePieceValid(){
        String upColor = cube3X3.getUpColor(1,0,1);
        String frontColor = cube3X3.getFrontColor(0,0,1);
        String frontUpColor = cube3X3.getUpColor(0,0,1);
        
        boolean isFrontPieceValid = !frontColor.equals(upColor) && !frontUpColor.equals(upColor);
        
        if(isFrontPieceValid)
            return true;
        else 
            return false;
    }
    
    //checks if any edge piece in the middle layer is invalid/(incorrectly placed)
    public boolean isAnyMiddleEdgePieceInvalid(){
        int j=1;
        
        String frontColor = cube3X3.getFrontColor(0,1,1);
        String leftColor = cube3X3.getLeftColor(1,1,0);
        String backColor = cube3X3.getBackColor(2,1,1);
        String rightColor = cube3X3.getRightColor(1,1,2);
        
        String frontFMLColor = cube3X3.getFrontColor(0, j, 0);
        String leftFMLColor = cube3X3.getLeftColor(0, j, 0);
        String leftBMLColor = cube3X3.getLeftColor(2, j, 0);
        String backBMLColor = cube3X3.getBackColor(2, j, 0);
        String backBMRColor = cube3X3.getBackColor(2, j, 2);
        String rightBMRColor = cube3X3.getRightColor(2, j, 2);
        String rightFMRColor = cube3X3.getRightColor(0, j, 2);
        String frontFMRColor = cube3X3.getFrontColor(0, j, 2);
        
        boolean isFMLValid = (frontFMLColor.equals(frontColor) && leftFMLColor.equals(leftColor));
        boolean isBMLValid = (leftBMLColor.equals(leftColor) && backBMLColor.equals(backColor));
        boolean isBMRValid = (backBMRColor.equals(backColor) && rightBMRColor.equals(rightColor));
        boolean isFMRValid = (rightFMRColor.equals(rightColor) && frontFMRColor.equals(frontColor));
        
        if(!isFMLValid || !isBMLValid || !isBMRValid || !isFMRValid){
            return true;
        }
        else{
            return false;
        }
    }
    
    //solves the second layer using all the edge pieces on the UP layer
    public void solveUpEdgePieces(){
        while(isAnyUpEdgePieceValid()){//while
            
           if(isUpEdgePieceValid()){
               
                while(!cube3X3.getFrontColor(0,0,1).equals(cube3X3.getFrontColor(0,1,1))){
                    move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
                    solution.add(new Pair(move,cube3X3.toString()));
                    move = cube3X3.circleHorizontal(RubiksCube3X3.H_RIGHT);
                    solution.add(new Pair(move,cube3X3.toString()));
                }
                if(cube3X3.getUpColor(0,0,1).equals(cube3X3.getLeftColor(1,1,0)))
                    (new Algorithm(cube3X3)).layer2(false,true,(++count).toString());
                
                else 
                    (new Algorithm(cube3X3)).layer2(true,true,(++count).toString());
                
            }//if color up is valid
            else{
                move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
                solution.add(new Pair(move,cube3X3.toString()));
            }//if color up is not valid

        }//while

    }
    
    //solves the unsolved pieces in the middle layer 
    public void solveMiddleEdgePieces(){
    
        Algorithm algorithm = new Algorithm(cube3X3);
        while(isAnyMiddleEdgePieceInvalid()){//whileMain

            String frontColor=cube3X3.getFrontColor(0,1,1);
            String rightColor=cube3X3.getRightColor(1,1,2);
            String frontFMRColor=cube3X3.getFrontColor(0,1,2);
            String rightFMRColor=cube3X3.getRightColor(0,1,2);
            
            //checks if FMR piece is at the right position or not
            if( !(frontFMRColor.equals(frontColor) && rightFMRColor.equals(rightColor)) ){
                algorithm.layer2(true,true,(++count).toString());
                solveUpEdgePieces();
            }//if
            else if(isAnyMiddleEdgePieceInvalid()){
                move = cube3X3.circleHorizontal(RubiksCube3X3.H_RIGHT);
                solution.add(new Pair(move,cube3X3.toString()));
            }//else if
        }//whileMain
    }
    
    public void solveAll(){
        solveUpEdgePieces();
        System.out.println("------------------------------------------------");
        solveMiddleEdgePieces();
        System.out.println("________________________________________________");
    }
    
    public Integer getCount(){
        return count;
    }
    
}


//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS




public class PlusTop {

    private RubiksCube3X3 cube3X3;
    private String move;
    private List <Pair<String,String>>solution;
    
    public PlusTop(RubiksCube3X3 cube3X3, Integer count){
        this.cube3X3 = cube3X3;
        this.count = count;
    }
    
    private boolean isTopPlus(){
        int j=0;
        String upColor = cube3X3.getUpColor(1,0,1);
        String frontUpColor = cube3X3.getUpColor(0, j, 1);
        String leftUpColor = cube3X3.getUpColor(1, j, 0);
        String backUpColor = cube3X3.getUpColor(2, j, 1);
        String rightUpColor = cube3X3.getUpColor(1, j, 2);
        
        if(frontUpColor.equals(upColor) && leftUpColor.equals(upColor) && backUpColor.equals(upColor) && rightUpColor.equals(upColor))
            return true;
        else
            return false;
    }
    
    private boolean isTopDot(){
        int j=0;
        String upColor = cube3X3.getUpColor(1,0,1);
        String frontUpColor = cube3X3.getUpColor(0, j, 1);
        String leftUpColor = cube3X3.getUpColor(1, j, 0);
        String backUpColor = cube3X3.getUpColor(2, j, 1);
        String rightUpColor = cube3X3.getUpColor(1, j, 2);
        
        if(!frontUpColor.equals(upColor) && !leftUpColor.equals(upColor) && !backUpColor.equals(upColor) && !rightUpColor.equals(upColor))
            return true;
        else
            return false;
    }
    
    private boolean isTopLORMinus(){
        int j=0;
        String upColor = cube3X3.getUpColor(1,0,1);
        String frontUpColor = cube3X3.getUpColor(0, j, 1);
        String leftUpColor = cube3X3.getUpColor(1, j, 0);
        String backUpColor = cube3X3.getUpColor(2, j, 1);
        String rightUpColor = cube3X3.getUpColor(1, j, 2);
        
        if( (leftUpColor.equals(upColor) && rightUpColor.equals(upColor)) || (leftUpColor.equals(upColor) && backUpColor.equals(upColor))  )
            return true;
        else 
            return false;
    }
    
    public void solvePlus(){
        Algorithm algorithm = new Algorithm(cube3X3);
        
        while(!isTopPlus()){//while
            if(isTopDot()){
                algorithm.twistFront(true,true,(++count).toString());
            }
            else{
                while(true){
                    if(isTopLORMinus() || isTopDot())
                        break;
                    else
                        move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
                        solution.add(new Pair(move,cube3X3.toString()));
                }
                algorithm.twistFront(true,true,(++count).toString());
            }
        }//while
    }
    
    public void solveAll(){
        solvePlus();
        System.out.println("________________________________________________");
    }
    
    public Integer getCount(){
        return count;
    }
}



//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS




public class AlignCenter {
    
    private RubiksCube3X3 cube3X3;
    private String move;
    private List <Pair<String,String>>solution;
    
    public AlignCenter(RubiksCube3X3 cube3X3, Integer count){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }
    
    private boolean isCenterAligned(){
        int j=0;
        String frontColor=cube3X3.getFrontColor(0,1,1);
        String leftColor=cube3X3.getLeftColor(1,1,0);
        String backColor=cube3X3.getBackColor(2,1,1);
        String rightColor=cube3X3.getRightColor(1,1,2);
        
        String frontFUMColor=cube3X3.getFrontColor(0, j, 1);
        String leftMULColor=cube3X3.getLeftColor(1, j, 0);
        String backBUMColor=cube3X3.getBackColor(2, j, 1);
        String rightMURColor=cube3X3.getRightColor(1, j, 2);
        
        if(frontFUMColor.equals(frontColor) && leftMULColor.equals(leftColor) && backBUMColor.equals(backColor) && rightMURColor.equals(rightColor))
            return true;
        else
            return false;  
    }
    
    private boolean isAdjacentColorsEqual(){
        int j=0;
        String frontColor=cube3X3.getFrontColor(0,1,1);
        String leftColor=cube3X3.getLeftColor(1,1,0);
        String backColor=cube3X3.getBackColor(2,1,1);
        String rightColor=cube3X3.getRightColor(1,1,2);
        
        String frontFUMColor=cube3X3.getFrontColor(0, j, 1);
        String leftMULColor=cube3X3.getLeftColor(1, j, 0);
        String backBUMColor=cube3X3.getBackColor(2, j, 1);
        String rightMURColor=cube3X3.getRightColor(1, j, 2);
        
        boolean adj1 = frontFUMColor.equals(frontColor) && leftMULColor.equals(leftColor);
        boolean adj2 = leftMULColor.equals(leftColor) && backBUMColor.equals(backColor);
        boolean adj3 = backBUMColor.equals(backColor) && rightMURColor.equals(rightColor);
        boolean adj4 = rightMURColor.equals(rightColor) && frontFUMColor.equals(frontColor);
        
        if(adj1 || adj2 || adj3 || adj4)
            return true;
        else
            return false;
    }
    
    private boolean isOppositeColorsEqual(){
        int j=0;
        String frontColor=cube3X3.getFrontColor(0,1,1);
        String leftColor=cube3X3.getLeftColor(1,1,0);
        String backColor=cube3X3.getBackColor(2,1,1);
        String rightColor=cube3X3.getRightColor(1,1,2);
        
        String frontFUMColor=cube3X3.getFrontColor(0, j, 1);
        String leftMULColor=cube3X3.getLeftColor(1, j, 0);
        String backBUMColor=cube3X3.getBackColor(2, j, 1);
        String rightMURColor=cube3X3.getRightColor(1, j, 2);
        
        boolean opp1 = frontFUMColor.equals(frontColor) && backBUMColor.equals(backColor);
        boolean opp2 = leftMULColor.equals(leftColor) && rightMURColor.equals(rightColor);
        
        if(opp1 || opp2)
            return true;
        else
            return false;
    }
    
    public void align(){
        String upColor=cube3X3.getUpColor(1,0,1);
        String backColor=cube3X3.getBackColor(2,1,1);
        String rightColor=cube3X3.getRightColor(1,1,2);
        Algorithm algorithm=new Algorithm(cube3X3);
        
        //loops until UP layer center gets aligned
        while(!isCenterAligned()){//whileMain
            if(isOppositeColorsEqual()){
                System.out.println("heerer");
                while(!cube3X3.getUpColor(0,0,1).equals(upColor))
                    move = cube3X3.circleHorizontal(RubiksCube3X3.H_RIGHT);
                    solution.add(new Pair(move,cube3X3.toString()));
                algorithm.upTwist(true,true,(++count).toString());
                move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
                solution.add(new Pair(move,cube3X3.toString()));
                algorithm.upTwist(true,true,(++count).toString());
                while(!isCenterAligned())
                    move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
                    solution.add(new Pair(move,cube3X3.toString()));
            }
            else if(isAdjacentColorsEqual()){
                while(!(cube3X3.getBackColor(2,0,1).equals(backColor) && cube3X3.getRightColor(1,0,2).equals(rightColor)))
                    move = cube3X3.circleHorizontal(RubiksCube3X3.H_RIGHT);
                    solution.add(new Pair(move,cube3X3.toString()));
            
                algorithm.upTwist(true,true,(++count).toString());
                while(!isCenterAligned())
                    move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
                    solution.add(new Pair(move,cube3X3.toString()));
            }
            else if(!isCenterAligned()){//else if(!isCenterAligned)
                int value=0;
                while(!isAdjacentColorsEqual() && !isOppositeColorsEqual()){//while
                    if(value==4)
                        algorithm.upTwist(true,true,(++count).toString());
                    
                    move = cube3X3.upHorizontal(RubiksCube3X3.H_LEFT);
                    solution.add(new Pair(move,cube3X3.toString()));
                    value++;
                }//while
            }//else if(!isCenterAligned())
        }//whileMain
    }
    
    public void solveAll(){
        align();
        System.out.println("________________________________________________");
    }
    
    public Integer getCount(){
        return count;
    }
}



//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS



public class Corner {

    private RubiksCube3X3 cube3X3;
    private String move;
    private List <Pair<String,String>>solution;
    
    public Corner(RubiksCube3X3 cube3X3, Integer count){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }
    
    private boolean isFrontLeftCornerValid(){
        int i=0,j=0,k=0;
        String frontColor = cube3X3.getFrontColor(0,1,1);
        String upColor = cube3X3.getUpColor(1,0,1);
        String leftColor = cube3X3.getLeftColor(1,1,0);
        boolean isValid=false;
        
        String []colors = cube3X3.getColors(i, j, k);
        
        for(int index = 0; index < 3; index++){
            if(colors[index].equals(frontColor) || colors[index].equals(leftColor) || colors[index].equals(upColor)){
                isValid=true;
            }
            else{
                isValid=false;
                break;
            }
        }
        return isValid;   
    }
    
    private boolean isBackLeftCornerValid(){
        int i=2,j=0,k=0;
        String backColor = cube3X3.getBackColor(2,1,1);
        String upColor = cube3X3.getUpColor(1,0,1);
        String leftColor = cube3X3.getLeftColor(1,1,0);
        boolean isValid=false;
        
        String []colors = cube3X3.getColors(i, j, k);
        
        for(int index = 0; index < 3; index++){
            if(colors[index].equals(backColor) || colors[index].equals(leftColor) || colors[index].equals(upColor)){
                isValid=true;
            }
            else{
                isValid=false;
                break;
            }
        }
        return isValid;   
    }
    
    private boolean isBackRightCornerValid(){
        int i=2,j=0,k=2;
        String backColor = cube3X3.getBackColor(2,1,1);
        String upColor = cube3X3.getUpColor(1,0,1);
        String rightColor = cube3X3.getRightColor(1,1,2);
        boolean isValid=false;
        
        String []colors = cube3X3.getColors(i, j, k);
        
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
    
    private boolean isFrontRightCornerValid(){
        int i=0,j=0,k=2;
        String frontColor = cube3X3.getFrontColor(0,1,1);
        String upColor = cube3X3.getUpColor(1,0,1);
        String rightColor = cube3X3.getRightColor(1,1,2);
        boolean isValid=false;
        
        String []colors = cube3X3.getColors(i, j, k);
        
        for(int index = 0; index < 3; index++){
            if(colors[index].equals(frontColor) || colors[index].equals(rightColor) || colors[index].equals(upColor)){
                isValid=true;
            }
            else{
                isValid=false;
                break;
            }
        }
        return isValid;   
    }
    
    private boolean isAllCornerAligned(){
        if(isFrontLeftCornerValid() && isBackLeftCornerValid() && isBackRightCornerValid() && isFrontRightCornerValid() )
            return true;
        else
            return false;
    }
    
    private boolean isAnyCornerAligned(){
      
        if(isFrontLeftCornerValid() || isBackLeftCornerValid() || isBackRightCornerValid() || isFrontRightCornerValid() )
            return true;
        else
            return false;
    }
    
    public void solveCorner(){
        while(!(isAllCornerAligned())){

            if(isAnyCornerAligned()){
               while(!isFrontRightCornerValid()){
                    move = cube3X3.circleHorizontal(RubiksCube3X3.H_RIGHT);
                    solution.add(new Pair(move,cube3X3.toString()));
               }
               while(!(isAllCornerAligned())){
                    (new Algorithm(cube3X3)).corner(true,true,(++count).toString());
               }
            }
            else{
                (new Algorithm(cube3X3)).corner(true,true,(++count).toString());
            }
        }

    }
    
    public void solveAll(){
        solveCorner();
        System.out.println("________________________________________________");
    }
    
    public Integer getCount(){
        return count;
    }
}



//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS



public class LayerThird {

    private RubiksCube3X3 cube3X3;
    private String move;
    private List <Pair<String,String>>solution;
    
    public LayerThird(RubiksCube3X3 cube3X3, Integer count){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }
    
    private void setCorner(){
        String upColor=cube3X3.getUpColor(1,0,1);
        
        for(int value=1;value<=4;value++){
            while(!cube3X3.getUpColor(0,0,2).equals(upColor))
                (new Algorithm(cube3X3)).L(true,true,(++count).toString());
            move = cube3X3.upHorizontal(RubiksCube3X3.H_RIGHT);
            solution.add(new Pair(move,cube3X3.toString()));

        }
    }
    
    public void solveAll(){
        setCorner();
        System.out.println("________________________________________________");
    }
    
    public Integer getCount(){
        return count;
    }
}


