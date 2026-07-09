# جلسه ۲ — رویدادها (Events) و افکت‌ها/انیمیشن‌ها

## ۱. مدل رویداد در jQuery

رویداد (Event) اتفاقی است که در صفحه می‌افتد: کلیک، تایپ، اسکرول، تغییر فوکوس و... . jQuery یک لایه یکسان و ساده روی مدل رویداد مرورگر می‌گذارد.

### ۱.۱ متد اصلی: `.on()`

از نسخه ۱.۷ به بعد، `.on()` روش استاندارد و توصیه‌شده برای وصل‌کردن رویداد است (متدهای قدیمی‌تر مثل `.click()`, `.bind()`, `.live()`, `.delegate()` همه یا Deprecated شده‌اند یا زیر پوستشان از `.on()` استفاده می‌کنند):

```javascript
$('#btn').on('click', function (event) {
  console.log('کلیک شد!');
});

// معادل قدیمی‌تر (هنوز کار می‌کند ولی توصیه نمی‌شود برای پروژه‌های جدید):
$('#btn').click(function () { ... });
```

### ۱.۲ چند رویداد با یک فراخوانی

```javascript
$('#input1').on('focus blur', function (event) {
  console.log('نوع رویداد: ' + event.type); // focus یا blur
});

// یا با آبجکت، هر رویداد یک هندلر جدا:
$('#input1').on({
  focus: function () { $(this).addClass('focused'); },
  blur:  function () { $(this).removeClass('focused'); }
});
```

### ۱.۳ حذف رویداد با `.off()`

```javascript
function handler() { alert('سلام'); }
$('#btn').on('click', handler);
$('#btn').off('click', handler); // فقط همین هندلر حذف می‌شود
$('#btn').off('click');            // همه هندلرهای click حذف می‌شوند
$('#btn').off();                     // همه رویدادها حذف می‌شوند
```

### ۱.۴ اجرای یک‌باره با `.one()`

```javascript
$('#btn').one('click', function () {
  alert('این فقط یک بار اجرا می‌شود، حتی اگر بارها کلیک کنید');
});
```

## ۲. شیء Event

هر تابع هندلر یک پارامتر `event` می‌گیرد (یک شیء نرمال‌شده jQuery، مستقل از مرورگر):

```javascript
$('#btn').on('click', function (event) {
  console.log(event.type);      // نوع رویداد: click
  console.log(event.target);    // المانی که واقعاً رویداد رویش رخ داد
  console.log(event.currentTarget); // المانی که هندلر رویش وصل شده (معمولاً برابر this)
  console.log(event.pageX, event.pageY); // موقعیت ماوس
  event.preventDefault();       // جلوگیری از رفتار پیش‌فرض (مثلاً submit فرم یا رفتن به لینک)
  event.stopPropagation();      // جلوگیری از bubble‌شدن رویداد به والدها
});
```

⚠️ نکته: `event.target` و `this` (که همان `event.currentTarget` است) می‌توانند متفاوت باشند، به‌خصوص در Event Delegation که در ادامه توضیح می‌دهیم.

## ۳. Event Delegation (تفویض رویداد) — یکی از مهم‌ترین مفاهیم jQuery

فرض کنید لیستی دارید که آیتم‌هایش پویا (Dynamic) با جاوااسکریپت اضافه می‌شوند:

```javascript
// ❌ مشکل: این فقط روی آیتم‌هایی که *در زمان اجرای این خط* وجود دارند کار می‌کند
$('#list li').on('click', function () {
  $(this).toggleClass('done');
});
// اگر بعداً یک <li> جدید اضافه شود، رویداد کلیک رویش کار نمی‌کند!
```

راه‌حل: به‌جای وصل‌کردن رویداد مستقیم به هر `li`، آن را به یک والد ثابت (که همیشه در صفحه وجود دارد) وصل می‌کنیم و یک سلکتور فیلتر (delegate selector) به‌عنوان آرگومان دوم می‌دهیم:

```javascript
// ✅ درست: رویداد روی #list (ثابت) وصل می‌شود
// و jQuery با bubbling بررسی می‌کند که رویداد از کدام li آمده
$('#list').on('click', 'li', function () {
  $(this).toggleClass('done'); // this همان li کلیک‌شده است
});

// حالا حتی li هایی که بعداً اضافه شوند هم کار می‌کنند:
$('#list').append('<li>آیتم جدید</li>');
```

