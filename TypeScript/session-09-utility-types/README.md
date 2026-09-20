# جلسه ۰۹: Utility Types ضروری (۹۰ دقیقه)

**محیط فرض:** TypeScript 5.x · strict

## پیش‌نیازها
جلسات ۰۶ و ۰۸.

## اهداف قابل‌اندازه‌گیری
1. `Partial`، `Pick`، `Omit`، `Record`، `Readonly` را روی مدل واقعی به کار ببرید
2. بگویید هر کدام چه شکلی می‌سازد
3. برای آپدیت patch از `Partial` استفاده کنید
4. utility سفارشی ننویسید وقتی آماده کافی است

## چرا مهم است؟
در فرم ویرایش، DTO و config همین‌ها روزانه تکرار می‌شوند.

## مفهوم عمیق
```ts
type User = { id: number; name: string; email: string };

type UserPatch = Partial<User>;
type UserPreview = Pick<User, "id" | "name">;
type UserPublic = Omit<User, "email">;
type Roles = Record<string, boolean>;
```

## اشتباه‌های رایج
1. `Partial` روی چیزی که فیلد اجباری معنایی دارد بدون فکر.
2. `Omit` اشتباه و جا گذاشتن فیلد حساس.
3. `Record<string, any>` به‌عنوان فرار.

## مثال گام‌به‌گام
```bash
npx tsx session-09-utility-types/examples/01-utilities.ts
```

## الگوی بهتر
برای patch API: `Partial<Pick<User, "name" | "email">>` دقیق‌تر از `Partial<User>` کامل.

## تمرین‌ها
### آسان
از `User` یک `Pick` برای کارت UI بسازید.
### چالشی
تابع `applyPatch(user, patch: Partial<User>): User`.

## راهنمای حل
`return { ...user, ...patch };`

## خودآزمایی
1. `Partial` چه می‌کند؟
2. فرق `Pick` و `Omit`؟
3. `Record` کجا مفید است؟
4. `Readonly` چه محدودیتی می‌گذارد؟
5. آیا utilityها فقط زمان کامپایل‌اند؟

## جمع‌بندی و پل جلسهٔ بعد
ابزارهای نوع آماده. جلسه بعد: ماژول و `import type`.
