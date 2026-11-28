# تمرین‌های جلسه ۳۲ — Try-with-Resources

## آسان
1. با try-with-resources یک فایل را بخوانید و چاپ کنید.
2. دو resource (FileWriter + BufferedWriter) را در یک try بنویسید.

## متوسط
3. `getSuppressed()` را برای دیدن suppressed exception تست کنید.
4. متد `readFile` که `BufferedReader` برمی‌گرداند بنویسید و در try-with-resources استفاده کنید.

## سخت
5. custom `AutoCloseable` بنویسید که در `close()` لاگ کند.
6. try-with-resources را با `Scanner` تست کنید — `Scanner` `Closeable` است.
