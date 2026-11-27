# پروژهٔ جلسه ۲۸ — سیستم نوبت‌دهی

یک سیستم نوبت‌دهی بسازید که:
- `Queue<String>` برای صف انتظار
- `Deque<String>` برای VIP (offerFirst) و عادی (offerLast)
- متدهای `addNormal`, `addVIP`, `serveNext`, `displayQueue`
- در `main` چند مشتری اضافه و سرو کنید

نمونه:
```
Queue: [VIP1, Normal1, Normal2, VIP2]
Serving: VIP1 (VIP first)
Serving: Normal1
```

## صورت پروژه
پاسخ در `solution/QueueSystem.java` — از `ArrayDeque` و `offerFirst`/`offerLast` استفاده کنید.
