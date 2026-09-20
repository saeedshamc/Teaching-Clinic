# جلسه 86: الگوهای Layout — navbar، hero، sidebar (2 ساعت)

## اهداف یادگیری
- سرهم کردن الگوهای رایج صفحه با utility
- Navbar ریسپانسیو
- Hero ساده
- Sidebar + محتوا با Grid/Flex

---

## 1. Navbar

```html
<header class="flex items-center justify-between px-4 py-3 border-b bg-white">
  <a class="font-bold" href="#">Brand</a>
  <nav class="hidden md:flex gap-4 text-sm">...</nav>
</header>
```

منوی موبایل را می‌توانید با JS ساده `hidden` را toggle کنید (تمرین).

---

## 2. Hero

```html
<section class="px-4 py-16 text-center bg-slate-900 text-white">
  <h1 class="text-3xl font-bold md:text-5xl">عنوان قهرمان</h1>
  <p class="mx-auto mt-4 max-w-xl text-slate-300">یک جمله.</p>
  <a class="mt-6 inline-block rounded-lg bg-sky-500 px-5 py-2">CTA</a>
</section>
```

---

## 3. Sidebar layout

```html
<div class="min-h-screen md:flex">
  <aside class="w-full border-b md:w-56 md:border-b-0 md:border-l bg-white p-4">...</aside>
  <main class="flex-1 p-6">...</main>
</div>
```

در RTL، `border-l` روی aside ممکن است از نظر بصری «بین سایدبار و محتوا» باشد — با چشم چک کنید یا از `border-e` استفاده کنید.

---

## اشتباهات رایج
- Hero شلوغ با آمار و کارت زیاد در اولین viewport
- سایدبار fixed بدون padding روی main
- فراموش کردن نسخه موبایل منو

---

## تمرین‌ها
### آسان
به Hero یک دکمه outline اضافه کنید.
### متوسط
سه لینک در سایدبار با حالت active (`bg-sky-50 text-sky-700`).
### چالش
Toggle منوی موبایل با چند خط JS.

## گام بعد
[جلسه 87: @apply و لایه‌ها](../session-87/index.html)
