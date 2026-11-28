// KafkaProducer.java — Kafka producer demo
// نیازمند: kafka-clients jar برای کامپایل
// import org.apache.kafka.clients.producer.*;
// import org.apache.kafka.common.serialization.StringSerializer;
// import java.util.*;

public class KafkaProducer {

    private static final String TOPIC = "tasks";

    public static void main(String[] args) {
        // Uncomment when kafka-clients is available:
        // Properties props = new Properties();
        // props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //
        // try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
        //     ProducerRecord<String, String> record =
        //         new ProducerRecord<>(TOPIC, "key1", "Hello Kafka!");
        //     producer.send(record);
        //     producer.flush();
        //     System.out.println("Sent to Kafka: " + TOPIC);
        // }

        System.out.println("Kafka producer — uncomment when kafka-clients is available");
        System.out.println("Topic: " + TOPIC);
        System.out.println("Message: Hello Kafka!");
    }
}
