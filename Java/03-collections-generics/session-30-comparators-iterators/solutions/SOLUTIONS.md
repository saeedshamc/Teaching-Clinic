# پاسخ تمرین‌های جلسه ۳۰

## آسان

**۱.**
```java
List<Integer> nums = new ArrayList<>(List.of(5, 2, 8, 1, 9));
Collections.sort(nums);   // [1, 2, 5, 8, 9]
```

**۲.**
```java
Iterator<String> it = names.iterator();
while (it.hasNext()) System.out.println(it.next());
```

## متوسط

**۳.**
```java
students.sort((a, b) -> b.name.compareTo(a.name));   // نزولی
```

**۴.**
```java
students.sort(Comparator.comparingInt(s -> s.age));   // صعودی بر اساس سن
students.sort(Comparator.comparingInt(s -> s.age).reversed());  // نزولی
```

## سخت

**۵.**
```java
students.sort(Comparator.comparingInt((Student s) -> s.age)
        .thenComparing(s -> s.name));
```

**۶.**
```java
Iterator<Student> it = students.iterator();
while (it.hasNext()) {
    Student s = it.next();
    if (s.age < 18) it.remove();
}
```