**چرا کار می‌کند؟** رویدادها در DOM از پایین به بالا حباب می‌زنند (Bubbling). jQuery روی `#list` گوش می‌دهد؛ وقتی روی هر `li` کلیک شود، رویداد تا `#list` بالا می‌آید، jQuery بررسی می‌کند «آیا منشأ این رویداد یک `li` بوده؟» و اگر بله، هندلر را اجرا می‌کند.

**قاعده طلایی**: هر وقت المان‌های شما به‌صورت پویا اضافه/حذف می‌شوند (لیست‌ها، جدول‌ها، کارت‌ها)، همیشه از Event Delegation استفاده کنید.

## ۴. Namespacing رویدادها

برای مدیریت بهتر (مخصوصاً هنگام حذف رویداد) می‌توان به رویداد یک "فضای نام" داد:

```javascript
$('#btn').on('click.myPlugin', function () { ... });
$('#btn').on('mouseenter.myPlugin', function () { ... });

// حذف فقط رویدادهایی که namespace شان myPlugin است
// بدون اینکه به رویدادهای click دیگری که کد شما نوشته آسیب بزند:
$('#btn').off('.myPlugin');
```

این تکنیک بسیار در نوشتن پلاگین‌ها (جلسه ۴) کاربرد دارد.

## ۵. رویدادهای رایج

| رویداد | زمان وقوع |
|---|---|
| `click` | کلیک |
| `dblclick` | دابل‌کلیک |
| `mouseenter` / `mouseleave` | ورود/خروج ماوس (بدون bubbling برخلاف mouseover/mouseout) |
| `mouseover` / `mouseout` | ورود/خروج ماوس (با bubbling، روی فرزندان هم تریگر می‌شود) |
| `keydown` / `keyup` / `keypress` | فشردن/رهاکردن کلید صفحه‌کلید |
| `focus` / `blur` | فوکوس گرفتن/از دست‌دادن input |
| `change` | تغییر مقدار input/select بعد از از دست دادن فوکوس |
| `input` | تغییر مقدار input در لحظه تایپ (بلادرنگ) |
| `submit` | ارسال فرم |
| `resize` | تغییر اندازه پنجره (روی `$(window)`) |
| `scroll` | اسکرول‌کردن (روی `$(window)` یا یک container) |

```javascript
$(window).on('resize', function () {
  console.log('عرض جدید: ' + $(window).width());
});

$('#searchInput').on('input', function () {
  console.log('کاربر در حال تایپ است: ' + $(this).val());
});
```

## ۶. افکت‌ها و انیمیشن‌های آماده

jQuery مجموعه‌ای از متدهای انیمیشن آماده دارد که با یک خط، Transition نرم ایجاد می‌کنند:

```javascript
$('#box').hide();        // مخفی فوری
$('#box').show();          // نمایش فوری
$('#box').toggle();          // تغییر وضعیت نمایش/مخفی

$('#box').fadeIn(400);         // محو شدن به داخل (شفافیت ۰ تا ۱) در ۴۰۰ms
$('#box').fadeOut(400);          // محو شدن به بیرون
$('#box').fadeToggle(400);         // تاگل fade

$('#box').slideDown(300);            // باز شدن به‌صورت کشویی (ارتفاع)
$('#box').slideUp(300);                // بسته شدن کشویی
$('#box').slideToggle(300);              // تاگل اسلاید

// همه این متدها آرگومان دوم callback هم می‌پذیرند که بعد از پایان انیمیشن اجرا می‌شود:
$('#box').fadeOut(400, function () {
  console.log('انیمیشن fadeOut تمام شد.');
  $(this).remove(); // حذف کامل از DOM بعد از محو شدن
});
```

سرعت‌های آماده: `'slow'` (۶۰۰ms) و `'fast'` (۲۰۰ms) هم قابل استفاده‌اند: `$('#box').fadeIn('slow')`.

## ۷. متد `.animate()` — انیمیشن سفارشی

با `.animate()` می‌توانید هر خاصیت CSS عددی (که انیمیشن‌پذیر باشد) را به‌دلخواه تغییر دهید:

```javascript
$('#box').animate({
  width: '300px',
  height: '200px',
  opacity: 0.5,
  marginLeft: '50px'
}, 800, 'swing', function () {
  console.log('انیمیشن سفارشی تمام شد');
});
```

پارامترها به‌ترتیب: آبجکت خصوصیات مقصد، مدت‌زمان (ms)، Easing (نحوه شتاب‌گیری: `'swing'` پیش‌فرض یا `'linear'`)، callback پایان.

⚠️ محدودیت مهم: `.animate()` فقط روی خصوصیات **عددی** کار می‌کند (width, height, opacity, margin, ...) و نمی‌تواند رنگ (`color`, `background-color`) را انیمیت کند مگر پلاگین jQuery UI اضافه شود. برای انیمیشن رنگ، بهتر است از CSS Transition استفاده کنید:

