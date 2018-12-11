package PushArchitecture;
import java.util.ArrayList;
/**
 *
 * @author joope
 */
public class PushArchitecture {
    private final static String EXCHANGE_NAME = "messages";
    private final static int NUM_CLIENTS = 2;
    private final static int NUM_MESSAGES = 25;
    private final static int MAX_MESSAGE_SIZE = 10000;
    private final static boolean USE_RANDOM_MESSAGE_SIZE = false;

    public static void main(String[] args) throws Exception {
        Statistics statistics = new Statistics(NUM_MESSAGES*NUM_CLIENTS);
        PayloadGenerator payloadGenerator = new PayloadGenerator(MAX_MESSAGE_SIZE, USE_RANDOM_MESSAGE_SIZE);
        
        ArrayList<String> messages = new ArrayList();
        for (int i = 0; i < NUM_MESSAGES; i++) {
            messages.add(payloadGenerator.getMessage());
        }
        
        ArrayList<Client> clients = new ArrayList();
        for (int i = 0; i < NUM_CLIENTS; i++) {
            clients.add(new Client(EXCHANGE_NAME, i, statistics));
        }
        
        Server server = new Server(EXCHANGE_NAME, statistics);
        statistics.startTimer();
        
        for (int i = 0; i < NUM_MESSAGES; i++) {
            server.pushMessage(messages.get(i));
        }        
    }
    
}
