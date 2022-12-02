import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

public class admin_agent extends Agent {
    private int admin_counter = 0;
    private boolean done = false;
    protected void setup() {
        System.out.printf("My name is %s%n", getLocalName());
        addBehaviour(new SimpleBehaviour(this) {
            public void action() {
                switch(admin_counter) {
                    case 0:
                        //Wait for Verification request from the Librarian Agent or the Stationer Agent or the Print Agent
                        ACLMessage student_verification = blockingReceive();
                        if (student_verification != null) {
                            System.out.println("Request to verify the student: "+student_verification.getContent());
                                ACLMessage student_verification_status = student_verification.createReply();
                                student_verification_status.setPerformative(ACLMessage.INFORM);
                                student_verification_status.setContent("Student is Registered");
                                send(student_verification_status);
                        }
                        break;

                    case 1:
                        //Query the Database to check the Student details for verification
                    case 2:
                        //Take and process a new registration request
                    case 3:
                        //Take and process a new de-registration request
                    case 4:
                        //Finish processing the student verification and/or registration requests
                        System.out.printf("Finished Admin Roles");
                        done = true;
                        break;
                }
            }
            public boolean done() {
                return false;
            }
        });
    }
}