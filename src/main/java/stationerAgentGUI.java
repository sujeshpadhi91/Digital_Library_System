import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class stationerAgentGUI extends JFrame implements ActionListener {

    JButton home_button;
    JButton buy_button;
    public static String msg_item =null;
    JComboBox stationery_list;
stationerAgentGUI()
{
    //String s ="Select";
    String[] item =stationer_agent.items ;
    stationery_list = new JComboBox(item);
    stationery_list.setSelectedIndex(0);
    //books_list.setSelectedItem(s);
    stationery_list.addActionListener(this);
    stationery_list.setBounds(300,200,150,25);
    //////////////////////////////////////////////////

    ///////////////////////////////////////////////////
    home_button = new JButton();
    home_button.setBounds(400,450,150,50);
    home_button.addActionListener(this);
    home_button.setText("Home button");
    home_button.setFocusable(false);
    ///////////////////////////////////////
    buy_button = new JButton();
    buy_button.setBounds(100,200,100,25);
    buy_button.addActionListener(this);
    buy_button.setText("Buy");
    buy_button.setFocusable(false);
    buy_button.setEnabled(false);
    ////////////////////////////
    JLabel label = new JLabel();
    label.setText("Stationery");
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setVerticalTextPosition(JLabel.TOP);
    label.setFont(new Font("Helvetica",Font.BOLD,25));
    label.setVerticalAlignment(JLabel.TOP);
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setBounds(150,50,300,150);
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
    this.add(home_button);
    this.add(buy_button);
    this.add(stationery_list);
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
            //button2.setEnabled(false); //will disable the button after one click
            //textfield.setEditable(false);
        }
        if(e.getSource()== stationery_list)
        {
            JComboBox cb = (JComboBox) e.getSource();
             msg_item = (String)cb.getSelectedItem();
            buy_button.setEnabled(true);
            //button2.setEnabled(false); //will disable the button after one click
            //textfield.setEditable(false);
            //JOptionPane.showMessageDialog(null,"successful purchase","Status",JOptionPane.INFORMATION_MESSAGE);

        }
        if(e.getSource()== buy_button)
        {
            buy_button.setEnabled(false);
            //button2.setEnabled(false); //will disable the button after one click
            //textfield.setEditable(false);
            JOptionPane.showMessageDialog(null,"successful purchase","Status",JOptionPane.INFORMATION_MESSAGE);

        }
    }
}
