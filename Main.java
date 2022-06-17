public class Main{
	public static void main(String[] args){
		RubiksCube3X3 cube = new RubiksCube3X3();//R R R R Y G O Y O B W B O G B R B B O Y W Y W G B R G W B G W B B W R W O G Y O R O Y G G Y O G W O Y Y W R
		cube.display(RubiksCube3X3.FACE_WISE);
		cube.circleHorizontal(RubiksCube.H_RIGHT);
		cube.circleVertical(RubiksCube.V_UP);
		cube.display(RubiksCube3X3.FACE_WISE);
		System.out.println(cube);
		}
}