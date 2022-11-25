import jade.core.Agent;
public class librarian_agent extends Agent {

    private int counter = 0;
    protected void setup() {
        System.out.printf("My name is %s%n", getLocalName());

        addBehaviour(new SimpleBehaviour(this) {
            public void action() {

            }
        }
    }

}
