import java.util.List;
import javafx.util.Pair;
import java.util.Scanner;
public class Main{
	public static void main(String[] args){
		RubiksCube3X3 cube = new RubiksCube3X3();//("O G G W G B R B B R R W R R G R B B B B B W B G W O G O O O W O O W W G Y Y Y Y Y Y G Y Y W O O R W R Y G R");
		List<Pair<String,String>> steps = Solve3X3.solveCube(cube);
		int count = 0;
		for(Pair myPair:steps){
			System.out.println(++count+")"+myPair);
			(new Scanner(System.in)).nextLine();
		}
	}
}

/*
B B W Y R Y W R B B R Y Y B G W B O O R R O O O G O G B B G B G G O G Y W G Y Y Y R R O O G W Y W W W R W R
*/

/*
g r w w r o b b o 
w y r r g g o y w
o w r y o b b r g
b w g g b o y r r
g b y g w o y y o
r o b b y g w w y
*/