# راه‌اندازی محیط CSS-UI

## مرورگر
Chrome، Edge، Firefox یا Safari به‌روز. DevTools با F12: Elements، Styles، Computed، Network.

صفحات جلسه را با Live Server در VS Code یا این دستور باز کنید:

```bash
npx --yes serve .
```

## Node.js
از https://nodejs.org نسخهٔ **LTS** نصب کنید (برای Sass، Vite و Tailwind لازم است).

```bash
node -v
npm -v
```

## Sass (جلسات ۴۹–۶۲)
```bash
npm install -g sass
# یا محلی:
npm install -D sass
npx sass main.scss style.css --watch
```

## Bootstrap (جلسات ۶۳–۷۸)
دو مسیر:
- CDN در HTML (شروع سریع)
- npm + سفارشی‌سازی Sass (جلسه ۷۶)

## Tailwind (جلسات ۷۹–۹۴)
پیشنهاد دوره: Vite + Tailwind. نمونه در `session-79/vite.sample.md`.

## Bulma (جلسات ۹۵–۹۹)
CDN برای شروع؛ برای سفارشی‌سازی از Sass variables استفاده کنید (جلسه ۹۹).

## ویرایشگر
VS Code + افزونه‌های مفید: Live Server، و در صورت تمایل Stylelint.
