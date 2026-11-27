# پاسخ تمرین‌های جلسه ۰۲

## آسان

**۱ و ۲.** کافی است IDE را نصب و `main` را Run/Debug کنید؛ خروجی `Hello from IDE!` کافی است.

## متوسط

**۳.** در IntelliJ: کلیک روی شمارهٔ خط کنار `println` → breakpoint (نقطهٔ قرمز) → Shift+F9 → در پنل Debug مقادیر را ببینید و Step Over (F8) کنید.

**۴.** `mvn -v` باید `Apache Maven 3.x.x` و `Java version: 17.x` نشان دهد؛ `gradle -v` مشابه. اگر نصب نیست، از داخل IDE (Maven tool window / Gradle panel) استفاده کنید.

## سخت

**۵.**
```bash
git init
git add .
git commit -m "initial project"
```

**۶.** Android Studio → More Actions → Virtual Device Manager → Create Device → Pixel 6 → System Image API 34 → Finish → ▶ Run. اگر خطای HAXM/Hyper-V بود، virtualization را در BIOS فعال کنید.
