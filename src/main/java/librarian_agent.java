import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
public class librarian_agent extends Agent {
    public static int librarian_counter = 0;
    private boolean done = false;
    protected void setup() {
        mainAgentGUI home_page = new mainAgentGUI();
        System.out.printf("My name is %s%n", getLocalName());
        addBehaviour(new SimpleBehaviour(this) {
            public void action() {
                switch (librarian_counter) {
                    case 0:
                        //Wait for Book lend request from Student
                        ACLMessage student_status = blockingReceive();
                        if(student_status.getContent().equals("Lend"))
                        {
                            //Do lending case
                        }
                        else if(student_status.getContent().equals("Return"))
                        {
                            //Do returning case
                        }
                        break;
                    case 1:
                        //Send student verification request to the Admin agent
                        String student_input = "";
                        System.out.println("Librarian: Enter Student First Name: ");
                        Scanner scanner = new Scanner(System.in);
                        student_input = scanner.nextLine();

                        ACLMessage student_verification = new ACLMessage(ACLMessage.REQUEST);
                        student_verification.setContent(student_input);
                        student_verification.addReceiver(new AID("Admin", AID.ISLOCALNAME));
                        send(student_verification);
                        System.out.println("Librarian: Sent Student Verification Request to the ADMIN");
                        librarian_counter = 2;
                        break;

                    case 2:
                        //Wait for student verification status
                        System.out.println("Librarian: Receiving student verification status from the Admin....");
                        ACLMessage student_verification_status = blockingReceive();
                        //System.out.println(student_verification_status.getContent());
                        if (student_verification_status.getContent().equals("Registered"))
                        {
                            System.out.println("Librarian: Student is Registered");
                        }
                        else
                        {
                            System.out.println("Librarian: Student is Not Registered");
                        }
                        //System.out.println("Librarian: "+librarian_counter);
                        //librarian_counter = 7;
                        //librarian_counter = 0;
                        break;

                    case 3:
                        //Checks for the availability of the book requested
                        System.out.println("Case 3");
                    case 4:
                        //Process book lend request and update the status in Database
                    case 5:
                        //Wait for Book return request from Student
                    case 6:
                        //Process book return request and update the status in Database
                    case 7:
                        //Finish processing the book requests
                        System.out.println("Librarian: Finished Librarian Roles");
                        done = true;
                        break;
                    case 8:
                        //idle
                        System.out.println("Librarian: Idling");
                        break;
                }
            }

            public boolean done() {
                if (done) {
                    System.out.printf("My name is %s%n", getLocalName());
                }
                return done;
            }
        });
    }
}