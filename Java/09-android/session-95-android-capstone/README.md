# جلسه ۹۵ — Android Capstone: اپ نهایی کامل

## اهداف یادگیری
- همهٔ مباحث Android را در یک اپ کامل ترکیب کنید
- معماری MVVM + Repository + Room + Retrofit + Hilt را به‌کار ببرید
- Navigation, WorkManager, Notifications, Services را با هم استفاده کنید

## پیش‌نیازها
- همهٔ جلسات فاز ۶ (۷۶–۹۴)

## مدت زمان پیشنهادی
۱۲۰ دقیقه

## درس

### پروژهٔ نهایی: اپ مدیریت وظایف و مخاطبین

یک اپ کامل بسازید که:

**Features:**
- مدیریت وظایف (CRUD با Room)
- مدیریت مخاطبین (CRUD با Retrofit + Room)
- Navigation بین صفحات
- WorkManager برای background sync
- Notifications برای reminders
- Settings با DataStore
- Hilt برای DI

**Architecture:**
```
UI (Activity/Fragment)
    ↓ observes
ViewModel
    ↓ uses
Repository
    ↓ calls
Room (local) + Retrofit (remote)
    ↓
WorkManager (background) + Notifications
```

## کد مهم

`src/` — کامل‌ترین نسخهٔ اپ با همهٔ features.

## جمع‌بندی فاز ۶ — Android (کامل)

| زیرشاخه | جلسات | موضوع |
|---|---|---|
| Legacy Views/XML | ۷۶–۸۱ | Setup, Activities, Layouts, RecyclerView, Fragments, Capstone |
| Modern Jetpack | ۸۲–۹۰ | ViewModel, Room, Retrofit, WorkManager, Navigation, Coroutines, Hilt, Capstone |
| Android Advanced | ۹۱–۹۵ | Services, Broadcast/Permissions, Notifications, Final Prep, Capstone |

**فاز ۶ کامل شد — ۲۰ جلسه ✅**
