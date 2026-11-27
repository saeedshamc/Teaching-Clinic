// OperatorsDemo.java — همهٔ عملگرهای اصلی با مثال
public class OperatorsDemo {

    public static void main(String[] args) {
        // ---------- عملگرهای حسابی ----------
        int a = 17, b = 5;
        System.out.println("a+b=" + (a + b));   // 22
        System.out.println("a-b=" + (a - b));   // 12
        System.out.println("a*b=" + (a * b));   // 85
        System.out.println("a/b=" + (a / b));   // 3  (تقسیم صحیح int)
        System.out.println("a%b=" + (a % b));   // 2  (باقیمانده)
        System.out.println("double: " + (a / (double) b));  // 3.4 — cast

        // ---------- مقایسه‌ای ----------
        System.out.println("a>b=" + (a > b));   // true
        System.out.println("a==b=" + (a == b));// false
        System.out.println("a!=b=" + (a != b));// true

        // ---------- منطقی (short-circuit) ----------
        boolean x = true, y = false;
        System.out.println("x&&y=" + (x && y)); // false
        System.out.println("x||y=" + (x || y)); // true
        System.out.println("!x=" + (!x));       // false
        // short-circuit: اگر false && ... باشد بقیه ارزیابی نمی‌شود
        System.out.println("false && risky()=" + (false && risky()));

        // ---------- بیتی ----------
        int p = 0b1100, q = 0b1010;   // 12 و 10
        System.out.println("p&q=" + (p & q));   // 8  (AND بیتی)
        System.out.println("p|q=" + (p | q));   // 14 (OR بیتی)
        System.out.println("p^q=" + (p ^ q));   // 6  (XOR)
        System.out.println("p<<1=" + (p << 1));// 24 (shift left)
        System.out.println("~p=" + (~p));       // -13 (NOT بیتی)

        // ---------- compound و increment ----------
        int n = 10;
        n += 5;   // n = 15
        n -= 3;   // n = 12
        n *= 2;   // n = 24
        n++;      // n = 25 (post-increment: مقدار قدیمی برمی‌گردد)
        ++n;      // n = 26 (pre-increment: مقدار جدید برمی‌گردد)
        System.out.println("n=" + n);

        // ---------- تقدم عملگرها ----------
        // 2 + 3 * 4 = 14 نه 20 — * قبل از + است
        System.out.println("2+3*4=" + (2 + 3 * 4));
        // با پرانتز: (2+3)*4 = 20
        System.out.println("(2+3)*4=" + ((2 + 3) * 4));
    }

    // متد برای تست short-circuit — اگر صدا زده نشود یعنی short-circuit کار کرده
    static boolean risky() {
        System.out.println("risky() was called!");
        return true;
    }
}
