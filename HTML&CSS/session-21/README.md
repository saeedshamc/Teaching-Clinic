# جلسه ۲۱: Positioning — static، relative، absolute، fixed، sticky (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- **position** و offsetها (`top`، `right`، `bottom`، `left`) را بکار ببرید
- **absolute** نسبت به ancestor positioned را درک کنید
- **fixed** header و **sticky** sidebar بسازید
- **z-index** و stacking context را مدیریت کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور display | پرسش و پاسخ |
| 10–25 | static و relative | offset و جریان سند |
| 25–40 | absolute | containing block |
| 40–55 | fixed و sticky | header ثابت، sidebar |
| 55–70 | z-index و لایه‌ها | stacking context |
| 70–85 | تمرین | کارت روی تصویر |
| 85–90 | جمع‌بندی | تکلیف |

---

## 1. static

پیش‌فرض — offset بی‌اثر.

---

## 2. relative

```css
.box { position: relative; top: 10px; left: 5px; }
``` جابجایی بدون خارج شدن از flow — فضای اصلی حفظ.

---

## 3. absolute

```css
.parent { position: relative; }
.badge { position: absolute; top: 0; left: 0; }
``` از flow خارج — نسبت به نزدیک‌ترین positioned ancestor.

---

## 4. fixed

```css
.header { position: fixed; top: 0; width: 100%; z-index: 100; }
``` نسبت به viewport.

---

## 5. sticky

```css
.sidebar { position: sticky; top: 80px; }
``` hybrid — تا scroll در container.

---

## 6. z-index

فقط روی positioned — stacking context.

---

## 7. use cases

badge، modal overlay، fixed nav، sticky TOC.

---

## 8. pitfalls

absolute بدون relative parent — body reference.

---

## اشتباهات رایج

| اشتباه | درست |
|--------|------|
| absolute بدون parent relative | parent position:relative |
| z-index بی‌نهایت | scale منطقی |
| fixed بدون padding body | padding-top برای header |

---

## بهترین روش‌ها

- relative برای anchor absolute
- fixed sparingly
- sticky برای nav

---



## ۹. position: static (پیش‌فرض)

جریان عادی سند — `top/left/right/bottom` و `z-index` بی‌اثر.

---

## ۱۰. position: relative

```css
.box { position: relative; top: 10px; left: 20px; }
```
جابه‌جایی نسبت به **جای اصلی** — فضای اصلی خالی می‌ماند.
**کاربرد:** offset جزئی، ایجاد containing block برای absolute فرزند.

---

## ۱۱. position: absolute

```css
.parent { position: relative; }
.badge {
    position: absolute;
    top: -8px;
    right: -8px;
}
```

نسبت به نزدیک‌ترین ancestor با position ≠ static.
از flow خارج — عناصر بعدی جای آن را نمی‌گیرند.

---

## ۱۲. position: fixed

```css
.header {
    position: fixed;
    top: 0; left: 0; right: 0;
    z-index: 100;
}
```
نسبت به **viewport** — هنگام scroll ثابت می‌ماند.
`body { padding-top: 64px; }` برای جبران ارتفاع header.

---

## ۱۳. position: sticky

```css
thead th {
    position: sticky;
    top: 0;
    background: white;
}
```
بین relative و fixed — تا رسیدن به threshold می‌چسبد.

---

## ۱۴. z-index و stacking context

```css
.modal { z-index: 1000; }
.dropdown { z-index: 100; }
```
فقط داخل همان stacking context مقایسه می‌شود.
`position` + `z-index` یا `opacity < 1` یا `transform` context جدید می‌سازد.

---

## ۱۵. تمرین: کارت با badge

کارت `position: relative`، badge قرمز `absolute` گوشه بالا-چپ (در RTL).

---

## تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_relative.html`
- `02_absolute.html`
- `03_fixed_header.html`
- `04_sticky.html`

تمرین‌ها: `exercises/question.md`

---

## جمع‌بندی

مفاهیم کلیدی جلسه 21 را مرور کنید و مثال‌ها را در مرورگر اجرا کنید.

---

## 🔜 جلسه بعد

**جلسه ۲۲: Flexbox پایه**
