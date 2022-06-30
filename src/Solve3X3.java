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
    
    public static void add(String move, RubiksCube3X3 cube3X3, List<Pair<String,String>> solution){
        solution.add(new Pair(move,cube3X3.toString()));
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
        LayerFirst layerFirstObj = new LayerFirst(cube3X3,solution);
        layerFirstObj.solveAll();
    }

    public static void layerSecond(RubiksCube3X3 cube3X3,List <Pair<String,String>>solution)throws Exception{
        LayerSecond layerSecondObj = new LayerSecond(cube3X3,solution);
        layerSecondObj.solveAll();
    }

    public static void plusTop(RubiksCube3X3 cube3X3,List <Pair<String,String>>solution)throws Exception{
        PlusTop plusTopObj = new PlusTop(cube3X3,solution);
        plusTopObj.solveAll();
    }

    public static void alignCenter(RubiksCube3X3 cube3X3,List <Pair<String,String>>solution)throws Exception{
        AlignCenter alignCenterObj = new AlignCenter(cube3X3,solution);
        alignCenterObj.solveAll();
    }

    public static void corner(RubiksCube3X3 cube3X3,List <Pair<String,String>>solution)throws Exception{
        Corner cornerObj = new Corner(cube3X3,solution);
        cornerObj.solveAll();
    }

    public static void layerThird(RubiksCube3X3 cube3X3,List <Pair<String,String>>solution)throws Exception{
        LayerThird layerThirdObj = new LayerThird(cube3X3,solution);
        layerThirdObj.solveAll();
    }

}