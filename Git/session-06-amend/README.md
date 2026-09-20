# جلسه ۰۶: amend فقط وقتی مجاز است (۹۰ دقیقه)

**محیط فرض:** Git · sandbox

## پیش‌نیازها
جلسات ۰۲–۰۳.

## اهداف قابل‌اندازه‌گیری
1. `git commit --amend` را برای اصلاح آخرین commit محلی اجرا کنید
2. سه شرط مجاز بودن amend را بگویید
3. بگویید چرا amend روی commitهای push‌شدهٔ اشتراکی خطرناک است
4. جایگزین امن (commit جدید) را بشناسید

## چرا مهم است؟
در Teaching Clinic گاهی پیام/فایل آخر را درست می‌کنید؛ عادت غلط amend روی تاریخچهٔ مشترک تاریخچه را می‌شکند.

## مفهوم عمیق
شرایط مجاز آموزشی این دوره:
1. آخرین commit مال خودتان باشد
2. هنوز push نشده باشد (یا فقط remote شخصی sandbox)
3. کس دیگری بر اساس آن کار نکرده باشد

```powershell
git add forgotten.txt
git commit --amend --no-edit
# یا با پیام جدید:
git commit --amend -m "fix: clearer message"
```

## اشتباه‌های رایج
1. amend بعد از push به `main` اشتراکی.
2. amend برای «هر تغییر بعدی» به‌جای commit جدید.
3. force push برای پوشاندن amend روی remote مشترک.

## مثال گام‌به‌گام
```powershell
powershell -File Git/session-06-amend/examples/01-amend-local.ps1
```

## الگوی بهتر
اگر شک دارید push شده یا نه: commit جدید بزنید.

## تمرین‌ها
### آسان
پیام آخرین commit را amend کنید.
### چالشی
فایل جاافتاده را به آخرین commit با `--no-edit` اضافه کنید.

## راهنمای حل
`git add file` سپس `git commit --amend --no-edit`.

## خودآزمایی
1. سه شرط amend؟
2. `--no-edit` چیست؟
3. چرا بعد از push خطرناک است؟
4. جایگزین چیست؟
5. آیا این دوره force به main Clinic را مجاز می‌کند؟

## جمع‌بندی و پل جلسهٔ بعد
اصلاح محلی محدود. جلسه بعد: شاخه.
