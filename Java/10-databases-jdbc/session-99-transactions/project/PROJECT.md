# پروژهٔ جلسه ۹۹ — سیستم بانکی با Transactions

یک سیستم بانکی بسازید که:
- `Account` model با id, name, balance
- `BankDao` با `transfer`, `deposit`, `withdraw` در transactions
- `BankManager` با main برای demo
- `insufficient funds` check در transaction
- `savepoint` برای complex operations
- `getBalance` برای check موجودی

نمونه:
```
=== Bank System ===
[Transfer] Ali → Sara 200 → SUCCESS
[Transfer] Reza → Ali 9999 → FAIL (insufficient)
[Balances] Ali: 800, Sara: 700, Reza: 300
```

## صورت پروژه
پاسخ در `solution/` — BankAccount.java + BankDao.java + BankManager.java.
