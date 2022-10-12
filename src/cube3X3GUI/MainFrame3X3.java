package src.cube3X3GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import src.solve3X3.*;

import java.util.Formatter;
import java.util.List;
import javafx.util.Pair;
import java.util.*;
import java.io.*;

/**
 * Provides user interface to help solve the RubiksCube
 * 
 * @author Sampanna T (kashi16sadan@gmail.com)
 * @version 1.0 8th August 2022
 * @since JDK 10.0.2
 */
public class MainFrame3X3 extends JFrame implements ActionListener{

    /*
     * holds the default title for the MainFrame
     */
    private static final String DEFAULT_TITLE = "RUBIKS CUBE SOLVER";
    /*
     * holds the default height for the MainFrame
     */
    private static final int DEFAULT_HEIGHT = 800;
    /*
     * holds the default width for the MainFrame
     */
    private static final int DEFAULT_WIDTH = 800;
    /*
     * holds the default dimension of RubiksCube i.e 3
     */
    private static final int DEFAULT_DIMENSION = 3;
    /*
     * holds the count of number of face colors
     */
    private static final int FACE_COUNT = 6;
    /*
     * holds the array of all colors present in RubiksCube
     */
    private static final String colors[] = {"RED","BLUE","WHITE","YELLOW","ORANGE","GREEN"};
   
    private List <Pair<String,String>>solutionList;//holds the solution list for given input color of RubiksCube
    private int solutionIndex;//holds the index for a given move
    private String initialState;//holds the color input Given by the user

    private int dimension;//holds width,height of main frame & dimension of RubiksCube
    private JPanel cubePanel[];//holds array of JPanel wherein each panel represents face of RubiksCube
    private JButton buttonPressed;//holds the JButton Object which has been pressed
    private JButton cubeButton[][];//holds all the JButton Objects which represent colors of RubiksCube

    private JLabel moveNumber;//holds the move number while solving the RubiksCube
    private JLabel moveRemaing;//holds the move remaining while solving the RubiksCube
    private JTextArea move;//holds the move to be performed while solving the RubiksCube

    private JTextArea info;//holds the information regarding instruction/error message etc.
    private JButton loadButton;//holds JButton Object which loads already saved RubiksCube color input
    private JButton saveButton;//holds JButton Object that helps in saving the RubiksCube color input into a file

    private JButton resetButton;//holds JButton Object which resets the mainframe
    private JButton solveButton;//holds JButton Object which solves the RubiksCube
    private JButton leftButton;//holds JButton Object to navigate to previous move
    private JButton rightButton;//holds JButton Object to navigate to next move

