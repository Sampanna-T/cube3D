package RubixCubeJava;

public class LayerFirst {
    
    private RubixCube rubixCube;
    private Integer count;
    boolean topFlag;
    
    public LayerFirst(RubixCube rubixCube, int count){
        this.rubixCube=rubixCube;
        this.count=count;
        this.topFlag=false;
    }
    
    //checks whether corner piece is the
    private boolean isCornerValid(int i, int j, int k){
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
    
    //checks whether the corner piece is in the right position or not
    private boolean isCornerSet(){
        String frontColor = rubixCube.getFrontMidColor();
        String upColor = rubixCube.getUpMidColor();
        String rightColor = rubixCube.getRightMidColor();
        
        if(rubixCube.getFrontColor(0,0,2).equals(frontColor) && rubixCube.getUpColor(0,0,2).equals(upColor) && rubixCube.getRightColor(0,0,2).equals(rightColor))
            return true;
        else 
            return false;
    }
    
    //Places the corner piece to be inserted on Top in positions 0,0,2 or 0,2,2
    private void placeThePiece(){
    /*
    if topFlag=true implies corner piece is at 0,0,2
    else if topFlag=false implies corner piece is at 0,2,2
    */
        if(isCornerValid(0,0,2)||isCornerValid(0,2,2)){  
            if(isCornerValid(0,0,2)) 
                topFlag=true;//Only case where piece ends up at position 0,0,2
        }
        else if(isCornerValid(0,0,0)){//else if 
            rubixCube.leftVertical(true,true,(++count).toString(),"");
            rubixCube.downHorizontal(true,true,(++count).toString(),"");
            rubixCube.leftVertical(false,true,(++count).toString(),"");
        }
        else if(isCornerValid(0,2,0)){//else if
            rubixCube.downHorizontal(true,true,(++count).toString(),"");
        }//else if
        else if(isCornerValid(2,0,0)){//else if
            rubixCube.rotateBack(false,true,(++count).toString(),"");
            rubixCube.downHorizontal(true,true,(++count).toString(),"");
            rubixCube.downHorizontal(true,true,(++count).toString(),"");
            rubixCube.rotateBack(true,true,(++count).toString(),"");
        }//else if
        else if(isCornerValid(2,2,0)){//else if
            rubixCube.downHorizontal(true,true,(++count).toString(),"");
            rubixCube.downHorizontal(true,true,(++count).toString(),"");
        }//else if
        else if(isCornerValid(2,0,2)){//else if
            rubixCube.rotateBack(true,true,(++count).toString(),"");
            rubixCube.downHorizontal(false,true,(++count).toString(),"");
            rubixCube.rotateBack(false,true,(++count).toString(),"");
        }//else if
        else if(isCornerValid(2,2,2)){//else if
            rubixCube.downHorizontal(false,true,(++count).toString(),"");
        }//else if
    }

    //to set the corner piece At the proper location
    private void finalStep(String upColor){
        for(int itr = 0; itr < 4; itr++){
            placeThePiece();
            if(!isCornerSet()){//if(!isCornerSet)
                if(topFlag){
                    if(rubixCube.getFrontColor(0,0,2).equals(upColor)){
                        rubixCube.rotateFront(true,true,(++count).toString(),"");
                        rubixCube.downHorizontal(true,true,(++count).toString(),"");
                        rubixCube.rotateFront(false,true,(++count).toString(),"");
                        rubixCube.downHorizontal(false,true,(++count).toString(),"");
                        rubixCube.downHorizontal(false,true,(++count).toString(),"");
                        rubixCube.rightVertical(true,true,(++count).toString(),"");
                        rubixCube.downHorizontal(true,true,(++count).toString(),"");
                        rubixCube.rightVertical(false,true,(++count).toString(),"");
                    }
                    else if(rubixCube.getRightColor(0,0,2).equals(upColor)){
                        (new Algorithm(rubixCube)).L(true,true,(++count).toString());//firstuse
                        rubixCube.rightVertical(true,true,(++count).toString(),"");
                        rubixCube.downHorizontal(false,true,(++count).toString(),"");
                        rubixCube.rightVertical(false,true,(++count).toString(),"");
                    }
                }//if(topFlag)
                else{
                    if(rubixCube.getFrontColor(0,2,2).equals(upColor)){
                        rubixCube.downHorizontal(false,true,(++count).toString(),"");
                        rubixCube.rightVertical(true,true,(++count).toString(),"");
                        rubixCube.downHorizontal(true,true,(++count).toString(),"");
                        rubixCube.rightVertical(false,true,(++count).toString(),"");
                    }
                    else if(rubixCube.getRightColor(0,2,2).equals(upColor)){
                        rubixCube.rightVertical(true,true,(++count).toString(),"");
                        rubixCube.downHorizontal(false,true,(++count).toString(),"");
                        rubixCube.rightVertical(false,true,(++count).toString(),"");
                    }
                    else if(rubixCube.getDownColor(0,2,2).equals(upColor)){
                        rubixCube.rightVertical(true,true,(++count).toString(),"");
                        rubixCube.downHorizontal(false,true,(++count).toString(),"");
                        rubixCube.downHorizontal(false,true,(++count).toString(),"");
                        rubixCube.rightVertical(false,true,(++count).toString(),"");
                        rubixCube.downHorizontal(true,true,(++count).toString(),"");
                        rubixCube.rightVertical(true,true,(++count).toString(),"");
                        rubixCube.downHorizontal(false,true,(++count).toString(),"");
                        rubixCube.rightVertical(false,true,(++count).toString(),"");

                    }
                }//else
            }//if(!isCornerSet())
        topFlag = false;

        if(itr != 3)
            rubixCube.circleHorizontal(true,true,(++count).toString(),"");

        } //for
    }
    
    public void solveAll(){
        finalStep(rubixCube.getUpMidColor());
        System.out.println("________________________________________________");

    }

    public Integer getCount(){
        return count;
    }
    
}
