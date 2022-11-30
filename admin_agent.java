import jade.core.Agent;
public class admin_agent extends Agent {

    private int counter = 0;
    protected void setup() {
        System.out.printf("My name is %s%n", getLocalName());

        addBehaviour(new SimpleBehaviour(this) {
            public void action() {
                switch(counter) {
                    case 0:
                        //Wait for Verification request from the Librarian Agent or the Stationer Agent or the Print Agent

                    case 1:
                        //Query the Database to check the Student details for verification

                    case 2:
                        //Take and process a new registration request

                    case 3:
                        //Take and process a new deregistration request

                    case 4:
                        //Finish processing the student verificationa and/or registration requests
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