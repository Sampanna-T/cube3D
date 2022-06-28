package RubixCubeJava;

public class AlignCenter {
    
    private RubixCube rubixCube;
    private Integer count;
    
    public AlignCenter(RubixCube rubixCube, Integer count){
        this.rubixCube=rubixCube;
        this.count=count;
    }
    
    private boolean isCenterAligned(){
        int j=0;
        String frontColor=rubixCube.getFrontMidColor();
        String leftColor=rubixCube.getLeftMidColor();
        String backColor=rubixCube.getBackMidColor();
        String rightColor=rubixCube.getRightMidColor();
        
        String frontFUMColor=rubixCube.getFrontColor(0, j, 1);
        String leftMULColor=rubixCube.getLeftColor(1, j, 0);
        String backBUMColor=rubixCube.getBackColor(2, j, 1);
        String rightMURColor=rubixCube.getRightColor(1, j, 2);
        
        if(frontFUMColor.equals(frontColor) && leftMULColor.equals(leftColor) && backBUMColor.equals(backColor) && rightMURColor.equals(rightColor))
            return true;
        else
            return false;  
    }
    
    private boolean isAdjacentColorsEqual(){
        int j=0;
        String frontColor=rubixCube.getFrontMidColor();
        String leftColor=rubixCube.getLeftMidColor();
        String backColor=rubixCube.getBackMidColor();
        String rightColor=rubixCube.getRightMidColor();
        
        String frontFUMColor=rubixCube.getFrontColor(0, j, 1);
        String leftMULColor=rubixCube.getLeftColor(1, j, 0);
        String backBUMColor=rubixCube.getBackColor(2, j, 1);
        String rightMURColor=rubixCube.getRightColor(1, j, 2);
        
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
        String frontColor=rubixCube.getFrontMidColor();
        String leftColor=rubixCube.getLeftMidColor();
        String backColor=rubixCube.getBackMidColor();
        String rightColor=rubixCube.getRightMidColor();
        
        String frontFUMColor=rubixCube.getFrontColor(0, j, 1);
        String leftMULColor=rubixCube.getLeftColor(1, j, 0);
        String backBUMColor=rubixCube.getBackColor(2, j, 1);
        String rightMURColor=rubixCube.getRightColor(1, j, 2);
        
        boolean opp1 = frontFUMColor.equals(frontColor) && backBUMColor.equals(backColor);
        boolean opp2 = leftMULColor.equals(leftColor) && rightMURColor.equals(rightColor);
        
        if(opp1 || opp2)
            return true;
        else
            return false;
    }
    
    public void align(){
        String upColor=rubixCube.getUpMidColor();
        String backColor=rubixCube.getBackMidColor();
        String rightColor=rubixCube.getRightMidColor();
        Algorithm algorithm=new Algorithm(rubixCube);
        
        //loops until UP layer center gets aligned
        while(!isCenterAligned()){//whileMain
            if(isOppositeColorsEqual()){
                System.out.println("heerer");
                while(!rubixCube.getUpColor(0,0,1).equals(upColor))
                    rubixCube.circleHorizontal(true,true,(++count).toString(),"");
                algorithm.upTwist(true,true,(++count).toString());
                rubixCube.upHorizontal(true,true,(++count).toString(),"");
                algorithm.upTwist(true,true,(++count).toString());
                while(!isCenterAligned())
                    rubixCube.upHorizontal(false,true,(++count).toString(),"");
            }
            else if(isAdjacentColorsEqual()){
                while(!(rubixCube.getBackColor(2,0,1).equals(backColor) && rubixCube.getRightColor(1,0,2).equals(rightColor)))
                    rubixCube.circleHorizontal(true,true,(++count).toString(),"");
            
                algorithm.upTwist(true,true,(++count).toString());
                while(!isCenterAligned())
                    rubixCube.upHorizontal(false,true,(++count).toString(),"");
            }
            else if(!isCenterAligned()){//else if(!isCenterAligned)
                int value=0;
                while(!isAdjacentColorsEqual() && !isOppositeColorsEqual()){//while
                    if(value==4)
                        algorithm.upTwist(true,true,(++count).toString());
                    
                    rubixCube.upHorizontal(false,true,(++count).toString(),"");
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
