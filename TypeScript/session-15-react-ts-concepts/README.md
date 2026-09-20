# جلسه ۱۵: مفاهیم React + TypeScript — props، event، children، state (۱۲۰ دقیقه)

**محیط فرض:** خواندن snippet کافی است · اجرا در Vite شخصی دانشجو · **نه** داخل [`Reactjs/React-project`](../../Reactjs/)

## پیش‌نیازها
جلسات ۱–۱۴. آشنایی با React مفید است؛ این پوشه وابستگی React اضافه نمی‌کند.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. props را با `type`/`interface` مدل کنید
2. نوع رویداد (`ChangeEvent`، `MouseEvent`) را در حد مفهوم به کار ببرید
3. children و state را تایپ‌شده توصیف کنید
4. بگویید چرا `React.FC` اجباری نیست
5. تأکید کنید اپ آمادهٔ ریپو را تغییر ندهید؛ Vite جدا بسازید

## چرا مهم است؟
پل بازار کار فرانت. بدون مدل ذهنی props/event، ورود به React+TS پر از `any` می‌شود.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | چرا snippet و نه وابستگی React اینجا |
| ۲۰–۴۵ | تایپ props |
| ۴۵–۷۰ | eventها |
| ۷۰–۹۰ | children و state |
| ۹۰–۱۰۵ | FC اختیاری |
| ۱۰۵–۱۲۰ | تمرین ذهنی + Vite |

---

## مفهوم عمیق

### ۱. این جلسه چرا .tsx را compile نمی‌کند؟

تا `TypeScript/` سبک بماند. فایل‌ها با پسوند `.tsx.txt` هستند. در Vite شخصی به `.tsx` کپی کنید.

```bash
npm create vite@latest clinic-react-ts -- --template react-ts
```

### ۲. Props نام‌دار

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

همان مهارت جلسه ۵–۶ روی مرز UI.

### ۳. Event

```tsx
function NameInput() {
  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log(e.target.value);
  };
  return <input onChange={onChange} />;
}
```

generic روی عنصر DOM دقت `target` را بالا می‌برد.

### ۴. Children

```tsx
type PanelProps = {
  title: string;
  children: React.ReactNode;
};
```

`ReactNode` برای اکثر محتوا کافی است. اگر فقط element می‌خواهید، `ReactElement` تنگ‌تر است.

### ۵. State

```tsx
const [count, setCount] = useState(0); // inference: number
const [user, setUser] = useState<User | null>(null);
```

وقتی initial مقدار null است، جنریک صریح بدهید وگرنه inference گیر می‌کند.

### ۶. FC اجباری نیست

```tsx
const Wrong: React.FC<Props> = (props) => ...
```

امروز بسیاری تیم‌ها تابع معمولی با props typed را ترجیح می‌دهند. FC تاریخچه و رفتار children قدیمی دارد؛ اجبارش نکنید.

### ۷. لیست و key

`users.map((u) => <li key={u.id}>{u.name}</li>)` — `key` از نوع پایدار (id) نه index اگر لیست جابه‌جا می‌شود.

### ۸. جلسات ۴۱–۴۲

hooks و الگوهای عمیق‌تر بعداً؛ اینجا فقط پل ورود.

---

## اشتباه‌های رایج

1. **تغییر React-project ریپو.** علت: خارج از قرارداد دوره.
2. **any روی props.** علت: دور زدن مرز.
3. **اصرار بی‌دلیل به FC.** علت: عادت قدیمی.
4. **useState(null) بدون جنریک.** علت: inference غلط.
5. **key={index} همیشه.** علت: باگ UI هنگام جابه‌جایی.

---

## مثال گام‌به‌گام

فایل‌های زیر را بخوانید؛ در Vite اجرا کنید:

| فایل | موضوع |
|------|--------|
| [01-user-card.tsx.txt](./examples/01-user-card.tsx.txt) | props |
| [02-events.tsx.txt](./examples/02-events.tsx.txt) | event |
| [03-props-model.ts](./examples/03-props-model.ts) | مدل props بدون JSX |
| [04-state-children-notes.ts](./examples/04-state-children-notes.ts) | یادداشت state/children |

```bash
npx tsc --noEmit
```

## الگوی بهتر

props با type نام‌دار؛ event با generic عنصر؛ state با جنریک وقتی null؛ Vite شخصی؛ دست نزن به اپ آماده.

## تمرین‌ها

### آسان
`type ButtonProps = { label: string; onClick: () => void }`

### چالشی
در Vite، لیست `User[]` را با map و key تایپ‌شده رندر کنید.

## راهنمای حل

`users.map((user) => <li key={user.id}>{user.name}</li>)`

## خودآزمایی

1. props را کجا تایپ می‌کنید؟
2. ChangeEvent مال چیست؟
3. چرا React به این پوشه اضافه نشد؟
4. قالب Vite مناسب؟
5. آیا React-project را ویرایش کنیم؟
6. FC اجباری است؟
7. useState(null) چه دام دارد؟
8. ReactNode برای چیست؟
9. key خوب چیست؟
10. پل جلسات بعدی hooks کجاست؟

## جمع‌بندی و پل جلسهٔ بعد
پل React را ذهنی ساختید. جلسهٔ ۱۶: **capstone میانی Notes** — ماژول تایپ‌شده خالص داخل همین پوشه (بدون UI).
