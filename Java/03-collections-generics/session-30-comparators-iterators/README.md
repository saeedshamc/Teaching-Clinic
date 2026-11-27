# جلسه ۳۰ — Comparator، Comparable و Iterator

## اهداف یادگیری
- `Comparable` و `Comparator` را بفهمید
- `compareTo` و `compare` را پیاده کنید
- `Collections.sort` و `List.sort` را به‌کار ببرید
- `Iterator` برای پیمایش و حذف امن را بشناسید
- lambda و method reference را برای comparator ببینید

## پیش‌نیازها
- جلسات ۲۵–۲۹

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Comparable — natural ordering

کلاس خودش می‌گوید «من چطور با دیگری مقایسه می‌شوم»:

```java
class Student implements Comparable<Student> {
    String name;
    int age;

    @Override
    public int compareTo(Student other) {
        return this.age - other.age;   // بر اساس سن
    }
}

List<Student> students = new ArrayList<>();
students.add(new Student("Ali", 20));
students.add(new Student("Sara", 18));
Collections.sort(students);   // بر اساس age
```

### Comparator — external ordering

بدون تغییر کلاس، ترتیب دلخواه:

```java
// بر اساس نام
Comparator<Student> byName = (a, b) -> a.name.compareTo(b.name);

// بر اساس سن نزولی
Comparator<Student> byAgeDesc = (a, b) -> b.age - a.age;

students.sort(byName);
students.sort(byAgeDesc);
```

### lambda و method reference

```java
// lambda
students.sort((a, b) -> a.name.compareTo(b.name));

// method reference — تمیزتر
students.sort(Comparator.comparing(s -> s.name));
students.sort(Comparator.comparingInt(s -> s.age).reversed());
students.sort(Comparator.comparing(s -> s.name, String.CASE_INSENSITIVE_ORDER));
```

### Iterator

```java
Iterator<String> it = names.iterator();
while (it.hasNext()) {
    String s = it.next();
    if (s.equals("remove me")) {
        it.remove();   // حذف امن در حین پیمایش
    }
}
```

## کد مهم

`src/ComparatorDemo.java` — Comparable، Comparator، lambda، Iterator.

## خطاهای رایج
- `compareTo` ناسازگار با `equals` → TreeSet/TreeMap رفتار غیرمنتظره.
- `Comparator` که `0` برنمی‌گرداند برای عناصر "برابر" → sort کار نمی‌کند درست.
- `it.remove()` قبل از `it.next()` → `IllegalStateException`.
- حذف با for-each → `ConcurrentModificationException` — از Iterator استفاده کنید.

## جمع‌بندی
- Comparable برای natural ordering، Comparator برای custom.
- `Collections.sort`/`List.sort` برای مرتب‌سازی.
- Iterator برای پیمایش امن و حذف.

**فاز ۲ کامل شد — فاز ۳: Exception، IO و Concurrency.**
