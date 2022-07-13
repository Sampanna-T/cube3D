import javafx.util.Pair;
import java.util.List;

/**
 * Reduces the number of moves in solving the RubiksCube3X3 
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 12th July 2022
 * @since JDK 10.0.2
 */
class Optimizer{

    private RubiksCube3X3 cube3X3;
    private List <Pair<String,String>>solution;
    
    /**
    * initializes Optimizer  
    * @param cube3X3
    * Reference to RubiksCube3X3 object
    * @param solution
    * Reference to a list containing [move to be performed, Rubikscube state] pair
	*/
    Optimizer(RubiksCube3X3 cube3X3,List <Pair<String,String>>solution){
        this.cube3X3 = cube3X3;
        this.solution = solution;
    }


    /**
    * returns true if optimize is performed
    * @param stage
    * stage 1,2,3,4,5,6,7 => PlusBottom,LayerFirst,LayerSecond,PlusTop,AlignCenter,Corner,LayerThrid
	*/
    boolean optimize(byte stage){
        byte faceNumber = 0;
        switch(stage){
            case 1: faceNumber = getPlusBottomFaceNumber();
                    if(faceNumber == 0)return false;
                    updateSolution(faceNumber);
                    return true;
            case 2: faceNumber = getLayerFirstFaceNumber();
                    if(faceNumber == 0)return false;
                    updateSolution(faceNumber);
                    return true;
            case 3: faceNumber = getLayerSecondFaceNumber();
                    if(faceNumber == 0)return false;
                    updateSolution(faceNumber);
                    return true;
            case 4: faceNumber = getPlusTopFaceNumber();
                    if(faceNumber == 0)return false;
                    updateSolution(faceNumber);
                    return true;
            case 5: faceNumber = getAlignCenterFaceNumber();
                    if(faceNumber == 0)return false;
                    updateSolution(faceNumber);
                    return true;
            case 6: faceNumber = getCornerFaceNumber();
                    if(faceNumber == 0)return false;
                    updateSolution(faceNumber);
                    return true;
            case 7: faceNumber = getLayerThirdFaceNumber();
                    if(faceNumber == 0)return false;
                    updateSolution(faceNumber);
                    return true;
        }
        return false;
    }


    /**
    * returns the best faceNumber for plusBottom 
	*/
    private byte getPlusBottomFaceNumber(){
        return getFaceNumber((byte)1);
    }


    /**
    * returns the best faceNumber for LayerFirst 
    */
    private byte getLayerFirstFaceNumber(){
        return getFaceNumber((byte)2);
    }

    
    /**
    * returns the best faceNumber for LayerSecond 
    */
    private byte getLayerSecondFaceNumber(){
        return getFaceNumber((byte)3);
    }

    
    /**
    * returns the best faceNumber for PlusTop 
    */
    private byte getPlusTopFaceNumber(){
        return getFaceNumber((byte)4);
    }

    
    /**
    * returns the best faceNumber for PlusTop 
    */
    private byte getAlignCenterFaceNumber(){
        return getFaceNumber((byte)5);
    }


    /**
    * returns the best faceNumber for PlusTop 
    */
    private byte getCornerFaceNumber(){
        return getFaceNumber((byte)6);
    }


