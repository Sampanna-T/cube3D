public class Main{
	public static void main(String[] args){
		RubiksCube3X3 cube = new RubiksCube3X3();//new RubiksCube3X3("b b y r y y r g y o g w g o w r b b b w g b w y g y w o b y r r r g o y y g r r g o o y b w o r w b w g o o");
		cube.display(RubiksCube.NODE_WISE);
		cube.display(RubiksCube.FACE_WISE);
	}
}