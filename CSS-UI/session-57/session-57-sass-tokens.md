# جلسه 57: توکن در Sass و خروجی CSS Variables (2 ساعت)

## اهداف
- نگه داشتن توکن در Sass
- خروجی runtime با custom properties

```scss
$brand: #c2410c;
:root {
  --brand: #{$brand};
}
.themed {
  border: 2px solid var(--brand);
}
```

این الگو برای تعویض تم در مرورگر عالی است.

## گام بعد
[جلسه 58](../session-58/index.html)
