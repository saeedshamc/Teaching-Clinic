// IODemo.java — System.out، printf، Scanner، تلهٔ nextInt+nextLine
import java.util.Scanner;

public class IODemo {

    public static void main(String[] args) {
        // ---------- System.out ----------
        System.out.println("println adds newline");
        System.out.print("print no newline ");
        System.out.println("— same line continues");

        // ---------- printf ----------
        System.out.printf("%d + %d = %d%n", 3, 4, 7);
        System.out.printf("%.2f%n", 3.14159);          // 3.14
        System.out.printf("%5d|%-5d|%05d%n", 42, 42, 42);  // width/align/pad
        System.out.printf("%s is %d years old%n", "Ali", 20);
        System.out.printf("%c%c%c%n", 'A', 'B', 'C');
        System.out.printf("%b%n", true);

        // ---------- Scanner ----------
        Scanner sc = new Scanner(System.in);

        // عدد صحیح
        System.out.print("Enter an int: ");
        int num = sc.nextInt();
        System.out.println("You entered: " + num);

        // تلهٔ nextInt + nextLine — \n باقی می‌ماند
        // sc.nextLine();   // این خط را اگر کامنت کنید، nextLine بعدی خالی است
        // (در اینجا برای دموی تعاملی نمی‌گذاریم — در پروژه می‌بینید)

        // رشتهٔ یک کلمه
        System.out.print("Enter a word: ");
        String word = sc.next();
        System.out.println("Word: " + word);

        // کل خط
        System.out.print("Enter a line: ");
        String line = sc.nextLine();
        System.out.println("Line: " + line);

        // double
        System.out.print("Enter a double: ");
        double d = sc.nextDouble();
        System.out.println("Double: " + d);

        // boolean
        System.out.print("Enter true/false: ");
        boolean b = sc.nextBoolean();
        System.out.println("Boolean: " + b);

        sc.close();
    }
}
