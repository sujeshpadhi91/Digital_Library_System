import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

public class librarian_agent extends Agent {
    private int librarian_counter = 0;
    private boolean done = false;
    protected void setup() {
        System.out.printf("My name is %s%n", getLocalName());
        addBehaviour(new SimpleBehaviour(this) {
            public void action() {
                switch (librarian_counter) {
                    case 0:
                        //Wait for Book lend request from Student

                    case 1:
                        //Send student verification request to the Admin agent
                    case 2:
                        //Wait for student verification status
                    case 3:
                        //Checks for the availability of the book requested
                    case 4:
                        //Process book lend request and update the status in Database
                    case 5:
                        //Wait for Book return request from Student
                    case 6:
                        //Process book return request and update the status in Database
                    case 7:
                        //Finish processing the book requests
                        done = true;
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