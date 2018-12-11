package PushArchitecture;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 *
 * @author joope
 */
public class Server {
    private final String EXCHANGE_NAME;
    private final Channel channel;
    private final Connection connection;

    public Server(String EXCHANGE_NAME, Statistics statistics) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://nowkfxju:9siROW2w6qIObi-aPrMxxoQTHgM6Hpkf@bee.rmq.cloudamqp.com/nowkfxju");
        this.connection = factory.newConnection();
        this.channel = this.connection.createChannel();
        this.channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        this.EXCHANGE_NAME = EXCHANGE_NAME;
    }
    
    public void pushMessage(String message) throws Exception {
        this.channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
    }
    
    public void clear() throws Exception {
        this.channel.close();
        this.connection.close();
    }
}
