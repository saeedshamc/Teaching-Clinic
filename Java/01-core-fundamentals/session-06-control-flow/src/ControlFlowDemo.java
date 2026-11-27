// ControlFlowDemo.java — if/else if، switch کلاسیک و expression
public class ControlFlowDemo {

    public static void main(String[] args) {
        int score = 85;

        // ---------- if / else if / else ----------
        if (score >= 90) {
            System.out.println("Grade: A");
        } else if (score >= 80) {
            System.out.println("Grade: B");   // این اجرا می‌شود
        } else if (score >= 70) {
            System.out.println("Grade: C");
        } else {
            System.out.println("Grade: F");
        }

        // ---------- switch کلاسیک ----------
        int day = 6;
        switch (day) {
            case 1 -> System.out.println("Saturday");   // arrow syntax (Java 14+)
            case 2 -> System.out.println("Sunday");
            case 3, 4, 5 -> System.out.println("Weekday");
            default -> System.out.println("Weekend");
        }

        // ---------- switch expression (مقدار برمی‌گرداند) ----------
        String name = switch (day) {
            case 6, 7 -> "Weekend";
            case 1 -> "Saturday";
            case 2 -> "Sunday";
            default -> "Weekday";
        };
        System.out.println("day=" + day + " → " + name);

        // ---------- switch روی String ----------
        String op = "+";
        int result = switch (op) {
            case "+" -> 10 + 5;
            case "-" -> 10 - 5;
            case "*" -> 10 * 5;
            default -> 0;
        };
        System.out.println("10 " + op + " 5 = " + result);

        // ---------- early return ----------
        printIfAdult(20);   // Age: 20
        printIfAdult(10);   // چاپ نمی‌شود
    }

    // الگوی guard clause — خروج زودهنگام برای ورودی نامعتبر
    static void printIfAdult(int age) {
        if (age < 0) return;
        if (age > 150) return;
        if (age < 18) return;      // زیر ۱۸ → خروج
        System.out.println("Age: " + age);
    }
}
