package RubixCubeJava;

public class PlusTop {

    private RubixCube rubixCube;
    private Integer count;
    
    public PlusTop(RubixCube rubixCube, Integer count){
        this.rubixCube = rubixCube;
        this.count = count;
    }
    
    private boolean isTopPlus(){
        int j=0;
        String upColor = rubixCube.getUpMidColor();
        String frontUpColor = rubixCube.getUpColor(0, j, 1);
        String leftUpColor = rubixCube.getUpColor(1, j, 0);
        String backUpColor = rubixCube.getUpColor(2, j, 1);
        String rightUpColor = rubixCube.getUpColor(1, j, 2);
        
        if(frontUpColor.equals(upColor) && leftUpColor.equals(upColor) && backUpColor.equals(upColor) && rightUpColor.equals(upColor))
            return true;
        else
            return false;
    }
    
    private boolean isTopDot(){
        int j=0;
        String upColor = rubixCube.getUpMidColor();
        String frontUpColor = rubixCube.getUpColor(0, j, 1);
        String leftUpColor = rubixCube.getUpColor(1, j, 0);
        String backUpColor = rubixCube.getUpColor(2, j, 1);
        String rightUpColor = rubixCube.getUpColor(1, j, 2);
        
        if(!frontUpColor.equals(upColor) && !leftUpColor.equals(upColor) && !backUpColor.equals(upColor) && !rightUpColor.equals(upColor))
            return true;
        else
            return false;
    }
    
    private boolean isTopLORMinus(){
        int j=0;
        String upColor = rubixCube.getUpMidColor();
        String frontUpColor = rubixCube.getUpColor(0, j, 1);
        String leftUpColor = rubixCube.getUpColor(1, j, 0);
        String backUpColor = rubixCube.getUpColor(2, j, 1);
        String rightUpColor = rubixCube.getUpColor(1, j, 2);
        
        if( (leftUpColor.equals(upColor) && rightUpColor.equals(upColor)) || (leftUpColor.equals(upColor) && backUpColor.equals(upColor))  )
            return true;
        else 
            return false;
    }
    
    public void solvePlus(){
        Algorithm algorithm = new Algorithm(rubixCube);
        
        while(!isTopPlus()){//while
            if(isTopDot()){
                algorithm.twistFront(true,true,(++count).toString());
            }
            else{
                while(true){
                    if(isTopLORMinus() || isTopDot())
                        break;
                    else
                        rubixCube.upHorizontal(true,true,(++count).toString(),"");
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
