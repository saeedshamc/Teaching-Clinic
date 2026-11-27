# پاسخ تمرین‌های جلسه ۱۵

## آسان

**۱. حذف با id:**
```java
void removeById(int id) {
    for (int i = 0; i < count; i++) {
        if (students[i].id == id) {
            for (int j = i; j < count - 1; j++) {
                students[j] = students[j + 1];
            }
            students[--count] = null;
            return;
        }
    }
}
```

**۲. آمار کلی:**
```java
double totalAvg = 0;
for (int i = 0; i < count; i++) totalAvg += students[i].average();
System.out.println("Total students: " + count + ", Class avg: " + (totalAvg / count));
```

## متوسط

**۳. ورودی کاربر:**
```java
System.out.print("Number of students: ");
int n = sc.nextInt();
sc.nextLine();
for (int i = 0; i < n; i++) {
    System.out.print("Name: ");
    String name = sc.nextLine();
    System.out.print("ID: ");
    int id = sc.nextInt();
    sc.nextLine();
    System.out.print("Grades (5): ");
    double[] grades = new double[5];
    for (int j = 0; j < 5; j++) grades[j] = sc.nextDouble();
    sc.nextLine();
    gm.addStudent(new Student(name, id, grades));
}
```

**۴. فیلتر:**
```java
void printAbove(double threshold) {
    for (int i = 0; i < count; i++) {
        if (students[i].average() >= threshold) {
            System.out.printf("%-15s avg=%.1f%n", students[i].name, students[i].average());
        }
    }
}
```

## سخت

**۵.** در فاز ۲ با `Comparator.comparingDouble(Student::average)` مرتب‌سازی را بازنویسی کنید.

**۶.** در فاز ۳ با `PrintWriter` خروجی را به فایل بنویسید.
