# جلسه ۰۵ — فرم‌های کنترل‌شده و اعتبارسنجی

**مدت:** ۱۲۰ دقیقه · **محیط:** React 18+/19 با Vite · Node LTS

## پیش‌نیازها
جلسات ۰۱–۰۴ (state، effect، لیست).

## اهداف قابل‌اندازه‌گیری
1. فرم کنترل‌شده (controlled) با `value` + `onChange` بسازید.
2. خطاهای فیلد را در state نگه دارید.
3. submit را با `preventDefault` مدیریت کنید.
4. بگویید چرا uncontrolled با `ref` برای بیشتر فرم‌های آموزشی پیش‌فرض نیست.

## چرا مهم است؟
تقریباً هر محصول React فرم دارد. الگوی کنترل‌شده منبع حقیقت را در React می‌گذارد و UI را با داده همگام نگه می‌دارد.

## مفهوم عمیق
در controlled input، React مالک مقدار است:

```jsx
const [email, setEmail] = useState("");
<input value={email} onChange={(e) => setEmail(e.target.value)} />
```

اعتبارسنجی می‌تواند هنگام تغییر یا هنگام submit باشد؛ برای شروع، روی submit + نمایش خطا کافی است.

## اشتباهات رایج
1. فراموش `preventDefault` و رفرش صفحه.
2. گذاشتن `value` بدون `onChange` (ورودی قفل می‌شود).
3. ذخیرهٔ خطا فقط در `console` بدون UI.

## مثال آموزشی قدم‌به‌قدم
در پروژهٔ Vite آموزشی (جدا از `React-project` ریپو):

```jsx
import { useState } from "react";

export default function SignupForm() {
  const [email, setEmail] = useState("");
  const [error, setError] = useState("");

  function onSubmit(e) {
    e.preventDefault();
    if (!email.includes("@")) {
      setError("ایمیل نامعتبر است");
      return;
    }
    setError("");
    alert(`ثبت: ${email}`);
  }

  return (
    <form onSubmit={onSubmit}>
      <input
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        placeholder="email"
      />
      {error && <p role="alert">{error}</p>}
      <button type="submit">ارسال</button>
    </form>
  );
}
```

## نسخهٔ بهتر
خطاها را به‌صورت آبجکت per-field نگه دارید؛ دکمه را تا وقتی فرم invalid است disable کنید یا پیام واضح بدهید.

## تمرین عملی
### آسان
فیلد `name` اجباری اضافه کنید.
### چالش
حداقل طول رمز ۸ و نمایش قوت ساده (کوتاه/قابل قبول).

## راهنمای راه‌حل (چالش)
روی submit هر دو فیلد را چک کنید؛ state خطا را `{ email, password }` کنید.

## خودآزمایی
- [ ] controlled را بلدم
- [ ] preventDefault بلدم
- [ ] خطا در UI نشان می‌دهم
- [ ] value بدون onChange نمی‌گذارم
- [ ] تفاوت uncontrolled را می‌دانم
- [ ] validate سمت کلاینت را کافی نمی‌دانم برای امنیت سرور

## جمع‌بندی + پل
فرم کنترل شد. جلسهٔ بعد routing مفهومی با React Router.
