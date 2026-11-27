# پاسخ تمرین‌های جلسه ۲۶

## آسان

**۱.**
```java
Set<Integer> nums = new HashSet<>();
Random rand = new Random();
for (int i = 0; i < 10; i++) nums.add(rand.nextInt(20));
System.out.println(nums);   // ممکن است کمتر از ۱۰ باشد — duplicateها حذف شدند
```

**۲.**
```java
Set<Integer> sorted = new TreeSet<>();
sorted.add(5); sorted.add(1); sorted.add(9); sorted.add(3); sorted.add(7);
// [1, 3, 5, 7, 9]
```

## متوسط

**۳.**
```java
Set<Integer> a = new HashSet<>(Set.of(1,2,3,4));
Set<Integer> b = new HashSet<>(Set.of(3,4,5,6));
Set<Integer> union = new HashSet<>(a); union.addAll(b);
Set<Integer> inter = new HashSet<>(a); inter.retainAll(b);
Set<Integer> diff = new HashSet<>(a); diff.removeAll(b);
```

**۴.**
```java
Set<String> ordered = new LinkedHashSet<>();
ordered.add("First"); ordered.add("Second"); ordered.add("Third");
// [First, Second, Third] — ترتیب درج حفظ می‌شود
```

## سخت

**۵.**
```java
String[] words = {"hello", "world", "hello", "java", "world"};
Set<String> unique = new HashSet<>(Arrays.asList(words));
// [hello, world, java]
```

**۶.**
```java
class Student {
    int id; String name;
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return id == s.id;
    }
    @Override public int hashCode() { return id; }
}
Set<Student> students = new HashSet<>();
students.add(new Student(1, "Ali"));
students.add(new Student(1, "Ali2"));   // duplicate — id یکسان
// size = 1
```
