# جلسه ۰۹: rebase محلی (۹۰ دقیقه)

**محیط فرض:** Git · sandbox

## پیش‌نیازها
جلسات ۰۷–۰۸.

## اهداف قابل‌اندازه‌گیری
1. شاخهٔ شخصی را روی main به‌روز با `rebase` کنید
2. بگویید rebase تاریخچه را چطور «خطی»تر می‌کند
3. قاعده را بگویید: روی commitهای اشتراکی rebase نکنید
4. فرق ذهنی rebase با merge را در یک جمله بگویید

## چرا مهم است؟
در کار شخصی، rebase تاریخچه را خوانا نگه می‌دارد؛ سوءاستفاده روی شاخهٔ مشترک دردسر می‌سازد.

## مفهوم عمیق
```powershell
git switch feature/x
git rebase main
```

اگر تعارض شد: حل کنید، `git add`، `git rebase --continue` (یا `--abort`).

**نکنید:** rebase شاخه‌ای که دیگران از آن pull کرده‌اند + force push به remote مشترک.

## اشتباه‌های رایج
1. rebase روی main اشتراکی بعد از push عمومی.
2. قاطی کردن `--abort` و رها کردن وضعیت.
3. rebase را جایگزین فهم merge دانستن.

## مثال گام‌به‌گام
```powershell
powershell -File Git/session-09-rebase/examples/01-rebase-local.ps1
```

## الگوی بهتر
feature شخصیِ push‌نشده یا فقط remote شخصی sandbox → rebase مجاز آموزشی.

## تمرین‌ها
### آسان
feature را روی main rebase کنید و graph را ببینید.
### چالشی
عمداً تعارض بسازید و با `--abort` برگردید.

## راهنمای حل
`git rebase --abort` وضعیت قبل از rebase را برمی‌گرداند.

## خودآزمایی
1. rebase چه می‌کند؟
2. کی ممنوع است؟
3. `--continue` کی؟
4. فرق با merge؟
5. force push چه ربطی دارد؟

## جمع‌بندی و پل جلسهٔ بعد
rebase محتاط. جلسه بعد: stash و tag.
