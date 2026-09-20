# جلسه 56: معماری 7-1 در Sass (2 ساعت)

## اهداف یادگیری
- شناخت ساختار پوشهٔ 7-1
- جدا کردن abstracts، base، components
- نقطهٔ ورود `main.scss` با `@use`

---

## ساختار پیشنهادی

```
styles/
  abstracts/
    _variables.scss
    _mixins.scss
  base/
    _reset.scss
  components/
    _buttons.scss
  layout/
  pages/
  themes/
  vendors/
  main.scss
```

در این جلسهٔ تمرینی، فایل‌های کلیدی در ریشهٔ جلسه آمده‌اند تا مسیر کوتاه باشد:

- `_variables.scss`
- `_mixins.scss`
- `_buttons.scss`
- `main.scss`

---

## main.scss

```scss
@use "buttons";
```

کامپوننت‌ها توکن و mixin را خودشان `@use` می‌کنند.

---

## تمرین
برای یک فرم، پوشهٔ components فرضی `_forms.scss` را روی کاغذ طراحی کنید.

## گام بعد
[جلسه 57](../session-57/index.html)
