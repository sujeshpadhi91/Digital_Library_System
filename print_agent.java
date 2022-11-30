import jade.core.Agent;
public class print_agent extends Agent {

    private int counter = 0;
    protected void setup() {
        System.out.printf("My name is %s%n", getLocalName());

        addBehaviour(new SimpleBehaviour(this) {
            public void action() {
                switch(counter) {
                    case 0:
                        //Wait for print request from Student

                    case 1:
                        //Send student verification request to the Admin agent

                    case 2:
                        //Wait for student verification status

                    case 3:
                        //Checks for the availibilty of the pages requested for printing

                    case 4:
                        //Process print request and update the page remaining details in  the Database

                    case 5:
                        //Finish processing the print requests
                        done = true;
                }
            }

            public boolean done() {
                if (done) {
                    System.out.printf("My name is %s%n", getLocalName());
                }
                return done;
            }
        }
    }

}