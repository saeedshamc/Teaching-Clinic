# جلسه ۱۲: تایپ Async و پاسخ API (۹۰ دقیقه)

**محیط فرض:** TypeScript 5.x · اینترنت برای مثال fetch اختیاری

## پیش‌نیازها
[`js/session-15`](../../js/session-15/) · جلسات TS ۰۴ و ۰۸ و ۱۱.

## اهداف قابل‌اندازه‌گیری
1. `Promise<T>` و `async` function تایپ‌شده بنویسید
2. شکل پاسخ API را با `type` مدل کنید
3. JSON را ابتدا `unknown` فرض کنید و بعد narrow/validate کنید
4. بدون ساخت backend، قرارداد داده را ثابت نگه دارید

## چرا مهم است؟
بیشتر باگ‌های فرانت از «فرض کردم فیلد هست» می‌آید. تایپ پاسخ، قرارداد را در کامپایلر قفل می‌کند.

## مفهوم عمیق
```ts
type UserDto = { id: number; name: string };

async function fetchUser(id: number): Promise<UserDto> {
  const res = await fetch(`https://jsonplaceholder.typicode.com/users/${id}`);
  if (!res.ok) throw new Error(`HTTP ${res.status}`);
  const data: unknown = await res.json();
  return parseUser(data);
}
```

حتی با جنریک `res.json() as UserDto` هنوز runtime تضمین ندارد — parse/validate آموزشی مهم است.

## اشتباه‌های رایج
1. `as UserDto` مستقیم روی هر JSON.
2. فراموش `res.ok`.
3. `Promise<any>`.

## مثال گام‌به‌گام
```bash
npx tsx session-12-async-typing/examples/01-api.ts
```

اگر شبکه نبود، همان فایل تابع `parseUser` را روی دادهٔ ساختگی هم نشان می‌دهد.

## الگوی بهتر
یک تابع `parse*` جدا؛ لایهٔ UI فقط DTO معتبر می‌بیند.

## تمرین‌ها
### آسان
`async function delay(ms: number): Promise<void>`
### چالشی
`parseUser` را برای فیلد `email` هم سخت‌گیر کنید.

## راهنمای حل
بعد از چک object بودن، `"email" in data && typeof data.email === "string"`.

## خودآزمایی
1. `Promise<T>` یعنی چه؟
2. چرا `unknown` بعد از `json()`؟
3. `res.ok` چه ربطی به نوع دارد؟
4. تفاوت compile-time و runtime validation؟
5. آیا backend در این جلسه لازم است؟

## جمع‌بندی و پل جلسهٔ بعد
دادهٔ ناهمگام تایپ شد. جلسه بعد: tsconfig کاربردی.
