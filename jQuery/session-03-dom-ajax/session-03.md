# جلسه ۳ — پیمایش پیشرفته DOM، ساخت/حذف المان و Ajax

## بخش اول: پیمایش (Traversing) پیشرفته DOM

پیمایش یعنی از یک المان به المان‌های مرتبط با آن (والد، فرزند، خواهر/برادر) حرکت کنیم، بدون نیاز به نوشتن سلکتور جدید از صفر.

### ۱.۱ حرکت به سمت والدین

```javascript
$('.child').parent();          // مستقیم‌ترین والد (یک سطح بالا)
$('.child').parents();           // همه اجداد تا <html> (از نزدیک به دور)
$('.child').parents('.card');      // فقط اجدادی که کلاس card دارند
$('.child').parentsUntil('.wrapper'); // اجداد تا رسیدن به .wrapper (بدون خودش)
$('.child').closest('.card');           // نزدیک‌ترین جد (یا خودش) که با سلکتور مچ شود — خیلی پرکاربرد!
```

⚠️ تفاوت مهم `closest` با `parents`: `closest` از خودِ المان شروع می‌کند و به محض پیدا کردن اولین match متوقف می‌شود (فقط یک نتیجه). `parents('.card')` همه اجداد منطبق را برمی‌گرداند (می‌تواند چندتا باشد).

### ۱.۲ حرکت به سمت فرزندان

```javascript
$('.parent').children();          // فقط فرزندان مستقیم (نه نوه‌ها)
$('.parent').children('.active');   // فرزندان مستقیم با فیلتر کلاس
$('.parent').find('.deep-item');      // همه نوادگان در هر عمقی که match شوند
```

⚠️ تفاوت `children()` و `find()`: `children` فقط یک سطح پایین می‌رود، `find` در تمام عمق DOM جستجو می‌کند. `find` معمولاً بیشتر استفاده می‌شود.

### ۱.۳ حرکت در سطح خواهر/برادر (Siblings)

```javascript
$('.item').siblings();          // همه خواهر/برادرهای هم‌سطح (بدون خودش)
$('.item').next();                // خواهر/برادر بلافاصله بعدی
$('.item').prev();                  // خواهر/برادر بلافاصله قبلی
$('.item').nextAll();                 // همه بعدی‌ها
$('.item').prevAll();                   // همه قبلی‌ها
$('.item').nextUntil('.stop-here');       // بعدی‌ها تا رسیدن به یک سلکتور خاص
```

### ۱.۴ فیلتر کردن نتایج

```javascript
$('li').filter('.active');       // فقط آن‌هایی که با سلکتور match می‌شوند
$('li').not('.disabled');          // همه به‌جز آن‌هایی که match می‌شوند
$('li').has('.badge');               // فقط آن‌هایی که یک فرزند .badge دارند
$('li').is('.active');                 // بولین: آیا (اولین عضو) این شرط را دارد؟
$('li').first();                         // اولین
$('li').last();                            // آخرین
$('li').eq(3);                               // شاخص مشخص
```

### ۱.۵ چرا پیمایش بهتر از نوشتن سلکتور جدید است؟

```javascript
// ❌ کدی که با کوچک‌ترین تغییر در ساختار HTML می‌شکند
$('.card .actions .delete-btn').on('click', ...);

// ✅ کدی که نسبت به تغییر ساختار مقاوم‌تر است، چون از خودِ المان کلیک‌شده شروع می‌کند
$(document).on('click', '.delete-btn', function () {
  var $card = $(this).closest('.card'); // هر جای card که باشد پیدایش می‌کند
  $card.remove();
});
```

## بخش دوم: ساخت، درج و حذف المان‌ها

### ۲.۱ ساخت المان جدید

```javascript
var $newDiv = $('<div>').addClass('card').text('محتوای جدید');
// یا مستقیم با HTML:
var $newLi = $('<li class="item">آیتم جدید</li>');
```

### ۲.۲ درج المان‌ها در DOM

| متد | رفتار |
|---|---|
| `.append(content)` | افزودن به **انتهای داخل** المان |
| `.prepend(content)` | افزودن به **ابتدای داخل** المان |
| `.after(content)` | افزودن **بعد از** المان (بیرون از آن، هم‌سطح) |
| `.before(content)` | افزودن **قبل از** المان |
| `.appendTo(target)` | برعکس append — خودِ این المان را به انتهای target اضافه می‌کند |
| `.prependTo(target)` | برعکس prepend |

