# جلسه ۲۰: Display — block، inline، inline-block، none (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- **display: block/inline/inline-block/none** را تفکیک کنید
- width/height روی inline vs block را بدانید
- **visibility: hidden** vs **display: none** را مقایسه کنید
- inline-block برای دکمه و badge بکار ببرید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور specificity | پرسش و پاسخ |
| 10–25 | block و inline | رفتار پیش‌فرض تگ‌ها |
| 25–40 | inline-block | دکمه‌ها و badge |
| 40–55 | display: none vs visibility | تفاوت در layout |
| 55–70 | تمرین | نوار ابزار و تگ‌ها |
| 70–85 | معرفی flex preview | display:flex |
| 85–90 | جمع‌بندی | تکلیف |

---

## 1. block

```css
div, p, h1 { display: block; }
```
عرض کامل، line break بعد، width/height/margin عمودی.

---

## 2. inline

```css
span, a { display: inline; }
```
در خط، width/height نادیده، margin عمودی محدود.

---

## 3. inline-block

```css
.btn { display: inline-block; padding: 8px 16px; }
```
در خط + box model کامل.

---

## 4. none

```css
.hidden { display: none; }
```
از layout حذف — screen reader هم معمولاً skip.

---

## 5. visibility

```css
.invisible { visibility: hidden; }
```
جای خالی حفظ — متفاوت از none.

---

## 6. hidden attribute

```html
<div hidden>...</div>
```

---

## 7. تغییر display

`<a>` block برای nav full-width click area.

---

## 8. accessibility

display:none برای toggle — aria-hidden هم در نظر بگیرید.

---

## اشتباهات رایج

| اشتباه | درست |
|--------|------|
| width روی span inline | inline-block یا block |
| none برای SEO content | off-screen یا aria |
| br زیاد برای layout | display block |
| float برای layout | flex/grid |

---

## بهترین روش‌ها

- inline-block برای UI chip
- none برای JS toggle
- semantic HTML first

---



## ۹. جدول display — رفتار کامل

| display | عرض/ارتفاع | margin/padding عمودی | line break |
|---------|------------|---------------------|------------|
| block | قابل تنظیم | ✅ | قبل و بعد |
| inline | محتوا | فقط افقی | نه |
| inline-block | قابل تنظیم | ✅ | نه |
| none | — | — | از layout حذف |

---

## ۱۰. block — تگ‌های پیش‌فرض

`div`, `p`, `h1-h6`, `section`, `article`, `ul`, `li` (لیست block)

```css
p { display: block; width: 100%; margin: 1em 0; }
```

---

## ۱۱. inline — تگ‌های پیش‌فرض

`span`, `a`, `strong`, `em`, `img` (replaced inline)

```css
span { display: inline; }
/* width/height روی pure inline اثر ندارد */
```

---

## ۱۲. inline-block — بهترین هر دو دنیا

```css
.btn {
    display: inline-block;
    padding: 8px 16px;
    width: auto;
}
```

دکمه‌ها، badge، chip — کنار هم در یک خط + ابعاد قابل کنترل.

---

## ۱۳. display: none vs visibility: hidden

```css
.hidden-layout { display: none; }      /* جا نمی‌گیرد */
.hidden-visual { visibility: hidden; } /* جا می‌گیرد، دیده نمی‌شود */
```

برای accessibility منو: `aria-hidden` + focus management.

---

## ۱۴. display: flex و grid (پیش‌نمایش)

```css
.nav { display: flex; gap: 1rem; }
.gallery { display: grid; grid-template-columns: repeat(3, 1fr); }
```

جلسات ۲۲–۲۵ عمیق‌تر می‌شوند.

---

## ۱۵. تمرین: تبدیل لیست افقی

```html
<ul class="nav">
  <li><a href="#">خانه</a></li>
  <li><a href="#">درباره</a></li>
</ul>
```
```css
.nav { list-style: none; padding: 0; display: flex; gap: 1rem; }
.nav li { display: inline-block; }
```

---

## تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_block_inline.html`
- `02_inline_block.html`
- `03_display_none.html`
- `04_visibility.html`

تمرین‌ها: `exercises/question.md`

---

## جمع‌بندی

مفاهیم کلیدی جلسه 20 را مرور کنید و مثال‌ها را در مرورگر اجرا کنید.

---

## 🔜 جلسه بعد

**جلسه ۲۱: Positioning**
