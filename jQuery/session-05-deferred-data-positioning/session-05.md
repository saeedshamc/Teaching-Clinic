# جلسه ۵ — Deferred/Promise پیشرفته، `$.data()`، `this` Binding و موقعیت‌یابی

## بخش اول: عمیق‌تر به Deferred و Promise

در جلسه ۳ با `$.ajax()` و `.done()/.fail()/.always()` آشنا شدید. حالا می‌خواهیم خودِ مکانیزم `Deferred` را از داخل بشناسیم و آن را با `Promise` استاندارد جاوااسکریپت مقایسه کنیم.

### ۱.۱ ساخت یک Deferred دستی

`$.Deferred()` یک شیء می‌سازد که هم می‌توانید "حلش کنید" (resolve) هم "ردش کنید" (reject)، و کد دیگر می‌تواند منتظرش بماند:

```javascript
function waitAndGreet(name, shouldFail) {
  var deferred = $.Deferred();

  setTimeout(function () {
    if (shouldFail) {
      deferred.reject('نام نامعتبر است');
    } else {
      deferred.resolve('سلام ' + name);
    }
  }, 1000);

  // نکته مهم: به بیرون فقط promise() برمی‌گردانیم، نه خودِ deferred
  // چون deferred کامل هم متد resolve/reject دارد که نباید در اختیار مصرف‌کننده باشد
  return deferred.promise();
}

waitAndGreet('علی', false)
  .done(function (msg) { console.log(msg); })
  .fail(function (err) { console.error(err); });
```

**چرا `.promise()` و نه خودِ `deferred`؟** اگر مستقیم `deferred` را برگردانید، هر کسی که آن را می‌گیرد می‌تواند `resolve()`/`reject()` را هم صدا بزند و state داخلی شما را از بیرون خراب کند. `.promise()` فقط متدهای «فقط-خواندنی» (`done`, `fail`, `always`, `then`) را در اختیار می‌گذارد — این یک الگوی طراحی مهم برای کپسوله‌سازی (Encapsulation) است.

### ۱.۲ `.then()` در jQuery

```javascript
waitAndGreet('مریم', false).then(
  function (msg) { console.log('موفق:', msg); },   // onFulfilled
  function (err) { console.error('ناموفق:', err); } // onRejected
);
```

⚠️ **نکته تاریخی مهم**: در نسخه‌های قدیمی jQuery (قبل از ۳.۰)، `.then()` رفتار متفاوتی با Promise استاندارد داشت (مثلاً خطا در callback باعث throw نمی‌شد و به‌درستی به `.fail()` بعدی زنجیره نمی‌رفت). از jQuery 3.0 به بعد، `.then()` تقریباً کاملاً مطابق مشخصات Promise/A+ رفتار می‌کند. با این حال، `.done()/.fail()/.always()` هنوز هم API اصلی و توصیه‌شده jQuery هستند و رفتار قابل‌پیش‌بینی‌تری دارند.

### ۱.۳ مقایسه با Promise استاندارد

| مفهوم | jQuery Deferred | Promise استاندارد |
|---|---|---|
| ساخت | `$.Deferred()` | `new Promise((resolve, reject) => {...})` |
| موفقیت | `.resolve(val)` | `resolve(val)` |
| شکست | `.reject(err)` | `reject(err)` |
| گوش‌دادن به موفقیت | `.done(fn)` | `.then(fn)` |
| گوش‌دادن به شکست | `.fail(fn)` | `.catch(fn)` |
| همیشه اجرا شود | `.always(fn)` | `.finally(fn)` |
| صبر برای چند مورد | `$.when(p1, p2)` | `Promise.all([p1, p2])` |

نکته کاربردی: چون jqXHR (خروجی `$.ajax`) یک Promise-like استاندارد هم هست، می‌توانید آن را داخل `await` هم استفاده کنید (در مرورگرهای/کدهای مدرن):

```javascript
async function loadData() {
  try {
    var data = await $.ajax({ url: '/api/data' });
    console.log(data);
  } catch (err) {
    console.error(err);
  }
}
```

### ۱.۴ ساخت یک پایپ‌لاین ترتیبی با Deferred (پیشرفته)

فرض کنید باید سه Ajax را **پشت‌سرهم** (نه هم‌زمان) صدا بزنید، جایی که هرکدام به نتیجه قبلی نیاز دارد:

