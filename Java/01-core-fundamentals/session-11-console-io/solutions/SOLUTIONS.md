# پاسخ تمرین‌های جلسه ۱۱

## آسان

**۱.**
```java
System.out.printf("%d + %d = %d%n", 3, 4, 7);
System.out.printf("%s is %.2f years old%n", "Ali", 20.5);
```

**۲.**
```java
Scanner sc = new Scanner(System.in);
System.out.print("Name: ");
String name = sc.nextLine();
System.out.print("Age: ");
int age = sc.nextInt();
sc.nextLine();   // بافر \n را بخور
System.out.printf("%s is %d years old%n", name, age);
```

## متوسط

**۳.**
```java
Scanner sc = new Scanner(System.in);
double a = sc.nextDouble();
double b = sc.nextDouble();
double c = sc.nextDouble();
System.out.printf("Average: %.2f%n", (a + b + c) / 3);
```

**۴.**
```java
// بدون sc.nextLine() اضافی:
int age = sc.nextInt();        // "25" می‌خواند، \n می‌ماند
String name = sc.nextLine();   // "" برمی‌گرداند — باگ!
// با sc.nextLine() اضافی:
sc.nextLine();                  // \n را می‌خورد
String name2 = sc.nextLine();  // درست کار می‌کند
```

## سخت

**۵.**
```java
if (sc.hasNextInt()) {
    int n = sc.nextInt();
    System.out.println("Got: " + n);
} else {
    System.out.println("Not a number: " + sc.next());
}
```

**۶.**
```java
System.out.printf("%-10s %-5s %-8s%n", "Name", "Age", "Score");
System.out.printf("%-10s %-5d %-8.2f%n", "Ali", 20, 17.5);
System.out.printf("%-10s %-5d %-8.2f%n", "Sara", 22, 18.0);
```
