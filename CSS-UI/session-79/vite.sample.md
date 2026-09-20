# Vite + Tailwind (مسیر واقعی)

این فایل راهنمای نصب است؛ جلسات آموزشی با CDN باز می‌شوند تا بدون npm هم کار کنند.

## Tailwind v4 با Vite

```bash
npm create vite@latest demo-tw -- --template vanilla
cd demo-tw
npm install
npm install -D tailwindcss @tailwindcss/vite
```

در `vite.config.js`:

```js
import { defineConfig } from "vite";
import tailwindcss from "@tailwindcss/vite";

export default defineConfig({
  plugins: [tailwindcss()],
});
```

در CSS ورودی (مثلاً `src/style.css`):

```css
@import "tailwindcss";
```

سپس:

```bash
npm run dev
```

## نکته RTL
روی `<html lang="fa" dir="rtl">` بگذارید. بسیاری از utilityهای منطقی (`ms-`, `me-`, `start-`, `end-`) با RTL درست عمل می‌کنند.

## نسخه کلاسیک (v3)
اگر قالب قدیمی دیدید: `tailwind.config.js` + PostCSS + `npx tailwindcss init`. منطق utility-first یکی است.
