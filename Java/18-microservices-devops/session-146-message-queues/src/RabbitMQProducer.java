// RabbitMQProducer.java — RabbitMQ producer demo
// نیازمند: amqp-client jar برای کامپایل
// import com.rabbitmq.client.*;

public class RabbitMQProducer {

    private static final String QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws Exception {
        // Uncomment when amqp-client is available:
        // ConnectionFactory factory = new ConnectionFactory();
        // factory.setHost("localhost");
        //
        // try (Connection connection = factory.newConnection();
        //      Channel channel = connection.createChannel()) {
        //     channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //     String message = "Hello RabbitMQ!";
        //     channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        //     System.out.println("Sent: " + message);
        // }

        System.out.println("RabbitMQ producer — uncomment when amqp-client is available");
        System.out.println("Queue: " + QUEUE_NAME);
        System.out.println("Message: Hello RabbitMQ!");
    }
}
