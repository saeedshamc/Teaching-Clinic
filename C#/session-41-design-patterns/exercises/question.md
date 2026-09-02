# تمرین جلسه ۴۱ — Design Patterns

## تمرین ۱ — Factory + Strategy

1. `IShippingStrategy` با `Standard` (رایگان بالای ۵۰۰هزار) و `Express` (۵۰هزار ثابت)
2. `ShippingFactory.Create(string type)` 
3. `CheckoutService` که shipping cost محاسبه کند

---

## تمرین ۲ — Observer + Decorator

1. `OrderService` با event `OrderPlaced`
2. دو observer: Logger و InventoryUpdater
3. `AuditNotifier` decorator که قبل/بعد Send لاگ کند

---

**راهنما:** [exercise_01.cs](./exercise_01.cs) | [exercise_02.cs](./exercise_02.cs)
