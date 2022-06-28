import java.util.List;
import javafx.util.Pair;
public class Main{
	public static void main(String[] args){
		RubiksCube3X3 cube = new RubiksCube3X3();//("R R R R Y G O Y O B W B O G B R B B O Y W Y W G B R G W B G W B B W R W O G Y O R O Y G G Y O G W O Y Y W R");//R R R R Y G O Y O B W B O G B R B B O Y W Y W G B R G W B G W B B W R W O G Y O R O Y G G Y O G W O Y Y W R
		List<Pair<String,String>> steps = Solve3X3.solveCube(cube);
		int count = 0;
		for(Pair myPair:steps){
			System.out.println(++count+")"+myPair);
		}
	}
}
/*r y y w w b o y y r y w g g r g g w o g g w y y g r o r b y w b o r w w w o b o o o b b g b r b r r g y b o*/