    /**
	 * constructor which initializes the MainFrame3X3 
	 */
    public MainFrame3X3(){
        this(DEFAULT_TITLE,DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
    
    /**
	 * constructor which initializes the MainFrame3X3
	 * 
	 * @param title
	 * represents title of MainFrame3X3 
	 */
    public MainFrame3X3(String title){
        this(title,DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }

    /**
	 * constructor which initializes the MainFrame3X3
	 * 
	 * @param title
	 * represents title of MainFrame3X3 
     * @param width
	 * represents width of MainFrame3X3
     * @param height
	 * represents of height
     * @param dimension
	 * represents dimension of RubiksCube   
	 */
    public MainFrame3X3(String title, int width, int height){
        this.dimension = DEFAULT_DIMENSION;
        this.buttonPressed = null;
        this.solutionIndex = -1;
        this.initialState = "";
        initFrame(title, width, height);
    }
    
    /**
     * Displays the user interface of MainFrame3X3
     */
    public void display(){
        Border blackBorder = BorderFactory.createLineBorder(Color.red);
        JPanel newPanelMain = getPanel(4,4,20,20,Color.white,blackBorder);
        
        cubePanel = new JPanel[FACE_COUNT];
        updateCubePanel(cubePanel,newPanelMain);

        JPanel controlPanel[] = new JPanel[4];
        updateControlPanel(controlPanel,newPanelMain);
        
        add(newPanelMain);
        setVisible(true);
    }

    private void updateCubePanel(JPanel cubePanel[],JPanel newPanelMain){
        Border blackBorder = BorderFactory.createLineBorder(Color.black);
        cubeButton = new JButton[FACE_COUNT][dimension*dimension];
        int index = 0;

        for(int i = 0; i < 12; i++){
            if(i==0 || i==1 || i==3 || i==8 || i==9 || i==11){
                newPanelMain.add(new JPanel(){{setBackground(Color.white);}});
            }
            else{
                cubePanel[index] = getPanel(dimension,dimension, 2,2, Color.black,blackBorder); 
                for(int j = 0; j < cubeButton[index].length; j++){
                    cubeButton[index][j] = getButton(Color.gray,blackBorder);
                    cubePanel[index].add(cubeButton[index][j]);
                }
                newPanelMain.add(cubePanel[index]);
                index++;
            }
        }

    }

    private void updateControlPanel(JPanel controlPanel[], JPanel newPanelMain){
        Border blackBorder = BorderFactory.createLineBorder(Color.black);
        JLabel moveNumberLabel = getLabel("MOVE NUMBER",Color.cyan,BorderFactory.createEmptyBorder());
        JLabel moveRemaingLabel = getLabel("MOVES REMAINING",Color.cyan,BorderFactory.createEmptyBorder());
        JLabel moveLabel = getLabel("MOVE", Color.cyan, BorderFactory.createEmptyBorder());
        controlPanel[0] = getPanel(3, 1, 5,5, Color.white,BorderFactory.createEmptyBorder());
        controlPanel[0].add(moveNumberLabel);
        controlPanel[0].add(moveRemaingLabel);
        controlPanel[0].add(moveLabel); 

        moveNumber = getLabel("",Color.green,blackBorder);
        moveRemaing = getLabel("",Color.green,blackBorder);
        move = getTextArea("",Color.green,blackBorder);
        controlPanel[1] = getPanel(3, 1, 5,5, Color.white,BorderFactory.createEmptyBorder());
        controlPanel[1].add(moveNumber);
        controlPanel[1].add(moveRemaing);
        controlPanel[1].add(move);  


        info = getTextArea("",Color.green,blackBorder);
        saveButton = getButton("SAVE",Color.magenta, blackBorder,15);
        loadButton = getButton("LOAD",Color.magenta, blackBorder,15);
        controlPanel[2] = getPanel(2, 1, 5,5, Color.white,BorderFactory.createEmptyBorder());
        controlPanel[2].add(info);
        JPanel temp1 = getPanel(1, 2, 5,5,Color.white,BorderFactory.createEmptyBorder());
        temp1.add(saveButton);
        temp1.add(loadButton);
        controlPanel[2].add(temp1);
        
       
        resetButton = getButton("RESET",Color.magenta,blackBorder,15);
        solveButton = getButton("SOLVE",Color.magenta,blackBorder,15);
        leftButton = getButton("PREV",Color.magenta, blackBorder,15);
        leftButton.setEnabled(false);
        rightButton = getButton("NEXT",Color.magenta, blackBorder,15);
        rightButton.setEnabled(false);
        JPanel temp2 = getPanel(1, 2, 5,5,Color.white,BorderFactory.createEmptyBorder());
        temp2.add(leftButton);
        temp2.add(rightButton);
        
        controlPanel[3] = getPanel(3, 1, 5,5, Color.white,BorderFactory.createEmptyBorder());
        controlPanel[3].add(resetButton);
        controlPanel[3].add(solveButton);
        controlPanel[3].add(temp2);

        for(int i = 0; i < controlPanel.length; i++)
        newPanelMain.add(controlPanel[i]);
    }

    //returns JTextArea Object with given text,color and border
    private JTextArea getTextArea(String text,Color color, Border border){
        JTextArea newTextArea = new JTextArea();
        newTextArea.setText(text);
        newTextArea.setOpaque(true);
        newTextArea.setBorder(border);
        newTextArea.setFont(new Font("PlayFair",Font.BOLD,15));
        newTextArea.setBackground(color);
        newTextArea.setForeground(Color.black);
        newTextArea.setVisible(true);
        newTextArea.setEditable(false);
        return newTextArea;
    }

    //returns JLabel Object with given text,color and border
    private JLabel getLabel(String text,Color color, Border border){
        JLabel newLabel = new JLabel();
        newLabel.setText(text);
        newLabel.setOpaque(true);
        newLabel.setBorder(border);
        newLabel.setFont(new Font("PlayFair",Font.BOLD,15));
        newLabel.setBackground(color);
        newLabel.setForeground(Color.black);
        newLabel.setHorizontalAlignment(JTextField.CENTER);	
        newLabel.setVerticalAlignment(JTextField.CENTER);
        newLabel.setVisible(true);
        return newLabel;
    }

    //returns JButton Object with given text,color,border and size
    private JButton getButton(String text, Color color, Border border, int size){
        JButton newButton = getButton(color, border);
        newButton.setText(text);
        newButton.setFont(new Font("PlayFair",Font.BOLD,size));
        return newButton;
    }

    //returns JButton Object with given color and border
    private JButton getButton(Color color, Border border){
        JButton newButton = new JButton();
        newButton.setBackground(color);
        newButton.setBorder(border);
        newButton.setVisible(true);
        newButton.addActionListener(this);
        return newButton;
    }

    //returns JPanel Object with given row,col,hGap,vGap,color,border
    private JPanel getPanel(int row, int col, int hGap, int vGap, Color color, Border border){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(row,col,hGap,vGap));
        newPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        newPanel.setBackground(color);
        newPanel.setBorder(border);
        newPanel.setVisible(true);
        return newPanel;
    }

    //creates a new Frame with given title,width and height
    private void initFrame(String title,int width, int height){
        setTitle(title);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocation(getXOrigin(width),getYOrigin(height));
        setVisible(true);
        setSize(width,height);
        setResizable(true);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //returns all the colors given as input by the user
    private String getAllColors(){
        StringBuilder sb = new StringBuilder();
        sb.append(getColors(3));
        sb.append(getColors(2));
        sb.append(getColors(1));
        sb.append(getColors(4));
        sb.append(getColors(0));
        sb.append(getColors(5));
        return sb.toString();
    }

    //returns all colors for a given face
    private String getColors(int index){
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < dimension*dimension; j++){
            Color curColor = cubeButton[index][j].getBackground();
            sb.append(getColor(curColor)+" ");
        }
        return sb.toString();
    }

    //returns String format of Color
    private String getColor(Color color){  
        if(color.equals(Color.red))
            return "RED";
        else if(color.equals(Color.white))
            return "WHITE";
        else if(color.equals(Color.blue))
            return "BLUE";
        else if(color.equals(Color.green))
            return "GREEN";
        else if(color.equals(Color.orange))
            return "ORANGE";
        else if(color.equals(Color.yellow))
            return "YELLOW";
        else
            return "GRAY";
    }   

    //returns Color format of string format
    private Color getColor(String color){
        switch(color){
            case "RED" : return Color.red;
            case "WHITE" : return Color.white;
            case "BLUE" : return Color.blue;
            case "GREEN" : return Color.green;
            case "ORANGE" : return Color.orange;
            case "YELLOW" : return Color.yellow;
        }
        return Color.gray;
    }   

    //disables color input by user while navigating through the solution list
    private void disableColorInput(){
        for(int i = 0; i < FACE_COUNT; i++){
            for(int j = 0; j < dimension*dimension; j++){
                cubeButton[i][j].setEnabled(false);
            }
        }
    }

    //returns part of array between startIndex and endIndex
    private String[] getPartialArray(String arr[], int startIndex, int endIndex){
        String []partialArray = new String[endIndex-startIndex+1];
        int partialArrayIndex = 0;
        for(int i = startIndex; i <= endIndex; i++)
            partialArray[partialArrayIndex++] = arr[i].trim();
        
        return partialArray;
    }

    //sets colors of entire RubiksCube based on allColors String
    private void setColors(String allColors){
        String allColor[] = allColors.split(" ");
        int index = 0;
        int colorsPerFace = dimension*dimension;
        setColors(3,getPartialArray(allColor, index, index+colorsPerFace-1));index+=colorsPerFace;
        setColors(2,getPartialArray(allColor, index, index+colorsPerFace-1));index+=colorsPerFace;
        setColors(1,getPartialArray(allColor, index, index+colorsPerFace-1));index+=colorsPerFace;
        setColors(4,getPartialArray(allColor, index, index+colorsPerFace-1));index+=colorsPerFace;
        setColors(0,getPartialArray(allColor, index, index+colorsPerFace-1));index+=colorsPerFace;
        setColors(5,getPartialArray(allColor, index, index+colorsPerFace-1));index+=colorsPerFace;

    }

    //sets colors for given face of RubiksCube with colors[]
    private void setColors(int index, String colors[]){ 
        int colorsIndex = 0;
        for(int i = 0; i < dimension*dimension; i++){
            Color color = getColor(colors[colorsIndex++]);
            cubeButton[index][i].setBackground(color);
        }
    }

    //returns x origin for given width
    private static int getXOrigin(int width){      
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        int halfScrWidth = (int)(dimension.getWidth()/2);
        int xOrigin = halfScrWidth-(width/2);
        return xOrigin;
    }

    //returns y origin for a given height
    private static int getYOrigin(int height){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        int halfScrHeight = (int)(dimension.getHeight()/2);
        int yOrigin = halfScrHeight-(height/2);
        return yOrigin;
        
    }

    /**
	 * Provides smaller window to enter color input for the RubiksCube
	 * 
	 * @author Sampanna T (kashi16sadan@gmail.com)
	 * @version 1.0 8th August 2022
	 * @since JDK 10.0.2
	 */
    private class PopUpFrame extends JFrame implements ActionListener,KeyListener{
        private int width,height;
        private String title;
        private JButton newButton[];

        /**
         * constructor which initializes the PopUpFrame
         * 
         * @param title
         * represents title of PopUpFrame 
         * @param width
         * represents width of PopUpFrame
         * @param height
         * represents of height  
	    */
        PopUpFrame(String title,int width, int height){
            this.width = width;
            this.height = height;
            this.title = title;
            initFrame();
        }
        
        //dispalys the GUI to input colors
        public void display(){
            Border blackBorder = BorderFactory.createLineBorder(Color.black);
            newButton = new JButton[FACE_COUNT];
            JPanel newPanel = getPanel(1, FACE_COUNT, 5, 5, Color.white, blackBorder);
            for(int i = 0; i < FACE_COUNT; i++){
                newButton[i] = getButton(getColor(colors[i]), blackBorder);
                newPanel.add(newButton[i]);
                newButton[i].addActionListener(this);
            }
            add(newPanel);
        }

        //returns JPanel Object with given row,col,hGap,vGap,color,border
        private JPanel getPanel(int row, int col, int hGap, int vGap, Color color, Border border){
            return MainFrame3X3.this.getPanel(row, col, hGap, vGap, color, border);
        }

        //returns JButton Object with given color,border
        private JButton getButton(Color color, Border border){
            return MainFrame3X3.this.getButton(color,border);
        }

        //initialized the PopUpFrame
        private void initFrame(){ 
            //ensuring that when PopUpFrame is closed MainFrame shouldn't be closed   
            addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    MainFrame3X3.this.setEnabled(true);
                    MainFrame3X3.this.buttonPressed.setBorder(BorderFactory.createLineBorder(Color.black)); 
                }
            });
            addKeyListener(this);
            setLayout(new BoxLayout(getContentPane(),BoxLayout.X_AXIS));
            setLocation(getXOrigin(width),getYOrigin(height));
            setVisible(true);
            setFocusable(true);
            setSize(width,height);
            setTitle(title);
            setResizable(false);
        }

