# جلسه ۰۹: تصاویر، فونت و Assetها (۷۵ دقیقه)

## پیش‌نیاز
جلسه ۰۵

## اهداف یادگیری
1. از `next/image` به‌جای `<img>` خام استفاده کنید (با درک نیاز).
2. فونت را با `next/font` بهینه کنید.
3. فایل‌های `public/` را سرو کنید.

## چرا مهم است
بهینه‌سازی تصویر/فونت روی LCP اثر مستقیم دارد.

## مفهوم اصلی
```tsx
import Image from "next/image";
import { Inter } from "next/font/google";
const inter = Inter({ subsets: ["latin"] });
```

برای فارسی اغلب فونت محلی یا ارائه‌دهندهٔ پشتیبانی‌کننده؛ الگو همان next/font است.

## اشتباهات رایج
1. نادیده گرفتن width/height یا fill.
2. دامنهٔ تصویر خارجی بدون config.
3. گذاشتن asset سنگین در import بدون نیاز.

## تمرین
### آسان
یک تصویر در public با Image.
### چالش
فونت را به layout اعمال کنید.

## خودآزمایی
- [ ] next/image بلدم
- [ ] public/ را می‌شناسم
- [ ] next/font بلدم
- [ ] دامنهٔ remote را config می‌کنم
- [ ] LCP را شنیده‌ام

## جمع‌بندی + پل
Assetها. جلسهٔ بعد Middleware.
