# جلسه ۷ — فرم‌های پیشرفته، آپلود فایل و کار با API واقعی

## بخش اول: اعتبارسنجی سفارشی فرم (Custom Validation)

### ۱.۱ چرا اعتبارسنجی سمت کلاینت (و چرا کافی نیست)

اعتبارسنجی سمت کلاینت (Client-side) تجربه کاربری بهتر می‌دهد (خطا فوری نمایش داده می‌شود، بدون رفت‌وبرگشت به سرور)، اما **هرگز جایگزین اعتبارسنجی سمت سرور نیست**، چون کاربر می‌تواند جاوااسکریپت را دور بزند (DevTools، غیرفعال‌کردن JS، درخواست مستقیم به API). همیشه هر دو لایه لازم است.

### ۱.۲ ساختار یک اعتبارسنج ساده

```javascript
function validateForm($form) {
  var isValid = true;
  $form.find('.error-message').remove(); // پاک‌کردن خطاهای قبلی
  $form.find('.field-error').removeClass('field-error');

  $form.find('[data-validate]').each(function () {
    var $field = $(this);
    var rules = $field.data('validate').split('|'); // مثلاً "required|email"
    var value = $field.val().trim();

    for (var i = 0; i < rules.length; i++) {
      var rule = rules[i];
      var errorMsg = null;

      if (rule === 'required' && value === '') {
        errorMsg = 'این فیلد اجباری است.';
      } else if (rule === 'email' && value !== '' && !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value)) {
        errorMsg = 'ایمیل معتبر نیست.';
      } else if (rule.indexOf('min:') === 0) {
        var minLen = parseInt(rule.split(':')[1], 10);
        if (value.length < minLen) errorMsg = 'حداقل ' + minLen + ' کاراکتر لازم است.';
      }

      if (errorMsg) {
        isValid = false;
        $field.addClass('field-error');
        $field.after('<div class="error-message">' + errorMsg + '</div>');
        break; // فقط اولین خطای هر فیلد نمایش داده شود
      }
    }
  });

  return isValid;
}
```

استفاده:

```html
<input type="text" name="username" data-validate="required|min:3">
<input type="email" name="email" data-validate="required|email">
```

```javascript
$('#myForm').on('submit', function (event) {
  event.preventDefault();
  if (validateForm($(this))) {
    // فرم معتبر است -> ادامه با Ajax
  }
});
```

### ۱.۳ اعتبارسنجی زنده (Real-time) هنگام تایپ

```javascript
$('#myForm').on('blur', '[data-validate]', function () {
  // اعتبارسنجی فقط همین فیلد، وقتی کاربر از آن خارج می‌شود (blur)
  validateForm($(this).closest('form'));
});
```

⚠️ نکته UX: اعتبارسنجی روی هر keystroke (`input`) معمولاً آزاردهنده است (چون قبل از تمام‌شدن تایپ کاربر پیام خطا نشان می‌دهد). الگوی رایج و بهتر: خطای اولیه روی `blur` نشان داده شود، ولی بعد از اولین خطا، اصلاح آن با `input` بلادرنگ بررسی شود (که کاربر فوراً ببیند مشکل رفع شده).

## بخش دوم: آپلود فایل با `FormData` و `$.ajax`

### ۲.۱ چرا آپلود فایل با `$.ajax` معمولی کار نمی‌کند

آپلود فایل نیاز به `Content-Type: multipart/form-data` دارد که مرورگر باید خودش با یک "boundary" منحصربه‌فرد بسازد. برای این کار باید تنظیمات پیش‌فرض jQuery را غیرفعال کنید:

```javascript
var formData = new FormData();
formData.append('avatar', fileInputElement.files[0]); // فایل انتخاب‌شده
formData.append('username', 'ali123');                 // فیلدهای متنی هم می‌توانند همراه فایل بروند

$.ajax({
  url: '/api/upload',
  method: 'POST',
  data: formData,
  contentType: false, // ضروری: نگذار jQuery خودش Content-Type بسازد
  processData: false, // ضروری: نگذار jQuery سعی کند formData را serialize کند
  success: function (response) {
    console.log('آپلود موفق:', response);
  },
  error: function (jqXHR) {
    console.error('خطا در آپلود:', jqXHR.status);
  }
});
```

`contentType: false` و `processData: false` دو تنظیمی هستند که همیشه فراموش می‌شوند و باعث خطای مبهم سمت سرور می‌شوند — حتماً حفظشان کنید.

### ۲.۲ نمایش پیش‌نمایش تصویر قبل از آپلود

```javascript
$('#avatarInput').on('change', function () {
  var file = this.files[0];
  if (!file) return;

  if (!file.type.startsWith('image/')) {
    alert('فقط فایل تصویری مجاز است.');
    return;
  }

  var reader = new FileReader();
  reader.onload = function (event) {
    $('#previewImg').attr('src', event.target.result).show();
  };
  reader.readAsDataURL(file);
});
```

### ۲.۳ نمایش درصد پیشرفت آپلود (Progress Bar)

