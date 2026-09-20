# جلسه ۰۷: Route Handlers (۹۰ دقیقه)

## پیش‌نیاز
جلسات ۰۳ و ۰۶

## اهداف یادگیری
1. `app/api/.../route.ts` بسازید.
2. متدهای `GET`/`POST` را export کنید.
3. بگویید تفاوت Handler با Server Action مفهومی چیست (در حد ورود).

## چرا مهم است
برای وب‌هوک، JSON API سبک، و endpointهای داخلی هم‌ریپو مفید است.

## مفهوم اصلی
```ts
// app/api/health/route.ts
import { NextResponse } from "next/server";
export function GET() {
  return NextResponse.json({ ok: true });
}
```

## اشتباهات رایج
1. گذاشتن منطق سنگین بدون اعتبارسنجی.
2. CORS را بی‌دلیل باز کردن.
3. قاطی کردن با pages/api قدیمی بدون دانستن تفاوت.

## تمرین
### آسان
`GET /api/health`
### چالش
`POST /api/echo` که JSON بدنه را برمی‌گرداند.

## خودآزمایی
- [ ] route.ts بلدم
- [ ] NextResponse بلدم
- [ ] متدها export می‌شوند
- [ ] برای UI داخلی گاهی action بهتر است
- [ ] اعتبارسنجی ورودی را فراموش نمی‌کنم

## جمع‌بندی + پل
API داخلی. جلسهٔ بعد Metadata و SEO.
