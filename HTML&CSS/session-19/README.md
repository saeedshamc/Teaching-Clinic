# جلسه ۱۹: Specificity، Cascade، Inheritance (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- **specificity** را محاسبه و مقایسه کنید
- **cascade** — ترتیب اعمال ruleها — را توضیح دهید
- **inheritance** — چه propertyهایی ارث می‌برند
- `inherit`، `initial`، `unset`، `revert` را بکار ببرید
- از `!important` فقط در موارد ضروری استفاده کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور display و box model | پرسش و پاسخ |
| 10–25 | Specificity | محاسبه امتیاز selector |
| 25–40 | Cascade | ترتیب منبع و !important |
| 40–55 | Inheritance | propertyهای inherited |
| 55–70 | Override عملی | DevTools — کدام rule برنده است |
| 70–85 | تمرین کلاسی | رفع تداخل استایل |
| 85–90 | جمع‌بندی | تکلیف |

---

## 1. Cascade

منبع: browser default → user → author. ترتیب در author: import → inline → id/class/element. آخرین rule برنده (specificity برابر).

---

## 2. Specificity

| selector | (a,b,c,d) |
| `#id` | (1,0,0,0) |
| `.class` | (0,1,0,0) |
| `element` | (0,0,0,1) |
| inline style | (1,0,0,0) style attribute |

```css
#nav .link { }  /* 0,1,1,0 */
p.intro { }     /* 0,0,1,1 */
```

---

## 3. Inheritance

ارث: color، font-family، line-height. **نه** margin، padding، border، width.

---

## 4. Keywords

```css
.child { color: inherit; }
.reset { all: unset; }
```

---

## 5. !important

```css
.override { color: red !important; }
```
فقط utility یا override library — نه habit.

---

## 6. Layer و order

ترتیب `<link>` — فایل بعد override.

---

## 7. DevTools

rule خط‌خورده = specificity یا order باخت.

---

## 8. معماری CSS

class-based، BEM، کم nesting — specificity پایین.

---

## اشتباهات رایج

| اشتباه | درست |
|--------|------|
| !important برای همه | refactor selector |
| #id برای هر استایل | class |
| selector خیلی طولانی | class component |
| inline style زیاد | external CSS |

---

## بهترین روش‌ها

- specificity پایین
- class component
- !important rare
- یک source truth

---



## ۹. محاسبه Specificity — گام‌به‌گام

```
#nav .item a:hover
(1, 2, 2) = 1 id + 2 class/pseudo + 2 element
```

**مقایسه:** از چپ به راست — id اول، بعد class، بعد element.

---

## ۱۰. Cascade — منابع استایل

1. User agent (پیش‌فرض مرورگر)
2. User (تنظیمات کاربر)
3. Author (CSS شما)
4. !important author
5. !important user

**ترتیب در یک منبع:** بعدی برنده — مگر specificity بالاتر.

---

## ۱۱. Inheritance — چه چیز به ارث می‌رسد؟

**Inherited:** color, font-family, font-size, line-height, text-align, ...
**Not inherited:** margin, padding, border, width, background, display, ...

```css
body { color: #333; font-family: Tahoma; }
/* همه descendants رنگ و فونت می‌گیرند مگر override */
```

### inherit و initial
```css
a { color: inherit; }
.reset { all: unset; }
```

---

## ۱۲. !important — کی استفاده کنیم؟

```css
.btn { background: blue !important; } /* اجتناب در پروژه */
```

فقط برای: override کتابخانه third-party، utility موقت در debug.
**بهتر:** specificity بالاتر با selector دقیق‌تر.

---

## ۱۳. @layer (مدرن)

```css
@layer reset, base, components, utilities;
@layer components {
    .btn { padding: 1rem; }
}
```
کنترل cascade بدون specificity war.

---

## ۱۴. DevTools — Computed Styles

1. Inspect element
2. تب Computed — مقدار نهایی
3. فلش کنار property → منبع rule

---

## ۱۵. تمرین: چه رنگی نمایش داده می‌شود؟

```css
p { color: blue; }
.intro { color: green; }
#lead { color: red; }
p.intro { color: orange; }
```
`<p id="lead" class="intro">` → **red** (id برنده)

---

## تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_specificity.html`
- `02_inheritance.html`
- `03_cascade_order.html`
- `04_important.html`

تمرین‌ها: `exercises/question.md`

---

## جمع‌بندی

مفاهیم کلیدی جلسه 19 را مرور کنید و مثال‌ها را در مرورگر اجرا کنید.

---

## 🔜 جلسه بعد

**جلسه ۲۰: Display**
