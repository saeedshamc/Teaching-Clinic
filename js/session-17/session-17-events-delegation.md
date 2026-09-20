# جلسه 17: رویدادها و Event Delegation (2 ساعت)

## 🎯 اهداف یادگیری
- `addEventListener` را به‌جای onclick در HTML ترجیح دهید
- `event.target` و `currentTarget` را تفکیک کنید
- `preventDefault` و `stopPropagation` را به‌جا استفاده کنید
- delegation روی والد برای لیست پویا بنویسید

---

## 1. مرور جلسه 7

کلیک و todo ساده را دیدید. مشکل: اگر روی هر آیتم listener بگذارید، آیتم جدید listener ندارد مگر دوباره bind کنید.

---

## 2. جریان رویداد

کلیک از هدف به بالا می‌رود (bubbling). یک listener روی `ul` همهٔ کلیک‌های `li` را می‌گیرد.

```javascript
list.addEventListener("click", (event) => {
    const btn = event.target.closest("button[data-action='delete']");
    if (!btn || !list.contains(btn)) return;
    const id = btn.dataset.id;
    // حذف از آرایه و render
});
```

`closest` عنصری را پیدا می‌کند که با selector جور باشد، حتی اگر روی متن داخل دکمه کلیک شده باشد.

---

## 3. preventDefault

```javascript
form.addEventListener("submit", (e) => {
    e.preventDefault(); // جلوگیری از رفرش صفحه
});

link.addEventListener("click", (e) => {
    e.preventDefault();
});
```

`stopPropagation` بالا رفتن رویداد را قطع می‌کند؛ فقط وقتی تداخل واقعی دارید استفاده کنید.

---

## 4. once و remove

```javascript
btn.addEventListener("click", handler, { once: true });
btn.removeEventListener("click", handler); // همان مرجع تابع
```

---

## 5. تمرین‌ها

### تمرین 1
یک دکمه با addEventListener (نه onclick در HTML).

### تمرین 2
delegation: کلیک روی آیتم‌های لیست، id را در output نشان دهد.

### تمرین 3 (چالش)
لیست پویا با یک listener روی والد برای حذف.

---

## 6. نکات مهم

1. ✅ یک listener روی ظرف، نه صدها روی فرزند
2. ✅ `data-*` برای شناسه
3. ❌ `stopPropagation` را عادت نکنید

---

## 7. خلاصه جلسه

- bubbling
- delegation + closest
- preventDefault برای فرم
- آماده برای جلسهٔ فرم‌ها

**آماده برای جلسه بعد: فرم‌ها و FormData! 🚀**
