import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class librarianAgentGUI extends JFrame implements ActionListener {

    JTextField textfield;
    JButton home_button;
    JButton borrow_button;
    JButton return_button;
    JComboBox books_list;
    JComboBox return_list;
    public static String msg=null;
    public static int msg2=0;
    public static int borrow_val=0;
    public static int return_val=0;
    public static String[] return_book= new String[1];
    JTextField Return_ID;
    librarianAgentGUI()
    {
        /////////////////////////////////
        /*Return_ID = new JTextField();
        Return_ID.setBounds(400,200,80,25);
        //textfield.setPreferredSize(new Dimension(250,40));
        Return_ID.setFont(new Font("Consloas",Font.PLAIN,15));*/
        //String s ="Select";
        String[] book = librarian_agent.books;
        int[] books_ID = librarian_agent.books_ID;
        books_list = new JComboBox(book);
        books_list.setSelectedIndex(0);
        //books_list.setSelectedItem(s);
        books_list.addActionListener(this);
        books_list.setBounds(100,200,100,25);
         return_book[0]= librarian_agent.borrowed_book_name;
        return_list = new JComboBox(return_book);
        return_list.setSelectedIndex(0);
        //books_list.setSelectedItem(s);
        return_list.addActionListener(this);
        return_list.setBounds(400,200,80,25);
        ////////////////////////
        borrow_button = new JButton();
        borrow_button.setBounds(0,200,80,25);
        borrow_button.addActionListener(this);
        borrow_button.setText("Borrow");
        borrow_button.setFocusable(false);
        borrow_button.setEnabled(false);
        ////////////////////////
        return_button = new JButton();
        return_button.setBounds(300,200,80,25);
        return_button.addActionListener(this);
        return_button.setText("Return");
        return_button.setFocusable(false);
        //return_button.setEnabled(false);
        //////////////////////////////////
        home_button = new JButton();
        home_button.setBounds(400,450,150,50);
        home_button.addActionListener(this);
        home_button.setText("Home button");
        home_button.setFocusable(false);
        /////////////////////
        JLabel label = new JLabel();
        label.setText("Library");
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
        this.add(borrow_button);
        this.add(books_list);
        this.add(return_list);
        this.add(return_button);
        this.add(Return_ID);
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
        if(e.getSource()== books_list)
        {
            JComboBox cb = (JComboBox) e.getSource();
             msg = (String)cb.getSelectedItem();
             //System.out.println("Book "+ msg);
            borrow_button.setEnabled(true);
            //button2.setEnabled(false); //will disable the button after one click
            //textfield.setEditable(false);
        }
        if(e.getSource()== borrow_button)
        {
           // JComboBox cb = (JComboBox) e.getSource();
            borrow_val=1;
           // msg = (String)cb.getSelectedItem();
            //JComboBox cb = (JComboBox) e.getSource();
            //String msg = (String)cb.getSelectedItem();
            //borrow_button.setEnabled(true);
            //button2.setEnabled(false); //will disable the button after one click
            //textfield.setEditable(false);
            borrow_button.setEnabled(false);
            books_list.setEnabled(false);

        }

        if(e.getSource()== return_list)
        {
            JComboBox cb = (JComboBox) e.getSource();
            String x=(String)cb.getSelectedItem();
            //System.out.println("Book "+ msg);
            return_button.setEnabled(true);
            //button2.setEnabled(false); //will disable the button after one click
            //textfield.setEditable(false);
        }
        if(e.getSource()== return_button)
        {
            borrow_val=2;
            borrow_button.setEnabled(false);
            books_list.setEnabled(false);
            //JOptionPane.showMessageDialog(null,"up dog","what's",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