    /**
    * returns the best faceNumber for PlusTop 
    */
    private byte getLayerThirdFaceNumber(){
        return getFaceNumber((byte)7);
    }

    
    /**
    * returns the best faceNumber for a given stage
    * stage 1,2,3,4,5,6,7 => PlusBottom,LayerFirst,LayerSecond,PlusTop,AlignCenter,Corner,LayerThrid
    * @return byte 
    */
    private byte getFaceNumber(byte stage){
        /*
        faceNumber = 0 means plusBottom not solved , 
        faceNumber = 1 means plusBottom found at Front ,
        faceNumber = 2 means plusBottom found at Up ,
        faceNumber = 3 means plusBottom found at Back ,
        faceNumber = 4 means plusBottom found at Down ,
        faceNumber = 5 means plusBottom found at Left ,
        faceNumber = 6 means plusBottom found at Right
        */
        byte faceNumber = 0;
        byte bestFaceNumber = 0;
        boolean faceNumberFound = false;// only applicable for stage = 1 i.e plusBottom
  
        for(int i = 0; i < 4; i++){
            ++faceNumber;

            switch(stage){
                case 1 : if(isPlusBottomSolved()){
                            if(!faceNumberFound){
                                bestFaceNumber = faceNumber;
                                if(isLayerFirstSolved())faceNumberFound = true;
                            }
                         }
                         break;
                case 2 : if(isLayerFirstSolved()){
                            bestFaceNumber = faceNumber;
                            if(!faceNumberFound)faceNumberFound = true;
                         }
                         break;
                case 3 : if(isLayerSecondSolved()){
                            bestFaceNumber = faceNumber;
                            if(!faceNumberFound)faceNumberFound = true;
                         }
                         break;
                case 4 : if(isPlusTopSolved()){
                            bestFaceNumber = faceNumber;
                            if(!faceNumberFound)faceNumberFound = true;
                         }
                         break;
                case 5 : if(isAlignCenterSolved()){
                            bestFaceNumber = faceNumber;
                            if(!faceNumberFound)faceNumberFound = true;
                         }
                         break;
                case 6 : if(isCornerSolved()){
                            bestFaceNumber = faceNumber;
                            if(!faceNumberFound)faceNumberFound = true;
                         }
            
                case 7 : if(isLayerThirdSolved()){
                            bestFaceNumber = faceNumber;
                            if(!faceNumberFound)faceNumberFound = true;
                         }
            }   
            cube3X3.circleVertical(RubiksCube3X3.V_DOWN);
        }
        
        cube3X3.circleRotate(RubiksCube3X3.R_ANTICLK);
        ++faceNumber;
        switch(stage){
            case 1 : if(isPlusBottomSolved()){
                        if(!faceNumberFound){
                            bestFaceNumber = faceNumber;
                            if(isLayerFirstSolved())faceNumberFound = true;
                        }
                     }
                     break;
            case 2 : if(isLayerFirstSolved()){
                        bestFaceNumber = faceNumber;
                        if(!faceNumberFound)faceNumberFound = true;
                     }
                     break;
            case 3 : if(isLayerSecondSolved()){
                        bestFaceNumber = faceNumber;
                        if(!faceNumberFound)faceNumberFound = true;
                     }
                     break;
            case 4 : if(isPlusTopSolved()){
                        bestFaceNumber = faceNumber;
                        if(!faceNumberFound)faceNumberFound = true;
                     }
                     break;
            case 5 : if(isAlignCenterSolved()){
                        bestFaceNumber = faceNumber;
                        if(!faceNumberFound)faceNumberFound = true;
                     }
                     break;
            case 6 : if(isCornerSolved()){
                        bestFaceNumber = faceNumber;
                        if(!faceNumberFound)faceNumberFound = true;
                     }
            case 7 : if(isLayerThirdSolved()){
                        bestFaceNumber = faceNumber;
                        if(!faceNumberFound)faceNumberFound = true;
                     }
        }   

        cube3X3.circleRotate(RubiksCube3X3.R_CLK);
        cube3X3.circleRotate(RubiksCube3X3.R_CLK);
        ++faceNumber;
        switch(stage){
            case 1 : if(isPlusBottomSolved()){
                        if(!faceNumberFound){
                            bestFaceNumber = faceNumber;
                            if(isLayerFirstSolved())faceNumberFound = true;
                        }
                     }
                     break;
            case 2 : if(isLayerFirstSolved()){
                        bestFaceNumber = faceNumber;
                        if(!faceNumberFound)faceNumberFound = true;
                     }
                     break;
            case 3 : if(isLayerSecondSolved()){
                        bestFaceNumber = faceNumber;
                        if(!faceNumberFound)faceNumberFound = true;
                     }
                     break;
            case 4 : if(isPlusTopSolved()){
                        bestFaceNumber = faceNumber;
                        if(!faceNumberFound)faceNumberFound = true;
                     }
                     break;
            case 5 : if(isAlignCenterSolved()){
                        bestFaceNumber = faceNumber;
                        if(!faceNumberFound)faceNumberFound = true;
                     }
                     break;
            case 6 : if(isCornerSolved()){
                        bestFaceNumber = faceNumber;
                        if(!faceNumberFound)faceNumberFound = true;
                     }
            case 7 : if(isLayerThirdSolved()){
                        bestFaceNumber = faceNumber;
                        if(!faceNumberFound)faceNumberFound = true;
                     }
        }   
        
        cube3X3.circleRotate(RubiksCube3X3.R_ANTICLK);

        return bestFaceNumber;
    }


