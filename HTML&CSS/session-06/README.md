# جلسه ۶: لینک‌ها و مسیرها (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- با `<a href="">` لینک بسازید
- تفاوت مسیر **مطلق (absolute)** و **نسبی (relative)** را بدانید
- لنگر درون‌صفحه با `id` و `#` بسازید
- `target`, `rel`, `title` را امن و درست استفاده کنید
- لینک mailto و tel بنویسید
- ساختار چندصفحه‌ای ساده طراحی کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | hyperlink چیست؟ |
| 10–30 | تگ a و href |
| 30–50 | مسیر مطلق vs نسبی |
| 50–65 | anchor، mailto، tel |
| 65–80 | target و rel |
| 80–90 | تمرین، تکلیف |

---

## ۱. Hyperlink

لینک **هاипرمتن** است — کلیک کاربر را به URL، فایل، بخش صفحه یا ایمیل می‌برد.

```html
<a href="https://example.com">متن لینک</a>
```

| بخش | نقش |
|-----|-----|
| `<a>` | anchor element |
| `href` | مقصد (Hypertext Reference) |
| متن داخل a | برچسب قابل کلیک |

---

## ۲. مسیر مطلق (Absolute)

آدرس کامل با پروتکل:

```html
<a href="https://developer.mozilla.org/fa/">MDN</a>
<a href="https://example.com/about.html">درباره</a>
```

- برای سایت‌های **خارجی**
- مستقل از محل فایل فعلی

---

## ۳. مسیر نسبی (Relative)

نسبت به **فایل HTML فعلی**:

```
project/
├── index.html
├── about.html
└── pages/
    └── team.html
```

از `index.html`:

```html
<a href="about.html">درباره</a>
<a href="pages/team.html">تیم</a>
```

از `pages/team.html`:

```html
<a href="../index.html">خانه</a>
<a href="../about.html">درباره</a>
```

| الگو | معنی |
|------|------|
| `file.html` | همان پوشه |
| `folder/file.html` | زیرپوشه |
| `../file.html` | یک سطح بالاتر |
| `./file.html` | صریحاً همان پوشه |

---

## ۴. لنگر درون‌صفحه

```html
<a href="#section2">برو به بخش ۲</a>
...
<h2 id="section2">بخش ۲</h2>
```

- `id` **یکتا** در صفحه
- `#` فقط نام id

---

## ۵. target و rel

```html
<a href="https://example.com" target="_blank" rel="noopener noreferrer">
    سایت خارجی
</a>
```

| Attribute | کاربرد |
|-----------|--------|
| `target="_blank"` | تب جدید |
| `rel="noopener noreferrer"` | امنیت — جلوگیری از tabnabbing |
| `title` | tooltip — توضیح کوتاه |

**همیشه** با `_blank` از `noopener` استفاده کنید.

---

## ۶. mailto و tel

```html
<a href="mailto:info@example.com">ایمیل</a>
<a href="mailto:info@example.com?subject=سوال">با موضوع</a>
<a href="tel:+989121234567">تماس</a>
```

---

## ۷. متن لینک معنادار

```html
<!-- ❌ -->
<a href="report.pdf">اینجا کلیک کنید</a>

<!-- ✅ -->
<a href="report.pdf">دانلود گزارش سالانه (PDF)</a>
```

Screen reader لیست لینک‌ها را می‌خواند — متن باید مقصد را بگوید.

---

## ۸. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_basic_link.html](./examples/01_basic_link.html) | لینک ساده |
| [02_relative_paths.html](./examples/02_relative_paths.html) | مسیر نسبی |
| [03_anchor_links.html](./examples/03_anchor_links.html) | #anchor |
| [04_link_types.html](./examples/04_link_types.html) | انواع href |

---

## ۹. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| href خالی `<a href="">` | `#` یا URL واقعی |
| فراموش `.html` | پسوند را در نسبی بنویسید |
| `../` زیاد اشتباه | ساختار پوشه را بکشید |
| لینک بدون متن | متن یا aria-label |
| target بدون rel | noopener noreferrer |
| id تکراری | id یکتا |

---

## ۱۰. download attribute (اختیاری)

```html
<a href="files/guide.pdf" download>دانلود راهنما</a>
```

مرورگر را به دانلود مستقیم تشویق می‌کند (محدودیت cross-origin).

---

## ۱۱. لینک به بخش صفحه دیگر

```html
<a href="about.html#team">تیم در صفحه درباره</a>
```

---

## ۱۲. بهترین شیوه‌ها

1. **متن توصیفی** برای a
2. **rel امن** با _blank
3. **ساختار پوشه ثابت** — index در root
4. **تست همه لینک‌ها** بعد از جابه‌جایی فایل
5. **لینک خارجی** — در صورت نیاز نشان دهید خارجی است

---

## ۱۳. تمرین کلاسی

1. سه صفحه با nav ul لینک‌دار
2. فهرست مطالب با anchor
3. mailto و tel
4. یک لینک _blank با rel

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۴. خلاصه

- ✅ a + href
- ✅ absolute برای خارج؛ relative برای داخل پروژه
- ✅ #id برای anchor
- ✅ target="_blank" + rel="noopener noreferrer"

**بعد:** [۷ — تصاویر](../session-07/)

---

**قبل:** [۵ — لیست‌ها](../session-05/) | **بعد:** [۷ — تصاویر](../session-07/)
