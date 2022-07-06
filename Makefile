CC = javac
DFLAG = -d
CPFLAG = -cp
RM = rm -rf
MK = mkdir
RN = java
MAIN = Main

CLSPATH = build

INC = src/Node.java\
inc/RubiksCube.java\

SRC = src/RubiksCube3X3.java\
src/Algorithm3X3.java\
src/PlusBottom.java\
src/LayerFirst.java\
src/LayerSecond.java\
src/PlusTop.java\
src/AlignCenter.java\
src/Corner.java\
src/LayerThird.java\
src/Solve3X3.java\
src/ValidateCube.java\
Main.java\


all: compileInc  compileSrc run

compileInc: 
	$(CC) $(CPFLAG) $(CLSPATH) $(INC) $(DFLAG) $(CLSPATH)

compileSrc:
	$(CC) $(CPFLAG) $(CLSPATH) $(SRC) $(DFLAG) $(CLSPATH) 

run:
	$(RN) $(CPFLAG) $(CLSPATH) $(MAIN)


clean: 
	$(RM) $(CLSPATH)
	$(MK) $(CLSPATH)   