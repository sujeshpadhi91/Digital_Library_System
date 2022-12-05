import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Scanner;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

import java.util.Scanner;

public class print_agent extends Agent {

    //counter for switch case that will be used to switch between cases
    //initialized as 0
    // public because it will be used by other agents
    public int print_counter = 0;
    public String inputpages;
    public String scaninput;
    public int number_of_pages;
    public int new_limit;
    public int current_limit;
    private String studentid;


    //to terminate the agent action
    private boolean done = false;

    // private static final Logger log = Logger.getLogger(print_agent.class.getName());

    //private String log;
    private static final Logger log;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(print_agent.class.getName());
    }

    protected void setup() {

        System.out.printf("My name is %s%n", getLocalName());

        addBehaviour(new SimpleBehaviour(this) {

            public void action() {
                switch (print_counter) {

                    case 0:

                        //listening to admin agent for request to print X number of pages

                        ACLMessage request = blockingReceive();
                        if (request != null) {
                            if(request.getContent().equals("Verified")) {
                                //condition to distinguish between reply from Admin and Master
                                print_counter = 2; break;
                            }
                            print_counter = 1;

                        }
                        break;

                    case 1:

                        // Take input from Student for his Student ID
                        try{

                            System.out.print("Welcome to Printer Station \n");
                            System.out.print("Please Enter Your Student ID: ");
                            Scanner scanner = new Scanner(System.in);
                            studentid = scanner.next();

                            // Send this Student id to Admin Agent for registration verification

                        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                        msg.setContent(studentid);
                        msg.addReceiver(new AID("Admin", AID.ISLOCALNAME));
                        send(msg);

                        } catch (Exception e) {
                            System.out.println(e);
                        }

                        print_counter =0;
                        break;

                    case 2:

                        // connect to db and query student details to work on them after

                        try{


                            Connection connection = DriverManager.getConnection("jdbc:sqlserver://mydls.database.windows.net:1433;DatabaseName=myDLS","dls@mydls","SENG696Proj");
                            PreparedStatement readStatement = connection.prepareStatement("SELECT print_limit FROM student WHERE student_id = ?");
                            readStatement.setString(1,studentid);
                            ResultSet resultSet = readStatement.executeQuery();

;
                            if (!resultSet.next()) {
                                log.info("There is no data in the database!");
                            }


                            current_limit = resultSet.getInt("print_limit");

                            if (current_limit== 0) {
                                System.out.print("You have reached you print limit \n");
                                print_counter = 3;break;}


                        } catch (Exception e){
                            System.out.println(e);
                        }

                        // ask the number of pages to be printed and convert string to integer

                        System.out.print("You are a registered student. \n");
                        System.out.println("Your current limit to print pages is: " + current_limit);
                        System.out.print("Please enter the number of pages to print: ");
                        Scanner scanner = new Scanner(System.in);
                        inputpages = scanner.next();
                        number_of_pages = Integer.parseInt(inputpages);

                        // check if number of pages requested to print are within current print limit

                        if (number_of_pages > current_limit) {
                            System.out.printf("Your current limit is to print %d pages \n", current_limit);
                            System.out.print("Please enter a number below the limit \n");
                            print_counter = 2; break;

                        }

                        else {
                            System.out.print("Sending your request to printer \n");
                            new_limit = current_limit - number_of_pages;

                            // update the new limit in the database

                            try {
                                Connection connection = DriverManager.getConnection("jdbc:sqlserver://mydls.database.windows.net:1433;DatabaseName=myDLS", "dls@mydls", "SENG696Proj");
                                PreparedStatement updateStatement = connection.prepareStatement("UPDATE student SET print_limit = ?");
                                updateStatement.setInt(1, new_limit);
                                updateStatement.executeUpdate();
                                System.out.print("Your pages have been printed. Thank you \n");
                                print_counter = 3; break;

                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                        break;


                    case 3:

                        System.out.print("Would you like to print more pages? [y/n]: ");
                        Scanner scanner1 = new Scanner(System.in);
                        scaninput = scanner1.next();
                        if(scaninput.equals("y")) {
                            print_counter = 1; break;
                        } else {
                            print_counter = 4; break;
                        }


                    case 4:
                        //Finish processing the print request

                        System.out.println("Thank you for using the print agent");
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