import java.util.List;
import javafx.util.Pair;
import java.util.Scanner;
public class Main{
	static long count = 0;
	public static void main(String[] args)throws Exception{
		/*Scanner input = new Scanner(System.in);
		String strTemp = input.next();
		String str = "";
		for(int i = 0;i < strTemp.length(); i++)str += strTemp.charAt(i)+" ";

		
		RubiksCube3X3 cube = new RubiksCube3X3(str);*/
		RubiksCube3X3 cube = new RubiksCube3X3("B B W Y R Y W R B B R Y Y B G W B O O R R O O O G O G B B G B G G O G Y W G Y Y Y R R O O G W Y W W W R W R");
		List<Pair<String,String>> steps = Solve3X3.solveCube(cube);
		int count = 0;
		for(Pair myPair:steps){
			System.out.println((++count)+")"+myPair);
		}
		//System.out.println(str);
		/*String str = "abcdefghijklm";
		fun(str,"");
		System.out.println(count);*/
	}
	public static void fun(String input, String output){
		if(input.length() == 0){
			count++;
			//System.out.println(count+")"+output);
		}
		
		for(int i = 0; i < input.length(); i++){
			fun(input.substring(0,i)+input.substring(i+1),output+input.charAt(i));
		}

	}
}

/*
B B W Y R Y W R B B R Y Y B G W B O O R R O O O G O G B B G B G G O G Y W G Y Y Y R R O O G W Y W W W R W R
G R W W R O B B O W Y R R G G O Y W O W R Y O B B R G B W G G B O Y R R G B Y G W O Y Y O R O B B Y G W W Y
B Y O W G B R W O G B R O W B Y G Y Y G O R B B W R R B G O R Y Y B W G W R G Y R O W O Y B O W Y O G G W R
B W O G Y R O G R Y W R G B O W Y W G O B O W Y R Y G G R Y B G Y Y O Y O W O B O G W R W B W G B R B R R B
B G G R O Y B W O G B W Y G W Y Y W G G O B R B Y B B R G R R B R Y O G W W Y O Y R O Y W R O B O W G R W O
*/
/*FRONT -> B B W Y R Y W R B 
LEFT ->  B R Y Y B G W B O 
BACK ->  O R R O O O G O G 
RIGHT -> B B G B G G O G Y 
UP -> 	 W G Y Y Y R R O O 
DOWN ->  G W Y W W W R W R

g o r r w o r g b
b b y b r b o g g
o o y y y y w r g
b r b w o g o y g
r b w w g w o y w
y o y w b r w g r

g o r b w o r g r
b b y b r b o g g
o o y y y y w r g
b r b w o g o y g
r b w w g w o y w
y o y w b r w g r

*/