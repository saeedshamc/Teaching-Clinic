# جلسه ۱۰: زیر‌پرس‌وجو (Subquery) (۹۰ دقیقه)

**محیط فرض:** SQLite 3

## پیش‌نیازها
جلسات ۰۵ و ۰۹.

## اهداف قابل‌اندازه‌گیری
1. subquery در WHERE با IN بنویسید
2. subquery اسکالر در مقایسه به کار ببرید
3. بگویید کی join خواناتر از subquery است
4. از subquery همبسته (correlated) فقط در حد آگاهی استفاده کنید

## چرا مهم است؟
گاهی فیلتر بر اساس نتیجهٔ یک پرس‌وجوی دیگر است.

## مفهوم عمیق
```sql
SELECT name FROM users
WHERE id IN (
  SELECT user_id FROM orders WHERE total > 100000
);

SELECT * FROM orders
WHERE total > (SELECT AVG(total) FROM orders);
```

## اشتباه‌های رایج
1. subquery اسکالر که بیش از یک سطر برمی‌گرداند.
2. پیچاندن همه چیز با subquery وقتی join ساده‌تر است.
3. فراموش alias در subquery داخل FROM.

## مثال گام‌به‌گام
```bash
sqlite3 clinic.db < session-10-subqueries/examples/01-subqueries.sql
```

## الگوی بهتر
برای خوانایی، CTE/`WITH` را در نسخهٔ پیشرفته‌تر ببینید؛ اینجا IN و اسکالر کافی است.

## تمرین‌ها
### آسان
کاربرانی که حداقل یک سفارش دارند (با IN).
### چالشی
سفارش‌های بالاتر از میانگین.

## راهنمای حل
`WHERE total > (SELECT AVG(total) FROM orders)`

## خودآزمایی
1. subquery اسکالر چیست؟
2. IN چه می‌خواهد؟
3. کی join بهتر است؟
4. اگر subquery خالی باشد IN چه می‌شود؟
5. correlated یعنی چه؟

## جمع‌بندی و پل جلسهٔ بعد
subquery. جلسه بعد: UNION و مجموعه‌ها.
