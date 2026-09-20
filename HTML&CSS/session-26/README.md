# جلسه ۲۶: پروژه — نوار ناوبری واکنش‌گرا (Responsive Navbar) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- ساخت navbar معنایی با `<nav>`, `<ul>`, `<li>`, `<a>`
- چیدمان افقی با Flexbox و تراز عمودی
- طراحی واکنش‌گرا با media query برای موبایل
- پیاده‌سازی منوی همبرگری (hamburger) بدون JavaScript (checkbox hack)
- navbar چسبان (sticky) و سایه برای عمق بصری
- ترکیب همه مهارت‌ها در یک پروژه کامل

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور Flexbox و semantic HTML |
| 10–25 | navbar پایه — لوگو + لینک‌ها |
| 25–40 | Flexbox: space-between و align-items |
| 40–55 | media query — مخفی کردن منو در موبایل |
| 55–70 | hamburger menu با checkbox |
| 70–85 | sticky + shadow + تمرین |
| 85–90 | جمع‌بندی و تکلیف |

---

## ۱. ساختار HTML معنایی

```html
<header class="site-header">
  <nav class="navbar" aria-label="ناوبری اصلی">
    <a href="#" class="logo">کلینیک آموزش</a>
    <ul class="nav-links">
      <li><a href="#home">خانه</a></li>
      <li><a href="#courses">دوره‌ها</a></li>
      <li><a href="#contact">تماس</a></li>
    </ul>
  </nav>
</header>
```

**چرا `<nav>`؟** Screen readerها مستقیماً به بخش ناوبری می‌پرند. `aria-label` وقتی چند nav دارید ضروری است.

---

## ۲. Flexbox برای navbar

```css
.navbar {
  display: flex;
  justify-content: space-between; /* لوگو راست، لینک‌ها چپ (RTL) */
  align-items: center;
  padding: 1rem 2rem;
  background: #1e293b;
}

.nav-links {
  display: flex;
  list-style: none;
  gap: 1.5rem;
  margin: 0;
  padding: 0;
}

.nav-links a {
  color: #f8fafc;
  text-decoration: none;
  font-weight: 500;
}
```

| ویژگی | نقش |
|--------|-----|
| `display: flex` | چیدمان یک‌خطی |
| `justify-content: space-between` | فاصله بین لوگو و منو |
| `align-items: center` | تراز عمودی وسط |
| `gap` | فاصله بین آیتم‌های li |

---

## ۳. reset پیش‌فرض مرورگر

```css
*, *::before, *::after {
  box-sizing: border-box;
}

body {
  margin: 0;
  font-family: system-ui, sans-serif;
}
```

بدون reset، `ul` padding پیش‌فرض دارد و navbar کج می‌شود.

---

## ۴. hover و focus برای دسترسی‌پذیری

```css
.nav-links a:hover,
.nav-links a:focus-visible {
  color: #38bdf8;
  outline: 2px solid #38bdf8;
  outline-offset: 2px;
}
```

`:focus-visible` فقط وقتی keyboard focus است outline نشان می‌دهد — بهتر از `:focus` برای ماوس.

---

## ۵. media query — breakpoint موبایل

```css
@media (max-width: 768px) {
  .nav-links {
    display: none; /* موقت — بعداً hamburger */
  }
}
```

| breakpoint | کاربرد |
|------------|--------|
| 768px | تبلت و موبایل (رایج) |
| 480px | موبایل کوچک |
| 1024px | دسکتاپ متوسط |

**نکته:** `max-width` یعنی «تا این عرض» — desktop-first. در جلسه ۲۸ mobile-first یاد می‌گیریم.

---

## ۶. منوی همبرگری — checkbox hack

بدون JS می‌توان با `<input type="checkbox">` مخفی و `<label>` منو را toggle کرد:

```html
<input type="checkbox" id="nav-toggle" class="nav-toggle" hidden>
<label for="nav-toggle" class="hamburger" aria-label="باز/بسته کردن منو">
  <span></span><span></span><span></span>
</label>
```

```css
@media (max-width: 768px) {
  .hamburger { display: flex; flex-direction: column; cursor: pointer; }
  .nav-links {
    display: none;
    flex-direction: column;
    position: absolute;
    top: 100%;
    right: 0;
    left: 0;
    background: #1e293b;
    padding: 1rem;
  }
  .nav-toggle:checked ~ .nav-links { display: flex; }
}
```

---

