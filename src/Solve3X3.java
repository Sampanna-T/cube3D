/**
 * @file Solve3X3.java
 * @author Sampanna T (kashisadan16@gmail.com)
 * @brief 
 * Solves the RubiksCube using beginners approach
 * 
 * @date 24th June 2022
 */
import javafx.util.Pair;
import java.util.*;

public class Solve3X3{

    RubiksCube3X3 cube3X3;

    public Solve3X3(RubiksCube3X3 cube3X3){
        this.cube3X3 = cube3X3;
    }


    public static List<Pair<String,String>> solveCube(RubiksCube3X3 cube3X3){
        try{
            List <Pair<String,String>>solution = new ArrayList<Pair<String,String>>();
            plusBottom(cube3X3,solution);
            layerFirst(cube3X3,solution);
            layerSecond(cube3X3,solution);
            plusTop(cube3X3,solution);
            alignCenter(cube3X3,solution);
            corner(cube3X3,solution);
            layerThird(cube3X3,solution);
            return solution;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void plusBottom(RubiksCube3X3 cube3X3,List <Pair<String,String>>solution)throws Exception{
        PlusBottom plusBottomObj= new PlusBottom(cube3X3,solution);
        plusBottomObj.solveAll();
    }

    public static void layerFirst(RubiksCube3X3 cube3X3,List <Pair<String,String>>solution)throws Exception{

    }

    public static void layerSecond(RubiksCube3X3 cube3X3,List <Pair<String,String>>solution)throws Exception{

    }

    public static void plusTop(RubiksCube3X3 cube3X3,List <Pair<String,String>>solution)throws Exception{

    }

    public static void alignCenter(RubiksCube3X3 cube3X3,List <Pair<String,String>>solution)throws Exception{

    }

    public static void corner(RubiksCube3X3 cube3X3,List <Pair<String,String>>solution)throws Exception{

    }

    public static void layerThird(RubiksCube3X3 cube3X3,List <Pair<String,String>>solution)throws Exception{

    }

}