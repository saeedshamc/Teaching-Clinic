# جلسه 44: کلاینت API تایپ‌شده — endpoint تا Response (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۱۲ و ۴۳.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. نقشهٔ endpoint به نوع پاسخ بسازید
2. کلاینت get typed بنویسید
3. خطای HTTP را از خطای parse جدا کنید
4. بگویید codegen/OpenAPI ایده‌اند نه اجبار
5. بدون backend واقعی با stub کار کنید

## چرا مهم است؟
بدون قرارداد endpoint→Response، هر صفحه نوع خودش را حدس می‌زند و drift پیش می‌آید.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | انگیزه و مسئله |
| ۲۰–۴۵ | مفاهیم پایه |
| ۴۵–۷۵ | مفاهیم میانی + مثال |
| ۷۵–۱۰۰ | دام‌ها و الگو |
| ۱۰۰–۱۲۰ | تمرین و خودآزمایی |

---

## مفهوم عمیق

### 1. نقشه

```ts
type Endpoints = { "/users/:id": User };
```

### 2. تابع get

جنریک یا overload روی path.

### 3. لایه‌ها

HTTP → json unknown → parse → T.

### 4. Stub

برای آموزش fetch واقعی لازم نیست.

### 5. OpenAPI

در شغل از schema تولید نوع — آگاهی.

### 6. نسخه API

عوض شدن پاسخ = bump نوع.

### 7. دام any در کلاینت

همه مصرف‌کننده‌ها آلوده می‌شوند.

### 8. تست

parse و نقشه را واحدی تست کنید.

---

## اشتباه‌های رایج

1. **any در کلاینت مرکزی.**
2. **یکی کردن HTTP و parse error.**
3. **کپی نوع در هر صفحه.**
4. **as روی body.**
5. **نادیده نسخه.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-44-api-client-typing/examples/01-user.ts
npx tsx session-44-api-client-typing/examples/02-endpoints.ts
npx tsx session-44-api-client-typing/examples/03-layers.ts
npx tsx session-44-api-client-typing/examples/04-no-any-client.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-user.ts](./examples/01-user.ts) | User |
| [02-endpoints.ts](./examples/02-endpoints.ts) | نقشه |
| [03-layers.ts](./examples/03-layers.ts) | لایه‌ها |
| [04-no-any-client.ts](./examples/04-no-any-client.ts) | ضد any |

## الگوی بهتر

یک کلاینت؛ یک parse؛ نقشهٔ صریح.

## تمرین‌ها

### آسان
type User و تابع getUserStub(): User

### چالشی
نقشه Endpoints با یک کلید.

## راهنمای حل

آسان: return {id:1,name:"x"}

چالش: type Endpoints = { "/me": User }

## خودآزمایی

1. نقشه؟
2. لایه‌ها؟
3. stub؟
4. OpenAPI؟
5. any؟
6. نسخه؟
7. خطای HTTP؟
8. parse؟
9. تست؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
کلاینت قرارداد گرفت. جلسهٔ ۴۵: الگوهای طراحی با نوع.