    //updates the solution list 
    private void updateSolution(byte faceNumber){
        
        switch(faceNumber){
            case 0 : break;
            case 1 : break;
            case 2 : Solve3X3.add(cube3X3.circleVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
                     break;
            case 3 : Solve3X3.add(cube3X3.circleVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
                     Solve3X3.add(cube3X3.circleVertical(RubiksCube3X3.V_DOWN),cube3X3,solution);
                     break;
            case 4 : Solve3X3.add(cube3X3.circleVertical(RubiksCube3X3.V_UP),cube3X3,solution);
                     break;
            case 5 : Solve3X3.add(cube3X3.circleRotate(RubiksCube3X3.R_ANTICLK),cube3X3,solution);
                     break;
            case 6 : Solve3X3.add(cube3X3.circleRotate(RubiksCube3X3.R_CLK),cube3X3,solution);
                     break;
        }
    }


    /**
	* returns true if PlusBottom is solved
    * @return boolean 
	*/
    private boolean isPlusBottomSolved(){
        String frontMidColor = cube3X3.getFrontColor(0,1,1);
        String leftMidColor = cube3X3.getLeftColor(1,1,0);
        String backMidColor = cube3X3.getBackColor(2,1,1);
        String rightMidColor = cube3X3.getRightColor(1,1,2);
        String downMidColor = cube3X3.getDownColor(1,2,1);
        
        boolean isDownValid = cube3X3.getDownColor(0,2,1).equals(downMidColor) &&
                              cube3X3.getDownColor(1,2,0).equals(downMidColor) &&
                              cube3X3.getDownColor(2,2,1).equals(downMidColor) &&
                              cube3X3.getDownColor(1,2,2).equals(downMidColor);
        boolean isSideValid = cube3X3.getFrontColor(0,2,1).equals(frontMidColor) &&
                              cube3X3.getLeftColor(1,2,0).equals(leftMidColor) &&
                              cube3X3.getBackColor(2,2,1).equals(backMidColor) &&
                              cube3X3.getRightColor(1,2,2).equals(rightMidColor);

        if(isDownValid && isSideValid)
            return true;
        else
            return false;
    }
    

    /**
	* returns true if LayerFirst is solved
    * @return boolean 
	*/
    private boolean isLayerFirstSolved(){  
        if(!isPlusBottomSolved())return false;

        String frontMidColor = cube3X3.getFrontColor(0,1,1);
        String leftMidColor = cube3X3.getLeftColor(1,1,0);
        String backMidColor = cube3X3.getBackColor(2,1,1);
        String rightMidColor = cube3X3.getRightColor(1,1,2);
        String downMidColor = cube3X3.getDownColor(1,2,1);
        
        boolean isFDLValid = cube3X3.getFrontColor(0,2,0).equals(frontMidColor) &&
                             cube3X3.getLeftColor(0,2,0).equals(leftMidColor) &&
                             cube3X3.getDownColor(0,2,0).equals(downMidColor);
        
        boolean isFDRValid = cube3X3.getFrontColor(0,2,2).equals(frontMidColor) &&
                             cube3X3.getRightColor(0,2,2).equals(rightMidColor) &&
                             cube3X3.getDownColor(0,2,2).equals(downMidColor);
                             
        boolean isBDLValid = cube3X3.getBackColor(2,2,0).equals(backMidColor) &&
                             cube3X3.getLeftColor(2,2,0).equals(leftMidColor) &&
                             cube3X3.getDownColor(2,2,0).equals(downMidColor);
                             
        boolean isBDRValid = cube3X3.getBackColor(2,2,2).equals(backMidColor) &&
                             cube3X3.getRightColor(2,2,2).equals(rightMidColor) &&
                             cube3X3.getDownColor(2,2,2).equals(downMidColor);

        if(isFDLValid && isFDRValid && isBDLValid && isBDRValid)
            return true;
        else
            return false;
                             
    }


    /**
	* returns true if LayerSecond is solved
    * @return boolean 
	*/
    private boolean isLayerSecondSolved(){  
        if(!isLayerFirstSolved())return false;

        String frontMidColor = cube3X3.getFrontColor(0,1,1);
        String leftMidColor = cube3X3.getLeftColor(1,1,0);
        String backMidColor = cube3X3.getBackColor(2,1,1);
        String rightMidColor = cube3X3.getRightColor(1,1,2);
        
        boolean isFMLValid = cube3X3.getFrontColor(0,1,0).equals(frontMidColor) &&
                             cube3X3.getLeftColor(0,1,0).equals(leftMidColor);
        
        boolean isFMRValid = cube3X3.getFrontColor(0,1,2).equals(frontMidColor) &&
                             cube3X3.getRightColor(0,1,2).equals(rightMidColor);
                             
        boolean isBMLValid = cube3X3.getBackColor(2,1,0).equals(backMidColor) &&
                             cube3X3.getLeftColor(2,1,0).equals(leftMidColor);
                             
        boolean isBMRValid = cube3X3.getBackColor(2,1,2).equals(backMidColor) &&
                             cube3X3.getRightColor(2,1,2).equals(rightMidColor);

        if(isFMLValid && isFMRValid && isBMLValid && isBMRValid)
            return true;
        else
            return false;
                             
    }


    /**
	* returns true if PlusTop is solved
    * @return boolean 
	*/
    private boolean isPlusTopSolved(){  
        if(!isLayerSecondSolved())return false;

        String upMidColor = cube3X3.getUpColor(1,0,1);
        String upFUMColor = cube3X3.getUpColor(0,0,1);
        String upMULColor = cube3X3.getUpColor(1,0,0);
        String upBUMColor = cube3X3.getUpColor(2,0,1);
        String upMURColor = cube3X3.getUpColor(1,0,2);
        
        if(upFUMColor.equals(upMidColor) && upMULColor.equals(upMidColor) && upBUMColor.equals(upMidColor) && upMURColor.equals(upMidColor))
            return true;
        else
            return false;          
    }


    /**
	* returns true if AlignCenter is solved
    * @return boolean 
	*/
    private boolean isAlignCenterSolved(){  
        if(!isPlusTopSolved())return false;

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
	* returns true if Corner is solved  
	* @return boolean 
	*/
    private boolean isCornerSolved(){
        if(!isAlignCenterSolved())return false;

        String upMidColor = cube3X3.getUpColor(1,0,1);
        String frontMidColor = cube3X3.getFrontColor(0,1,1);
        String leftMidColor = cube3X3.getLeftColor(1,1,0);
        String backMidColor = cube3X3.getBackColor(2,1,1);
        String rightMidColor = cube3X3.getRightColor(1,1,2);
        
        String []FULcolors = cube3X3.getColors(0,0,0);
        if(!containsAll(FULcolors,frontMidColor,upMidColor,leftMidColor))return false;

        String []BULcolors = cube3X3.getColors(2,0,0);
        if(!containsAll(BULcolors,backMidColor,upMidColor,leftMidColor))return false;
        
        String []BURcolors = cube3X3.getColors(2,0,2);
        if(!containsAll(BURcolors,backMidColor,upMidColor,rightMidColor))return false;
        
        String []FURcolors = cube3X3.getColors(0,0,2);
        if(!containsAll(FURcolors,frontMidColor,upMidColor,rightMidColor))return false;

        return true;
    }
    

    //returns true if colors[] contains all colors color1,color2,color3
    private boolean containsAll(String colors[],String color1,String color2,String color3){
        boolean flag = false;
        
        for(int index = 0; index < 3; index++){
            if(colors[index].equals(color1) || colors[index].equals(color2) || colors[index].equals(color3)){
                flag = true;
            }
            else{
                flag = false;
                break;
            }
        }
        return flag;
    }


    /**
	* returns true if LayerThird is solved
    * @return boolean 
	*/
    private boolean isLayerThirdSolved(){  
        if(!isCornerSolved())return false;

        String frontMidColor = cube3X3.getFrontColor(0,1,1);
        String leftMidColor = cube3X3.getLeftColor(1,1,0);
        String backMidColor = cube3X3.getBackColor(2,1,1);
        String rightMidColor = cube3X3.getRightColor(1,1,2);
        String upMidColor = cube3X3.getUpColor(1,0,1);
        
        boolean isFULValid = cube3X3.getFrontColor(0,0,0).equals(frontMidColor) &&
                             cube3X3.getLeftColor(0,0,0).equals(leftMidColor) &&
                             cube3X3.getUpColor(0,0,0).equals(upMidColor);
        
        boolean isFURValid = cube3X3.getFrontColor(0,0,2).equals(frontMidColor) &&
                             cube3X3.getRightColor(0,0,2).equals(rightMidColor) &&
                             cube3X3.getUpColor(0,0,2).equals(upMidColor);
                             
        boolean isBULValid = cube3X3.getBackColor(2,0,0).equals(backMidColor) &&
                             cube3X3.getLeftColor(2,0,0).equals(leftMidColor) &&
                             cube3X3.getUpColor(2,0,0).equals(upMidColor);
                             
        boolean isBURValid = cube3X3.getBackColor(2,0,2).equals(backMidColor) &&
                             cube3X3.getRightColor(2,0,2).equals(rightMidColor) &&
                             cube3X3.getUpColor(2,0,2).equals(upMidColor);

        if(isFULValid && isFURValid && isBULValid && isBURValid)
            return true;
        else
            return false;                      
    }


}