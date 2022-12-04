import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

import java.sql.*;
import java.util.Scanner;

public class librarian_agent extends Agent {
    public static int librarian_counter = 0;
    private boolean done = false;
    protected void setup() {
        System.out.printf("Librarian: I am online %s%n", getLocalName());
        addBehaviour(new SimpleBehaviour(this) {
            public void action() {
                Scanner sc = new Scanner(System.in);

                switch (librarian_counter) {
                    case 0:
                        System.out.printf("Librarian: My name is %s%n", getLocalName());
                        //Wait for Book operation request from Student
                        ACLMessage student_book_operations_request = blockingReceive();
//                        if(student_status.getContent().equals("Lend"))
//                        {
//                            //Do lending case
//                        }
//                        else if(student_status.getContent().equals("Return"))
//                        {
//                            //Do returning case
//                        }
                        if (student_book_operations_request.getContent().equals("Book_Operations")) {
                            librarian_counter = 1;
                        }
                        break;

                    case 1:
                        //Send student verification request to the Admin agent
//                        String student_input = "";
                        System.out.println("Librarian: Enter Student First Name: ");
                        Scanner scanner_student_name = new Scanner(System.in);
                        master_agent.student_name = scanner_student_name.nextLine();
                        System.out.println("Librarian: Enter Student ID: ");
                        Scanner scanner_student_id = new Scanner(System.in);
                        master_agent.student_id = scanner_student_id.nextLine();

                        ACLMessage student_verification = new ACLMessage(ACLMessage.REQUEST);
                        student_verification.setContent(master_agent.student_id);
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
                            librarian_counter = 3;
                        }
                        else
                        {
                            System.out.println("Librarian: Student with ID: "+master_agent.student_id+"is not registered.\nPlease Register from the Homepage to proceed.");
                        }
                        //System.out.println("Librarian: "+librarian_counter);
                        //librarian_counter = 7;
                        //librarian_counter = 0;
                        break;

                    case 3:
                        //Choice of Book Operations
                        System.out.println("Library - \nPress 1: Borrow\nPress 2: Return");
                        master_agent.choice_of_book_operations = sc.nextInt();
                        if(master_agent.choice_of_book_operations==1)
                        {
                            //Query the DataBase to check if there is an already borrowed book by the Student
                            try {
                                Connection connection_book_status_for_student = DriverManager.getConnection("jdbc:sqlserver://mydls.database.windows.net:1433;DatabaseName=myDLS","dls@mydls","SENG696Proj");
                                Statement create_statement = connection_book_status_for_student.createStatement();
                                ResultSet book_details_for_student = create_statement.executeQuery("select book_id,book_borrowed from student where student_id = '"+master_agent.student_id+"'");

                                //If a book has been already borrowed then Return is mandatory first
                                if (book_details_for_student.equals(true)) {
                                    ResultSet borrowed_book_name = create_statement.executeQuery("select book_name from book where book_id = '"+book_details_for_student.getInt("book_id")+"'");
                                    System.out.println("You have already borrowed a Book: "+borrowed_book_name+".\nPlease return the book to proceed.");
                                } else if (book_details_for_student.equals(false)) {
                                    librarian_counter = 4;
                                }
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                        }

                        }
                        else if(master_agent.choice_of_book_operations==2)
                        {
                          librarian_counter = 5;
                        }

                        break;

                    case 4:
                        //Process Book Borrow request
                        //Else give a list of books that can be borrowed
                        System.out.println("Please find the list of books available in the library");
                        try {
                            Connection connection_book_list = DriverManager.getConnection("jdbc:sqlserver://mydls.database.windows.net:1433;DatabaseName=myDLS","dls@mydls","SENG696Proj");
                            Statement create_statement = connection_book_list.createStatement();
                            ResultSet book_list = create_statement.executeQuery("select * from book");

                            //Process the Book borrow request
                            System.out.println("case 4: Process the Book borrow request");
//                            if (book_list.equals(true)) {
//                                ResultSet borrowed_book_name = create_statement.executeQuery("select book_name from book where book_id = '"+book_list.getInt("book_id")+"'");
//                                System.out.println("You have already borrowed a Book: "+borrowed_book_name+".\nPlease return the book to proceed.");
//                            } else if (book_list.equals(false)) {
//                                librarian_counter = 4;
//                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;

                    case 5:
                        //Process Book Return request
                        System.out.println("case 5: Process Book Return request");
                        //Show the book to be returned


                        //Process the return request


                        break;

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