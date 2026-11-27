# پروژهٔ جلسه ۲۰ — سیستم کارمندان

یک سیستم منابع انسانی بسازید که:
- `abstract Employee` با `name`, `id`, `hireDate` و `abstract double calculateSalary()`
- `FullTime` (حقوق ماهانه + bonus) و `PartTime` (ساعتی × ساعت) را پیاده کنید
- متد `printPayslip()` در abstract class که جزئیات را چاپ کند
- در `main` لیستی از کارمندان بسازید و payslip هرکدام را چاپ کنید

نمونه:
```
Ali (id=1, FullTime): base=5000, bonus=500, total=5500
Sara (id=2, PartTime): rate=50, hours=80, total=4000
```

## صورت پروژه
پاسخ در `solution/EmployeeSystem.java` — از abstract class و polymorphism استفاده کنید.
