# پروژهٔ جلسه ۳۰ — سیستم رتبه‌بندی

یک سیستم رتبه‌بندی بسازید که:
- `List<Student>` با `name`, `score`, `rank`
- مرتب‌سازی بر اساس score (نزولی) با `Comparator`
- تخصیص rank خودکار بعد از sort
- نمایش جدول با rank، نام، score
- در `main` ۵ دانش‌آموز با scoreهای مختلف بسازید

نمونه:
```
Rank  Name    Score
1     Mina    95
2     Ali     88
3     Sara    82
```

## صورت پروژه
پاسخ در `solution/RankingSystem.java` — از `Comparator.comparingInt` و `reversed` استفاده کنید.
