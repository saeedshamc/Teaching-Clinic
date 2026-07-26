# 📝 jQuery Code Snippets - مرجع سریع کدهای مهم

## جلسه ۱: سلکتورها

```javascript
// سلکتور پایه
$('div')                      // همه div ها
$('.classname')               // کلاس
$('#idname')                  // ID
$('div.card')                 // ترکیب

// سلکتور jQuery اختصاصی
$('li:first')                 // اولی
$('li:last')                  // آخری
$('li:eq(2)')                 // index ۲
$('li:even')                  // زوج‌ها

// خواندن/نوشتن
$('#box').text('متن جدید')
$('#box').html('<b>HTML</b>')
$('#input').val('مقدار')
$('#box').attr('data-id', '123')
$('#check').prop('checked', true)
$('#box').css('color', 'red')
$('#box').addClass('active')
```

---

## جلسه ۲: رویدادها و انیمیشن

```javascript
// رویدادها
$('#btn').on('click', function() {
  console.log('کلیک شد');
});

// Event Delegation
$('#list').on('click', '.item', function() {
  $(this).addClass('selected');
});

// افکت‌ها
$('#box').fadeIn(400)
$('#box').slideToggle(300)
$('#box').animate({ width: '200px' }, 800)

// صف انیمیشن
$('#box')
  .fadeOut(300)
  .delay(200)
  .fadeIn(300)
  .stop()
```

---

## جلسه ۳: DOM و Ajax

```javascript
// پیمایش
$('#item').parent()
$('#list').find('.item')
$('#item').siblings()
$('#item').closest('.container')

// ساخت و درج
$('#list').append('<li>جدید</li>')
$('#list').prepend('<li>اول</li>')
$('#item').after('<li>بعد</li>')
$('#list').remove()

// Ajax
$.ajax({
  url: 'data.php',
  method: 'GET',
  success: function(data) { console.log(data) },
  error: function() { console.log('خطا') }
})

// Promise
$.get('/api').done(function(data) {
  console.log(data)
}).fail(function() {
  console.log('ناموفق')
})
```

---

## جلسه ۴: پلاگین

```javascript
// ساخت پلاگین
$.fn.highlight = function(color) {
  this.css('background-color', color)
  return this  // برای chaining
}

// استفاده
$('#box').highlight('yellow').fadeIn()
```

---

## جلسه ۵: Deferred و This Binding

```javascript
// Deferred
var dfd = $.Deferred()
dfd.done(function() { console.log('موفق') })
dfd.fail(function() { console.log('ناموفق') })
dfd.resolve()

// Proxy
var myFunc = $.proxy(function() { console.log(this) }, obj)
myFunc()

// Bind
var boundFunc = myFunc.bind(obj)
```

---

## جلسه ۶: UI Components

```javascript
// Tabs ساده
$('.tab-content').hide()
$('#tab-1').show()
$('.tab').on('click', function() {
  var id = $(this).data('id')
  $('.tab-content').hide()
  $('#tab-' + id).show()
})

// Modal
$('#modal').fadeIn()
$('#closeBtn').on('click', function() {
  $('#modal').fadeOut()
})
```

---

## جلسه ۷: فرم و API

```javascript
// اعتبارسنجی فرم
$('#form').on('submit', function(e) {
  e.preventDefault()
  var email = $('#email').val()
  if (!email) {
    $('#error').text('ایمیل را وارد کنید').show()
  }
})

// آپلود فایل
var formData = new FormData()
formData.append('file', $('#file')[0].files[0])
$.ajax({
  url: '/upload',
  type: 'POST',
  data: formData,
  processData: false,
  contentType: false
})

// API
$.get('https://jsonplaceholder.typicode.com/posts/1', function(data) {
  console.log(data)
})
```

---

## جلسه ۸: معماری

```javascript
// Module Pattern
var MyApp = (function() {
  var privateVar = 'خصوصی'
  
  function privateFunc() {}
  
  return {
    init: function() {
      privateFunc()
    },
    publicMethod: function() {
      console.log(privateVar)
    }
  }
})()

MyApp.init()

// Event Bus
$(document).trigger('event:name', [data])
$(document).on('event:name', function(e, data) {
  console.log(data)
})
```

---

## جلسه ۹: DOM Traversal

```javascript
// Traversal
$('#item').parent()         // والد
$('#item').parents()        // همه والدها
$('#list').children()       // فرزندان
$('#list').find('li')       // جستجو
$('#item').siblings()       // برادرها
$('#item').next()           // بعدی
$('#item').prev()           // قبلی
$('#item').closest('.box')  // نزدیک‌ترین والد
```

---

## جلسه ۱۰: انیمیشن

```javascript
// افکت‌های پایه
$('#box').fadeIn(300)
$('#box').fadeOut(300)
$('#box').slideDown(300)
$('#box').slideUp(300)
$('#box').slideToggle(300)

// انیمیشن سفارشی
$('#box').animate({
  width: '200px',
  opacity: 0.5,
  marginLeft: '50px'
}, 800)

// Queue
$('#box').stop()
$('#box').finish()
$('#box').delay(500)
```

---

## جلسه ۱۱: Ajax

```javascript
// GET
$.get('/api/data', function(data) {
  console.log(data)
})

// POST
$.post('/api/save', { name: 'علی' }, function(result) {
  console.log(result)
})

// AJAX کامل
$.ajax({
  url: '/api/endpoint',
  method: 'POST',
  data: JSON.stringify({ key: 'value' }),
  contentType: 'application/json',
  dataType: 'json',
  timeout: 5000
}).done(function(data) {
  console.log('موفق:', data)
}).fail(function(xhr) {
  console.error('خطا:', xhr.status)
}).always(function() {
  console.log('انجام شد')
})
```