`$.ajax` به‌صورت پیش‌فرض دسترسی مستقیم به رویداد `progress` ندارد، اما می‌توانید با `xhr` سفارشی به آن دسترسی پیدا کنید:

```javascript
$.ajax({
  url: '/api/upload',
  method: 'POST',
  data: formData,
  contentType: false,
  processData: false,
  xhr: function () {
    var xhr = $.ajaxSettings.xhr();
    if (xhr.upload) {
      xhr.upload.addEventListener('progress', function (event) {
        if (event.lengthComputable) {
          var percent = Math.round((event.loaded / event.total) * 100);
          $('#progressBar').css('width', percent + '%').text(percent + '%');
        }
      });
    }
    return xhr;
  }
});
```

## بخش سوم: کار با یک API واقعی و مفهوم CORS

### ۳.۱ نمونه: JSONPlaceholder (یک API عمومی رایگان برای تست)

```javascript
$.getJSON('https://jsonplaceholder.typicode.com/users', function (users) {
  var html = '';
  $.each(users, function (i, user) {
    html += '<li>' + user.name + ' — ' + user.email + '</li>';
  });
  $('#userList').html(html);
});
```

### ۳.۲ CORS چیست؟

CORS (Cross-Origin Resource Sharing) یک مکانیزم امنیتی مرورگر است: اگر صفحه شما روی دامنه A باشد و بخواهد با Ajax به API دامنه B درخواست بزند، مرورگر این درخواست را فقط در صورتی اجازه می‌دهد که **سرور B صریحاً با هدر `Access-Control-Allow-Origin` اجازه داده باشد**.

اگر با خطای شبیه این مواجه شدید:

```
Access to XMLHttpRequest at 'https://api.example.com/...' from origin 'https://mysite.com'
has been blocked by CORS policy
```

این خطا **از سمت جاوااسکریپت/jQuery قابل حل نیست** — باید سرور مقصد هدر CORS را اضافه کند، یا شما درخواست را از طریق سرور خودتان (Proxy) پاس بدهید. این یک تله بسیار رایج برای مبتدیانی است که فکر می‌کنند مشکل از کدشان است.

### ۳.۳ ترکیب jQuery با `localStorage`

`localStorage` API خودِ مرورگر است (نه jQuery)، ولی معمولاً در کنار jQuery برای ذخیره داده سبک استفاده می‌شود (مثل تنظیمات کاربر، پیش‌نویس فرم):

```javascript
// ذخیره
$('#draftForm').on('input', 'textarea', function () {
  localStorage.setItem('draftText', $(this).val());
});

// بازیابی هنگام بارگذاری صفحه
$(document).ready(function () {
  var saved = localStorage.getItem('draftText');
  if (saved) {
    $('#draftForm textarea').val(saved);
  }
});
```

⚠️ نکته: `localStorage` فقط رشته (string) ذخیره می‌کند. برای آبجکت/آرایه باید با `JSON.stringify()` قبل از ذخیره و `JSON.parse()` بعد از خواندن تبدیل کنید:

```javascript
localStorage.setItem('userPrefs', JSON.stringify({ theme: 'dark', lang: 'fa' }));
var prefs = JSON.parse(localStorage.getItem('userPrefs') || '{}');
```

## ۴. تله‌های رایج

1. **فراموش‌کردن `contentType: false` و `processData: false`** هنگام آپلود فایل با FormData → خطای مبهم سرور یا آپلود فایل خراب.
2. **اعتبارسنجی فقط سمت کلاینت و اعتماد کامل به آن** → مشکل امنیتی جدی، چون سمت کلاینت همیشه قابل دور زدن است.
3. **تصور اینکه خطای CORS از کدِ Ajax است** → این خطا همیشه باید در سمت سرور (backend) حل شود.
4. **ذخیره آبجکت مستقیم در `localStorage` بدون `JSON.stringify`** → مقدار ذخیره‌شده رشته‌ای بی‌معنی مثل `"[object Object]"` خواهد بود.
5. **اعتبارسنجی روی هر keystroke بدون debounce** → پیام‌های خطای آزاردهنده و لرزان حین تایپ.

## ۵. تمرین‌های پیشنهادی

1. اعتبارسنج بالا را طوری گسترش دهید که یک قانون جدید `match:fieldName` اضافه کنید (برای تکرار رمز عبور).
2. یک فرم آپلود عکس با پیش‌نمایش زنده (قبل از ارسال) بسازید.
3. با `$.getJSON` به JSONPlaceholder وصل شوید و یک لیست کاربران با قابلیت جستجوی زنده (مثل جلسه ۴) بسازید.
4. یک فرم «یادداشت سریع» بسازید که محتوایش خودکار در `localStorage` ذخیره شود و بعد از رفرش صفحه دوباره ظاهر شود.

فایل `code/index.html` این جلسه، اعتبارسنجی سفارشی، شبیه‌سازی آپلود فایل با progress bar، اتصال واقعی به JSONPlaceholder (نیاز به اینترنت دارد) و ذخیره در localStorage را کامل پیاده‌سازی کرده است.
