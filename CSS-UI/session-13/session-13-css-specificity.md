# جلسه 13: Specificity و Inheritance (2 ساعت)

## اهداف
- محاسبه اولویت: inline > id > class > element
- ارث‌بری ویژگی‌هایی مثل `color` و `font-family`

```css
p { color: #64748b; }        /* 0,0,1 */
.text { color: #2563eb; }    /* 0,1,0 */
#p1 { color: #dc2626; }      /* 1,0,0 → برنده */
```

`!important` را عادت نکنید؛ آخرِ خط برای استثناست.

## گام بعد
[جلسه 14](../session-14/index.html)
