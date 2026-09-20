# جلسه 27: Media Queries و اصول Responsive — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- درک responsive design و viewport
- نوشتن media query با min-width و max-width
- breakpointهای رایج: 480, 768, 1024, 1280
- تست با DevTools device mode
- ترکیب media query با Flexbox و Grid

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | responsive چیست؟ |
| 10–25 | meta viewport |
| 25–40 | syntax media query |
| 40–55 | min-width vs max-width |
| 55–70 | breakpoint strategy |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## ۱. responsive design

طراحی واکنش‌گرا یعنی layout با عرض viewport تغییر کند.

```html
<meta name="viewport" content="width=device-width, initial-scale=1.0">
```

بدون این meta، موبایل صفحه را zoom out می‌کند.

---

## ۲. syntax پایه

```css
@media (max-width: 768px) {
  .container { flex-direction: column; }
}

@media (min-width: 769px) {
  .sidebar { display: block; }
}
```

---

## ۳. breakpointهای رایج

| نام | عرض | دستگاه |
|-----|-----|--------|
| mobile | < 480px | موبایل کوچک |
| tablet | 768px | تبلت |
| laptop | 1024px | لپ‌تاپ |
| desktop | 1280px+ | دسکتاپ |

---

## ۴. mobile vs desktop first

Desktop-first: `max-width` — استایل پایه دسکتاپ، override موبایل.
Mobile-first: `min-width` — پایه موبایل، override بزرگ‌تر (جلسه ۲۸).

---

## ۵. media features

```css
@media (orientation: landscape) { }
@media (prefers-color-scheme: dark) { }
@media (prefers-reduced-motion: reduce) { }
```

---

## ۶. container responsive

```css
.container {
  width: min(100% - 2rem, 1100px);
  margin-inline: auto;
}
```

---

## ۷. hidden/visible

```css
.mobile-only { display: none; }
@media (max-width: 768px) {
  .mobile-only { display: block; }
  .desktop-only { display: none; }
}
```

---

## ۸. تست

1. Chrome DevTools → Ctrl+Shift+M
2. عرض‌های 320, 375, 768, 1024
3. zoom 200%
4. چاپ: `@media print`

---

## ۹. Flexbox + MQ

```css
.cards { display: flex; flex-wrap: wrap; gap: 1rem; }
.card { flex: 1 1 280px; }
```

---

## ۱۰. Grid + MQ

```css
.grid { display: grid; gap: 1rem; }
@media (min-width: 600px) { .grid { grid-template-columns: 1fr 1fr; } }
@media (min-width: 900px) { .grid { grid-template-columns: repeat(3, 1fr); } }
```

---

## ۱۱. واحد rem در MQ

breakpoint بر px است؛ font-size داخل MQ با rem انعطاف‌پذیرتر است.

---

## ۱۲. range syntax

```css
@media (768px <= width <= 1024px) { /* تبلت landscape */ }
```

---

## مرور — مفاهیم کلیدی

در جلسه 27 (Media Queries و اصول Responsive) مهم است که مثال‌ها را در مرورگر باز کنید و با DevTools آزمایش کنید.

---

## تمرین گام‌به‌گام

1. فایل `01_*.html` را باز کنید.
2. CSS را خط‌به‌خط بخوانید.
3. یک property را تغییر دهید و نتیجه را ببینید.
4. تمرین `exercise_01.html` را بدون نگاه به پاسخ انجام دهید.

---

## سوالات کلاسی

- تفاوت این مبحث با جلسه قبل چیست؟
- چه اشتباه رایجی دیدید؟
- در پروژه واقعی کجا استفاده می‌شود؟

---

## چک‌لیست تحویل

- [ ] HTML معتبر
- [ ] viewport meta
- [ ] کامنت فارسی در CSS
- [ ] تست موبایل 320px
- [ ] keyboard navigation

---

## ارتباط با پروژه نهایی

مهارت‌های این جلسه در پروژه‌های responsive، landing page و dashboard کاربرد مستقیم دارند.

---

## منابع ویدیویی

