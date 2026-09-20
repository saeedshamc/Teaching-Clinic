# راه‌اندازی Next.js برای این دوره

## نصب Node
از https://nodejs.org نسخهٔ LTS نصب کنید. تأیید:

```bash
node -v
npm -v
```

## ساخت پروژهٔ آموزشی (روی سیستم شما)
```bash
npx create-next-app@latest clinic-next --typescript --eslint --app --src-dir --tailwind --no-turbopack
cd clinic-next
npm run dev
```

پرچم‌ها را می‌توانید مطابق ویزارد تنظیم کنید؛ مهم این است که **App Router** فعال باشد.

## نسخه
این دوره برای **Next.js 15** نوشته شده. اگر نسخهٔ جدیدتر نصب کردید، تفاوت‌های جزئی API را با مستندات رسمی چک کنید؛ مفاهیم App Router پایدارند.
