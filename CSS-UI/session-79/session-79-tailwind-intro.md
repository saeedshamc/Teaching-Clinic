# جلسه 79: مقدمه Tailwind CSS (2 ساعت)

## اهداف یادگیری
- فهم تفکر **utility-first**
- تفاوت ذهنی با Bootstrap (کامپوننت آماده در برابر کلاس‌های ریز)
- راه‌اندازی واقعی با Vite (فایل `vite.sample.md`)
- کار با CDN آموزشی برای تمرین بدون بیلد

---

## 1. Utility-first یعنی چه؟

به‌جای نوشتن:

```css
.hero-title { font-size: 3rem; font-weight: 700; color: #0f172a; }
```

در HTML می‌نویسید:

```html
<h1 class="text-5xl font-bold text-slate-900">عنوان</h1>
```

هر کلاس یک کار کوچک می‌کند: رنگ، فاصله، عرض، نمایش، ...

مزیت: سرعت پروتوتایپ، یکنواختی مقیاس فاصله/رنگ، کمتر CSS مرده.

---

## 2. مقایسه با Bootstrap (بدون قاطی کردن)

| | Bootstrap | Tailwind |
|--|-----------|----------|
| ذهنیت | کامپوننت + کمی utility | تقریباً فقط utility |
| دکمه | `btn btn-primary` | `bg-sky-500 text-white px-4 py-2 rounded-lg` |
| سفارشی‌سازی | متغیر Sass | `tailwind.config` / `@theme` |
| JS داخلی | زیاد (Modal و ...) | ندارد (خودتان می‌نویسید) |

در این بلوک (۷۹–۹۴) کلاس Bootstrap نگذارید.

---

## 3. دو مسیر اجرا

### الف) CDN آموزشی (همین جلسات)
برای باز شدن فایل بدون npm:

```html
<script src="https://cdn.tailwindcss.com"></script>
```

مناسب یادگیری است؛ برای محصول نهایی توصیه نمی‌شود.

### ب) Vite + Tailwind (مسیر حرفه‌ای)
جزئیات در [`vite.sample.md`](./vite.sample.md).

---

## 4. اولین بلوک

```html
<section class="mx-auto max-w-3xl px-4 py-16">
  <h1 class="text-3xl font-bold text-slate-900 md:text-5xl">Utility-first</h1>
  <p class="mt-4 text-slate-600">کلاس‌های کوچک به‌جای CSS پراکنده.</p>
  <button type="button" class="mt-6 rounded-lg bg-sky-500 px-5 py-2 text-white hover:bg-sky-600">
    شروع
  </button>
</section>
```

---

## اشتباهات رایج
- مخلوط کردن `btn btn-primary` با کلاس‌های Tailwind در یک پروژهٔ تمرینی
- فکر کردن که CDN برای پروداکشن کافی است
- نوشتن CSS سفارشی زیاد قبل از یادگیری utilityها

---

## تمرین‌ها
### آسان
رنگ دکمه را به `bg-emerald-500` تغییر دهید.
### متوسط
یک کارت با `bg-white shadow rounded-xl p-6` بسازید.
### چالش
با Vite طبق `vite.sample.md` یک پروژه خالی راه بیندازید.

## خلاصه
Tailwind زبان فاصله و رنگ است روی HTML. از Bootstrap جدا یاد بگیرید.

## گام بعد
[جلسه 80: Spacing و Sizing](../session-80/index.html)
