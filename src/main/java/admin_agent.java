import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

import javax.swing.*;
import java.util.Scanner;
import java.io.*;
//import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class admin_agent extends Agent {
    public static int admin_counter = 0;
    private boolean done = false;
    public int check = 0;
    public ACLMessage msg = new ACLMessage();
    protected void setup() {
        System.out.printf("Admin: I am online %s%n", getLocalName());
        addBehaviour(new SimpleBehaviour(this) {
            public void action(){
                //System.out.println("Check");
                switch(admin_counter) {
                    case 0:
                        System.out.printf("Admin lalala: My name is %s%n", getLocalName());
                        ACLMessage agent_request = blockingReceive();
                        if(agent_request.getContent().equals("Register"))
                        {
                            admin_counter = 2;
                        }
                        else if(agent_request.getContent().equals("Deregister"))
                        {
                            admin_counter = 3;
                        }
                        else
                        {
                            msg = agent_request;
                            check = Integer.parseInt(msg.getContent());
                            System.out.println(check);
                            admin_counter = 1;
                        }
                        break;

                    case 1:
                        //Query the Database to check the Student details for verification
                        int id_db = 0;
                        String name_db = "";
                        //Wait for Verification request from the Librarian Agent or the Stationer Agent or the Print Agent

                        if (check != 0) {
                            System.out.println("Admin: Request to verify the student ID: "+check);

                            ACLMessage student_verification_status = msg.createReply();
                            student_verification_status.setPerformative(ACLMessage.INFORM);
                            //student_verification_status.setContent("Admin: Student is Registered");
                            //send(student_verification_status);
                            try {
                                Connection con=DriverManager.getConnection(
                                        "jdbc:sqlserver://mydls.database.windows.net:1433;DatabaseName=myDLS","dls@mydls","SENG696Proj");
                                //System.out.println(con);
                                Statement stmt = con.createStatement();
                                ResultSet result = stmt.executeQuery("select * from student where student_id = '"+check+"' ");
                                System.out.println(result);

                                while (result.next())
                                {
                                    id_db = result.getInt("student_id");
                                    name_db = result.getString("student_name");
                                    //System.out.print(id_db+" "+name_db);
                                }

                                if(id_db!=0)
                                {
                                    //System.out.println("Found");
                                    student_verification_status.setContent("Registered");
                                    send(student_verification_status);
                                    //admin_counter = 4;
                                    //librarian_agent.librarian_counter = 3;
                                    //System.out.println("Admin: "+librarian_agent.librarian_counter);
                                    admin_counter = 4;
                                }
                                else
                                {
                                    //System.out.println("Not found");
                                    student_verification_status.setContent("Not Registered");
                                    send(student_verification_status);
                                    System.out.println("Admin: You must register, please enter your details when prompted!");
                                    admin_counter = 0;
                                }

                                //System.out.println(id_db+" "+name_db);

                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            //admin_counter = 4;
                        }
                        break;
                    case 2:
                        //Take and process a new registration request
                        while(adminAgentGUI.name==null)
                        {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        Scanner sc = new Scanner(System.in);
                        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
                        System.out.println("Admin: Enter first name: ");
                        String name = adminAgentGUI.name;
                       // String name2 = adminAgentGUI.x;
                        //System.out.println("Admin: Enter first name:"+name2);
                        System.out.println("Admin: Enter ID:");
                        int id = adminAgentGUI.ID;
                        System.out.println("Admin: Enter email:");
                        String email = adminAgentGUI.student_email;

                        try {
                            Connection con=DriverManager.getConnection(
                                    "jdbc:sqlserver://mydls.database.windows.net:1433;DatabaseName=myDLS","dls@mydls","SENG696Proj");
                            //System.out.println(con);
                            Statement stmt = con.createStatement();
                            stmt.executeUpdate("insert into student values ('"+id+"','"+name+"','"+email+"','"+100+"','"+true+"','"+false+"','"+0+"')");
                            //System.out.println(result);
                            //System.out.println("Student record created successfully!");
                            JOptionPane.showMessageDialog(null,"Student record created successfully","Registration",JOptionPane.PLAIN_MESSAGE);
                            //master_agent.student_counter = 0;
                            admin_counter = 4;

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        master_agent.flag=0;
                        break;

                        //check if already present in db, if not then push. If yes, show message.
                    case 3:
                        //Take and process a new de-registration request
                        int id1 = -1;
                        //br = new BufferedReader (new InputStreamReader(System.in));
                        while(adminAgentGUI.ID==0)
                        {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("Enter ID:");
                        id1 = adminAgentGUI.ID;


                        try {
                            Connection con = DriverManager.getConnection(
                                    "jdbc:sqlserver://mydls.database.windows.net:1433;DatabaseName=myDLS", "dls@mydls", "SENG696Proj");
                            //System.out.println(con);
                            Statement stmt = con.createStatement();
                            stmt.executeUpdate("delete from student where student_id = '" + id1 + "'");
                            //System.out.println(result);
                            //System.out.println("Record Deleted!");
                            JOptionPane.showMessageDialog(null,"Student record deleted!","De-registration",JOptionPane.PLAIN_MESSAGE);
                            admin_counter = 4;
                            master_agent.flag=0;
                            break;
                        }
                        catch (SQLException e) {
                        throw new RuntimeException(e);
                        }


                    case 4:
                        //Finish processing the student verification and/or registration requests
                        System.out.println("Admin: Finished Admin Roles");
                        //master_agent.flag=0;
                        admin_counter = 0;

                        //done = true;
                }
            }
            public boolean done() {
                if (done) {
                    System.out.println("My name is " +getLocalName());
                }
                return done;
            }
        });
    }
}