# جلسه 16: DOM پیشرفته (2 ساعت)

## 🎯 اهداف یادگیری
- عنصر بسازید، به درخت وصل کنید، حذف کنید
- تفاوت `textContent` و `innerHTML` را بگویید
- بین `parent` / `children` / `closest` جابه‌جا شوید
- لیست را از داده بازسازی کنید (الگوی بعداً در React)

---

## 1. جلسه 7 چه بود؟

انتخاب و تغییر محتوا را دیدید. اینجا **ساخت درخت** است.

```javascript
const li = document.createElement("li");
li.textContent = "آیتم جدید";
list.append(li);
```

`append` چند گره یا متن می‌گیرد. `appendChild` فقط یک Node.

---

## 2. text در برابر HTML

```javascript
el.textContent = "<b>سلام</b>"; // همان متن خام
el.innerHTML = "<b>سلام</b>";   // HTML تفسیر می‌شود
```

ورودی کاربر را در `innerHTML` نگذارید (XSS). برای متن: `textContent`.

---

## 3. پیمایش

```javascript
el.parentElement;
el.children;
el.closest("ul");
el.querySelector(".item");
```

`closest` برای event delegation جلسه بعد مهم است.

---

## 4. بازسازی از داده

```javascript
function render(items) {
    list.replaceChildren();
    for (const item of items) {
        const li = document.createElement("li");
        li.textContent = item;
        list.append(li);
    }
}
```

منبع حقیقت آرایه است؛ DOM فقط نمایش است. همین ذهنیت state در React است.

---

## 5. تمرین‌ها

### تمرین 1
با `createElement` یک `p` بسازید و به صفحه وصل کنید.

### تمرین 2
لیست را از آرایهٔ سه اسم render کنید.

### تمرین 3 (چالش)
دکمهٔ حذف روی هر آیتم بدون innerHTML رشته‌ای.

---

## 6. نکات مهم

1. ✅ منبع داده جدا از DOM
2. ✅ `textContent` برای متن
3. ❌ HTML را با چسباندن رشته از کاربر نسازید

---

## 7. خلاصه جلسه

- createElement / append / remove
- textContent در برابر innerHTML
- پیمایش و render از آرایه

**آماده برای جلسه بعد: رویدادها و delegation! 🚀**
