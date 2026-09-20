# جلسه 21: Set، Map و Date (2 ساعت)

## 🎯 اهداف یادگیری
- `Set` را برای یکتایی و `Map` را برای کلید غیررشته استفاده کنید
- تاریخ را با `Date` و نمایش را با `Intl` جدا کنید
- حلقه روی این ساختارها را بنویسید

---

## 1. Set

```javascript
const tags = new Set(["js", "js", "dom"]);
tags.size; // 2
tags.has("js");
tags.add("css");
[...tags];
```

برای حذف تکراری از آرایه: `[...new Set(arr)]`.

---

## 2. Map

```javascript
const scores = new Map();
scores.set("علی", 18);
scores.get("علی");
```

کلید object هم مجاز است. `{}` به‌عنوان کلید object معمولی به رشته `"[object Object]"` تبدیل می‌شود — برای دیکشنری واقعی Map بهتر است.

`WeakMap` / `WeakSet` را فقط در حد نام بشناسید: کلید object، بدون جلوگیری از GC.

---

## 3. Date و Intl

```javascript
const now = new Date();
now.toISOString();
new Intl.DateTimeFormat("fa-IR", {
    dateStyle: "medium",
    timeStyle: "short"
}).format(now);

new Intl.NumberFormat("fa-IR").format(1250000);
```

برای محاسبات، timestamp (`Date.now()`)؛ برای نمایش، Intl. کتابخانهٔ تاریخ را وقتی تقویم پیچیده شد اضافه کنید.

---

## 4. تمرین‌ها

### تمرین 1
تکراری‌های یک آرایهٔ تگ را با Set بردارید.

### تمرین 2
Map از نام به نمره؛ چاپ همه با `for...of`.

### تمرین 3 (چالش)
تاریخ امروز را با `fa-IR` قالب کنید.

---

## 5. نکات مهم

1. ✅ Set برای عضویت سریع
2. ✅ نمایش را از مقدار خام جدا کنید
3. ❌ `==` روی Date — از getTime استفاده کنید

---

## 6. خلاصه جلسه

- Set / Map
- Date + Intl
- آماده برای ابزار روزمره

**آماده برای جلسه بعد: DevTools و npm! 🚀**
