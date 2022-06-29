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

/*
B B W Y R Y W R B B R Y Y B G W B O O R R O O O G O G B B G B G G O G Y W G Y Y Y R R O O G W Y W W W R W R
*/

/*
w o b o o o b b g 
g g r g g y w r w
o b y g r r b r b
y o w b b w r w r
o r g y y w g g o
r y y w w b o y y
*/