# جلسه 90: Dark Mode در Tailwind (2 ساعت)

## اهداف یادگیری
- استراتژی `media` (ترجیح سیستم) در برابر `class`
- کلاس `dark:` روی utilityها
- سوییچ تم با افزودن `dark` روی `<html>`
- انتخاب رنگ با کنتراست کافی در هر دو حالت

---

## 1. استراتژی class (کنترل دستی)

```js
tailwind.config = {
  darkMode: "class",
};
```

```html
<html class="dark">
```

```html
<div class="bg-white text-slate-900 dark:bg-slate-900 dark:text-slate-100">
```

---

## 2. استراتژی media

بدون کلاس؛ بر اساس `prefers-color-scheme`. برای دموی سوییچ‌دار، `class` راحت‌تر است.

---

## 3. الگوی سوییچ

```js
document.documentElement.classList.toggle("dark");
localStorage.setItem("theme", ...);
```

ترجیح ذخیره‌شده را هنگام لود بخوانید تا چشمک نزند.

---

## اشتباهات رایج
- فقط پس‌زمینه تیره بدون عوض کردن رنگ متن
- کنتراست `text-slate-400` روی `bg-slate-800` برای متن اصلی
- فراموش کردن `darkMode: "class"` وقتی با کلاس سوییچ می‌کنید

---

## تمرین‌ها
### آسان
یک کارت با `dark:bg-slate-800`.
### متوسط
سوییچ تم با دکمه.
### چالش
تم را در `localStorage` نگه دارید.

## گام بعد
[جلسه 91: انیمیشن](../session-91/index.html)
