# جلسه ۱۵ — مینی‌پروژهٔ جمع‌بندی: سیستم نمره‌دهی دانش‌آموزان

## اهداف یادگیری
- همهٔ مباحث فاز ۱ را در یک پروژهٔ واقعی ترکیب کنید
- ساختار کلاس، متد، شرط، حلقه، آرایه، String و Scanner را هم‌زمان به‌کار ببرید
- کد تمیز و خوانا بنویسید

## پیش‌نیازها
- همهٔ جلسات ۰۱–۱۴ فاز ۱

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### پروژه: GradeManager

یک سیستم کنسولی برای مدیریت نمرات دانش‌آموزان بسازید که:

1. **مدل داده**: هر دانش‌آموز `name` (String)، `id` (int)، `grades` (double[5]) دارد.
2. **عملیات**:
   - افزودن دانش‌آموز جدید
   - نمایش لیست دانش‌آموزان با میانگین
   - جستجوی دانش‌آموز بر اساس نام یا id
   - محاسبهٔ بیشترین/کمترین/میانگین نمرات
   - مرتب‌سازی بر اساس میانگین (نزولی)
3. **خروجی**: جدول با `printf` قالب‌دار.

### معماری پیشنهادی

```java
class Student {
    String name;
    int id;
    double[] grades;
    double average() { ... }
    String gradeLetter() { ... }
}

class GradeManager {
    Student[] students;
    int count;
    void addStudent(...) { ... }
    void printAll() { ... }
    Student findByName(String name) { ... }
    void sortByAverage() { ... }
}
```

### چالش‌های فکری
- آرایهٔ `students` با اندازهٔ ثابت → وقتی پر شد چه کنید؟ (ArrayList در فاز ۲)
- جستجو با `equals()` نه `==` برای String.
- مرتب‌سازی بدون `Arrays.sort` → bubble/selection sort (یا بعداً comparator).
- میانگین با `BigDecimal` برای دقت؟ (در این مقیاس double کافی است)

## کد مهم

`src/GradeManager.java` — پیاده‌سازی کامل پروژه با همهٔ مباحث.

## خطاهای رایج
- `students[i]` روی null → NullPointerException — قبل از استفاده چک کنید.
- `grades[j]` با index خارج از محدوده → ArrayIndexOutOfBounds.
- `==` برای مقایسهٔ String name → همیشه `equals()`.
- فراموش کردن `sc.nextLine()` بعد از `nextInt()`/`nextDouble()`.

## جمع‌بندی فاز ۱
- Setup: JDK، IDE، Git ✅
- Core: syntax، متغیرها، تایپ‌ها، عملگرها، کنترل جریان، حلقه، آرایه، String، متد، I/O، cast، varargs، Math ✅
- **آماده برای فاز ۲: شی‌گرایی و کالکشن‌ها**

**فاز ۲ جلسهٔ بعد:** کلاس و آبجکت — مفاهیم عمیق OOP.