```javascript
function step1() {
  var d = $.Deferred();
  setTimeout(function () { d.resolve('نتیجه مرحله ۱'); }, 500);
  return d.promise();
}
function step2(prevResult) {
  var d = $.Deferred();
  setTimeout(function () { d.resolve(prevResult + ' -> نتیجه مرحله ۲'); }, 500);
  return d.promise();
}

step1()
  .then(function (result1) {
    return step2(result1); // برگرداندن یک promise جدید داخل then زنجیره را ادامه می‌دهد
  })
  .then(function (finalResult) {
    console.log(finalResult); // "نتیجه مرحله ۱ -> نتیجه مرحله ۲"
  });
```

این الگو دقیقاً مثل زنجیره‌کردن `.then()` در Promise استاندارد جاوااسکریپت است.

## بخش دوم: `$.data()` — ذخیره داده روی المان بدون کثیف‌کردن DOM

### ۲.۱ مشکل با data-* attribute ها

می‌توانید داده را مستقیم در HTML با `data-*` بنویسید:

```html
<div id="product" data-id="42" data-price="19.99"></div>
```

```javascript
$('#product').data('id');     // 42 (عدد! jQuery خودکار تبدیل نوع می‌کند)
$('#product').data('price');  // 19.99
```

اما `$.data()` قابلیت مهم‌تری هم دارد: می‌توانید **هر نوع داده جاوااسکریپتی** (حتی آبجکت و تابع) را، بدون اینکه در HTML چاپ شود، به یک المان "بچسبانید":

```javascript
$('#product').data('fullInfo', {
  name: 'گوشی',
  tags: ['موبایل', 'الکترونیک'],
  onSale: true
});

var info = $('#product').data('fullInfo');
console.log(info.name); // 'گوشی'
```

این داده در حافظه جاوااسکریپت نگه داشته می‌شود (نه در خودِ HTML)، پس امن‌تر، سریع‌تر، و برای داده‌های پیچیده مناسب‌تر از `data-*` است.

### ۲.۲ کاربرد رایج: جلوگیری از اجرای دوباره یک تابع روی یک المان

```javascript
$('.widget').each(function () {
  var $el = $(this);
  if ($el.data('initialized')) return; // اگر قبلاً initialize شده، دوباره کاری نکن
  $el.data('initialized', true);
  // ... کد initialize ...
});
```

### ۲.۳ حذف داده

```javascript
$('#product').removeData('fullInfo'); // فقط این کلید
$('#product').removeData();             // همه داده‌های ذخیره‌شده روی این المان
```

## بخش سوم: `this` Binding و `$.proxy()`

### ۳.۱ مشکل کلاسیک از دست‌دادن `this`

```javascript
var Timer = {
  seconds: 0,
  start: function () {
    setInterval(function () {
      this.seconds++; // ❌ خطا! داخل callback معمولی، this دیگر به Timer اشاره ندارد
      console.log(this.seconds); // NaN یا خطا
    }, 1000);
  }
};
```

### ۳.۲ راه‌حل با `$.proxy()`

```javascript
var Timer = {
  seconds: 0,
  start: function () {
    setInterval($.proxy(function () {
      this.seconds++; // ✅ حالا this درست به Timer اشاره دارد
      console.log(this.seconds);
    }, this), 1000);
  }
};
```

`$.proxy(fn, context)` یک نسخه جدید از تابع `fn` می‌سازد که هر وقت اجرا شود، `this` داخلش همیشه برابر `context` خواهد بود — دقیقاً کاری که `.bind()` استاندارد جاوااسکریپت هم انجام می‌دهد:

```javascript
// معادل مدرن‌تر و امروزی‌تر (بدون نیاز به jQuery):
setInterval(function () {
  this.seconds++;
}.bind(this), 1000);

// یا ساده‌تر با Arrow Function (که this را از محیط بیرونی به ارث می‌برد):
setInterval(() => {
  this.seconds++;
}, 1000);
```

**نکته امروزی**: در پروژه‌های جدید معمولاً Arrow Function یا `.bind()` استاندارد جای `$.proxy()` را گرفته‌اند، ولی در کدهای legacy زیاد با `$.proxy()` مواجه می‌شوید، پس شناختنش لازم است.

### ۳.۳ `this` داخل event handler ها (یادآوری و تعمیق)

```javascript
$('.btn').on('click', function () {
  console.log(this); // this = خودِ DOM element کلیک‌شده (نه jQuery object)
  console.log($(this).text()); // برای استفاده متدهای jQuery باید بپیچانیمش
});
```

اگر همان تابع را با Arrow Function بنویسید، `this` دیگر به المان اشاره نمی‌کند (چون Arrow Function `this` خودش را ندارد و از محیط بیرونی به ارث می‌برد) — این یکی از رایج‌ترین اشتباهات هنگام ترکیب jQuery با سینتکس مدرن ES6 است:

