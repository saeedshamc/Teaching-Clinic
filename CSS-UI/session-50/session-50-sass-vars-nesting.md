# جلسه 50: متغیر و Nesting در Sass (2 ساعت)

## اهداف یادگیری
- تعریف متغیر با `$`
- تودرتویی سلکتورها
- کاربرد `&` برای parent selector

---

## 1. متغیر

```scss
$link: #9a3412;
$link-hover: #c2410c;

a {
  color: $link;
}
```

نام متغیرها را معنایی بگذارید (`$color-brand` بهتر از `$c1`).

---

## 2. Nesting

```scss
.nav {
  display: flex;

  a {
    color: $link;
    text-decoration: none;

    &:hover,
    &.is-active {
      color: $link-hover;
      font-weight: 700;
    }
  }
}
```

`&` جای والد را می‌گیرد → `.nav a:hover` و `.nav a.is-active`.

---

## 3. احتیاط در Nesting

تودرتویی خیلی عمیق خروجی سلکتور شکننده می‌سازد:

```scss
// بد: خیلی عمیق
.header { .nav { ul { li { a { ... } } } } }
```

معمولاً ۲–۳ سطح کافی است؛ برای ساختار عمیق‌تر به BEM و فایل جدا فکر کنید.

---

## تمرین‌ها
### آسان
`$link` را عوض و کامپایل کنید.
### متوسط
حالت `.is-active` را با `&` بنویسید.
### چالش
یک منو با nesting برای `ul/li/a` بدون بیش از ۳ سطح.

## گام بعد
[جلسه 51: ماژول‌های @use](../session-51/index.html)
