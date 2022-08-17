import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Test {
    public static void main(String[] args){
        MainFrame frame = new MainFrame();
        }
}

class MainFrame extends JFrame implements ActionListener,KeyListener{
    JLabel newLabel;
    JTextField newField;
    JButton newButton;
    String value;

    public MainFrame(){
        initializeFrame();
    }

    public void initializeFrame(){
        // By default we have cardlayout where components are added one above other
        // But in FlowLayout components (say label) won't overlap 
        /*addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
              System.out.println("keyTyped: '" + e.getKeyChar() + " "+ e.getID());
            }
          });*/
        addKeyListener(this);
        setLayout(new FlowLayout());
        value = "";
        
        newLabel = new JLabel("NEW LABEL1");
        newLabel.setText("HIEEEE");
        add(newLabel);//label added to Jframe

        newField = new JTextField(10);
        newButton = new JButton("buttonName");

        //add(newField);//field added to Jframe
        add(newButton);//button added to Jframe

        //As we have to pass ActionListner as parameter which is an interface
        //with one method public void actionPerformed(ActionEvent e) hence lambda 
        //can be used.
        //action listener basically does what needs to be performed when
        //buttonName is pressed.
       // newButton.addActionListener(new OnButtonClick(newField, newLabel));
       newButton.addActionListener(this);
        System.out.println("HEELLO");
        setVisible(true);//to show the Jframe
        setFocusable(true);
        setSize(300,300);//Size of Jframe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//program stops if close is pressed
    
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        newLabel.setText(newField.getText());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println(e.getKeyCode()+" "+e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}


