package PushArchitecture;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 *
 * @author joope
 */
public class Client {
    public Client(String EXCHANGE_NAME, int id, Statistics statistics) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUri("amqp://nowkfxju:9siROW2w6qIObi-aPrMxxoQTHgM6Hpkf@bee.rmq.cloudamqp.com/nowkfxju");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
//            String queueName = Integer.toString(id);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, "");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
//                System.out.println(Integer.toString(id) + " Received '" + message + "'");
                statistics.receiveMessage();
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
        } catch (Exception e) {
            System.out.println(Integer.toString(id) + " Got error '" + e.getMessage() + "'");
        }

    }
}