```javascript
$('#list').append('<li>آیتم آخر</li>');
$('#list').prepend('<li>آیتم اول</li>');
$('#targetDiv').after('<p>بعد از targetDiv</p>');
$('<span>جدید</span>').appendTo('#container');
```

### ۲.۳ حذف و خالی‌کردن

```javascript
$('.item').remove();     // حذف کامل المان (و رویدادهای متصل به آن) از DOM
$('.item').empty();        // خالی‌کردن محتوای داخلی، خودِ المان می‌ماند
$('.item').detach();         // مثل remove ولی داده‌ها/رویدادها حفظ می‌شوند (برای اتصال دوباره بعداً)
```

**تفاوت کلیدی remove/detach**: اگر می‌خواهید یک المان را موقتاً از DOM خارج کنید ولی بعداً همان المان (با همان event handler ها) را دوباره جای دیگری بچسبانید، از `detach()` استفاده کنید نه `remove()`.

### ۲.۴ کلون‌کردن

```javascript
var $copy = $('.template').clone(); // کپی کامل (بدون event handler ها به‌صورت پیش‌فرض)
var $copyWithEvents = $('.template').clone(true); // کپی همراه با event handler ها
```

## بخش سوم: Ajax در jQuery

### ۳.۱ چرا Ajax؟

Ajax یعنی ارسال درخواست به سرور و دریافت پاسخ **بدون رفرش‌کردن صفحه**. jQuery این کار را که در جاوااسکریپت خالص با `XMLHttpRequest` کمی طولانی بود، به یک تابع ساده تبدیل می‌کند.

### ۳.۲ `$.ajax()` — متد اصلی و کامل

```javascript
$.ajax({
  url: 'https://api.example.com/users',
  method: 'GET',              // یا 'POST', 'PUT', 'DELETE'
  data: { page: 1 },          // پارامترهای query string (برای GET) یا body (برای POST)
  dataType: 'json',           // نوع پاسخ مورد انتظار: json, html, text, xml
  timeout: 5000,               // میلی‌ثانیه، بعد از این مدت درخواست لغو می‌شود
  headers: {                   // هدرهای دلخواه (مثل توکن احراز هویت)
    'Authorization': 'Bearer xyz'
  }
})
.done(function (response) {
  console.log('موفق:', response);
})
.fail(function (jqXHR, textStatus, errorThrown) {
  console.error('خطا:', textStatus, errorThrown);
})
.always(function () {
  console.log('در هر صورت (چه موفق چه ناموفق) این اجرا می‌شود.');
});
```

### ۳.۳ متدهای میان‌بر (Shorthand)

```javascript
// GET ساده
$.get('https://api.example.com/users', function (data) {
  console.log(data);
});

// POST ساده
$.post('https://api.example.com/users', { name: 'علی' }, function (data) {
  console.log('کاربر ساخته شد:', data);
});

// دریافت مستقیم JSON (مطمئن‌ترین حالت برای API های JSON)
$.getJSON('https://api.example.com/users', function (data) {
  console.log(data);
});
```

### ۳.۴ Deferred / Promise — مفهوم پیشرفته

`$.ajax()` (و بسیاری متدهای دیگر jQuery مثل انیمیشن‌ها) یک شیء **jqXHR** برمی‌گرداند که یک Promise-like است (شبیه `Promise` استاندارد جاوااسکریپت ولی با API خودِ jQuery: `.done()`, `.fail()`, `.always()` به‌جای `.then()`, `.catch()`, `.finally()`).

```javascript
var request = $.ajax({ url: '/api/data' });

request.done(function (data) { console.log('done', data); });
request.fail(function () { console.log('fail'); });

// چون chainable است می‌توان مستقیم نوشت:
$.ajax({ url: '/api/data' }).done(fn1).fail(fn2);
```

### ۳.۵ `$.when()` — اجرای چند درخواست هم‌زمان و صبر برای همه

```javascript
var req1 = $.get('/api/users');
var req2 = $.get('/api/posts');

$.when(req1, req2).done(function (usersResp, postsResp) {
  // usersResp و postsResp هرکدام آرایه‌ای هستند: [data, textStatus, jqXHR]
  var users = usersResp[0];
  var posts = postsResp[0];
  console.log(users, posts);
});
```