```css
#box { transition: background-color 0.4s ease; }
```

```javascript
$('#box').css('background-color', '#e74c3c'); // با transition CSS نرم تغییر می‌کند
```

**توصیه امروزی**: برای انیمیشن‌های ساده (رنگ، transform، سایه)، ترکیب `addClass`/`removeClass` + CSS Transition معمولاً از `.animate()` سریع‌تر و روان‌تر است (چون از GPU و CSS Engine مرورگر استفاده می‌کند، نه محاسبه جاوااسکریپتی فریم به فریم).

## ۸. صف انیمیشن (fx Queue) — مفهوم پیشرفته

وقتی چند انیمیشن پشت‌سرهم روی یک المان صدا بزنید، jQuery آن‌ها را به‌صورت خودکار در یک «صف» (queue) قرار می‌دهد و یکی‌یکی اجرا می‌کند (نه هم‌زمان):

```javascript
$('#box').fadeOut(300).fadeIn(300).slideUp(300).slideDown(300);
// این چهار انیمیشن پشت‌سرهم (نه هم‌زمان) اجرا می‌شوند
```

### `.delay()`

برای ایجاد مکث بین انیمیشن‌ها بدون `setTimeout`:

```javascript
$('#box').fadeOut(300).delay(1000).fadeIn(300);
```

### `.stop()` و `.finish()`

اگر کاربر سریع چند بار روی دکمه‌ای کلیک کند که انیمیشن اجرا می‌کند، انیمیشن‌ها در صف جمع می‌شوند و رفتار عجیب ایجاد می‌کنند. راه‌حل استاندارد:

```javascript
$('#btn').on('click', function () {
  $('#box').stop(true, true).slideToggle(300);
  // آرگومان اول (clearQueue): همه انیمیشن‌های صف را پاک کن
  // آرگومان دوم (jumpToEnd): انیمیشن در حال اجرا را فوراً به مقصد نهایی برسان
});
```

`.finish()` مشابه `stop(true, true)` است ولی علاوه بر آن همه انیمیشن‌های صف را هم فوراً به حالت نهایی می‌رساند (نه فقط اولی).

**قاعده طلایی**: هر جا انیمیشن با کلیک تکراری تریگر می‌شود، همیشه قبل از آن `.stop(true, true)` بگذارید.

## ۹. ترکیب Event + Effect (الگوی رایج UI)

```javascript
$('.accordion-header').on('click', function () {
  var $panel = $(this).next('.accordion-panel');
  $('.accordion-panel').not($panel).slideUp(200); // بستن بقیه پنل‌ها
  $panel.stop(true, true).slideToggle(200);
});
```

## ۱۰. تله‌های رایج

1. **وصل‌کردن مستقیم رویداد به المان‌های پویا** بدون Delegation → رویداد کار نمی‌کند.
2. **فراموش‌کردن `.stop()`** قبل از انیمیشن جدید → صف طولانی و رفتار لگ‌دار.
3. **استفاده از `mouseover`/`mouseout` وقتی منظور `mouseenter`/`mouseleave` است** → به‌خاطر bubbling، رویداد چندین‌بار روی فرزندان تریگر می‌شود (مثلاً افکت hover چشمک می‌زند).
4. **تلاش برای انیمیت‌کردن رنگ با `.animate()` بدون jQuery UI** → کار نمی‌کند، سکوت خطا هم نمی‌دهد.
5. **فراموش‌کردن `event.preventDefault()`** روی فرم‌هایی که با Ajax مدیریت می‌کنید → صفحه رفرش می‌شود.

## ۱۱. تمرین‌های پیشنهادی

1. یک آکاردئون (Accordion) با ۳ بخش بسازید که با کلیک روی هر عنوان، محتوایش با `slideToggle` باز/بسته شود و بقیه بسته شوند.
2. یک لیست To-Do بسازید که با دکمه "افزودن" آیتم جدید اضافه شود و با Event Delegation بتوان روی آیتم‌های جدید هم کلیک کرد تا خط‌خورده (done) شوند.
3. یک باکس بسازید که با هاور (`mouseenter`/`mouseleave`) رنگش با CSS Transition تغییر کند (نه animate).
4. یک دکمه بسازید که با کلیک سریع و پشت‌سرهم تست کنید چه اتفاقی بدون `.stop()` می‌افتد، سپس با `.stop(true,true)` رفتار را اصلاح کنید.

فایل `code/index.html` این جلسه، نمونه زنده هرکدام از این مفاهیم را دارد.
