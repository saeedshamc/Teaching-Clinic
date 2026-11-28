# پروژهٔ جلسه ۴۹ — گزارشHR

یک سیستم گزارش HR بسازید که:
- `Employee` با `name`, `department`, `salary`, `age`
- stream برای: تعداد کارمند هر department، میانگین حقوق، بیشترین حقوق
- `groupingBy` برای department
- `partitioningBy` برای senior/junior
- `toMap` برای name→salary
- در `main` دادهٔ نمونه بسازید و گزارش چاپ کنید

نمونه:
```
Employees by dept: {IT=2, HR=2, Finance=1}
Avg salary by dept: {IT=5000.0, HR=4000.0, Finance=6000.0}
Highest salary: Ahmed (6000)
Senior (age>=30): 2
```

## صورت پروژه
پاسخ در `solution/HRReport.java` — از Collectors پیشرفته استفاده کنید.
