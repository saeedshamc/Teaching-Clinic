# جلسه ۱۴: دستورات خطرناک در sandbox (۹۰ دقیقه)

**محیط فرض:** فقط Temp sandbox · ممنوع روی Clinic

## پیش‌نیازها
جلسات ۰۵–۰۶ و ۱۱.

## اهداف قابل‌اندازه‌گیری
1. `reset --soft` / `--mixed` / `--hard` را در sandbox مقایسه کنید
2. بگویید `--hard` کار commit‌نشده را دور می‌اندازد
3. قاعده را حفظ کنید: **هرگز `push --force` به main اشتراکی Clinic**
4. جایگزین امن برای «جمع کردن commitها» را بشناسید (revert مفهومی)

## چرا مهم است؟
این دستورات قدرتمندند؛ روی ریپوی اشتراکی بدون فهم، داده‌کاری دیگران را می‌شکنند.

## مفهوم عمیق
```powershell
git reset --soft HEAD~1   # commit عقب؛ staging می‌ماند
git reset --mixed HEAD~1  # پیش‌فرض؛ تغییرات unstaged
git reset --hard HEAD~1   # تاریخچه و working به آن commit؛ کار کثیف پریده
```

`git revert` commit جدیدِ خنثی‌کننده می‌سازد — برای تاریخچهٔ عمومی امن‌تر است (این جلسه فقط اشاره).

## اشتباه‌های رایج
1. `--hard` روی Clinic.
2. force push برای «درست کردن» بعد از reset روی remote مشترک.
3. reset به‌جای restore برای یک فایل.

## مثال گام‌به‌گام
```powershell
powershell -File Git/session-14-dangerous-commands/examples/01-reset-sandbox.ps1
```

## الگوی بهتر
شک دارید؟ اول شاخهٔ backup در sandbox: `git branch backup`.

## تمرین‌ها
### آسان
`--soft` و `--mixed` را مقایسه کنید.
### چالشی
قبل از `--hard` یک شاخهٔ backup بگیرید.

## راهنمای حل
`git branch safety` سپس reset؛ با `switch safety` برگردید.

## خودآزمایی
1. سه حالت reset؟
2. hard چه چیزی را پاک می‌کند؟
3. force به main Clinic مجاز است؟
4. revert چه فرقی دارد؟
5. backup branch چرا؟

## جمع‌بندی و پل جلسهٔ بعد
ایمنی. جلسه بعد: گردش کار Teaching Clinic.
