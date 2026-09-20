# جلسه 21: Overflow و z-index (2 ساعت)

## اهداف یادگیری
- کنترل سرریز با `overflow` (`visible` / `hidden` / `auto` / `scroll`)
- درک `z-index` و stacking context
- دانستن اینکه `z-index` روی عنصر positioned اثر دارد

---

## 1. Overflow

```css
.clip {
  max-height: 4.5rem;
  overflow: auto; /* اسکرول داخلی وقتی محتوا زیاد است */
}
```

| مقدار | رفتار |
|--------|--------|
| visible | پیش‌فرض؛ بیرون می‌زند |
| hidden | بریده می‌شود |
| auto | در صورت نیاز اسکرول |
| scroll | معمولاً اسکرول‌بار همیشه |

---

## 2. z-index و لایه

```css
.card { position: absolute; }
.c1 { z-index: 1; }
.c2 { z-index: 2; } /* بالاتر */
```

بدون `position` غیر از `static`، `z-index` عملاً کار نمی‌کند. هر stacking context جدید (مثلاً `opacity < 1` یا `transform`) لایه را ایزوله می‌کند.

---

## اشتباهات رایج
- z-indexهای نجومی (`99999`) بدون نظم
- انتظار داشتن z-index روی عنصر static
- مخفی کردن overflow بدون دسترسی به محتوا

---

## تمرین‌ها
### آسان
جعبه با `overflow: auto` و متن بلند.
### متوسط
دو کارت روی هم با z-index.
### چالش
یک overlay ساده (پس‌زمینه نیمه‌شفاف + پنل بالا).

## گام بعد
[جلسه 22: Flexbox ظرف](../session-22/index.html)
