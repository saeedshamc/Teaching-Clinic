# پروژهٔ جلسه ۸۶ — سیستم همگام‌سازی پس‌زمینه

یک سیستم همگام‌سازی پس‌زمینه بسازید که:
- `SyncWorker` برای background sync
- `PeriodicWorkRequest` برای recurring sync (هر ۱۵ دقیقه)
- `Constraints` برای network requirement
- `Data` برای input/output
- `WorkManagerActivity` برای enqueue/cancel
- `TextView` برای status

نمونه:
```
[Sync Data] → Enqueue periodic work
[Status: Sync scheduled every 15 min]
[Cancel] → Cancel all work
```

## صورت پروژه
پاسخ در `solution/` — SyncWorker.java + WorkManagerActivity.java.
