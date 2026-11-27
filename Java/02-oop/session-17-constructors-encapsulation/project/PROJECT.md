# پروژهٔ جلسه ۱۷ — سیستم حساب بانکی

یک سیستم بانکی بسازید که:
- کلاس `BankAccount` با `accountNumber`, `ownerName`, `balance` (private)
- متدهای `deposit`, `withdraw` (با چک موجودی), `transfer` (به حساب دیگر)
- getter برای `balance` و `accountNumber`
- در `main` دو حساب بسازید و بین آن‌ها transfer انجام دهید

نمونه:
```
Account 1001 (Ali): 1000.00
Account 1002 (Sara): 500.00
Transfer 200 from 1001 to 1002
After: 1001=800.00, 1002=700.00
```

## صورت پروژه
پاسخ در `solution/BankSystem.java` — از encapsulation و validation استفاده کنید.
