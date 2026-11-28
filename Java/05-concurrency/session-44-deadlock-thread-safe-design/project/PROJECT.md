# پروژهٔ جلسه ۴۴ — سیستم حسابداری thread-safe

یک سیستم حسابداری بسازید که:
- `Transaction` با `amount`, `type` (DEBIT/CREDIT), `timestamp`
- `Account` با `balance` و `List<Transaction>`
- `transfer(from, to, amount)` با atomic operation
- از `ReentrantLock` یا `synchronized` برای thread-safety
- در `main` چند thread همزمان transfer انجام دهند

نمونه:
```
Account A: 1000 → 800 (after transfer)
Account B: 500 → 700
Transactions: 3
```

## صورت پروژه
پاسخ در `solution/AccountingSystem.java` — از synchronized و lock ordering استفاده کنید.
