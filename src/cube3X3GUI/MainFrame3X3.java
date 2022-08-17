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


public class MainFrame3X3 extends JFrame implements ActionListener{

    private final String DEFAULT_TITLE = "";
    private final int DEFAULT_HEIGHT = 800;
    private final int DEFAULT_WIDTH = 800;
    private final int DEFAULT_DIMENSION = 3;
    private final Color DEFAULT_COLOR = Color.gray;
    private final int FACE_COUNT = 6;
    private final String colors[] = {"RED","BLUE","WHITE","YELLOW","ORANGE","GREEN"};
   
    List <Pair<String,String>>solutionList;
    int solutionIndex;
    String initialState;

    int width, height, dimension;
    String title;
    JPanel cubePanel[];
    JButton buttonPressed;
    JButton cubeButton[][];

    JLabel moveNumber;
    JLabel moveRemaing;
    JTextArea move;

    JTextArea info;
    JButton loadButton;
    JButton saveButton;

    JButton resetButton;
    JButton solveButton;
    JButton leftButton;
    JButton rightButton;

    public MainFrame3X3(){
        this.dimension = DEFAULT_DIMENSION;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.title = DEFAULT_TITLE;
        this.buttonPressed = null;
        this.solutionIndex = -1;
        this.initialState = "";
        initFrame(title, width, height);
    }
    
    public MainFrame3X3(String title){
        this.dimension = DEFAULT_DIMENSION;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.title = title;
        this.buttonPressed = null;
        this.solutionIndex = -1;
        this.initialState = "";
        initFrame(title, width, height);
    }

    public MainFrame3X3(String title, int width, int height, int dimension){
        this.dimension = dimension;
        this.title = title;
        this.width = width;
        this.height = height;
        this.buttonPressed = null;
        this.solutionIndex = -1;
        this.initialState = "";
        initFrame(title, width, height);
    }
    
    public void display(){
        Border blackBorder = BorderFactory.createLineBorder(Color.black);
        JPanel newPanelMain = getPanel(4,4,20,20,Color.white,blackBorder);
        
        cubePanel = new JPanel[FACE_COUNT];
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

        JPanel controlPanel[] = new JPanel[4];

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
        
        for(int i = 0; i < controlPanel.length; i++){
            newPanelMain.add(controlPanel[i]);
        }

        add(newPanelMain);

        setVisible(true);
    }