این معادل `Promise.all()` در جاوااسکریپت مدرن است، منتهی با syntax قدیمی‌تر jQuery.

### ۳.۶ مدیریت خطا به‌درستی

```javascript
$.ajax({ url: '/api/data' })
  .done(function (data) {
    render(data);
  })
  .fail(function (jqXHR, textStatus) {
    if (jqXHR.status === 404) {
      showError('داده پیدا نشد.');
    } else if (textStatus === 'timeout') {
      showError('زمان درخواست تمام شد.');
    } else {
      showError('خطای ناشناخته: ' + textStatus);
    }
  });
```

### ۳.۷ سریالایز کردن فرم برای ارسال با Ajax

```javascript
$('#myForm').on('submit', function (event) {
  event.preventDefault(); // جلوگیری از رفرش صفحه

  var formData = $(this).serialize();
  // مثال خروجی: "name=Ali&email=ali%40test.com"

  $.ajax({
    url: '/api/submit',
    method: 'POST',
    data: formData
  }).done(function () {
    alert('فرم با موفقیت ارسال شد.');
  });
});
```

متد مرتبط `serializeArray()` هم داده‌ها را به‌شکل آرایه‌ای از آبجکت‌های `{name, value}` برمی‌گرداند که برای پردازش سفارشی‌تر مفید است.

### ۳.۸ AJAX Setup سراسری (پیشرفته)

اگر می‌خواهید در همه درخواست‌های Ajax پروژه یک هدر مشترک (مثل توکن) بفرستید:

```javascript
$.ajaxSetup({
  headers: { 'Authorization': 'Bearer ' + myToken },
  timeout: 8000
});
// از این پس دیگر نیازی نیست در هر $.ajax جداگانه این‌ها را تکرار کنید
```

⚠️ نکته: در پروژه‌های بزرگ، `$.ajaxSetup` می‌تواند غیرقابل‌پیش‌بینی شود چون تنظیمات سراسری روی همه درخواست‌ها (حتی کتابخانه‌های دیگر) اثر می‌گذارد. جایگزین ایمن‌تر، ساخت یک تابع wrapper اختصاصی است:

```javascript
function apiRequest(options) {
  return $.ajax($.extend({}, {
    headers: { 'Authorization': 'Bearer ' + myToken },
    timeout: 8000
  }, options));
}
```

## ۴. تله‌های رایج

1. **فراموش‌کردن `event.preventDefault()`** روی submit فرم → صفحه رفرش می‌شود و Ajax اصلاً دیده نمی‌شود.
2. **استفاده از `remove()` به‌جای `detach()`** وقتی قرار است المان بعداً دوباره استفاده شود → event handler ها از دست می‌روند.
3. **قاطی‌کردن `children()` و `find()`** → انتظار پیدا کردن نوادگان عمیق با `children()` (که فقط یک سطح می‌رود).
4. **عدم مدیریت خطای Ajax (`fail`)** → اگر سرور خطا بدهد یا اینترنت قطع شود، کاربر هیچ بازخوردی نمی‌بیند.
5. **ارسال دیتای حساس با GET** → پارامترهای GET در URL و تاریخچه مرورگر قابل مشاهده‌اند؛ برای داده حساس از POST استفاده کنید.

## ۵. تمرین‌های پیشنهادی

1. یک لیست کارت (`.card`) با دکمه حذف داخل هرکدام بسازید؛ با `closest('.card')` کارت مربوطه را پیدا و با `remove()` حذف کنید.
2. با استفاده از `$.getJSON` به یک API عمومی و رایگان (مثلاً JSONPlaceholder) وصل شوید و لیست پست‌ها را در صفحه رندر کنید (نمونه در فایل کد این جلسه با شبیه‌سازی داخلی آمده تا بدون نیاز به اینترنت هم قابل تست باشد).
3. یک فرم بسازید، با `serialize()` مقادیرش را بگیرید و در کنسول چاپ کنید (به‌جای ارسال واقعی).
4. با `clone(true)` یک تمپلیت کارت را کپی کنید و بررسی کنید که رویداد کلیکش هم منتقل شده.

فایل `code/index.html` این جلسه، نمونه زنده هرکدام از این مفاهیم (از جمله یک Ajax شبیه‌سازی‌شده با تاخیر مصنوعی، بدون نیاز به اتصال اینترنت) را دارد.
