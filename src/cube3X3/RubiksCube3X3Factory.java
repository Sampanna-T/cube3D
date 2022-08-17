package src.cube3X3;

public class RubiksCube3X3Factory {

    public static RubiksCube3X3 getInstance(){
        try{
            RubiksCube3X3 cube3X3 = new RubiksCube3X3();
            return cube3X3;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static RubiksCube3X3 getInstance(String color){
        try{
            RubiksCube3X3 cube3X3 = new RubiksCube3X3(color);
            return cube3X3;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}