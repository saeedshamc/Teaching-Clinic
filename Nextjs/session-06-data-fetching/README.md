# جلسه ۰۶: Data Fetching و Caching ذهنی (۱۲۰ دقیقه)

## پیش‌نیاز
جلسه ۰۵

## اهداف یادگیری
1. fetch در Server Component را بنویسید.
2. ذهنیت cache/revalidate را در حد کاربردی توضیح دهید.
3. تفاوت render استاتیک و دینامیک را مفهومی بگویید.

## چرا مهم است
بدون مدل ذهنی کش، یا داده کهنه می‌بینید یا سرور را بی‌دلیل می‌کوبید.

## مفهوم اصلی
در Server Component:
```tsx
async function getPosts() {
  const res = await fetch("https://jsonplaceholder.typicode.com/posts?_limit=5", {
    next: { revalidate: 60 },
  });
  return res.json();
}
```
- `revalidate: 60` یعنی ISRگونه هر ۶۰ثانیه.
- برای دادهٔ کاملاً دینامیک per-request از گزینه‌های no-store / dynamic استفاده می‌شود (طبق docs نسخه).

## اشتباهات رایج
1. fetch کلاینتی بی‌دلیل برای دادهٔ عمومی.
2. نادیده گرفتن خطا و loading.
3. فرض اینکه «همه چیز همیشه SSR زنده» است.

## تمرین
### آسان
لیست ۵ پست در page سرور.
### چالش
یک مسیر با revalidate کوتاه و توضیح رفتار.

## خودآزمایی
- [ ] fetch سرور بلدم
- [ ] revalidate را می‌فهمم
- [ ] loading.tsx را وصل می‌کنم
- [ ] secret را در URL کلاینت نمی‌گذارم
- [ ] می‌دانم نسخهٔ Next جزئیات cache را عوض کرده — docs را چک می‌کنم

## جمع‌بندی + پل
داده از سرور. جلسهٔ بعد Route Handlers.
