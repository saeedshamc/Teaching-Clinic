# پروژهٔ جلسه ۳۸ — دانلود موازی

یک برنامه شبیه‌سازی دانلود بنویسید که:
- ۳ thread برای دانلود ۳ فایل متفاوت
- هر thread ۵ ثانیه "دانلود" می‌کند (با sleep)
- پیشرفت را چاپ کند
- `join()` برای انتظار همه
- زمان کل را اندازه بگیرد

نمونه:
```
Downloading file1... 20%
Downloading file2... 40%
...
All downloads complete in ~5 seconds (parallel)
```

## صورت پروژه
پاسخ در `solution/DownloadManager.java` — از Thread class و join استفاده کنید.
