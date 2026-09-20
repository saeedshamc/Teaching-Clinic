# جلسه 60: تم روشن و تاریک با Sass (2 ساعت)

## اهداف
- نقشهٔ رنگ تم در Sass
- خروجی به CSS variables
- تعویض کلاس `body.dark`

```scss
$light: ("bg": #fff7ed, "fg": #7c2d12);
$dark: ("bg": #1c1917, "fg": #ffedd5);

:root {
  --bg: #{map-get($light, bg)};
  --fg: #{map-get($light, fg)};
}
body.dark {
  --bg: #{map-get($dark, bg)};
  --fg: #{map-get($dark, fg)};
}
```

## گام بعد
[جلسه 61](../session-61/index.html)
