# جلسه ۱۴ — مباحث متفرقهٔ Dart

## اهداف یادگیری
- `assert` برای debugging را بشناسید
- `typedef` برای function types استفاده کنید
- `enum` ساده را به‌کار ببرید
- `dynamic` vs `Object` را بفهمید

## پیش‌نیازها
- جلسهٔ ۱۳

## مدت زمان پیشنهادی
۵۰ دقیقه

## درس

### assert
```dart
void checkAge(int age) {
  assert(age >= 0, 'Age cannot be negative');   // فقط debug mode
  print('Age: $age');
}
```

### typedef
```dart
typedef IntOp = int Function(int, int);
int add(IntOp op, int a, int b) => op(a, b);
```

### enum
```dart
enum Color { red, green, blue }
var c = Color.red;
print(c.name);   // 'red'
print(c.index);  // 0
```

### dynamic vs Object
```dart
dynamic d = 'hello';   // هر متدی صدا می‌شود — unsafe
Object o = 'hello';    // فقط Object methods — safe
print(d.length);       // ok (dynamic)
// print(o.length);    // compile error
```

## کد مهم

`src/main.dart` — assert, typedef, enum, dynamic.

## خطاهای رایج
- `assert` در release mode اجرا نمی‌شود — برای validation واقعی استفاده نکنید
- `dynamic` → runtime errors — ترجیحاً `Object` یا generics
- `enum` بدون `.name` → index برمی‌گرداند

## جمع‌بندی
- `assert` برای debug، نه production validation
- `typedef` برای named function types
- `enum` برای ثابت‌های ثابت
- `dynamic` unsafe — `Object` safer

**جلسهٔ بعد:** پروژهٔ capstone مبانی.
