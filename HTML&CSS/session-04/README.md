# جلسه ۴: قالب‌بندی و معنای متن (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- تفاوت `<b>`/`<strong>` و `<i>`/`<em>` را بدانید
- از تگ‌های معنایی (`mark`, `small`, `del`, `ins`, `sub`, `sup`) استفاده کنید
- `blockquote` و `cite` برای نقل‌قول بنویسید
- `<code>` و `<pre>` برای کد مناسب به کار ببرید
- بین «ظاهر» و «معنا» در HTML تفاوت قائل شوید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | چرا معنایی مهم است؟ |
| 10–30 | strong/em vs b/i |
| 30–50 | mark، small، del، ins |
| 50–65 | sub، sup، blockquote، code |
| 65–80 | تمرین: متن غنی |
| 80–90 | اشتباهات، تکلیف، Q&A |

---

## ۱. HTML معنایی (Semantic)

HTML فقط «چگونه دیده شود» نیست — **معنای** متن را هم می‌گوید. Screen reader و موتور جستجو از معنا استفاده می‌کنند.

---

## ۲. تأکید مهم — `<strong>`

```html
<p><strong>هشدار:</strong> این عملیات برگشت‌پذیر نیست.</p>
```

- **معنایی:** اهمیت، فوریت، هشدار
- پیش‌فرض مرورگر: **bold**
- برای «فقط bold» اگر اهمیت ندارید، `<b>` یا CSS

---

## ۳. تأکید ملایم — `<em>`

```html
<p>من <em>واقعاً</em> HTML را دوست دارم.</p>
```

- **معنایی:** تأکید گفتاری (stress)
- پیش‌فرض: *italic*
- برای اصطلاحات خارجی: `<em lang="en">API</em>`

---

## ۴. `<b>` و `<i>` — بدون معنای قوی

| تگ | کاربرد معمول |
|----|--------------|
| `<b>` | کلمات کلیدی، خلاصه، نام محصول — بدون اهمیت خاص |
| `<i>` | اصطلاح فنی، اندیشه، نام لاتین |

```html
<p>محصول <b>HTML Pro</b> برای <i>مبتدیان</i> مناسب است.</p>
```

**ترجیح:** وقتی معنا دارید `strong`/`em` بهتر است.

---

## ۵. سایر تگ‌های inline معنایی

### ۵.۱. `<mark>` — هایلایت

```html
<p>کلمه <mark>HTML</mark> در جستجو برجسته شد.</p>
```

### ۵.۲. `<small>` — متن فرعی

```html
<p><small>آخرین به‌روزرسانی: ۱۴۰۴/۰۶/۱۱</small></p>
```

### ۵.۳. `<del>` و `<ins>` — حذف و افزودن

```html
<p>قیمت: <del>۵۰۰</del> <ins>۴۰۰</ins> هزار تومان</p>
```

### ۵.۴. `<sub>` و `<sup>`

```html
<p>فرمول آب: H<sub>2</sub>O — توان: x<sup>2</sup></p>
```

---

## ۶. نقل‌قول — `<blockquote>` و `<cite>`

```html
<blockquote cite="https://example.com/source">
    <p>وب برای همه ساخته شده است.</p>
    <footer>— <cite>تیم W3C</cite></footer>
</blockquote>
```

- `blockquote` برای نقل‌قول block
- `cite` برای عنوان اثر (کتاب، مقاله) — نه نام شخص به‌تنهایی (از footer استفاده کنید)

---

## ۷. کد — `<code>` و `<pre>`

```html
<p>از تگ <code>&lt;p&gt;</code> برای پاراگراف استفاده کنید.</p>

<pre><code>&lt;!DOCTYPE html&gt;
&lt;html lang="fa"&gt;
...
&lt;/html&gt;</code></pre>
```

- `code` — قطعه کوتاه inline
- `pre` — حفظ فاصله و خطوط (block)
- برای نمایش `<` از entity `&lt;` استفاده کنید

---

## ۸. `<abbr>` — مخفف

```html
<p><abbr title="HyperText Markup Language">HTML</abbr> زبان نشانه‌گذاری است.</p>
```

---

## ۹. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_strong_em.html](./examples/01_strong_em.html) | strong و em |
| [02_b_i_semantic.html](./examples/02_b_i_semantic.html) | b، i و مقایسه |
| [03_mark_del_ins.html](./examples/03_mark_del_ins.html) | mark، del، ins |
| [04_quotes_code.html](./examples/04_quotes_code.html) | blockquote و code |

---

## ۱۰. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| strong برای همه bold | معنا گم می‌شود | فقط برای اهمیت |
| heading برای bold | outline خراب | strong در p |
| u برای underline | اشتباه با link | CSS یا لینک واقعی |
| `<font>` | deprecated | CSS |
| `<center>` | deprecated | CSS text-align |

### ❌ تگ‌های منسوخ

```html
<font color="red">متن</font>   <!-- ❌ -->
<b>متن</b>                      <!-- فقط اگر معنای b مدنظر است -->
```

---

## ۱۱. بهترین شیوه‌ها

1. **strong/em اول** — مگر دلیل خاص برای b/i
2. **mark برای جستجو/هایلایت** — نه decoration بی‌دلیل
3. **del/ins برای تاریخچه تغییر** — قیمت، ویرایش مقاله
4. **code برای نام تگ و دستور** — خوانایی برای برنامه‌نویسان
5. **lang روی em** برای واژه خارجی

---

## ۱۲. تمرین کلاسی (۳۰ دقیقه)

1. پاراگرaph با strong، em، mark
2. قیمت با del و ins
3. blockquote با cite
4. یک خط کد HTML در code

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۳. خلاصه جلسه

- ✅ strong/em = معنا؛ b/i = ظاهر بدون معنای قوی
- ✅ mark، small، del، ins، sub، sup
- ✅ blockquote، cite، code، pre
- ✅ abbr برای مخفف‌ها
- ✅ از تگ‌های deprecated دوری کنید

**جلسه بعد:** لیست‌ها — ul، ol، dl

---

**قبل:** [۳ — عناصر متنی](../session-03/) | **بعد:** [۵ — لیست‌ها](../session-05/)
