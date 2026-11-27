# جلسه ۰۸ — آرایه‌ها (Arrays)

## اهداف یادگیری
- آرایهٔ یک‌بعدی و چندبعدی را تعریف و استفاده کنید
- `Arrays` utility class را بشناسید (sort, binarySearch, fill, equals)
- `for-each` loop روی آرایه را به‌کار ببرید
- `ArrayIndexOutOfBoundsException` را بفهمید

## پیش‌نیازها
- جلسات ۰۴–۰۷

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### آرایهٔ یک‌بعدی

```java
// روش ۱: مقداردهی اولیه
int[] numbers = {1, 2, 3, 4, 5};

// روش ۲: با اندازه
int[] arr = new int[5];        // همه 0
arr[0] = 10; arr[4] = 50;

// روش ۳: با مقادیر
int[] vals = new int[]{7, 8, 9};

// طول ثابت — arr.length
System.out.println(numbers.length);   // 5
```

### دسترسی و پیمایش

```java
// index loop
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}

// for-each — روی آرایه و کالکشن کار می‌کند
for (int n : numbers) {
    System.out.println(n);
}
```

### آرایهٔ چندبعدی (matrix)

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
System.out.println(matrix[1][2]);   // 6 — سطر ۱، ستون ۲

for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();
}
```

### Arrays utility (java.util.Arrays)

```java
import java.util.Arrays;

int[] nums = {5, 2, 8, 1, 9};
Arrays.sort(nums);                        // مرتب‌سازی
System.out.println(Arrays.toString(nums)); // [1, 2, 5, 8, 9]

int idx = Arrays.binarySearch(nums, 5);   // index 2 (باید sorted باشد)
Arrays.fill(nums, 0, 3, -1);              // nums[0..2] = -1

boolean eq = Arrays.equals(nums, nums);   // مقایسهٔ محتوا
int[] copy = Arrays.copyOf(nums, 10);     // کپی با اندازهٔ جدید
```

### ArrayIndexOutOfBoundsException

```java
int[] arr = new int[3];
arr[3] = 10;   // خطا! index 0..2 معتبر است — arr.length = 3
```

## کد مهم

`src/ArraysDemo.java` — تعریف، پیمایش for/for-each، چندبعدی، Arrays utility، و خطای index.

## خطاهای رایج
- `ArrayIndexOutOfBoundsException` → index از `0` تا `length-1` معتبر است.
- `arr.length` روی `null` یا نوع اشتباه → `NullPointerException` یا compile error.
- `Arrays.binarySearch` روی آرایهٔ **unsorted** → نتیجهٔ نامعتبر.
- `arr` را بعد از تعریف resize نمی‌کنید — طول ثابت است؛ برای اندازهٔ متغیر از `ArrayList` (فاز ۲) استفاده کنید.

## جمع‌بندی
- آرایه = طول ثابت، index از ۰، `length` فیلد.
- `for-each` برای پیمایش ساده، `Arrays` برای sort/search/fill/copy.
- چندبعدی = آرایه‌ای از آرایه‌ها.

**جلسهٔ بعد:** String و StringBuilder.
