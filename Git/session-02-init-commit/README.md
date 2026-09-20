# جلسه ۰۲: init، add، commit (۹۰ دقیقه)

**محیط فرض:** Git · sandbox در Temp

## پیش‌نیازها
جلسه ۰۱.

## اهداف قابل‌اندازه‌گیری
1. با `git init -b main` ریپو بسازید
2. فایل را `add` و `commit` کنید
3. خروجی `status` را قبل و بعد تفسیر کنید
4. یک پیام commit کوتاه و معنادار بنویسید

## چرا مهم است؟
این چرخهٔ روزانهٔ هر توسعه‌دهنده است.

## مفهوم عمیق
```powershell
git init -b main
git status
git add README.md
git commit -m "docs: add README"
```

پیام خوب: چرا این تغییر — نه فقط «update».

## اشتباه‌های رایج
1. `commit` بدون `add`.
2. پیام خالی یا بی‌معنا (`fix`, `wip` دائمی).
3. `git config --global` بی‌دلیل وسط تمرین sandbox.

## مثال گام‌به‌گام
```powershell
powershell -File Git/session-02-init-commit/examples/01-first-commit.ps1
```

## الگوی بهتر
`status` قبل از commit؛ یک موضوع در هر commit وقتی ممکن است.

## تمرین‌ها
### آسان
یک فایل `note.txt` اضافه و commit کنید.
### چالشی
دو فایل را جداگانه در دو commit ثبت کنید.

## راهنمای حل
بعد از commit اول، فایل دوم را `add` و دوباره `commit`.

## خودآزمایی
1. `init` چه می‌سازد؟
2. staging را چه دستوری پر می‌کند؟
3. پیام خوب چه ویژگی دارد؟
4. `status` چه می‌گوید؟
5. چرا `-b main`؟

## جمع‌بندی و پل جلسهٔ بعد
اولین تاریخچه. جلسه بعد: log و diff.