        //sets the color of MainFrame RubiksCube based on String color input
        private void setColor(String color){
            MainFrame3X3.this.setEnabled(true);
            MainFrame3X3.this.buttonPressed.setBackground(getColor(color));
            MainFrame3X3.this.buttonPressed.setBorder(BorderFactory.createLineBorder(Color.black));     
            dispose();
        }

        //Handles ActionEvents on PopUpFrame
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < FACE_COUNT; i++){
                if(e.getSource() == newButton[i]){
                    setColor(MainFrame3X3.colors[i]);
                    break;
                }
            }
        }

        //Handles Key press events
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyChar() == 'r')
                setColor("RED");
            else if(e.getKeyChar() == 'b')
                setColor("BLUE");
            else if(e.getKeyChar() == 'w')
                setColor("WHITE");
            else if(e.getKeyChar() == 'g')
                setColor("GREEN");
            else if(e.getKeyChar() == 'y')
                setColor("YELLOW");
            else if(e.getKeyChar() == 'o')
                setColor("ORANGE");
            else
                setColor("DEFAULT");
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyTyped(KeyEvent e) {}

    } 

    
    //performs necessary updations while navigating solution list to left
    private void navigatePrev(){
        if(solutionIndex > 0){
            info.setText("");
            solutionIndex--;
            Pair <String,String>curPair = solutionList.get(solutionIndex);
            String curMove = curPair.getKey();
            String curState = curPair.getValue();
            if(curMove.length() == 1){
                info.setText(Solve3X3.getStageName(curMove)+"\nSOLVED\nSUCCESSFULLY");
            }
            else{
                String []splitStr = curMove.split(" ");
                String formatStr = "";
                for(int i = 0; i < 2; i++)formatStr += splitStr[i]+" ";
                formatStr += "\n";
                for(int i = 2; i < splitStr.length; i++)formatStr += splitStr[i]+" ";
                move.setText(formatStr);
                setColors(curState);
            }
            moveNumber.setText(Integer.toString(solutionIndex+1));
            moveRemaing.setText(Integer.toString(Solve3X3.getTotalMoves(solutionList)-solutionIndex-1));
        }
        else{
            if(solutionIndex == 0){
                solutionIndex--;
                move.setText("");
                moveNumber.setText("0");
                moveRemaing.setText(Integer.toString(Solve3X3.getTotalMoves(solutionList)));
                setColors(initialState);
            }
            else{
                info.setText("CAN'T GO BACK \n FURTHER");
            }
        }
    }

    //performs necessary updations while navigating solution list to right
    private void navigateNext(){
        int end = Solve3X3.getTotalMoves(solutionList)-1;
        if(solutionIndex < end){
            info.setText("");
            solutionIndex++;
            Pair <String,String>curPair = solutionList.get(solutionIndex);
            String curMove = curPair.getKey();
            String curState = curPair.getValue();
            if(curMove.length() == 1){
                info.setText(Solve3X3.getStageName(curMove)+"\nSOLVED\nSUCCESSFULLY");
            }
            else{
                String []splitStr = curMove.split(" ");
                String formatStr = "";
                for(int i = 0; i < 2; i++)formatStr += splitStr[i]+" ";
                formatStr += "\n";
                for(int i = 2; i < splitStr.length; i++)formatStr += splitStr[i]+" ";
                move.setText(formatStr);
                setColors(curState);
            }
            moveNumber.setText(Integer.toString(solutionIndex+1));
            moveRemaing.setText(Integer.toString(Solve3X3.getTotalMoves(solutionList)-solutionIndex-1));
            
        }
        else{
            info.setText("CAN'T GO FORWARD FURTHER");
        }
    }

    //Handles ActionEvents on MainFrame
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == solveButton){
            String colorInput = getAllColors();
            solutionList = Solve3X3.solveCube(colorInput);
            if(solutionList == null){
                info.setText("INVALID INPUT");
            }
            else{
                disableColorInput();
                info.setText("PRESS PREV / NEXT\nBUTTON\nTO NAVIGATE\nSOLUTION LIST");
                initialState = colorInput;
                solutionIndex = 0;
                leftButton.setEnabled(true);
                rightButton.setEnabled(true);
            }
        }
        else if(e.getSource() == leftButton){
            navigatePrev();
        }
        else if(e.getSource() == rightButton){
            navigateNext();
        }
        else if(e.getSource() == saveButton){
            try{
                Formatter f = new Formatter("src/cube3X3GUI/input.txt");
                f.format("%s",getAllColors());
                f.close();
            }
            catch(Exception exception){
                info.setText("FAILED TO SAVE");   
            }    
        }
        else if(e.getSource() == loadButton){
            try{
                File file = new File("src/cube3X3GUI/input.txt");
                if(file.exists()){
                    Scanner input = new Scanner(file); 
                    if(input.hasNext()){
                        setColors(input.nextLine());
                        loadButton.setEnabled(false);
                        setFocusable(true);
                        input.close();
                        return;
                    }
                    input.close();
                }
                info.setText("FAILED TO LOAD");
            }
            catch(Exception exception){
                exception.printStackTrace();
                info.setText("FAILED TO LOAD");
            }
        }
        else if(e.getSource() == resetButton){
            leftButton.setEnabled(false);
            rightButton.setEnabled(false);

            for(int i = 0; i < FACE_COUNT; i++){
                for(int j = 0; j < dimension*dimension; j++){
                    cubeButton[i][j].setBackground(Color.gray);
                    cubeButton[i][j].setEnabled(true);
                }
            }

            info.setText("");
            move.setText("");
            moveNumber.setText("");
            moveRemaing.setText("");
            loadButton.setEnabled(true);
        }
        else{
            for(int i = 0; i < FACE_COUNT; i++){
                for(int j = 0; j < dimension*dimension; j++){
                    if(e.getSource() == cubeButton[i][j]){
                        buttonPressed = cubeButton[i][j];
                        buttonPressed.setBorder(BorderFactory.createRaisedBevelBorder());
                        setEnabled(false);//disables the MainFrame3X3
                        PopUpFrame popUpFrame = new PopUpFrame("SET COLOR",300,75);
                        popUpFrame.display();
                        break;
                    }
                }
            }
        }
    }


}

//INPUT => RED ORANGE GREEN WHITE YELLOW GREEN RED BLUE ORANGE RED WHITE GREEN RED RED RED RED WHITE YELLOW BLUE RED WHITE RED WHITE YELLOW YELLOW ORANGE GREEN WHITE YELLOW ORANGE YELLOW ORANGE BLUE BLUE ORANGE GREEN BLUE GREEN YELLOW BLUE BLUE BLUE WHITE GREEN ORANGE BLUE ORANGE WHITE GREEN GREEN YELLOW YELLOW WHITE ORANGE 
