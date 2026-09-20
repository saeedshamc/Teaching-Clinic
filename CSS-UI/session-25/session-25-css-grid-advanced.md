# جلسه 25: Grid پیشرفته (2 ساعت)

## اهداف
- `grid-template-areas`
- `minmax` و `auto-fit` / `auto-fill`

```css
.page {
  display: grid;
  grid-template-areas:
    "header header"
    "nav main"
    "footer footer";
  grid-template-columns: 180px 1fr;
}
header { grid-area: header; }
```

```css
.cards {
  grid-template-columns: repeat(auto-fit, minmax(14rem, 1fr));
}
```

## گام بعد
[جلسه 26](../session-26/index.html)