## ۷. sticky navbar

```css
.site-header {
  position: sticky;
  top: 0;
  z-index: 100;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
}
```

`position: sticky` navbar را هنگام scroll بالای viewport نگه می‌دارد.

---

## ۸. z-index و stacking context

- navbar باید بالای محتوا باشد → `z-index: 100`
- منوی dropdown موبایل → `position: absolute` نسبت به `.navbar` با `position: relative`

```css
.navbar { position: relative; }
```

---

## ۹. RTL و navbar

در `dir="rtl"`:
- `space-between` لوگو را راست و منو را چپ می‌گذارد
- `padding-right` و `padding-left` را در media query تست کنید
- آیکون hamburger معمولاً سمت چپ (در RTL) قرار می‌گیرد

---

## ۱۰. تست واکنش‌گرایی

1. DevTools → Toggle device toolbar (Ctrl+Shift+M)
2. عرض‌های 320, 768, 1024, 1440 را تست کنید
3. Tab برای keyboard navigation
4. zoom 200% برای accessibility

---

## ۱۱. فایل‌های جلسه (پروژه — مثال‌های غنی‌تر)

| فایل | موضوع |
|------|--------|
| [01_navbar_basic.html](./examples/01_navbar_basic.html) | navbar ساده با Flexbox |
| [02_hamburger_menu.html](./examples/02_hamburger_menu.html) | منوی همبرگری کامل |
| [03_sticky_navbar.html](./examples/03_sticky_navbar.html) | sticky + scroll |
| [04_full_responsive_nav.html](./examples/04_full_responsive_nav.html) | پروژه نهایی ترکیبی |

---

## ۱۲. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| `<div>` به‌جای `<nav>` | دسترسی‌پذیری ضعیف |
| فراموش کردن `list-style: none` | bullet در navbar |
| hamburger بدون label | غیرقابل استفاده با screen reader |
| `position: fixed` بدون padding body | محتوا زیر navbar پنهان |
| breakpoint نامشخص | طراحی شکسته بین 768–800px |

---

## ۱۳. گسترش پروژه (اختیاری)

- زیرمنو (dropdown) با `:hover` یا focus
- حالت active برای لینک صفحه فعلی (`aria-current="page"`)
- انیمیشن باز شدن منو (جلسه ۳۰)
- تم تاریک با CSS variables (جلسه ۲۹)

---

## ۱۴. چک‌لیست تحویل پروژه

- [ ] HTML معنایی و valid
- [ ] navbar در دسکتاپ افقی
- [ ] در موبایل hamburger کار می‌کند
- [ ] sticky هنگام scroll
- [ ] focus قابل مشاهده با keyboard
- [ ] بدون horizontal scroll در 320px

---

## ۱۵. تمرین کلاسی

1. navbar با ۴ لینک و لوگو بسازید
2. در 768px منو به hamburger تبدیل شود
3. sticky + box-shadow اضافه کنید

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۶. ارتباط با جلسات بعد

| جلسه | ارتباط |
|------|--------|
| ۲۷ | media query عمیق‌تر |
| ۲۸ | mobile-first refactor |
| ۲۹ | متغیرهای CSS برای رنگ navbar |
| ۳۰ | انیمیشن باز شدن منو |

---

## 📚 مطالعه تکمیلی

