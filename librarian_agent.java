import jade.core.Agent;
public class librarian_agent extends Agent {

    private int counter = 0;
    protected void setup() {
        System.out.printf("My name is %s%n", getLocalName());

        addBehaviour(new SimpleBehaviour(this) {
            public void action() {
                switch(counter) {
                    case 0:
                        //Wait for Book lend request from Student

                    case 1:
                        //Send student verification request to the Admin agent

                    case 2:
                        //Wait for student verification status

                    case 3:
                        //Checks for the availibilty of the book requested

                    case 4:
                        //Process book lend request and update the status in Database

                    case 5:
                        //Wait for Book return request from Student

                    case 6:
                        //Process book return request and update the status in Database
                        
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