```javascript
// ❌ اشتباه رایج
$('.btn').on('click', () => {
  console.log(this); // this اینجا المان کلیک‌شده نیست! به window یا محیط بیرونی اشاره دارد
});

// ✅ درست: از function عادی استفاده کنید یا از event.currentTarget بگیرید
$('.btn').on('click', (event) => {
  console.log(event.currentTarget); // این همیشه درست است، مستقل از نوع تابع
});
```

## بخش چهارم: موقعیت‌یابی المان‌ها — `offset()` در برابر `position()`

### ۴.۱ `offset()`

موقعیت المان را **نسبت به کل سند (document)** برمی‌گرداند — یعنی مستقل از اینکه چقدر صفحه اسکرول شده یا والدینش چه position ای دارند:

```javascript
var pos = $('#box').offset();
console.log(pos.top, pos.left); // مختصات نسبت به بالای/چپِ کل صفحه
```

### ۴.۲ `position()`

موقعیت المان را **نسبت به نزدیک‌ترین والد positioned** (والدی که `position: relative/absolute/fixed` دارد) برمی‌گرداند:

```javascript
var pos = $('#box').position();
console.log(pos.top, pos.left); // مختصات نسبت به والد positioned
```

**قاعده ساده برای انتخاب**: اگر می‌خواهید یک Tooltip یا Dropdown را دقیقاً کنار یک المان (با در نظر گرفتن اسکرول صفحه) قرار دهید، از `offset()` استفاده کنید. اگر می‌خواهید یک المان را نسبت به کانتینر مستقیمش جابه‌جا کنید (مثلاً یک باکس داخل یک کارت `position: relative`)، از `position()` استفاده کنید.

### ۴.۳ `scrollTop()` / `scrollLeft()`

```javascript
$(window).scrollTop();              // میزان اسکرول عمودی فعلی صفحه
$(window).scrollTop(500);              // اسکرول‌کردن برنامه‌ای به ۵۰۰ پیکسل
$('html, body').animate({ scrollTop: 0 }, 500); // اسکرول نرم به بالای صفحه (دکمه Back to Top)
```

### ۴.۴ مثال کاربردی: دکمه «بازگشت به بالا» که فقط بعد از اسکرول ظاهر می‌شود

```javascript
$(window).on('scroll', debounce(function () {
  if ($(window).scrollTop() > 300) {
    $('#backToTop').fadeIn(200);
  } else {
    $('#backToTop').fadeOut(200);
  }
}, 100));

$('#backToTop').on('click', function () {
  $('html, body').animate({ scrollTop: 0 }, 400);
});
```

(تابع `debounce` را در جلسه ۴ ساختیم؛ در فایل کد این جلسه دوباره تعریف شده تا فایل مستقل باشد.)

## ۵. تله‌های رایج

1. **برگرداندن خودِ `deferred` به‌جای `deferred.promise()`** از یک تابع → کد بیرونی می‌تواند state داخلی شما را دستکاری کند.
2. **استفاده از Arrow Function در event handler و انتظار درست‌کارکردن `this`** → همیشه از `event.currentTarget` یا function عادی استفاده کنید.
3. **قاطی‌کردن `offset()` و `position()`** → المان‌ها در جای اشتباه (معمولاً افست‌شده با مقدار اسکرول) قرار می‌گیرند.
4. **ذخیره داده‌های پیچیده (آبجکت/آرایه) در `data-*` به‌شکل رشته JSON دستی** به‌جای استفاده مستقیم از `$.data()` → کد غیرضروری پیچیده می‌شود.
5. **گوش‌دادن به scroll بدون debounce/throttle** → افت شدید عملکرد در صفحات طولانی.

## ۶. تمرین‌های پیشنهادی

1. یک زنجیره Deferred سه‌مرحله‌ای بسازید که هر مرحله ۵۰۰ میلی‌ثانیه طول بکشد و نتیجه نهایی، ترکیب هر سه پیام باشد.
2. با `$.data()` یک شمارنده کلیک اختصاصی برای هر دکمه در یک لیست از دکمه‌ها پیاده‌سازی کنید (هر دکمه شمارنده جدای خودش را دارد).
3. یک Tooltip ساده بسازید که با `offset()` دقیقاً بالای المانی که هاور شده قرار بگیرد (حتی وقتی صفحه اسکرول شده).
4. دکمه «بازگشت به بالا» بالا را کامل پیاده‌سازی کنید (نمونه آن در فایل کد این جلسه هست).

فایل `code/index.html` این جلسه، نمونه زنده هرکدام از این مفاهیم را دارد.
