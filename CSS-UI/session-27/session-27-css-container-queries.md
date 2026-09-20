# جلسه 27: Container Queries (2 ساعت)

## اهداف
- تفاوت viewport query و container query
- `container-type` و `@container`

```css
.card { container-type: inline-size; }
@container (min-width: 280px) {
  .card { display: flex; }
}
```

کامپوننت نسبت به **عرض والد** واکنش می‌دهد، نه فقط کل صفحه.

## گام بعد
[جلسه 28](../session-28/index.html)
