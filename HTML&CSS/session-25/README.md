# جلسه ۲۵: CSS Grid پیشرفته — Areas، Alignment، Nested (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- **grid-template-areas** برای layout نام‌گذاری‌شده
- **justify-items**، **align-items**، **place-items** را بکار ببرید
- **nested grid** بسازید
- responsive grid با **auto-fit/minmax**

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور grid basics | پرسش و پاسخ |
| 10–25 | grid-template-areas | layout نام‌گذاری |
| 25–40 | justify/align items | تراز داخل سلول |
| 40–55 | nested grid | grid داخل grid |
| 55–70 | auto-fit/minmax | responsive بدون media |
| 70–85 | تمرین | صفحه کامل header/sidebar |
| 85–90 | جمع‌بندی | تکلیف |

---

## 1. grid-template-areas

```css
grid-template-areas:
  "header header"
  "sidebar main"
  "footer footer";
```

---

## 2. grid-area

item: grid-area: header;

---

## 3. alignment

justify-items, align-items, justify-content, align-content

---

## 4. place-items

shorthand align + justify items

---

## 5. nested grid

child display:grid — subgrid preview

---

## 6. responsive

`grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));`

---

## 7. full layout

header sidebar main footer یک grid

---

## 8. finish

پایان CSS layout core — JS frameworks بعدی.

---

## اشتباهات رایج

| اشتباه | درست |
|--------|------|
| areas نام ناهماهنگ | rectangular regions |
| nested بدون plan | sketch first |
| minmax too small | 250px+ cards |

---

## بهترین روش‌ها

- areas for readability
- auto-fit responsive
- gap consistent

---



## ۹. grid-template-areas — layout خوانا

```css
.layout {
    display: grid;
    grid-template-areas:
        "header header"
        "sidebar main"
        "footer footer";
    grid-template-columns: 200px 1fr;
    grid-template-rows: auto 1fr auto;
    min-height: 100vh;
}
header  { grid-area: header; }
.sidebar { grid-area: sidebar; }
main    { grid-area: main; }
footer  { grid-area: footer; }
```

---

## ۱۰. justify-items و align-items

```css
.grid {
    display: grid;
    justify-items: center;   /* افقی داخل سلول */
    align-items: center;     /* عمودی داخل سلول */
    place-items: center;     /* shorthand */
}
```

---

## ۱۱. justify-content و align-content

وقتی grid کوچک‌تر از container:
```css
.grid {
    justify-content: center;
    align-content: start;
}
```

---

## ۱۲. Nested Grid

```css
.parent { display: grid; grid-template-columns: 1fr 1fr; }
.child {
    display: grid;
    grid-template-columns: subgrid; /* مرورگرهای مدرن */
    /* یا: repeat(2, 1fr) */
}
```

---

## ۱۳. Responsive با auto-fit

```css
.gallery {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
    gap: 1.5rem;
}
```
بدون media query — ستون‌ها خودکار wrap.

---

## ۱۴. grid-area shorthand

```css
.item {
    grid-column: 1 / 3;  /* از خط 1 تا 3 */
    grid-row: 2 / 4;
}
/* یا: grid-area: 2 / 1 / 4 / 3; (row-start/col-start/row-end/col-end) */
```

---

## ۱۵. تمرین نهایی: صفحه کامل

header، sidebar، main، footer با `grid-template-areas` — responsive: sidebar زیر main در موبایل با تغییر areas.

---

## تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_grid_areas.html`
- `02_grid_alignment.html`
- `03_nested_grid.html`
- `04_responsive_grid.html`

تمرین‌ها: `exercises/question.md`

---

## جمع‌بندی

مفاهیم کلیدی جلسه 25 را مرور کنید و مثال‌ها را در مرورگر اجرا کنید.

---

## 🔜 جلسه بعد

**ادامه دوره — Responsive Design و Media Queries**
