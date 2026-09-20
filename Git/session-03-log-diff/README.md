# جلسه ۰۳: log، show، diff (۹۰ دقیقه)

**محیط فرض:** Git · sandbox

## پیش‌نیازها
جلسه ۰۲.

## اهداف قابل‌اندازه‌گیری
1. `git log --oneline` را بخوانید
2. `git show` یک commit را تفسیر کنید
3. فرق `diff`، `diff --staged` و diff بین commitها را بگویید
4. بگویید قبل از commit دیدن diff چرا مهم است

## چرا مهم است؟
بدون خواندن تاریخچه و diff، review و دیباگ با Git ممکن نیست.

## مفهوم عمیق
```powershell
git diff              # working vs staging
git diff --staged     # staging vs آخرین commit
git log -p            # تاریخچه با پچ
git show HEAD
```

## اشتباه‌های رایج
1. commit بدون نگاه به diff.
2. قاطی کردن unstaged و staged.
3. اتکا فقط به GUI بدون فهم خروجی CLI.

## مثال گام‌به‌گام
```powershell
powershell -File Git/session-03-log-diff/examples/01-log-diff.ps1
```

## الگوی بهتر
قبل از commit: `status` + `diff` (+ `diff --staged`).

## تمرین‌ها
### آسان
یک خط به فایل اضافه و `git diff` ببینید.
### چالشی
همان تغییر را stage کنید و `diff --staged` را با `diff` مقایسه کنید.

## راهنمای حل
بعد از `add`، `diff` خالی و `diff --staged` پر می‌شود.

## خودآزمایی
1. `log --oneline` چه می‌دهد؟
2. `show HEAD` چیست؟
3. سه حالت diff؟
4. چرا قبل از commit diff؟
5. `-p` در log چه می‌کند؟

## جمع‌بندی و پل جلسهٔ بعد
خواندن تاریخچه. جلسه بعد: gitignore.