    JTextArea getTextArea(String text,Color color, Border border){
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

    JLabel getLabel(String text,Color color, Border border){
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

    JButton getButton(String text, Color color, Border border, int size){
        JButton newButton = getButton(color, border);
        newButton.setText(text);
        newButton.setFont(new Font("PlayFair",Font.BOLD,size));
        return newButton;
    }

    JButton getButton(Color color, Border border){
        JButton newButton = new JButton();
        newButton.setBackground(color);
        newButton.setBorder(border);
        newButton.setVisible(true);
        newButton.addActionListener(this);
        return newButton;
    }

    JPanel getPanel(int row, int col, int hGap, int vGap, Color color, Border border){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(row,col,hGap,vGap));
        newPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        newPanel.setBackground(color);
        newPanel.setBorder(border);
        newPanel.setVisible(true);
        return newPanel;
    }

    void initFrame(String title,int width, int height){
        setTitle(title);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocation(getXOrigin(width),getYOrigin(height));
        setVisible(true);
        setSize(width,height);
        setResizable(true);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    String getAllColors(){
        StringBuilder sb = new StringBuilder();
        sb.append(getColors(3));
        sb.append(getColors(2));
        sb.append(getColors(1));
        sb.append(getColors(4));
        sb.append(getColors(0));
        sb.append(getColors(5));
        return sb.toString();
    }

    String getColors(int index){
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < dimension*dimension; j++){
            Color curColor = cubeButton[index][j].getBackground();
            sb.append(getColor(curColor)+" ");
        }
        return sb.toString();
    }

    private String getColor(Color color){
        
        if(color.equals(Color.red)){
            return "RED";
        }
        else if(color.equals(Color.white)){
            return "WHITE";
        }
        else if(color.equals(Color.blue)){
            return "BLUE";
        }
        else if(color.equals(Color.green)){
            return "GREEN";
        }
        else if(color.equals(Color.orange)){
            return "ORANGE";
        }
        else if(color.equals(Color.yellow)){
            return "YELLOW";
        }
        else{
            return "GRAY";
        }
    }   

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

    
    private void disableColorInput(){
        for(int i = 0; i < FACE_COUNT; i++){
            for(int j = 0; j < dimension*dimension; j++){
                cubeButton[i][j].setEnabled(false);
            }
        }
    }

    private String[] getPartialArray(String arr[], int startIndex, int endIndex){
        String []partialArray = new String[endIndex-startIndex+1];
        int partialArrayIndex = 0;
        for(int i = startIndex; i <= endIndex; i++){
            partialArray[partialArrayIndex++] = arr[i].trim();
        }
        return partialArray;
    }

    private void setColors(String allColors){
        String allColor[] = allColors.split(" ");
        int index = 0;

        setColors(3,getPartialArray(allColor, index, index+8));index+=9;
        setColors(2,getPartialArray(allColor, index, index+8));index+=9;
        setColors(1,getPartialArray(allColor, index, index+8));index+=9;
        setColors(4,getPartialArray(allColor, index, index+8));index+=9;
        setColors(0,getPartialArray(allColor, index, index+8));index+=9;
        setColors(5,getPartialArray(allColor, index, index+8));index+=9;

    }

    private void setColors(int index, String colors[]){ 
        int colorsIndex = 0;
        for(int i = 0; i < dimension*dimension; i++){
            Color color = getColor(colors[colorsIndex++]);
            cubeButton[index][i].setBackground(color);
        }
    }


    //type == true returns xOrigin else returns yorigin
    private int getOrigin(boolean type,int length){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        
        if(type){
            int halfScrWidth = (int)(dimension.getWidth()/2);
            int xOrigin = halfScrWidth-(length/2);
            return xOrigin;
        }
        else{
            int halfScrHeight = (int)(dimension.getHeight()/2);
            int yOrigin = halfScrHeight-(length/2);
            return yOrigin;
        }
    }

    private int getXOrigin(int width){
        return getOrigin(true,width);
    }

    private int getYOrigin(int height){
        return getOrigin(false,height);
    }

    private class PopUpFrame extends JFrame implements ActionListener,KeyListener{
        final int DEFAULT_HEIGHT = 100;
        final int DEFAULT_WIDTH = 100;
        final String DEFAULT_TITLE = "";

        int width,height;
        String title;
        JButton newButton[];

        PopUpFrame(){
            this.width = DEFAULT_WIDTH;
            this.height = DEFAULT_HEIGHT;
            this.title = DEFAULT_TITLE;
            initFrame();
        }

        PopUpFrame(String title,int width, int height){
            this.width = width;
            this.height = height;
            this.title = title;
            initFrame();
        }

        void display(){
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

        JPanel getPanel(int row, int col, int hGap, int vGap, Color color, Border border){
            JPanel newPanel = new JPanel();
            newPanel.setLayout(new GridLayout(row,col,hGap,vGap));
            newPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            newPanel.setBackground(color);
            newPanel.setBorder(border);
            newPanel.setVisible(true);
            return newPanel;
        }

        JButton getButton(Color color, Border border){
            JButton newButton = new JButton();
            newButton.setBackground(color);
            newButton.setBorder(border);
            newButton.setVisible(true);
            return newButton;
        }

        void initFrame(){    
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

        void setColor(String color){
            MainFrame3X3.this.setEnabled(true);
            buttonPressed.setBackground(getColor(color));
            MainFrame3X3.this.buttonPressed.setBorder(BorderFactory.createLineBorder(Color.black));     
            dispose();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < FACE_COUNT; i++){
                if(e.getSource() == newButton[i]){
                    setColor(colors[i]);
                    break;
                }
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyChar() == 'r'){
                setColor("RED");
            }
            else if(e.getKeyChar() == 'b'){
                setColor("BLUE");
            }
            else if(e.getKeyChar() == 'w'){
                setColor("WHITE");
            }
            else if(e.getKeyChar() == 'g'){
                setColor("GREEN");
            }
            else if(e.getKeyChar() == 'y'){
                setColor("YELLOW");
            }
            else if(e.getKeyChar() == 'o'){
                setColor("ORANGE");
            }
            else{
                setColor("DEFAULT");
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyTyped(KeyEvent e) {}

    } 

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
                info.setText("START REACHED");
            }
        }

    }

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
            info.setText("END REACHED");
        }
    }

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
                info.setText("PRESS\n<,> BUTTOn\nTO NAVIGATE\nSOLUTION LIST");
                initialState = colorInput;
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
                        return;
                    }
                }
                info.setText("FAILED TO LOAD");
            }
            catch(Exception exception){
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