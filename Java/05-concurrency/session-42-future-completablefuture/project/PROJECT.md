# پروژهٔ جلسه ۴۲ — پردازش زنجیره‌ای

یک سیستم پردازش زنجیره‌ای بسازید که:
- `CompletableFuture` برای fetch کردن داده (شبیه‌سازی با sleep)
- `thenApply` برای transform
- `thenCompose` برای مرحلهٔ بعدی
- `exceptionally` برای خطا
- در `main` کل زنجیره را تست کنید

نمونه:
```
Step 1: Fetching user data...
Step 2: Processing...
Step 3: Saving...
Result: User processed successfully
```

## صورت پروژه
پاسخ در `solution/AsyncPipeline.java` — از CompletableFuture chain استفاده کنید.
