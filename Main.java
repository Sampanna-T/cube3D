public class Main{
	public static void main(String[] args){
		RubiksCube3X3 cube = new RubiksCube3X3();//new RubiksCube3X3("b b y r y y r g y o g w g o w r b b b w g b w y g y w o b y r r r g o w y g r r g o o y b w o r w b w g o o");
		cube.display(RubiksCube.FACE_WISE);
		/*
		int i =0,j=0,k=0;
		System.out.println("FRONT");
		for(j = 0; j < 3; j++)
			for(k = 0; k < 3; k++)
				System.out.println(cube.getFrontColor(i,j,k));

		i=2;
		
		System.out.println("BACK");
		for(j = 0; j < 3; j++)
			for(k = 0; k < 3; k++)
				System.out.println(cube.getBackColor(i,j,k));
		
		j=0;
		
		System.out.println("UP");
		for(i = 0; i < 3; i++)
			for(k = 0; k < 3; k++)
				System.out.println(cube.getUpColor(i,j,k));
		
		j=2;
		
		System.out.println("DOWN");
		for(i = 0; i < 3; i++)
			for(k = 0; k < 3; k++)
				System.out.println(cube.getDownColor(i,j,k));

		k=0;
		System.out.println("LEFT");
		for(j = 0; j < 3; j++)
			for(i = 0; i < 3; i++)
				System.out.println(cube.getLeftColor(i,j,k));

		k=2;
		System.out.println("RIGHT");
		for(j = 0; j < 3; j++)
			for(i = 0; i < 3; i++)
				System.out.println(cube.getRightColor(i,j,k));*/
	}
}