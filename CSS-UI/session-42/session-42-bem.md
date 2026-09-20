# جلسه 42: BEM — Block Element Modifier (2 ساعت)

## اهداف یادگیری
- فهم سه بخش نام‌گذاری BEM
- نوشتن کلاس‌های قابل‌خواندن برای تیم
- جدا کردن «حالت» (modifier) از «قطعه» (element)

---

## 1. چرا BEM؟

وقتی پروژه بزرگ می‌شود، نام‌هایی مثل `.title` یا `.red` مبهم می‌شوند. BEM یک قرارداد است تا بفهمید هر کلاس مال کدام قطعه UI است و چه حالتی دارد.

### سه بخش

| بخش | معنی | مثال |
|-----|------|------|
| **Block** | قطعه مستقل | `card`، `navbar` |
| **Element** | بخش داخل بلوک | `card__title` |
| **Modifier** | نسخه یا حالت | `card--featured`، `btn--disabled` |

جداکنندهٔ عنصر: `__`  
جداکنندهٔ حالت: `--`

---

## 2. مثال کامل

```html
<article class="card card--featured">
  <h2 class="card__title">عنوان</h2>
  <p class="card__body">توضیح</p>
  <button class="card__btn card__btn--primary" type="button">خرید</button>
</article>
```

```css
.card { /* بلوک */ }
.card__title { /* عنصر */ }
.card--featured { /* حالت بلوک */ }
.card__btn--primary { /* حالت عنصر */ }
```

---

## 3. قوانین کاربردی

1. Element را بدون Block ننویسید (`.title` به‌تنهایی در BEM کافی نیست؛ `card__title` بهتر است).
2. Element تو در تو را زیاد عمیق نکنید (`card__header__title` معمولاً بد است).
3. Modifier را برای «حالت» نگه دارید نه برای ساخت قطعهٔ جدید.
4. BEM با CSS خالص کار می‌کند؛ بعداً با Sass هم ترکیب می‌شود.

---

## اشتباهات رایج
- گذاشتن modifier به‌جای element: `card--title`
- وابسته کردن استایل یک بلوک به بلوک دیگر با سلکتور تودرتوی شکننده
- مخلوط کردن utilityهای تصادفی بدون نام معنادار در کنار BEM (بد نیست، ولی قرارداد تیم را مشخص کنید)

---

## تمرین‌ها

### آسان
یک دکمه با `btn` و `btn--ghost` بسازید.

### متوسط
یک `navbar` با `navbar__brand`، `navbar__link` و `navbar__link--active` بنویسید.

### چالش
کارت محصول با تصویر، قیمت و دو حالت `card--sale` / `card--soldout`.

---

## خلاصه
BEM زبان مشترک تیم برای نام کلاس‌هاست: Block، Element، Modifier.

## گام بعد
[جلسه 43: ITCSS و لایه‌بندی](../session-43/index.html)
