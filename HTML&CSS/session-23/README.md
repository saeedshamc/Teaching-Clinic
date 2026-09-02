# جلسه ۲۳: Flexbox پیشرفته — Item Properties و الگوها (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- **flex-grow**، **flex-shrink**، **flex-basis** و shorthand **flex** را بکار ببرید
- **align-self**، **order** را تنظیم کنید
- الگوهای navbar، card row، holy grail را بسازید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور flex container | پرسش و پاسخ |
| 10–25 | flex-grow/shrink/basis | توزیع فضا |
| 25–40 | align-self و order | کنترل تک item |
| 40–55 | الگو: holy grail | سه ستونه |
| 55–70 | الگو: card footer | margin-top:auto |
| 70–85 | تمرین | gallery responsive |
| 85–90 | جمع‌بندی | تکلیف |

---

## 1. flex-grow

سهم رشد فضای اضافه — `flex: 1` == `1 1 0`

---

## 2. flex-shrink

کوچک شدن در overflow

---

## 3. flex-basis

اندازه اولیه قبل از grow/shrink

---

## 4. flex shorthand

`flex: 1 1 auto` — grow shrink basis

---

## 5. align-self

override align-items برای یک item

---

## 6. order

ترتیب visual — بدون تغییر DOM accessibility مراقب

---

## 7. patterns

navbar: space-between. sidebar: flex 0 0 250px. main: flex 1.

---

## 8. holy grail

flex column + row — header footer fixed main grow.

---

## اشتباهات رایج

| اشتباه | درست |
|--------|------|
| flex:1 روی همه بدون basis | basis صریح |
| order برای mobile content | DOM order first |
| min-width:0 forgot | overflow flex child |

---

## بهترین روش‌ها

- flex:1 for main
- flex-shrink:0 sidebar
- min-width:0 truncate

---



## ۹. flex shorthand

```css
.item { flex: 1; }           /* flex: 1 1 0% */
.item { flex: 0 0 200px; }   /* ثابت 200px */
.item { flex: 2 1 auto; }    /* دو برابر grow نسبت به flex:1 */
```

| بخش | معنی |
|-----|------|
| grow | چقدر فضای اضافه بگیرد |
| shrink | چقدر جمع شود |
| basis | اندازه اولیه |

---

## ۱۰. align-self

```css
.container { display: flex; align-items: flex-start; }
.tall { align-self: stretch; }
.short { align-self: center; }
```

override `align-items` برای یک item.

---

## ۱۱. order

```css
.first-visual { order: 2; }
.second-visual { order: 1; }
```
فقط visual — ترتیب DOM و accessibility تغییر نمی‌کند. با احتیاط.

---

## ۱۲. الگو: Holy Grail Layout

```css
body { display: flex; flex-direction: column; min-height: 100vh; }
main { display: flex; flex: 1; }
.sidebar { flex: 0 0 200px; }
.content { flex: 1; }
```

---

## ۱۳. الگو: Card با footer پایین

```css
.card {
    display: flex;
    flex-direction: column;
    min-height: 280px;
}
.card-footer { margin-top: auto; }
```

---

## ۱۴. flex vs grid — کی کدام؟

| Flexbox | Grid |
|---------|------|
| یک بعد (row یا column) | دو بعد |
| محتوا اندازه می‌دهد | ساختار از قبل |
| navbar، toolbar | صفحه، گالری |

---

## ۱۵. تمرین: گالری responsive

کارت‌ها `flex: 1 1 250px` در container با `flex-wrap: wrap` و `gap`.

---

## تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_flex_grow.html`
- `02_flex_basis.html`
- `03_align_self.html`
- `04_navbar_pattern.html`

تمرین‌ها: `exercises/question.md`

---

## جمع‌بندی

مفاهیم کلیدی جلسه 23 را مرور کنید و مثال‌ها را در مرورگر اجرا کنید.

---

## 🔜 جلسه بعد

**جلسه ۲۴: CSS Grid پایه** — ستون‌ها، ردیف‌ها و gap

---

## چک‌لیست پایان جلسه

- [ ] `flex: 1` و `flex: 0 0 200px` را تفاوت می‌دهم
- [ ] `margin-top: auto` footer کارت را پایین می‌چسباند
- [ ] الگوی navbar را بدون float پیاده کرده‌ام


## منابع تکمیلی

- [MDN CSS](https://developer.mozilla.org/fa/docs/Web/CSS)
- DevTools → Elements → Styles
- فایل‌های `examples/` این جلسه
