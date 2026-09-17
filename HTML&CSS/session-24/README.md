# جلسه ۲۴: CSS Grid پایه — Columns، Rows، Gap (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- `display: grid` و **grid-template-columns/rows** بنویسید
- واحد **fr** و **repeat()** را بکار ببرید
- **gap** (row/column) تنظیم کنید
- flex vs grid — چه موقع کدام

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور flexbox | پرسش و پاسخ |
| 10–25 | display:grid | grid vs flex |
| 25–40 | columns و rows | fr unit |
| 40–55 | gap | فاصله یکنواخت |
| 55–70 | repeat و minmax | responsive grid |
| 70–85 | تمرین | گالری ۳ ستونه |
| 85–90 | جمع‌بندی | تکلیف |

---

## 1. display grid

```css
.grid { display: grid; grid-template-columns: 1fr 1fr 1fr; gap: 16px; }
```

---

## 2. fr unit

فraction فضای آزاد — `2fr 1fr`

---

## 3. repeat

`repeat(3, 1fr)` — `repeat(auto-fit, minmax(200px, 1fr))` preview

---

## 4. rows

grid-template-rows: auto 1fr auto — layout page

---

## 5. gap

gap: 16px — row-gap column-gap

---

## 6. grid vs flex

flex: یک بعد — grid: دو بعد

---

## 7. implicit grid

auto-placement extra items

---

## 8. devtools

Grid overlay در Firefox/Chrome

---

## اشتباهات رایج

| اشتباه | درست |
|--------|------|
| grid بدون columns | template explicit |
| px only columns | fr responsive |
| flex for 2d | grid for 2d |

---

## بهترین روش‌ها

- fr + minmax responsive
- gap not margin
- grid for page layout

---



## ۹. display: grid — شروع

```css
.container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-rows: auto 1fr auto;
    gap: 20px;
}
```

---

## ۱۰. واحد fr (fraction)

```css
grid-template-columns: 1fr 2fr 1fr; /* وسط دو برابر کناره */
grid-template-columns: 200px 1fr;   /* sidebar ثابت */
```

---

## ۱۱. repeat و minmax

```css
grid-template-columns: repeat(3, 1fr);
grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
```

`auto-fill`: ستون‌های خالی هم جا می‌گیرند.
`auto-fit`: ستون‌های خالی جمع می‌شوند.

---

## ۱۲. gap — row و column

```css
gap: 16px;              /* هر دو */
row-gap: 12px;
column-gap: 24px;
gap: 12px 24px;        /* row column */
```

---

## ۱۳. grid vs flex — تصمیم

- **Grid:** layout صفحه، گالری دو بعدی، areas
- **Flex:** نوار ابزار، لیست افقی، توزیع در یک خط

---

## ۱۴. implicit vs explicit tracks

```css
grid-template-columns: repeat(3, 1fr);
/* item چهارم به ردیف دوم implicit می‌رود */
grid-auto-rows: minmax(100px, auto);
```

---

## ۱۵. تمرین: گالری ۳×N

۶ کارت در grid با `repeat(3, 1fr)` و `gap: 1rem` — در موبایل `repeat(1, 1fr)` با media query.

---

## تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_grid_columns.html`
- `02_grid_rows.html`
- `03_fr_unit.html`
- `04_grid_gap.html`

تمرین‌ها: `exercises/question.md`

---

## جمع‌بندی

مفاهیم کلیدی جلسه 24 را مرور کنید و مثال‌ها را در مرورگر اجرا کنید.

---

## 🔜 جلسه بعد

**جلسه ۲۵: CSS Grid پیشرفته** — areas، alignment و nested grid

---

## چک‌لیست پایان جلسه

- [ ] `repeat(3, 1fr)` و `minmax(250px, 1fr)` را می‌نویسم
- [ ] تفاوت `gap` و margin بین سلول‌ها را می‌دانم
- [ ] گالری responsive با grid ساخته‌ام


## منابع تکمیلی

- [MDN CSS](https://developer.mozilla.org/fa/docs/Web/CSS)
- DevTools → Elements → Styles
- فایل‌های `examples/` این جلسه


## منابع تکمیلی

- [MDN CSS](https://developer.mozilla.org/fa/docs/Web/CSS)
- DevTools → Elements → Styles
- فایل‌های `examples/` این جلسه
