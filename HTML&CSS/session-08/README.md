# جلسه ۸: جداول HTML (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- جدول با `table`, `tr`, `td`, `th` بسازید
- `thead`, `tbody`, `tfoot` را سازماندهی کنید
- `colspan` و `rowspan` برای ادغام سلول به کار ببرید
- `caption` و `scope` برای دسترس‌پذیری بنویسید
- بدانید جدول برای **داده** است نه layout

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | کاربرد جدول |
| 10–30 | table, tr, td, th |
| 30–50 | thead, tbody, tfoot |
| 50–65 | colspan, rowspan |
| 65–80 | scope, caption |
| 80–90 | تمرین، تکلیف |

---

## ۱. ساختار پایه

```html
<table>
    <tr>
        <td>سلول ۱</td>
        <td>سلول ۲</td>
    </tr>
</table>
```

| تگ | نقش |
|----|-----|
| `table` | container |
| `tr` | table row |
| `td` | table data |
| `th` | table header |

---

## ۲. سرستون و بدنه

```html
<table>
    <caption>عنوان جدول</caption>
    <thead>
        <tr>
            <th scope="col">نام</th>
            <th scope="col">سن</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <th scope="row">علی</th>
            <td>۲۵</td>
        </tr>
    </tbody>
</table>
```

---

## ۳. colspan و rowspan

```html
<tr>
    <td colspan="2">دو ستون ادغام</td>
</tr>
<tr>
    <td rowspan="2">دو سطر</td>
    <td>سلول</td>
</tr>
```

---

## ۴. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_basic_table.html](./examples/01_basic_table.html) | پایه |
| [02_table_headers.html](./examples/02_table_headers.html) | thead/tfoot |
| [03_colspan_rowspan.html](./examples/03_colspan_rowspan.html) | ادغام |
| [04_accessible_table.html](./examples/04_accessible_table.html) | a11y |

---

## ۵. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| جدول برای layout | CSS Grid/Flexbox |
| th در body بدون scope | scope="col/row" |
| بدون caption | caption توصیفی |
| td برای همه | th برای header |

---

## ۶. td vs th

| تگ | کاربرد |
|----|--------|
| `th` | header cell — bold پیش‌فرض |
| `td` | data cell |

در thead همه th؛ در tbody معمولاً td — مگر row header با th + scope="row".

---

## ۷. tfoot

```html
<tfoot>
    <tr>
        <td colspan="3">جمع کل: ۱٬۰۰۰٬۰۰۰</td>
    </tr>
</tfoot>
```

خلاصه، جمع — می‌تواند قبل از tbody در HTML هم نوشته شود (render یکسان).

---

## ۸. colspan/rowspan — جزئیات

```html
<table>
    <tr>
        <th colspan="2">نمرات</th>
        <th>میانگین</th>
    </tr>
    <tr>
        <td rowspan="2">علی</td>
        <td>۱۸</td>
        <td rowspan="2">۱۷</td>
    </tr>
    <tr>
        <td>۱۶</td>
    </tr>
</table>
```

مراقب باشید تعداد سلول‌ها در هر row با grid همخوان بماند.

---

## ۹. scope — مقادیر

| scope | معنی |
|-------|------|
| `col` | header ستون |
| `row` | header سطر |
| `colgroup` | گروه ستون (پیشرفته) |
| `rowgroup` | گروه سطر (پیشرفته) |

---

## ۱۰. caption

```html
<caption>جدول ۱ — فروش فصلی</caption>
```

اولین child از table — توضیح **کل** جدول.

---

## ۱۱. چرا table برای layout بد است؟

- دسترس‌پذیری ضعیف
- responsive سخت
- نگهداری بد
- CSS Grid/Flexbox جایگزین مدرن

---

## ۱۲. responsive table (آینده)

overflow-x: auto روی wrapper — در CSS جلسه بعد.

---

## ۱۳. empty cells

```html
<td>&nbsp;</td>  <!-- اجتناب -->
<td></td>        <!-- بهتر — CSS برای ارتفاع -->
```

---

## ۱۴. بهترین شیوه‌ها

1. thead برای header
2. th + scope
3. caption توصیفی
4. فقط داده tabular
5. colspan کم — خوانایی

---

## ۱۵. تمرین کلاسی

1. جدول برنامه هفتگی
2. colspan در header
3. scope روی th
4. caption

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۶. خلاصه

- ✅ table/tr/td/th
- ✅ thead/tbody/tfoot
- ✅ colspan/rowspan
- ✅ caption + scope

**بعد:** [۹ — فرم ۱](../session-09/)

---

**قبل:** [۷ — تصاویر](../session-07/) | **بعد:** [۹ — فرم ۱](../session-09/)
