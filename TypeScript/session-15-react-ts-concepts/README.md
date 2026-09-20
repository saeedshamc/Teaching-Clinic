# جلسه ۱۵: مفاهیم React + TypeScript (۹۰ دقیقه)

**محیط فرض:** خواندن snippetها کافی است · اجرا اختیاری در Vite شخصی دانشجو

## پیش‌نیازها
کل فازهای قبلی TS · آشنایی با React از [`Reactjs/`](../../Reactjs/) مفید است ولی اجباری نیست.

## اهداف قابل‌اندازه‌گیری
1. props یک کامپوننت را با `type`/`interface` مدل کنید
2. نوع رویداد (`ChangeEvent`, `MouseEvent`) را در حد مفهوم بشناسید
3. بگویید چرا `React.FC` اجباری نیست
4. تأکید کنید: **[`Reactjs/React-project`](../../Reactjs/) را تغییر ندهید** — پروژهٔ Vite جدا بسازید

## چرا مهم است؟
پل مستقیم به بازار کار فرانت. بدون این جلسه، ورود به React+TS با آزمون‌وخطا پرهزینه است.

## مفهوم عمیق
```tsx
type UserCardProps = {
  name: string;
  age?: number;
  onSelect: (id: number) => void;
};

function UserCard({ name, age = 0, onSelect }: UserCardProps) {
  return (
    <button type="button" onClick={() => onSelect(1)}>
      {name} ({age})
    </button>
  );
}
```

```tsx
function NameInput() {
  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log(e.target.value);
  };
  return <input onChange={onChange} />;
}
```

این فایل‌های `.tsx` داخل دورهٔ TypeScript **اجرا نمی‌شوند** تا وابستگی React به این پوشه اضافه نشود. الگو را در پروژهٔ Vite خودتان کپی کنید:

```bash
npm create vite@latest clinic-react-ts -- --template react-ts
```

## اشتباه‌های رایج
1. تغییر دادن `React-project` موجود ریپو.
2. `any` روی props.
3. اصرار بی‌دلیل به `React.FC` و گم شدن children.

## مثال گام‌به‌گام
فایل‌های `examples/*.tsx.txt` را بخوانید (پسوند عمداً `.txt` تا `tsc` دوره درگیر JSX نشود). محتوا را در Vite خودتان به `.tsx` تبدیل کنید.

## الگوی بهتر
props را با `type` نام‌دار؛ state را با `useState<T>(...)`؛ رویداد را از نوع DOM درست.

## تمرین‌ها
### آسان
`type ButtonProps = { label: string; onClick: () => void }`
### چالشی
لیست `User[]` را با `map` و `key={user.id}` در Vite تایپ‌شده رندر کنید.

## راهنمای حل
`users.map((user) => <li key={user.id}>{user.name}</li>)`

## خودآزمایی
1. props را کجا تایپ می‌کنید؟
2. `ChangeEvent` مال چیست؟
3. چرا این ریپو React را به TypeScript/ اضافه نکرد؟
4. قالب Vite مناسب چیست؟
5. آیا باید React-project را ویرایش کنید؟

## جمع‌بندی و پل جلسهٔ بعد
پل React. جلسه آخر: مینی‌ماژول Note کاملاً تایپ‌شده داخل همین پوشه.
