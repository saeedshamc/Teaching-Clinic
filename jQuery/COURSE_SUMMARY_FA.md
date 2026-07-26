# خلاصه دوره آموزش jQuery - ۲۰ جلسه

## 📋 ساختار کامل دوره

### بلاک اول: مبانی jQuery (جلسات ۱-۴)
---

#### **جلسه ۱: شروع کار با jQuery و سلکتورها**
- علامت `$` و معادل `jQuery`
- استفاده از `$(document).ready`
- سلکتورهای CSS (مثل `.class`, `#id`, `element`)
- سلکتورهای ویژه jQuery (`:first`, `:last`, `:eq()`, `:visible`)
- متدهای خواندن/نوشتن: `.text()`, `.html()`, `.val()`, `.attr()`, `.prop()`
- تفاوت بین `attr()` و `prop()`
- Method Chaining و کش کردن سلکتورها

**فایلها:**
- 📄 `session-01-intro-selectors/session-01.md` - توضیح مفصل
- 🎯 `session-01-intro-selectors/code/index.html` - نمونه عملی

---

#### **جلسه ۲: رویدادها و انیمیشن/افکت‌ها**
- متدهای رویداد: `on()`, `off()`, `one()`
- Event Delegation برای عناصر پویا
- شیء Event و خصوصیات آن
- افکت‌های آماده: `fadeIn()`, `fadeOut()`, `slideToggle()`
- `animate()` برای انیمیشن سفارشی
- صف انیمیشن (Queue) و `delay()`, `stop()`, `finish()`

**فایلها:**
- 📄 `session-02-events-effects/session-02.md`
- 🎯 `session-02-events-effects/code/index.html`

---

#### **جلسه ۳: DOM پیمایش و Ajax پایه**
- متدهای پیمایش: `parent()`, `find()`, `siblings()`, `closest()`
- ساخت المان: `$('<div></div>')`
- درج المان: `append()`, `prepend()`, `after()`, `before()`
- حذف المان: `remove()`, `empty()`, `detach()`
- Ajax مقدماتی: `$.ajax()`, `$.get()`, `$.post()`
- Promise و Deferred اولیه
- مدیریت خطاهای Ajax

**فایلها:**
- 📄 `session-03-dom-ajax/session-03.md`
- 🎯 `session-03-dom-ajax/code/index.html`

---

#### **جلسه ۴: پلاگین‌نویسی و معماری سادهٔ**
- ساخت پلاگین: `$.fn.pluginName`
- استفاده از `return this;` برای Chaining
- Namespacing و جلوگیری از تداخل نام
- مزایای پلاگین برای کد قابل‌استفاده

**فایلها:**
- 📄 `session-04-advanced-plugin-project/session-04.md`
- 🎯 `session-04-advanced-plugin-project/code/index.html`

---

### بلاک دوم: مباحث متوسط (جلسات ۵-۸)
---

#### **جلسه ۵: Deferred، `this` binding و موقعیت‌یابی**
- `$.Deferred()` و `resolve()`/`reject()`
- `$.when()` برای مدیریت چند Promise
- `$.proxy()` و `bind()`/`call()`/`apply()`
- `offset()` در برابر `position()`
- رویدادهای scroll و موقعیت‌یابی

**فایلها:**
- 📄 `session-05-deferred-data-positioning/session-05.md`
- 🎯 `session-05-deferred-data-positioning/code/index.html`

---

#### **جلسه ۶: کامپوننت‌های UI از صفر**
- ساخت Tabs بدون کتابخانه
- Modal/Dialog سفارشی
- Tooltip و Dropdown
- Drag & Drop ساده با HTML5 + jQuery

**فایلها:**
- 📄 `session-06-ui-components-from-scratch/session-06.md`
- 🎯 `session-06-ui-components-from-scratch/code/index.html`

---

#### **جلسه ۷: فرم‌های پیشرفته و API واقعی**
- اعتبارسنجی فرم سفارشی
- نمایش پیام خطا
- آپلود فایل با `FormData`
- کار با API واقعی (JSONPlaceholder)
- مفهوم CORS
- ترکیب jQuery با `localStorage`

**فایلها:**
- 📄 `session-07-forms-upload-api/session-07.md`
- 🎯 `session-07-forms-upload-api/code/index.html`

---

