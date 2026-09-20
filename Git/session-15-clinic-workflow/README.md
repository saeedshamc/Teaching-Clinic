# جلسه ۱۵: گردش کار Teaching Clinic (۹۰ دقیقه)

**محیط فرض:** مفهومی + شبیه‌سازی در sandbox

## پیش‌نیازها
کل فازهای قبلی · مشاهدهٔ الگوی کامیت دوره‌های [`SQL/`](../../SQL/) یا [`TypeScript/`](../../TypeScript/).

## اهداف قابل‌اندازه‌گیری
1. الگوی «یک کامیت per session» را توضیح دهید
2. پیام با پیشوند دوره را بنویسید (`SQL: ...` / `Git: ...`)
3. چک‌لیست قبل از commit را بگویید: status، diff، فایل حساس
4. بگویید finalize docs جداگانه است

## چرا مهم است؟
همین ریپو با همین قرارداد جلو رفته؛ یادگیری‌اش انتقال مستقیم به مشارکت واقعی است.

## مفهوم عمیق
```
1) فقط فایل‌های همان جلسه را stage کن
2) پیام: "Git: session-07 — شاخه و switch"
3) یک جلسه = یک commit (مگر تکمیل کوچک جدا با پیام واضح)
4) در پایان دوره: "Git: finalize docs — README + SYLLABUS + SETUP"
```

بدون `Co-authored-by: Cursor` و بدون اشاره به AI در پیام.

## اشتباه‌های رایج
1. قاطی کردن چند جلسه در یک commit غول.
2. stage کردن `_sandbox` یا `.env`.
3. amend روی کامیت‌هایی که قبلاً push شده‌اند بدون هماهنگی.

## مثال گام‌به‌گام
```powershell
powershell -File Git/session-15-clinic-workflow/examples/01-simulate-session-commit.ps1
```

## الگوی بهتر
`git status` و `git diff --staged` درست قبل از commit.

## تمرین‌ها
### آسان
یک پیام نمونه برای session-16 بنویسید.
### چالشی
در sandbox دو «جلسه» را در دو commit جدا شبیه‌سازی کنید.

## راهنمای حل
دو بار add+commit با پیام‌های `Git: session-01 — ...` و `Git: session-02 — ...`.

## خودآزمایی
1. یک کامیت per session یعنی چه؟
2. پیشوند پیام چرا؟
3. قبل از commit چه چک‌هایی؟
4. finalize چیست؟
5. آیا trailer هوش مصنوعی مجاز است؟

## جمع‌بندی و پل جلسهٔ بعد
قرارداد Clinic. جلسه آخر: capstone.
