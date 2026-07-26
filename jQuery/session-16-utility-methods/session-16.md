# جلسه ۱۶ — متدهای کمکی jQuery و کار با مجموعه داده‌ها

## ۱. متدهای کمکی jQuery

jQuery علاوه بر متدهای DOM، متدهای کمکی هم دارد که برای کار با آرایه، آبجکت و رشته مفیدند.

```javascript
$.each([1, 2, 3], function (i, value) {
  console.log(value);
});

$.map([1, 2, 3], function (value) {
  return value * 2;
});
```

## ۲. `$.grep` و `$.inArray`

```javascript
$.grep([1, 2, 3, 4], function (value) {
  return value > 2;
});

$.inArray(3, [1, 2, 3]);
```

## ۳. کار با آبجکت و JSON

```javascript
$.extend({}, { a: 1 }, { b: 2 });
```

## ۴. تمرین‌های پیشنهادی

1. با `$.each` لیست را چاپ کنید.
2. با `$.map` یک آرایه‌ی جدید بسازید.
3. یک آبجکت را با `$.extend` ادغام کنید.

## ۵. جمع‌بندی

متدهای کمکی jQuery به شما کمک می‌کنند داده‌ها را بهتر مدیریت و پردازش کنید.