#### **جلسه ۸: معماری و پروژه نهایی**
- Module Pattern برای تنظیم کد
- Event Bus Pattern (پیام‌رسانی بین ماژول‌ها)
- Loose Coupling و Single Responsibility
- پروژه نهایی: داشبورد گالری تصاویر با:
  - جستجوی زنده
  - فیلتر بر اساس دسته‌بندی
  - Drag & Drop برای تغییر ترتیب
  - ذخیره در localStorage

**فایلها:**
- 📄 `session-08-architecture-capstone/session-08.md`
- 🎯 `session-08-architecture-capstone/code/index.html`

---

### بلاک سوم: مهارت‌های پیشرفته (جلسات ۹-۲۰)
---

#### **جلسه ۹: پیمایش DOM پیشرفته**
- `parent()`, `children()`, `find()` - تفاوت و کاربرد
- `siblings()`, `next()`, `prev()` - پیمایش سطحی
- `closest()` - پیدا کردن نزدیک‌ترین والد
- ساخت المان با `$()`
- `append()`, `prepend()`, `after()`, `before()`
- `remove()` و `detach()` - تفاوت

**فایلها:**
- 📄 `session-09-dom-traversal/session-09.md`
- 🎯 `session-09-dom-traversal/code/index.html`

---

#### **جلسه ۱۰: انیمیشن‌ها و صف اجرا**
- افکت‌های ساده: `fadeIn()`, `fadeOut()`, `slideToggle()`
- `animate()` برای انیمیشن سفارشی
- صف انیمیشن (Queue) و چگونه کار می‌کند
- `delay()` برای توقف موقت
- `stop()` و `finish()` برای متوقف‌کردن

**فایلها:**
- 📄 `session-10-animations/session-10.md`
- 🎯 `session-10-animations/code/index.html`

---

#### **جلسه ۱۱: Ajax و کار با API**
- `$.ajax()` - درخواست‌های HTTP
- `$.get()` و `$.post()` - میانبرها
- `success`, `error`, `complete` - مدیریت پاسخ
- Promise: `done()`, `fail()`, `always()`
- CORS و نحوه رفع مشکلات آن

**فایلها:**
- 📄 `session-11-ajax-api/session-11.md`
- 🎯 `session-11-ajax-api/code/index.html`

---

#### **جلسه ۱۲: فرم‌ها و اعتبارسنجی**
- خواندن مقادیر فرم: `val()`, `prop()`, `attr()`
- رویداد `submit`
- اعتبارسنجی ساده و نمایش خطا
- Disabled state و تغییر دینامیکی فرم
- نمایش پیام کاربر

**فایلها:**
- 📄 `session-12-forms-validation/session-12.md`
- 🎯 `session-12-forms-validation/code/index.html`

---

#### **جلسه ۱۳: localStorage و ماندگاری داده**
- `localStorage.setItem()` و `localStorage.getItem()`
- `localStorage.removeItem()` و `localStorage.clear()`
- ذخیره state و بازیابی آن بعد از رفرش
- مثال عملی: یادداشت‌برداری

**فایلها:**
- 📄 `session-13-localstorage/session-13.md`
- 🎯 `session-13-localstorage/code/index.html`

---

#### **جلسه ۱۴: رویدادها و Delegation**
- `on()` برای رویدادهای ایستا و پویا
- Event Delegation برای عناصری که بعداً اضافه می‌شوند
- رویدادهای مختلف: `hover`, `keyup`, `change`
- مثال: لیست پویا با کلیک‌های Delegated

**فایلها:**
- 📄 `session-14-events-delegation/session-14.md`
- 🎯 `session-14-events-delegation/code/index.html`

---

#### **جلسه ۱۵: پلاگین‌نویسی مقدماتی**
- ساخت پلاگین: `$.fn.myPlugin`
- پارامترهای پلاگین
- بازگرداندن `this` برای chaining
- Namespacing برای جلوگیری از تداخل

**فایلها:**
- 📄 `session-15-plugins/session-15.md`
- 🎯 `session-15-plugins/code/index.html`

---

#### **جلسه ۱۶: متدهای کمکی jQuery**
- `$.each()` برای پیمایش آرایه و آبجکت
- `$.map()` برای تبدیل داده‌ها
- `$.grep()` برای فیلتر کردن
- `$.inArray()` برای جستجو
- `$.extend()` برای ادغام آبجکت‌ها

**فایلها:**
- 📄 `session-16-utility-methods/session-16.md`
- 🎯 `session-16-utility-methods/code/index.html`

---

#### **جلسه ۱۷: بهینه‌سازی عملکرد**
- Selector Caching و کاهش DOM query
- Event Delegation بجای رویداد جداگانه
- `detach()` برای تغییرات مکرر
- DOM insertion یک‌جا بجای حلقه
- ProfileSelectorرفتار و measurement

