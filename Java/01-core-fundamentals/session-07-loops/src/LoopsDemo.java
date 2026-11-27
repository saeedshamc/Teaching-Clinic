// LoopsDemo.java — for/while/do-while + break/continue + nested
public class LoopsDemo {

    public static void main(String[] args) {
        // ---------- for ----------
        System.out.println("for 0..4:");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // for با گام دلخواه
        System.out.println("for i+=10:");
        for (int i = 0; i < 100; i += 10) {
            System.out.print(i + " ");
        }
        System.out.println();

        // for معکوس
        System.out.println("for reverse:");
        for (int i = 9; i >= 0; i--) {
            System.out.print(i + " ");
        }
        System.out.println();

        // ---------- while ----------
        System.out.println("while:");
        int w = 0;
        while (w < 3) {
            System.out.print(w + " ");
            w++;
        }
        System.out.println();

        // ---------- do-while (حداقل یک‌بار) ----------
        System.out.println("do-while:");
        int d = 10;
        do {
            System.out.print(d + " ");
            d++;
        } while (d < 12);   // حتی اگر شرط false باشد یک‌بار اجرا می‌شود
        System.out.println();

        // ---------- break / continue ----------
        System.out.println("break/continue:");
        for (int i = 0; i < 10; i++) {
            if (i == 3) continue;   // ۳ چاپ نمی‌شود
            if (i == 7) break;      // بعد از ۷ خارج می‌شود
            System.out.print(i + " ");
        }
        System.out.println();   // 0 1 2 4 5 6

        // ---------- nested loop ----------
        System.out.println("multiplication table:");
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.print(i * j + "\t");
            }
            System.out.println();
        }

        // ---------- الگوی sum ----------
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println("sum 1..100 = " + sum);   // 5050
    }
}
