import java.util.List;
import javafx.util.Pair;
import java.util.Scanner;
import src.cube.*;
import src.cube3X3.*;
import src.solve3X3.*;
import src.cube3X3GUI.*;

public class Main {

	public static void main(String[] args) throws Exception {
		int dimension = 3;
        MainFrame3X3 myFrame = new MainFrame3X3("RUBIKSCUBE SOLVER",800,800,dimension);   
        myFrame.display();
	}

}
		/*String str = "r r r r r r b r o g g g g g g g g r o o o o o o y o y b b b b b b g b o w w w w w w w w w y y y y y y r y b";
		RubiksCube3X3 cube = new RubiksCube3X3(str);
		//cube.circleRotate(RubiksCube3X3.R_CLK);
		cube.horizontal(1,RubiksCube3X3.H_RIGHT);
		cube.vertical(1,RubiksCube3X3.V_UP);
		System.out.println(cube);
		List<Pair<String, String>> steps = Solve3X3.solveCube(cube);
		int count = 0;
		for (Pair <String,String>myPair : steps) {
			System.out.println((++count) + ")" + myPair);
		}*/