---

## جلسه ۱۲: فرم

```javascript
// خواندن مقادیر
var name = $('#name').val()
var checked = $('#chk').prop('checked')
var email = $('#email').attr('value')

// رویدادهای فرم
$('#form').on('submit', function(e) {
  e.preventDefault()
  // ...
})

$('#input').on('keyup', function() {
  console.log($(this).val())
})

$('#select').on('change', function() {
  console.log($(this).val())
})
```

---

## جلسه ۱۳: localStorage

```javascript
// ذخیره
localStorage.setItem('name', 'علی')

// خواندن
var name = localStorage.getItem('name')

// حذف
localStorage.removeItem('name')

// خالی کردن
localStorage.clear()

// مثال عملی
var todos = JSON.parse(localStorage.getItem('todos') || '[]')
todos.push('کار جدید')
localStorage.setItem('todos', JSON.stringify(todos))
```

---

## جلسه ۱۴: Event Delegation

```javascript
// رویداد پویا
$('#list').on('click', '.item', function() {
  $(this).toggleClass('active')
})

// حذف رویداد
$('#list').off('click', '.item')

// رویدادهای کلید
$(document).on('keydown', function(e) {
  if (e.keyCode === 13) {
    console.log('Enter فشردید')
  }
})
```

---

## جلسه ۱۵: پلاگین

```javascript
// پلاگین با Option
$.fn.notify = function(options) {
  var settings = $.extend({
    duration: 3000,
    message: 'اطلاع'
  }, options)
  
  return this.each(function() {
    $(this).text(settings.message)
    $(this).fadeIn().delay(settings.duration).fadeOut()
  })
}

// استفاده
$('#msg').notify({ message: 'سلام!' })
```

---

## جلسه ۱۶: متدهای کمکی

```javascript
// each
$.each([1, 2, 3], function(i, val) {
  console.log(i, val)
})

// map
var doubled = $.map([1, 2, 3], function(val) {
  return val * 2
})

// grep
var filtered = $.grep([1, 2, 3, 4], function(val) {
  return val > 2
})

// extend
var obj = $.extend({}, {a: 1}, {b: 2})
```

---

## جلسه ۱۷: عملکرد

```javascript
// Cache selector
var $items = $('#list .item')
$items.addClass('active')

// Event Delegation بجای رویداد جداگانه
// ❌ بد
$('.item').on('click', function() { })

// ✅ خوب
$('#list').on('click', '.item', function() { })

// Detach برای تغییرات مکرر
var $items = $('#list').find('.item').detach()
$items.addClass('processed')
$('#list').append($items)
```

---

## جلسه ۱۸: دیباگ

```javascript
// Console
console.log('متغیر:', var)
console.table([{name: 'علی'}, {name: 'سارا'}])

// Debugger
debugger  // اجرا متوقف شود

// بررسی DOM
console.log($('#box')[0])  // المان خام
console.log($('#box').html())  // محتوا

// بررسی Event
$(document).on('click', function(e) {
  console.log('event:', e)
})
```

---

## جلسه ۱۹: ساختار پروژه

```javascript
// Object-based organization
var App = {
  config: {
    debug: true,
    apiUrl: 'https://api.example.com'
  },
  
  init: function() {
    this.bindEvents()
  },
  
  bindEvents: function() {
    $('#btn').on('click', $.proxy(this.handleClick, this))
  },
  
  handleClick: function() {
    console.log(this)  // App object
  }
}

$(function() {
  App.init()
})
```

---

## جلسه ۲۰: پروژه نهایی

```javascript
// Todo App
var TodoApp = {
  todos: [],
  
  init: function() {
    this.loadFromStorage()
    this.bindEvents()
  },
  
  bindEvents: function() {
    $('#addBtn').on('click', $.proxy(this.addTodo, this))
    $('#list').on('click', '.delete', $.proxy(this.deleteTodo, this))
  },
  
  addTodo: function(text) {
    this.todos.push({ text: text, done: false })
    this.save()
    this.render()
  },
  
  save: function() {
    localStorage.setItem('todos', JSON.stringify(this.todos))
  },
  
  loadFromStorage: function() {
    this.todos = JSON.parse(localStorage.getItem('todos') || '[]')
  },
  
  render: function() {
    $('#list').empty()
    $.each(this.todos, $.proxy(function(i, todo) {
      $('#list').append('<li>' + todo.text + '</li>')
    }, this))
  }
}

$(function() {
  TodoApp.init()
})
```

---

## 🎯 فرمول‌های رایج

```javascript
// ترکیب متدهای رایج
$('#element')
  .addClass('active')           // کلاس اضافه
  .animate({width: '200px'}, 400)  // انیمیشن
  .delay(500)                   // تاخیر
  .fadeOut()                    // fade out

// درستی‌سنجی
if (!$('#email').val()) {
  $('#error').text('لطفا ایمیل را وارد کنید').show()
  return false
}

// رویداد + localStorage
$('#input').on('keyup', function() {
  localStorage.setItem('draft', $(this).val())
})

// لود داده و نمایش
$.get('/api/data', function(data) {
  $.each(data, function(i, item) {
    $('#list').append('<li>' + item.name + '</li>')
  })
})
```

---

**این مرجع شامل کدهای پر‌کاربرد هر جلسه است. برای جزئیات بیشتر به فایل‌های `.md` هر جلسه مراجعه کنید.**
