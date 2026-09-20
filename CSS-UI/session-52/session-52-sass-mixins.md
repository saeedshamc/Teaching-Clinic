# جلسه 52: Mixin در Sass (2 ساعت)

## اهداف یادگیری
- تعریف `@mixin` و فراخوانی با `@include`
- پاس دادن آرگومان
- تفاوت mixin با کپی‌پیست CSS

---

## الگو

```scss
@mixin pill($bg, $color: #fff) {
  border-radius: 999px;
  background: $bg;
  color: $color;
  padding: 0.6rem 1rem;
  border: 0;
  font-weight: 700;
  cursor: pointer;
}

.btn {
  @include pill(#c2410c);
}

.btn-ghost {
  @include pill(transparent, #c2410c);
  border: 2px solid #c2410c;
}
```

---

## چه موقع mixin؟

- الگوی تکراری با پارامتر (breakpoint، دکمه، focus ring)
- وقتی فقط `@extend` کافی نیست

## احتیاط

هر mixin لزوماً CSS کمتر تولید نمی‌کند؛ تکرار خروجی ممکن است. برای سهم خشک صفات گاهی placeholder/`%` مناسب‌تر است (جلسه ۵۵).

---

## تمرین‌ها
### آسان
mixin `focus-ring` بسازید.
### متوسط
`pill` را با آرگومان رنگ بسازید.
### چالش
mixin ریسپانسیو `up($bp)` اسکلت بزنید (کامل در جلسه ۵۹).

## گام بعد
[جلسه 53](../session-53/index.html)
