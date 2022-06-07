CC = javac
DFLAG = -d
CPFLAG = -cp
RM = rm -rf
MK = mkdir

CLSPATH = build

INC = inc/Cube.java\
inc/RubiksCube.java\

SRC = src/Node.java\
src/RubiksCube3X3.java\
Main.java\



all: compileSrc compileInc 

compileInc: 
	$(CC) $(CPFLAG) $(CLSPATH) $(INC) $(DFLAG) $(CLSPATH)

compileSrc:
	$(CC) $(CPFLAG) $(CLSPATH) $(SRC) $(DFLAG) $(CLSPATH) 

clean: 
	$(RM) $(CLSPATH)
	$(MK) $(CLSPATH)