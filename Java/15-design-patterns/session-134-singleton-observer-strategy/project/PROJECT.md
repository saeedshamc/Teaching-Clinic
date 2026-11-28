# پروژهٔ جلسه ۱۳۴ — سیستم با Singleton, Observer & Strategy

یک سیستم با Singleton, Observer & Strategy بسازید:
- `ConfigManager` با Singleton برای configuration
- `User` با `PropertyChangeSupport` برای Observer
- `PaymentProcessor` با `PaymentGateway` Strategy
- `Sorter` با `SortStrategy` برای sorting algorithms
- `Logger` با Singleton + Observer برای logging

نمونه:
```
ConfigManager: getInstance() returns same instance
User: name changed → listeners notified
Payment: credit card vs PayPal vs bank transfer
Sort: bubble sort vs quick sort
Logger: multiple listeners receive log messages
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
