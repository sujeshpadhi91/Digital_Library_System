import jade.core.Agent;
public class stationer_agent extends Agent {

    private int counter = 0;
    protected void setup() {
        System.out.printf("My name is %s%n", getLocalName());

        addBehaviour(new SimpleBehaviour(this) {
            public void action() {
                switch(counter) {
                    case 0:
                        //Wait for item request from Student

                    case 1:
                        //Send student verification request to the Admin agent

                    case 2:
                        //Wait for student verification status

                    case 3:
                        //Checks for the availibilty of the item requested

                    case 4:
                        //Process item request and update the status in  the Database

                    case 5:
                        //Finish processing the item requests
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