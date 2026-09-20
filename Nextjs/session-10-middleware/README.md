# جلسه ۱۰: Middleware مفهومی (۷۵ دقیقه)

## پیش‌نیاز
جلسات ۰۳ و ۰۷

## اهداف یادگیری
1. نقش `middleware.ts` در لبهٔ درخواست را بگویید.
2. matcher را برای محدود کردن اجرا تنظیم کنید.
3. کاربردهای رایج: redirect، هدر، چک نشست سبک.

## چرا مهم است
منطق زود هنگام قبل از رندر صفحه — مناسب گیت ساده، نه جایگزین کامل auth سنگین.

## مفهوم اصلی
```ts
import { NextResponse } from "next/server";
import type { NextRequest } from "next/server";

export function middleware(req: NextRequest) {
  if (req.nextUrl.pathname.startsWith("/admin")) {
    // تصمیم redirect مفهومی
  }
  return NextResponse.next();
}
export const config = { matcher: ["/admin/:path*"] };
```

## اشتباهات رایج
1. کار سنگین/DB در middleware.
2. matcher باز روی همه مسیرهای استاتیک.
3. فرض امنیت کامل فقط با middleware کلاینت‌ساید.

## تمرین
### آسان
لاگ مسیر در middleware (توسعه).
### چالش
redirect مهمان از `/dashboard` به `/login` با چک cookie مفهومی.

## خودآزمایی
- [ ] middleware کجاست
- [ ] matcher بلدم
- [ ] کار سنگین نمی‌گذارم
- [ ] فقط لایهٔ اول دفاع است
- [ ] NextResponse را بلدم

## جمع‌بندی + پل
لبهٔ درخواست. جلسهٔ بعد env و config.
