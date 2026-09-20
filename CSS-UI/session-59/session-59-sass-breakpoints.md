# جلسه 59: Breakpoint Map و mixin ریسپانسیو (2 ساعت)

## اهداف
- نقشهٔ breakpoint
- mixin `up($name)` با `@content`

```scss
$bp: ("md": 768px, "lg": 1100px);

@mixin up($name) {
  @media (min-width: map-get($bp, $name)) {
    @content;
  }
}

.mq {
  background: #ffedd5;
  @include up(md) { background: #dcfce7; }
}
```

## گام بعد
[جلسه 60](../session-60/index.html)
