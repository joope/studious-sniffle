package PushArchitecture;

import java.util.ArrayList;

/**
 *
 * @author joope
 */
public class Statistics {
    private long messageSendTime;
    private final ArrayList<Long> messageReceivedTime = new ArrayList();
    private final int numberOfMessages;
    
    public Statistics(int numberOfMessages){
        this.numberOfMessages = numberOfMessages;
    }
    
    public void startTimer() {
        this.messageSendTime = System.currentTimeMillis();
    }
    
    public void receiveMessage() {
        this.messageReceivedTime.add(System.currentTimeMillis() - this.messageSendTime);

        if (this.messageReceivedTime.size() == this.numberOfMessages) {
            this.clear();
        }
    }
    
    public long getAverage() {
        long average = 0;
        for (int i = 0; i < this.messageReceivedTime.size(); i++) {
            average += this.messageReceivedTime.get(i);
        }
        return average / this.messageReceivedTime.size();
    }
    
    public long getInterArrivalRate() {
        if (this.messageReceivedTime.size() < 2) return 0;
        long previous = this.messageReceivedTime.get(0);
        long average = 0;
        for (int i = 1; i < this.messageReceivedTime.size(); i++) {
            long current = this.messageReceivedTime.get(i);
            long diff = current - previous;
            average += diff;
            previous = current;
            
        }
        return average / (this.messageReceivedTime.size() - 1);
    }
    
    public void clear() {
        long avg = this.getAverage();
        long interArrivalRate = this.getInterArrivalRate();
        System.out.println("Received all " + this.numberOfMessages + " messages");
        System.out.println("Messages took " + avg + "ms average");
        System.out.println("Inter arrival rate between messages was " + interArrivalRate + "ms average)");
        System.out.println("Time it took to receive all messages: " + (System.currentTimeMillis() - messageSendTime) + "ms");
        System.exit(0);
    }
}
