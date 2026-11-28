# پروژهٔ جلسه ۱۴۶ — سیستم پیام‌رسانی با Message Queue

یک سیستم پیام‌رسانی با Message Queue بسازید:
- `MessageQueueDemo` با `BlockingQueue` برای in-memory queue
- `RabbitMQProducer` با `queueDeclare` و `basicPublish`
- `RabbitMQConsumer` با `basicConsume` و `DeliverCallback`
- `KafkaProducer` با `ProducerRecord` برای distributed streaming
- `Producer`/`Consumer` pattern برای async communication

نمونه:
```
Produced: Message 0
Consumed: Message 0
Produced: Message 1
Consumed: Message 1
...
Queue processing complete!
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
