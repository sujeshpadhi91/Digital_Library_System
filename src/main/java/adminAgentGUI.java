import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminAgentGUI extends JFrame implements ActionListener {

    public static String name=null;
    public static int ID=0;
    public static String student_email=null;
    JButton home_button;
    JButton next_button;
    JButton submit_ID;
    JTextField student_name;
    JTextField email;
    JTextField student_ID;
    adminAgentGUI()
    {
        //text field
        student_name = new JTextField();
        student_name.setBounds(100,200,100,30);
        //textfield.setPreferredSize(new Dimension(250,40));
        student_name.setFont(new Font("Consloas",Font.PLAIN,15));
        //student_name.setText("username");
        //textfield.addActionListener(this);
/////////////////////////////////////////////////////
        email = new JTextField();
        email.setBounds(100,300,100,30);
        //textfield.setPreferredSize(new Dimension(250,40));
        email.setFont(new Font("Consloas",Font.PLAIN,15));
        ///////////////////////////////////////////////
        //text field
        student_ID = new JTextField();
        student_ID.setBounds(100,250,100,30);
        //textfield.setPreferredSize(new Dimension(250,40));
        student_ID.setFont(new Font("Consloas",Font.PLAIN,15));
        //textfield.addActionListener(this);
        //////////////////////////////////////////
        home_button = new JButton();
        home_button.setBounds(400,450,150,50);
        home_button.addActionListener(this);
        home_button.setText("Home button");
        home_button.setFocusable(false);
        ///////////////////////////////////////////
        next_button = new JButton();
        next_button.setBounds(200,450,150,50);
        next_button.addActionListener(this);
        next_button.setText("Next");
        next_button.setFocusable(false);
        next_button.setEnabled(false);
        ///////////////////////////////////
        submit_ID = new JButton();
        submit_ID.setBounds(250,230,100,30);
        submit_ID.addActionListener(this);
        submit_ID.setText("Submit");
        submit_ID.setFocusable(false);
        //submit_ID.setEnabled(false);
        ////////////////////////////
        JLabel label = new JLabel();
        label.setText("Please Enter your Username and ID");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setFont(new Font("Helvetica",Font.BOLD,20));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(60,50,400,150);
        label.setVisible(true);

        ///////////////////////////////////////////////////////
        JLabel label1 = new JLabel();
        label1.setText("Username:");
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.TOP);
        label1.setFont(new Font("Helvetica",Font.BOLD,15));
        label1.setVerticalAlignment(JLabel.TOP);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setBounds(0,205,100,30);
        label1.setVisible(true);
        ////////////////////////////////////////
        JLabel label3 = new JLabel();
        label3.setText(" Email:");
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setVerticalTextPosition(JLabel.TOP);
        label3.setFont(new Font("Helvetica",Font.BOLD,15));
        label3.setVerticalAlignment(JLabel.TOP);
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setBounds(0,310,100,30);
        label3.setVisible(true);
        //label.setLayout(null);
        ///////////////////////////////////////////
        JLabel label2 = new JLabel();
        label2.setText("Student ID:");
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setVerticalTextPosition(JLabel.TOP);
        label2.setFont(new Font("Helvetica",Font.BOLD,15));
        label2.setVerticalAlignment(JLabel.TOP);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setBounds(0,255,100,30);
        label2.setVisible(true);
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
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(home_button);
        this.add(next_button);
        this.add(submit_ID);
        this.add(student_name);
        this.add(student_ID);
        this.add(email);
        //this.pack();
        this.setVisible(true);//make frame visible
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== home_button)
        {
            mainAgentGUI home_page = new mainAgentGUI();
            //textfield.getText();
            this.dispose();
            //System.out.println("hiiiiiiiiiiiiiiii");
            //button2.setEnabled(false); //will disable the button after one click
            //textfield.setEditable(false);
        }


            if (e.getSource() == submit_ID) {
                //mainAgentGUI home_page = new mainAgentGUI();
                name = student_name.getText();
                ID=Integer.parseInt(student_ID.getText());
                student_email=email.getText();
                student_name.setEditable(false);
                student_ID.setEditable(false);
                submit_ID.setEnabled(false);
                next_button.setEnabled(true);
                //JOptionPane.showMessageDialog(null,"Student record created successfully","Registration",JOptionPane.PLAIN_MESSAGE);
            }

        if (e.getSource() == next_button) {
            //mainAgentGUI home_page = new mainAgentGUI();
            if(mainAgentGUI.master_choice==1)
            {
                mainAgentGUI home_page = new mainAgentGUI();
            }
            else if (mainAgentGUI.master_choice==2)
            {
                mainAgentGUI home_page = new mainAgentGUI();
            }
            else if (mainAgentGUI.master_choice==3)
            {
                librarianAgentGUI library_window = new librarianAgentGUI();
                //next_button.setEnabled(true);
            }
            else if (mainAgentGUI.master_choice==4)
            {
                stationerAgentGUI stationry_window = new stationerAgentGUI();
               // next_button.setEnabled(true);
            }
            else if (mainAgentGUI.master_choice==5)
            {
                printAgentGUI print_window = new printAgentGUI();
               // next_button.setEnabled(true);
            }
            else
            {
                //do nothing
            }
            this.dispose();
            //JOptionPane.showMessageDialog(null,"tessssst","title",JOptionPane.PLAIN_MESSAGE);
        }

    }
}
