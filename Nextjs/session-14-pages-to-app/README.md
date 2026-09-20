# جلسه ۱۴: مهاجرت ذهنی Pages Router → App Router (۶۰ دقیقه)

## پیش‌نیاز
جلسات ۰۳–۰۵

## اهداف یادگیری
1. معادل‌های `pages/` با `app/` را بگویید.
2. `_app`/`_document` را به layout ترجمه کنید.
3. `getServerSideProps` ذهنیت را با fetch سرور جایگزین کنید.

## چرا مهم است
خیلی از آموزش‌های قدیمی Pages هستند؛ بدون نقشهٔ مهاجرت گیج می‌شوید.

## مفهوم اصلی
| Pages | App |
|-------|-----|
| pages/index.js | app/page.tsx |
| _app | app/layout.tsx |
| getServerSideProps | fetch در Server Component / dynamic |
| pages/api | app/api/route.ts |

## اشتباهات رایج
1. مخلوط بی‌برنامه دو روتر.
2. کپی عینی getServerSideProps بدون درک RSC.
3. فراموش کردن کلاینت‌باونداری برای hooks.

## تمرین
### آسان
یک صفحهٔ Pages فرضی را روی کاغذ به App نگاشت کنید.
### چالش
لیست ۳ تله در مهاجرت تدریجی.

## خودآزمایی
- [ ] جدول معادل را بلدم
- [ ] layout جایگزین _app
- [ ] api به route handler
- [ ] مهاجرت تدریجی ممکن است
- [ ] این دوره App-first است

## جمع‌بندی + پل
نقشهٔ مهاجرت. جلسهٔ بعد تمرین‌های متصل.
