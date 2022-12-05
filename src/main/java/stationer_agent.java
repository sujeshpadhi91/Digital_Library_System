import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Scanner;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

import java.util.Scanner;

public class stationer_agent extends Agent {

    //counter for switch case that will be used to switch between cases
    //initialized as 0
    // public because it will be used by other agents
    public int stationer_counter = 0;

    public String scaninput;
    public int number_of_items;

    private int studentid;
    private String itemid;

    public int remaining_items;
    private int bought_items = 0;
    public String item_name;

    public static String[] items = new String[10];


    //to terminate the agent action
    private boolean done = false;


    //private String log;
    private static final Logger log;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(stationer_agent.class.getName());
    }

    protected void setup() {

        System.out.printf("My name is %s%n", getLocalName());

        addBehaviour(new SimpleBehaviour(this) {

            public void action() {
                switch (stationer_counter) {

                    case 0:

                        //listening to admin agent for request

                        ACLMessage request = blockingReceive();
                        if (request != null) {
                            if(request.getContent().equals("Registered")) {
                                //condition to distinguish between reply from Admin and Master
                                stationer_counter = 2; break;
                            }
                            stationer_counter = 1;

                        }
                        break;

                    case 1:

                        // Take input from Student for his Student ID
                        try{

                            System.out.print("Welcome to Stationer Agent \n");
                            System.out.print("Please Enter Your Student ID: ");

                            while(adminAgentGUI.ID==0)
                            {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            Scanner scanner = new Scanner(System.in);
                            studentid = adminAgentGUI.ID;

                            // Send this Student id to Admin Agent for registration verification

                        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                        msg.setContent(studentid+"");
                        msg.addReceiver(new AID("Admin", AID.ISLOCALNAME));
                        send(msg);

                        } catch (Exception e) {
                            System.out.println(e);
                        }

                        stationer_counter = 0;

                        break;

                    case 2:

                        // connect to db and query student details to work on them after

                        try{

                            // connect to database

                            Connection connection = DriverManager.getConnection("jdbc:sqlserver://mydls.database.windows.net:1433;DatabaseName=myDLS","dls@mydls","SENG696Proj");

                            Statement create_statement = connection.createStatement();
                            ResultSet item_list = create_statement.executeQuery("select * from items");
                            int i = 0;
                            while(item_list.next())
                            {
                                items[i] = item_list.getString("item_name");
                                System.out.println(items[i]);
                                i++;
                            }

                            // bring item names
                            System.out.println("Enter item name:");
                            while(stationerAgentGUI.msg_item==null)
                            {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Scanner scanner = new Scanner(System.in);
                            item_name = stationerAgentGUI.msg_item;

                            System.out.println("Enter quantity: ");
                            bought_items = scanner.nextInt();


                            PreparedStatement readStmtName = connection.prepareStatement("SELECT quantity FROM items where item_name = ?");
                            readStmtName.setString(1,item_name);
                            ResultSet resultSetName = readStmtName.executeQuery();



                            // count the items

                            if (!resultSetName.next()) {
                                log.info("There is no data in the database!");
                            }
                            number_of_items = resultSetName.getInt("quantity");
                            remaining_items = number_of_items - bought_items;

                        } catch (Exception e){
                            System.out.println(e);
                        }



                        System.out.print("You are a registered student. \n");
                        System.out.printf("You have purchased %s %s: \n", bought_items, item_name);


                       // update the remaining items in DB

                            try {
                                Connection connection = DriverManager.getConnection("jdbc:sqlserver://mydls.database.windows.net:1433;DatabaseName=myDLS", "dls@mydls", "SENG696Proj");
                                PreparedStatement updateStatement = connection.prepareStatement("UPDATE items SET quantity = ? WHERE item_name = ?");
                                updateStatement.setInt(1, remaining_items);
                                updateStatement.setString(2,item_name);
                                updateStatement.executeUpdate();
                                System.out.print("Thank you for your purchase \n");
                                stationer_counter = 3; break;

                            } catch (Exception e) {
                                System.out.println(e);
                            }


                    case 3:
                        //Finish processing the print request

                        System.out.println("Thank you for using the Stationer Agent");
                        done = true;
                        break;
                }
            }

            public boolean done() {
                if (done) {
                    System.out.printf("My name is %s \n", getLocalName());
                }
                return done;
            }


        });
    }
}