import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class printAgentGUI extends JFrame implements ActionListener {
    JButton home_button;
    JButton print_button;
    public static int no_pages=0;
    JTextField pages;
    printAgentGUI()
    {
        pages = new JTextField();
        pages.setBounds(360,205,100,30);
        //textfield.setPreferredSize(new Dimension(250,40));
        pages.setFont(new Font("Consloas",Font.PLAIN,15));
        ////////////////////
        JLabel label1 = new JLabel();
        label1.setText("Enter number of pages to print:");
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.TOP);
        label1.setFont(new Font("Helvetica",Font.BOLD,15));
        label1.setVerticalAlignment(JLabel.TOP);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setBounds(50,205,300,30);
        label1.setVisible(true);
        //////////////////////////////
        home_button = new JButton();
        home_button.setBounds(400,450,150,50);
        home_button.addActionListener(this);
        home_button.setText("Home button");
        home_button.setFocusable(false);
        ////////////////////////////
        print_button = new JButton();
        print_button.setBounds(200,300,150,30);
        print_button.addActionListener(this);
        print_button.setText("Print");
        print_button.setFocusable(false);
        //print_button.setEnabled(false);
        ////////////////////////////
        JLabel label = new JLabel();
        label.setText("Printer");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setFont(new Font("Helvetica",Font.BOLD,25));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(150,50,300,150);
        label.setVisible(true);
        //label.setLayout(null);
        ///////////////////////////////////////////
        //frame creationo=
        this.setTitle("Digital Library System");
        this.setSize(600,600); // set frame dimensions
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //frame.getContentPane().setBackground(Color.BLUE);
        this.setLayout(null);
        //this.setLayout(new FlowLayout());
        this.add(label);
        this.add(label1);
        this.add(home_button);
        this.add(print_button);
        this.add(pages);
        //this.add(textfield);
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

            //print_button.setEnabled(true);
            if (e.getSource() == print_button) {
                no_pages = Integer.parseInt(pages.getText());
                JOptionPane.showMessageDialog(null,"Number of pages: "+no_pages,"title",JOptionPane.PLAIN_MESSAGE);
            }


    }
}
