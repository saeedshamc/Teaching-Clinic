# جلسه ۱۱: strict، unknown و never (۹۰ دقیقه)

**محیط فرض:** `strict: true` در tsconfig دوره

## پیش‌نیازها
جلسات ۰۴ و ۰۸.

## اهداف قابل‌اندازه‌گیری
1. چند پرچم مهم strict را نام ببرید
2. ورودی نامعتبر را با `unknown` بگیرید و قبل از استفاده narrow کنید
3. نقش `never` در exhaustiveness را توضیح دهید
4. تفاوت `unknown` و `any` را با مثال بگویید

## چرا مهم است؟
`strict` همان کیفیت پیش‌فرض حرفه‌ای است. `unknown` جایگزین امن `any` در مرزهاست.

## مفهوم عمیق
```ts
function handle(value: unknown) {
  if (typeof value === "string") {
    console.log(value.toUpperCase());
  }
}

type Status = "a" | "b";
function assertNever(x: never): never {
  throw new Error(String(x));
}
```

اگر به `switch` عضو جدید اضافه شود و `default` به `assertNever` برود، کامپایلر جاافتادگی را فاش می‌کند.

## اشتباه‌های رایج
1. خاموش کردن strict برای راحتی.
2. `value as string` روی `unknown` بدون چک.
3. `any` در catch به‌جای `unknown`.

## مثال گام‌به‌گام
```bash
npx tsx session-11-strict-unknown-never/examples/01-strict.ts
```

## الگوی بهتر
مرز خارجی (JSON.parse، فرم، شبکه) → `unknown` سپس validate/narrow.

## تمرین‌ها
### آسان
تابعی که `unknown` بگیرد و اگر number بود مربعش را برگرداند وگرنه `null`.
### چالشی
`switch` روی `"low" | "high"` با `default: assertNever(...)`.

## راهنمای حل
تابع `assertNever` را از مثال کپی کنید.

## خودآزمایی
1. `unknown` چه اجباری می‌آورد؟
2. `any` چرا خطرناک است؟
3. `never` کجا ظاهر می‌شود؟
4. یک پرچم strict نام ببرید.
5. آیا می‌توان strict را فقط برای یک فایل خاموش کرد؟ (ممکن است ولی توصیه نمی‌شود)

## جمع‌بندی و پل جلسهٔ بعد
ایمنی نوع در مرزها. جلسه بعد: تایپ async و پاسخ API.
