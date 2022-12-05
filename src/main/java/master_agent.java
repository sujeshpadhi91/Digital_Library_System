import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.*;

public class master_agent extends Agent {

    public static int student_counter = 0;
    public static int admin_counter = 0;
    public static int librarian_counter = 0;
    public static int choice = -1;
    public static int choice_of_book_operations = -1;
    public static String student_name = "";
    public static String student_id = "";
    public static int flag = -1;

    public static int book_id;

//    public static int stationer_counter = 0;
//    public static int print_counter = 0;

    protected void setup() {
//        System.out.printf("Master: My name is %s%n", getLocalName());
        Scanner sc = new Scanner(System.in);
        //mainAgentGUI main_window = new mainAgentGUI();
        addBehaviour(new SimpleBehaviour() {
            @Override
            public void action() {
                switch (student_counter) {
                    case 0:
                        //Request user input
                        while(flag==1)
                        {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        mainAgentGUI main_window = new mainAgentGUI();
                        flag=1;
                        mainAgentGUI.master_choice=0;
                        adminAgentGUI.name=null;
                        adminAgentGUI.ID=0;
                        System.out.printf("Master: My name is %s%n", getLocalName());
                        System.out.println("Master: Enter 1: Register\nEnter 2: Deregister\nEnter 3: Library\nEnter 4: Stationer\nEnter 5: Print");
                        while(mainAgentGUI.master_choice==0)
                        {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        int choice = mainAgentGUI.master_choice;
                        if(choice==1) //Register
                        {
                            student_counter = 1;
                        }
                        else if(choice==2) //Deregister
                        {
                            student_counter = 2;
                        }
                        else if (choice==3) //Library
                        {
//                            System.out.println("Library - \nPress 1: Lend\nPress 2: Return");
//                            libchoice = sc.nextInt();
//                            if(libchoice==1)
//                            {
//                                student_counter = 3;
//                            }
//                            else if(libchoice==2)
//                            {
//                                student_counter = 4;
//                            }
                            student_counter = 3;
                        }
                        else if(choice==4) //Stationer
                        {
                            student_counter = 5;
                        }
                        else if(choice==5) //Printer
                        {
                            student_counter = 6;
                        }
                        break;

                    case 1:
                        //redirect request to admin agent for reg
                        ACLMessage student_reg = new ACLMessage(ACLMessage.REQUEST);
                        student_reg.setContent("Register");
                        student_reg.addReceiver(new AID("Admin", AID.ISLOCALNAME));
                        send(student_reg);

                        //admin_agent.admin_counter = 2;
                        student_counter = 99;
                        break;


                    case 2:
                        //redirect request to admin for de-registration
                        ACLMessage student_de_registration = new ACLMessage(ACLMessage.REQUEST);
                        student_de_registration.setContent("Deregister");
                        student_de_registration.addReceiver(new AID("Admin", AID.ISLOCALNAME));
                        send(student_de_registration);

                        //admin_agent.admin_counter = 3;
                        student_counter = 99;
                        break;

//                    case 3:
//                        //redirect request to librarian-lend agent
//                        System.out.println("Lend");
//                        ACLMessage student_lend = new ACLMessage(ACLMessage.REQUEST);
//                        student_lend.setContent("Lend");
//                        student_lend.addReceiver(new AID("Librarian", AID.ISLOCALNAME));
//                        send(student_lend);
//
//                        student_counter = 99;
//                        break;
//
//                    case 4:
//                        //redirect request to librarian-return agent
//                        System.out.println("Return");
//                        ACLMessage student_ret = new ACLMessage(ACLMessage.REQUEST);
//                        student_ret.setContent("Return");
//                        student_ret.addReceiver(new AID("Librarian", AID.ISLOCALNAME));
//                        send(student_ret);
//
//                        student_counter = 99;
//                        break;

                    case 3:
                        System.out.println("Librarian");
                        ACLMessage student_lib = new ACLMessage(ACLMessage.REQUEST);
                        student_lib.setContent("Book_Operations");
                        student_lib.addReceiver(new AID("Librarian", AID.ISLOCALNAME));
                        send(student_lib);

                        student_counter = 99;
                        break;

                    case 5:
                        //redirect request to stationer agent
                        System.out.println("Stationer");
                        ACLMessage student_stat = new ACLMessage(ACLMessage.REQUEST);
                        student_stat.setContent("Buy");
                        student_stat.addReceiver(new AID("Stationer", AID.ISLOCALNAME));
                        send(student_stat);

                        student_counter = 99;
                        break;

                    case 6:
                        //redirect to print agent
                        System.out.println("Printer");
                        ACLMessage student_print = new ACLMessage(ACLMessage.REQUEST);
                        student_print.setContent("Print");
                        student_print.addReceiver(new AID("Printer", AID.ISLOCALNAME));
                        send(student_print);

                        student_counter = 99;
                        break;

                    case 99 :
                        //Student agent goes silent (but still active)
                        student_counter = 0;


                }
            }

            @Override
            public boolean done() {
                return false;
            }
        });
    }
}