- [MDN Learn](https://developer.mozilla.org/en-US/docs/Learn)
- [web.dev/learn/css](https://web.dev/learn/css/)

---

## واژگان انگلیسی

| English | فارسی |
|---------|--------|
| responsive | واکنش‌گرا |
| breakpoint | نقطه شکست |
| viewport | نمای viewport |

---

## نکات حرفه‌ای

- کد CSS را modular نگه دارید.
- از naming convention ثابت استفاده کنید.
- قبل از deploy روی دستگاه واقعی تست کنید.

---

## Debug

اگر layout شکست:
1. DevTools → Computed
2. box model را بررسی کنید
3. media query فعال را در Styles ببینید

---

## جمع‌بندی نهایی

پایان جلسه 27. تکالیف `question.md` را انجام دهید و مثال `04_*` را با تغییرات شخصی گسترش دهید.

---

## فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_basic_media.html](./examples/01_basic_media.html) | media query ساده |
| [02_breakpoints.html](./examples/02_breakpoints.html) | چند breakpoint |
| [03_layout_shift.html](./examples/03_layout_shift.html) | تغییر layout |
| [04_responsive_grid.html](./examples/04_responsive_grid.html) | Grid واکنش‌گرا |

---

## اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| فراموش viewport meta | موبایل zoom out |
| فقط یک breakpoint | شکست بین اندازه‌ها |
| px ثابت everywhere | عدم انعطاف |
| !important در MQ | cascade شکسته |

---

## تمرین کلاسی

جزئیات در [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [MDN — CSS](https://developer.mozilla.org/fa/docs/Web/CSS)
- [web.dev — Learn CSS](https://web.dev/learn/css/)

---

**جلسه قبل:** [26](../session-26/) | **بعد:** [28](../session-28/)

---

## راهنمای یادگیری خودآموز

### عنوان، شماره و زمان

**جلسهٔ 27 — Media Query | ۹۰ دقیقه**

### پیش‌نیازها

- مفاهیم جلسهٔ 26: الگوی Navigation واکنش‌گرا
- توانایی باز کردن فایل HTML در مرورگر و استفادهٔ پایه از DevTools
- اجرای مثال‌های جلسه پیش از شروع تمرین

### اهداف قابل‌اندازه‌گیری

در پایان جلسه باید بتوانید:

1. مفهوم «Media Query» را با یک مثال و واژگان فارسی/English توضیح دهید.
2. نمونهٔ موجود را بدون کپی کورکورانه بازسازی و نتیجه را در مرورگر بررسی کنید.
3. حداقل سه خطای رایج این موضوع را با DevTools تشخیص دهید.
4. راه‌حل خود را از نظر معنا، خوانایی، responsive بودن و accessibility ارزیابی کنید.

### چرا این جلسه مهم است؟

موضوع **Media Query** یک جزیرهٔ جدا نیست؛ مستقیماً روی انتخاب rule، محاسبهٔ style، layout و paint در مرورگر اثر می‌گذارد. یادگیری آن به‌صورت تصمیم‌محور باعث می‌شود به‌جای حفظ کردن تگ یا property، بتوانید برای محتوای واقعی راه‌حل درست انتخاب کنید و دلیل انتخابتان را توضیح دهید.

### مفهوم عمیق: از کد تا نتیجه

مرورگر کد را از بالا به پایین parse می‌کند، آن را به ساختارهای داخلی مانند DOM و CSSOM تبدیل می‌کند و سپس style، layout و paint را انجام می‌دهد. هنگام کار با **Media Query** همیشه سه پرسش داشته باشید: «معنای این کد چیست؟»، «مرورگر چگونه آن را محاسبه می‌کند؟» و «کاربر با keyboard، موبایل یا صفحه‌خوان چه تجربه‌ای دارد؟». این مدل ذهنی از آزمون‌وخطای تصادفی جلوگیری می‌کند.

### Media Query بر پایهٔ محتوا

Breakpoint را از جایی انتخاب کنید که layout می‌شکند، نه از نام یک دستگاه. قابلیت‌ها را نیز می‌توان با `prefers-reduced-motion`، `hover` و `pointer` سنجید.


### سه اشتباه رایج و اصلاح آن‌ها

1. **کپی‌کردن بدون پیش‌بینی نتیجه:** پیش از refresh بنویسید انتظار دارید چه تغییری ببینید؛ سپس با DevTools اختلاف را پیدا کنید.
2. **حل ظاهر و نادیده‌گرفتن معنا:** ابتدا ساختار، ترتیب محتوا و دسترسی با keyboard را درست کنید؛ بعد سراغ polish بصری بروید.
3. **تغییرهای بزرگ و تست دیرهنگام:** هر بار یک تغییر کوچک انجام دهید و در عرض باریک و عریض بررسی کنید.
4. **استفاده از workaround فوری:** پیش از `!important`، selector پیچیده یا مقدار جادویی، علت اصلی را در Computed/Box Model بیابید.

### مثال گام‌به‌گام

فایل [`examples/01_basic_media.html`](./examples/01_basic_media.html) را مبنا قرار دهید:

1. فایل را در مرورگر باز کنید و بدون دیدن کد، اجزای صفحه را نام ببرید.
2. سورس را بخوانید و هر بخش مرتبط با «Media Query» را با یک کامنت مشخص کنید.
3. یک مقدار یا عنصر را تغییر دهید و نتیجهٔ مورد انتظار را قبل از refresh یادداشت کنید.
4. با Inspect، مقدار نهایی و دلیل اعمال‌شدن آن را بررسی کنید.
5. تغییر را با keyboard و عرض ۳۲۰ پیکسل آزمایش کنید.
6. کد آزمایشی را به نسخه‌ای خوانا با نام‌گذاری معنادار refactor کنید.

### الگوی بهتر (Better Pattern)

چرخهٔ **content → structure → style → test → refactor** را رعایت کنید: با محتوای واقعی شروع کنید، کمترین ساختار معنایی لازم را بسازید، style را از ساده به پیچیده اضافه کنید، در هر مرحله تست کنید و فقط پس از کارکرد صحیح abstraction بسازید. این الگو از over-engineering و CSS شکننده جلوگیری می‌کند.

### تمرین‌ها

#### تمرین آسان

مثال اصلی را از حافظه در یک فایل تازه بازسازی کنید و سه تصمیم خود دربارهٔ «Media Query» را در کامنت‌ها توضیح دهید. سپس یک screenshot یا یادداشت از بررسی DevTools ثبت کنید.

#### تمرین چالشی

مثال را با محتوای فارسی واقعی گسترش دهید؛ نسخه باید در عرض ۳۲۰ تا ۱۲۸۰ پیکسل، با Tab، و بدون خطای مسیر فایل کار کند. یک محدودیت عمدی ایجاد کنید، آن را تشخیص دهید و علت/اصلاح را بنویسید.

### راهنمای حل

- ابتدا معیار پذیرش را بنویسید: محتوای کامل، ساختار معتبر، نبود overflow و focus قابل‌مشاهده.
- نسخهٔ آسان را با کوچک‌ترین کد ممکن بسازید و با نمونه مقایسه کنید.
- در تمرین چالشی از Responsive Mode و پنل Computed استفاده کنید؛ فقط پس از یافتن علت، کد را تغییر دهید.
- پاسخ یکتا نیست؛ راه‌حل خوب باید **قابل توضیح، قابل تست و قابل نگهداری** باشد.

### خودآزمایی

1. موضوع اصلی این جلسه در کدام مرحله از کار مرورگر یا تجربهٔ کاربر اثر می‌گذارد؟
2. چگونه بدون حدس‌زدن می‌فهمید کد شما واقعاً اعمال شده است؟
3. یک تصمیم ضعیف در این موضوع چه اثری بر موبایل یا accessibility دارد؟
4. چه زمانی راه‌حل ساده‌تر از abstraction پیچیده بهتر است؟

<details>
<summary>راهنمای پاسخ</summary>

پاسخ باید به‌ترتیب به مدل DOM/CSSOM و render، مشاهده در DevTools و آزمون واقعی، اثر بر ترتیب/خوانایی/focus، و اصل «کمترین پیچیدگی لازم» اشاره کند. پاسخ را با یک مثال از فایل همین جلسه کامل کنید.
</details>

### جمع‌بندی و پل جلسهٔ بعد

اکنون باید بتوانید **Media Query** را توضیح دهید، در نمونه تشخیص دهید، به‌صورت مرحله‌ای پیاده کنید و با ابزار مرورگر بسنجید. گام بعدی **جلسهٔ 28: طراحی Mobile-first** است؛ پیش از رفتن، تمرین چالشی را نگه دارید تا در جلسهٔ بعد روی همان خروجی توسعه دهید.

