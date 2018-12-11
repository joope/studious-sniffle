package PushArchitecture;

import java.util.Random;

/**
 *
 * @author joope
 */
public class PayloadGenerator {
    private Random random = new Random();
    private String messageContent = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
    private int maxLength;
    private boolean isRandom;
    
    public PayloadGenerator(int maxLength, boolean isRandom) {
        this.maxLength = maxLength; // Length of message in bytes
        this.isRandom = isRandom; // should the message size vary?
    }
    
    public String getMessage(){
        int sizeLimit = this.isRandom ? 
                this.random.nextInt(this.maxLength) : 
                this.maxLength;
        String message = "";
        for (int i = 0; i < sizeLimit; i++) {
            message += messageContent.charAt(random.nextInt(messageContent.length()));
        }
        return message;
    }
}
