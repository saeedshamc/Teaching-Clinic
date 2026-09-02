# جلسه ۲۲: Flexbox پایه — Container Properties (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- `display: flex` و محور **main/cross** را بدانید
- **justify-content**، **align-items**، **flex-direction**، **flex-wrap**، **gap** بکار ببرید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور positioning | پرسش و پاسخ |
| 10–25 | display:flex و محورها | main/cross در RTL |
| 25–40 | justify-content و align-items | centering |
| 40–55 | flex-direction و wrap | responsive row |
| 55–70 | gap و navbar | live coding |
| 70–85 | تمرین کلاسی | flex toolbar |
| 85–90 | جمع‌بندی | تکلیف |

---

## 1. display flex

```css
.container { display: flex; }
```

---

## 2. flex-direction

row | row-reverse | column | column-reverse

---

## 3. justify-content

flex-start | center | space-between | space-around | space-evenly

---

## 4. align-items

stretch | flex-start | center | flex-end | baseline

---

## 5. flex-wrap

nowrap | wrap — responsive rows

---

## 6. gap

فاصله بین items — modern

---

## 7. main vs cross

row: main=horizontal در LTR — در RTL جهت معکوس.

---

## 8. when flex

navbar، row cards، centering، equal columns.

---

## اشتباهات رایج

| اشتباه | درست |
|--------|------|
| flex روی wrong element | parent container |
| width 100% child conflict | flex:1 |
| float + flex | یک روش layout |

---

## بهترین روش‌ها

- gap به جای margin hack
- flex-direction column mobile
- align-items center nav

---



## ۹. محورهای Flex — در RTL

```css
.container {
    display: flex;
    flex-direction: row; /* در RTL: main از راست به چپ */
}
```

| flex-direction | main axis | cross axis |
|----------------|-----------|------------|
| row | افقی | عمودی |
| column | عمودی | افقی |

---

## ۱۰. justify-content — توزیع در main axis

```css
.toolbar { display: flex; justify-content: space-between; }
.centered { justify-content: center; }
```

| مقدار | رفتار |
|-------|--------|
| flex-start | ابتدای main |
| center | وسط |
| space-between | فاصله بین، بدون کناره |
| space-evenly | فاصله یکسان همه جا |

---

## ۱۱. align-items — تراز در cross axis

```css
.cards {
    display: flex;
    align-items: stretch; /* پیش‌فرض — ارتفاع یکسان */
    align-items: center;    /* عمودی وسط */
}
```

---

## ۱۲. flex-wrap و responsive

```css
.gallery {
    display: flex;
    flex-wrap: wrap;
    gap: 16px;
}
.gallery > * { flex: 1 1 200px; }
```

---

## ۱۳. gap — جایگزین margin hack

```css
.nav { display: flex; gap: 1rem; }
/* دیگر li:last-child margin:0 لازم نیست */
```

---

## ۱۴. الگوی navbar

```css
.navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1rem 2rem;
}
.nav-links { display: flex; gap: 1.5rem; list-style: none; }
```

---

## ۱۵. تمرین کلاسی

نوار ابزار با logo راست، لینک‌ها وسط، دکمه چپ — فقط flex container properties.

---

## تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_flex_container.html`
- `02_justify_align.html`
- `03_flex_direction.html`
- `04_flex_wrap.html`

تمرین‌ها: `exercises/question.md`

---

## جمع‌بندی

مفاهیم کلیدی جلسه 22 را مرور کنید و مثال‌ها را در مرورگر اجرا کنید.

---

## 🔜 جلسه بعد

**جلسه ۲۳: Flexbox پیشرفته**
