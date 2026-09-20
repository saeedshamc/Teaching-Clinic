# جلسه 9: مقدمه CSS (2 ساعت)

## اهداف یادگیری
- فهم نقش CSS در جدا کردن ظاهر از ساختار
- سه روش افزودن استایل: inline، internal، external
- مفهوم خیلی اولیهٔ cascade (آبشاری بودن)

---

## 1. CSS چیست؟

Cascading Style Sheets زبان توصیف **ظاهر** است: رنگ، فاصله، فونت، چیدمان.

```html
<link rel="stylesheet" href="style.css">
```

این روش (external) برای پروژه واقعی استاندارد است.

---

## 2. سه روش استایل

```html
<!-- inline -->
<p style="color: red">متن</p>

<!-- internal در head -->
<style>p { color: blue; }</style>

<!-- external -->
<link rel="stylesheet" href="style.css">
```

در شرایط برابرِ specificity، معمولاً قانون **آخر** برنده است؛ inline خیلی قوی است و نگهداری‌اش سخت.

---

## 3. قانون ساده

```css
selector {
  property: value;
}
```

مثال:

```css
.lead {
  color: #64748b;
  font-size: 1.05rem;
}
```

---

## اشتباهات رایج
- پخش کردن inline در کل پروژه
- فراموش کردن لینک `style.css`
- انتظار داشتن که CSS بدون ذخیره/رفرش عوض شود

---

## تمرین‌ها
### آسان
رنگ عنوان را فقط از `style.css` عوض کنید.
### متوسط
یک کلاس `.highlight` بسازید و به یک پاراگراف بدهید.
### چالش
همان کلاس را با یک قانون element مقایسه کنید و ببینید کدام برنده می‌شود.

## گام بعد
[جلسه 10: سلکتورهای پایه](../session-10/index.html)
