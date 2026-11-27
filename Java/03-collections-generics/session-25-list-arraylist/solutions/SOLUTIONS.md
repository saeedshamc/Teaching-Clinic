# پاسخ تمرین‌های جلسه ۲۵

## آسان

**۱.**
```java
List<Integer> nums = new ArrayList<>();
nums.add(10); nums.add(20); nums.add(30); nums.add(40); nums.add(50);
int sum = 0;
for (int n : nums) sum += n;
System.out.println(sum);   // 150
```

**۲.**
```java
List<String> names = new ArrayList<>();
names.add("Ali"); names.add("Sara"); names.add("Reza");
for (String s : names) System.out.println(s);
```

## متوسط

**۳.**
```java
List<String> list = List.of("a", "b", "c");
// list.add("d"); // UnsupportedOperationException!
```

**۴.**
```java
List<String> fixed = Arrays.asList("x", "y", "z");
fixed.set(0, "X");   // OK — set مجاز است
// fixed.add("w");   // خطا — fixed size
```

## سخت

**۵.**
```java
LinkedList<String> linked = new LinkedList<>();
linked.add("Middle");
linked.addFirst("First");
linked.addLast("Last");
System.out.println(linked);   // [First, Middle, Last]
```

**۶.**
```java
class Student { String name; int age; }
List<Student> students = new ArrayList<>();
students.add(new Student("Ali", 20));
System.out.println(students.get(0).name);
```
