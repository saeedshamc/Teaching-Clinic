// RabbitMQConsumer.java — RabbitMQ consumer demo
// نیازمند: amqp-client jar برای کامپایل
// import com.rabbitmq.client.*;

public class RabbitMQConsumer {

    private static final String QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws Exception {
        // Uncomment when amqp-client is available:
        // ConnectionFactory factory = new ConnectionFactory();
        // factory.setHost("localhost");
        //
        // try (Connection connection = factory.newConnection();
        //      Channel channel = connection.createChannel()) {
        //     channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //     DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        //         String message = new String(delivery.getBody());
        //         System.out.println("Received: " + message);
        //     };
        //     channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        //     System.out.println("Waiting for messages...");
        //     Thread.sleep(10000);
        // }

        System.out.println("RabbitMQ consumer — uncomment when amqp-client is available");
        System.out.println("Queue: " + QUEUE_NAME);
        System.out.println("Waiting for messages...");
    }
}
