package RubixCubeJava;

public class LayerSecond {

    private RubixCube rubixCube;
    private Integer count;
    
    public LayerSecond(RubixCube rubixCube, Integer count){
        this.rubixCube=rubixCube;
        this.count=count;
    }
    
    //checks if there is any valid edge piece at the Up layer
    public boolean isAnyUpEdgePieceValid(){
        int j=0;
        
        String upColor = rubixCube.getUpMidColor();
        String frontColor = rubixCube.getFrontColor(0,j,1);
        String frontUpColor = rubixCube.getUpColor(0,j,1);
        String leftColor = rubixCube.getLeftColor(1,j,0);
        String leftUpColor = rubixCube.getUpColor(1,j,0);
        String backColor = rubixCube.getBackColor(2,j,1);
        String backUpColor = rubixCube.getUpColor(2,j,1);
        String rightColor = rubixCube.getRightColor(1,j,2);
        String rightUpColor = rubixCube.getUpColor(1,j,2);
       
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
        String upColor = rubixCube.getUpMidColor();
        String frontColor = rubixCube.getFrontColor(0,0,1);
        String frontUpColor = rubixCube.getUpColor(0,0,1);
        
        boolean isFrontPieceValid = !frontColor.equals(upColor) && !frontUpColor.equals(upColor);
        
        if(isFrontPieceValid)
            return true;
        else 
            return false;
    }
    
    //checks if any edge piece in the middle layer is invalid/(incorrectly placed)
    public boolean isAnyMiddleEdgePieceInvalid(){
        int j=1;
        
        String frontColor = rubixCube.getFrontMidColor();
        String leftColor = rubixCube.getLeftMidColor();
        String backColor = rubixCube.getBackMidColor();
        String rightColor = rubixCube.getRightMidColor();
        
        String frontFMLColor = rubixCube.getFrontColor(0, j, 0);
        String leftFMLColor = rubixCube.getLeftColor(0, j, 0);
        String leftBMLColor = rubixCube.getLeftColor(2, j, 0);
        String backBMLColor = rubixCube.getBackColor(2, j, 0);
        String backBMRColor = rubixCube.getBackColor(2, j, 2);
        String rightBMRColor = rubixCube.getRightColor(2, j, 2);
        String rightFMRColor = rubixCube.getRightColor(0, j, 2);
        String frontFMRColor = rubixCube.getFrontColor(0, j, 2);
        
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
               
                while(!rubixCube.getFrontColor(0,0,1).equals(rubixCube.getFrontMidColor())){
                    rubixCube.upHorizontal(false,true,(++count).toString(),"");
                    rubixCube.circleHorizontal(true,true,(++count).toString(),"");
                }
                if(rubixCube.getUpColor(0,0,1).equals(rubixCube.getLeftMidColor()))
                    (new Algorithm(rubixCube)).layer2(false,true,(++count).toString());
                
                else 
                    (new Algorithm(rubixCube)).layer2(true,true,(++count).toString());
                
            }//if color up is valid
            else{
                rubixCube.upHorizontal(false,true,(++count).toString(),"");
            }//if color up is not valid

        }//while

    }
    
    //solves the unsolved pieces in the middle layer 
    public void solveMiddleEdgePieces(){
    
        Algorithm algorithm = new Algorithm(rubixCube);
        while(isAnyMiddleEdgePieceInvalid()){//whileMain

            String frontColor=rubixCube.getFrontMidColor();
            String rightColor=rubixCube.getRightMidColor();
            String frontFMRColor=rubixCube.getFrontColor(0,1,2);
            String rightFMRColor=rubixCube.getRightColor(0,1,2);
            
            //checks if FMR piece is at the right position or not
            if( !(frontFMRColor.equals(frontColor) && rightFMRColor.equals(rightColor)) ){
                algorithm.layer2(true,true,(++count).toString());
                solveUpEdgePieces();
            }//if
            else if(isAnyMiddleEdgePieceInvalid()){
                rubixCube.circleHorizontal(true,true,(++count).toString(),"");
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
