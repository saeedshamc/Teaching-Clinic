# جلسه 84: States — hover، focus، group، peer (2 ساعت)

## اهداف یادگیری
- شبه‌کلاس‌ها: `hover:`، `focus:`، `active:`، `disabled:`
- `focus-visible:` برای کیبورد
- الگوی `group` / `group-hover`
- الگوی `peer` / `peer-checked` برای فرم‌ها

---

## 1. State روی خود عنصر

```html
<button class="bg-sky-500 hover:bg-sky-600 focus:outline-none focus-visible:ring-2 focus-visible:ring-sky-400">
  دکمه
</button>
```

`disabled:opacity-50 disabled:pointer-events-none` برای حالت غیرفعال.

---

## 2. group

وقتی هاور روی والد باید فرزند را عوض کند:

```html
<a class="group block rounded-lg p-4 hover:bg-slate-50">
  <h3 class="font-bold group-hover:text-sky-600">عنوان</h3>
  <p class="text-sm text-slate-500">توضیح</p>
</a>
```

---

## 3. peer

عنصر قبلی (معمولاً input مخفی) وضعیت را به بعدی می‌دهد:

```html
<input id="tos" type="checkbox" class="peer sr-only">
<label for="tos" class="peer-checked:text-emerald-600">قوانین را خواندم</label>
```

---

## اشتباهات رایج
- فقط `hover` بدون `focus-visible` (دسترس‌پذیری کیبورد)
- `outline-none` بدون جایگزین ring
- اشتباه گرفتن ترتیب sibling برای `peer`

---

## تمرین‌ها
### آسان
لینکی با `hover:underline`.
### متوسط
کارت group که عنوانش رنگ عوض کند.
### چالش
چک‌باکس peer که یک باکس راهنما را `peer-checked:block` کند.

## گام بعد
[جلسه 85: فرم‌ها](../session-85/index.html)
