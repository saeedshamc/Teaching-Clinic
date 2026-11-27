# جلسه ۱۱ — ورودی/خروجی کنسول

## اهداف یادگیری
- `System.out` برای خروجی را بشناسید (`println`, `print`, `printf`)
- `Scanner` برای ورودی کاربر را به‌کار ببرید (`nextInt`, `nextLine`, `nextDouble`...)
- `printf`/`String.format` را برای خروجی قالب‌دار بفهمید
- تلهٔ `nextInt` + `nextLine` را بشناسید

## پیش‌نیازها
- جلسات ۰۴–۱۰

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### System.out — خروجی

```java
System.out.println("Hello");        // + خط جدید
System.out.print("Hello");          // بدون خط جدید
System.out.printf("%d + %d = %d%n", 3, 4, 7);   // قالب‌دار + خط جدید
System.out.format("%.2f", 3.14159);            // 3.14
```

### printf format specifiers

| specifier | معنی |
|---|---|
| `%d` | int/long |
| `%f` | double/float (`%.2f` دو رقم) |
| `%s` | String/object |
| `%c` | char |
| `%b` | boolean |
| `%n` | خط جدید (platform-independent) |
| `%5d` | عرض ۵، راست‌چین |
| `%-5d` | چپ‌چین |
| `%05d` | با صفر padding |

### Scanner — ورودی

```java
import java.util.Scanner;

Scanner sc = new Scanner(System.in);

int age = sc.nextInt();            // عدد صحیح
double price = sc.nextDouble();    // اعشار
String name = sc.next();           // یک کلمه (تا space)
String line = sc.nextLine();       // کل خط
boolean flag = sc.nextBoolean();   // true/false
```

### تلهٔ nextInt + nextLine

```java
Scanner sc = new Scanner(System.in);
int age = sc.nextInt();            // "25" را می‌خواند ولی \n را در بافر می‌گذارد
String name = sc.nextLine();       // خالی برمی‌گرداند! چون \n باقی‌مانده
// رفع: بعد از nextInt یک sc.nextLine() اضافی بزنید تا \n را بخورد
sc.nextLine();
String name2 = sc.nextLine();      // حالا درست کار می‌کند
```

### مثال کامل

```java
import java.util.Scanner;

public class IO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();   // بافر \n را بخور
        System.out.print("City: ");
        String city = sc.nextLine();
        System.out.printf("%s is %d years old from %s%n", name, age, city);
    }
}
```

## کد مهم

`src/IODemo.java` — printf، Scanner با همهٔ متدها، تلهٔ nextLine، مثال کامل.

## خطاهای رایج
- `nextInt` بعد `nextLine` خالی برمی‌گرداند → `sc.nextLine()` اضافی بزنید.
- `InputMismatchException` → کاربر به‌جای عدد حروف وارد کرد؛ با `hasNextInt()` چک کنید.
- `printf` با `%d` برای double → `IllegalFormatConversionException`.
- `Scanner` را نبندید اگر System.in است (می‌توانید ولی بعداً نمی‌توانید بخوانید).

## جمع‌بندی
- `System.out` برای خروجی، `Scanner(System.in)` برای ورودی.
- `printf` برای قالب‌دار، `nextLine` برای خط کامل.
- تلهٔ `nextInt`+`nextLine` را با `sc.nextLine()` اضافی حل کنید.

**جلسهٔ بعد:** cast و type conversion.
