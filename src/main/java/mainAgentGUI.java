import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainAgentGUI extends JFrame implements ActionListener {

    JButton stationery_button;
    JButton library_button;
    JButton Register_button;
    JButton deregister_button;
    JButton printer_button;
   public static int master_choice=0;

    mainAgentGUI()
    {
        ///////////////////////////////////////
        library_button = new JButton();
        library_button.setBounds(50,300,100,100);
        library_button.addActionListener(this);
        library_button.setText("Library");
//        library_button.setBackground(Color.CYAN);
        library_button.setFocusable(false);
        ///////////////////////////////////////////
        stationery_button = new JButton();
        stationery_button.setBounds(225,300,100,100);
        stationery_button.addActionListener(this);
        stationery_button.setText("Stationary");
//        stationery_button.setBackground(Color.CYAN);
        stationery_button.setFocusable(false);
        ///////////////////////////////////////////////////
        printer_button = new JButton();
        printer_button.setBounds(400,300,100,100);
        printer_button.addActionListener(this);
        printer_button.setText("Print");
//        printer_button.setBackground(Color.CYAN);
        printer_button.setFocusable(false);
        /////////////////////////////////////
        Register_button = new JButton();
        Register_button.setBounds(125,150,100,50);
        Register_button.addActionListener(this);
        Register_button.setText("Register");
//        Register_button.setBackground(Color.orange);
        Register_button.setFocusable(false);
        ///////////////////////////////////////
        deregister_button = new JButton();
        deregister_button.setBounds(325,150,100,50);
        deregister_button.addActionListener(this);
        deregister_button.setText("Deregister");
//        deregister_button.setBackground(Color.orange);
        deregister_button.setFocusable(false);
        //////////////////////////////////////////////////////////
        //JFrame frame = new JFrame();//create frame
        //label creation
        JLabel label = new JLabel();
        label.setText("Digital Library System");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        this.getContentPane().setBackground(Color.lightGray);
        label.setFont(new Font("Helvetica",Font.BOLD,30));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(75,50,400,200);
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
        this.add(Register_button);
        this.add(deregister_button);
        this.add(library_button);
        this.add(printer_button);
        this.add(stationery_button);
        //this.add(textfield);
        //this.pack();
        this.setVisible(true);//make frame visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== stationery_button)
        {
            master_choice=4;
            adminAgentGUI admin_window = new adminAgentGUI();
            //stationerAgentGUI stationry_window = new stationerAgentGUI();
            //textfield.getText();
            this.dispose();
            //button2.setEnabled(false); //will disable the button after one click
            //textfield.setEditable(false);
        }
        if(e.getSource()== library_button)
        {
            master_choice=3;
            adminAgentGUI admin_window = new adminAgentGUI();
            //textfield.getText();
            //librarianAgentGUI library_window = new librarianAgentGUI();
            this.dispose();
           // System.out.println("vamosssssssssss");
            //button2.setEnabled(false); //will disable the button after one click
            //textfield.setEditable(false);
        }
        if(e.getSource()== printer_button)
        {
            master_choice=5;
            adminAgentGUI admin_window = new adminAgentGUI();
            //textfield.getText();
            //printAgentGUI print_window = new printAgentGUI();
            this.dispose();
            //System.out.println("nooooooooo");
            //button2.setEnabled(false); //will disable the button after one click
            //textfield.setEditable(false);
        }
        if(e.getSource()== Register_button)
        {
            master_choice=1;
            //textfield.getText();
            adminAgentGUI admin_window = new adminAgentGUI();
            this.dispose();
            //System.out.println("yessssssssss");
            //button2.setEnabled(false); //will disable the button after one click
            //textfield.setEditable(false);
        }
        if(e.getSource()== deregister_button)
        {
            master_choice=2;
            //textfield.getText();
            adminAgentGUI admin_window = new adminAgentGUI();
            this.dispose();
            //button2.setEnabled(false); //will disable the button after one click
            //textfield.setEditable(false);
        }

    }
}