**فایلها:**
- 📄 `session-17-performance/session-17.md`
- 🎯 `session-17-performance/code/index.html`

---

#### **جلسه ۱۸: دیباگ و تست**
- `console.log()` و `console.table()`
- استفاده از DevTools و Elements Inspector
- `debugger;` برای توقف اجرا
- بررسی Network برای Ajax
- تکنیک‌های رفع اشتباهات رایج

**فایلها:**
- 📄 `session-18-testing-debugging/session-18.md`
- 🎯 `session-18-testing-debugging/code/index.html`

---

#### **جلسه ۱۹: ساختار پروژه**
- سازمان‌دهی فایل‌ها: `css/`, `js/`, `index.html`
- Module Pattern برای تنظیم منطق
- جدا کردن Concern‌های مختلف
- استفاده از Objects برای namespace کردن

**فایلها:**
- 📄 `session-19-project-structure/session-19.md`
- 🎯 `session-19-project-structure/code/index.html`

---

#### **جلسه ۲۰: پروژه نهایی - Todo List**
- ترکیب تمام مهارت‌های ۱۹ جلسه قبل
- ویژگی‌ها:
  - فرم افزودن آیتم
  - نمایش لیست
  - mark-as-done کردن
  - حذف آیتم
  - ذخیره خودکار در localStorage
- معماری: Object-based Organization

**فایلها:**
- 📄 `session-20-capstone/session-20.md`
- 🎯 `session-20-capstone/code/index.html`

---

## 🎯 خلاصه کلیدی هر جلسه

| جلسه | موضوع اصلی | مهارت کسب‌شده |
|---|---|---|
| ۱ | سلکتورها | انتخاب و دستکاری المان |
| ۲ | رویدادها و افکت | تعامل با کاربر |
| ۳ | DOM و Ajax | داده‌رسانی پویا |
| ۴ | پلاگین و معماری | کد قابل‌استفاده |
| ۵ | Deferred و Binding | کنترل جریان و Context |
| ۶ | UI Components | ساخت رابط پیشرفته |
| ۷ | فرم و API | تعامل پیشرفته کاربر |
| ۸ | معماری | طراحی پروژه بزرگ |
| ۹ | DOM Traversal | ناوبری DOM |
| ۱۰ | انیمیشن | Queue و Timing |
| ۱۱ | Ajax | کار با سرور |
| ۱۲ | فرم | Validation |
| ۱۳ | localStorage | Persistence |
| ۱۴ | Delegation | رویداد پویا |
| ۱۵ | پلاگین | توسعه‌پذیری |
| ۱۶ | متدهای کمکی | پردازش داده |
| ۱۷ | عملکرد | بهینه‌سازی |
| ۱۸ | دیباگ | رفع اشتباه |
| ۱۹ | ساختار | سازمان‌دهی |
| ۲۰ | پروژه | پیکربندی کل |

---

## 📚 نحوه استفاده از دوره

1. **برای هر جلسه:**
   - فایل `.md` را بخوانید (توضیح نظری)
   - فایل `index.html` را در مرورگر باز کنید (نمونه عملی)
   - Console مرورگر را باز نگه دارید و کد را بازی کنید

2. **ترتیب توصیه‌شده:**
   - جلسات ۱-۴ را متوالی دنبال کنید (مبانی الزامی)
   - جلسات ۵-۸ برای درک معماری مهم است
   - جلسات ۹-۲۰ را براساس علاقه انتخاب کنید (تخصصی)

3. **تمرین:**
   - تمرین‌های پیشنهادی هر جلسه را انجام دهید
   - تله‌های رایج را یادداشت کنید
   - کد را Modify کنید و نتیجه را بررسی کنید

---

## 🔗 منابع اضافی

- jQuery Official Docs: https://api.jquery.com/
- JSONPlaceholder (برای تمرین Ajax): https://jsonplaceholder.typicode.com/
- MDN Web Docs (برای مقایسه با Vanilla JS): https://developer.mozilla.org/

---

## ✅ نتیجه‌گیری

پس از ۲۰ جلسه، شما:
- ✓ jQuery را از صفر یادگرفتید
- ✓ پروژه‌های واقعی می‌سازید
- ✓ کدهای قدیمی را درک و نگهداری می‌کنید
- ✓ برای مهاجرت به فریم‌ورک‌های مدرن آماده هستید

**موفق باشید!** 🚀
