package RubixCubeJava;

public class Corner {

    private RubixCube rubixCube;
    private Integer count;
    
    public Corner(RubixCube rubixCube, Integer count){
        this.rubixCube=rubixCube;
        this.count=count;
    }
    
    private boolean isFrontLeftCornerValid(){
        int i=0,j=0,k=0;
        String frontColor = rubixCube.getFrontMidColor();
        String upColor = rubixCube.getUpMidColor();
        String leftColor = rubixCube.getLeftMidColor();
        boolean isValid=false;
        
        String []colors = rubixCube.getColors(i, j, k);
        
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
        String backColor = rubixCube.getBackMidColor();
        String upColor = rubixCube.getUpMidColor();
        String leftColor = rubixCube.getLeftMidColor();
        boolean isValid=false;
        
        String []colors = rubixCube.getColors(i, j, k);
        
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
        String backColor = rubixCube.getBackMidColor();
        String upColor = rubixCube.getUpMidColor();
        String rightColor = rubixCube.getRightMidColor();
        boolean isValid=false;
        
        String []colors = rubixCube.getColors(i, j, k);
        
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
        String frontColor = rubixCube.getFrontMidColor();
        String upColor = rubixCube.getUpMidColor();
        String rightColor = rubixCube.getRightMidColor();
        boolean isValid=false;
        
        String []colors = rubixCube.getColors(i, j, k);
        
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
                    rubixCube.circleHorizontal(true,true,(++count).toString(),"");
               }
               while(!(isAllCornerAligned())){
                    (new Algorithm(rubixCube)).corner(true,true,(++count).toString());
               }
            }
            else{
                (new Algorithm(rubixCube)).corner(true,true,(++count).toString());
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
