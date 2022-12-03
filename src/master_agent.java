import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

public class student_agent extends Agent {

    public static int student_counter = 0;
    public static int admin_counter = 0;
    public static int librarian_counter = 0;
//    public static int stationer_counter = 0;
//    public static int print_counter = 0;

    protected void setup() {
        System.out.printf("Student: My name is %s%n", getLocalName());

        addBehaviour(new SimpleBehaviour() {
            @Override
            public void action() {
                switch (student_counter) {
                    case 0:
                        //Request user input
                        System.out.println("Welcome to the Digital Library System");
                        System.out.println("Student");


                    case 1:
                        //redirect request to admin agent

                    case 2:
                        //redirect request to librarian agent

                    case 3:
                        //redirect request to stationer agent

                    case 4:
                        //redirect request to print agent

                    case 99 :
                        //Student agent goes silent (but still active)


                }
            }

            @Override
            public boolean done() {
                return false;
            }
        });
    }
}
