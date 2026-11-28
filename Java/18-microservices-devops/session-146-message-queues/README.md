# جلسه ۱۴۶ — Message Queues: Async Communication

## اهداف یادگیری
- `Message Queues` را برای async communication بشناسید
- `Producer`, `Consumer`, `Queue`, `Topic` را بفهمید
- `RabbitMQ`, `Kafka`, `ActiveMQ` را مقایسه کنید
- `JMS`, `AMQP` را برای messaging استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۴۵ (CI/CD)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Message Queue چیست؟

`Message Queue` — async communication بین services. Producer پیام می‌فرستد، Consumer پردازش می‌کند.

```
Producer → Queue → Consumer
         ↓
    Message Broker (RabbitMQ, Kafka)
```

### RabbitMQ — AMQP

```java
// نیازمند: amqp-client jar
import com.rabbitmq.client.*;

public class RabbitMQProducer {

    private static final String QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            String message = "Hello RabbitMQ!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

            System.out.println("Sent: " + message);
        }
    }
}
```

```java
public class RabbitMQConsumer {

    private static final String QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody());
                System.out.println("Received: " + message);
            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

            System.out.println("Waiting for messages...");
            Thread.sleep(10000);
        }
    }
}
```

### Kafka — distributed streaming

```java
// نیازمند: kafka-clients jar
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.util.*;
import java.util.Properties;

public class KafkaProducer {

    private static final String TOPIC = "tasks";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
            ProducerRecord<String, String> record =
                new ProducerRecord<>(TOPIC, "key1", "Hello Kafka!");
            producer.send(record);
            producer.flush();
        }
    }
}
```

### Message Queue Patterns

| Pattern | Description |
|---|---|
| **Point-to-Point** | One producer, one consumer |
| **Pub/Sub** | One producer, multiple consumers |
| **Request-Reply** | Synchronous over async |
| **Dead Letter Queue** | Failed messages |

## کد مهم

`src/RabbitMQProducer.java` — RabbitMQ producer.
`src/RabbitMQConsumer.java` — RabbitMQ consumer.
`src/KafkaProducer.java` — Kafka producer.
`src/MessageQueueDemo.java` — in-memory queue demo.

## خطاهای رایج
- `queueDeclare` را بدون `durable` استفاده کردن → queue lost on restart.
- `basicPublish` را بدون `mandatory` استفاده کردن → message lost.
- `basicConsume` را بدون `autoAck` استفاده کردن → message loss.
- `KafkaProducer` را بدون `close()` استفاده کردن → resource leak.

## جمع‌بندی
- Message queues برای async communication.
- RabbitMQ برای AMQP, Kafka برای distributed streaming.
- Producer/Consumer pattern.
- Pub/Sub, Point-to-Point, Request-Reply patterns.

**جلسهٔ بعد:** Microservices Intro.
