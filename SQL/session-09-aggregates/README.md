# جلسه ۰۹: تجمیع و GROUP BY / HAVING (۹۰ دقیقه)

**محیط فرض:** SQLite 3

## پیش‌نیازها
جلسات ۰۵ و ۰۷.

## اهداف قابل‌اندازه‌گیری
1. COUNT، SUM، AVG، MIN، MAX را به کار ببرید
2. GROUP BY درست بنویسید
3. تفاوت WHERE و HAVING را بگویید
4. COUNT(*) را با COUNT(col) مقایسه کنید

## چرا مهم است؟
داشبورد و گزارش بدون تجمیع ساخته نمی‌شود.

## مفهوم عمیق
```sql
SELECT user_id, COUNT(*) AS order_count, SUM(total) AS revenue
FROM orders
GROUP BY user_id
HAVING SUM(total) > 100000;
```

WHERE قبل از گروه؛ HAVING بعد از تجمیع.

## اشتباه‌های رایج
1. ستون غیرتجمیعی در SELECT بدون GROUP BY.
2. فیلتر تجمیع با WHERE.
3. انتظار AVG روی ستون TEXT.

## مثال گام‌به‌گام
```bash
sqlite3 clinic.db < session-09-aggregates/examples/01-aggregates.sql
```

## الگوی بهتر
alias واضح برای متریک‌ها (`revenue`, `order_count`).

## تمرین‌ها
### آسان
تعداد سفارش هر کاربر.
### چالشی
فقط کاربرانی با بیش از یک سفارش.

## راهنمای حل
`GROUP BY user_id HAVING COUNT(*) > 1`

## خودآزمایی
1. HAVING کی لازم است؟
2. COUNT(*) چه می‌شمارد؟
3. آیا می‌توان روی عبارت GROUP BY کرد؟
4. MIN/MAX روی تاریخ چه معنایی دارد؟
5. چرا SELECT * با GROUP BY خطرناک است؟

## جمع‌بندی و پل جلسهٔ بعد
تجمیع. جلسه بعد: subquery.
