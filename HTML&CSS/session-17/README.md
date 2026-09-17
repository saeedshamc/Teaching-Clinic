# جلسه ۱۷: رنگ، پس‌زمینه و واحدها — px، %، em، rem (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- رنگ با **hex**، **rgb/rgba**، **hsl/hsla** بنویسید
- **background-color**، **background-image**، **gradient** بکار ببرید
- واحدهای **px**، **%**، **em**، **rem** را مقایسه کنید
- متغیر CSS در `:root` تعریف کنید
- واحد مناسب برای font-size و layout انتخاب کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور box model | پرسش و پاسخ |
| 10–25 | فرمت‌های رنگ | hex، rgb، hsl |
| 25–40 | پس‌زمینه و gradient | background-* |
| 40–55 | واحدهای px و % | layout نسبی |
| 55–70 | em، rem و متغیر CSS | typography scale |
| 70–85 | تمرین کلاسی | hero + theme variables |
| 85–90 | جمع‌بندی | تکلیف |

---

## 1. رنگ در CSS

```css
color: #3498db;
color: rgb(52, 152, 219);
color: rgba(52, 152, 219, 0.8);
color: hsl(204, 70%, 53%);
```

| فرمت | مثال | کاربرد |
|------|------|--------|
| hex | `#fff`، `#3498db` | رایج، compact |
| rgb | `rgb(255,0,0)` | شفافیت با rgba |
| hsl | `hsl(0,100%,50%)` | تنظیم hue/saturation |

---

## 2. پس‌زمینه

```css
.hero {
    background-color: #1a252f;
    background-image: url("bg.jpg");
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
}

.gradient {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}
```

---

## 3. واحد px

**px** — پیکسل فیزیکی (نسبی به density). ثابت برای border، shadow.

```css
border: 1px solid #ccc;
box-shadow: 0 2px 4px rgba(0,0,0,0.1);
```

---

## 4. واحد %

نسبت به **والد**. width: 50% = نصف عرض parent.

```css
.container { width: 100%; max-width: 960px; }
.col { width: 33.333%; }
```

---

## 5. em و rem

**em** — نسبت به font-size **عنصر** (یا والد برای font-size)
**rem** — نسبت به font-size **root** (`html`)

```css
html { font-size: 16px; }
body { font-size: 1rem; }    /* 16px */
h1 { font-size: 2rem; }      /* 32px */
.card { padding: 1.5em; }     /* نسبت به font-size card */
```

**توصیه:** font-size با rem — predictable scaling.

---

## 6. متغیر CSS

```css
:root {
    --primary: #3498db;
    --spacing: 1rem;
}
.btn {
    background: var(--primary);
    padding: var(--spacing);
}
```

---

## 7. DevTools

Color picker در Styles — hex/rgb/hsl. Computed values را ببینید.

---

## 8. تمرین عملی

صفحه hero با gradient، دکمه با var(--primary)، typography با rem.

---

## اشتباهات رایج

| اشتباه | درست |
|--------|------|
| px برای همه font-size | rem برای typography |
| % بدون width والد | parent باید width داشته باشد |
| em تو در تو برای font | rem برای font-size |
| رنگ نام‌دار فقط | hex/hsl برای control |

---

## بهترین روش‌ها

- rem برای font-size
- متغیر CSS برای theme
- gradient ملایم برای hero
- contrast کافی متن/پس‌زمینه

---



## ۹. فرمت‌های رنگ — جزئیات بیشتر

### Hex
```css
#RGB      /* کوتاه: #f00 = #ff0000 */
#RRGGBB   /* کامل: #ff0000 */
#RRGGBBAA /* با آلفا: #ff000080 */
```

### RGB و RGBA
```css
rgb(255, 87, 51);
rgba(255, 87, 51, 0.5); /* 50% شفاف */
```

### HSL — Hue, Saturation, Lightness
```css
hsl(9, 100%, 60%);      /* نارنجی */
hsla(9, 100%, 60%, 0.8);
```
**مزیت HSL:** تغییر روشنایی بدون تغییر hue — `hsl(204, 70%, 40%)` تیره‌تر از `53%`.

### رنگ‌های نام‌دار (Named Colors)
`red`، `navy`، `transparent` — فقط برای پروtotype یا موارد ساده.

---

## ۱۰. پس‌زمینه — shorthand و چند لایه

```css
/* shorthand: color image position/size repeat attachment */
background: #eee url("pattern.png") center/cover no-repeat;

/* چند پس‌زمینه */
background:
    linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)),
    url("hero.jpg") center/cover;
```

| property | مقادیر رایج |
|----------|-------------|
| `background-size` | `cover`، `contain`، `100% auto` |
| `background-position` | `center`، `top right`، `50% 50%` |
| `background-attachment` | `scroll`، `fixed` (parallax) |

### گرادیان خطی و شعاعی
```css
linear-gradient(to bottom, #667eea, #764ba2);
linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
radial-gradient(circle at center, #fff, #ddd);
```

---

## ۱۱. واحدها — vw، vh، ch

| واحد | نسبت به | کاربرد |
|------|---------|--------|
| `vw` | 1% عرض viewport | full-width sections |
| `vh` | 1% ارتفاع viewport | hero height |
| `ch` | عرض کاراکتر "0" | عرض input متن |

```css
.hero { min-height: 80vh; }
.container { width: min(90vw, 1200px); }
```

---

## ۱۲. مقایسه em و rem — مثال عملی

```css
html { font-size: 16px; }
.parent { font-size: 20px; }
.child-em { font-size: 1.5em; }   /* 30px — نسبت به parent */
.child-rem { font-size: 1.5rem; } /* 24px — نسبت به html */
```

**قانون thumb:** `rem` برای font-size؛ `em` برای padding/margin نسبت به همان عنصر.

---

## ۱۳. متغیرهای CSS — تم روشن/تاریک

```css
:root {
    --bg: #ffffff;
    --text: #1a1a1a;
    --accent: #2980b9;
}
@media (prefers-color-scheme: dark) {
    :root {
        --bg: #1a1a1a;
        --text: #f0f0f0;
    }
}
body {
    background: var(--bg);
    color: var(--text);
}
```

---

## ۱۴. دسترسی‌پذیری رنگ (Contrast)

- نسبت کنتراست متن معمولی: حداقل **4.5:1** (WCAG AA)
- ابزار: Chrome DevTools → CSS Overview یا WebAIM Contrast Checker
- فقط رنگ برای انتقال معنا کافی نیست — آیکون یا متن هم اضافه کنید

---

## ۱۵. تمرین کلاسی پیشنهادی

1. کارت محصول با `background: linear-gradient` و `border-radius`
2. دکمه با `var(--primary)` و hover با `hsl` تیره‌تر
3. تایپوگرافی صفحه فقط با `rem` (بدون px برای font)

---

## ۱۶. چک‌لیست قبل از جلسه بعد

- [ ] تفاوت hex و hsl را توضیح می‌دهم
- [ ] `background-size: cover` vs `contain` را می‌دانم
- [ ] rem برای font-size استفاده می‌کنم
- [ ] متغیر CSS در `:root` تعریف کرده‌ام

---

## تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_colors.html`
- `02_backgrounds.html`
- `03_units.html`
- `04_css_variables.html + 04_vars.css`

تمرین‌ها: `exercises/question.md`

---

## جمع‌بندی

مفاهیم کلیدی جلسه 17 را مرور کنید و مثال‌ها را در مرورگر اجرا کنید.

---

## 🔜 جلسه بعد

**جلسه ۱۸: تایپوگرافی**