- [MDN — Flexbox](https://developer.mozilla.org/fa/docs/Web/CSS/CSS_flexible_box_layout)
- [MDN — position: sticky](https://developer.mozilla.org/en-US/docs/Web/CSS/position)
- [WAI — Navigation](https://www.w3.org/WAI/tutorials/menus/)

---

**جلسه قبل:** [۲۵](../session-25/) | **بعد:** [۲۷ — Media Queries](../session-27/)

---

## راهنمای یادگیری خودآموز

### عنوان، شماره و زمان

**جلسهٔ 26 — الگوی Navigation واکنش‌گرا | ۹۰ دقیقه**

### پیش‌نیازها

- مفاهیم جلسهٔ 25: Grid پیشرفته
- توانایی باز کردن فایل HTML در مرورگر و استفادهٔ پایه از DevTools
- اجرای مثال‌های جلسه پیش از شروع تمرین

### اهداف قابل‌اندازه‌گیری

در پایان جلسه باید بتوانید:

1. مفهوم «الگوی Navigation واکنش‌گرا» را با یک مثال و واژگان فارسی/English توضیح دهید.
2. نمونهٔ موجود را بدون کپی کورکورانه بازسازی و نتیجه را در مرورگر بررسی کنید.
3. حداقل سه خطای رایج این موضوع را با DevTools تشخیص دهید.
4. راه‌حل خود را از نظر معنا، خوانایی، responsive بودن و accessibility ارزیابی کنید.

### چرا این جلسه مهم است؟

موضوع **الگوی Navigation واکنش‌گرا** یک جزیرهٔ جدا نیست؛ مستقیماً روی انتخاب rule، محاسبهٔ style، layout و paint در مرورگر اثر می‌گذارد. یادگیری آن به‌صورت تصمیم‌محور باعث می‌شود به‌جای حفظ کردن تگ یا property، بتوانید برای محتوای واقعی راه‌حل درست انتخاب کنید و دلیل انتخابتان را توضیح دهید.

### مفهوم عمیق: از کد تا نتیجه

مرورگر کد را از بالا به پایین parse می‌کند، آن را به ساختارهای داخلی مانند DOM و CSSOM تبدیل می‌کند و سپس style، layout و paint را انجام می‌دهد. هنگام کار با **الگوی Navigation واکنش‌گرا** همیشه سه پرسش داشته باشید: «معنای این کد چیست؟»، «مرورگر چگونه آن را محاسبه می‌کند؟» و «کاربر با keyboard، موبایل یا صفحه‌خوان چه تجربه‌ای دارد؟». این مدل ذهنی از آزمون‌وخطای تصادفی جلوگیری می‌کند.


### سه اشتباه رایج و اصلاح آن‌ها

1. **کپی‌کردن بدون پیش‌بینی نتیجه:** پیش از refresh بنویسید انتظار دارید چه تغییری ببینید؛ سپس با DevTools اختلاف را پیدا کنید.
2. **حل ظاهر و نادیده‌گرفتن معنا:** ابتدا ساختار، ترتیب محتوا و دسترسی با keyboard را درست کنید؛ بعد سراغ polish بصری بروید.
3. **تغییرهای بزرگ و تست دیرهنگام:** هر بار یک تغییر کوچک انجام دهید و در عرض باریک و عریض بررسی کنید.
4. **استفاده از workaround فوری:** پیش از `!important`، selector پیچیده یا مقدار جادویی، علت اصلی را در Computed/Box Model بیابید.

### مثال گام‌به‌گام

فایل [`examples/01_navbar_basic.html`](./examples/01_navbar_basic.html) را مبنا قرار دهید:

1. فایل را در مرورگر باز کنید و بدون دیدن کد، اجزای صفحه را نام ببرید.
2. سورس را بخوانید و هر بخش مرتبط با «الگوی Navigation واکنش‌گرا» را با یک کامنت مشخص کنید.
3. یک مقدار یا عنصر را تغییر دهید و نتیجهٔ مورد انتظار را قبل از refresh یادداشت کنید.
4. با Inspect، مقدار نهایی و دلیل اعمال‌شدن آن را بررسی کنید.
5. تغییر را با keyboard و عرض ۳۲۰ پیکسل آزمایش کنید.
6. کد آزمایشی را به نسخه‌ای خوانا با نام‌گذاری معنادار refactor کنید.

### الگوی بهتر (Better Pattern)

چرخهٔ **content → structure → style → test → refactor** را رعایت کنید: با محتوای واقعی شروع کنید، کمترین ساختار معنایی لازم را بسازید، style را از ساده به پیچیده اضافه کنید، در هر مرحله تست کنید و فقط پس از کارکرد صحیح abstraction بسازید. این الگو از over-engineering و CSS شکننده جلوگیری می‌کند.

### تمرین‌ها

#### تمرین آسان

مثال اصلی را از حافظه در یک فایل تازه بازسازی کنید و سه تصمیم خود دربارهٔ «الگوی Navigation واکنش‌گرا» را در کامنت‌ها توضیح دهید. سپس یک screenshot یا یادداشت از بررسی DevTools ثبت کنید.

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

اکنون باید بتوانید **الگوی Navigation واکنش‌گرا** را توضیح دهید، در نمونه تشخیص دهید، به‌صورت مرحله‌ای پیاده کنید و با ابزار مرورگر بسنجید. گام بعدی **جلسهٔ 27: Media Query** است؛ پیش از رفتن، تمرین چالشی را نگه دارید تا در جلسهٔ بعد روی همان خروجی توسعه دهید.

