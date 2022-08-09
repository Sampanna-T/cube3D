package src.solve3X3;

import src.cube3X3.RubiksCube3X3;
import src.cube.RubiksCubeException;
import javafx.util.Pair;
import java.util.List;
import java.util.ArrayList;

/**
 * Solves the RubiksCube using beginners approach
 * 
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 24th June 2022
 * @since JDK 10.0.2
 */
public interface Solve3X3 {

    /**
     * adds [move to be performed, RubiksCube state] pair to the solution list
     * 
     * @param move
     *                 holds move to be performed
     * @param cube3X3
     *                 Reference to RubiksCube3X3 object
     * @param solution
     *                 Reference to a list containing [move to be performed,
     *                 Rubikscube state] pair
     */
    static void add(String move, RubiksCube3X3 cube3X3, List<Pair<String, String>> solution){
        if(cube3X3 == null){
            Pair<String, String> currentPair = new Pair<>(move,"(:");
            solution.add(currentPair);
        }
        else{
            Pair<String, String> currentPair = new Pair<>(move, cube3X3.toString());
            solution.add(currentPair);
        }
    }

    /**
     * Solves the 3X3 RubiksCube and adds [move to be performed, RubiksCube state]
     * pair to solution list
     * 
     * @param cube3X3
     *                Reference to RubiksCube3X3 object
     * @return List
     */
    public static List<Pair<String, String>> solveCube(RubiksCube3X3 cube3X3) {
        try {
            if(cube3X3 == null)throw RubiksCubeException.invalidCube();
            List<Pair<String, String>> solution = new ArrayList<Pair<String, String>>();
            
            plusBottom(cube3X3, solution);
            add("PLUS BOTTOM SOLVED",null, solution);
            layerFirst(cube3X3, solution);
            add("LAYER FIRST SOLVED", null, solution);
            layerSecond(cube3X3, solution);
            add("LAYER SECOND SOLVED",null,solution);
            plusTop(cube3X3, solution);
            add("PLUS TOP SOLVED",null,solution);
            alignCenter(cube3X3, solution);
            add("ALIGN CENTER SOLVED",null,solution);
            corner(cube3X3, solution);
            add("CORNER SOLVED",null,solution);
            layerThird(cube3X3, solution);
            add("LAYER THIRD SOLVED",null,solution);
            return solution;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * solves PlusBottom of RubiksCube i.e. Stage1 and adds [move to be performed,
     * RubiksCube state]
     * pair to the solution list
     * 
     * @param cube3X3
     *                 Reference to RubiksCube3X3 object
     * @param solution
     *                 Reference to a list containing [move to be performed,
     *                 Rubikscube state] pair
     * @throws Exception
     *                   If PlusBottom can't be solved
     */
    private static void plusBottom(RubiksCube3X3 cube3X3, List<Pair<String, String>> solution) throws Exception {
        Optimizer optimizerObj = new Optimizer(cube3X3, solution);
        if (optimizerObj.optimize((byte) 1))
            return;
        PlusBottom plusBottomObj = new PlusBottom(cube3X3, solution);
        plusBottomObj.solveAll();
    }

    /**
     * solves LayerFirst of RubiksCube i.e. Stage2 and adds [move to be performed,
     * RubiksCube state]
     * pair to the solution list
     * 
     * @param cube3X3
     *                 Reference to RubiksCube3X3 object
     * @param solution
     *                 Reference to a list containing [move to be performed,
     *                 Rubikscube state] pair
     * @throws Exception
     *                   If LayerFirst can't be solved
     */
    private static void layerFirst(RubiksCube3X3 cube3X3, List<Pair<String, String>> solution) throws Exception {
        Optimizer optimizerObj = new Optimizer(cube3X3, solution);
        if (optimizerObj.optimize((byte) 2))
            return;
        LayerFirst layerFirstObj = new LayerFirst(cube3X3, solution);
        layerFirstObj.solveAll();
    }

    /**
     * solves LayerSecond of RubiksCube i.e. Stage3 and adds [move to be performed,
     * RubiksCube state]
     * pair to the solution list
     * 
     * @param cube3X3
     *                 Reference to RubiksCube3X3 object
     * @param solution
     *                 Reference to a list containing [move to be performed,
     *                 Rubikscube state] pair
     * @throws Exception
     *                   If LayerSecond can't be solved
     */
    private static void layerSecond(RubiksCube3X3 cube3X3, List<Pair<String, String>> solution) throws Exception {
        Optimizer optimizerObj = new Optimizer(cube3X3, solution);
        if (optimizerObj.optimize((byte) 3))
            return;
        LayerSecond layerSecondObj = new LayerSecond(cube3X3, solution);
        layerSecondObj.solveAll();
    }

    /**
     * solves PlusTop of RubiksCube i.e. Stage4 and adds [move to be performed,
     * RubiksCube state]
     * pair to the solution list
     * 
     * @param cube3X3
     *                 Reference to RubiksCube3X3 object
     * @param solution
     *                 Reference to a list containing [move to be performed,
     *                 Rubikscube state] pair
     * @throws Exception
     *                   If PlusTop can't be solved
     */
    private static void plusTop(RubiksCube3X3 cube3X3, List<Pair<String, String>> solution) throws Exception {
        Optimizer optimizerObj = new Optimizer(cube3X3, solution);
        if (optimizerObj.optimize((byte) 4))
            return;
        PlusTop plusTopObj = new PlusTop(cube3X3, solution);
        plusTopObj.solveAll();
    }

    /**
     * solves AlignCenter of RubiksCube i.e. Stage5 and adds [move to be performed,
     * RubiksCube state]
     * pair to the solution list
     * 
     * @param cube3X3
     *                 Reference to RubiksCube3X3 object
     * @param solution
     *                 Reference to a list containing [move to be performed,
     *                 Rubikscube state] pair
     * @throws Exception
     *                   If AlignCenter can't be solved
     */
    private static void alignCenter(RubiksCube3X3 cube3X3, List<Pair<String, String>> solution) throws Exception {
        Optimizer optimizerObj = new Optimizer(cube3X3, solution);
        if (optimizerObj.optimize((byte) 5))
            return;
        AlignCenter alignCenterObj = new AlignCenter(cube3X3, solution);
        alignCenterObj.solveAll();
    }

    /**
     * solves Corner of RubiksCube i.e. Stage6 and adds [move to be performed,
     * RubiksCube state]
     * pair to the solution list
     * 
     * @param cube3X3
     *                 Reference to RubiksCube3X3 object
     * @param solution
     *                 Reference to a list containing [move to be performed,
     *                 Rubikscube state] pair
     * @throws Exception
     *                   If Corner can't be solved
     */
    private static void corner(RubiksCube3X3 cube3X3, List<Pair<String, String>> solution) throws Exception {
        Optimizer optimizerObj = new Optimizer(cube3X3, solution);
        if (optimizerObj.optimize((byte) 6))
            return;
        Corner cornerObj = new Corner(cube3X3, solution);
        cornerObj.solveAll();
    }

    /**
     * solves LayerThird of RubiksCube i.e. Stage7 and adds [move to be performed,
     * RubiksCube state]
     * pair to the solution list
     * 
     * @param cube3X3
     *                 Reference to RubiksCube3X3 object
     * @param solution
     *                 Reference to a list containing [move to be performed,
     *                 Rubikscube state] pair
     * @throws Exception
     *                   If LayerThird can't be solved
     */
    private static void layerThird(RubiksCube3X3 cube3X3, List<Pair<String, String>> solution) throws Exception {
        Optimizer optimizerObj = new Optimizer(cube3X3, solution);
        if (optimizerObj.optimize((byte) 7))
            return;
        LayerThird layerThirdObj = new LayerThird(cube3X3, solution);
        layerThirdObj.solveAll();
    }

}