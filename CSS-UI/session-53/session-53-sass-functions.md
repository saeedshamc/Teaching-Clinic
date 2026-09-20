# جلسه 53: Function و Map در Sass (2 ساعت)

## اهداف یادگیری
- ساخت `@function` سفارشی
- کار با `map` و `map-get`
- تولید کلاس از روی نقشهٔ رنگ

---

## Map و Function

```scss
$colors: (
  "ok": #16a34a,
  "warn": #d97706,
  "danger": #dc2626,
);

@function tone($name) {
  @return map-get($colors, $name);
}

.badge-ok { background: tone("ok"); }
.badge-warn { background: tone("warn"); }
```

Map برای توکن‌های مرتبط عالی است؛ function منطق خواندن را تمیز می‌کند.

---

## تمرین‌ها
### آسان
یک map فاصله بسازید و با function بخوانید.
### متوسط
سه badge از روی map رنگ بسازید.
### چالش
اگر کلید نبود، `@error` بدهید.

## گام بعد
[جلسه 54](../session-54/index.html)
