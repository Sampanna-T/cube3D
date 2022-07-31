CC = javac
DFLAG = -d
CPFLAG = -cp
RM = rm -rf
MK = mkdir
RN = java
MAIN = Main

CLSPATH = build

SRC =  src/com/cube/Node.java\
src/com/cube/ValidateCube.java\
src/com/cube/RubiksCube.java\
src/com/cube/RubiksCube3X3.java\
src/com/cube/solve/Algorithm3X3.java\
src/com/cube/solve/PlusBottom.java\
src/com/cube/solve/LayerFirst.java\
src/com/cube/solve/LayerSecond.java\
src/com/cube/solve/PlusTop.java\
src/com/cube/solve/AlignCenter.java\
src/com/cube/solve/Corner.java\
src/com/cube/solve/LayerThird.java\
src/com/cube/solve/Solve3X3.java\
src/com/cube/solve/Optimizer.java\
Main.java\


all: compileSrc run

compileSrc:
	$(CC) $(CPFLAG) $(CLSPATH) $(SRC) $(DFLAG) $(CLSPATH) 

run:
	$(RN) $(CPFLAG) $(CLSPATH) $(MAIN)


clean: 
	$(RM) $(CLSPATH)
	$(MK) $(CLSPATH)   