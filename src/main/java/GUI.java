import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {


    private JPanel panel1;
    JButton button2;
    JButton button1;
    JTextField textfield;
    GUI()
    {
        //text field
         textfield = new JTextField();
         textfield.setBounds(250,200,100,50);
        //textfield.setPreferredSize(new Dimension(250,40));
        textfield.setFont(new Font("Consloas",Font.PLAIN,20));
        ///////////////////////////////////////////
        button2 = new JButton();
        button2.setBounds(50,200,100,50);
        button2.addActionListener(this);
        button2.setText("Register");
        button2.setFocusable(false);
        button1 = new JButton();
        button1.setBounds(50,150,100,50);
        button1.addActionListener(this);
        button1.setText("Register");
        button1.setFocusable(false);
        /////////////////////////////////////
        //JFrame frame = new JFrame();//create frame
        //label creation
        JLabel label = new JLabel();
        label.setText("Digital Library System");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setFont(new Font("Helvetica",Font.BOLD,25));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(100,50,300,150);
        label.setVisible(true);
        //label.setLayout(null);
        ///////////////////////////////////////////
        //frame creation
        this.setTitle("Digital Library System");
        this.setSize(600,600); // set frame dimensions
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //frame.getContentPane().setBackground(Color.BLUE);
        this.setLayout(null);
        //this.setLayout(new FlowLayout());
        this.add(label);
        this.add(button2);
        this.add(button1);
        this.add(textfield);
        //this.pack();
        this.setVisible(true);//make frame visible

    }
    public static void doSomething()
    {
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== button2)
        {
            textfield.getText();
            //this.dispose();
            System.out.println("hiiiiiiiiiiiiiiii");
            //button2.setEnabled(false); //will disable the button after one click
            //textfield.setEditable(false);
        }
        if(e.getSource()== button1)
        {
            textfield.getText();
            //this.dispose();
            System.out.println("vamosssssssssss");
            //button2.setEnabled(false); //will disable the button after one click
            //textfield.setEditable(false);
        }
    }
}
