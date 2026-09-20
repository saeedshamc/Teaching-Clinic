# جلسه ۰۴ — useEffect، چرخه همگام‌سازی و State کهنه

**مدت:** ۱۵۰ دقیقه

## پیش‌نیازها

- State، Event و رندر
- Promise، `fetch` و Cleanup تابع

## اهداف قابل‌اندازه‌گیری

1. Effect لازم را از محاسبه قابل انجام در رندر تشخیص دهید.
2. Setup و Cleanup را درست پیاده کنید.
3. Dependencyها را بر اساس مقادیر Reactive بنویسید.
4. Stale Closure و Race Condition را رفع کنید.

## چرا مهم است؟

بیشتر باگ‌های Hooks از تصور «Effect = بعد از رندر» می‌آیند. Effect ابزار همگام‌سازی React با سیستم بیرونی مانند شبکه، Timer یا Event Listener است؛ نه محل عمومی اجرای منطق برنامه.

## مفهوم عمیق: چرخه مستقل Effect

هر Effect یک فرآیند Start/Stop دارد. با تغییر Dependency، ابتدا Cleanup نسخه قبلی و سپس Setup نسخه جدید اجرا می‌شود؛ هنگام Unmount نیز Cleanup اجرا می‌شود. هر رندر Closure خودش را دارد، پس Callback قدیمی مقادیر همان Snapshot را می‌بیند. فهرست Dependency انتخاب سلیقه‌ای نیست: هر مقدار Reactive خوانده‌شده باید حاضر باشد.

## مثال گام‌به‌گام و کامل

```jsx
import { useEffect, useState } from "react";

function User({ userId }) {
  const [user, setUser] = useState(null);
  const [error, setError] = useState("");

  useEffect(() => {
    const controller = new AbortController();

    async function loadUser() {
      setError("");
      try {
        const response = await fetch(
          `https://jsonplaceholder.typicode.com/users/${userId}`,
          { signal: controller.signal },
        );
        if (!response.ok) throw new Error("دریافت کاربر ناموفق بود");
        setUser(await response.json());
      } catch (error) {
        if (error.name !== "AbortError") setError(error.message);
      }
    }

    loadUser();
    return () => controller.abort();
  }, [userId]);

  if (error) return <p role="alert">{error}</p>;
  if (!user) return <p>در حال دریافت...</p>;
  return <h2>{user.name}</h2>;
}

export default function App() {
  const [userId, setUserId] = useState(1);

  return (
    <main>
      <button onClick={() => setUserId((id) => (id === 3 ? 1 : id + 1))}>
        کاربر بعدی
      </button>
      <User userId={userId} />
    </main>
  );
}
```

گام‌ها: سیستم بیرونی را مشخص کنید، Setup درخواست را بنویسید، با `AbortController` Cleanup کنید و تمام ورودی‌های Reactive یعنی `userId` را در Dependency قرار دهید.

## Stale State در Timer

```jsx
useEffect(() => {
  const id = setInterval(() => {
    setSeconds((current) => current + 1);
  }, 1000);
  return () => clearInterval(id);
}, []);
```

Functional Update نیاز به خواندن `seconds` در Closure را حذف می‌کند.

## الگوی بهتر

- منطق ناشی از کلیک را در Event Handler نگه دارید.
- مقدار مشتق‌شده را در رندر محاسبه کنید.
- هر Effect یک مسئولیت همگام‌سازی داشته باشد.
- هشدار Dependency Linter را خاموش نکنید؛ ساختار کد را اصلاح کنید.

## اشتباهات رایج

1. **Dependency خالی اجباری:** پنهان‌کردن `userId` باعث داده کهنه می‌شود.
2. **نبود Cleanup:** Timer، Listener یا درخواست قبلی بعد از تغییر ورودی ادامه می‌یابد.
3. **Effect برای Derived State:** تنظیم `fullName` از نام و نام خانوادگی یک رندر اضافه و ناسازگاری می‌سازد.
4. **تابع ناپایدار در Dependency:** تابع تازه در هر رندر Effect را بی‌دلیل تکرار می‌کند؛ آن را داخل Effect ببرید.

## تمرین آسان

عنوان Tab را با تعداد کارهای باز همگام کنید و در Cleanup عنوان قبلی را برگردانید.

### راه‌حل

```jsx
useEffect(() => {
  const previous = document.title;
  document.title = `${openCount} کار باز`;
  return () => {
    document.title = previous;
  };
}, [openCount]);
```

## تمرین چالشی

جست‌وجویی بسازید که پس از ۴۰۰ میلی‌ثانیه سکون کاربر درخواست بفرستد و درخواست قبلی را لغو کند.

### راهنمای حل

یک Effect برای `query` بسازید؛ `setTimeout` و `AbortController` ایجاد کنید. Cleanup هم Timeout را پاک و هم Controller را Abort کند.

## خودآزمایی

1. Effect برای چه نوع کاری است؟
2. Cleanup چه زمان‌هایی اجرا می‌شود؟
3. Stale Closure چیست؟
4. Dependencyها چگونه تعیین می‌شوند؟
5. چرا Derived State معمولاً Effect نمی‌خواهد؟
6. Abort درخواست چه Race Conditionی را کاهش می‌دهد؟

## جمع‌بندی و پل جلسه بعد

Effect را فرآیند همگام‌سازی مستقل دیدیم و با Cleanup، Dependency کامل و Functional Update از داده کهنه جلوگیری کردیم. جلسه بعد فرم‌های Controlled و اعتبارسنجی را می‌سازیم.
