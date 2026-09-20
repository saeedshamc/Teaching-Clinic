# جلسه 31: Animation و @keyframes (2 ساعت)

## اهداف
- تعریف `@keyframes`
- ویژگی‌های `animation-name`، `duration`، `iteration-count`، `timing-function`
- احترام به `prefers-reduced-motion` (در جلسه ۴۵ کامل‌تر)

```css
@keyframes pulse {
  50% { transform: scale(1.15); opacity: .7; }
}
.pulse { animation: pulse 1.4s ease-in-out infinite; }
```

## گام بعد
[جلسه 32](../session-32/index.html)
