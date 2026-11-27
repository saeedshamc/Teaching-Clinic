# پروژهٔ جلسه ۱۴ — شبیه‌ساز تاس

یک برنامه بنویسید که:
- `Random` یا `ThreadLocalRandom` استفاده کند
- n تاس را شبیه‌سازی کند (هرکدام ۱ تا ۶)
- مجموع، بیشترین و کمترین را چاپ کند
- با `BigDecimal` میانگین را با ۲ رقم اعشار چاپ کند

نمونه:
```
java Dice 5
Dice: 3 5 2 6 4
Sum: 20, Avg: 4.00, Max: 6, Min: 2
```

## صورت پروژه
پاسخ در `solution/Dice.java` — از `ThreadLocalRandom.current().nextInt(1, 7)` استفاده کنید.
