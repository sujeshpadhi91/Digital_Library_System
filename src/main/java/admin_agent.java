import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Scanner;
import java.io.*;
//import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class admin_agent extends Agent {
    public static int admin_counter = 0;
    private boolean done = false;
    protected void setup() {
        System.out.printf("My name is %s%n", getLocalName());
        addBehaviour(new SimpleBehaviour(this) {
            public void action(){
                //System.out.println("Check");
                switch(admin_counter) {
                    case 0:
                        ACLMessage agent_request = blockingReceive();
                        if(agent_request.getContent().equals("Register"))
                        {
                            admin_counter = 2;
                        }
                        else if(agent_request.getContent().equals("Deregister"))
                        {
                            admin_counter = 3;
                        }
                        else if(agent_request.getContent().equals("Verify"))
                        {
                            admin_counter = 1;
                        }
                        break;

                    case 1:
                        //Query the Database to check the Student details for verification
                        int id_db = 0;
                        String name_db = "";
                        //Wait for Verification request from the Librarian Agent or the Stationer Agent or the Print Agent
                        ACLMessage student_verification = blockingReceive();
                        if (student_verification != null) {
                            System.out.println("Admin: Request to verify the student: "+student_verification.getContent());
                            String check = student_verification.getContent();
                            ACLMessage student_verification_status = student_verification.createReply();
                            student_verification_status.setPerformative(ACLMessage.INFORM);
                            //student_verification_status.setContent("Admin: Student is Registered");
                            //send(student_verification_status);
                            try {
                                Connection con=DriverManager.getConnection(
                                        "jdbc:sqlserver://mydls.database.windows.net:1433;DatabaseName=myDLS","dls@mydls","SENG696Proj");
                                //System.out.println(con);
                                Statement stmt = con.createStatement();
                                ResultSet result = stmt.executeQuery("select * from student where student_name = '"+check+"' ");
                                //System.out.println(result);

                                while (result.next())
                                {
                                    id_db = result.getInt("student_id");
                                    name_db = result.getString("student_name");
                                    //System.out.print(id_db+" "+name_db);
                                }

                                if(id_db!=0)
                                {
                                    //System.out.println("Found");
                                    student_verification_status.setContent("Already registered");
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
                                    admin_counter = 2;
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
                        Scanner sc = new Scanner(System.in);
                        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
                        System.out.println("Admin: Enter first name: ");
                        String name = sc.nextLine();
                       // String name2 = adminAgentGUI.x;
                        //System.out.println("Admin: Enter first name:"+name2);
                        System.out.println("Admin: Enter ID:");
                        int id = sc.nextInt();
                        System.out.println("Admin: Enter email:");
                        String email = null;
                        try {
                            email = br.readLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        try {
                            Connection con=DriverManager.getConnection(
                                    "jdbc:sqlserver://mydls.database.windows.net:1433;DatabaseName=myDLS","dls@mydls","SENG696Proj");
                            //System.out.println(con);
                            Statement stmt = con.createStatement();
                            stmt.executeUpdate("insert into student values ('"+id+"','"+name+"','"+email+"','"+100+"','"+true+"','"+false+"','"+0+"')");
                            //System.out.println(result);
                            System.out.println("Student record created successfully!");
                            admin_counter = 4;

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;

                        //check if already present in db, if not then push. If yes, show message.
                    case 3:
                        //Take and process a new de-registration request
                        int id1 = -1;
                        br = new BufferedReader (new InputStreamReader(System.in));
                        System.out.println("Enter ID:");
                        try {
                            id1 = Integer.parseInt(br.readLine());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        try {
                            Connection con = DriverManager.getConnection(
                                    "jdbc:sqlserver://mydls.database.windows.net:1433;DatabaseName=myDLS", "dls@mydls", "SENG696Proj");
                            //System.out.println(con);
                            Statement stmt = con.createStatement();
                            stmt.executeUpdate("delete from student where student_id = '" + id1 + "'");
                            //System.out.println(result);
                            System.out.println("Record Deleted!");
                            admin_counter = 4;
                            break;
                        }
                        catch (SQLException e) {
                        throw new RuntimeException(e);
                        }

                    case 4:
                        //Finish processing the student verification and/or registration requests
                        System.out.println("Admin: Finished Admin Roles");
                        done = true;
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