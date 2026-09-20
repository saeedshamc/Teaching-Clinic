# جلسه ۱۰ — Data Fetching و امنیت پایهٔ کلاینت

**مدت:** ۱۲۰ دقیقه

## پیش‌نیازها
جلسات ۰۴ (effect) و ۰۶–۰۹.

## اهداف قابل‌اندازه‌گیری
1. fetch را با وضعیت‌های loading/error/success مدل کنید.
2. race شرط را با AbortController یا flag لغو کنید.
3. توکن را در localStorage بدون آگاهی از ریسک نگذارید (مفهومی).
4. XSS از دادهٔ API را با React text children کم کنید؛ `dangerouslySetInnerHTML` را بی‌دلیل استفاده نکنید.

## چرا مهم است؟
بیشتر باگ‌های SPA از fetch بدون لغو، state کهنه، یا اعتماد به دادهٔ سرور در HTML خام است.

## مفهوم عمیق
```jsx
useEffect(() => {
  const ac = new AbortController();
  setStatus("loading");
  fetch(url, { signal: ac.signal })
    .then((r) => r.json())
    .then((data) => setData(data))
    .catch((e) => {
      if (e.name !== "AbortError") setStatus("error");
    });
  return () => ac.abort();
}, [url]);
```

الگوهای رایج: fetch در effect، کتابخانهٔ اختصاصی (React Query) در مسیر بعدی، یا framework با سرور (Next).

## اشتباهات رایج
1. setState بعد از unmount بدون abort.
2. قاطی کردن token در کد فرانت به‌عنوان «امن».
3. نمایش HTML خام API.

## مثال
لیست پست‌ها از `https://jsonplaceholder.typicode.com/posts?_limit=5` با سه وضعیت UI.

## تمرین
### آسان
دکمهٔ Retry بعد از error.
### چالش
تغییر `userId` در query و جلوگیری از پاسخ کهنه با abort.

## خودآزمایی
- [ ] loading/error بلدم
- [ ] AbortController بلدم
- [ ] XSS را می‌فهمم
- [ ] امنیت کامل سمت کلاینت نیست
- [ ] dependency اثر را درست می‌گذارم
- [ ] dangerouslySetInnerHTML را پیش‌فرض نمی‌گذارم

## جمع‌بندی + پل
داده از شبکه آمد. جلسهٔ بعد تست‌نویسی مفهومی React.
