# پاسخ تمرین‌های جلسه ۱۴۶

## آسان

**۱ و ۲.**
```java
BlockingQueue<String> queue = new LinkedBlockingQueue<>();

Thread producer = new Thread(() -> {
    queue.put("message");
});

Thread consumer = new Thread(() -> {
    String message = queue.take();
});
```

## متوسط

**۳ و ۴.**
```java
channel.queueDeclare(QUEUE_NAME, true, false, false, null);
channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

DeliverCallback deliverCallback = (consumerTag, delivery) -> {
    String message = new String(delivery.getBody());
    System.out.println("Received: " + message);
};
channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
```

## سخت

**۵ و ۶.**
```java
Properties props = new Properties();
props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

KafkaProducer<String, String> producer = new KafkaProducer<>(props);
ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, "key1", "message");
producer.send(record);
```
