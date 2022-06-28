package RubixCubeJava;

public class LayerThird {
    
    private RubixCube rubixCube;
    private Integer count;
    
    public LayerThird(RubixCube rubixCube, Integer count){
        this.rubixCube=rubixCube;
        this.count=count;
    }
    
    private void setCorner(){
        String upColor=rubixCube.getUpMidColor();
        
        for(int value=1;value<=4;value++){
            while(!rubixCube.getUpColor(0,0,2).equals(upColor))
                (new Algorithm(rubixCube)).L(true,true,(++count).toString());
            rubixCube.upHorizontal(true,true,(++count).toString(),"");

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
