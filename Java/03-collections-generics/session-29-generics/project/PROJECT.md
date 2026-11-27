# پروژهٔ جلسه ۲۹ — Generic Container

یک کانتینر generic بسازید که:
- کلاس `Container<T>` با `add`, `get`, `remove`, `size`
- متد `max` با `<T extends Comparable<T>>`
- متد `sum` با `<T extends Number>`
- در `main` چند نوع مختلف تست کنید (String, Integer, Double)

نمونه:
```
Container<String>: [Ali, Sara, Reza]
max: Reza
Container<Integer>: [10, 20, 30]
sum: 60.0
```

## صورت پروژه
پاسخ در `solution/GenericContainer.java` — از generic class و bounded method استفاده کنید.
