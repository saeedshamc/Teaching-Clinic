# جلسه ۰۳: File-based Routing در App Router (۹۰ دقیقه)

## پیش‌نیاز
جلسه ۰۲

## اهداف یادگیری
1. با ساخت پوشه زیر `app/` مسیر بسازید.
2. Dynamic segment مثل `[id]` را بفهمید.
3. Route group با `(marketing)` را در حد مفهوم بشناسید.

## چرا مهم است
در Next مسیر = ساختار فایل. اشتباه نام‌گذاری = 404.

## مفهوم اصلی
- `app/page.tsx` → `/`
- `app/about/page.tsx` → `/about`
- `app/blog/[slug]/page.tsx` → `/blog/:slug`
- `(group)` روی URL اثر نمی‌گذارد؛ برای سازماندهی layout است.

## اشتباهات رایج
1. فراموش فایل `page.tsx` (فقط پوشه کافی نیست).
2. قاطی کردن `[id]` با `[[...slug]]`.
3. گذاشتن کامپوننت بدون page در مسیر و انتظار URL.

## مثال
```tsx
// app/blog/[slug]/page.tsx
export default function Post({ params }: { params: Promise<{ slug: string }> }) {
  // در Next 15 params ممکن است Promise باشد — await کنید
}
```
(طبق نسخهٔ نصب‌شده‌تان docs را چک کنید؛ الگو: خواندن slug و نمایش.)

## تمرین
### آسان
مسیر `/contact` بسازید.
### چالش
`/products/[id]` با نمایش id.

## خودآزمایی
- [ ] page.tsx اجباری است
- [ ] dynamic segment بلدم
- [ ] route group را می‌فهمم
- [ ] لینک با next/link می‌سازم
- [ ] 404 را بعداً با not-found وصل می‌کنم

## جمع‌بندی + پل
مسیرها ساخته شد. جلسهٔ بعد فایل‌های خاص layout/loading/error.
