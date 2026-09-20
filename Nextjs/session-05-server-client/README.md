# جلسه ۰۵: Server vs Client Components (۱۲۰ دقیقه)

## پیش‌نیاز
جلسات ۰۱–۰۴ · React hooks

## اهداف یادگیری
1. پیش‌فرض App Router: Server Component.
2. چه وقت `"use client"` لازم است (رویداد، state، effect، API مرورگر).
3. مرز کلاینت را پایین نگه دارید (leaf clients).
4. secret سرور را در Client Component نگذارید.

## چرا مهم است
اشتباه رایج: همه فایل‌ها `"use client"` — فایدهٔ RSC از بین می‌رود.

## مفهوم اصلی
| نیاز | کجا |
|------|-----|
| fetch سرور، خواندن DB، نگه داشتن کلید | Server |
| onClick، useState، useEffect | Client |

Server می‌تواند Client را import کند؛ برعکسِ مستقیم با دادهٔ حساس ممنوع/بی‌معنی است.

## اشتباهات رایج
1. useState در فایل بدون use client.
2. پاس دادن تابع از server به client به‌عنوان prop (سریالایز نمی‌شود).
3. قرار دادن کل صفحه در client به‌خاطر یک دکمه.

## مثال
```tsx
// Counter.tsx
"use client";
import { useState } from "react";
export function Counter() {
  const [n, setN] = useState(0);
  return <button onClick={() => setN(n + 1)}>{n}</button>;
}
```
در Server Page فقط `<Counter />` را صدا بزنید.

## تمرین
### آسان
یک دکمهٔ کلاینت داخل page سرور.
### چالش
لیست را در server رندر کنید؛ فقط فیلتر جست‌وجو client باشد.

## خودآزمایی
- [ ] پیش‌فرض server است
- [ ] use client را محدود می‌کنم
- [ ] secret در client نمی‌گذارم
- [ ] مرز برگ را بلدم
- [ ] hook فقط در client

## جمع‌بندی + پل
مرز سرور/کلاینت. جلسهٔ بعد data fetching و caching ذهنی.
