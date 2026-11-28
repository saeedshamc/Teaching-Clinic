# پروژهٔ جلسه ۱۴ — سیستم وضعیت با enum

## صورت پروژه
یک سیستم سفارش بسازید:
- `enum OrderStatus { pending, processing, shipped, delivered, cancelled }`
- `enum PaymentMethod { cash, card, online }`
- کلاس `Order` با `status` و `paymentMethod`
- متد `advance()` که status را به مرحلهٔ بعد ببرد
- `display()` که وضعیت را چاپ کند

خروجی نمونه:
```
Order #1: pending → processing → shipped → delivered
Payment: card
```

## پاسخ
`solution/main.dart`
