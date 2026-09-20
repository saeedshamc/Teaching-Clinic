# جلسه 46: RTL و Logical Properties (2 ساعت)

## اهداف یادگیری
- تفاوت ویژگی فیزیکی (`margin-left`) و منطقی (`margin-inline-start`)
- ساخت UI که با `dir="rtl"` و `dir="ltr"` درست بماند
- استفاده از `inset`، `padding-block`، `border-inline-start`

---

## 1. مشکل ویژگی فیزیکی

```css
.card { margin-left: 1rem; border-left: 4px solid blue; }
```

در LTR درست است؛ در RTL «سمت شروع» عوض می‌شود و حاشیه جای غلط می‌رود.

---

## 2. معادل منطقی

| فیزیکی (LTR ذهنی) | منطقی |
|-------------------|--------|
| margin-left / right | margin-inline-start / end |
| padding-top / bottom | padding-block-start / end |
| left / right / top / bottom | inset-inline / inset-block |
| border-left | border-inline-start |

```css
.card {
  padding-block: 1rem;
  padding-inline: 1.5rem;
  margin-inline-start: 1rem;
  border-inline-start: 4px solid #7c3aed;
}
```

با `dir="rtl"` روی `<html>`، «شروع» سمت راست است.

---

## 3. inset

```css
.badge {
  position: absolute;
  inset-block-start: 0.5rem;
  inset-inline-end: 0.5rem;
}
```

---

## اشتباهات رایج
- مخلوط کردن left فیزیکی با inline منطقی در یک کامپوننت
- فرض کردن که RTL فقط `direction` است و فاصله‌ها خودشان درست می‌شوند

---

## تمرین‌ها
### آسان
یک کارت فقط با logical properties فاصله‌دهی کنید.
### متوسط
`dir` صفحه را موقتاً `ltr` کنید و ببینید کارت درست می‌ماند.
### چالش
یک badge گوشه‌ای با `inset-inline-end` بسازید.

## خلاصه
Logical properties زبان جهت‌خنثی برای UI چندجهته‌اند.

## گام بعد
[جلسه 47: DevTools](../session-47/index.html)
