# جلسه 54: شرط و حلقه در Sass (2 ساعت)

## اهداف یادگیری
- `@if` / `@else`
- `@for`، `@each`، آشنایی با `@while`
- تولید کلاس‌های مقیاس‌دار از حلقه

---

## مثال @for

```scss
@for $i from 1 through 3 {
  .s-#{$i} {
    height: 20px * $i;
  }
}
```

`#{$i}` برای جای‌گذاری در نام کلاس (interpolation) است.

---

## مثال @each روی map

```scss
$bp: ("md": 768px, "lg": 1100px);
@each $name, $value in $bp {
  .u-hide-#{$name} {
    @media (min-width: $value) { display: none; }
  }
}
```

---

## تمرین‌ها
### آسان
با `@for` سه کلاس فاصله بسازید.
### متوسط
`@each` روی map رنگ.
### چالش
شرط `@if` برای تم dark داخل حلقه.

## گام بعد
[جلسه 55](../session-55/index.html)
