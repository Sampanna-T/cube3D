


# ***Rubik's Cube Solver***

### RubiksCubeSolver is basically a GUI project which helps the user to solve the Rubiks cube for any valid color input stage by stage as shown below.



## STAGES INVOLVED IN SOLVING A RUBIKSCUBE 
![Rubiks cube stages](https://github.com/Sampanna-T/cube3D/blob/master/Image/RUBIKSCUBE_STAGES.png)

### Following are the different faces of the Rubiks cube which are mapped to 2D square grids in the GUI as shown below.



## ACTUAL FACES OF RUBIKSCUBE
![How to Fill Color](https://github.com/Sampanna-T/cube3D/blob/master/Vedio/RubiksCubeFaces.gif)



## MAPPED 2D SQUARE GRIDS OF RUBIKSCUBE IN THE GUI
![Rubiks faces](https://github.com/Sampanna-T/cube3D/blob/master/Image/RUBIKSCUBE_FACES.png)



# HOW TO USE RUBIKSCUBE SOLVER APPLICATION

## 1) Filling the colors
Keep on selecting the colors for all the boxes as shown below.

![How to Fill Color](https://github.com/Sampanna-T/cube3D/blob/master/Vedio/FillColor.gif)

## 2) Solve button
### Once all the colors have been entered press solve botton.
### (a) If the color input is invalid then it will be shown in the green field.
### (b) If the color input is valid then "PREV" & "NEXT" button gets activated to navigate through the solution list as shown below.

![Rubiks color input cases](https://github.com/Sampanna-T/cube3D/blob/master/Image/COLOR_INPUT_CASE.png)

## 3) Save and Load
### This option allows the user to save the state of the RubiksCube(i.e. input color) at any given point and continue using it again from the same state using the load option.

## 4) Reset
### This option resets every thing in the GUI.


# PROGRAM FLOW

### (a) Our program starts with Main by creating MainFrame3X3 instance and calling display method of MainFrame3X3.

### (b) The display method is responsible for displaying the GUI.

### (c) When solve button is pressed in the GUI a solveCube(color) method of SolveCube3X3 is called, which does two things 
    1. Creates RubiksCube3X3 instance
    2. Solves the RubiksCube3X3 and returns the solution list

### (d) If soution exists
        Solution list will be returned and navigation option will be available for the solution list.
###     else
        Displays "INVALID COLOR INPUT"


![Rubiks color input cases](https://github.com/Sampanna-T/cube3D/blob/master/Image/PROGRAM_FLOW.png)


# LEARNINGS

## 1) Object oriented programming concepts

##     (a) Inheritance
###        Example: 
                Using RubiksCube as the parent class and RubiksCube3X3.
                
##     (b) Abstraction
###        Example:
                Using RubiksCubeInterface helps in achieving abstraction by not revealing the details of the functionality but providing the necessary functionality.

##     (c) Encapsulation
###        Example: 
                Using the concept of Encapsulation RubiksCube class was successfully able to provide functionalities that a RubiksCube can perform by tightly encapsulating the data members and the associated functions.
                 Using the concept of Encapsulation even MainFrame3X3 was also
        successfully able to provide functionalities that the GUI needed by tightly encapsulating the data members and the assicated functions.

##     (d) Compile time Polymorphism
###        Example:
                Solve3X3 class has methods like solveCube(),solveCube(colorInput),solveCube(cube3X3) which is an example for compile time polymorphism.

##     (e) Run time Polymorphism
###        Example:
                RubiksCube3X3 has methods circleHorizontal(direction),circleVertical(direction) and circleRotate(direction) which is an example for run time polymorphism.
                 Similarly actionPerformed(ActionEvent e) in MainFrame3X3 also is overriden to provide new options for the button click events in our case.

## 2) Exception handling

### Example:
        Using the concept of checked Exceptions it was also possible to handle Exceptions in the RubiksCube like colorinvalid excetpion, unsolvable exception etc.

## 3) Data structure & problem solving

###     Example:
            (a) Using the concept of 3D array Entire structure of RubiksCube could be built successfully. 
            (b) Using the concept of transponse of matix RubiksCube operations like rotate, horizontal, vertical could be easily achieved.         
            (c) Using HashMap validation of all the colors was successfully done to check if the given color input is valid or not.

## 4) Basics of GUI

###     Example: 
            Learnt to use the Java swing options to build a beautiful GUI with the help of Jbutton,JTextField,JPanel,JLabel etc.
            Learnt more about the ActionListener and improvize the GUI by adding user interactive features.

## 5) Algorithms

###     Example:
            Used different algorithms to solve RubiksCube at various stages like PlusBottom, LayerFirst, LayerSecond, PlusTop, AlignCenter, Corner and LayerThird.
            Used Optimizer to further optimize the steps involved in solving the RubiksCube.

# Future Enhancements

 1) Improve the GUI to provide better 3D visualization of Cube.
 2) Provide implementations for RubiksCube of different dimensions.
 3) Provide different approaches to solve the RubiksCube other than beginners approach.
